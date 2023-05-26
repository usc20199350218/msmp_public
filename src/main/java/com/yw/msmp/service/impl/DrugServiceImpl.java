package com.yw.msmp.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.entity.DrugEntity;
import com.yw.msmp.mapper.DrugMapper;
import com.yw.msmp.service.DrugService;
import com.yw.msmp.vo.DrugVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Log4j2
@Service
public class DrugServiceImpl extends ServiceImpl< DrugMapper, DrugEntity >
        implements DrugService {

    @Resource
    private DrugMapper drugMapper;

    @Override
    public IPage< DrugVo > findPage( int current, int size ) {
        Page< DrugVo > drugVoPage = new Page<>( current, size );
        IPage< DrugVo > page = drugMapper.selectDrugVoPage( drugVoPage );
        log.info( "drug分页查询:" + page.getRecords( ) );
        return page;
    }

    @Override
    public boolean myUpdate( Integer status, Integer drugId ) {
        int i = drugMapper.updateById( DrugEntity.builder( )
                                                 .drugId( drugId )
                                                 .drugStatus( status )
                                                 .build( ) );
        if ( i != 1 ) {
            throw new AppException( ResponseEnum.UPDATE_ERROR );
        }
        return true;
    }

    //    @Override
    //    public IPage< DrugDetailsDTO > findDetailsPage( int current, int size ) {
    //        Page< DrugDetailsDTO > drugDetailsDTOPage = new Page<>( current, size );
    //        IPage< DrugDetailsDTO > page = drugMapper.selectMyDetailsPage( drugDetailsDTOPage );
    //        log.info( "drugDetails分页查询:" + page.getRecords( ) );
    //        return page;
    //    }
}
