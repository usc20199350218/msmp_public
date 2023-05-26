package com.yw.msmp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.enums.DeliveryStatusEnum;
import com.yw.msmp.service.DeliveryService;
import com.yw.msmp.vo.DeliveryVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping( "/admin/delivery" )
@CrossOrigin( "*" )
@Log4j2
public class DeliveryController {

    @Resource
    private DeliveryService deliveryService;

    @GetMapping( "/status" )
    public R getStatus( ) {
        List< String > chineseList = DeliveryStatusEnum.getChineseList( );
        log.info( "获取到的状态列表:" + chineseList );
        return new R( ResponseEnum.SUCCESS, chineseList );
    }

    @GetMapping( "/list" )
    public R getListByStatus( Integer userId, String status, Integer current, Integer size ) {
        log.info( "userId:" + userId );
        log.info( "status:" + status );
        IPage< DeliveryVO > deliveryList = deliveryService.selectStatusList( userId, status, current, size );
        log.info( "deliveryList:" + deliveryList );
        return new R( ResponseEnum.SUCCESS, deliveryList );
    }

    @PutMapping
    public R changeDelivery( Integer deliveryId, String deliveryStatus, Integer userId ) {
        log.info( "deliveryId:" + deliveryId );
        log.info( "deliveryStatus:" + deliveryStatus );
        log.info( "userId:" + userId );
        deliveryService.changeStatus( deliveryId, deliveryStatus, userId );
        return new R( ResponseEnum.SUCCESS, null );
    }

}
