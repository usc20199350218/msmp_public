package com.yw.msmp.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.entity.DeliveryEntity;
import com.yw.msmp.entity.UserBasisEntity;
import com.yw.msmp.enums.DeliveryStatusEnum;
import com.yw.msmp.mapper.DeliveryMapper;
import com.yw.msmp.mapper.UserBasisMapper;
import com.yw.msmp.service.DeliveryService;
import com.yw.msmp.vo.DeliveryVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Log4j2
@Service
public class DeliveryServiceImpl extends ServiceImpl< DeliveryMapper, DeliveryEntity > implements DeliveryService {

    @Resource
    private DeliveryMapper deliveryMapper;

    @Resource
    private UserBasisMapper userMapper;

    @Value( "${position.positionId.delivery}" )
    private Integer deliveryStaffId;

    /**
     * 获取配送列表根据状态与userId
     *
     * @param userId
     * @param status
     * @param current
     * @param size
     * @return
     */
    @Override
    public IPage< DeliveryVO > selectStatusList( Integer userId, String status, Integer current, Integer size ) {
        Page< DeliveryVO > deliveryVOPage = new Page<>( current, size );
        // status转换
        String deliveryStatus = DeliveryStatusEnum.fromString( status )
                                                  .toString( );
        boolean isTrue = false;
        // 判断配送角色id是否存在
        if ( deliveryStaffId != null ) {
            // 判断角色是否为配送人员
            UserBasisEntity user = userMapper.selectById( userId );
            if ( user.getRoleId( )
                     .equals( deliveryStaffId ) ) {
                isTrue = true;
            }
        }

        if ( !DeliveryStatusEnum.CREATED.toString( )
                                        .equals( deliveryStatus ) && isTrue ) {
            // 只能查看自己已经分配的
            return deliveryMapper.selectByUserIdStatus( userId, deliveryStatus, deliveryVOPage );
        }
        else {
            // 都可查看
            return deliveryMapper.selectByUserIdStatus( null, deliveryStatus, deliveryVOPage );
        }
    }

    @Override
    public void changeStatus( Integer deliveryId, String deliveryStatus, Integer userId ) {
        boolean isTrue = false;
        // 判断配送角色id是否存在
        if ( deliveryStaffId != null ) {
            // 判断角色是否为配送人员
            UserBasisEntity user = userMapper.selectById( userId );
            if ( user.getRoleId( )
                     .equals( deliveryStaffId ) ) {
                isTrue = true;
            }
        }

        // 不是，thr
        if ( !isTrue ) {
            throw new AppException( ResponseEnum.NO_PERMISSIONS );
        }

        // 获取
        DeliveryEntity delivery = deliveryMapper.selectById( deliveryId );

        // 判断是否为退回
        if ( DeliveryStatusEnum.CREATED.toString( )
                                       .equals( deliveryStatus ) ) {
            // 是，清理user_id
            delivery.setUserId( null );
        }
        else if ( DeliveryStatusEnum.PICKUP.toString( )
                                           .equals( deliveryStatus ) ) {
            // 取货的话，设置用户Id
            delivery.setUserId( userId );
        }
        delivery.setDeliveryStatus( deliveryStatus );
        // 更新状态
        deliveryMapper.updateById( delivery );
    }

}
