package com.yw.msmp.controller;
/*
 * @PACKAGE_NAME com.yw.msmp.controller
 * @author  yanhaoyuwen
 * @version  1.0
 */


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.entity.ServiceEntryEntity;
import com.yw.msmp.service.ServiceEntryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yanhaoyuwen
 **/
@RestController
@RequestMapping( "/admin/service_entry" )
@CrossOrigin( "*" )
@Log4j2
public class ServiceEntryController {

    @Resource
    private ServiceEntryService serviceEntryService;

    @GetMapping( "/page" )
    public R getPage( String searchMethod, String searchText, Integer current, Integer size ) {
        IPage< ServiceEntryEntity > page = serviceEntryService.getPage( searchMethod, searchText, current, size );
        return new R( ResponseEnum.SUCCESS, page );
    }

    @PostMapping
    public R add( ServiceEntryEntity serviceEntry ) {
        if ( serviceEntry.getServiceEntryId( ) == null ) {
            log.info( "新增:" + serviceEntry );
            serviceEntryService.add( serviceEntry );
        }
        else {
            log.info( "更新:" + serviceEntry );
            serviceEntryService.upd( serviceEntry );
        }
        return new R( ResponseEnum.SUCCESS, null );
    }

    @DeleteMapping( "/{serviceEntryId}" )
    public R del( @PathVariable Integer serviceEntryId ) {
        log.info( "删除接口:" + serviceEntryId );
        serviceEntryService.removeById( serviceEntryId );
        return new R( ResponseEnum.SUCCESS, null );
    }

}
