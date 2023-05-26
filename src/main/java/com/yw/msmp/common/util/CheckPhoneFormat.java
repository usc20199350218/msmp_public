package com.yw.msmp.common.util;

import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * @作者 Forest Hoo
 * @日期 2022/9/14 9:15
 */
@Slf4j
public class CheckPhoneFormat {

    /**
     * @param phoneNum
     */
    public static void checkPhoneFormat( String phoneNum ) {
        String regex = "[1][34578][0-9]{9}"; //手机号码的格式：第一位只能为1，第二位可以是3，4，5，7，8，第三位到第十一位可以为0-9中任意一个数字

        if ( !phoneNum.matches( regex ) ) {
            log.error( "电话号码格式不正确.." );
            throw new AppException( ResponseEnum.PHONE_FORMAT_ERROR );
        }

    }

}
