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
import com.yw.msmp.entity.ServiceEntryEntity;
import com.yw.msmp.mapper.ServiceEntryMapper;
import com.yw.msmp.service.ServiceEntryService;
import com.yw.msmp.vo.ServiceDetailVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yanhaoyuwen
 **/
@Log4j2
@Service
public class ServiceEntryServiceImpl extends ServiceImpl< ServiceEntryMapper, ServiceEntryEntity >
        implements ServiceEntryService {

    @Resource
    private ServiceEntryMapper serviceEntryMapper;

    /**
     * @param searchMethod
     * @param searchText
     * @param current
     * @param size
     * @return
     */
    @Override
    public IPage< ServiceEntryEntity > getPage( String searchMethod,
                                                String searchText,
                                                Integer current,
                                                Integer size ) {
        Page< ServiceEntryEntity > serviceEntryPage = new Page<>( current, size );
        IPage< ServiceEntryEntity > page = serviceEntryMapper.selectMyPage( searchMethod,
                                                                            searchText,
                                                                            serviceEntryPage );
        log.info( "正常查询" );
        return page;
    }

    /**
     * @param serviceEntry
     * @return
     */
    @Override
    public boolean add( ServiceEntryEntity serviceEntry ) {

        // 检查重名
        QueryWrapper< ServiceEntryEntity > wrapper = new QueryWrapper<>( );
        wrapper.eq( "service_entry_name", serviceEntry.getServiceEntryName( ) );
        List< ServiceEntryEntity > list = serviceEntryMapper.selectList( wrapper );
        if ( !list.isEmpty( ) ) {
            throw new AppException( ResponseEnum.NAME_REPEAT_ERROR );
        }

        log.info( "添加:" + serviceEntry );
        serviceEntry.setServiceEntryId( null );
        if ( serviceEntryMapper.insert( serviceEntry ) == 1 ) {
            log.info( "添加成功" + serviceEntry );

            return true;
        }
        throw new AppException( ResponseEnum.ADD_ERROR );
    }

    /**
     * @param serviceEntry
     * @return
     */
    @Override
    public boolean upd( ServiceEntryEntity serviceEntry ) {
        ServiceEntryEntity entryEntity = serviceEntryMapper.selectById( serviceEntry.getServiceEntryId( ) );
        if ( !entryEntity.getServiceEntryName( ).equals( serviceEntry.getServiceEntryName( ) ) ) {
            // 变化了，搜索是否存在同名
            QueryWrapper< ServiceEntryEntity > wrapper = new QueryWrapper<>( );
            wrapper.eq( "service_entry_name", serviceEntry.getServiceEntryName( ) );
            List< ServiceEntryEntity > list = serviceEntryMapper.selectList( wrapper );
            if ( !list.isEmpty( ) ) {
                throw new AppException( ResponseEnum.NAME_REPEAT_ERROR );
            }
        }

        int updated = serviceEntryMapper.updateById( serviceEntry );
        if ( updated == 1 ) {
            return true;
        }
        else {
            throw new AppException( ResponseEnum.UPDATE_ERROR );
        }
    }

    /**
     * @return
     */
    @Override
    public List< ServiceDetailVO > getBlank( ) {

        List< ServiceDetailVO > list = serviceEntryMapper.selectBlankList( );
        log.info( "list:" + list );
        return list;
    }


}




