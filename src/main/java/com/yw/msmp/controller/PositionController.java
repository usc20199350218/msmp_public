package com.yw.msmp.controller;

import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.entity.PositionEntity;
import com.yw.msmp.service.PositionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping( "/admin/position" )
@CrossOrigin( "*" )
@Log4j2
public class PositionController {

    @Resource
    private PositionService positionService;

    @GetMapping
    public R getList( ) {
        log.info( "开始获取职位list" );
        List< PositionEntity > list = positionService.list( );
        log.info( "职位：" + list );
        return new R( ResponseEnum.SUCCESS, list );
    }

    @PostMapping
    public R addPosition( PositionEntity positionEntity ) {
        positionService.add( positionEntity );
        return new R( ResponseEnum.SUCCESS, null );
    }

    @PutMapping
    public R updPosition( PositionEntity positionEntity ) {
        positionService.upd( positionEntity );
        return new R( ResponseEnum.SUCCESS, null );
    }

    @DeleteMapping( "{positionId}" )
    public R delPosition( @PathVariable( "positionId" ) Integer positionId ) {
        positionService.del( positionId );
        return new R( ResponseEnum.SUCCESS, positionId );
    }

}
