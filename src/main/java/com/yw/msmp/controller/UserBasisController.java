package com.yw.msmp.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yw.msmp.common.aop.anno.CheckLogin;
import com.yw.msmp.common.config.JWTConfig;
import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.dto.SearchUserDTO;
import com.yw.msmp.dto.UserDTO;
import com.yw.msmp.entity.UserBasisEntity;
import com.yw.msmp.service.UserBasisService;
import com.yw.msmp.vo.SelectVO;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yanhaoyuwen
 */
@RestController
@RequestMapping( "/admin/user" )
@CrossOrigin( "*" )
@Log4j2
@Api( description = "通用使用的 user 接口类" )
public class UserBasisController {

    @Resource
    private JWTConfig jwtConfig;
    @Resource
    private UserBasisService userBasisService;

    /**
     * 获取所有的user信息
     *
     * @return R
     */
    @GetMapping
    public R getAll( ) {
        return new R( ResponseEnum.SUCCESS, userBasisService.list( ) );
    }

    @GetMapping( "/supplier" )
    public R getSupplierUsers( ) {
        List< UserBasisEntity > list = userBasisService.getSupplierList( );
        log.info( "查询supplier用户列表：" + list );
        return new R( ResponseEnum.SUCCESS, list );
    }

    @GetMapping( "/findpage" )
    @CheckLogin
    public R findPage( int current, int size ) {
        log.warn( "分页数据为：int current:" + current + ",int pageSize:" + size );
        IPage< UserDTO > userList = userBasisService.selectPage( current, size );
        log.info( "userList:" + userList.getRecords( ) );
        return new R( ResponseEnum.SUCCESS, userList );
    }

    @GetMapping( "/page" )
    //    @CheckLogin
    public R getPage( SearchUserDTO searchUserDTO ) {
        // 空值
        log.info( "searchUserDTO:" + searchUserDTO );
        IPage< UserDTO > page = userBasisService.searchPage( searchUserDTO );
        //        log.info( "userList:" + userList.getRecords( ) );
        return new R( ResponseEnum.SUCCESS, page );
    }

    @PostMapping
    @CheckLogin
    @Transactional( rollbackFor = Exception.class )
    public R addUser( UserBasisEntity user ) {
        log.info( "新增用户：" + user );
        //        boolean bool = userBasisService.addUser( user );
        boolean bool = userBasisService.addNUser( user );
        return new R( ResponseEnum.SUCCESS, bool );
    }

    @PutMapping
    @CheckLogin
    public R updUser( UserBasisEntity tbUser ) {
        log.info( tbUser );
        boolean b = userBasisService.updateById( tbUser );
        return new R( ResponseEnum.SUCCESS, b );
    }

    @DeleteMapping( "{userId}" )
    @CheckLogin
    public R delUserByUserId( @PathVariable( "userId" ) Long userId ) {
        boolean b = userBasisService.removeById( userId );
        return new R( ResponseEnum.SUCCESS, b );
    }

    @PutMapping( "/offline" )
    @Transactional( rollbackFor = Exception.class )
    @CheckLogin
    public R offLineUser( Long userId ) {
        // to do 使用update会因为设置了非空而拒绝更新
        log.warn( "下线的userId:" + userId );
        UpdateWrapper< UserBasisEntity > userBasisEntityUpdateWrapper = new UpdateWrapper<>( );
        userBasisEntityUpdateWrapper.eq( "user_id", userId );
        boolean b = userBasisService.update( UserBasisEntity.builder( ).userStatus( 0 ).build( ),
                                             userBasisEntityUpdateWrapper );
        return new R( ResponseEnum.SUCCESS, b );
    }

    @PutMapping( "/online" )
    @Transactional( rollbackFor = Exception.class )
    @CheckLogin
    public R onLineUser( Long userId ) {
        // to do 使用update会因为设置了非空而拒绝更新
        log.warn( "上线的userId:" + userId );
        UpdateWrapper< UserBasisEntity > userBasisEntityUpdateWrapper = new UpdateWrapper<>( );
        userBasisEntityUpdateWrapper.eq( "user_id", userId );
        boolean b = userBasisService.update( UserBasisEntity.builder( ).userStatus( 0 ).build( ),
                                             userBasisEntityUpdateWrapper );
        return new R( ResponseEnum.SUCCESS, b );
    }

    @GetMapping( "/drugdetailid/{drugDetailId}" )
    public R getUserByDrugDetailId( @PathVariable( "drugDetailId" ) Integer drugDetailId ) {
        String str = userBasisService.getUserByDrugDetailId( drugDetailId );
        return new R( ResponseEnum.SUCCESS, str );
    }

    //    @Value( "${config.staff}" )
    //    private List< Integer > staffList;

    @GetMapping( "/staff" )
    public R getStaff( ) {
        List< UserBasisEntity > list = userBasisService.selectStaff( );
        return new R( ResponseEnum.SUCCESS, list );
    }

    @GetMapping( "/search" )
    public R searchUser( String searchType, String searchText, Integer current, Integer size ) {
        log.info( "searchType:" + searchType );
        log.info( "searchText:" + searchText );
        log.info( "current : " + current + " , size : " + size );
        IPage< SelectVO > searchPage = userBasisService.search( searchType, searchText, current, size );
        return new R( ResponseEnum.SUCCESS, searchPage );
    }

    /**
     * 动态操作封禁用户
     *
     * @param operate 使用操作code
     * @param userId  用户 id
     * @return R
     */
    @PutMapping( "/{operate}/{userId}" )
    public R updateUser( @PathVariable String operate, @PathVariable Integer userId ) {
        int i = userBasisService.changeLine( userId, operate );
        return new R( ResponseEnum.SUCCESS, i );
    }

    @PostMapping( "/save" )
    @Transactional( rollbackFor = Exception.class )
    public R save( UserBasisEntity user ) {
        log.info( "user:" + user );
        boolean bool;
        if ( user.getUserId( ) == null ) {
            // 新增
            bool = userBasisService.addNUser( user );
        }
        else {
            // 更新
            bool = userBasisService.updateNById( user );
        }
        return new R( ResponseEnum.SUCCESS, bool );
    }


    @GetMapping( "/{userId}" )
    public R getUser( @PathVariable Integer userId ) {
        log.info( "userId:" + userId );
        return new R( ResponseEnum.SUCCESS, userBasisService.getById( userId ) );
    }

    /**
     * 用户注册
     *
     * @param userName     用户名
     * @param userPhone    手机号
     * @param userPassword 密码
     * @return R
     */
    @PostMapping( "/register" )
    public R register( String userName, String userPhone, String userPassword ) {

        log.info( "user:" + userName + "|" + userPassword + "|" + userPhone );
        boolean b = userBasisService.addSimpleUser( userName, userPassword, userPhone );
        return new R( ResponseEnum.SUCCESS, b );
    }

    @PutMapping( "/reset/{userId}" )
    public R resetPass( @PathVariable String userId ) {
        log.info( "userId:" + userId );
        int i = userBasisService.resetUserPass( userId );
        return new R( ResponseEnum.SUCCESS, userId );
    }

    @GetMapping( "/info/{userId}" )
    public R getUserInfo( @PathVariable Integer userId ) {
        UserBasisEntity user = userBasisService.getById( userId );
        log.info( "user:" + user );
        return new R( ResponseEnum.SUCCESS, user );
    }

}




















































