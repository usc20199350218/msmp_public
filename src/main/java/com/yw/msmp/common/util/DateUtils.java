package com.yw.msmp.common.util;
/*
 * @PACKAGE_NAME com.yw.msmp.common.util
 * @author  yanhaoyuwen
 * @version  1.0
 */

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author yanhaoyuwen
 **/
@Component
public class DateUtils {

    public static String getStringDate( String format, Integer num ) {
        // 使用Java 8的LocalDateTime类来获取当前时间
        LocalDateTime now = LocalDateTime.now( );

        // 将当前时间加上30分钟
        LocalDateTime after30Mins = now.plusMinutes( num );

        Date from = Date.from( after30Mins.atZone( ZoneId.systemDefault( ) )
                                          .toInstant( ) );

        // 创建SimpleDateFormat对象，指定输出格式为yyyy-MM-dd HH:mm:ss
        SimpleDateFormat sdf = new SimpleDateFormat( format );

        // 将Date对象格式化为指定格式的字符串
        return sdf.format( from );
    }

    public static String getStringDate( Integer num ) {
        // 使用Java 8的LocalDateTime类来获取当前时间
        LocalDateTime now = LocalDateTime.now( );

        // 将当前时间加上30分钟
        LocalDateTime after30Mins = now.plusMinutes( num );

        Date from = Date.from( after30Mins.atZone( ZoneId.systemDefault( ) )
                                          .toInstant( ) );

        // 创建SimpleDateFormat对象，指定输出格式为yyyy-MM-dd HH:mm:ss
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

        // 将Date对象格式化为指定格式的字符串
        return sdf.format( from );
    }

    public static String getStringDate( ) {
        // 使用Java 8的LocalDateTime类来获取当前时间
        LocalDateTime now = LocalDateTime.now( );

        // 将当前时间加上30分钟
        LocalDateTime after30Mins = now.plusMinutes( 30 );

        Date from = Date.from( after30Mins.atZone( ZoneId.systemDefault( ) )
                                          .toInstant( ) );

        // 创建SimpleDateFormat对象，指定输出格式为yyyy-MM-dd HH:mm:ss
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

        // 将Date对象格式化为指定格式的字符串
        return sdf.format( from );
    }

}
