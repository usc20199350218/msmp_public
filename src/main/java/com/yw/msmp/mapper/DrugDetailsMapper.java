package com.yw.msmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yw.msmp.dto.DrugDetailsDTO;
import com.yw.msmp.dto.DrugSearchDTO;
import com.yw.msmp.dto.DrugVo;
import com.yw.msmp.entity.DrugDetailsEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugDetailsMapper extends BaseMapper< DrugDetailsEntity > {

    IPage< DrugDetailsDTO > selectMyPage( Page< DrugDetailsDTO > drugDetailsDTOPage );

    List< DrugVo > selectDrugVoList( Integer drugDetailsStatus );

    IPage< DrugDetailsDTO > selectOnlineDrugSearchPage( Page< DrugDetailsDTO > page,
                                                        @Param( "storeId" ) Integer storeId,
                                                        @Param( "drugSearchDTO" ) DrugSearchDTO drugSearchDTO );

}
