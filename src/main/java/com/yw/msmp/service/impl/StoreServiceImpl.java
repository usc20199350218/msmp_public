package com.yw.msmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.entity.StaffEntity;
import com.yw.msmp.entity.StoreEntity;
import com.yw.msmp.entity.UserBasisEntity;
import com.yw.msmp.mapper.OrderMapper;
import com.yw.msmp.mapper.StaffMapper;
import com.yw.msmp.mapper.StoreMapper;
import com.yw.msmp.mapper.UserBasisMapper;
import com.yw.msmp.service.StoreService;
import com.yw.msmp.vo.StoreVo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class StoreServiceImpl extends ServiceImpl< StoreMapper, StoreEntity > implements StoreService {

    @Resource
    private StoreMapper storeMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private UserBasisMapper userMapper;

    @Resource
    private StaffMapper staffMapper;

    @Override
    public void add( StoreEntity storeEntity ) {
        storeMapper
                .insert( storeEntity );
    }

    @Override
    public List< StoreVo > getApplyList( ) {
        return storeMapper.selectApply( );
    }

    /**
     * @return
     */
    @Override
    public List< StoreEntity > getOnlineStoreList( ) {
        List< StoreEntity > storeEntityList = storeMapper.selectList( null );
        log.info( storeEntityList );
        // 后续通过设置字段来实现动态的实现店铺对线上的支持
        return storeEntityList;
    }

    /**
     * @param userId 用户id
     * @return 有订单的店铺
     */
    @Override
    public List< StoreEntity > getUserOrder( Integer userId ) {
        List< StoreEntity > list = orderMapper.selectByUser( userId );
        log.info( "list:" + list );
        return list;
    }

    /**
     * 通过userId获取店铺列表
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public List< StoreEntity > getStoreListByUserId( Integer userId ) {

        UserBasisEntity user = userMapper.selectById( userId );
        Integer roleId = user.getRoleId( );
        log.info( roleId );
        if ( roleId == 1 ) {
            // 管理员可以查看全部店铺
            List< StoreEntity > list = storeMapper.selectList( null );
            log.info( list );
            return list;
        }
        else if ( roleId == 2 || roleId == 3 ) {
            // 工作人员roleId
            // 查询此用户所有的店铺
            QueryWrapper< StaffEntity > staffQueryWrapper = new QueryWrapper<>( );
            staffQueryWrapper.eq( "user_id", userId );
            List< StaffEntity > staffList = staffMapper.selectList( staffQueryWrapper );
            ArrayList< StoreEntity > list = new ArrayList<>( );
            for ( StaffEntity ref : staffList ) {
                StoreEntity store = storeMapper.selectById( ref.getStoreId( ) );
                list.add( store );
            }

            return list;
        }
        // 工作人员可以查看自己所属店铺
        throw new AppException( ResponseEnum.PERMISSION_NOT_SET_ERROR );
    }

}
