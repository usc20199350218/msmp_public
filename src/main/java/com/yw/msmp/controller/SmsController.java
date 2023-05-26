package com.yw.msmp.controller;
/*
 * @PACKAGE_NAME com.yw.msmp.controller
 * @author  yanhaoyuwen
 * @version  1.0
 */

/**
 * @author yanhaoyuwen
 **/

import com.aliyuncs.exceptions.ClientException;
import com.yw.msmp.common.config.DefaultUserConfig;
import com.yw.msmp.common.config.JWTConfig;
import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.common.util.CheckCodeFormat;
import com.yw.msmp.common.util.CheckPhoneFormat;
import com.yw.msmp.common.util.RandomCode;
import com.yw.msmp.common.util.RandomNickName;
import com.yw.msmp.dto.LoginDTO;
import com.yw.msmp.entity.UserBasisEntity;
import com.yw.msmp.service.PhoneCodeService;
import com.yw.msmp.service.SmsService;
import com.yw.msmp.service.UserBasisService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@CrossOrigin( "*" )
@RequestMapping( "/api/sms" )
@Api( description = "短信服务类" )
@Log4j2
public class SmsController {

    @Resource
    private SmsService smsService;

    @Resource
    private PhoneCodeService phoneCodeService;

    @Resource
    private UserBasisService userService;

    @Resource
    private JWTConfig jwtConfig;

    @Resource
    private DefaultUserConfig defaultUserConfig;

    /**
     * 1.前端传来手机号和验证码
     * 2.首先去验证手机号格式对不对,不对直接抛异常
     * 3.然后验证验证码格式对不对,不对直接抛异常
     * 4.查code表,phoneNum+code能不能在数据库找到,找不到就抛异常
     * 5.找到了,别急,先去user表查看电话号码在不在
     * 在的话,说明之前登录过了,直接放行
     * 不在的话,说明之前没有登陆过,需要向user表插入数据(phone+roleid+userName+随机昵称+avatarUrl+createTime+updateTime(且相等)),然后放行
     * 这个随机昵称可以写工具类,avatarUrl放在配置文件
     */
    @PostMapping( "/code/login" )
    //    public R loginByCode( @RequestBody String objects ) {
    //        //准备
    //        JSONObject jsonObject = JSONObject.parseObject( objects );
    //        String code = jsonObject.getString( "code" );
    //        String phoneNum = jsonObject.getString( "phoneNum" );
    public R loginByCode( String code, String userPhone ) {
        //准备
        //        JSONObject jsonObject = JSONObject.parseObject( objects );
        //        String code = jsonObject.getString( "code" );
        //        String phoneNum = jsonObject.getString( "phoneNum" );
        CheckCodeFormat.checkCodeFormat( code );      //拦截
        CheckPhoneFormat.checkPhoneFormat( userPhone );//拦截
        // 检查是否存在code
        phoneCodeService.findCodeByPhoneAndCode( code, userPhone );//这行有异常处理
        //如果走到这一行说明codeEntity查到了,没有抛出异常
        UserBasisEntity user = userService.findUserByPhoneNum( userPhone );
        /*
        user无论是不是null最终都要放行的,只不过在数据和不在数据库区别还是有的
         */
        if ( user == null ) {

            //生成随机昵称
            String nickNameByUUID = RandomNickName.randomNickNameByUUID( );
            /*
            build 一个user
             */
            user = UserBasisEntity.builder( )
                                  .userPhone( userPhone )
                                  .userAvatarUrl( defaultUserConfig.userAvatarUrl )
                                  .roleId( defaultUserConfig.roleId )
                                  .userName( nickNameByUUID )
                                  .userPassword( defaultUserConfig.userPassWord )
                                  .build( );
            //插入
            userService.addUser( user );

        }
            /*
            生成LoginDto
             */
        LoginDTO loginDTO = new LoginDTO( );
        loginDTO.setUserAvatarUrl( user.getUserAvatarUrl( ) );
        loginDTO.setRoleId( user.getRoleId( ) );
        loginDTO.setUserName( user.getUserName( ) );
        loginDTO.setUserId( user.getUserId( ) );
        /*
            生成token
         */
        String token = jwtConfig.generateJwt( loginDTO );
        log.info( "sms的token=" + token );
        loginDTO.setToken( token );
        //返回
        return new R( ResponseEnum.SUCCESS, loginDTO );
    }

    /**
     * 1.接收用户发到后端的手机号
     * 2.生成一个随机4位数的验证码,保存在数据库(phoneNum,code)
     * 3.调用阿里云服务
     * 4.返回成功的消息
     *
     * @param userPhone 手机号
     * @return 生成的验证码
     */
    @PostMapping( "/getcode" )
    //    public R getCode( @RequestBody String objects ) throws ClientException {
    //        //准备数据
    //        log.info( "controller的:" + objects );
    //
    //        //处理数据
    //        JSONObject jsonObject = JSONObject.parseObject( objects );
    //        String phoneNum = ( String ) jsonObject.get( "phoneNum" );
    public R getCode( String userPhone ) throws ClientException {
        //        //准备数据
        //        log.info( "controller的:" + objects );
        //
        //        //处理数据
        //        JSONObject jsonObject = JSONObject.parseObject( objects );
        //        String phoneNum = ( String ) jsonObject.get( "phoneNum" );
        /*
        验证手机号格式:
         */
        CheckPhoneFormat.checkPhoneFormat( userPhone );
        String code = RandomCode.getRandomCodeBy4( );
        /*
        手机号没有异常,调用阿里的云服务
        把手机号和验证码存入数据库:tb_code
         */
        smsService.sendCheckCode( userPhone, code );
        phoneCodeService.saveCodeAndPhone( code, userPhone );

        //输出数据
        return new R( ResponseEnum.SUCCESS, null );
    }

}
