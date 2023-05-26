package com.yw.msmp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.msmp.entity.AddressEntity;

import java.util.List;


/**
 * @author yanhaoyuwen
 */
public interface AddressService extends IService< AddressEntity > {

    List< AddressEntity > searchAddress( String userId );

    boolean addNAddress( AddressEntity address );

    /**
     * 更新地址
     * 需要注意的是默认地址是否设置了的问题
     * 如果传入的地址为默认地址，那么需要将其他地址更新为非默认地址，如何通过id更新当前地址
     * 在此之前获取原数据是否是默认地址，即默认地址这个选项是否是变更而来的，以决定是否批量设置非默认地址
     *
     * @param address 地址实体
     * @return boolean
     */
    boolean updNById( AddressEntity address );

}
