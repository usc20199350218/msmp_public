package com.yw.msmp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.msmp.entity.ShoppingCartEntity;
import com.yw.msmp.vo.ScDrugVO;

import java.util.List;

/**
 * @author yanhaoyuwen
 */
public interface ShoppingCartService extends IService< ShoppingCartEntity > {

    List< ScDrugVO > getUserSc( Integer userId );

    List< ScDrugVO > getSearchList( Integer userId, Integer storeId, String searchMethod, String searchText );

    void updateCart( Integer cartId, Integer number );

    void add( Integer userId, Integer drugDetailId, Integer storeId );

    /**
     * 获取用户的购物车数量
     *
     * @param userId 用户id
     * @return 用户购物车数量
     */
    Integer getUserNumber( Integer userId );

    /**
     * 检查当前的用户购物车药品状态
     */
    void checkEfficient( Integer userId );

}
