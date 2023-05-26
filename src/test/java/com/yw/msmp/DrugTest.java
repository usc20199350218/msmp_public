package com.yw.msmp;

import com.yw.msmp.service.DrugService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Log4j2
@SpringBootTest
public class DrugTest {

    @Resource
    private DrugService drugService;


}
