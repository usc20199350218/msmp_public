package com.yw.msmp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.msmp.dto.StaffDTO;
import com.yw.msmp.entity.StaffEntity;
import com.yw.msmp.vo.StaffVO;

import java.util.List;

public interface StaffService extends IService< StaffEntity > {

    IPage< StaffDTO > selectPage( Integer storeId, Integer positionId, Integer current, Integer size );

    void add( StaffEntity staffEntity );

    void upd( StaffEntity staff );

    void del( Integer staffId );

    List< StaffVO > getStaffManageByStoreId( Integer storeId, String positionName );

}
