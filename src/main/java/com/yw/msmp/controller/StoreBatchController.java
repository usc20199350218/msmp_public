package com.yw.msmp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yw.msmp.common.aop.anno.CheckStoreBatch;
import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.dto.FastStoreBatchDTO;
import com.yw.msmp.entity.StoreBatchEntity;
import com.yw.msmp.service.StoreBatchService;
import com.yw.msmp.vo.ApplyBatchVo;
import com.yw.msmp.vo.StoreBatchDetailVO;
import com.yw.msmp.vo.StoreBatchVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yanhaoyuwen
 */
@RestController
@RequestMapping( "/admin/store_batch" )
@CrossOrigin( "*" )
@Log4j2
public class StoreBatchController {

    @Resource
    private StoreBatchService storeBatchService;

    @CheckStoreBatch
    @GetMapping( "/page/{storeId}" )
    public R getAll( int current, int size, @PathVariable( "storeId" ) Integer storeId ) {
        log.info( "获取指定状态整体全部库存" );
        IPage< StoreBatchVO > page = storeBatchService.findPageOverAll( current, size, storeId );
        return new R( ResponseEnum.SUCCESS, page );
    }

    /**
     * @param storeBatch 店内批次
     * @return null
     */
    @CheckStoreBatch
    @PostMapping
    public R addBatch( StoreBatchEntity storeBatch ) {
        log.info( "新增的店内批次：" + storeBatch );
        storeBatchService.addStoreBatch( storeBatch );
        return new R( ResponseEnum.SUCCESS, null );
    }

    //    @PutMapping
    //    public R changeBatch( BatchEntity batchEntity ) {
    //        log.info( "改变状态：" + batchEntity );
    //        batchService.changeBatch( batchEntity );
    //        return new R( ResponseEnum.SUCCESS, null );
    //    }
    //
    @DeleteMapping( "/{storeId}/{drugDetailId}" )
    public R delBatch( @PathVariable( "storeId" ) Integer storeId,
                       @PathVariable( "drugDetailId" ) Integer drugDetailId ) {
        log.info( "删除storeId为：" + storeId );
        log.info( "删除drugDetailId为：" + drugDetailId );
        int i = storeBatchService.removeByDrugDetailId( storeId, drugDetailId );
        return new R( ResponseEnum.SUCCESS, i );
    }
    //
    //    // 因为不太需要所有删除了分页
    //


    /**
     * @param drugDetailId 药品详情id
     * @param active       对应状态
     * @return 该药品对应的批次
     */
    @CheckStoreBatch
    @GetMapping( "/detail/{drugDetailId}" )
    public R getDetail( @PathVariable( "drugDetailId" ) Integer drugDetailId, String active, Integer storeId ) {
        log.info( "开始获取storeId为:" + storeId + ",drugDetailId为:" + drugDetailId + "且状态为:" + active + "的详细批次详细" );
        List< StoreBatchDetailVO > batchEntityList = storeBatchService.getStoreBatchDetail( drugDetailId,
                                                                                            active,
                                                                                            storeId );
        return new R( ResponseEnum.SUCCESS, batchEntityList );
    }


    //
    //
    //    @GetMapping( "/status" )
    //    public R getStatus( ) {
    //        return new R( ResponseEnum.SUCCESS, BatchStatusEnum.getChineseList( ) );
    //    }
    //
    //    @PutMapping( "/detail" )
    //    public R changeDetail( BatchEntity batchEntity ) {
    //        log.info( "更新：" + batchEntity );
    //        boolean b = batchService.updateById( batchEntity );
    //        if ( !b ) {
    //            throw new AppException( ResponseEnum.UPDATE_ERROR );
    //        }
    //        return new R( ResponseEnum.SUCCESS, null );
    //    }

    @CheckStoreBatch
    @GetMapping( "/apply/{storeId}" )
    public R getApply( @PathVariable( "storeId" ) Integer storeId ) {
        log.info( "storeId" );
        List< ApplyBatchVo > applyBatchVoList = storeBatchService.selectApplyByStoreId( storeId );
        return new R( ResponseEnum.SUCCESS, applyBatchVoList );
    }

    @CheckStoreBatch
    @PutMapping( "/fast" )
    public R fastApproval( @RequestBody FastStoreBatchDTO fastStoreBatchDTO ) {
        log.info( fastStoreBatchDTO );
        storeBatchService.fastApproval( fastStoreBatchDTO );
        return new R( ResponseEnum.SUCCESS, null );
    }

    @CheckStoreBatch
    @PutMapping( "/list" )
    public R getDetails( @RequestBody FastStoreBatchDTO fastStoreBatchDTO ) {
        log.info( fastStoreBatchDTO );
        return new R( ResponseEnum.SUCCESS, null );
    }


    //    @GetMapping( "/list" )
    //    public R getDetails( FastStoreBatchDTO fastStoreBatchDTO ) {
    //        log.info( fastStoreBatchDTO );
    //        return new R( ResponseEnum.SUCCESS, null );
    //    }

    @CheckStoreBatch
    @GetMapping( "/apply/details/external" )
    public R getApplyDetailList( Integer storeId, Integer drugDetailId ) {
        log.info( "storeId:" + storeId );
        log.info( "drugDetailId:" + drugDetailId );
        List< ApplyBatchVo > applyBatchVoList = storeBatchService.getApplyList( storeId, drugDetailId );
        log.info( "applyBatchVoList:" + applyBatchVoList );
        return new R( ResponseEnum.SUCCESS, applyBatchVoList );
    }

    @PutMapping( "/apply/change" )
    public R changeDetail( Integer storeBatchId, String storeBatchStatus ) {
        log.info( "storeBatchId:" + storeBatchId );
        log.info( "storeBatchStatus:" + storeBatchStatus );
        storeBatchService.changeStoreBatch( storeBatchId, storeBatchStatus, null );
        return new R( ResponseEnum.SUCCESS, storeBatchId + "|" + storeBatchStatus );
    }

    @PutMapping()
    public R changeStatus( StoreBatchEntity storeBatch ) {
        log.error( "storeBatch:" + storeBatch );
        storeBatchService.changeStatus( storeBatch );
        return new R( ResponseEnum.SUCCESS, null );
    }

    //    @GetMapping( "/{storeId}" )
    //    public R searchDrug( @PathVariable( "storeId" ) Integer storeId, Integer typeId, String searchMethod, String
    //    searchText, Integer current, Integer size ) {
    //        storeBatchService.searchList( storeId, typeId, searchMethod, searchText, current, size );
    //        return new R( ResponseEnum.SUCCESS, null );
    //    }

    //    @GetMapping( "/{storeId}" )
    //    public R searchDrug( @PathVariable( "storeId" ) Integer storeId, DrugSearchDTO drugSearchDTO ) {
    ////    storeBatchService.searchList( storeId, drugSearchDTO);
    //        log.info( "storeId:" + storeId );
    //        log.info( "drugSearchDTO:" + drugSearchDTO );
    //        return new R( ResponseEnum.SUCCESS, null );
    //    }
}

