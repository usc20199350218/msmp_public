package com.yw.msmp.enums;

import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;

import java.util.*;

public enum PayStatus {
    /**
     * 支付状态
     */
    CREATED( "创建" ),
    SUCCESS( "成功" ),
    ING( "支付中" ),
    GIVE_UP( "放弃" ),
    REFUNDING( "退款中" ),
    REFUND( "退款" );

    private final String status;

    PayStatus( String status ) {
        this.status = status;
    }

    public static List< PayStatus > getAllBatchStatus( ) {
        return Arrays.asList( PayStatus.values( ) );
    }

    public static List< String > getChineseList( ) {
        List< String > chineseList = new ArrayList<>( );
        for ( PayStatus status : PayStatus.values( ) ) {
            chineseList.add( status.getChineseValue( ) );
        }
        return chineseList;
    }

    public static PayStatus fromString( String statusString ) {
        for ( PayStatus statusEnum : PayStatus.values( ) ) {
            if ( statusEnum.getChineseValue( )
                           .equals( statusString ) ) {
                return statusEnum;
            }
        }
        throw new AppException( ResponseEnum.OPERATE_ERROR );
    }

    /**
     * 将枚举转换为Map
     *
     * @return Map
     *
     * @author yanhaoyuwen
     */
    public static Map< String, String > toMap( ) {
        Map< String, String > map = new HashMap<>( );
        for ( PayStatus status : PayStatus.values( ) ) {
            map.put( status.name( ), status.getStatus( ) );
        }
        return map;
    }

    public String getStatus( ) {
        return status;
    }

    public String getChineseValue( ) {
        switch ( this ) {
            case CREATED:
                return "创建";
            case SUCCESS:
                return "成功";
            case ING:
                return "支付中";
            case GIVE_UP:
                return "放弃";
            case REFUNDING:
                return "退款中";
            case REFUND:
                return "退款";
            default:
                throw new IllegalArgumentException( "Invalid status: " + this );
        }
    }
}
