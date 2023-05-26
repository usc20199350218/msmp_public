package com.yw.msmp;
/*
 * @PACKAGE_NAME com.yw.msmp
 * @author  yanhaoyuwen
 * @version  1.0
 */

import com.yw.msmp.service.DrugDetailsService;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author yanhaoyuwen
 **/
@SpringBootTest
public class OnlineSaleTest {

    @Resource
    private DrugDetailsService drugDetailsService;


}
