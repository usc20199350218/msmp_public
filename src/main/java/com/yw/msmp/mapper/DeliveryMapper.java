package com.yw.msmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yw.msmp.entity.DeliveryEntity;
import com.yw.msmp.vo.DeliveryVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryMapper extends BaseMapper< DeliveryEntity > {

    IPage< DeliveryVO > selectByUserIdStatus( @Param( "userId" ) Integer userId,
                                              @Param( "deliveryStatus" ) String deliveryStatus,
                                              Page< DeliveryVO > deliveryVOPage );

    //    List< DeliveryVO > selectByUserIdStatus( Integer userId, String deliveryStatus );
}





