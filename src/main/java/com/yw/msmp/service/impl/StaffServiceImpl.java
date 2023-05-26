package com.yw.msmp.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.dto.StaffDTO;
import com.yw.msmp.entity.StaffEntity;
import com.yw.msmp.mapper.StaffMapper;
import com.yw.msmp.service.StaffService;
import com.yw.msmp.vo.StaffVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Log4j2
@Service
public class StaffServiceImpl extends ServiceImpl< StaffMapper, StaffEntity > implements StaffService {

    @Resource
    private StaffMapper staffMapper;

    @Override
    public IPage< StaffDTO > selectPage( Integer storeId, Integer positionId, Integer current, Integer size ) {
        Page< StaffDTO > staffDTOPage = new Page<>( current, size );
        IPage< StaffDTO > page = staffMapper.selectMyPage( storeId, positionId, staffDTOPage );
        return page;
    }

    @Override
    public void add( StaffEntity staffEntity ) {
        // 添加职员
        int insert = staffMapper.insert( staffEntity );
        if ( insert != 1 ) {
            throw new AppException( ResponseEnum.ADD_ERROR );
        }
    }

    @Override
    public void upd( StaffEntity staff ) {
        int i = staffMapper.updateById( staff );
        if ( i != 1 ) {
            throw new AppException( ResponseEnum.UPDATE_ERROR );
        }
    }

    @Override
    public void del( Integer staffId ) {
        int i = staffMapper.deleteById( staffId );
        if ( i != 1 ) {
            throw new AppException( ResponseEnum.DEL_ERROR );
        }
    }

    /**
     * @param storeId
     * @param positionName
     * @return
     */
    @Override
    public List< StaffVO > getStaffManageByStoreId( Integer storeId, String positionName ) {
        List< StaffVO > list = staffMapper.getStaff( storeId, positionName );
        list.forEach( System.out::println );
        return list;
    }

}
