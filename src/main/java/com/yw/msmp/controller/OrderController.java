package com.yw.msmp.controller;

import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.dto.SearchOrderDTO;
import com.yw.msmp.entity.OrderDetailEntity;
import com.yw.msmp.entity.OrderEntity;
import com.yw.msmp.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yanhaoyuwen
 */
@RestController
@RequestMapping( "/admin/order" )
@CrossOrigin( "*" )
@Log4j2
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 线下结算
     *
     * @param userId  用户id
     * @param payment 支付方式
     * @param orderId 订单id
     * @return R
     */
    @PutMapping( "/offline" )
    @Transactional( rollbackFor = Exception.class )
    public R putOrder( Integer userId, String payment, Integer orderId ) {
        log.info( userId + "|" + payment + "|" + orderId );
        orderService.continueOrder( userId, payment, orderId );
        return new R( ResponseEnum.SUCCESS, userId + "|" + payment + "|" + orderId );
    }

    /**
     * 取消订单
     *
     * @param orderId 订单id
     * @return R
     */
    @PutMapping( "/cancel/{orderId}" )
    public R cancelOrder( @PathVariable( "orderId" ) Integer orderId ) {
        log.info( "orderId:" + orderId );
        orderService.cancel( orderId );
        return new R( ResponseEnum.SUCCESS, orderId );
    }

    /**
     * 检查
     *
     * @param orderId 订单id
     * @return R
     */
    @GetMapping( "/examine/{orderId}" )
    public R examineOrder( @PathVariable( "orderId" ) Integer orderId ) {
        log.info( "order" + orderId );
        boolean bool = orderService.examineOrderId( orderId );
        return new R( ResponseEnum.SUCCESS, bool );
    }

    @GetMapping( "/{orderType}" )
    public R getAllOnline( @PathVariable( "orderType" ) String orderType ) {
        log.info( "orderType:" + orderType );
        List< OrderEntity > allList = orderService.getAllList( orderType );
        return new R( ResponseEnum.SUCCESS, allList );
    }

    @GetMapping( "/user/{userId}" )
    public R searchOrder( @PathVariable Integer userId, SearchOrderDTO searchOrderDTO ) {

        log.info( "userId:" + userId );
        log.info( "orderType:" + searchOrderDTO );
        IPage< OrderEntity > page = orderService.searchOrder( userId, searchOrderDTO );

        return new R( ResponseEnum.SUCCESS, page );
    }

    @GetMapping( "/detail/{orderId}" )
    public R getOrderDetail( @PathVariable( "orderId" ) Integer orderId ) {
        log.info( "orderId:" + orderId );
        List< OrderDetailEntity > list = orderService.getOrderDetail( orderId );
        return new R( ResponseEnum.SUCCESS, list );
    }


    @PutMapping( "/refund/{orderId}" )
    @Transactional( rollbackFor = Exception.class )
    public R refunding( @PathVariable( "orderId" ) Integer orderId ) throws AlipayApiException {
        log.info( "refund" );
        boolean refunding = orderService.refunding( orderId );
        return new R( ResponseEnum.SUCCESS, refunding );
    }

}
