package com.yw.msmp.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yw.msmp.entity.ShoppingCartEntity;
import com.yw.msmp.vo.ScDrugVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author yanhaoyuwen
 */
@Repository
public interface ShoppingCartMapper extends BaseMapper< ShoppingCartEntity > {

    List< ScDrugVO > selectUserCart( @Param( "userId" ) Integer userId );

    List< ScDrugVO > search( Integer userId, Integer storeId, String searchMethod, String searchText );

    /**
     * @param userId 用户id
     * @return 购物车数量
     */
    Integer getUserNumber( @Param( "userId" ) Integer userId );

}
