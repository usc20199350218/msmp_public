package com.yw.msmp.controller;

import com.yw.msmp.common.config.JWTConfig;
import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.dto.LoginDTO;
import com.yw.msmp.entity.UserBasisEntity;
import com.yw.msmp.service.UserBasisService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author guyue
 **/
@RestController
@RequestMapping( "login" )
@CrossOrigin
@Log4j2
@Api( description = "通用使用的 登录 接口类" )
public class LoginController {

    @Resource
    private JWTConfig jwtConfig;
    @Resource
    private UserBasisService userService;

    //    @PostMapping( "user" )
    //    public R userLogin( ) {
    //        log.info( "1" );
    //        return new R( );
    //    }

    @PostMapping
    public R login( String userName, String userPassword ) {
        log.info( "2" );
        log.info( "进入登录controller的userName是：" + userName );
        log.info( "userPassword是：" + userPassword );
        UserBasisEntity user = userService.login( userName, userPassword );

        LoginDTO dto = new LoginDTO( );
        BeanUtils.copyProperties( user, dto );
        // 1 把dto 生成一块令牌
        String token = jwtConfig.generateJwt( dto );
        dto.setToken( token );
        log.info( "dto>>>>" + dto );
        return new R( ResponseEnum.SUCCESS, dto );
    }

}
