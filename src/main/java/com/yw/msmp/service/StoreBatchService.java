package com.yw.msmp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.msmp.dto.DrugDetailsDTO;
import com.yw.msmp.dto.DrugSearchDTO;
import com.yw.msmp.dto.FastStoreBatchDTO;
import com.yw.msmp.entity.StoreBatchEntity;
import com.yw.msmp.vo.*;

import java.util.List;

public interface StoreBatchService extends IService< StoreBatchEntity > {

    IPage< StoreBatchVO > findPageOverAll( int current, int size, Integer storeId );

    void addStoreBatch( StoreBatchEntity storeBatch );

    List< StoreBatchDetailVO > getStoreBatchDetail( Integer drugDetailId, String active, Integer storeId );

    int removeByDrugDetailId( Integer storeId, Integer drugDetailId );

    List< ApplyBatchVo > selectApplyByStoreId( Integer storeId );

    void fastApproval( FastStoreBatchDTO fastStoreBatchDTO );

    List< ApplyBatchVo > getApplyList( Integer storeId, Integer drugDetailId );

    void changeStoreBatch( Integer storeBatchId, String status, String remarkText );

    void changeStatus( StoreBatchEntity storeBatch );

    IPage< OfflineSaleVO > selectByMode( String mode, String input, Integer storeId, Integer current, Integer size );

    PrepareVO addOrder( OrderVo orderVo );

    //    void searchList( Integer storeId, Integer typeId, String searchMethod, String searchText, Integer current,
    //    Integer size );

    IPage< DrugDetailsDTO > searchList( Integer storeId, DrugSearchDTO drugSearchDTO );


    /**
     * 检查店铺批次状态
     */
    void checkStoreBatch( );

}
