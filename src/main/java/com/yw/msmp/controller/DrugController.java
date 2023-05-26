package com.yw.msmp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.entity.DrugEntity;
import com.yw.msmp.service.DrugService;
import com.yw.msmp.vo.DrugVo;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping( "/admin/drug" )
@CrossOrigin
@Log4j2
@Api( tags = "通用使用的 药品信息 接口类" )
public class DrugController {

    @Resource
    private DrugService drugService;

    @GetMapping
    public R getAll( ) {
        return new R( ResponseEnum.SUCCESS, drugService.list( ) );
    }


    @GetMapping( "/page" )
    public R getAllList( int current, int size ) {
        IPage< DrugVo > page = drugService.findPage( current, size );
        log.info( page.getRecords( )
                      .get( 0 ) );
        return new R( ResponseEnum.SUCCESS, page );
    }

    @PostMapping
    public R addDrug( DrugEntity drugEntity ) {
        log.info( drugEntity );
        boolean save = drugService.save( drugEntity );
        if ( !save ) {
            throw new AppException( ResponseEnum.ADD_ERROR );
        }
        return new R( ResponseEnum.SUCCESS, save );
    }

    @PutMapping
    public R changeDrug( DrugEntity drugEntity ) {
        log.info( drugEntity );
        boolean b = drugService.updateById( drugEntity );
        return new R( ResponseEnum.SUCCESS, b );
    }

    @PutMapping( "/status" )
    public R statusChange( Integer status, Integer drugId ) {
        log.info( "将drugId为" + drugId + "的状态修改为" + status );
        boolean upb = drugService.myUpdate( status, drugId );
        return new R( ResponseEnum.SUCCESS, upb );
    }

    @DeleteMapping( "{drugId}" )
    public R delDrug( @PathVariable Integer drugId ) {
        boolean b = drugService.removeById( drugId );
        if ( !b ) {
            throw new AppException( ResponseEnum.DEL_ERROR );
        }
        return new R( ResponseEnum.SUCCESS, b );
    }

}
