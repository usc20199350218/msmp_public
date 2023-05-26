package com.yw.msmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.dto.DrugDetailsDTO;
import com.yw.msmp.dto.DrugVo;
import com.yw.msmp.entity.DrugDetailsEntity;
import com.yw.msmp.mapper.DrugDetailsMapper;
import com.yw.msmp.service.DrugDetailsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Log4j2
@Service
public class DrugDetailsServiceImpl extends ServiceImpl< DrugDetailsMapper, DrugDetailsEntity >
        implements DrugDetailsService {

    @Resource
    private DrugDetailsMapper drugDetailsMapper;


    @Override
    public IPage< DrugDetailsDTO > findPage( int current, int size ) {
        Page< DrugDetailsDTO > drugDetailsDTOPage = new Page<>( current, size );
        IPage< DrugDetailsDTO > page = drugDetailsMapper.selectMyPage( drugDetailsDTOPage );
        log.info( "drug分页查询:" + page.getRecords( ) );
        return page;
    }

    @Override
    public boolean addDrugDetails( DrugDetailsEntity drugDetailsEntity ) {
        // 需要代码去重
        QueryWrapper< DrugDetailsEntity > drugDetailsEntityQueryWrapper = new QueryWrapper<>( );
        drugDetailsEntityQueryWrapper
                .eq( "drug_id", drugDetailsEntity.getDrugId( ) )
                .eq( "brand_id", drugDetailsEntity.getBrandId( ) )
                .eq( "drug_specification", drugDetailsEntity.getDrugSpecification( ) );
        // 检查药品id、匹配id、规格是否相同
        Long aLong = drugDetailsMapper.selectCount( drugDetailsEntityQueryWrapper );
        if ( aLong != 0 ) {
            throw new AppException( ResponseEnum.ADD_ERROR );
        }

        int insert = drugDetailsMapper.insert( drugDetailsEntity );
        if ( insert != 1 ) {
            throw new AppException( ResponseEnum.ADD_ERROR );
        }
        return true;
    }

    @Override
    public List< DrugVo > selectList( Integer drugDetailsStatus ) {
        List< DrugVo > list = drugDetailsMapper.selectDrugVoList( drugDetailsStatus );
        return list;
    }

}
