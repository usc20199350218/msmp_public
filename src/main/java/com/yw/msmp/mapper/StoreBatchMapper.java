package com.yw.msmp.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yw.msmp.dto.CheckGetBatchDTO;
import com.yw.msmp.entity.StoreBatchEntity;
import com.yw.msmp.enums.BatchStatusEnum;
import com.yw.msmp.vo.ApplyBatchVo;
import com.yw.msmp.vo.OfflineSaleVO;
import com.yw.msmp.vo.StoreBatchDetailVO;
import com.yw.msmp.vo.StoreBatchVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yanhaoyuwen
 */
@Repository
public interface StoreBatchMapper extends BaseMapper< StoreBatchEntity > {

    IPage< StoreBatchVO > selectMyPage( Integer storeId, Page< StoreBatchVO > storeBatchVOPage );

    List< StoreBatchDetailVO > selectDetailList( Integer drugDetailId, String active, Integer storeId );

    List< ApplyBatchVo > selectApplyBatchVoList( Integer storeId );

    List< CheckGetBatchDTO > selectCheckList( Integer storeId );

    List< ApplyBatchVo > selectByStoreIdDDId( Integer storeId, Integer drugDetailId );

    IPage< OfflineSaleVO > selectOfflineByName( String input, Integer storeId, Page< OfflineSaleVO > page );

    IPage< OfflineSaleVO > selectOfflineByNumber( String input, Integer storeId, Page< OfflineSaleVO > page );

    Integer selectSum( Integer storeId, Integer drugDetailId, BatchStatusEnum batchStatusEnum );

    /**
     * 获取药品详情的数量
     *
     * @param drugDetailId Starting Drug Detail Id
     * @param storeId      Store Id
     * @return initializedDrugDetail Drug Detail Number
     */
    Integer getDetailNum( @Param( "drugDetailId" ) Integer drugDetailId, @Param( "storeId" ) Integer storeId );

    /**
     * 获取过期的批次
     *
     * @return List of expired batch
     */
    List< StoreBatchEntity > getExpired( );

}
