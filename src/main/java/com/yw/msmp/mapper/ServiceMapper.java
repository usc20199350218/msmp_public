package com.yw.msmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yw.msmp.entity.ServiceEntity;
import com.yw.msmp.vo.ServiceVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author yanhaoyuwen
 */
@Repository
public interface ServiceMapper extends BaseMapper< ServiceEntity > {

    ServiceVO selectLastService( @Param( "userId" ) Integer userId );

    ServiceVO searchService( @Param( "serviceId" ) Integer serviceId );

}
