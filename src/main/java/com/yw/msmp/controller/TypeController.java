package com.yw.msmp.controller;

import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.entity.TypeEntity;
import com.yw.msmp.service.TypeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yanhaoyuwen
 */
@RestController
@RequestMapping( "/admin/type" )
@CrossOrigin( "*" )
@Log4j2
public class TypeController {

    @Resource
    private TypeService typeService;

    @GetMapping
    public R list( ) {
        log.info( "开始获取" );
        return new R( ResponseEnum.SUCCESS, typeService.list( ) );
    }

    @PostMapping
    public R add( TypeEntity typeEntity ) {
        typeService.add( typeEntity );
        return new R( ResponseEnum.SUCCESS, null );
    }

    @PutMapping
    public R upd( TypeEntity typeEntity ) {
        log.info( "更新" + typeEntity );
        typeService.upd( typeEntity );
        return new R( ResponseEnum.SUCCESS, null );
    }

    @DeleteMapping( "{typeId}" )
    public R del( @PathVariable( "typeId" ) Integer typeId ) {
        typeService.del( typeId );
        return new R( ResponseEnum.SUCCESS, null );
    }

}
