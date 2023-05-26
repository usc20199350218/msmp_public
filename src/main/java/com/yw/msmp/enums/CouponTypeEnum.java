package com.yw.msmp.enums;
/*
 * @PACKAGE_NAME com.yw.msmp.enums
 * @author  yanhaoyuwen
 * @version  1.0
 */

import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;

import java.util.*;

/**
 * @author yanhaoyuwen
 **/
public enum CouponTypeEnum {
    /**
     * 1）现金券：不限制订单金额，可以直接使用。
     * <p>
     * 2）满减券：订单金额需要满足一定的最低额度才可使用，例如：满100减10元优惠券。
     * <p>
     * 3）折扣券：消费者持券消费可享受消费折扣。
     */
    CASH( "现金券" ),
    FULL( "满减券" ),
    DISCOUNT( "折扣券" );
    private final String status;

    CouponTypeEnum( String status ) {
        this.status = status;
    }

    public static List< CouponTypeEnum > getAllBatchStatus( ) {
        return Arrays.asList( CouponTypeEnum.values( ) );
    }

    public static List< String > getChineseList( ) {
        List< String > chineseList = new ArrayList<>( );
        for ( CouponTypeEnum status : CouponTypeEnum.values( ) ) {
            chineseList.add( status.getChineseValue( ) );
        }
        return chineseList;
    }

    public static CouponTypeEnum fromString( String statusString ) {
        for ( CouponTypeEnum statusEnum : CouponTypeEnum.values( ) ) {
            if ( statusEnum.getChineseValue( )
                           .equals( statusString ) ) {
                return statusEnum;
            }
        }
        throw new AppException( ResponseEnum.OPERATE_ERROR );
    }

    public static Map< String, String > toMap( ) {
        Map< String, String > map = new HashMap<>( );
        for ( CouponTypeEnum status : CouponTypeEnum.values( ) ) {
            map.put( status.name( ), status.getStatus( ) );
        }
        return map;
    }

    public String getStatus( ) {
        return status;
    }

    public String getChineseValue( ) {
        switch ( this ) {
            case CASH:
                return "现金券";
            case FULL:
                return "满减券";
            case DISCOUNT:
                return "折扣券";
            default:
                throw new IllegalArgumentException( "Invalid status: " + this );
        }
    }
}
