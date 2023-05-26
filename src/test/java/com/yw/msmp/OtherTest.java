package com.yw.msmp;
/*
 * @PACKAGE_NAME com.yw.msmp
 * @author  yanhaoyuwen
 * @version  1.0
 */

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author yanhaoyuwen
 **/
@SpringBootTest
public class OtherTest {

    @Test
    public void aVoid( ) {
        List< Stu > myList = Arrays.asList( Stu.builder( )
                                               .name( "zs" )
                                               .phone( "123" )
                                               .age( 11 )
                                               .email( "11" )
                                               .build( ), Stu.builder( )
                                                             .name( "zs" )
                                                             .phone( "123" )
                                                             .age( 12 )
                                                             .email( "222" )
                                                             .build( ), Stu.builder( )
                                                                           .name( "zs" )
                                                                           .phone( "345" )
                                                                           .age( 13 )
                                                                           .email( "34" )
                                                                           .build( ), Stu.builder( )
                                                                                         .name( "ww" )
                                                                                         .phone( "1245" )
                                                                                         .age( 14 )
                                                                                         .email( "35" )
                                                                                         .build( ) );
        Stream< Stu > myStream = myList.stream( );
        Stream< String > distinctStream = myStream.map( Stu::getName )
                                                  .distinct( );
        //        List< String > distinctList = distinctStream.collect( Collectors.toList( ) );
        //        System.out.println( "Distinct list: " + distinctList );
    }

}





