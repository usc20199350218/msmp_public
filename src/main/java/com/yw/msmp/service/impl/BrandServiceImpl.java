package com.yw.msmp.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yw.msmp.dto.BrandDTO;
import com.yw.msmp.entity.BrandEntity;
import com.yw.msmp.mapper.BrandMapper;
import com.yw.msmp.service.BrandService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Log4j2
@Service
public class BrandServiceImpl extends ServiceImpl< BrandMapper, BrandEntity >
        implements BrandService {

    @Resource
    private BrandMapper brandMapper;

    @Override
    public IPage< BrandDTO > findPage( int current, int size ) {
        Page< BrandDTO > brandDTOPage = new Page<>( current, size );
        IPage< BrandDTO > page = brandMapper.selectMyPage( brandDTOPage );
        log.info( "drug分页查询:" + page.getRecords( ) );
        return page;
    }

    @Override
    public boolean myUpdate( Integer status, Integer brandId ) {

        return false;
    }

}
