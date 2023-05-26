package com.yw.msmp.service.impl;
/*
 * @PACKAGE_NAME com.yw.msmp.service.impl
 * @author  yanhaoyuwen
 * @version  1.0
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.entity.ServiceDetailEntity;
import com.yw.msmp.entity.ServiceEntity;
import com.yw.msmp.entity.ServiceEntryEntity;
import com.yw.msmp.mapper.ServiceDetailMapper;
import com.yw.msmp.mapper.ServiceEntryMapper;
import com.yw.msmp.mapper.ServiceMapper;
import com.yw.msmp.service.ServiceService;
import com.yw.msmp.vo.ServiceDetailVO;
import com.yw.msmp.vo.ServiceVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yanhaoyuwen
 **/
@Log4j2
@Service
public class ServiceServiceImpl extends ServiceImpl< ServiceMapper, ServiceEntity > implements ServiceService {

    @Resource
    private ServiceMapper serviceMapper;

    @Resource
    private ServiceDetailMapper serviceDetailMapper;

    @Resource
    private ServiceEntryMapper serviceEntryMapper;

    /**
     * @param userId 用户id
     * @return some info
     */
    @Override
    public ServiceVO getLastVo( Integer userId ) {
        // 获取主体
        ServiceVO serviceVO = serviceMapper.selectLastService( userId );
        log.info( "serviceVO:" + serviceVO );

        // 准备map
        HashMap< Object, Object > hashMap = new HashMap<>( );
        ArrayList< ServiceDetailVO > list = new ArrayList<>( );
        // 获取map的key
        List< ServiceEntryEntity > entryList = serviceEntryMapper.selectList( null );
        log.info( "entryList:" + entryList );

        //        // 遍历获取map的val，并放入map
        //        for ( ServiceEntryEntity entryEntity : entryList ) {
        //            QueryWrapper< ServiceDetailEntity > wrapper = new QueryWrapper<>( );
        //            wrapper.eq( "service_entry_id", entryEntity.getServiceEntryId( ) ).eq( "service_id",
        //                                                                                   serviceVO.getServiceId( ) ).eq(
        //                    "user_id",
        //                    userId );
        //            List< ServiceDetailEntity > detailList = serviceDetailMapper.selectList( wrapper );
        //            hashMap.put( entryEntity.getServiceEntryName( ),
        //                         !detailList.isEmpty( ) ? ServiceDetailVO.builder( ).content( detailList.get( 0 )
        //                                                                                                .getContent( ) )
        //                                                                 .remark( entryEntity.getRemark( ) )
        //                                                                 .build( ) : ServiceDetailVO.builder( )
        //                                                                                            .remark( entryEntity.getRemark( ) )
        //                                                                                            .build( ) );
        //        }
        //
        //        // 与主体拼接
        //        serviceVO.setServiceMap( hashMap );

        // 遍历获取放入list
        for ( ServiceEntryEntity entryEntity : entryList ) {
            QueryWrapper< ServiceDetailEntity > wrapper = new QueryWrapper<>( );
            wrapper.eq( "service_entry_id", entryEntity.getServiceEntryId( ) ).eq( "service_id",
                                                                                   serviceVO.getServiceId( ) ).eq(
                    "user_id",
                    userId );
            List< ServiceDetailEntity > detailList = serviceDetailMapper.selectList( wrapper );
            //            hashMap.put( entryEntity.getServiceEntryName( ),
            //                         !detailList.isEmpty( ) ? ServiceDetailVO.builder( ).content( detailList.get( 0 )
            //                                                                                                .getContent( ) )
            //                                                                 .remark( entryEntity.getRemark( ) )
            //                                                                 .build( ) : ServiceDetailVO.builder( )
            //                                                                                            .remark( entryEntity.getRemark( ) )
            //                                                                                            .build( ) );

            list.add( ServiceDetailVO.builder( ).serviceEntryName( entryEntity.getServiceEntryName( ) )
                                     .content( !detailList.isEmpty( ) ? detailList.get( 0 ).getContent( ) : "" )
                                     .remark( entryEntity.getRemark( ) ).build( ) );
        }

        // 与主体拼接
        serviceVO.setServiceList( list );

        log.info( "serviceVO:" + serviceVO );
        return serviceVO;
    }

    /**
     * @param userId 用户id
     * @return List<ServiceEntity>
     */
    @Override
    public List< ServiceEntity > myList( Integer userId ) {
        log.info( "userId:" + userId );
        QueryWrapper< ServiceEntity > wrapper = new QueryWrapper<>( );
        wrapper.eq( "user_id", userId );
        List< ServiceEntity > list = serviceMapper.selectList( wrapper );
        log.info( "list:" + list );
        return list;
    }

    /**
     * @param userId
     * @param current
     * @param size
     * @return
     */
    @Override
    public IPage< ServiceEntity > getMyPage( Integer userId, Integer current, Integer size ) {
        Page< ServiceEntity > page = new Page<>( current, size );

        QueryWrapper< ServiceEntity > wrapper = new QueryWrapper<>( );
        wrapper.eq( "user_id", userId )
               .orderByDesc( "create_time" );
        Page< ServiceEntity > selectPage = serviceMapper.selectPage( page, wrapper );
        log.info( "page:" + selectPage );

        return selectPage;
    }

    /**
     * 根据id获取视图对象
     *
     * @param serviceId 服务id
     * @return ServiceVO
     */
    @Override
    public ServiceVO getVoById( Integer serviceId ) {
        // 获取主体
        ServiceVO serviceVO = serviceMapper.searchService( serviceId );
        log.info( "serviceVO:" + serviceVO );
        if ( serviceVO == null ) {
            throw new AppException( ResponseEnum.OPERATE_ERROR );
        }
        // 准备list
        ArrayList< ServiceDetailVO > list = new ArrayList<>( );
        // entryList
        List< ServiceEntryEntity > entryList = serviceEntryMapper.selectList( null );
        log.info( "entryList:" + entryList );

        // todo: 目前的考虑是A直接根据服务id获取详情，还是老套路B根据entry去获取详情（多一个循环）
        // 折中方案：先都取出来，比较数量，数量没问题，则正常A, 反之B

        // 遍历entryList，查询，插入
        for ( ServiceEntryEntity entryEntity : entryList ) {
            QueryWrapper< ServiceDetailEntity > wrapper = new QueryWrapper<>( );
            wrapper.eq( "service_entry_id", entryEntity.getServiceEntryId( ) )
                   .eq( "service_id", serviceVO.getServiceId( ) );
            List< ServiceDetailEntity > detailList = serviceDetailMapper.selectList( wrapper );

            //            list.add( ServiceDetailVO.builder( ).serviceDetailId( detailList.get( 0 ).getServiceDetailId( ) )
            //                                     .serviceEntryId( entryEntity.getServiceEntryId( ) )
            //                                     .serviceEntryName( entryEntity.getServiceEntryName( ) )
            //                                     .content( !detailList.isEmpty( ) ? detailList.get( 0 ).getContent( ) : "" )
            //                                     .remark( entryEntity.getRemark( ) ).build( ) );
            if ( !detailList.isEmpty( ) ) {
                list.add( ServiceDetailVO.builder( )
                                         .serviceDetailId( detailList.get( 0 ).getServiceDetailId( ) )
                                         .serviceEntryId( entryEntity.getServiceEntryId( ) )
                                         .serviceEntryName( entryEntity.getServiceEntryName( ) )
                                         .content( detailList.get( 0 ).getContent( ) )
                                         .remark( entryEntity.getRemark( ) ).build( ) );
            }
            else {
                //添加一个默认值
                list.add( ServiceDetailVO.builder( )
                                         .serviceDetailId( null )
                                         .serviceEntryId( entryEntity.getServiceEntryId( ) )
                                         .serviceEntryName( entryEntity.getServiceEntryName( ) )
                                         .content( "" )
                                         .remark( entryEntity.getRemark( ) ).build( ) );
            }
        }

        // 与主体拼接
        serviceVO.setServiceList( list );

        log.info( "serviceVO:" + serviceVO );
        return serviceVO;
    }

    /**
     * @param serviceVO 服务的添加对象
     * @return
     */
    @Override
    public boolean addService( ServiceVO serviceVO ) {
        // 更新其他明细
        ServiceEntity.builder( ).isLast( "0" ).build( );
        QueryWrapper< ServiceEntity > wrapper = new QueryWrapper<>( );
        wrapper.eq( "is_last", "1" );
        serviceMapper.update( ServiceEntity.builder( ).isLast( "0" ).build( ), wrapper );
        // 新建service
        ServiceEntity service = ServiceEntity.builder( ).isLast( "1" ).isNormal( serviceVO.getIsNormal( ) ).userId(
                serviceVO.getUserId( ) ).serviceDate( serviceVO.getServiceDate( ) ).build( );
        int insert = serviceMapper.insert( service );
        if ( insert != 1 ) {
            throw new AppException( ResponseEnum.ADD_ERROR );
        }
        // 新建明细
        List< ServiceDetailVO > detailVOList = serviceVO.getServiceList( );
        for ( ServiceDetailVO serviceDetailVO : detailVOList ) {
            int i = serviceDetailMapper.insert( ServiceDetailEntity.builder( )
                                                                   .serviceEntryId( serviceDetailVO.getServiceEntryId( ) )
                                                                   .serviceId( service.getServiceId( ) ).content(
                            serviceDetailVO.getContent( ) ).userId( serviceVO.getUserId( ) ).build( ) );
            if ( i != 1 ) {
                throw new AppException( ResponseEnum.ADD_ERROR );
            }
        }
        return true;

    }

    /**
     * @param serviceVO 服务的更新对象
     * @return
     */
    @Override
    public boolean updService( ServiceVO serviceVO ) {
        // 更新service
        int updated = serviceMapper.updateById( ServiceEntity.builder( ).serviceId( serviceVO.getServiceId( ) ).isLast(
                                                                     serviceVO.getIsLast( ) ).isNormal( serviceVO.getIsNormal( ) ).userId( serviceVO.getUserId( ) )
                                                             .serviceDate( serviceVO.getServiceDate( ) ).build( ) );
        log.info( "updated:" + updated );
        // 更新明细
        List< ServiceDetailVO > detailVOList = serviceVO.getServiceList( );
        for ( ServiceDetailVO serviceDetailVO : detailVOList ) {
            int updated1 = serviceDetailMapper.updateById( ServiceDetailEntity.builder( ).serviceDetailId(
                                                                                      serviceDetailVO.getServiceDetailId( ) ).serviceEntryId( serviceDetailVO.getServiceEntryId( ) )
                                                                              .userId( serviceVO.getUserId( ) ).content(
                            serviceDetailVO.getContent( ) ).serviceId( serviceVO.getServiceId( ) ).build( ) );
            log.info( "updated1:" + updated1 );
        }
        return true;
    }

    /**
     * @param serviceId
     */
    @Override
    public void deleteService( Integer serviceId ) {
        int deleteNum = serviceMapper.deleteById( serviceId );
        if ( deleteNum != 1 ) {
            throw new AppException( ResponseEnum.DEL_ERROR );
        }

        QueryWrapper< ServiceDetailEntity > wrapper = new QueryWrapper<>( );
        wrapper.eq( "service_id", serviceId );
        serviceDetailMapper.delete( wrapper );
    }

}
