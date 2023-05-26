package com.yw.msmp.controller;
/*
 * @PACKAGE_NAME com.yw.msmp.controller
 * @author  yanhaoyuwen
 * @version  1.0
 */

import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.enums.OrderTypeEnum;
import com.yw.msmp.enums.PayStatus;
import com.yw.msmp.enums.PaymentEnum;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author yanhaoyuwen
 **/
@RestController
@RequestMapping( "/api/common" )
@CrossOrigin
@Log4j2
@Api( tags = "通用接口类" )
public class CommonController {

    @GetMapping( "/payment/{payment}" )
    public R getPayment( @PathVariable( "payment" ) String payment ) {
        log.info( "status:" + payment );
        List< PaymentEnum > paymentEnumList = PaymentEnum.getAllBatchStatus( );
        //        if ( "online".equals( payment ) ) {
        //            paymentEnumList.remove( PaymentEnum.CASH );
        //        }
        log.info( "paymentEnumList:" + paymentEnumList );
        return new R<>( ResponseEnum.SUCCESS, paymentEnumList );
    }

    @GetMapping( "/order_type" )
    public R getOrderType( ) {
        List< String > chineseList = OrderTypeEnum.getChineseList( );
        return new R( ResponseEnum.SUCCESS, chineseList );
    }

    /**
     * 获取支付状态
     *
     * @return R
     */
    @GetMapping( "/map/pay_status" )
    public R getPayStatusMap( ) {

        Map< String, String > map = PayStatus.toMap( );

        return new R( ResponseEnum.SUCCESS, map );
    }

    @GetMapping( "/list/pay_status" )
    public R getPayStatusList( ) {

        List< String > list = PayStatus.getChineseList( );

        return new R( ResponseEnum.SUCCESS, list );
    }

    /**
     * 获取支付方式
     *
     * @return R
     */
    @GetMapping( "/map/pay/method" )
    public R getPayMethodMap( ) {
        Map< String, String > map = PaymentEnum.toMap( );
        return new R( ResponseEnum.SUCCESS, map );
    }

    @GetMapping( "/list/pay/method" )
    public R getPayMethodList( ) {
        List< String > list = PaymentEnum.getChineseList( );
        return new R( ResponseEnum.SUCCESS, list );
    }

}
