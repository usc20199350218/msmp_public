package com.yw.msmp.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.common.util.OrderIdGenerator;
import com.yw.msmp.dto.AlipayDTO;
import com.yw.msmp.dto.OnlineOrderDTO;
import com.yw.msmp.dto.SearchOrderDTO;
import com.yw.msmp.entity.*;
import com.yw.msmp.enums.BatchStatusEnum;
import com.yw.msmp.enums.OrderTypeEnum;
import com.yw.msmp.enums.PayStatus;
import com.yw.msmp.enums.PaymentEnum;
import com.yw.msmp.mapper.*;
import com.yw.msmp.service.AlipayService;
import com.yw.msmp.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author yanhaoyuwen
 */
@Service
@Log4j2
public class OrderServiceImpl extends ServiceImpl< OrderMapper, OrderEntity > implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private StoreBatchMapper storeBatchMapper;

    @Resource
    private DrugDetailsMapper drugDetailsMapper;

    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Resource
    private DrugMapper drugMapper;

    @Resource
    private AlipayService alipayService;

    @Resource
    private AddressMapper addressMapper;

    @Override
    public void cancel( Integer orderId ) {
        // 检查是否已经支付
        OrderEntity order = orderMapper.selectById( orderId );
        if ( PayStatus.SUCCESS.toString( )
                              .equals( order.getStatus( ) ) ) {
            throw new AppException( ResponseEnum.PAY_FINISH_ERROR );
        }

        // 更新状态
        orderMapper.updateById( OrderEntity.builder( )
                                           .orderId( orderId )
                                           .status( PayStatus.GIVE_UP.toString( ) )
                                           .build( ) );

        // 获取订单详情数据
        QueryWrapper< OrderDetailEntity > orderDetailEntityQueryWrapper = new QueryWrapper<>( );
        orderDetailEntityQueryWrapper.eq( "order_id", order.getOrderId( ) );
        OrderDetailEntity orderDetail = orderDetailMapper.selectOne( orderDetailEntityQueryWrapper );
        // 修改批次
        StoreBatchEntity storeBatch = storeBatchMapper.selectById( orderDetail.getStoreBatchId( ) );
        storeBatch.setStoreBatchExistingQuantity( storeBatch.getStoreBatchExistingQuantity( ) + orderDetail.getQuantity( ) );
        storeBatchMapper.updateById( storeBatch );
    }

    /**
     * @param userId  用户id
     * @param payment 支付方式
     * @param orderId 订单id
     */
    @Override
    public void continueOrder( Integer userId, String payment, Integer orderId ) {

        // 检查支付状态,避免重复支付
        OrderEntity check = orderMapper.selectById( orderId );
        if ( PayStatus.SUCCESS.toString( )
                              .equals( check.getStatus( ) ) ) {
            throw new AppException( ResponseEnum.PAY_OVER_ERROR );
        }

        String paymentEng = PaymentEnum.fromString( payment )
                                       .toString( );

        OrderEntity order = OrderEntity.builder( )
                                       .orderId( orderId )
                                       .paymentMethod( paymentEng )
                                       .orderType( OrderTypeEnum.OFFLINE.toString( ) )
                                       .build( );

        if ( !Objects.isNull( userId ) ) {
            order.setUserId( userId );
        }

        // 现金支付，默认成功
        if ( PaymentEnum.CASH.toString( )
                             .equals( paymentEng ) ) {
            // 更新状态
            order.setStatus( PayStatus.SUCCESS.toString( ) );
            // 设置当前时间为支付时间
            order.setPaymentTime( new Date( ) );
        }
        else {
            // 更新状态
            order.setStatus( PayStatus.ING.toString( ) );
        }
        log.info( "order:" + order );
        // 更新
        orderMapper.updateById( order );
    }

    /**
     * @param orderId 订单id
     * @return 支付结果
     */
    @Override
    public boolean examineOrderId( Integer orderId ) {
        OrderEntity order = orderMapper.selectById( orderId );
        log.info( "order:" + order );
        if ( PayStatus.SUCCESS.toString( )
                              .equals( order.getStatus( ) ) ) {
            log.info( "支付成功" );
            return true;
        }
        return false;
    }

    /**
     * @param onlineOrderDTO 创建订单的数据
     * @return
     */
    @Override
    public AlipayTradePagePayResponse createOnlineOrder( OnlineOrderDTO onlineOrderDTO ) {
        // 整理数组
        List< Integer > drugDetailIdList = onlineOrderDTO.getDrugDetailIdList( );
        Map< Integer, Integer > idMap = new HashMap<>( );
        for ( Integer id : drugDetailIdList ) {
            // 是否存在
            if ( idMap.containsKey( id ) ) {
                idMap.put( id, idMap.get( id ) + 1 );
                // 计数器加1或者其他需要更新的操作
            }
            else {
                idMap.put( id, 1 );
                // 初始化计数器或者其他需要更新的操作
            }
        }

        BigDecimal decimal = new BigDecimal( 0 );

        // 生成订单
        String orderNum = OrderIdGenerator.generateOrderId( );
        OrderEntity order = OrderEntity.builder( )
                                       .orderNum( orderNum )
                                       .userId( onlineOrderDTO.getUserId( ) )
                                       .status( PayStatus.CREATED.toString( ) )
                                       .operateUserId( onlineOrderDTO.getUserId( ) )
                                       .amount( onlineOrderDTO.getTotalPrice( ) )
                                       .paymentMethod( PaymentEnum.ALIPAY.toString( ) )
                                       .remark( onlineOrderDTO.getRemark( ) )
                                       .deliveryAddress( onlineOrderDTO.getAddress( ) )
                                       .storeId( onlineOrderDTO.getStoreId( ) )
                                       .build( );
        orderMapper.insert( order );

        log.info( "==========" );
        // 遍历 药品详情id map
        for ( Map.Entry< Integer, Integer > entry : idMap.entrySet( ) ) {
            // 当前遍历数据
            Integer drugDetailId = entry.getKey( );
            Integer num = entry.getValue( );

            // 判断数量总体是否满足
            Integer sum = storeBatchMapper.selectSum( onlineOrderDTO.getStoreId( ),
                                                      drugDetailId,
                                                      BatchStatusEnum.SOLD );
            log.info( "num:" + num );
            log.info( "sum:" + sum );
            if ( num > sum ) {
                throw new AppException( ResponseEnum.INVENTORY_SHORTAGE_ERROR );
            }

            // 取药品详情信息
            DrugDetailsEntity drugDetail = drugDetailsMapper.selectById( drugDetailId );
            DrugEntity drug = drugMapper.selectById( drugDetail.getDrugId( ) );
            // 计算金额
            decimal = decimal.add( drugDetail.getDrugRetailPrice( )
                                             .multiply( new BigDecimal( num ) ) );

            // 获取店内该药品可售数据
            QueryWrapper< StoreBatchEntity > storeBatchEntityQueryWrapper = new QueryWrapper<>( );
            storeBatchEntityQueryWrapper.eq( "store_id", onlineOrderDTO.getStoreId( ) )
                                        .eq( "drug_detail_id", drugDetailId )
                                        .eq( "store_batch_status", BatchStatusEnum.SOLD )
                                        .orderByAsc( "store_batch_id" );
            List< StoreBatchEntity > storeBatchList = storeBatchMapper.selectList( storeBatchEntityQueryWrapper );
            log.info( "对应可用的list" + storeBatchList );

            // 更新店内数量
            for ( StoreBatchEntity storeBatchEntity : storeBatchList ) {
                if ( num > 0 ) {

                    // 插入详情数据 - num后续会清零
                    int insertOrder = orderDetailMapper.insert( OrderDetailEntity.builder( )
                                                                                 .orderId( order.getOrderId( ) )
                                                                                 .drugDetailId( drugDetailId )
                                                                                 .drugName( drug.getDrugName( ) )
                                                                                 .drugPrice( drugDetail.getDrugRetailPrice( ) )
                                                                                 .totalPrice( drugDetail.getDrugRetailPrice( )
                                                                                                        .multiply( new BigDecimal(
                                                                                                                num ) ) )
                                                                                 .quantity( num )
                                                                                 .storeBatchId( storeBatchEntity.getStoreBatchId( ) )
                                                                                 .build( ) );
                    if ( insertOrder != 1 ) {
                        throw new AppException( ResponseEnum.ADD_ERROR );
                    }

                    // 满足 且 有剩余
                    if ( storeBatchEntity.getStoreBatchExistingQuantity( ) > num ) {
                        storeBatchEntity.setStoreBatchExistingQuantity( storeBatchEntity.getStoreBatchExistingQuantity( ) - num );
                        storeBatchMapper.updateById( storeBatchEntity );
                        num = 0;
                    }
                    else if ( storeBatchEntity.getStoreBatchExistingQuantity( )
                                              .equals( num ) ) {
                        // 刚刚好
                        num = 0;
                        storeBatchEntity.setStoreBatchExistingQuantity( 0 );
                        storeBatchEntity.setStoreBatchStatus( BatchStatusEnum.SOLD_OUT.toString( ) );
                        storeBatchMapper.updateById( storeBatchEntity );
                    }
                    else {
                        // 不满足
                        num -= storeBatchEntity.getStoreBatchExistingQuantity( );
                        storeBatchEntity.setStoreBatchExistingQuantity( 0 );
                        storeBatchEntity.setStoreBatchStatus( BatchStatusEnum.SOLD_OUT.toString( ) );
                        storeBatchMapper.updateById( storeBatchEntity );
                    }
                }
                else {
                    break;
                }
            }

        }
        log.info( "---------" );

        // 对比金额
        log.info( "前端的价格为:" + onlineOrderDTO.getTotalPrice( ) );
        log.info( "计算的价格为:" + decimal );
        if ( onlineOrderDTO.getTotalPrice( )
                           .compareTo( decimal ) == 0 ) {
            // 价格不对，更新价格
            order.setAmount( decimal );
            int updatedOrder = orderMapper.updateById( order );
            if ( updatedOrder != 1 ) {
                throw new AppException( ResponseEnum.UPDATE_ERROR );
            }
        }
        // 调用支付
        AlipayTradePagePayResponse alipayTradePagePayResponse;
        try {
            log.info( "正常，调用支付" );
            alipayTradePagePayResponse = alipayService.computerScanCode( AlipayDTO.builder( )
                                                                                  .totalAmount( decimal )
                                                                                  .storeId( onlineOrderDTO.getStoreId( )
                                                                                                          .toString( ) )
                                                                                  .merchantOrderNo( orderNum )
                                                                                  .userId( onlineOrderDTO.getUserId( ) )
                                                                                  .build( ) );
        } catch ( AlipayApiException e ) {
            throw new AppException( ResponseEnum.PAY_ERROR );
        }
        // 返回支付
        return alipayTradePagePayResponse;
    }

    /**
     * 生成的在线订单
     *
     * @param onlineOrderDTO 订单
     * @return 订单信息
     */
    @Override
    public AlipayDTO createOnlineOrderPlus( OnlineOrderDTO onlineOrderDTO ) {
        // 整理数组
        List< Integer > drugDetailIdList = onlineOrderDTO.getDrugDetailIdList( );
        Map< Integer, Integer > idMap = new HashMap<>( );
        for ( Integer id : drugDetailIdList ) {
            // 是否存在
            if ( idMap.containsKey( id ) ) {
                idMap.put( id, idMap.get( id ) + 1 );
                // 计数器加1或者其他需要更新的操作
            }
            else {
                idMap.put( id, 1 );
                // 初始化计数器或者其他需要更新的操作
            }
        }

        BigDecimal decimal = new BigDecimal( 0 );

        // 生成订单编号
        String orderNum = OrderIdGenerator.generateOrderId( );
        // 搜索地址
        AddressEntity address = addressMapper.selectById( onlineOrderDTO.getAddressId( ) );

        // 生成订单
        OrderEntity order = OrderEntity.builder( )
                                       .orderNum( orderNum )
                                       .userId( onlineOrderDTO.getUserId( ) )
                                       .operateUserId( onlineOrderDTO.getUserId( ) )
                                       .amount( onlineOrderDTO.getTotalPrice( ) )
                                       .status( PayStatus.CREATED.toString( ) )
                                       .paymentMethod( PaymentEnum.ALIPAY.toString( ) )
                                       .deliveryAddress( address.getAddressContent( ) )
                                       .userName( address.getUserName( ) )
                                       .userPhone( address.getUserPhone( ) )
                                       .remark( onlineOrderDTO.getRemark( ) )
                                       .orderType( OrderTypeEnum.ONLINE.toString( ) )
                                       .storeId( onlineOrderDTO.getStoreId( ) )
                                       .build( );
        // 插入订单
        int insert = orderMapper.insert( order );
        if ( insert != 1 ) {
            throw new AppException( ResponseEnum.CHECK_ERROR );
        }

        log.info( "==========" );
        // 遍历 药品详情id map
        for ( Map.Entry< Integer, Integer > entry : idMap.entrySet( ) ) {
            // 当前遍历数据
            Integer drugDetailId = entry.getKey( );
            Integer num = entry.getValue( );

            // 判断数量总体是否满足
            Integer sum = storeBatchMapper.selectSum( onlineOrderDTO.getStoreId( ),
                                                      drugDetailId,
                                                      BatchStatusEnum.SOLD );
            log.info( "num:" + num );
            log.info( "sum:" + sum );
            if ( num > sum ) {
                throw new AppException( ResponseEnum.INVENTORY_SHORTAGE_ERROR );
            }

            // 取药品详情信息
            DrugDetailsEntity drugDetail = drugDetailsMapper.selectById( drugDetailId );
            DrugEntity drug = drugMapper.selectById( drugDetail.getDrugId( ) );
            // 计算金额
            decimal = decimal.add( drugDetail.getDrugRetailPrice( )
                                             .multiply( new BigDecimal( num ) ) );

            // 获取店内该药品可售数据
            QueryWrapper< StoreBatchEntity > storeBatchEntityQueryWrapper = new QueryWrapper<>( );
            storeBatchEntityQueryWrapper.eq( "store_id", onlineOrderDTO.getStoreId( ) )
                                        .eq( "drug_detail_id", drugDetailId )
                                        .eq( "store_batch_status", BatchStatusEnum.SOLD )
                                        .orderByAsc( "store_batch_id" );
            List< StoreBatchEntity > storeBatchList = storeBatchMapper.selectList( storeBatchEntityQueryWrapper );
            log.info( "对应可用的list" + storeBatchList );

            // 更新店内数量
            for ( StoreBatchEntity storeBatchEntity : storeBatchList ) {
                if ( num > 0 ) {

                    // 插入详情数据 - num后续会清零
                    int insertOrder = orderDetailMapper.insert( OrderDetailEntity.builder( )
                                                                                 .orderId( order.getOrderId( ) )
                                                                                 .drugDetailId( drugDetailId )
                                                                                 .drugName( drug.getDrugName( ) )
                                                                                 .drugPrice( drugDetail.getDrugRetailPrice( ) )
                                                                                 .totalPrice( drugDetail.getDrugRetailPrice( )
                                                                                                        .multiply( new BigDecimal(
                                                                                                                num ) ) )
                                                                                 .quantity( num )
                                                                                 .storeBatchId( storeBatchEntity.getStoreBatchId( ) )
                                                                                 .build( ) );
                    if ( insertOrder != 1 ) {
                        throw new AppException( ResponseEnum.ADD_ERROR );
                    }

                    // 满足 且 有剩余
                    if ( storeBatchEntity.getStoreBatchExistingQuantity( ) > num ) {
                        storeBatchEntity.setStoreBatchExistingQuantity( storeBatchEntity.getStoreBatchExistingQuantity( ) - num );
                        storeBatchMapper.updateById( storeBatchEntity );
                        num = 0;
                    }
                    else if ( storeBatchEntity.getStoreBatchExistingQuantity( )
                                              .equals( num ) ) {
                        // 刚刚好
                        num = 0;
                        storeBatchEntity.setStoreBatchExistingQuantity( 0 );
                        storeBatchEntity.setStoreBatchStatus( BatchStatusEnum.SOLD_OUT.toString( ) );
                        storeBatchMapper.updateById( storeBatchEntity );
                    }
                    else {
                        // 不满足
                        num -= storeBatchEntity.getStoreBatchExistingQuantity( );
                        storeBatchEntity.setStoreBatchExistingQuantity( 0 );
                        storeBatchEntity.setStoreBatchStatus( BatchStatusEnum.SOLD_OUT.toString( ) );
                        storeBatchMapper.updateById( storeBatchEntity );
                    }
                }
                else {
                    break;
                }
            }

        }
        log.info( "---------" );

        // 对比金额
        log.info( "前端的价格为:" + onlineOrderDTO.getTotalPrice( ) );
        log.info( "计算的价格为:" + decimal );
        if ( onlineOrderDTO.getTotalPrice( )
                           .compareTo( decimal ) == 0 ) {
            // 价格不对，更新价格
            order.setAmount( decimal );
            int updatedOrder = orderMapper.updateById( order );
            if ( updatedOrder != 1 ) {
                throw new AppException( ResponseEnum.UPDATE_ERROR );
            }
        }
        //        // 调用支付
        //        AlipayTradePagePayResponse alipayTradePagePayResponse;
        //        try {
        //            log.info( "正常，调用支付" );
        //            alipayTradePagePayResponse = alipayService.computerScanCode(
        //                    AlipayDTO
        //                            .builder( )
        //                            .totalAmount( decimal )
        //                            .storeId( onlineOrderDTO.getStoreId( )
        //                                                    .toString( ) )
        //                            .merchantOrderNo( orderNum )
        //                            .userId( onlineOrderDTO.getUserId( ) )
        //                            .build( ) );
        //        } catch ( AlipayApiException e ) {
        //            throw new AppException( ResponseEnum.PAY_ERROR );
        //        }
        //        // 返回支付
        //        return alipayTradePagePayResponse;
        return AlipayDTO.builder( )
                        .totalAmount( decimal )
                        .merchantOrderNo( orderNum )
                        .build( );
    }

    /**
     * @param orderNum
     * @return
     */
    @Override
    public Boolean examineStatus( String orderNum ) {
        QueryWrapper< OrderEntity > orderQueryWrapper = new QueryWrapper<>( );
        orderQueryWrapper.eq( "order_num", orderNum );
        OrderEntity order = orderMapper.selectOne( orderQueryWrapper );
        if ( PayStatus.SUCCESS.toString( )
                              .equals( order.getStatus( ) ) ) {
            // 支付成功
            return true;
        }
        throw new AppException( ResponseEnum.PAY_NULL_ERROR );
    }

    /**
     * 根据类型获取所有的订单
     *
     * @param orderType 订单类型
     * @return List<OrderEntity>
     */
    @Override
    public List< OrderEntity > getAllList( String orderType ) {
        QueryWrapper< OrderEntity > orderQueryWrapper = new QueryWrapper<>( );
        orderQueryWrapper.eq( "order_type", orderType );
        List< OrderEntity > orderEntities = orderMapper.selectList( orderQueryWrapper );
        log.info( orderType + "orderEntities:" + orderEntities );
        return orderEntities;
    }

    /**
     * 根据用户id和订单类型查询订单
     *
     * @param userId         用户id
     * @param searchOrderDTO 订单数据
     * @return IPage<OrderEntity>
     */
    @Override
    public IPage< OrderEntity > searchOrder( Integer userId, SearchOrderDTO searchOrderDTO ) {
        String orderType = StringUtils.isNotBlank( searchOrderDTO.getOrderType( ) ) ? OrderTypeEnum.fromString(
                                                                                                           searchOrderDTO.getOrderType( ) )
                                                                                                   .toString( ) : null;
        String payStatus = StringUtils.isNotBlank( searchOrderDTO.getPayStatus( ) ) ? PayStatus.fromString(
                                                                                                       searchOrderDTO.getPayStatus( ) )
                                                                                               .toString( ) : null;
        String payment = StringUtils.isNotBlank( searchOrderDTO.getPayment( ) ) ? PaymentEnum.fromString( searchOrderDTO.getPayment( ) )
                                                                                             .toString( ) : null;

        Page< OrderEntity > orderEntityPage = new Page<>( searchOrderDTO.getCurrent( ), searchOrderDTO.getSize( ) );
        IPage< OrderEntity > page = orderMapper.searchOrderPage( userId,
                                                                 orderType,
                                                                 searchOrderDTO.getStoreId( ),
                                                                 payStatus,
                                                                 payment,
                                                                 searchOrderDTO.getStartDate( ),
                                                                 searchOrderDTO.getEndDate( ),
                                                                 searchOrderDTO.getSearchMethod( ),
                                                                 searchOrderDTO.getSearchText( ),
                                                                 orderEntityPage );
        log.info( "list:" + page );
        return page;
    }

    /**
     * 根据订单id获取订单详情
     *
     * @param orderId 订单id
     * @return OrderDetailEntity
     */
    @Override
    public List< OrderDetailEntity > getOrderDetail( Integer orderId ) {
        QueryWrapper< OrderDetailEntity > orderDetailQueryWrapper = new QueryWrapper<>( );
        orderDetailQueryWrapper.eq( "order_id", orderId );
        List< OrderDetailEntity > list = orderDetailMapper.selectList( orderDetailQueryWrapper );
        log.info( "list:" + list );
        return list;
    }

    /**
     * @param orderId 订单id
     * @return AlipayTradeFastpayRefundQueryResponse
     */
    @Override
    public boolean refunding( Integer orderId ) throws AlipayApiException {
        OrderEntity order = orderMapper.selectById( orderId );
        // 检查订单状态
        if ( PayStatus.REFUNDING.toString( ).equals( order.getStatus( ) ) || PayStatus.REFUND.toString( )
                                                                                             .equals( order.getStatus( ) ) ) {
            throw new AppException( ResponseEnum.PAY_REFUND_ERROR );
        }
        boolean refund = alipayService.refund( order.getOrderNum( ), order.getAmount( ) );
        if ( refund ) {
            // 退款成功更新状态
            order.setStatus( PayStatus.REFUND.toString( ) );
            orderMapper.updateById( order );
        }
        log.info( "refund:" + refund );
        return refund;
    }

}
