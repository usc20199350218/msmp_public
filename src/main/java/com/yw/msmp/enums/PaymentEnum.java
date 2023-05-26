package com.yw.msmp.enums;

import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;

import java.util.*;

public enum PaymentEnum {
    /**
     *
     */

    CASH( "现金" ),
    ALIPAY( "支付宝" ),
    WECHATPAY( "微信" );

    private final String status;

    PaymentEnum( String status ) {
        this.status = status;
    }

    public static List< PaymentEnum > getAllBatchStatus( ) {
        return Arrays.asList( PaymentEnum.values( ) );
    }

    public static List< String > getChineseList( ) {
        List< String > chineseList = new ArrayList<>( );
        for ( PaymentEnum status : PaymentEnum.values( ) ) {
            chineseList.add( status.getChineseValue( ) );
        }
        return chineseList;
    }

    public static PaymentEnum fromString( String statusString ) {
        for ( PaymentEnum statusEnum : PaymentEnum.values( ) ) {
            if ( statusEnum.getChineseValue( )
                           .equals( statusString ) ) {
                return statusEnum;
            }
        }
        throw new AppException( ResponseEnum.OPERATE_ERROR );
    }

    public static Map< String, String > toMap( ) {
        Map< String, String > map = new HashMap<>( );
        for ( PaymentEnum status : PaymentEnum.values( ) ) {
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
                return "现金";
            case ALIPAY:
                return "支付宝";
            case WECHATPAY:
                return "微信";
            default:
                throw new IllegalArgumentException( "Invalid status: " + this );
        }
    }
}
