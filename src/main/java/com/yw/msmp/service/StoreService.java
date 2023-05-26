package com.yw.msmp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.msmp.entity.StoreEntity;
import com.yw.msmp.vo.StoreVo;

import java.util.List;

public interface StoreService extends IService< StoreEntity > {

    void add( StoreEntity storeEntity );

    List< StoreVo > getApplyList( );

    List< StoreEntity > getOnlineStoreList( );

    List< StoreEntity > getUserOrder( Integer userId );

    /**
     * 通过userId获取店铺列表
     *
     * @param userId 用户id
     * @return
     */
    List< StoreEntity > getStoreListByUserId( Integer userId );

}
