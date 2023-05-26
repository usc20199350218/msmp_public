package com.yw.msmp.controller;

import com.yw.msmp.common.config.AlipayConfig;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Log4j2
@RestController
@RequestMapping( "/api" )
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger( PaymentController.class );

    @Resource
    private AlipayConfig alipayConfig;


}
