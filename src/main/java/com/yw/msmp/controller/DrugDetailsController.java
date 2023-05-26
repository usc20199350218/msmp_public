package com.yw.msmp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.dto.DrugDetailsDTO;
import com.yw.msmp.dto.DrugVo;
import com.yw.msmp.entity.DrugDetailsEntity;
import com.yw.msmp.service.DrugDetailsService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping( "/admin/drugdetail" )
@CrossOrigin
@Log4j2
@Api( tags = "通用使用的 药品详情 接口类" )
public class DrugDetailsController {

    @Resource
    private DrugDetailsService drugDetailsService;

    @GetMapping( "/drugvo" )
    public R getDetailList( ) {
        List< DrugVo > list = drugDetailsService.selectList( null );
        return new R( ResponseEnum.SUCCESS, list );
    }

    @GetMapping( "/page" )
    public R getDetailsPage( int current, int size ) {
        IPage< DrugDetailsDTO > page = drugDetailsService.findPage( current, size );
        return new R( ResponseEnum.SUCCESS, page );
    }

    @PostMapping
    public R addDrugDetails( DrugDetailsEntity drugDetailsEntity ) {
        log.info( "新增的药品详情：" + drugDetailsEntity );
        boolean b = drugDetailsService.addDrugDetails( drugDetailsEntity );
        return new R( ResponseEnum.SUCCESS, b );
    }

    @PutMapping
    public R changeDrugDetails( DrugDetailsEntity drugDetailsEntity ) {
        log.info( "修改的药品详情：" + drugDetailsEntity );
        boolean b = drugDetailsService.updateById( drugDetailsEntity );
        if ( !b ) {
            throw new AppException( ResponseEnum.UPDATE_ERROR );
        }
        return new R( ResponseEnum.SUCCESS, b );
    }

    @DeleteMapping( "{drugDetailsId}" )
    public R delDrugDetails( @PathVariable( "drugDetailsId" ) int drugDetailsId ) {
        log.info( "删除的drugDetailsId：" + drugDetailsId );
        boolean b = drugDetailsService.removeById( drugDetailsId );
        if ( !b ) {
            throw new AppException( ResponseEnum.DEL_ERROR );
        }
        return new R( ResponseEnum.SUCCESS, b );
    }


    // todo 快捷状态修改

    @PutMapping( "/status" )
    public R changeStatus( Integer drugDetailsStatus, Integer drugDetailId ) {
        log.info( "修改状态：" + drugDetailId + "|" + drugDetailsStatus );
        boolean b = drugDetailsService.updateById( DrugDetailsEntity.builder( )
                                                                    .drugDetailId( drugDetailId )
                                                                    .drugDetailsStatus( drugDetailsStatus )
                                                                    .build( ) );
        if ( !b ) {
            throw new AppException( ResponseEnum.UPDATE_ERROR );
        }
        return new R( ResponseEnum.SUCCESS, b );
    }

}
