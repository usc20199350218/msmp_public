package com.yw.msmp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.entity.TypeEntity;
import com.yw.msmp.mapper.TypeMapper;
import com.yw.msmp.service.TypeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Log4j2
@Service
public class TypeServiceImpl extends ServiceImpl< TypeMapper, TypeEntity > implements TypeService {

    @Resource
    private TypeMapper typeMapper;

    /**
     * @param typeEntity 添加
     */
    @Override
    public void add( TypeEntity typeEntity ) {
        int insert =
                typeMapper.insert( typeEntity );
        if ( insert != 1 ) {
            throw new AppException( ResponseEnum.ADD_ERROR );
        }
    }

    /**
     * @param typeEntity 更新
     */
    @Override
    public void upd( TypeEntity typeEntity ) {
        int i = typeMapper.updateById( typeEntity );
        if ( i != 1 ) {
            throw new AppException( ResponseEnum.UPDATE_ERROR );
        }
    }

    /**
     * @param typeId 删除的Id
     */
    @Override
    public void del( Integer typeId ) {
        int i = typeMapper.deleteById( typeId );
        if ( i != 1 ) {
            throw new AppException( ResponseEnum.DEL_ERROR );
        }
    }

}
