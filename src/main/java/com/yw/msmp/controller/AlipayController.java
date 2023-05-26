package com.yw.msmp.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradePayResponse;
import com.yw.msmp.common.config.AlipayConfig;
import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.common.util.GenerateNum;
import com.yw.msmp.dto.AlipayDTO;
import com.yw.msmp.dto.PayDTO;
import com.yw.msmp.service.AlipayService;
import com.yw.msmp.service.PayCommonService;
import com.yw.msmp.vo.JOrder;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author yanhaoyuwen
 */
@Log4j2
@CrossOrigin( "*" )
@RestController
@RequestMapping( "/api/alipay" )
public class AlipayController {

    @Resource
    private AlipayConfig alipayConfig;

    @Resource
    private AlipayService alipayService;

    @Resource
    private PayCommonService payCommonService;

    @GetMapping( "/pay" )
    @ResponseBody
    public byte[] alipay( PayDTO payDTO ) {
        payDTO.setUserId( 1 );
        log.info( payDTO );
        return alipayService.alipay( payDTO );
    }

    /**
     * 支付回调
     *
     * @param request 请求
     * @return 是否成功
     *
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping( "/notify" )
    public String notifyUrl( HttpServletRequest request ) throws Exception {
        log.info( "回调" );
        boolean result = alipayService.callback( request );
        if ( result ) {
            return "success";
        }
        else {
            return "fail";
        }
    }

    @GetMapping
    public void payTest( ) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient( alipayConfig.gatewayUrl,
                                                             alipayConfig.app_id,
                                                             alipayConfig.merchant_private_key,
                                                             "json",
                                                             alipayConfig.charset,
                                                             alipayConfig.alipay_public_key,
                                                             alipayConfig.sign_type );
        AlipayTradePayRequest request = new AlipayTradePayRequest( );
        JSONObject bizContent = new JSONObject( );
        bizContent.put( "out_trade_no", "20210817010101001" );
        bizContent.put( "total_amount", 0.01 );
        bizContent.put( "subject", "测试商品" );
        bizContent.put( "scene", "bar_code" );
        bizContent.put( "auth_code", "28763443825664394" );

        //// 商品明细信息
        //JSONArray goodsDetail = new JSONArray();
        //JSONObject goods1 = new JSONObject();
        //goods1.put("goods_id", "goodsNo1");
        //goods1.put("goods_name", "子商品1");
        //goods1.put("quantity", 1);
        //goods1.put("price", 0.01);
        //goodsDetail.add(goods1);
        //bizContent.put("goods_detail", goodsDetail);

        //// 扩展信息
        //JSONObject extendParams = new JSONObject();
        //extendParams.put("sys_service_provider_id", "2088511833207846");
        //bizContent.put("extend_params", extendParams);

        //// 结算信息
        //JSONObject settleInfo = new JSONObject();
        //JSONArray settleDetailInfos = new JSONArray();
        //JSONObject settleDetail = new JSONObject();
        //settleDetail.put("trans_in_type", "defaultSettle");
        //settleDetail.put("amount", 0.01);
        //settleDetailInfos.add(settleDetail);
        //settleInfo.put("settle_detail_infos", settleDetailInfos);
        //bizContent.put("settle_info", settleInfo);

        //// 二级商户信息
        //JSONObject subMerchant = new JSONObject();
        //subMerchant.put("merchant_id", "2088000603999128");
        //bizContent.put("sub_merchant", subMerchant);

        //// 业务参数信息
        //JSONObject businessParams = new JSONObject();
        //businessParams.put("busi_params_key", "busiParamsValue");
        //bizContent.put("business_params", businessParams);

        //// 营销信息
        //JSONObject promoParams = new JSONObject();
        //promoParams.put("promo_params_key", "promoParamsValue");
        //bizContent.put("promo_params", promoParams);

        //// 返回参数选项
        //JSONArray queryOptions = new JSONArray();
        //queryOptions.add("fund_bill_list");
        //queryOptions.add("voucher_detail_list");
        //bizContent.put("query_options", queryOptions);

        request.setBizContent( bizContent.toString( ) );
        AlipayTradePayResponse response = alipayClient.execute( request );
        if ( response.isSuccess( ) ) {
            System.out.println( "调用成功" );
        }
        else {
            System.out.println( "调用失败" );
        }
    }

    @PostMapping( "/pay" )
    public R juejingPay( String outTradeNo,
                         String subject,
                         String totalAmount,
                         String description ) throws AlipayApiException {
        JOrder order = new JOrder( );
        //        order.setOutTradeNo( outTradeNo );
        order.setOutTradeNo( GenerateNum.generateOrder( ) );
        //        GenerateNum.generateOrder( );
        order.setSubject( subject );
        order.setTotalAmount( totalAmount );
        order.setDescription( description );
        log.info( "order:" + order );
        String s = alipayService.jaliPay( order );
        log.info( "s:" + s );
        return new R( ResponseEnum.SUCCESS, s );
    }

    @GetMapping( "/ali" )
    public R docPay( ) throws AlipayApiException {
        AlipayTradePagePayResponse alipayTradePagePayResponse = alipayService.docPay( );
        return new R( ResponseEnum.SUCCESS, alipayTradePagePayResponse );
    }

    /**
     * 支付宝扫码支付
     *
     * @param alipayDTO 支付宝参数
     * @return R
     *
     * @throws AlipayApiException 支付宝异常
     */
    @PostMapping( "/last" )
    public R perfectVersion( AlipayDTO alipayDTO ) throws AlipayApiException {
        log.info( "alipayDTO:" + alipayDTO );
        AlipayTradePagePayResponse alipayTradePagePayResponse = alipayService.computerScanCode( alipayDTO );
        log.info( "alipayTradePagePayResponse:" + alipayTradePagePayResponse );
        return new R( ResponseEnum.SUCCESS, alipayTradePagePayResponse );
    }


}
