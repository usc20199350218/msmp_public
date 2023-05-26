package com.yw.msmp.controller;
/*
 * @PACKAGE_NAME com.yw.msmp.controller
 * @author  yanhaoyuwen
 * @version  1.0
 */

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.entity.ServiceEntity;
import com.yw.msmp.service.ServiceEntryService;
import com.yw.msmp.service.ServiceService;
import com.yw.msmp.vo.ServiceDetailVO;
import com.yw.msmp.vo.ServiceVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yanhaoyuwen
 **/
@RestController
@RequestMapping( "/api/service" )
@CrossOrigin( "*" )
@Log4j2
public class ServiceController {

    @Resource
    private ServiceService serviceService;

    @Resource
    private ServiceEntryService serviceEntryService;

    @GetMapping( "/last/{userId}" )
    public R getService( @PathVariable Integer userId ) {
        // 使用代码拼接完成map来提供
        ServiceVO serviceVO = serviceService.getLastVo( userId );
        return new R( ResponseEnum.SUCCESS, serviceVO );
    }

    @GetMapping( "/page/{userId}" )
    public R getServiceList( @PathVariable Integer userId, Integer current, Integer size ) {
        IPage< ServiceEntity > p = serviceService.getMyPage( userId, current, size );

        return new R( ResponseEnum.SUCCESS, p );
    }

    @GetMapping( "/blank" )
    public R getServiceMap( ) {
        List< ServiceDetailVO > list = serviceEntryService.getBlank( );
        return new R( ResponseEnum.SUCCESS, list );
    }

    @PostMapping
    @Transactional( rollbackFor = Exception.class )
    public R saveService( @RequestBody String object ) {
        log.info( "object:" + object );
        ServiceVO serviceVO = JSON.parseObject( object, ServiceVO.class );
        log.info( "serviceVO:" + serviceVO );
        if ( serviceVO == null ) {
            // 空白
            log.info( "空白" );
            throw new AppException( ResponseEnum.OPERATE_ERROR );
        }
        else if ( serviceVO.getServiceId( ) == null ) {
            // 新增
            log.info( "新增" );
            serviceService.addService( serviceVO );
        }
        else {
            // 更新
            log.info( "更新" );
            serviceService.updService( serviceVO );
        }
        return new R( ResponseEnum.SUCCESS, object );
    }

    @GetMapping( "/{serviceId}" )
    public R getServiceDetail( @PathVariable Integer serviceId ) {
        ServiceVO service = serviceService.getVoById( serviceId );
        return new R( ResponseEnum.SUCCESS, service );
    }


    @DeleteMapping( "/{serviceId}" )
    @Transactional( rollbackFor = Exception.class )
    public R deleteServiceDetail( @PathVariable Integer serviceId ) {
        log.info( "删除接口:" + serviceId );
        serviceService.deleteService( serviceId );
        return new R( ResponseEnum.SUCCESS, null );
    }

}
