package com.yw.msmp;

import com.yw.msmp.entity.UserBasisEntity;
import com.yw.msmp.mapper.UserBasisMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class MsmpApplicationTests {

    @Resource
    private UserBasisMapper userBasisMapper;

    @Test
    void contextLoads( ) {
        List< UserBasisEntity > userBasisEntities = userBasisMapper.selectList( null );
        userBasisEntities.forEach( System.out::println );
    }

}
