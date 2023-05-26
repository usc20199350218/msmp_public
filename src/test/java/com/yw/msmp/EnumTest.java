package com.yw.msmp;

import com.yw.msmp.enums.BatchStatusEnum;
import com.yw.msmp.enums.PayStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class EnumTest {

    @Test
    void t1( ) {
        List< BatchStatusEnum > allBatchStatus = BatchStatusEnum
                .getAllBatchStatus( );
        allBatchStatus
                .forEach( System.out::println );
    }

    @Test
    void modelPlus( ) {
        BatchStatusEnum batchStatusEnum = BatchStatusEnum.fromString( "str" );
        System.out.println( batchStatusEnum );
    }

    @Test
    void toMap( ) {
        Map< String, String > map = PayStatus.toMap( );
        for ( String key : map.keySet( ) ) {
            String value = map.get( key );
            System.out.println( key + " : " + value );
        }
    }

}
