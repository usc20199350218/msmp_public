package com.yw.msmp.service.impl;
/*
 * @PACKAGE_NAME com.yw.msmp.service.impl
 * @author  yanhaoyuwen
 * @version  1.0
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yw.msmp.entity.AddressEntity;
import com.yw.msmp.mapper.AddressMapper;
import com.yw.msmp.service.AddressService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yanhaoyuwen
 **/
@Service
@Log4j2
public class AddressServiceImpl extends ServiceImpl< AddressMapper, AddressEntity >
        implements AddressService {

    @Resource
    private AddressMapper addressMapper;

    /**
     * @param userId 用户ID
     * @return 地址集合
     */
    @Override
    public List< AddressEntity > searchAddress( String userId ) {
        QueryWrapper< AddressEntity > wrapper = new QueryWrapper<>( );
        wrapper.eq( "user_id", userId );

        List< AddressEntity > list = addressMapper.selectList( wrapper );
        log.info( "查询的地址信息:" + list );
        return list;
    }

    /**
     * @param address 要添加的地址实体
     * @return boolean 操作结果
     */
    @Override
    public boolean addNAddress( AddressEntity address ) {
        // 添加地址是否为默认
        if ( 1 == address.getIsDefault( ) ) {
            // 默认地址，更新
            QueryWrapper< AddressEntity > wrapper = new QueryWrapper<>( );
            wrapper.eq( "user_id", address.getUserId( ) );
            addressMapper.update( AddressEntity.builder( ).isDefault( 0 ).build( ), wrapper );
        }
        int insert = addressMapper.insert( address );
        return insert == 1;
    }

    /**
     * @param address 要更新的地址实体
     * @return boolean 操作结果
     */
    @Override
    public boolean updNById( AddressEntity address ) {

        // 更新数据为默认
        if ( 1 == address.getIsDefault( ) ) {
            // 检查默认地址是变化的还是继承的
            AddressEntity addressEntity = addressMapper.selectById( address.getAddressId( ) );
            if ( !addressEntity.getIsDefault( ).equals( address.getIsDefault( ) ) ) {
                // 默认地址是变更而来，更新
                QueryWrapper< AddressEntity > wrapper = new QueryWrapper<>( );
                wrapper.eq( "user_id", address.getUserId( ) );
                addressMapper.update( AddressEntity.builder( ).isDefault( 0 ).build( ), wrapper );
            }
        }
        addressMapper.updateById( address );
        return true;
    }

}
