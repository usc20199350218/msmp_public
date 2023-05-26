package com.yw.msmp.enums;

import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;

import java.util.*;

/*
 * @PACKAGE_NAME com.yw.msmp.enums
 * @author  yanhaoyuwen
 * @version  1.0
 */
public enum CouponStatusEnum {
    /**
     * 未使用：发放后的正常状态，可以正常使用。
     * <p>
     * 占用中：订单使用优惠券，已经下单，但是未付款，优惠券占用中不可使用，根据订单后续状态转化状态，订单失效转为未使用。
     * <p>
     * 已使用：订单支付成功后标记优惠券已使用。
     * <p>
     * 已过期：优惠券超期未使用失效。
     */
    UNUSED( "未使用" ),
    OCCUPYING( "占用中" ),
    USED( "已使用" ),
    EXPIRED( "已过期" );

    private final String status;

    CouponStatusEnum( String status ) {
        this.status = status;
    }

    public static List< CouponStatusEnum > getAllBatchStatus( ) {
        return Arrays.asList( CouponStatusEnum.values( ) );
    }

    public static List< String > getChineseList( ) {
        List< String > chineseList = new ArrayList<>( );
        for ( CouponStatusEnum status : CouponStatusEnum.values( ) ) {
            chineseList.add( status.getChineseValue( ) );
        }
        return chineseList;
    }

    public static CouponStatusEnum fromString( String statusString ) {
        for ( CouponStatusEnum statusEnum : CouponStatusEnum.values( ) ) {
            if ( statusEnum.getChineseValue( )
                           .equals( statusString ) ) {
                return statusEnum;
            }
        }
        throw new AppException( ResponseEnum.OPERATE_ERROR );
    }

    public static Map< String, String > toMap( ) {
        Map< String, String > map = new HashMap<>( );
        for ( CouponStatusEnum status : CouponStatusEnum.values( ) ) {
            map.put( status.name( ), status.getStatus( ) );
        }
        return map;
    }

    public String getStatus( ) {
        return status;
    }

    public String getChineseValue( ) {
        switch ( this ) {
            case UNUSED:
                return "未使用";
            case OCCUPYING:
                return "占用中";
            case USED:
                return "已使用";
            case EXPIRED:
                return "已过期";
            default:
                throw new IllegalArgumentException( "Invalid status: " + this );
        }
    }

}
