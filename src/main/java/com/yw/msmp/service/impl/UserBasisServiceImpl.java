package com.yw.msmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yw.msmp.common.config.DefaultUserConfig;
import com.yw.msmp.common.config.RoleConfig;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.dto.SearchUserDTO;
import com.yw.msmp.dto.UserDTO;
import com.yw.msmp.entity.UserBasisEntity;
import com.yw.msmp.mapper.BrandMapper;
import com.yw.msmp.mapper.DrugDetailsMapper;
import com.yw.msmp.mapper.UserBasisMapper;
import com.yw.msmp.service.UserBasisService;
import com.yw.msmp.vo.SelectVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author yanhaoyuwen
 */
@Log4j2
@Service
public class UserBasisServiceImpl extends ServiceImpl< UserBasisMapper, UserBasisEntity > implements UserBasisService {

    @Resource
    private UserBasisMapper userMapper;

    @Resource
    private DrugDetailsMapper drugDetailsMapper;

    @Resource
    private BrandMapper brandMapper;

    @Resource
    private DefaultUserConfig defaultUserConfig;

    @Resource
    private RoleConfig roleConfig;

    @Value( "${position.positionId.manager}" )
    private Integer manager;
    @Value( "${position.positionId.clerk}" )
    private Integer clerk;

    /**
     * 登录
     *
     * @param userName     用户名
     * @param userPassword 密码
     * @return 用户信息
     */
    @Override
    public UserBasisEntity login( String userName, String userPassword ) {
        log.info( "顺利进入login的service携带的参数为userName：>>" + userName + "==|userPassword:>>" + userPassword );
        // 根据用户名查询用户信息
        QueryWrapper< UserBasisEntity > queryWrapper = new QueryWrapper<>( );
        UserBasisEntity user = userMapper.selectOne( queryWrapper.eq( "user_Name", userName ) );
        // 检查是否不存在
        if ( user == null ) {
            throw new AppException( ResponseEnum.USERNAME_NOT_FOUND );
        }
        log.info( "查询出来的user为：" + user );
        if ( user.getUserStatus( ) == 0 ) {
            throw new AppException( ResponseEnum.USER_STATUS_ERROR );
        }
        // 检查密码是否相符
        log.info( "开始检查密码" );
        String md5pwd = DigestUtils.md5DigestAsHex( userPassword.getBytes( ) );
        if ( !md5pwd.equals( user.getUserPassword( ) ) ) {
            throw new AppException( ResponseEnum.USERNAME_OR_PASSWORD_INVALIDATE );
        }
        log.info( "密码相符，返回user" );
        // 检查状态
        if ( user.getUserStatus( ) != 1 ) {
            throw new AppException( ResponseEnum.USER_STATUS_ERROR );
        }
        return user;
    }

    @Override
    public IPage< UserDTO > selectPage( int current, int size ) {
        Page< UserBasisEntity > tbUserPage = new Page<>( current, size );
        IPage< UserDTO > page = userMapper.selectMyPage( tbUserPage );
        log.info( "tbUser:" + page.getRecords( ) );
        return page;
    }

    @Override
    public boolean addUser( UserBasisEntity user ) {
        // 检查手机号、用户名等是否重复
        //        List< UserBasisEntity > userBasisEntityList = userMapper.selectListPlus( user.getUserName( ), user
        //        .getUserPhone( ) );
        QueryWrapper< UserBasisEntity > userqw1 = new QueryWrapper<>( );
        userqw1.eq( "user_name", user.getUserName( ) );
        List< UserBasisEntity > userBasisEntityList1 = userMapper.selectList( userqw1 );

        QueryWrapper< UserBasisEntity > userqw2 = new QueryWrapper<>( );
        userqw2.eq( "user_phone", user.getUserPhone( ) );
        List< UserBasisEntity > userBasisEntityList2 = userMapper.selectList( userqw2 );

        if ( userBasisEntityList1.isEmpty( ) && userBasisEntityList2.isEmpty( ) ) {
            // 不重复
            // 加密密码
            user.setUserPassword( DigestUtils.md5DigestAsHex( user.getUserPassword( ).getBytes( ) ) );
            int insert = userMapper.insert( user );
            return insert == 1;
        }
        else {
            throw new AppException( ResponseEnum.Repeating_Field_ERROR );
        }
    }

    @Override
    public String getUserByDrugDetailId( Integer drugDetailId ) {
        Integer brandId = drugDetailsMapper.selectById( drugDetailId ).getBrandId( );
        Integer userId = brandMapper.selectById( brandId ).getUserId( );
        UserBasisEntity userBasisEntity = userMapper.selectById( userId );
        return userBasisEntity.getUserRealName( ) + " 电话：" + userBasisEntity.getUserPhone( );
    }

    @Override
    public List< UserBasisEntity > selectStaff( ) {
        log.info( "配置文件读取出来的店长角色id为：" + manager );

        log.info( "配置文件读取出来的店员角色id为：" + clerk );

        QueryWrapper< UserBasisEntity > wrapper = new QueryWrapper<>( );
        if ( manager != null && clerk == null ) {
            wrapper.eq( "role_id", manager );
        }
        if ( clerk != null && manager == null ) {
            wrapper.eq( "role_id", clerk );
        }
        if ( manager != null && clerk != null ) {
            //            wrapper.eq( "role_id", manager ).or( ).eq( "role_id", clerk );
            wrapper.in( "role_id", Arrays.asList( manager, clerk ) );
        }
        return userMapper.selectList( wrapper );
    }

    @Override
    public IPage< SelectVO > search( String searchType, String searchText, int current, int size ) {
        boolean isName = "realName".equals( searchType );
        //        QueryWrapper< UserBasisEntity > userBasisEntityQueryWrapper = new QueryWrapper<>( );
        if ( isName ) {
            log.info( "开始根据姓名查询" );
            //            userBasisEntityQueryWrapper.like( "user_real_name", searchText );
        }
        else {
            log.info( "开始根据手机号查询" );
            //            userBasisEntityQueryWrapper.like( "user_phone", searchText );
        }
        Page< SelectVO > selectVOPage = new Page<>( current, size );
        IPage< SelectVO > page = userMapper.selectVOPage( searchType, searchText, selectVOPage );
        log.info( "page==============================" );
        return page;
    }

    /**
     * @param userId  用户id
     * @param operate 操作
     * @return 操作是否成功
     */
    @Override
    public int changeLine( Integer userId, String operate ) {
        log.info( "入参当前用户：" + userId + "进行操作：" + operate );
        // 判断操作
        if ( "OnLine".equals( operate ) ) {
            return userMapper.updateById( UserBasisEntity.builder( ).userId( userId ).userStatus( 1 ).build( ) );
        }
        else {
            return userMapper.updateById( UserBasisEntity.builder( ).userId( userId ).userStatus( 0 ).build( ) );
        }
    }

    /**
     * 添加用户
     *
     * @param user 用户
     * @return 是否成功
     */
    @Override
    public boolean addNUser( UserBasisEntity user ) {
        log.info( "addNUser：" + user );
        // 检查手机号
        QueryWrapper< UserBasisEntity > queryWrapper = new QueryWrapper<>( );
        queryWrapper.eq( "user_phone", user.getUserPhone( ) );
        List< UserBasisEntity > list = userMapper.selectList( queryWrapper );
        if ( !list.isEmpty( ) ) {
            throw new AppException( ResponseEnum.PHONE_REPEAT_ERROR );
        }

        // 检查用户名是否重复
        log.info( "checkAlreadyExit：" + user.getUserName( ) );
        queryWrapper = new QueryWrapper<>( );
        queryWrapper.eq( "user_name", user.getUserName( ) );
        list = userMapper.selectList( queryWrapper );
        if ( !list.isEmpty( ) ) {
            throw new AppException( ResponseEnum.NAME_REPEAT_ERROR );
        }

        // 默认用户名
        user.setUserPassword( DigestUtils.md5DigestAsHex( user.getUserName( ).getBytes( ) ) );

        // 插入
        int insert = userMapper.insert( user );
        if ( insert == 1 ) {
            return true;
        }
        throw new AppException( ResponseEnum.ADD_ERROR );
    }

    /**
     * 更改
     *
     * @param user 用户信息
     * @return 是否成功
     */
    @Override
    public boolean updateNById( UserBasisEntity user ) {
        UserBasisEntity userBasis = userMapper.selectById( user.getUserId( ) );
        // 检查手机号是否更改
        log.info( "updateNById：" + user + "进行操作：" );

        if ( !userBasis.getUserPhone( ).equals( user.getUserPhone( ) ) ) {
            // 手机号更改，检查是否重复
            QueryWrapper< UserBasisEntity > queryWrapper = new QueryWrapper<>( );
            queryWrapper.eq( "user_phone", user.getUserPhone( ) );
            List< UserBasisEntity > list = userMapper.selectList( queryWrapper );
            if ( list.size( ) > 1 ) {
                throw new AppException( ResponseEnum.PHONE_REPEAT_ERROR );
            }
        }
        int updated = userMapper.updateById( user );
        return updated == 1;
    }

    /**
     * 查询用户
     *
     * @param searchUserDTO searchUserDTO
     * @return 用户分页列表
     */
    @Override
    public IPage< UserDTO > searchPage( SearchUserDTO searchUserDTO ) {
        Page< UserBasisEntity > userPage = new Page<>( searchUserDTO.getCurrent( ), searchUserDTO.getSize( ) );
        IPage< UserDTO > page = userMapper.searchPage( searchUserDTO, userPage );
        log.info( "page.Records" + page.getRecords( ) );
        return page;
    }

    /**
     * 根据手机号查询用户信息
     *
     * @param phoneNum 手机号
     * @return 用户信息
     */
    @Override
    public UserBasisEntity findUserByPhoneNum( String phoneNum ) {
        log.info( "isDetailValidManager：" + phoneNum );
        QueryWrapper< UserBasisEntity > queryWrapper = new QueryWrapper<>( );
        queryWrapper.eq( "user_phone", phoneNum );
        UserBasisEntity userBasisEntity = userMapper.selectOne( queryWrapper );
        log.info( "queryUserByPhoneNum：" + userBasisEntity.getUserName( ) );
        return userBasisEntity;
    }

    /**
     * 添加用户
     *
     * @param userName     用户名
     * @param userPassword 密码
     * @param userPhone    手机号
     * @return 是否成功
     */
    @Override
    public boolean addSimpleUser( String userName, String userPassword, String userPhone ) {
        UserBasisEntity user = UserBasisEntity.builder( )
                                              .userName( userName )
                                              .userPhone( userPhone )
                                              .userPassword( DigestUtils.md5DigestAsHex( userPassword.getBytes( ) ) )
                                              .roleId( defaultUserConfig.roleId )
                                              .build( );
        int insert = userMapper.insert( user );
        return insert == 1;
    }

    /**
     * 重置密码
     *
     * @param userId id
     * @return 更改状态
     */
    @Override
    public int resetUserPass( String userId ) {
        UserBasisEntity user = userMapper.selectById( userId );
        if ( user != null ) {
            user.setUserPassword( DigestUtils.md5DigestAsHex( defaultUserConfig.userPassWord.getBytes( ) ) );
            int updated = userMapper.updateById( user );
            if ( updated == 1 ) {
                return updated;
            }
        }
        throw new AppException( ResponseEnum.UPDATE_ERROR );
    }

    /**
     * 获取供应商列表
     *
     * @return 供应商列表
     */
    @Override
    public List< UserBasisEntity > getSupplierList( ) {
        System.out.println( roleConfig.supplier );
        QueryWrapper< UserBasisEntity > queryWrapper = new QueryWrapper<>( );
        queryWrapper.eq( "role_id", roleConfig.supplier );
        List< UserBasisEntity > userBasisEntities = userMapper.selectList( queryWrapper );
        log.info( "userBasisEntities" + userBasisEntities );
        return userBasisEntities;
    }

}

