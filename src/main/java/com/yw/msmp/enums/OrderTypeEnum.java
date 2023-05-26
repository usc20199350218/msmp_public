package com.yw.msmp.enums;
/*
 * @PACKAGE_NAME com.yw.msmp.enums
 * @author  yanhaoyuwen
 * @version  1.0
 */

import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yanhaoyuwen
 **/

public enum OrderTypeEnum {
    ONLINE( "在线" ),
    OFFLINE( "线下" );

    private final String status;

    OrderTypeEnum( String status ) {
        this.status = status;
    }

    public static List< OrderTypeEnum > getAllBatchStatus( ) {
        return Arrays.asList( OrderTypeEnum.values( ) );
    }

    public static List< String > getChineseList( ) {
        List< String > chineseList = new ArrayList<>( );
        for ( OrderTypeEnum status : OrderTypeEnum.values( ) ) {
            chineseList.add( status.getChineseValue( ) );
        }
        return chineseList;
    }

    public static OrderTypeEnum fromString( String statusString ) {
        for ( OrderTypeEnum statusEnum : OrderTypeEnum.values( ) ) {
            if ( statusEnum.getChineseValue( )
                           .equals( statusString ) ) {
                return statusEnum;
            }
        }
        throw new AppException( ResponseEnum.OPERATE_ERROR );
    }

    public String getStatus( ) {
        return status;
    }

    public String getChineseValue( ) {
        switch ( this ) {
            case ONLINE:
                return "在线";
            case OFFLINE:
                return "线下";
            default:
                throw new IllegalArgumentException( "Invalid status: " + this );
        }
    }
}
