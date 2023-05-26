package com.yw.msmp;

import com.yw.msmp.common.util.GenerateNum;
import com.yw.msmp.common.util.OrderIdGenerator;
import com.yw.msmp.enums.PayStatus;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
@Log4j2
public class Gid {

    @Test
    void aVoid( ) {
        String s = OrderIdGenerator.generateOrderId( );
        System.out.println( s );
    }

    @Test
    void big( ) {
        BigDecimal decimal = new BigDecimal( 12 );
        decimal = decimal.add( new BigDecimal( 2 ) );
        System.out.println( decimal );


    }

    @Test
    void gen( ) {
        int i = 0;
        while ( i < 10000 ) {
            System.out.println( i + "______" + GenerateNum.generateOrder( ) );
            i++;
        }
    }

    @Test
    void en( ) {
        System.out.println( PayStatus.SUCCESS );
    }

}
