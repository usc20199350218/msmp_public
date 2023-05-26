package com.yw.msmp.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@CrossOrigin( "*" )
@Controller
@RequestMapping( "/api/alipay" )
public class PayController {

    @GetMapping( "/page/pay" )
    public String index( ) {
        return "index";
    }

    @GetMapping( "/page/main" )
    public String main( ) {
        return "main";
    }

}


