package com.yw.msmp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.msmp.dto.SearchUserDTO;
import com.yw.msmp.dto.UserDTO;
import com.yw.msmp.entity.UserBasisEntity;
import com.yw.msmp.vo.SelectVO;

import java.util.List;

/**
 * @author yanhaoyuwen
 */
public interface UserBasisService extends IService< UserBasisEntity > {

    /**
     * 验证登录
     *
     * @param userName 用户名
     * @param userPassword 密码
     * @return 返回用户基本信息
     */
    UserBasisEntity login( String userName, String userPassword );

    IPage< UserDTO > selectPage( int current, int size );

    boolean addUser( UserBasisEntity tbUser );

    String getUserByDrugDetailId( Integer drugDetailId );

    List< UserBasisEntity > selectStaff( );

    //    List< SelectVO > search( String searchType, String searchText );

    IPage< SelectVO > search( String searchType, String searchText, int current, int size );

    /**
     * 更改状态
     *
     * @param userId  id
     * @param operate 使用操作code
     * @return 更改状态
     */
    int changeLine( Integer userId, String operate );

    /**
     * 添加用户
     *
     * @param user 用户信息
     * @return 是否成功
     */
    boolean addNUser( UserBasisEntity user );

    /**
     * 更改
     *
     * @param user 用户信息
     * @return 是否成功
     */
    boolean updateNById( UserBasisEntity user );

    /**
     * 查询用户
     *
     * @param searchUserDTO searchUserDTO
     * @return 用户分页列表
     */
    IPage< UserDTO > searchPage( SearchUserDTO searchUserDTO );

    /**
     * 根据手机号查询用户信息
     *
     * @param phoneNum 手机号
     * @return 用户信息
     */
    UserBasisEntity findUserByPhoneNum( String phoneNum );

    /**
     * 添加用户
     *
     * @param userName     用户名
     * @param userPassword 密码
     * @param userPhone    手机号
     * @return 添加结果
     */
    boolean addSimpleUser( String userName, String userPassword, String userPhone );

    /**
     * 重置密码
     *
     * @param userId id
     * @return 更改状态
     */
    int resetUserPass( String userId );

    /**
     * 获取供应商列表
     * @return 供应商列表
     */
    List< UserBasisEntity> getSupplierList( );

}
