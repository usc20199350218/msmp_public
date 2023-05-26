package com.yw.msmp.enums;

import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum BatchStatusEnum {
    /**
     *
     */
    CREATED( "创建" ),
    NORMAL_PURCHASE( "进货" ),
    SOLD( "正常" ),
    SOLD_OUT( "售完" ),
    EXPIRED( "过期（待处理）" ),
    EXPIRED_PROCESSED( "过期（已处理）" ),
    FORBIDDEN( "禁止" );

    private final String status;

    BatchStatusEnum( String status ) {
        this.status = status;
    }

    public static List< BatchStatusEnum > getAllBatchStatus( ) {
        return Arrays.asList( BatchStatusEnum.values( ) );
    }

    public static List< String > getChineseList( ) {
        List< String > chineseList = new ArrayList<>( );
        for ( BatchStatusEnum status : BatchStatusEnum.values( ) ) {
            chineseList.add( status.getChineseValue( ) );
        }
        return chineseList;
    }

    public static BatchStatusEnum fromString( String statusString ) {
        for ( BatchStatusEnum statusEnum : BatchStatusEnum.values( ) ) {
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
            case CREATED:
                return "创建";
            case NORMAL_PURCHASE:
                return "进货";
            case SOLD:
                return "正常";
            case SOLD_OUT:
                return "售完";
            case EXPIRED:
                return "过期（待处理）";
            case EXPIRED_PROCESSED:
                return "过期（已处理）";
            case FORBIDDEN:
                return "禁止";
            default:
                throw new IllegalArgumentException( "Invalid status: " + this );
        }
    }
}
