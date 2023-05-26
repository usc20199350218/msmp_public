package com.yw.msmp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yw.msmp.service.PayCommonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class PayCommonServiceImpl implements PayCommonService {
    //    @Resource
    //    private OrderDetailMapper orderDetailMapper;
    //    @Resource
    //    private ProductCourseMapper productCourseMapper;

    /**
     * 支付回调封装
     *
     * @param jsonObject
     * @param userId
     * @param orderNumber
     * @param transaction_id
     * @param paymethod
     */
    @Override
    public void payproductcourse( JSONObject jsonObject,
                                  String userId,
                                  String orderNumber,
                                  String transaction_id,
                                  String paymethod ) {
        //        String courseId = jsonObject.getString( "courseId" );
        //        String money = jsonObject.getString( "money" );
        //        ProductCourse productCourse = productCourseMapper.selectById( courseId );
        //        if ( productCourse == null ) {
        //            log.info( "【" + ( paymethod.equals( "2" ) ? "微信" : "支付宝" ) + "】你支付的课程被删除了：{}", courseId );
        //            return;
        //        }
        //        OrderDetail orderDetail = new OrderDetail( );
        //        orderDetail.setUserid( userId );
        //        orderDetail.setCourseid( courseId );
        //        orderDetail.setUsername( "飞哥" );
        //        orderDetail.setPaymethod( paymethod );
        //        orderDetail.setCoursetitle( productCourse.getTitle( ) );
        //        orderDetail.setCourseimg( productCourse.getImg( ) );
        //        orderDetail.setOrdernumber( orderNumber );
        //        orderDetail.setTradeno( transaction_id );
        //        orderDetail.setPrice( money == null ? "0.01" : money );
        //        orderDetailMapper.insert( orderDetail );
    }

}
