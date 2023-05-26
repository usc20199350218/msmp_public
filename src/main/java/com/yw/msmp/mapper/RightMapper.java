package com.yw.msmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yw.msmp.dto.MenuDTO;
import com.yw.msmp.entity.RightEntity;
import com.yw.msmp.vo.RightVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RightMapper extends BaseMapper< RightEntity > {

    List< MenuDTO > selectMenuByRoleIdAndParentId( @Param( "roleId" ) Integer roleId,
                                                   @Param( "rightParentId" ) Integer rightParentId );

    List< RightEntity > selectRoleList( @Param( "rightType" ) Integer rightType, @Param( "roleId" ) Integer roleId );

    RightEntity selectByRightText( String rightText );

    int changeState( @Param( "rightId" ) Integer rightId );


    List< RightVO > selectVOList( );

}
