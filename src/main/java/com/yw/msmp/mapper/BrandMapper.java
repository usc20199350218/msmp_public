package com.yw.msmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yw.msmp.dto.BrandDTO;
import com.yw.msmp.entity.BrandEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandMapper extends BaseMapper< BrandEntity > {

    IPage< BrandDTO > selectMyPage( Page< BrandDTO > brandDTOPage );

}
