package com.yw.msmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yw.msmp.entity.DrugEntity;
import com.yw.msmp.vo.DrugVo;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugMapper extends BaseMapper< DrugEntity > {

    IPage< DrugEntity > selectMyPage( Page< DrugEntity > drugEntityPage );

    IPage< DrugVo > selectDrugVoPage( Page< DrugVo > drugVoPage );


    //    IPage< DrugDetailsDTO> selectMyDetailsPage( Page< DrugDetailsDTO> drugDetailsDTOPage );
}
