package com.yw.msmp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.msmp.entity.ServiceEntity;
import com.yw.msmp.vo.ServiceVO;

import java.util.List;

public interface ServiceService extends IService< ServiceEntity > {

    ServiceVO getLastVo( Integer userId );

    List< ServiceEntity > myList( Integer userId );

    IPage< ServiceEntity > getMyPage( Integer userId, Integer current, Integer size );

    ServiceVO getVoById( Integer serviceId );

    boolean addService( ServiceVO serviceVO );

    boolean updService( ServiceVO serviceVO );

    void deleteService( Integer serviceId );

}
