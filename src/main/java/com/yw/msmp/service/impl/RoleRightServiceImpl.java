package com.yw.msmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.entity.RightEntity;
import com.yw.msmp.entity.RoleRightEntity;
import com.yw.msmp.mapper.RightMapper;
import com.yw.msmp.mapper.RoleRightMapper;
import com.yw.msmp.service.RoleRightService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Log4j2
@Service
public class RoleRightServiceImpl implements RoleRightService {

    @Resource
    private RightMapper rightMapper;
    @Resource
    private RoleRightMapper roleRightMapper;

    @Override
    public int addRoleRight( List< Integer > roleIdList, String rightText ) {
        // 根据rightText查询rightId -rightText为刚插入的数据 -不是前端不传
        QueryWrapper< RightEntity > queryWrapper = new QueryWrapper<>( );
        RightEntity right = rightMapper.selectOne( queryWrapper.eq( "right_text", rightText ) );
        log.info( right );
        // 遍历roleIdList 添加
        int i = 0;
        int insertSelective;
        for ( Integer integer : roleIdList ) {
            insertSelective = roleRightMapper.insert( RoleRightEntity.builder( )
                                                                     .rightId( right.getRightId( ) )
                                                                     .roleId( integer )
                                                                     .build( ) );
            if ( insertSelective != 1 ) {
                throw new AppException( ResponseEnum.UPDATE_ERROR );
            }
            i++;
        }
        return i;
    }


}
