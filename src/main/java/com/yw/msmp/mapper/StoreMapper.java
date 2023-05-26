package com.yw.msmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yw.msmp.entity.StoreEntity;
import com.yw.msmp.vo.StoreVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreMapper extends BaseMapper< StoreEntity > {

    List< StoreVo > selectApply( );

}
