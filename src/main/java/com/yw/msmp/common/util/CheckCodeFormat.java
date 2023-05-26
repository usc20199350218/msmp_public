package com.yw.msmp.common.util;


import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * @作者 Forest Hoo
 * @日期 2022/9/14 10:15
 */
@Slf4j
public class CheckCodeFormat {

    public static void checkCodeFormat( String code ) {

        String regex = "\\d{4}";
        if ( !code.matches( regex ) ) {
            log.error( "验证码不 是4位数字" );
            throw new AppException( ResponseEnum.CODE_FORMAT_ERROR );
        }
    }

}
