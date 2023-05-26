package com.yw.msmp.enums;

import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public enum DeliveryStatusEnum {
    /**
     * 创建、分配、取货、配送、到达
     * CREATE, ALLOCATE, PICKUP, SHIP, ARRIVE
     */
    CREATED( "待分配" ),
    PICKUP( "取货" ),
    SHIP( "配送" ),
    ARRIVE( "到达" );

    private final String status;

    DeliveryStatusEnum( String status ) {
        this.status = status;
    }

    public static List< DeliveryStatusEnum > getAllBatchStatus( ) {
        return Arrays.asList( DeliveryStatusEnum.values( ) );
    }

    public static List< String > getChineseList( ) {
        List< String > chineseList = new ArrayList<>( );
        for ( DeliveryStatusEnum status : DeliveryStatusEnum.values( ) ) {
            chineseList.add( status.getChineseValue( ) );
        }
        return chineseList;
    }

    public static DeliveryStatusEnum fromString( String statusString ) {
        for ( DeliveryStatusEnum statusEnum : DeliveryStatusEnum.values( ) ) {
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
                return "待分配";
            case PICKUP:
                return "取货";
            case SHIP:
                return "配送";
            case ARRIVE:
                return "到达";
            default:
                throw new IllegalArgumentException( "Invalid status: " + this );
        }
    }
}
