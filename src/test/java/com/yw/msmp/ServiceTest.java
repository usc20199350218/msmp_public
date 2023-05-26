package com.yw.msmp;
/*
 * @PACKAGE_NAME com.yw.msmp
 * @author  yanhaoyuwen
 * @version  1.0
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yw.msmp.entity.ServiceEntity;
import com.yw.msmp.service.ServiceService;
import com.yw.msmp.vo.ServiceVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author yanhaoyuwen
 **/
@SpringBootTest
class ServiceTest {

    @Resource
    private ServiceService services;

    // 使用代码拼接完成map来提供
    @Test
    void Te( ) {
        Integer userId = 1;
        ServiceVO myMap = services.getLastVo( userId );
        System.out.println( myMap );
    }

    @Test
    void DFy( ) {
        IPage< ServiceEntity > myPage = services.getMyPage( 1, 0, 2 );
        System.out.println( myPage );
        System.out.println( myPage.getCurrent( ) );
        System.out.println( myPage.getSize( ) );
        System.out.println( myPage.getRecords( ) );
        System.out.println( myPage.getTotal( ) );
    }

}
