package com.yw.msmp;

import com.yw.msmp.service.BatchService;
import com.yw.msmp.service.StoreBatchService;
import com.yw.msmp.vo.ApplyBatchVo;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@Log4j2
@SpringBootTest
public class BatchTest {

    @Resource
    private StoreBatchService storeBatchService;
    @Resource
    private BatchService batchService;

    @Test
    void check( ) {
        List< ApplyBatchVo > applyBatchVoList = storeBatchService.selectApplyByStoreId( 1 );
        log.info( "==============" );
        applyBatchVoList.forEach( System.out::println );
    }

    @Test
    void checkDate( ) {
        batchService.checkDrugBatch( );
    }

}
