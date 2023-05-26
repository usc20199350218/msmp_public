package com.yw.msmp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.entity.PositionEntity;
import com.yw.msmp.mapper.PositionMapper;
import com.yw.msmp.service.PositionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Log4j2
@Service
public class PositionServiceImpl extends ServiceImpl< PositionMapper, PositionEntity > implements PositionService {

    @Resource
    private PositionMapper positionMapper;

    @Override
    public void add( PositionEntity positionEntity ) {
        int insert = positionMapper.insert( positionEntity );
        if ( insert != 1 ) {
            throw new AppException( ResponseEnum.ADD_ERROR );
        }
    }

    @Override
    public void upd( PositionEntity positionEntity ) {
        int i = positionMapper.updateById( positionEntity );
        if ( i != 1 ) {
            throw new AppException( ResponseEnum.UPDATE_ERROR );
        }
    }

    @Override
    public void del( Integer positionId ) {
        int i = positionMapper.deleteById( positionId );
        if ( i != 1 ) {
            throw new AppException( ResponseEnum.DEL_ERROR );
        }
    }

}
