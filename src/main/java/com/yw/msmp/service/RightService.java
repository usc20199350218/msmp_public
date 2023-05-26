package com.yw.msmp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.msmp.dto.MenuDTO;
import com.yw.msmp.dto.RightDTO;
import com.yw.msmp.entity.RightEntity;
import com.yw.msmp.vo.RightVO;

import java.util.List;

public interface RightService extends IService< RightEntity > {

    List< String > getRightCodesByRoleId( Integer roleId );

    List< MenuDTO > selectMenuByRoleId( Integer roleId );

    List< RightEntity > findAllRight( );

    List< RightEntity > findAllParentRight( );

    int addRight( RightEntity rightEntity );

    List< RightDTO > findTreeRight( );

    List< RightDTO > selectMenuByRoleIdPlus( Integer roleId );

    /**
     * 删除菜单
     *
     * @param rightId 菜单ID
     * @return 是否成功
     */
    boolean delById( Integer rightId );


    List< RightVO > getAllRight( );

    /**
     * 新增
     *
     * @param rightVO 新增的值
     * @return 是否成功
     */
    boolean saveVO( RightVO rightVO );

    /**
     * 更新
     *
     * @param rightVO 更新的值
     * @return 是否成功
     */
    boolean updateVOById( RightVO rightVO );

}
