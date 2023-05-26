package com.yw.msmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.common.util.OrderIdGenerator;
import com.yw.msmp.dto.CheckGetBatchDTO;
import com.yw.msmp.dto.DrugDetailsDTO;
import com.yw.msmp.dto.DrugSearchDTO;
import com.yw.msmp.dto.FastStoreBatchDTO;
import com.yw.msmp.entity.*;
import com.yw.msmp.enums.BatchStatusEnum;
import com.yw.msmp.enums.DeliveryStatusEnum;
import com.yw.msmp.enums.PayStatus;
import com.yw.msmp.mapper.*;
import com.yw.msmp.service.StoreBatchService;
import com.yw.msmp.vo.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class StoreBatchServiceImpl extends ServiceImpl< StoreBatchMapper, StoreBatchEntity >
        implements StoreBatchService {

    @Resource
    private StoreBatchMapper storeBatchMapper;

    @Resource
    private DrugDetailsMapper drugDetailsMapper;

    @Resource
    private BatchMapper batchMapper;

    @Resource
    private DeliveryMapper deliveryMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderDetailMapper orderDetailMapper;

    @Resource
    private DrugMapper drugMapper;

    @Override
    public IPage< StoreBatchVO > findPageOverAll( int current, int size, Integer storeId ) {
        Page< StoreBatchVO > storeBatchVOPage = new Page<>( current, size );
        IPage< StoreBatchVO > page = storeBatchMapper.selectMyPage( storeId, storeBatchVOPage );
        log.info( "drug分页查询:" + page.getRecords( ) );
        return page;
    }

    @Override
    public void addStoreBatch( StoreBatchEntity storeBatch ) {
        // 根据药品详情id判断当前药品是否可用
        QueryWrapper< DrugDetailsEntity > detailsEntityQueryWrapper = new QueryWrapper<>( );
        detailsEntityQueryWrapper.eq( "drug_detail_id", storeBatch.getDrugDetailId( ) );
        DrugDetailsEntity drugDetailsEntity = drugDetailsMapper.selectOne( detailsEntityQueryWrapper );
        if ( drugDetailsEntity.getDrugDetailsStatus( ) != 1 ) {
            log.error( "批次状态不可使用" + drugDetailsEntity );
            throw new AppException( ResponseEnum.ADD_DRUG_ERROR );
        }
        log.info( "店铺id为:" + storeBatch.getStoreId( ) + "即将对" + drugDetailsEntity + "进货" );
        // 新增
        int insert = storeBatchMapper.insert( storeBatch );
        if ( insert != 1 ) {
            throw new AppException( ResponseEnum.ADD_ERROR );
        }
    }

    @Override
    public List< StoreBatchDetailVO > getStoreBatchDetail( Integer drugDetailId, String active, Integer storeId ) {

        // 转化active
        return storeBatchMapper.selectDetailList( drugDetailId, BatchStatusEnum.fromString( active )
                                                                               .toString( ), storeId );

    }

    @Override
    public int removeByDrugDetailId( Integer storeId, Integer drugDetailId ) {
        QueryWrapper< StoreBatchEntity > storeBatchEntityQueryWrapper = new QueryWrapper<>( );
        storeBatchEntityQueryWrapper.eq( "drug_detail_id", drugDetailId )
                                    .eq( "store_id", storeId );
        int i = storeBatchMapper.delete( storeBatchEntityQueryWrapper );
        if ( i == 0 ) {
            throw new AppException( ResponseEnum.DEL_ERROR );
        }
        return i;
    }

    @Override
    public List< ApplyBatchVo > selectApplyByStoreId( Integer storeId ) {
        log.info( "开始查询" + storeId + "的申请总览" );
        List< ApplyBatchVo > applyBatchVoList = storeBatchMapper.selectApplyBatchVoList( storeId );
        log.info( "applyBatchVoList:" + applyBatchVoList );
        // 基于BUG——SUPER，增加一个主键IdList
        // 开始获取此次可能的list
        List< CheckGetBatchDTO > checkList = storeBatchMapper.selectCheckList( storeId );
        log.info( "checkList:" + checkList );
        checkList.forEach( System.out::println );
        log.info( "------------------------------------------" );
        // 校验
        ArrayList< Integer > arrayList = new ArrayList<>( );
        Integer totalCreated = 0;
        for ( ApplyBatchVo applyBatchVo : applyBatchVoList ) {
            // 重置
            arrayList.clear( );
            totalCreated = applyBatchVo.getTotalCreated( );
            // 循环计算
            for ( CheckGetBatchDTO checkGetBatchDTO : checkList ) {
                // 筛选
                if ( applyBatchVo.getDrugDetailId( )
                                 .equals( checkGetBatchDTO.getDrugDetailId( ) ) ) {
                    totalCreated -= checkGetBatchDTO.getStoreBatchPurchaseQuantity( );
                    arrayList.add( checkGetBatchDTO.getStoreBatchId( ) );
                }
            }
            // 校验

            if ( !totalCreated.equals( 0 ) ) {
                throw new AppException( ResponseEnum.CHECK_ERROR );
            }
            log.info( "检验正常:" + applyBatchVo );
            applyBatchVo.setApplyIdList( new ArrayList<>( arrayList ) );
            log.info( "此时totalCreated:" + totalCreated );
        }
        log.info( "全部正常" );
        log.info( "------------------------------------------" );

        log.info( "applyBatchVoList:" + applyBatchVoList );
        return applyBatchVoList;
    }

    /**
     * 根据主键，依次进行一个送货
     * 然后
     *
     * @param fastStoreBatchDTO 前端数据
     */
    public void fastApprovalOld( FastStoreBatchDTO fastStoreBatchDTO ) {
        log.warn( "=======" );
        List< Integer > idList = fastStoreBatchDTO.getApplyIdList( );
        String temp = null;
        boolean enterBool = false;
        if ( "pass".equals( fastStoreBatchDTO.getStatus( ) ) ) {
            temp = "NORMAL_PURCHASE";
            enterBool = true;
        }
        else if ( "fail".equals( fastStoreBatchDTO.getStatus( ) ) ) {
            temp = "FORBIDDEN";
        }
        int updateCount = 0;
        int count;

        // 通过之后要同时添加配送表
        // 遍历idList，获取店内批次，更新状态
        StoreBatchEntity storeBatch;
        QueryWrapper< BatchEntity > batchEntityQueryWrapper = new QueryWrapper<>( );
        batchEntityQueryWrapper.eq( "drug_detail_id", fastStoreBatchDTO.getDrugDetailId( ) )
                               .eq( "batch_status", "SOLD" )
                               .orderByAsc( "batch_production_date" );
        List< BatchEntity > batchEntityList = batchMapper.selectList( batchEntityQueryWrapper );
        log.error( "获取对应仓库批次" );
        batchEntityList.forEach( System.out::println );
        // 遍历店内批次Id
        for ( Integer integer : idList ) {
            // 店内批次
            storeBatch = storeBatchMapper.selectById( integer );
            if ( enterBool ) {
                // 店内批次申请数量
                Integer storeBatchPurchaseQuantity = storeBatch.getStoreBatchPurchaseQuantity( );
                // 获取仓库批次,并录入配送
                for ( int i = 0; i < batchEntityList.size( ); i++ ) {
                    BatchEntity batchEntity = batchEntityList.get( i );
                    // 将仓库批次依次进行数量对比
                    if ( ( batchEntity.getBatchExistingQuantity( ) > storeBatchPurchaseQuantity ) && batchEntity.getBatchExistingQuantity( ) != 0 ) {
                        // 当前满足申请,且当前批次不为零，则录入,录入数量为-storeBatchPurchaseQuantity
                        // 根据店内批次生成对应的配送
                        deliveryMapper.insert( DeliveryEntity.builder( )
                                                             .storeId( storeBatch.getStoreId( ) )
                                                             .deliveryStatus( DeliveryStatusEnum.CREATED.toString( ) )
                                                             .batchId( batchEntity.getBatchId( ) )
                                                             .drugDetailId( storeBatch.getDrugDetailId( ) )
                                                             .storeBatchId( integer )
                                                             .deliveryNum( storeBatchPurchaseQuantity )
                                                             .build( ) );
                        // 更新仓库批次数量
                        batchEntity.setBatchExistingQuantity( batchEntity.getBatchExistingQuantity( ) - storeBatchPurchaseQuantity );
                        batchMapper.updateById( batchEntity );
                        break;
                    }
                    else if ( ( batchEntity.getBatchExistingQuantity( ) <= storeBatchPurchaseQuantity ) && batchEntity.getBatchExistingQuantity( ) > 0 ) {
                        // 录入
                        deliveryMapper.insert( DeliveryEntity.builder( )
                                                             .storeId( storeBatch.getStoreId( ) )
                                                             .deliveryStatus( DeliveryStatusEnum.CREATED.toString( ) )
                                                             .batchId( batchEntity.getBatchId( ) )
                                                             .drugDetailId( storeBatch.getDrugDetailId( ) )
                                                             .storeBatchId( integer )
                                                             .deliveryNum( batchEntity.getBatchExistingQuantity( ) )
                                                             .build( ) );
                        // 更新 店内批次申请数量
                        storeBatchPurchaseQuantity -= batchEntity.getBatchExistingQuantity( );
                        // 更新仓库批次状态
                        batchEntity.setBatchStatus( "SOLD_OUT" );
                        // 更新仓库批次数量
                        batchEntity.setBatchExistingQuantity( 0 );

                        batchMapper.updateById( batchEntity );
                    }
                }
            }
            storeBatch.setStoreBatchStatus( temp );
            count = storeBatchMapper.updateById( storeBatch );
            updateCount += count;
        }
        if ( updateCount != idList.size( ) ) {
            throw new AppException( ResponseEnum.UPDATE_ERROR );
        }
    }

    /**
     * 根据主键，依次录入配送
     *
     * @param fastStoreBatchDTO 前端数据
     */
    @Override
    @Transactional( rollbackFor = Exception.class )
    public void fastApproval( FastStoreBatchDTO fastStoreBatchDTO ) {

        List< Integer > storeBatchIdList = fastStoreBatchDTO.getApplyIdList( );
        boolean isPass = "pass".equals( fastStoreBatchDTO.getStatus( ) );
        String storeBatchStatus = isPass ? "NORMAL_PURCHASE" : "FORBIDDEN";
        // 分情况执行更新
        if ( isPass ) {
            // 正常
            // 获取对应药品详情id，且可用的仓库批次
            QueryWrapper< BatchEntity > batchEntityQueryWrapper = new QueryWrapper<>( );
            batchEntityQueryWrapper
                    .eq( "drug_detail_id", fastStoreBatchDTO.getDrugDetailId( ) )
                    .eq( "batch_status", "SOLD" )
                    .orderByAsc( "batch_production_date" );
            List< BatchEntity > batchList = batchMapper.selectList( batchEntityQueryWrapper );
            // 遍历店铺批次主键id
            for ( Integer integer : storeBatchIdList ) {
                // 获取当前店铺批次请求数据
                StoreBatchEntity storeBatch = storeBatchMapper.selectById( integer );
                // 获取当前店铺批次请求数量
                Integer storeBatchPurchaseQuantity = storeBatch.getStoreBatchPurchaseQuantity( );
                for ( int i = 0; i < batchList.size( ); i++ ) {
                    BatchEntity batch = batchList.get( i );
                    if ( i == 0 ) {
                        // 是否第一次执行
                        if ( batch.getBatchExistingQuantity( ) >= storeBatchPurchaseQuantity ) {
                            // 一次满足
                            // 更新店铺批次
                            storeBatch.setBatchId( batch.getBatchId( ) );
                            storeBatch.setBatchNumber( batch.getBatchNumber( ) );
                            storeBatch.setStoreBatchRemark( fastStoreBatchDTO.getRemarkText( ) );
                            storeBatch.setStoreBatchStatus( BatchStatusEnum.NORMAL_PURCHASE.toString( ) );
                            storeBatchMapper.updateById( storeBatch );

                            // 生成配送数据
                            deliveryMapper.insert( DeliveryEntity.builder( )
                                                                 .storeId( storeBatch.getStoreId( ) )
                                                                 .deliveryStatus( DeliveryStatusEnum.CREATED.toString( ) )
                                                                 .batchId( batch.getBatchId( ) )
                                                                 .drugDetailId( storeBatch.getDrugDetailId( ) )
                                                                 .storeBatchId( storeBatch.getStoreBatchId( ) )
                                                                 .deliveryNum( storeBatchPurchaseQuantity )
                                                                 .build( ) );

                            // 更新仓库批次
                            if ( batch.getBatchExistingQuantity( )
                                      .equals( storeBatchPurchaseQuantity ) ) {
                                batch.setBatchStatus( "SOLD_OUT" );
                            }
                            batch.setBatchExistingQuantity( batch.getBatchExistingQuantity( ) - storeBatchPurchaseQuantity );
                            batchMapper.updateById( batch );

                            storeBatchPurchaseQuantity = 0;
                        }
                        else if ( batch.getBatchExistingQuantity( ) != 0 ) {
                            // 一次不能满足
                            // 多个仓库批次对应一个店铺批次，需要新建店铺批次来维护
                            // 更新剩余待分配请求数量
                            storeBatchPurchaseQuantity -= batch.getBatchExistingQuantity( );

                            // 更新店铺批次
                            storeBatch.setBatchId( batch.getBatchId( ) );
                            storeBatch.setBatchNumber( batch.getBatchNumber( ) );
                            storeBatch.setStoreBatchRemark( fastStoreBatchDTO.getRemarkText( ) );
                            storeBatch.setStoreBatchPurchaseQuantity( batch.getBatchExistingQuantity( ) );
                            storeBatch.setStoreBatchStatus( BatchStatusEnum.NORMAL_PURCHASE.toString( ) );
                            storeBatchMapper.updateById( storeBatch );

                            // 生成配送数据
                            deliveryMapper.insert( DeliveryEntity.builder( )
                                                                 .storeId( storeBatch.getStoreId( ) )
                                                                 .deliveryStatus( DeliveryStatusEnum.CREATED.toString( ) )
                                                                 .batchId( batch.getBatchId( ) )
                                                                 .drugDetailId( storeBatch.getDrugDetailId( ) )
                                                                 .storeBatchId( storeBatch.getStoreBatchId( ) )
                                                                 .deliveryNum( batch.getBatchExistingQuantity( ) )
                                                                 .build( ) );

                            // 更新仓库批次
                            batch.setBatchStatus( "SOLD_OUT" );
                            batch.setBatchExistingQuantity( 0 );
                            batchMapper.updateById( batch );
                        }
                    }
                    else {
                        // 第2...n次
                        // 更新剩余待分配请求数量
                        if ( batch.getBatchExistingQuantity( ) < storeBatchPurchaseQuantity && batch.getBatchExistingQuantity( ) != 0 ) {
                            // 此次不能满足
                            // 更新剩余待处理数量
                            storeBatchPurchaseQuantity -= batch.getBatchExistingQuantity( );

                            // 更新店铺批次
                            // 因为不是第一次，所以执行添加,storeBatch此时只有提供数据功能
                            // 当前批次仍然无法满足申请数量
                            storeBatchMapper.insert( StoreBatchEntity.builder( )
                                                                     .batchId( batch.getBatchId( ) )
                                                                     .batchNumber( batch.getBatchNumber( ) )
                                                                     .storeId( storeBatch.getStoreId( ) )
                                                                     .drugDetailId( storeBatch.getDrugDetailId( ) )
                                                                     .storeBatchStatus( BatchStatusEnum.NORMAL_PURCHASE.toString( ) )
                                                                     .storeBatchPurchaseQuantity( batch.getBatchExistingQuantity( ) )
                                                                     .build( ) );
                            // 生成配送数据
                            deliveryMapper.insert( DeliveryEntity.builder( )
                                                                 .storeId( storeBatch.getStoreId( ) )
                                                                 .deliveryStatus( DeliveryStatusEnum.CREATED.toString( ) )
                                                                 .batchId( batch.getBatchId( ) )
                                                                 .drugDetailId( storeBatch.getDrugDetailId( ) )
                                                                 .storeBatchId( storeBatch.getStoreBatchId( ) )
                                                                 .deliveryNum( batch.getBatchExistingQuantity( ) )
                                                                 .build( ) );

                            // 更新仓库批次
                            batch.setBatchStatus( "SOLD_OUT" );
                            batch.setBatchExistingQuantity( 0 );
                            batchMapper.updateById( batch );
                        }
                        else if ( batch.getBatchExistingQuantity( ) != 0 ) {
                            // 此时满足

                            // 更新店铺批次
                            // 因为不是第一次，所以执行添加,storeBatch此时只有提供数据功能
                            // 当前批次仍然无法满足申请数量
                            storeBatchMapper.insert( StoreBatchEntity.builder( )
                                                                     .batchId( batch.getBatchId( ) )
                                                                     .batchNumber( batch.getBatchNumber( ) )
                                                                     .storeId( storeBatch.getStoreId( ) )
                                                                     .drugDetailId( storeBatch.getDrugDetailId( ) )
                                                                     .storeBatchStatus( BatchStatusEnum.NORMAL_PURCHASE.toString( ) )
                                                                     .storeBatchPurchaseQuantity(
                                                                             storeBatchPurchaseQuantity )
                                                                     .build( ) );
                            // 生成配送数据
                            deliveryMapper.insert( DeliveryEntity.builder( )
                                                                 .storeId( storeBatch.getStoreId( ) )
                                                                 .deliveryStatus( DeliveryStatusEnum.CREATED.toString( ) )
                                                                 .batchId( batch.getBatchId( ) )
                                                                 .drugDetailId( storeBatch.getDrugDetailId( ) )
                                                                 .storeBatchId( storeBatch.getStoreBatchId( ) )
                                                                 .deliveryNum( storeBatchPurchaseQuantity )
                                                                 .build( ) );

                            // 更新仓库批次
                            batch.setBatchExistingQuantity( batch.getBatchExistingQuantity( ) - storeBatchPurchaseQuantity );
                            // 判断是否只是刚好满足
                            if ( batch.getBatchExistingQuantity( ) == 0 ) {
                                // 更新状态
                                batch.setBatchStatus( "SOLD_OUT" );
                            }
                            batchMapper.updateById( batch );
                            storeBatchPurchaseQuantity = 0;
                        }
                    }
                    if ( storeBatchPurchaseQuantity == 0 ) {
                        break;
                    }
                }
                // 防止全部执行完还是不能满足
                if ( storeBatchPurchaseQuantity != 0 ) {
                    throw new AppException( ResponseEnum.UPDATE_ERROR );
                }
            }
        }
        else {
            // 退回，直接遍历改状态
            for ( Integer storeBatchId : storeBatchIdList ) {
                StoreBatchEntity storeBatch = storeBatchMapper.selectById( storeBatchId );
                storeBatch.setStoreBatchStatus( storeBatchStatus );
                storeBatch.setStoreBatchRemark( fastStoreBatchDTO.getRemarkText( ) );
                storeBatchMapper.updateById( storeBatch );
            }
        }
    }

    @Override
    public List< ApplyBatchVo > getApplyList( Integer storeId, Integer drugDetailId ) {
        return storeBatchMapper.selectByStoreIdDDId( storeId, drugDetailId );
    }

    @Override
    @Transactional( rollbackFor = Exception.class )
    public void changeStoreBatch( Integer storeBatchId, String status, String remarkText ) {
        boolean isPass = "pass".equals( status );
        String storeBatchStatus = isPass ? "NORMAL_PURCHASE" : "FORBIDDEN";
        // 获取店铺申请批次
        StoreBatchEntity storeBatch = storeBatchMapper.selectById( storeBatchId );
        Integer storeBatchPurchaseQuantity = storeBatch.getStoreBatchPurchaseQuantity( );
        storeBatch.setStoreBatchStatus( storeBatchStatus );
        storeBatch.setStoreBatchRemark( remarkText );
        if ( !isPass ) {
            // 退回则直接更新
            storeBatchMapper.updateById( storeBatch );
            return;
        }
        // 药品详情id
        Integer drugDetailId = storeBatch.getDrugDetailId( );

        QueryWrapper< BatchEntity > batchQueryWrapper = new QueryWrapper<>( );
        batchQueryWrapper.eq( "drug_detail_id", drugDetailId )
                         .eq( "batch_status", "SOLD" )
                         .orderByAsc( "batch_production_date" );
        // 获取可用仓库批次
        List< BatchEntity > batchList = batchMapper.selectList( batchQueryWrapper );
        // 遍历仓库批次
        for ( int i = 0; i < batchList.size( ); i++ ) {
            BatchEntity batch = batchList.get( i );
            if ( i == 0 ) {
                // 是否第一次执行
                if ( batch.getBatchExistingQuantity( ) >= storeBatchPurchaseQuantity ) {
                    // 一次满足
                    // 更新店铺批次
                    storeBatch.setBatchId( batch.getBatchId( ) );
                    storeBatch.setBatchNumber( batch.getBatchNumber( ) );
                    storeBatch.setStoreBatchRemark( remarkText );
                    storeBatch.setStoreBatchStatus( BatchStatusEnum.NORMAL_PURCHASE.toString( ) );
                    storeBatchMapper.updateById( storeBatch );

                    // 生成配送数据
                    deliveryMapper.insert( DeliveryEntity.builder( )
                                                         .storeId( storeBatch.getStoreId( ) )
                                                         .deliveryStatus( DeliveryStatusEnum.CREATED.toString( ) )
                                                         .batchId( batch.getBatchId( ) )
                                                         .drugDetailId( storeBatch.getDrugDetailId( ) )
                                                         .storeBatchId( storeBatch.getStoreBatchId( ) )
                                                         .deliveryNum( storeBatchPurchaseQuantity )
                                                         .build( ) );

                    // 更新仓库批次
                    if ( batch.getBatchExistingQuantity( )
                              .equals( storeBatchPurchaseQuantity ) ) {
                        batch.setBatchStatus( "SOLD_OUT" );
                    }
                    batch.setBatchExistingQuantity( batch.getBatchExistingQuantity( ) - storeBatchPurchaseQuantity );
                    batchMapper.updateById( batch );

                    storeBatchPurchaseQuantity = 0;
                }
                else if ( batch.getBatchExistingQuantity( ) != 0 ) {
                    // 一次不能满足
                    // 多个仓库批次对应一个店铺批次，需要新建店铺批次来维护
                    // 更新剩余待分配请求数量
                    storeBatchPurchaseQuantity -= batch.getBatchExistingQuantity( );

                    // 更新店铺批次
                    storeBatch.setBatchId( batch.getBatchId( ) );
                    storeBatch.setBatchNumber( batch.getBatchNumber( ) );
                    storeBatch.setStoreBatchRemark( remarkText );
                    storeBatch.setStoreBatchPurchaseQuantity( batch.getBatchExistingQuantity( ) );
                    storeBatch.setStoreBatchStatus( BatchStatusEnum.NORMAL_PURCHASE.toString( ) );
                    storeBatchMapper.updateById( storeBatch );

                    // 生成配送数据
                    deliveryMapper.insert( DeliveryEntity.builder( )
                                                         .storeId( storeBatch.getStoreId( ) )
                                                         .deliveryStatus( DeliveryStatusEnum.CREATED.toString( ) )
                                                         .batchId( batch.getBatchId( ) )
                                                         .drugDetailId( storeBatch.getDrugDetailId( ) )
                                                         .storeBatchId( storeBatch.getStoreBatchId( ) )
                                                         .deliveryNum( batch.getBatchExistingQuantity( ) )
                                                         .build( ) );

                    // 更新仓库批次
                    batch.setBatchStatus( "SOLD_OUT" );
                    batch.setBatchExistingQuantity( 0 );
                    batchMapper.updateById( batch );
                }
            }
            else {
                // 第2...n次
                // 更新剩余待分配请求数量
                if ( batch.getBatchExistingQuantity( ) < storeBatchPurchaseQuantity && batch.getBatchExistingQuantity( ) != 0 ) {
                    // 此次不能满足
                    // 更新剩余待处理数量
                    storeBatchPurchaseQuantity -= batch.getBatchExistingQuantity( );

                    // 更新店铺批次
                    // 因为不是第一次，所以执行添加,storeBatch此时只有提供数据功能
                    // 当前批次仍然无法满足申请数量
                    storeBatchMapper.insert( StoreBatchEntity.builder( )
                                                             .batchId( batch.getBatchId( ) )
                                                             .batchNumber( batch.getBatchNumber( ) )
                                                             .storeId( storeBatch.getStoreId( ) )
                                                             .drugDetailId( storeBatch.getDrugDetailId( ) )
                                                             .storeBatchStatus( BatchStatusEnum.NORMAL_PURCHASE.toString( ) )
                                                             .storeBatchPurchaseQuantity( batch.getBatchExistingQuantity( ) )
                                                             .build( ) );
                    // 生成配送数据
                    deliveryMapper.insert( DeliveryEntity.builder( )
                                                         .storeId( storeBatch.getStoreId( ) )
                                                         .deliveryStatus( DeliveryStatusEnum.CREATED.toString( ) )
                                                         .batchId( batch.getBatchId( ) )
                                                         .drugDetailId( storeBatch.getDrugDetailId( ) )
                                                         .storeBatchId( storeBatch.getStoreBatchId( ) )
                                                         .deliveryNum( batch.getBatchExistingQuantity( ) )
                                                         .build( ) );

                    // 更新仓库批次
                    batch.setBatchStatus( "SOLD_OUT" );
                    batch.setBatchExistingQuantity( 0 );
                    batchMapper.updateById( batch );
                }
                else if ( batch.getBatchExistingQuantity( ) != 0 ) {
                    // 此时满足

                    // 更新店铺批次
                    // 因为不是第一次，所以执行添加,storeBatch此时只有提供数据功能
                    // 当前批次仍然无法满足申请数量
                    storeBatchMapper.insert( StoreBatchEntity.builder( )
                                                             .batchId( batch.getBatchId( ) )
                                                             .batchNumber( batch.getBatchNumber( ) )
                                                             .storeId( storeBatch.getStoreId( ) )
                                                             .drugDetailId( storeBatch.getDrugDetailId( ) )
                                                             .storeBatchStatus( BatchStatusEnum.NORMAL_PURCHASE.toString( ) )
                                                             .storeBatchPurchaseQuantity( storeBatchPurchaseQuantity )
                                                             .build( ) );
                    // 生成配送数据
                    deliveryMapper.insert( DeliveryEntity.builder( )
                                                         .storeId( storeBatch.getStoreId( ) )
                                                         .deliveryStatus( DeliveryStatusEnum.CREATED.toString( ) )
                                                         .batchId( batch.getBatchId( ) )
                                                         .drugDetailId( storeBatch.getDrugDetailId( ) )
                                                         .storeBatchId( storeBatch.getStoreBatchId( ) )
                                                         .deliveryNum( storeBatchPurchaseQuantity )
                                                         .build( ) );

                    // 更新仓库批次
                    batch.setBatchExistingQuantity( batch.getBatchExistingQuantity( ) - storeBatchPurchaseQuantity );
                    // 判断是否只是刚好满足
                    if ( batch.getBatchExistingQuantity( ) == 0 ) {
                        // 更新状态
                        batch.setBatchStatus( "SOLD_OUT" );
                    }
                    batchMapper.updateById( batch );
                    storeBatchPurchaseQuantity = 0;
                }
            }
            if ( storeBatchPurchaseQuantity == 0 ) {
                break;
            }
        }
    }

    @Override
    public void changeStatus( StoreBatchEntity storeBatch ) {
        // 先校验是否已经配送完成,根据店铺批次Id
        QueryWrapper< DeliveryEntity > deliveryQueryWrapper = new QueryWrapper<>( );
        deliveryQueryWrapper.eq( "store_batch_id", storeBatch.getStoreBatchId( ) );
        log.info( "storeBatch:" + storeBatch );
        DeliveryEntity delivery = deliveryMapper.selectOne( deliveryQueryWrapper );
        if ( !DeliveryStatusEnum.ARRIVE.toString( )
                                       .equals( delivery.getDeliveryStatus( ) ) ) {
            throw new AppException( ResponseEnum.PLEASE_WAIT_DELIVERY_ARRIVE );
        }
        if ( BatchStatusEnum.SOLD.toString( )
                                 .equals( storeBatch.getStoreBatchStatus( ) ) ) {
            // 上架按钮
            // 将现存数量补全
            storeBatch.setStoreBatchExistingQuantity( storeBatch.getStoreBatchPurchaseQuantity( ) );
        }
        else if ( "SOLD_AGAIN".equals( storeBatch.getStoreBatchStatus( ) ) ) {
            // 恢复按钮
            // 更新状态
            storeBatch.setStoreBatchStatus( BatchStatusEnum.SOLD.toString( ) );
        }
        // 更新
        storeBatchMapper.updateById( storeBatch );
    }

    @Override
    public IPage< OfflineSaleVO > selectByMode( String mode,
                                                String input,
                                                Integer storeId,
                                                Integer current,
                                                Integer size ) {
        Page< OfflineSaleVO > page = new Page<>( current, size );
        if ( "drugName".equals( mode ) ) {
            // 根据药品名称查询
            log.info( "根据药品名称查询" );
            return storeBatchMapper.selectOfflineByName( input, storeId, page );
        }
        else {
            // 根据药品编码查询
            log.info( "根据药品编码查询" );
            return storeBatchMapper.selectOfflineByNumber( input, storeId, page );
        }
    }

    /**
     * 线下生成预订单
     *
     * @param orderVo 参数
     * @return 预览vo
     */
    @Override
    public PrepareVO addOrder( OrderVo orderVo ) {
        // 拿到id 减少库存，生成详情订单,生成订单
        // 转换成map，减少操作
        List< Integer > storeBatchIdList = orderVo.getStoreBatchId( );

        Map< Integer, Integer > idMap = new HashMap<>( );
        for ( Integer id : storeBatchIdList ) {
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

        StoreBatchEntity storeBatch;

        // 生成订单
        String orderNum = OrderIdGenerator.generateOrderId( );
        OrderEntity order = OrderEntity.builder( )
                                       .orderNum( orderNum )
                                       .status( PayStatus.CREATED.toString( ) )
                                       .deliveryAddress( "" )
                                       .operateUserId( orderVo.getUserId( ) )
                                       .storeId( orderVo.getStoreId( ) )
                                       .build( );
        orderMapper.insert( order );
        // 获取订单id
        Integer orderId = order.getOrderId( );
        BigDecimal decimal = new BigDecimal( 0 );
        for ( Map.Entry< Integer, Integer > entry : idMap.entrySet( ) ) {
            Integer id = entry.getKey( );
            Integer count = entry.getValue( );
            // 根据id和count更新相应的数据
            // 有店铺批次id直接查id，然后更新id
            storeBatch = storeBatchMapper.selectById( id );
            storeBatch.setStoreBatchExistingQuantity( storeBatch.getStoreBatchExistingQuantity( ) - count );
            storeBatchMapper.updateById( storeBatch );
            // 零售价
            DrugDetailsEntity drugDetail = drugDetailsMapper.selectById( storeBatch.getDrugDetailId( ) );
            BigDecimal retailPrice = drugDetail.getDrugRetailPrice( );
            // 中间金额
            BigDecimal multiply = retailPrice.multiply( new BigDecimal( count ) );
            // 记录金额
            decimal = decimal.add( multiply );
            // 获取药品名称
            DrugEntity drug = drugMapper.selectById( drugDetail.getDrugId( ) );
            // 插入订单详情表
            orderDetailMapper.insert( OrderDetailEntity.builder( )
                                                       .orderId( orderId )
                                                       .drugDetailId( storeBatch.getDrugDetailId( ) )
                                                       .drugName( drug.getDrugName( ) )
                                                       .drugPrice( drugDetail.getDrugRetailPrice( ) )
                                                       .quantity( count )
                                                       .totalPrice( multiply )
                                                       .storeBatchId( storeBatch.getStoreBatchId( ) )
                                                       .build( ) );
        }

        // 补充更新
        order.setAmount( decimal );
        orderMapper.updateById( order );
        log.info( "order:" + order );
        // 返回订单id，校验金额（预计金额）
        return PrepareVO.builder( )
                        .orderId( order.getOrderId( ) )
                        .orderNum( orderNum )
                        .amount( decimal )
                        .build( );
    }

    /**
     * @param storeId       店铺id
     * @param drugSearchDTO 搜索参数
     * @return 分页
     */
    @Override
    public IPage< DrugDetailsDTO > searchList( Integer storeId, DrugSearchDTO drugSearchDTO ) {
        Page< DrugDetailsDTO > page = new Page<>( drugSearchDTO.getCurrent( ), drugSearchDTO.getSize( ) );
        IPage< DrugDetailsDTO > iPage = drugDetailsMapper.selectOnlineDrugSearchPage( page, storeId, drugSearchDTO );
        log.info( "iPage:" + iPage );
        return iPage;
    }

    /**
     * 检查店铺批次状态
     */
    @Override
    public void checkStoreBatch( ) {
        // 获取所有过期的批次
        List< StoreBatchEntity > list = storeBatchMapper.getExpired( );
        // 更新批次状态
        for ( StoreBatchEntity batchEntity : list ) {
            batchEntity.setStoreBatchStatus( BatchStatusEnum.EXPIRED.toString( ) );
            storeBatchMapper.updateById( batchEntity );
        }
    }

}
