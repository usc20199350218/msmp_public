package com.yw.msmp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.msmp.dto.RoleDTO;
import com.yw.msmp.entity.RoleEntity;

import java.util.List;

public interface RoleService extends IService< RoleEntity > {

    int addRole( RoleEntity build, List< Integer > list );

    /**
     * 更新角色
     *
     * @param roleDTO 角色信息
     * @return 返回修改的数量
     */
    boolean updateRole( RoleDTO roleDTO );

}




