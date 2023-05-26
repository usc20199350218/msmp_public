package com.yw.msmp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.msmp.dto.BatchDTO;
import com.yw.msmp.entity.BatchEntity;
import com.yw.msmp.entity.SearchDrugDTO;

import java.util.List;

/**
 * @author yanhaoyuwen
 */
public interface BatchService extends IService< BatchEntity > {

    IPage< BatchDTO > findPageOverAll( int current, int size
                                       //            , String batchStatus
                                     );

    void addBatch( BatchEntity batchEntity );

    Page< BatchEntity > getPageBatchDetail( int current, int size, Integer drugDetailId );

    void changeBatch( BatchEntity batchEntity );

    List< BatchEntity > getBatchDetail( Integer drugDetailId, String active );

    int removeByDrugDetailId( Integer drugDetailId );

    /**
     * @param searchDrugDTO 分页查询条件信息 筛选条件为空时执行全部
     * @return page
     */
    IPage< BatchDTO > searchPage( SearchDrugDTO searchDrugDTO );

    void checkDrugBatch( );

    //    void myAddBatch( BatchAddDTO batchAddDTO );
}
