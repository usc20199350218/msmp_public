package com.yw.msmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yw.msmp.entity.PhoneCodeEntity;
import org.springframework.stereotype.Repository;

/**
 * @author yanhaoyuwen
 */
@Repository
public interface PhoneCodeMapper extends BaseMapper< PhoneCodeEntity > {

    void insertSelective( PhoneCodeEntity phoneCode );

}
