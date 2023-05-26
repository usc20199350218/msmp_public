package com.yw.msmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yw.msmp.dto.StaffDTO;
import com.yw.msmp.entity.StaffEntity;
import com.yw.msmp.vo.StaffVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffMapper extends BaseMapper< StaffEntity > {

    IPage< StaffDTO > selectMyPage( Integer storeId, Integer positionId, Page< StaffDTO > staffDTOPage );

    List< StaffVO > getStaff( Integer storeId, String positionName );

}
