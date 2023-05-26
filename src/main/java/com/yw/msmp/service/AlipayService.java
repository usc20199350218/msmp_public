package com.yw.msmp.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.yw.msmp.dto.AlipayDTO;
import com.yw.msmp.dto.PayDTO;
import com.yw.msmp.vo.JOrder;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;


public interface AlipayService {

    byte[] alipay( PayDTO payDTO );

    String jaliPay( JOrder order ) throws AlipayApiException;

    AlipayTradePagePayResponse docPay( ) throws AlipayApiException;

    AlipayTradePagePayResponse computerScanCode( AlipayDTO alipayDTO ) throws AlipayApiException;

    boolean callback( HttpServletRequest request );

    boolean refund( String orderNum, BigDecimal refundAmount ) throws AlipayApiException;

}
