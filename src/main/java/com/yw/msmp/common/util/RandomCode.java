package com.yw.msmp.common.util;

import java.util.Random;


public class RandomCode {

    /**
     * 生成4位数随机的
     *
     * @return
     */
    public static String getRandomCodeBy4( ) {
        int code = new Random( ).nextInt( 9999 );
        return String.valueOf( code );
    }

}
