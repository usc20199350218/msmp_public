package com.yw.msmp.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderIdGenerator {

    private static final String DATE_FORMAT = "yyyyMMddHHmmss";
    private static final int RANDOM_NUM_DIGITS = 6;

    public static String generateOrderId( ) {
        SimpleDateFormat sdf = new SimpleDateFormat( DATE_FORMAT );
        String timestamp = sdf.format( new Date( ) );

        Random random = new Random( );
        int randomNumber = random.nextInt( ( int ) Math.pow( 10, RANDOM_NUM_DIGITS ) );

        return timestamp + String.format( "%d", randomNumber );
    }

}

