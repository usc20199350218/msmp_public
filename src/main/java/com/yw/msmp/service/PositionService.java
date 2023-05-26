package com.yw.msmp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.msmp.entity.PositionEntity;

public interface PositionService extends IService< PositionEntity > {

    void add( PositionEntity positionEntity );

    void upd( PositionEntity positionEntity );

    void del( Integer positionId );

}
