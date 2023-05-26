package com.yw.msmp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yw.msmp.entity.OrderEntity;
import com.yw.msmp.entity.StoreEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderMapper extends BaseMapper< OrderEntity > {

    List< StoreEntity > selectByUser( @Param( "userId" ) Integer userId );

    List< OrderEntity > searchOrder( @Param( "userId" ) Integer userId,
                                     @Param( "orderType" ) String orderType,
                                     @Param( "storeId" ) Integer storeId,
                                     @Param( "payStatus" ) String payStatus,
                                     @Param( "payment" ) String payment,
                                     @Param( "searchMethod" ) String searchMethod,
                                     @Param( "searchText" ) String searchText );


    IPage< OrderEntity > searchOrderPage( @Param( "userId" ) Integer userId,
                                          @Param( "orderType" ) String orderType,
                                          @Param( "storeId" ) Integer storeId,
                                          @Param( "payStatus" ) String payStatus,
                                          @Param( "payment" ) String payment,
                                          @Param( "startDate" ) Date startDate,
                                          @Param( "endDate" ) Date endDate,
                                          @Param( "searchMethod" ) String searchMethod,
                                          @Param( "searchText" ) String searchText,
                                          Page< OrderEntity > orderEntityPage );

}
