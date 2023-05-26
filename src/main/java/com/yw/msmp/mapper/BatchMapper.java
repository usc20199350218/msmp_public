package com.yw.msmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yw.msmp.dto.BatchDTO;
import com.yw.msmp.entity.BatchEntity;
import com.yw.msmp.entity.SearchDrugDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yanhaoyuwen
 */
@Repository
public interface BatchMapper extends BaseMapper< BatchEntity > {

    /**
     * @param batchDTOPage 分页数据
     * @return 分页后批次数据
     */
    IPage< BatchDTO > selectMyPage( Page< BatchDTO > batchDTOPage );

    /**
     * 搜索page
     *
     * @param searchDrugDTO 搜索条件
     * @param batchDTOPage  分页数据
     * @return page
     */
    IPage< BatchDTO > searchPage( @Param( "searchDrugDTO" ) SearchDrugDTO searchDrugDTO,
                                  Page< BatchDTO > batchDTOPage );

    List< BatchEntity > getExpired( );

}
