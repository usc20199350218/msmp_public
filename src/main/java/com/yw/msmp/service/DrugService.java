package com.yw.msmp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.msmp.entity.DrugEntity;
import com.yw.msmp.vo.DrugVo;

public interface DrugService extends IService< DrugEntity > {


    IPage< DrugVo > findPage( int current, int size );

    boolean myUpdate( Integer status, Integer drugId );

    //    IPage< DrugDetailsDTO> findDetailsPage( int current, int size );
}
