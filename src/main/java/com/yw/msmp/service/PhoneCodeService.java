package com.yw.msmp.service;

import com.yw.msmp.entity.PhoneCodeEntity;

/*
 * @PACKAGE_NAME com.yw.msmp.service
 * @author  yanhaoyuwen
 * @version  1.0
 */public interface PhoneCodeService {

    void saveCodeAndPhone( String code, String phoneNum );

    PhoneCodeEntity findCodeByPhoneAndCode( String code, String phoneNum );

}
