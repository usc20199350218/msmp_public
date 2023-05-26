package com.yw.msmp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.msmp.dto.DrugDetailsDTO;
import com.yw.msmp.dto.DrugVo;
import com.yw.msmp.entity.DrugDetailsEntity;

import java.util.List;

public interface DrugDetailsService extends IService< DrugDetailsEntity > {

    IPage< DrugDetailsDTO > findPage( int current, int size );

    boolean addDrugDetails( DrugDetailsEntity drugDetailsEntity );

    List< DrugVo > selectList( Integer drugDetailsStatus );

}
