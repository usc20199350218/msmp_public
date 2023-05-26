package com.yw.msmp.service.impl;
/*
 * @PACKAGE_NAME com.yw.msmp.service.impl
 * @author  yanhaoyuwen
 * @version  1.0
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.entity.PhoneCodeEntity;
import com.yw.msmp.mapper.PhoneCodeMapper;
import com.yw.msmp.service.PhoneCodeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author yanhaoyuwen
 **/
@Service
@Log4j2
public class PhoneCodeServiceImpl extends ServiceImpl< PhoneCodeMapper, PhoneCodeEntity > implements PhoneCodeService {

    @Resource
    private PhoneCodeMapper phoneCodeMapper;

    /**
     * @param code     验证码
     * @param phoneNum 手机号
     */
    @Override
    public void saveCodeAndPhone( String code, String phoneNum ) {
        //准备
        PhoneCodeEntity phoneCode = PhoneCodeEntity.builder( ).code( code ).phoneNum( phoneNum )
                                                   .createTime( new Date( ) ).build( );
        //处理
        int insert = phoneCodeMapper.insert( phoneCode );
        if ( insert != 1 ) {
            throw new AppException( ResponseEnum.ADD_ERROR );
        }
    }

    /**
     * @param code
     * @param phoneNum
     * @return
     */
    @Override
    public PhoneCodeEntity findCodeByPhoneAndCode( String code, String phoneNum ) {

        QueryWrapper< PhoneCodeEntity > wrapper = new QueryWrapper<>( );
        wrapper.eq( "code", code ).eq( "phone_num", phoneNum );
        List< PhoneCodeEntity > codes = phoneCodeMapper.selectList( wrapper );


        if ( codes.size( ) == 0 || codes == null ) {
            //没查到
            throw new AppException( ResponseEnum.CODE_INVALID );//抛出异常
        }
        PhoneCodeEntity codeEntity = codes.get( 0 );
        //返回
        return codeEntity;
    }

}
