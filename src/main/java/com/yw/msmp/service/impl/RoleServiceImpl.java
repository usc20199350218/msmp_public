package com.yw.msmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.dto.RoleDTO;
import com.yw.msmp.entity.RoleEntity;
import com.yw.msmp.entity.RoleRightEntity;
import com.yw.msmp.mapper.RoleMapper;
import com.yw.msmp.mapper.RoleRightMapper;
import com.yw.msmp.service.RoleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Log4j2
@Service
public class RoleServiceImpl extends ServiceImpl< RoleMapper, RoleEntity > implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleRightMapper roleRightMapper;

    @Override
    public int addRole( RoleEntity role, List< Integer > list ) {
        // 添加角色
        int insertSelective = roleMapper.insert( RoleEntity.builder( )
                                                           .roleName( role.getRoleName( ) )
                                                           .build( ) );
        // 判断是否成功
        if ( insertSelective != 1 ) {
            throw new AppException( ResponseEnum.UPDATE_ERROR );
        }
        // 赋权
        // 获取之前添加的用户Id
        QueryWrapper< RoleEntity > roleQueryWrapper = new QueryWrapper<>( );
        Integer roleId =
                roleMapper.selectOne( roleQueryWrapper.eq( "role_name", role.getRoleName( ) ) )
                          .getRoleId( );

        int i = 1;
        for ( Integer integer : list ) {
            int insertSelective1 =
                    roleRightMapper.insert( RoleRightEntity.builder( )
                                                           .roleId( roleId )
                                                           .rightId( integer )
                                                           .build( ) );
            if ( insertSelective1 != 1 ) {
                throw new AppException( ResponseEnum.UPDATE_ERROR );
            }
        }
        return i;
    }

    /**
     * 更新角色
     *
     * @param roleDTO 角色信息
     * @return 返回修改的数量
     */
    @Override
    public boolean updateRole( RoleDTO roleDTO ) {
        // 更新角色，还要更新role——right
        log.info( "进入传入的role:" + roleDTO );
        // 删除旧role——right数据
        QueryWrapper< RoleRightEntity > roleRightQueryWrapper = new QueryWrapper<>( );
        roleRightQueryWrapper.eq( "role_id", roleDTO.getRoleId( ) );
        roleRightMapper.delete( roleRightQueryWrapper );
        // 更新role
        roleMapper.updateById( RoleEntity.builder( ).roleId( roleDTO.getRoleId( ) ).roleName( roleDTO.getRoleName( ) )
                                         .build( ) );
        // 插入role—right
        List< Integer > rightIds = roleDTO.getRightIds( );
        if ( rightIds != null ) {
            for ( Integer rightId : rightIds ) {
                roleRightMapper.insert( RoleRightEntity.builder( ).roleId( roleDTO.getRoleId( ) ).rightId( rightId )
                                                       .build( ) );
            }
        }
        return true;
    }

}
