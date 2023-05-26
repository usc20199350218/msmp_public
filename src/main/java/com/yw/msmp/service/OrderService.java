package com.yw.msmp.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yw.msmp.dto.AlipayDTO;
import com.yw.msmp.dto.OnlineOrderDTO;
import com.yw.msmp.dto.SearchOrderDTO;
import com.yw.msmp.entity.OrderDetailEntity;
import com.yw.msmp.entity.OrderEntity;

import java.util.List;

public interface OrderService extends IService< OrderEntity > {

    void cancel( Integer orderId );

    /**
     * 线下结算
     *
     * @param userId  用户id
     * @param payment 方式
     * @param orderId 订单id
     */
    void continueOrder( Integer userId, String payment, Integer orderId );

    boolean examineOrderId( Integer orderId );

    AlipayTradePagePayResponse createOnlineOrder( OnlineOrderDTO onlineOrderDTO );

    /**
     * 生成在线订单
     *
     * @param onlineOrderDTO 订单信息
     * @return String
     */
    AlipayDTO createOnlineOrderPlus( OnlineOrderDTO onlineOrderDTO );

    Boolean examineStatus( String orderNum );

    /**
     * 根据类型获取所有的订单
     *
     * @param orderType 订单类型
     * @return List<OrderEntity>
     */
    List< OrderEntity > getAllList( String orderType );

    /**
     * 根据用户id和订单类型获取所有的订单
     *
     * @param userId         用户id
     * @param searchOrderDTO 订单类型
     * @return List<OrderEntity>
     */
    IPage< OrderEntity > searchOrder( Integer userId, SearchOrderDTO searchOrderDTO );

    /**
     * 根据订单id获取订单详情
     *
     * @param orderId 订单id
     * @return OrderDetailEntity
     */
    List< OrderDetailEntity > getOrderDetail( Integer orderId );

    boolean refunding( Integer orderId ) throws AlipayApiException;

}



































