package com.yw.msmp;
/*
 * @PACKAGE_NAME com.yw.msmp
 * @author  yanhaoyuwen
 * @version  1.0
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * @author yanhaoyuwen
 **/

public class SeTest {

    public static void main( String[] args ) {

        List< Student > list = Arrays.asList(
                Student.builder( )
                       .name( "甲" )
                       .age( 1 )
                       .email( "jia" )
                       .build( ),
                Student.builder( )
                       .name( "乙" )
                       .age( 2 )
                       .email( "yi" )
                       .build( ),
                Student.builder( )
                       .name( "丙" )
                       .age( 3 )
                       .email( "bing" )
                       .build( ),
                Student.builder( )
                       .name( "丁" )
                       .age( 4 )
                       .email( "ding" )
                       .build( ),
                Student.builder( )
                       .name( "戊" )
                       .age( 5 )
                       .email( "wu" )
                       .build( ) );
        System.out.println( "开始" );
        for ( Student student : list ) {
            System.out.println( student );
        }
        System.out.println( "------------------" );
        for ( int i = 0; i < list.size( ); i++ ) {
            System.out.println( list.get( i ) );
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static
    class Student {

        private String name;
        private Integer age;
        private String email;

    }

}
