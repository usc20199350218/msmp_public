package com.yw.msmp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.msmp.entity.DeliveryEntity;
import com.yw.msmp.vo.DeliveryVO;

public interface DeliveryService extends IService< DeliveryEntity > {

    IPage< DeliveryVO > selectStatusList( Integer userId, String status, Integer current, Integer size );

    void changeStatus( Integer deliveryId, String deliveryStatus, Integer userId );

}
