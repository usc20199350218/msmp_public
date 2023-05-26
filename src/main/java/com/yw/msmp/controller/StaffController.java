package com.yw.msmp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.dto.StaffDTO;
import com.yw.msmp.entity.StaffEntity;
import com.yw.msmp.service.StaffService;
import com.yw.msmp.vo.StaffVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping( "/admin/staff" )
@CrossOrigin( "*" )
@Log4j2
public class StaffController {

    @Resource
    private StaffService staffService;

    @GetMapping
    public R getList( Integer storeId, Integer positionId, Integer current, Integer size ) {
        log.warn( "分页数据为：int current:" + current + ",int pageSize:" + size );
        log.info( "storeId:" + storeId );
        log.info( "positionId:" + positionId );
        IPage< StaffDTO > staffDTOIPage = staffService.selectPage( storeId, positionId, current, size );
        return new R( ResponseEnum.SUCCESS, staffDTOIPage );
    }

    @PostMapping
    public R addStaff( StaffEntity staff ) {
        log.info( "即将添加的职员为:" + staff );
        staffService.add( staff );
        return new R( ResponseEnum.SUCCESS, null );
    }

    @PutMapping
    public R updStaff( StaffEntity staff ) {
        log.info( "即将更新的职员为:" + staff );
        staffService.upd( staff );
        return new R( ResponseEnum.SUCCESS, null );
    }

    @DeleteMapping( "{staffId}" )
    public R delStaff( @PathVariable( "staffId" ) Integer staffId ) {
        log.info( "即将删除的职员ID为:" + staffId );
        staffService.del( staffId );
        return new R( ResponseEnum.SUCCESS, null );
    }

    @GetMapping( "/{positionName}/{storeId}" )
    public R getStaffByStoreId( @PathVariable( "positionName" ) String positionName,
                                @PathVariable( "storeId" ) Integer storeId ) {
        log.info( "即将查询的店铺id为:" + storeId + "的" + positionName );
        List< StaffVO > list = staffService.getStaffManageByStoreId( storeId, positionName );
        return new R( ResponseEnum.SUCCESS, list );
    }

}
