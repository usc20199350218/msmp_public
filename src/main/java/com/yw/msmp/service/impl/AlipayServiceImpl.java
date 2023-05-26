package com.yw.msmp.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.internal.util.file.ByteArrayOutputStream;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yw.msmp.common.config.AlipayConfig;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.qrcode.QRCodeUtil;
import com.yw.msmp.common.qrcode.QrCodeResponse;
import com.yw.msmp.common.qrcode.QrResponse;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.common.util.DateUtils;
import com.yw.msmp.common.util.GenerateNum;
import com.yw.msmp.dto.AlipayDTO;
import com.yw.msmp.dto.PayDTO;
import com.yw.msmp.entity.OrderEntity;
import com.yw.msmp.entity.PayMatchEntity;
import com.yw.msmp.entity.StoreEntity;
import com.yw.msmp.enums.PayStatus;
import com.yw.msmp.mapper.OrderMapper;
import com.yw.msmp.mapper.PayMatchMapper;
import com.yw.msmp.mapper.StoreMapper;
import com.yw.msmp.service.AlipayService;
import com.yw.msmp.vo.JOrder;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Log4j2
@Service
public class AlipayServiceImpl implements AlipayService {

    @Resource
    private AlipayConfig alipayConfig;

    @Resource
    private StoreMapper storeMapper;

    @Resource
    private PayMatchMapper payMatchMapper;

    @Resource
    private OrderMapper orderMapper;

    @Override
    public byte[] alipay( PayDTO payVo ) {
        try {
            // 1：支付的用户
            Integer userId = payVo.getUserId( );
            // 2: 支付金额
            String money = payVo.getMoney( ).toString( );
            // 3: 支付的产品
            String title = "java面向对象";
            // 4: 支付的订单编号
            String orderNumber = GenerateNum.generateOrder( );
            // 5：支付宝携带的参数在回调中可以通过request获取
            JSONObject json = new JSONObject( );
            json.put( "userId", userId );
            json.put( "orderNumber", orderNumber );
            json.put( "money", money );
            String params = json.toString( );
            // 6：设置支付相关的信息
            AlipayTradePrecreateModel model = new AlipayTradePrecreateModel( );
            model.setOutTradeNo( orderNumber ); // 自定义订单号
            model.setTotalAmount( money );// 支付金额
            model.setSubject( title );// 支付的产品名称
            model.setBody( params );// 支付的请求体参数
            model.setTimeoutExpress( "30m" );// 支付的超时时间
            model.setStoreId( userId.toString( ) );// 支付的库存id
            QrCodeResponse qrCodeResponse = qrcodePay( model );

            ByteArrayOutputStream output = new ByteArrayOutputStream( );
            String logopath = "";
            try {
                logopath = ResourceUtils.getFile( "classpath:favicon.png" ).getAbsolutePath( );
            } catch ( Exception ex ) {
                logopath = ResourceUtils.getFile( "/www/images/favicon.png" ).getAbsolutePath( );
            }
            BufferedImage buffImg = QRCodeUtil.encode( qrCodeResponse.getQr_code( ), logopath, false );//获取二维码
            ImageOutputStream imageOut = ImageIO.createImageOutputStream( output );
            ImageIO.write( buffImg, "JPEG", imageOut );
            imageOut.close( );
            ByteArrayInputStream input = new ByteArrayInputStream( output.toByteArray( ) );
            return FileCopyUtils.copyToByteArray( input );
        } catch ( Exception ex ) {
            ex.printStackTrace( );
            return new byte[ 0 ];
        }
    }

    @Override
    public String jaliPay( JOrder order ) throws AlipayApiException {
        log.info( "成功进入" );
        // 1、获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient( alipayConfig.gatewayUrl,
                                                             alipayConfig.app_id,
                                                             alipayConfig.merchant_private_key,
                                                             alipayConfig.format,
                                                             alipayConfig.charset,
                                                             alipayConfig.alipay_public_key,
                                                             alipayConfig.sign_type );

        // 2、设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest( );
        // 页面跳转同步通知页面路径
        alipayRequest.setReturnUrl( alipayConfig.return_url );
        // 服务器异步通知页面路径
        alipayRequest.setNotifyUrl( alipayConfig.notify_url );
        // 封装参数(以json格式封装)
        alipayRequest.setBizContent( JSON.toJSONString( order ) );

        // 3、请求支付宝进行付款，并获取支付结果
        String result = alipayClient.pageExecute( alipayRequest ).getBody( );
        log.debug( "result:" + result );
        // 返回付款信息
        return result;
    }

    /**
     * @return
     */
    @Override
    public AlipayTradePagePayResponse docPay( ) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient( alipayConfig.gatewayUrl,
                                                             alipayConfig.app_id,
                                                             alipayConfig.merchant_private_key,
                                                             alipayConfig.format,
                                                             alipayConfig.charset,
                                                             alipayConfig.alipay_public_key,
                                                             alipayConfig.sign_type );
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest( );
        request.setNotifyUrl( alipayConfig.notify_url );
        request.setReturnUrl( alipayConfig.return_url );
        JSONObject bizContent = new JSONObject( );
        bizContent.put( "out_trade_no", GenerateNum.generateOrder( ) );
        bizContent.put( "total_amount", 0.01 );
        bizContent.put( "subject", "测试商品" );
        bizContent.put( "product_code", "FAST_INSTANT_TRADE_PAY" );
        //bizContent.put("time_expire", "2022-08-01 22:00:00");

        //// 商品明细信息，按需传入
        //JSONArray goodsDetail = new JSONArray();
        //JSONObject goods1 = new JSONObject();
        //goods1.put("goods_id", "goodsNo1");
        //goods1.put("goods_name", "子商品1");
        //goods1.put("quantity", 1);
        //goods1.put("price", 0.01);
        //goodsDetail.add(goods1);
        //bizContent.put("goods_detail", goodsDetail);

        //// 扩展信息，按需传入
        //JSONObject extendParams = new JSONObject();
        //extendParams.put("sys_service_provider_id", "2088511833207846");
        //bizContent.put("extend_params", extendParams);

        request.setBizContent( bizContent.toString( ) );
        System.out.println( "request:" + request );
        log.info( "request:" + request );
        AlipayTradePagePayResponse response = alipayClient.pageExecute( request );
        System.out.println( "response:" + response );
        log.info( "response:" + response );
        if ( response.isSuccess( ) ) {
            System.out.println( "调用成功" );

        }
        else {
            System.out.println( "调用失败" );
        }
        return response;
    }

    /**
     * @param alipayDTO 前端数据
     * @return 支付宝付款代码
     */
    @Override
    public AlipayTradePagePayResponse computerScanCode( AlipayDTO alipayDTO ) throws AlipayApiException {
        // 检查是否支付
        QueryWrapper< OrderEntity > orderQueryWrapper = new QueryWrapper<>( );
        orderQueryWrapper.eq( "order_num", alipayDTO.getMerchantOrderNo( ) );

        OrderEntity order = orderMapper.selectOne( orderQueryWrapper );
        log.info( "order:" + order );
        if ( PayStatus.SUCCESS.toString( ).equals( order.getStatus( ) ) ) {
            throw new AppException( ResponseEnum.PAY_ERROR );
        }

        AlipayClient alipayClient = new DefaultAlipayClient( alipayConfig.gatewayUrl,
                                                             alipayConfig.app_id,
                                                             alipayConfig.merchant_private_key,
                                                             alipayConfig.format,
                                                             alipayConfig.charset,
                                                             alipayConfig.alipay_public_key,
                                                             alipayConfig.sign_type );
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest( );
        request.setNotifyUrl( alipayConfig.notify_url );
        request.setReturnUrl( alipayConfig.return_url );
        JSONObject bizContent = new JSONObject( );
        // 生成订单号
        String generateOrder = GenerateNum.generateOrder( );
        // 将提交的数据保存进数据库，用于通知到达之后更新支付状态
        int insert = payMatchMapper.insert( PayMatchEntity.builder( )
                                                          .outTradeNo( generateOrder )
                                                          .orderNum( alipayDTO.getMerchantOrderNo( ) )
                                                          .build( ) );
        if ( insert == 0 ) {
            throw new AppException( ResponseEnum.Repeating_Field_ERROR );
        }
        // 准备提交支付宝信息
        bizContent.put( "out_trade_no", generateOrder );
        bizContent.put( "total_amount", alipayDTO.getTotalAmount( ) );
        StoreEntity store = storeMapper.selectById( alipayDTO.getStoreId( ) );
        bizContent.put( "subject", store.getStoreName( ) + "店的订单" + alipayDTO.getMerchantOrderNo( ) );
        bizContent.put( "product_code", "FAST_INSTANT_TRADE_PAY" );
        bizContent.put( "time_expire", DateUtils.getStringDate( 30 ) );
        bizContent.put( "store_id", alipayDTO.getStoreId( ) );
        bizContent.put( "merchant_order_no", alipayDTO.getMerchantOrderNo( ) );

        log.info( "bizContent:" + bizContent );
        request.setBizContent( bizContent.toString( ) );
        log.info( "request:" + request );
        AlipayTradePagePayResponse response = alipayClient.pageExecute( request );
        log.info( "response:" + response );
        if ( response.isSuccess( ) ) {
            log.info( "调用成功" );
        }
        else {
            log.info( "调用失败" );
        }
        return response;
    }

    /**
     * @param request
     * @return
     */
    @Override
    public boolean callback( HttpServletRequest request ) {
        // 获取支付宝GET过来反馈信息
        Map< String, String > params = new HashMap<>( );
        log.debug( "request:" + request );
        Map requestParams = request.getParameterMap( );
        for ( Iterator iter = requestParams.keySet( ).iterator( ); iter.hasNext( ); ) {
            String name = ( String ) iter.next( );
            String[] values = ( String[] ) requestParams.get( name );
            String valueStr = "";
            for ( int i = 0; i < values.length; i++ ) {
                valueStr = ( i == values.length - 1 ) ? valueStr + values[ i ] : valueStr + values[ i ] + ",";
            }
            params.put( name, new String( valueStr.getBytes( StandardCharsets.ISO_8859_1 ), StandardCharsets.UTF_8 ) );
        }
        // 计算得出通知验证结果
        log.info( "1：---->获取支付宝回传的参数---------------------------------->" + params );
        //        log.info( "out_trade_no:" + params.get( "out_trade_no" ) );
        // 获取支付状态
        String tradeStatus = params.get( "trade_status" );
        // 判断是否支付成功
        if ( "TRADE_SUCCESS".equals( tradeStatus ) ) {
            // 获取提交的支付宝单号
            String outTradeNo = params.get( "out_trade_no" );

            // 获取支付宝单号与系统单号对应关系
            PayMatchEntity payMatch = payMatchMapper.selectById( outTradeNo );
            String tradeNo = params.get( "trade_no" );
            // 插入支付宝订单号
            payMatch.setTradeNo( tradeNo );
            payMatchMapper.updateById( payMatch );
            // 获取订单
            QueryWrapper< OrderEntity > queryWrapper = new QueryWrapper<>( );
            queryWrapper.eq( "order_num", payMatch.getOrderNum( ) );
            List< OrderEntity > orderEntities = orderMapper.selectList( queryWrapper );
            OrderEntity order = orderEntities.get( 0 );
            // 更新订单
            order.setStatus( PayStatus.SUCCESS.toString( ) );
            order.setPaymentTime( new Date( ) );
            log.info( "orderPayTime:" + order );
            orderMapper.updateById( order );
        }

        //        // 返回公共参数
        //        String extparamString = request.getParameter( "extra_common_param" );
        //        log.info( "2---->：支付宝交易返回的参数：{}", extparamString );
        //        String tradeno = params.get( "trade_no" );
        //        //交易完成  q
        //        String body = params.get( "body" );
        //        if ( StringUtils.isEmpty( tradeno ) ) {
        //            tradeno = params.get( "trade_no" );
        //        }
        //        log.info( "3---->:【支付宝】交易的参数信息是：{}，流水号是：{}", body, tradeno );
        //        try {
        //            JSONObject bodyJson = JSONObject.parseObject( body );
        //            String userId = bodyJson.getString( "userId" );
        //            String ptype = bodyJson.getString( "ptype" );
        //            String orderNumber = bodyJson.getString( "orderNumber" );
        //            log.info( "4---->:【支付宝】交易的参数信息是：ptype:{}，orderNumber是：{}", ptype, orderNumber );
        //            // 课程支付
        //            if ( "productcourse".equalsIgnoreCase( ptype ) ) {
        ////                payCommonService.payproductcourse( bodyJson, userId, orderNumber, tradeno, "1" );
        //            }
        //        } catch ( Exception ex ) {
        //            log.info( "支付宝支付出现了异常....." );
        //            ex.printStackTrace( );
        //            return false;
        //        }


        String sign = params.get( "sign" );
        String content = AlipaySignature.getSignCheckContentV1( params );
        boolean checkSignature = false; // 验证签名
        try {
            checkSignature = AlipaySignature.rsa256CheckContent( content,
                                                                 sign,
                                                                 alipayConfig.getAlipay_public_key( ),
                                                                 "UTF-8" );
        } catch ( AlipayApiException e ) {
            throw new RuntimeException( e );
        }
        log.info( "5---->:验签结果:{}", checkSignature );
        // 支付宝验签
        if ( checkSignature ) {
            // 验签通过
            System.out.println( "交易名称: " + params.get( "subject" ) );
            System.out.println( "交易状态: " + params.get( "trade_status" ) );
            System.out.println( "支付宝交易凭证号: " + params.get( "trade_no" ) );
            System.out.println( "商户订单号: " + params.get( "out_trade_no" ) );
            System.out.println( "交易金额: " + params.get( "total_amount" ) );
            System.out.println( "买家在支付宝唯一id: " + params.get( "buyer_id" ) );
            System.out.println( "买家付款时间: " + params.get( "gmt_payment" ) );
            System.out.println( "买家付款金额: " + params.get( "buyer_pay_amount" ) );

            // 更新订单未已支付
            //            ordersMapper.updateState(tradeNo, "已支付", gmtPayment, alipayTradeNo);
        }


        return true;
    }


    @Override
    public boolean refund( String orderNum, BigDecimal refundAmount ) throws
                                                                      AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient( alipayConfig.getGatewayUrl( ),
                                                             alipayConfig.getApp_id( ),
                                                             alipayConfig.getMerchant_private_key( ),
                                                             alipayConfig.getFormat( ),
                                                             alipayConfig.getCharset( ),
                                                             alipayConfig.getAlipay_public_key( ),
                                                             alipayConfig.getSign_type( ) );
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest( );
        JSONObject bizContent = new JSONObject( );

        // 查询支付
        QueryWrapper< PayMatchEntity > matchQueryWrapper = new QueryWrapper<>( );
        matchQueryWrapper.eq( "order_num", orderNum );
        List< PayMatchEntity > payMatchEntities = payMatchMapper.selectList( matchQueryWrapper );
        PayMatchEntity payMatch = payMatchEntities.get( payMatchEntities.size( ) - 1 );

        bizContent.put( "trade_no", payMatch.getTradeNo( ) );
        bizContent.put( "refund_amount", refundAmount );
        bizContent.put( "out_request_no", "HZ01RF001" );
        // 返回参数选项，按需传入
        //JSONArray queryOptions = new JSONArray();
        //queryOptions.add("refund_detail_item_list");
        //bizContent.put("query_options", queryOptions);

        request.setBizContent( bizContent.toString( ) );
        AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute( request );
        log.info( "调用支付宝退款接口，请求参数：" + bizContent.toString( ) );
        log.info( "response.getBody:" + response.getBody( ) );
        log.info( "response.getMsg:" + response.getMsg( ) );
        log.info( "+response.getSubMsg():" + response.getSubMsg( ) );
        log.info( "+response.getOutTradeNo():" + response.getOutTradeNo( ) );
        log.info( "+response.getTradeNo():" + response.getTradeNo( ) );
        log.info( "+response.getRefundAmount():" + response.getRefundAmount( ) );
        log.info( "+response.getCode():" + response.getCode( ) );
        log.info( "response.getRefundStatus():" + response.getRefundStatus( ) );
        if ( response.isSuccess( ) ) {
            System.out.println( "调用成功" );
            // 如果success直接更新状态
            return true;
        }
        else {
            System.out.println( "调用失败" );
            return false;
        }

    }


    public QrCodeResponse qrcodePay( AlipayTradePrecreateModel model ) {
        // 1: 获取阿里请求客户端
        AlipayClient alipayClient = new DefaultAlipayClient( alipayConfig.getGatewayUrl( ),
                                                             alipayConfig.getApp_id( ),
                                                             alipayConfig.getMerchant_private_key( ),
                                                             "JSON",
                                                             alipayConfig.getCharset( ),
                                                             alipayConfig.getAlipay_public_key( ),
                                                             alipayConfig.getSign_type( ) ); //获得初始化的AlipayClient
        //        AlipayClient alipayClient = getAlipayClient( );
        // 2: 获取阿里请求对象
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest( );
        // 3：设置请求参数的集合，最大长度不限
        request.setBizModel( model );
        // 设置异步回调地址
        request.setNotifyUrl( alipayConfig.getNotify_url( ) );
        // 设置同步回调地址
        request.setReturnUrl( alipayConfig.getReturn_url( ) );
        AlipayTradePrecreateResponse alipayTradePrecreateResponse = null;
        try {
            alipayTradePrecreateResponse = alipayClient
                    //                    .pageExecute( request )
                    .execute( request );
        } catch ( AlipayApiException e ) {
            e.printStackTrace( );
        }
        QrResponse qrResponse = JSON.parseObject( alipayTradePrecreateResponse.getBody( ), QrResponse.class );
        return qrResponse.getAlipay_trade_precreate_response( );
    }

    //    private AlipayClient getAlipayClient( ) {
    //        AlipayClient alipayClient = new DefaultAlipayClient( alipayConfig.getGatewayUrl( ), alipayConfig.getApp_id(
    //        ), alipayConfig.getMerchant_private_key( ), "JSON", alipayConfig.getCharset( ), alipayConfig
    //        .getAlipay_public_key( ), alipayConfig.getSign_type( ) ); //获得初始化的AlipayClient
    //        return alipayClient;
    //    }


}

