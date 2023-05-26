package com.yw.msmp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.msmp.entity.TypeEntity;

public interface TypeService extends IService< TypeEntity > {

    /**
     * @param typeEntity 添加
     */
    void add( TypeEntity typeEntity );

    /**
     * @param typeEntity 更新
     */
    void upd( TypeEntity typeEntity );

    /**
     * @param typeId 删除的Id
     */
    void del( Integer typeId );

}
