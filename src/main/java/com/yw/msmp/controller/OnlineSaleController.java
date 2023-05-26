package com.yw.msmp.controller;
/*
 * @PACKAGE_NAME com.yw.msmp.controller
 * @author  yanhaoyuwen
 * @version  1.0
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.dto.AlipayDTO;
import com.yw.msmp.dto.DrugDetailsDTO;
import com.yw.msmp.dto.DrugSearchDTO;
import com.yw.msmp.dto.OnlineOrderDTO;
import com.yw.msmp.entity.StoreEntity;
import com.yw.msmp.service.OrderService;
import com.yw.msmp.service.StoreBatchService;
import com.yw.msmp.service.StoreService;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yanhaoyuwen
 **/
@RestController
@RequestMapping( "/api/online" )
@CrossOrigin( "*" )
@Log4j2
public class OnlineSaleController {

    @Resource
    private StoreService storeService;

    @Resource
    private StoreBatchService storeBatchService;

    @Resource
    private OrderService orderService;

    @GetMapping( "/store" )
    public R getOnlineList( ) {
        List< StoreEntity > storeEntityList = storeService.getOnlineStoreList( );
        log.info( "storeEntityList:" + storeEntityList );
        return new R( ResponseEnum.SUCCESS, storeEntityList );
    }

    @GetMapping( "/{storeId}" )
    public R searchDrug( @PathVariable( "storeId" ) Integer storeId, DrugSearchDTO drugSearchDTO ) {
        log.info( "storeId:" + storeId );
        log.info( "drugSearchDTO:" + drugSearchDTO );
        IPage< DrugDetailsDTO > iPage = storeBatchService.searchList( storeId, drugSearchDTO );
        log.info( "iPage:" + iPage );
        return new R( ResponseEnum.SUCCESS, iPage );
    }

    @PostMapping( "/order" )
    @Transactional( rollbackFor = Exception.class )
    public R createOrder( OnlineOrderDTO onlineOrderDTO ) {
        log.info( "在线结算" + onlineOrderDTO );
        //        AlipayTradePagePayResponse alipayTradePagePayResponse = orderService.createOnlineOrder( onlineOrderDTO );
        //        log.info( "alipayTradePagePayResponse:" + alipayTradePagePayResponse );
        //        return new R( ResponseEnum.SUCCESS, alipayTradePagePayResponse );

        AlipayDTO alipayDTO = orderService.createOnlineOrderPlus( onlineOrderDTO );
        return new R( ResponseEnum.SUCCESS, alipayDTO );
    }

    @GetMapping( "/examine/{orderNum}" )
    public R getStatus( @PathVariable( "orderNum" ) String orderNum ) {
        Boolean bool = orderService.examineStatus( orderNum );
        log.info( bool );
        return new R( ResponseEnum.SUCCESS, bool );
    }

}
