package com.yw.msmp.controller;
/*
 * @PACKAGE_NAME com.yw.msmp.controller
 * @author  yanhaoyuwen
 * @version  1.0
 */


import com.yw.msmp.common.aop.anno.CheckEfficient;
import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.service.ShoppingCartService;
import com.yw.msmp.vo.ScDrugVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping( "/api/shopping_cart" )
@CrossOrigin( "*" )
@Log4j2
/**
 * @author yanhaoyuwen
 **/ public class ShoppingCartController {

    @Resource
    private ShoppingCartService shoppingCartService;

    @CheckEfficient
    @GetMapping( "/show/{userId}" )
    public R getList( @PathVariable( "userId" ) Integer userId ) {
        List< ScDrugVO > scDrugList = shoppingCartService.getUserSc( userId );
        return new R( ResponseEnum.SUCCESS, scDrugList );
    }

    @CheckEfficient
    @GetMapping( "/search/{userId}" )
    public R search( @PathVariable( "userId" ) Integer userId,
                     String searchText,
                     String searchMethod,
                     Integer storeId ) {
        List< ScDrugVO > scDrugVOList = shoppingCartService.getSearchList( userId, storeId, searchMethod, searchText );
        return new R( ResponseEnum.SUCCESS, scDrugVOList );
    }

    @CheckEfficient
    @PutMapping
    public R update( Integer cartId, Integer number ) {
        log.info( "cartId:" + cartId + ",number:" + number );
        shoppingCartService.updateCart( cartId, number );
        return new R( ResponseEnum.SUCCESS, null );
    }

    @CheckEfficient
    @PostMapping
    public R addCart( Integer userId, Integer drugDetailId, Integer storeId ) {
        log.info( "userId:" + userId + ",drugDetailId:" + drugDetailId + ",storeId:" + storeId );
        shoppingCartService.add( userId, drugDetailId, storeId );
        return new R( ResponseEnum.SUCCESS, null );
    }

    @CheckEfficient
    @GetMapping( "/{userId}/number" )
    public R getUserNumber( @PathVariable( "userId" ) Integer userId ) {
        Integer num = shoppingCartService.getUserNumber( userId );
        return new R( ResponseEnum.SUCCESS, num );
    }

}
