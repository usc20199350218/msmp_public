package com.yw.msmp.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.msmp.entity.ServiceEntryEntity;
import com.yw.msmp.vo.ServiceDetailVO;

import java.util.List;

/**
 * @author yanhaoyuwen
 */
public interface ServiceEntryService extends IService< ServiceEntryEntity > {

    IPage< ServiceEntryEntity > getPage( String searchMethod, String searchText, Integer current, Integer size );

    boolean add( ServiceEntryEntity serviceEntry );

    boolean upd( ServiceEntryEntity serviceEntry );

    List< ServiceDetailVO > getBlank( );


}
