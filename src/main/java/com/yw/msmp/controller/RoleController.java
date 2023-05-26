package com.yw.msmp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yw.msmp.common.aop.anno.CheckLogin;
import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.dto.RoleDTO;
import com.yw.msmp.entity.RoleEntity;
import com.yw.msmp.service.RoleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author yanhaoyuwen
 */
@RestController
@RequestMapping( "/admin/role" )
@CrossOrigin( "*" )
@Log4j2
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping
    @CheckLogin
    public R selectAllRole( ) {
        log.info( "进入查询role" );
        List< RoleEntity > roleList = roleService.list( null );
        log.info( "查询到的role为：" + roleList );
        return new R( ResponseEnum.SUCCESS, roleList );
    }

    @PostMapping( "/addjson" )
    @CheckLogin
    public R addRole( @RequestBody String objects ) {
        System.out.println( objects );
        JSONObject jsonObject = JSONObject.parseObject( objects );
        String roleName = jsonObject.getString( "roleName" );
        Object rightIds = jsonObject.get( "rightIds" );
        List< Integer > list = JSON.parseObject( JSON.toJSONString( rightIds ), List.class );
        int i = roleService.addRole( RoleEntity.builder( ).roleName( roleName ).build( ), list );
        return new R( ResponseEnum.SUCCESS, i );
    }

    @PostMapping( "/update" )
    public R updateRole( RoleDTO roleDTO ) {
        log.info( "进入传入的role:" + roleDTO );
        boolean b = roleService.updateRole( roleDTO );
        log.info( "修改完成的结果为：" + b );
        return new R( ResponseEnum.SUCCESS, b );
    }

}

