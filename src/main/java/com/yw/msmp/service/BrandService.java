package com.yw.msmp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.msmp.dto.BrandDTO;
import com.yw.msmp.entity.BrandEntity;

public interface BrandService extends IService< BrandEntity > {

    IPage< BrandDTO > findPage( int current, int size );

    boolean myUpdate( Integer status, Integer brandId );

}
