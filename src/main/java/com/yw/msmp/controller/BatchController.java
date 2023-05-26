package com.yw.msmp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yw.msmp.common.aop.anno.CheckDrugBatch;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.dto.BatchDTO;
import com.yw.msmp.entity.BatchEntity;
import com.yw.msmp.entity.SearchDrugDTO;
import com.yw.msmp.enums.BatchStatusEnum;
import com.yw.msmp.service.BatchService;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yanhaoyuwen
 */
@RestController
@RequestMapping( "/admin/batch" )
@CrossOrigin( "*" )
@Log4j2
public class BatchController {

    @Resource
    private BatchService batchService;

    @GetMapping( "/page" )
    //    @GetMapping( "/page/{batchStatus}" )
    public R getAll( int current, int size
                     //            , @PathVariable( value = "batchStatus", required = false ) String batchStatus
                   ) {
        log.info( "获取指定状态整体全部库存" );
        // batchStatus 0-进货中/1-在库/2-过期/3-售完

        // 对状态进行一个转换——string——>BatchStatusEnum
        IPage< BatchDTO > page = batchService.findPageOverAll( current, size
                                                               //                , batchStatus
                                                             );
        return new R( ResponseEnum.SUCCESS, page );
    }

    @CheckDrugBatch
    @GetMapping( "/search/page" )
    public R searchAll( SearchDrugDTO searchDrugDTO ) {
        log.info( "获取指定状态整体全部库存" + searchDrugDTO );
        // batchStatus 0-进货中/1-在库/2-过期/3-售完

        // 对状态进行一个转换——string——>BatchStatusEnum
        IPage< BatchDTO > page = batchService.searchPage( searchDrugDTO );
        return new R( ResponseEnum.SUCCESS, page );
    }


    /**
     * 对于新增，只需要药品跟进货数量，后期再通过put更新填补批号、有效期
     *
     * @param batchEntity 新增批次信息
     * @return 新增结果
     */
    @PostMapping
    public R addBatch( BatchEntity batchEntity ) {
        log.info( "新增的批次：" + batchEntity );
        batchService.addBatch( batchEntity );
        return new R( ResponseEnum.SUCCESS, null );
    }

    /**
     * 批量改变
     *
     * @param batchEntity 批次信息
     * @return 改变结果
     */
    @PutMapping
    @Transactional( rollbackFor = Exception.class )
    public R changeBatch( BatchEntity batchEntity ) {
        log.info( "改变状态：" + batchEntity );
        batchService.changeBatch( batchEntity );
        return new R( ResponseEnum.SUCCESS, null );
    }

    /**
     * 批量删除
     *
     * @param drugDetailId 药品详情id
     * @return 删除结果
     */
    @DeleteMapping( "{drugDetailId}" )
    public R delBatch( @PathVariable( "drugDetailId" ) Integer drugDetailId ) {
        log.info( "删除drugDetailId为：" + drugDetailId );
        int i = batchService.removeByDrugDetailId( drugDetailId );
        return new R( ResponseEnum.SUCCESS, i );
    }

    // 因为不太需要所有删除了分页

    /**
     * @param drugDetailId 药品详情id
     * @param active       对应状态
     * @return 该药品对应的批次
     */
    @GetMapping( "/detail/{drugDetailId}" )
    public R getDetail( @PathVariable( "drugDetailId" ) Integer drugDetailId, String active ) {
        log.info( "开始获取drugDetailId为" + drugDetailId + "且状态为" + active + "的详细批次详细" );
        List< BatchEntity > batchEntityList = batchService.getBatchDetail( drugDetailId, active );
        return new R( ResponseEnum.SUCCESS, batchEntityList );
    }

    //    @PostMapping( "batchadddto" )
    //    public R addBatchAddDTO( BatchAddDTO batchAddDTO ) {
    //        log.info( "新增的批次：" + batchAddDTO );
    //        batchService.myAddBatch( batchAddDTO );
    //        return new R( );
    //    }

    /**
     * 获取批次状态
     *
     * @return 批次状态
     */
    @GetMapping( "/status" )
    public R getStatus( ) {
        return new R( ResponseEnum.SUCCESS, BatchStatusEnum.getChineseList( ) );
    }

    /**
     * 更新批次状态
     *
     * @param batchEntity 批次
     * @return 更新结果
     */
    @PutMapping( "/detail" )
    public R changeDetail( BatchEntity batchEntity ) {
        log.info( "更新：" + batchEntity );
        boolean b = batchService.updateById( batchEntity );
        if ( !b ) {
            throw new AppException( ResponseEnum.UPDATE_ERROR );
        }
        return new R( ResponseEnum.SUCCESS, null );
    }


}

