package com.yw.msmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.dto.MenuDTO;
import com.yw.msmp.dto.RightDTO;
import com.yw.msmp.entity.RightEntity;
import com.yw.msmp.entity.RoleRightEntity;
import com.yw.msmp.mapper.RightMapper;
import com.yw.msmp.mapper.RoleRightMapper;
import com.yw.msmp.service.RightService;
import com.yw.msmp.vo.RightVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yanhaoyuwen
 */
@Log4j2
@Service
public class RightServiceImpl extends ServiceImpl< RightMapper, RightEntity > implements RightService {

    @Resource
    private RightMapper rightMapper;

    @Resource
    private RoleRightMapper roleRightMapper;

    String right_parent_id = "right_parent_id";

    @Override
    public List< String > getRightCodesByRoleId( Integer roleId ) {
        return null;
    }

    @Override
    public List< MenuDTO > selectMenuByRoleId( Integer roleId ) {
        log.info( "开始查询roleId为" + roleId + "的菜单权限" );
        List< MenuDTO > menuDTOList = rightMapper.selectMenuByRoleIdAndParentId( roleId, 0 );
        if ( menuDTOList == null || menuDTOList.size( ) == 0 ) {
            throw new AppException( ResponseEnum.ROLE_NO_MENUS );
        }
        for ( MenuDTO menuDTO : menuDTOList ) {

            Integer parentId = menuDTO.getRightId( );
            log.info( "现在正在查询的parentId是：" + parentId );
            List< MenuDTO > menuDTOS = rightMapper.selectMenuByRoleIdAndParentId( roleId, parentId );
            log.info( "查询出来的MenuDTO：" + menuDTOS );
            menuDTO.setMenuDTO( menuDTOS );
        }

        return menuDTOList;
    }

    @Override
    public List< RightEntity > findAllRight( ) {
        List< RightEntity > list = rightMapper.selectList( null );
        //        List< RightVO > list = rightMapper.selectVOList( );
        log.info( "list:>>>" + list );
        return list;
    }

    @Override
    public List< RightEntity > findAllParentRight( ) {
        QueryWrapper< RightEntity > queryWrapper = new QueryWrapper<>( );
        return rightMapper.selectList( queryWrapper.eq( right_parent_id, 0 ) );
    }

    @Override
    public int addRight( RightEntity right ) {
        // 重置父菜单的一些可能遗漏的
        if ( right.getRightType( ) == 0 ) {
            right.setRightUrl( "" );
            right.setRightParentId( 0 );
        }
        log.info( "service进入的right为：" + right );
        // 检查是否重名
        RightEntity entity = rightMapper.selectByRightText( right.getRightText( ) );
        if ( entity == null ) {
            log.info( "不存在:" + right.getRightText( ) );
            return rightMapper.insert( right );
        }
        //        else {
        //            log.info( "存在:" + right.getRightText( ) );
        //            log.info( "判断是否被删除" );
        //            if ( entity.getDeleted( ) == 1 ) {
        //                log.info( "被删除，该状态" );
        //                QueryWrapper< RightEntity > rightEntityQueryWrapper = new QueryWrapper<>( );
        //                right.setRightId( entity.getRightId( ) );
        //                int recover = rightMapper.changeState( entity.getRightId( ) );
        //                return recover;
        //            }
        //            else {
        //                log.info( "未删除，报错" );
        throw new AppException( ResponseEnum.UPDATE_ERROR );
        //            }
        //        }

    }

    // todo 获取tree，只能获取到父菜单，子菜单无法显示
    @Override
    public List< RightDTO > findTreeRight( ) {
        // 获取父菜单
        QueryWrapper< RightEntity > rightQueryWrapper1 = new QueryWrapper<>( );
        List< RightEntity > rights = rightMapper.selectList( rightQueryWrapper1.eq( "right_type", 0 ) );

        List< RightDTO > rightDTOList = new ArrayList<>( );
        // 遍历父菜单，使用id，查询子菜单
        for ( RightEntity right : rights ) {
            log.info( "正在遍历父菜单，获取子菜单" );
            QueryWrapper< RightEntity > rightQueryWrapper2 = new QueryWrapper<>( );
            List< RightEntity > rightList = rightMapper.selectList( rightQueryWrapper2.eq( right_parent_id,
                                                                                           right.getRightId( ) ) );
            RightDTO rightDTO = new RightDTO( );
            BeanUtils.copyProperties( right, rightDTO );
            rightDTO.setRightList( rightList );
            log.info( "copy之后得到：" + rightDTO );
            rightDTOList.add( rightDTO );
        }
        log.info( "完整的rights为：" + rightDTOList );
        return rightDTOList;
    }

    @Override
    public List< RightDTO > selectMenuByRoleIdPlus( Integer roleId ) {
        log.info( "_______________" + roleId );
        // 获取父菜单
        QueryWrapper< RightEntity > rightQueryWrapper1 = new QueryWrapper<>( );
        List< RightEntity > rights = rightMapper.selectRoleList( 0, roleId );
        log.info( "获取的权限为：" + rights );
        Integer rightId;
        List< RightDTO > rightDTOList = new ArrayList<>( );
        // 遍历父菜单，使用id，查询子菜单
        for ( RightEntity right : rights ) {
            // 菜单权限id
            rightId = right.getRightId( );
            QueryWrapper< RightEntity > rightQueryWrapper2 = new QueryWrapper<>( );
            // 查询子菜单
            List< RightEntity > rightList =
                    //                    rightMapper.selectList( rightQueryWrapper2.eq( right_parent_id, rightId )
                    //                                                                                      .eq( "rigth_type", 1 ) );
                    rightMapper.selectRoleList( 1, roleId );
            // copy一下
            RightDTO rightDTO = new RightDTO( );
            BeanUtils.copyProperties( right, rightDTO );
            // 子菜单上位
            rightDTO.setRightList( rightList );
            log.info( "copy之后得到：" + rightDTO );
            rightDTOList.add( rightDTO );
        }
        log.info( "_______________" + roleId );
        log.info( "完整的rights为：" + rightDTOList );
        log.info( "_______________" + roleId );
        return rightDTOList;
    }

    /**
     * 删除菜单
     *
     * @param rightId 菜单ID
     * @return 是否删除成功
     */
    @Override
    public boolean delById( Integer rightId ) {
        // 子菜单，删除role——right，删除right
        RightEntity rightEntity = rightMapper.selectById( rightId );
        // 准备删除的权限id集合
        ArrayList< Integer > list = new ArrayList<>( );
        list.add( rightId );

        if ( rightEntity.getRightType( ) == 0 ) {
            // 父菜单，获取子菜单一并删除
            QueryWrapper< RightEntity > rightQueryWrapper = new QueryWrapper<>( );
            rightQueryWrapper.eq( right_parent_id, rightEntity.getRightId( ) );
            List< RightEntity > rightList = rightMapper.selectList( rightQueryWrapper );
            // 遍历添加子菜单
            for ( RightEntity right : rightList ) {
                list.add( right.getRightId( ) );
            }
        }

        // 根据list逐个删除
        for ( Integer integer : list ) {
            QueryWrapper< RoleRightEntity > roleRightEntityQueryWrapper = new QueryWrapper<>( );
            roleRightEntityQueryWrapper.eq( "right_id", integer );
            roleRightMapper.delete( roleRightEntityQueryWrapper );
            // 删除该菜单
            int i = rightMapper.deleteById( integer );
            if ( i != 1 ) {
                throw new AppException( ResponseEnum.DEL_ERROR );
            }
        }

        return true;
    }


    @Override
    public List< RightVO > getAllRight( ) {
        List< RightVO > rightVOList = rightMapper.selectVOList( );
        // 遍历right
        for ( RightVO rightVO : rightVOList ) {
            // 根据rightId 获取角色id
            QueryWrapper< RoleRightEntity > roleRightEntityQueryWrapper = new QueryWrapper<>( );
            roleRightEntityQueryWrapper.eq( "right_id", rightVO.getRightId( ) );
            List< RoleRightEntity > roleRightEntityList = roleRightMapper.selectList( roleRightEntityQueryWrapper );
            List< Integer > list = new ArrayList<>( );
            for ( RoleRightEntity roleRightEntity : roleRightEntityList ) {
                list.add( roleRightEntity.getRoleId( ) );
            }
            rightVO.setRoleId( list );
        }
        return rightVOList;
    }

    /**
     * 新增
     *
     * @param rightVO 新增的值
     * @return 是否成功
     */
    @Override
    public boolean saveVO( RightVO rightVO ) {

        // 保存right
        RightEntity right = RightEntity.builder( )
                                       .rightText( rightVO.getRightText( ) )
                                       .rightType( rightVO.getRightType( ) )
                                       .rightUrl( rightVO.getRightUrl( ) )
                                       .rightParentId( rightVO.getRightParentId( ) )
                                       .rightMenu( rightVO.getRightMenu( ) )
                                       .build( );

        // 清理重置
        if ( right.getRightType( ) == 0 ) {
            right.setRightUrl( "" );
            right.setRightParentId( 0 );
        }
        int insert1 = rightMapper.insert( right );
        if ( insert1 != 1 ) {
            throw new AppException( ResponseEnum.ADD_ERROR );
        }
        // 保存role——right
        List< Integer > list = rightVO.getRoleId( );
        for ( Integer roleId : list ) {
            int insert = roleRightMapper.insert( RoleRightEntity.builder( )
                                                                .rightId( right.getRightId( ) )
                                                                .roleId( roleId )
                                                                .build( ) );
            if ( insert != 1 ) {
                throw new AppException( ResponseEnum.ADD_ERROR );
            }
        }
        return true;
    }

    /**
     * 更新
     *
     * @param rightVO 更新的值
     * @return 是否成功
     */
    @Override
    public boolean updateVOById( RightVO rightVO ) {
        // 更新right
        RightEntity right = RightEntity.builder( )
                                       .rightId( rightVO.getRightId( ) )
                                       .rightText( rightVO.getRightText( ) )
                                       .rightType( rightVO.getRightType( ) )
                                       .rightUrl( rightVO.getRightUrl( ) )
                                       .rightParentId( rightVO.getRightParentId( ) )
                                       .rightMenu( rightVO.getRightMenu( ) )
                                       .build( );
        if ( right.getRightType( ) == 0 ) {
            right.setRightUrl( "" );
            right.setRightParentId( 0 );
        }
        rightMapper.updateById( right );

        // 清理 role——right
        List< Integer > roleIdList = rightVO.getRoleId( );

        QueryWrapper< RoleRightEntity > queryWrapper = new QueryWrapper<>( );
        queryWrapper.in( "role_id", roleIdList ).eq( "right_id", rightVO.getRightId( ) );
        roleRightMapper.delete( queryWrapper );

        // 添加
        for ( Integer roleId : roleIdList ) {
            int insert = roleRightMapper.insert( RoleRightEntity.builder( )
                                                                .roleId( roleId )
                                                                .rightId( rightVO.getRightId( ) )
                                                                .build( ) );
            if ( insert != 1 ) {
                throw new AppException( ResponseEnum.ADD_ERROR );
            }
        }

        return true;
    }


}
