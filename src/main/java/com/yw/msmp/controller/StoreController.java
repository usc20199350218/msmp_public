package com.yw.msmp.controller;

import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.entity.StoreEntity;
import com.yw.msmp.service.StoreService;
import com.yw.msmp.vo.StoreVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yanhaoyuwen
 */
@RestController
@RequestMapping( "/admin/store" )
@CrossOrigin( "*" )
@Log4j2
public class StoreController {

    @Resource
    private StoreService storeService;

    @GetMapping
    public R getAll( ) {
        List< StoreEntity > list = storeService.list( );
        return new R( ResponseEnum.SUCCESS, list );
    }

    @GetMapping( "/{userId}" )
    public R getStoreByUserId( @PathVariable( "userId" ) Integer userId ) {
        List< StoreEntity > list = storeService.getStoreListByUserId( userId );
        return new R( ResponseEnum.SUCCESS, list );
    }

    @PostMapping
    public R addStore( StoreEntity storeEntity ) {
        log.info( "即将新增的storeEntity:" + storeEntity );
        storeService.add( storeEntity );
        return new R( ResponseEnum.SUCCESS, null );
    }

    @PutMapping
    public R updStore( StoreEntity storeEntity ) {
        log.info( "即将更新的storeEntity:" + storeEntity );
        boolean b = storeService.updateById( storeEntity );
        if ( b ) {
            return new R( ResponseEnum.SUCCESS, storeEntity );
        }
        throw new AppException( ResponseEnum.UPDATE_ERROR );
    }

    @DeleteMapping( "{storeId}" )
    public R delStore( @PathVariable( "storeId" ) Integer storeId ) {
        boolean b = storeService.removeById( storeId );
        if ( b ) {
            return new R( ResponseEnum.SUCCESS, null );
        }
        throw new AppException( ResponseEnum.DEL_ERROR );
    }

    @GetMapping( "/apply" )
    public R applyList( ) {
        List< StoreVo > list = storeService.getApplyList( );
        return new R( ResponseEnum.SUCCESS, list );
    }

    @GetMapping( "/user/{userId}" )
    public R getUserOrderStore( @PathVariable( "userId" ) Integer userId ) {

        List< StoreEntity > storeList = storeService.getUserOrder( userId );

        return new R( ResponseEnum.SUCCESS, storeList );
    }

}

