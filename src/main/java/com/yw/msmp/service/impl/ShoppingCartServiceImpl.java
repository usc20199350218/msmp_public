package com.yw.msmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.entity.ShoppingCartEntity;
import com.yw.msmp.mapper.ShoppingCartMapper;
import com.yw.msmp.mapper.StoreBatchMapper;
import com.yw.msmp.service.ShoppingCartService;
import com.yw.msmp.vo.ScDrugVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Log4j2
@Service
public class ShoppingCartServiceImpl extends ServiceImpl< ShoppingCartMapper, ShoppingCartEntity >
        implements ShoppingCartService {

    @Resource
    private ShoppingCartMapper shoppingCartMapper;

    @Resource
    private StoreBatchMapper storeBatchMapper;

    /**
     * @param userId
     * @return
     */
    @Override
    public List< ScDrugVO > getUserSc( Integer userId ) {
        List< ScDrugVO > list = shoppingCartMapper.selectUserCart( userId );
        log.info( userId + "用户购物车list:" + list );
        return list;
    }

    /**
     * @param userId       用户id
     * @param storeId      店铺id
     * @param searchMethod 搜索方式
     * @param searchText   搜索内容
     * @return list
     */
    @Override
    public List< ScDrugVO > getSearchList( Integer userId, Integer storeId, String searchMethod, String searchText ) {
        log.info( "userId:" + userId + ",storeId:" + storeId + ",searchMethod:" + searchMethod + ",searchText:" + searchText );
        List< ScDrugVO > scDrugVOList = shoppingCartMapper.search( userId, storeId, searchMethod, searchText );
        log.info( "scDrugVOList:" + scDrugVOList );
        return scDrugVOList;
    }

    /**
     * @param cartId
     * @param number
     */
    @Override
    public void updateCart( Integer cartId, Integer number ) {
        log.info( "cartId:" + cartId + ",number:" + number );
        int updated = shoppingCartMapper.updateById( ShoppingCartEntity.builder( ).cartId( cartId ).number( number )
                                                                       .build( ) );
        if ( updated != 1 ) {
            throw new AppException( ResponseEnum.UPDATE_ERROR );
        }
    }

    /**
     * @param userId
     * @param drugDetailId
     * @param storeId
     */
    @Override
    public void add( Integer userId, Integer drugDetailId, Integer storeId ) {
        // 判断是否存在
        QueryWrapper< ShoppingCartEntity > queryWrapper = new QueryWrapper<>( );
        queryWrapper.eq( "user_id", userId ).eq( "drug_detail_id", drugDetailId ).eq( "store_id", storeId );

        List< ShoppingCartEntity > shoppingCartEntityList = shoppingCartMapper.selectList( queryWrapper );
        if ( shoppingCartEntityList == null || shoppingCartEntityList.isEmpty( ) ) {
            // 不存在，新增
            log.info( "购物车不存在当前药品，添加" );
            shoppingCartMapper.insert( ShoppingCartEntity.builder( ).userId( userId ).drugDetailId( drugDetailId )
                                                         .storeId( storeId ).number( 1 ).build( ) );
        }
        else {
            log.info( "购物车存在当前药品，+1" );
            ShoppingCartEntity shoppingCartEntity = shoppingCartEntityList.get( 0 );
            shoppingCartEntity.setNumber( shoppingCartEntity.getNumber( ) + 1 );
            shoppingCartMapper.updateById( shoppingCartEntity );
        }
    }

    /**
     * 获取用户的购物车数量
     *
     * @param userId 用户id
     * @return 用户购物车数量
     */
    @Override
    public Integer getUserNumber( Integer userId ) {
        Integer userNumber = shoppingCartMapper.getUserNumber( userId );
        log.info( "用户购物车数量" + userNumber );
        return userNumber;
    }

    /**
     * 检查当前的用户购物车药品状态
     *
     * @param userId 用户id
     */
    @Override
    public void checkEfficient( Integer userId ) {
        // 获取购物车药品
        QueryWrapper< ShoppingCartEntity > shoppingCartQueryWrapper = new QueryWrapper<>( );
        shoppingCartQueryWrapper.eq( "user_id", userId );
        List< ShoppingCartEntity > shoppingCartEntityList = shoppingCartMapper.selectList( shoppingCartQueryWrapper );
        // 遍历检查库存并赋值
        for ( ShoppingCartEntity shoppingCartEntity : shoppingCartEntityList ) {
            // 获取数据
            Integer detailNum = storeBatchMapper.getDetailNum( shoppingCartEntity.getDrugDetailId( ),
                                                               shoppingCartEntity.getStoreId( ) );
            // 赋值
            if ( detailNum != null ) {
                shoppingCartEntity.setMaxNum( detailNum );
            }
            else {
                shoppingCartEntity.setMaxNum( 0 );
            }
            // 更新
            int num = shoppingCartMapper.updateById( shoppingCartEntity );
            if ( num != 1 ) {
                throw new AppException( ResponseEnum.UPDATE_ERROR );
            }
        }
    }

}
