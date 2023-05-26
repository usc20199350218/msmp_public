package com.yw.msmp;
/*
 * @PACKAGE_NAME com.yw.msmp
 * @author  yanhaoyuwen
 * @version  1.0
 */

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author yanhaoyuwen
 **/
@SpringBootTest
public class TimeTest {

    @Test
    public void dou( ) {
        // 使用Java 8的LocalDateTime类来获取当前时间
        LocalDateTime now = LocalDateTime.now( );

        // 将当前时间加上30分钟
        LocalDateTime after30Mins = now.plusMinutes( 30 );

        Date from = Date.from( after30Mins.atZone( ZoneId.systemDefault( ) )
                                          .toInstant( ) );

        // 创建SimpleDateFormat对象，指定输出格式为yyyy-MM-dd HH:mm:ss
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

        // 将Date对象格式化为指定格式的字符串
        String output = sdf.format( from );

        System.out.println( output );
    }

}
