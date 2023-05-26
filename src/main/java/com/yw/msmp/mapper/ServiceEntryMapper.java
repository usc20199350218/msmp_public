package com.yw.msmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yw.msmp.entity.ServiceEntryEntity;
import com.yw.msmp.vo.ServiceDetailVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yanhaoyuwen
 */
@Repository
public interface ServiceEntryMapper extends BaseMapper< ServiceEntryEntity > {

    IPage< ServiceEntryEntity > selectMyPage( @Param( "searchMethod" ) String searchMethod,
                                              @Param( "searchText" ) String searchText,
                                              Page< ServiceEntryEntity > serviceEntryPage );

    List< ServiceDetailVO > selectBlankList( );

}
