package com.yw.msmp.controller;

import com.alibaba.fastjson.JSONObject;
import com.yw.msmp.common.aop.anno.CheckLogin;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.dto.MenuDTO;
import com.yw.msmp.dto.RightDTO;
import com.yw.msmp.entity.RightEntity;
import com.yw.msmp.entity.RoleEntity;
import com.yw.msmp.service.RightService;
import com.yw.msmp.service.RoleRightService;
import com.yw.msmp.service.RoleService;
import com.yw.msmp.vo.RightVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yanhaoyuwen
 */
@RestController
@RequestMapping( "/admin/right" )
@CrossOrigin
@Transactional( rollbackFor = Exception.class )
@Log4j2
@Api( description = "right 接口类" )
public class RightController {

    @Resource
    private RightService rightService;
    @Resource
    private RoleService roleService;

    @Resource
    private RoleRightService roleRightService;

    /**
     * 根据角色ID查询角色拥有的菜单
     */
    @GetMapping( "/menus" )
    @CheckLogin
    public R mens( HttpServletRequest request ) {
        Integer roleId = Integer.valueOf( request.getAttribute( "roleId" )
                                                 .toString( ) );
        List< MenuDTO > menuDTOS = rightService.selectMenuByRoleId( roleId );
        return new R( ResponseEnum.SUCCESS, menuDTOS );
    }

    /**
     * 获取全部菜单权限
     *
     * @return 全部菜单权限 List<RightVO>
     */
    @GetMapping
    @CheckLogin
    @ApiOperation( "获取全部菜单权限" )
    public R findAllRight( ) {
        List< RightVO > list = rightService.getAllRight( );
        return new R( ResponseEnum.SUCCESS, list );
    }

    /**
     * 获取父菜单列表
     * @return 父菜单列表
     */
    @GetMapping( "/parent" )
    @CheckLogin
    @ApiOperation( "获取父菜单列表" )
    public R findAllParentRight( ) {
        List< RightEntity > allParentRight = rightService.findAllParentRight( );
        return new R( ResponseEnum.SUCCESS, allParentRight );
    }

    @PutMapping
    @Transactional( rollbackFor = Exception.class )
    public R changeRight( @RequestBody RightEntity right ) {
        boolean b = rightService.updateById( right );
        if ( !b ) {
            throw new AppException( ResponseEnum.UPDATE_ERROR );
        }
        return new R( ResponseEnum.SUCCESS, right.getRightId( ) );
    }

    @CheckLogin
    @DeleteMapping( "{rightId}" )
    @ApiOperation( "删除指定菜单" )
    @Transactional( rollbackFor = Exception.class )
    public R delRightByRightId( @PathVariable( "rightId" ) Integer rightId ) {
        boolean b = rightService.delById( rightId );
        return new R( ResponseEnum.SUCCESS, b );
    }

    @PostMapping( "/addjson" )
    @CheckLogin
    @Transactional( rollbackFor = Exception.class )
    public R addRightjson( @RequestBody String objects ) {
        JSONObject jsonObject = JSONObject.parseObject( objects );
        // 获取角色ID
        List< Integer > roleIdList = ( List< Integer > ) jsonObject.get( "roleId" );
        log.warn( "roleIdList:" + roleIdList );
        // 获取right名称
        String rightText = jsonObject.getString( "rightText" );
        log.warn( "rightText:" + rightText );
        Integer rightType = Integer.valueOf( jsonObject.getString( "rightType" ) );
        String rightUrl = jsonObject.getString( "rightUrl" );
        Integer rightParentId = Integer.valueOf( jsonObject.getString( "rightParentId" ) );
        Boolean rightMenu = Boolean.valueOf( jsonObject.getString( "rightMenu" ) );
        // 添加right
        int i = rightService.addRight(
                RightEntity.builder( )
                           .rightText( rightText )
                           .rightType( rightType )
                           .rightUrl( rightUrl )
                           .rightParentId( rightParentId )
                           .rightMenu( rightMenu )
                           .build( ) );
        // 依据角色数组赋权
        int i2 = roleRightService.addRoleRight( roleIdList, rightText );
        if ( i2 != roleIdList.size( ) ) {
            throw new AppException( ResponseEnum.UPDATE_ERROR );
        }
        return new R( ResponseEnum.SUCCESS, i );
    }

    @PostMapping( "/edit" )
    @Transactional( rollbackFor = Exception.class )
    @CheckLogin
    public R editRight( @RequestBody String objects ) {
        log.warn( "前端传来的要修改的right为：" + objects );
        JSONObject jsonObject = JSONObject.parseObject( objects );
        Integer rightId = Integer.valueOf( jsonObject.getString( "rightId" ) );
        String rightText = jsonObject.getString( "rightText" );
        log.warn( "rightText:" + rightText );
        Integer rightType = Integer.valueOf( jsonObject.getString( "rightType" ) );
        String rightUrl = jsonObject.getString( "rightUrl" );
        Integer rightParentId = Integer.valueOf( jsonObject.getString( "rightParentId" ) );
        Boolean rightMenu = Boolean.valueOf( jsonObject.getString( "rightMenu" ) );
        // 防止父权限自定义设置url、ParentId
        if ( rightType == 0 ) {
            log.info( "开始重置部分属性" );
            rightUrl = "";
            rightParentId = 0;
        }
        boolean i = rightService.updateById(
                RightEntity.builder( )
                           .rightText( rightText )
                           .rightType( rightType )
                           .rightUrl( rightUrl )
                           .rightParentId( rightParentId )
                           .rightMenu( rightMenu )
                           .rightId( rightId )
                           .build( ) );
        if ( !i ) {
            log.warn( "权限更新失败" );
            throw new AppException( ResponseEnum.UPDATE_ERROR );
        }
        return new R( ResponseEnum.SUCCESS, i );
    }

    /**
     * 以树形结构返回
     *
     * @return
     */
    @GetMapping( "/tree" )
    @CheckLogin
    public R findTreeRight( ) {
        List< RightDTO > treeRight = rightService.findTreeRight( );
        return new R( ResponseEnum.SUCCESS, treeRight );
    }

    @PostMapping( "/selecttreeId" )
    @CheckLogin
    public R findTreeRightByRoleId( RoleEntity role ) {
        log.warn( "role:" + role );
        Integer roleId = role.getRoleId( );
        log.warn( "roleId:" + roleId );
        List< MenuDTO > menuDTOList = rightService.selectMenuByRoleId( roleId );
        return new R( ResponseEnum.SUCCESS, menuDTOList );
    }

    @PostMapping( "/selectroletree" )
    @CheckLogin
    public R findTreeRightByRoleIdPlus( RoleEntity role ) {
        log.warn( "role:" + role );
        Integer roleId = role.getRoleId( );
        log.warn( "roleId:" + roleId );
        List< RightDTO > treeRight = rightService.selectMenuByRoleIdPlus( roleId );
        return new R( ResponseEnum.SUCCESS, treeRight );
    }

    /**
     * 最新修改版本新增更新通用
     *
     * @param rightVO 新增或者更新的版本
     * @return 新增或者更新的状态
     */
    @PostMapping( "/save" )
    @Transactional( rollbackFor = Exception.class )
    public R save( RightVO rightVO ) {
        boolean save = false;
        log.info( "rightVO:" + rightVO );
        if ( rightVO == null || rightVO.getRightId( ) == null ) {
            // 新增
            save = rightService.saveVO( rightVO );
        }
        else {
            // 更新
            save = rightService.updateVOById( rightVO );
        }
        return new R( ResponseEnum.SUCCESS, save );
    }


    /**
     * 以树形结构返回
     *
     * @return
     */
    @PostMapping( "/selecttree" )
    @CheckLogin
    public R findTreeOldRight( ) {
        List< RightDTO > treeRight = rightService.findTreeRight( );
        return new R( ResponseEnum.SUCCESS, treeRight );
    }

}
