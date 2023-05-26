package com.yw.msmp.controller;
/*
 * @PACKAGE_NAME com.yw.msmp.controller
 * @author  yanhaoyuwen
 * @version  1.0
 */

import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.entity.AddressEntity;
import com.yw.msmp.service.AddressService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yanhaoyuwen
 **/
@RestController
@CrossOrigin( "*" )
@RequestMapping( "/api/address" )
@Api( description = "地址地址信息API" )
@Log4j2
public class AddressController {

    @Resource
    private AddressService addressService;

    @GetMapping( "/{userId}" )
    public R getUserAddress( @PathVariable String userId ) {
        List< AddressEntity > list = addressService.searchAddress( userId );
        return new R( ResponseEnum.SUCCESS, list );
    }

    @PostMapping( "/save" )
    public R saveAddress( AddressEntity address ) {
        log.info( "address:" + address );
        boolean bool;
        if ( address.getAddressId( ) == null ) {
            // 新增
            bool = addressService.addNAddress( address );
        }
        else {
            // 更新
            bool = addressService.updNById( address );
        }
        return new R( ResponseEnum.SUCCESS, bool );
    }

    @DeleteMapping( "/{addressId}" )
    public R deleteAddress( @PathVariable String addressId ) {
        log.info( "addressId:" + addressId );
        boolean bool = addressService.removeById( addressId );
        log.info( "bool:" + bool );
        return new R( ResponseEnum.SUCCESS, bool );
    }

}
