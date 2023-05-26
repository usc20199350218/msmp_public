package com.yw.msmp;

import com.yw.msmp.dto.RightDTO;
import com.yw.msmp.entity.RightEntity;
import com.yw.msmp.service.RightService;
import com.yw.msmp.vo.RightVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class RightTest {

    @Resource
    private RightService rightService;

    @Test
    void findAllRight( ) {
        List< RightEntity > allRight = rightService.findAllRight( );
        allRight.forEach( System.out::println );
    }

    @Test
    void getTree( ) {
        List< RightDTO > treeRight = rightService.findTreeRight( );
        treeRight.forEach( System.out::println );
    }

    @Test
    void getSelectTree( ) {
        List< RightDTO > rightDTOS = rightService.selectMenuByRoleIdPlus( 1 );
        System.out.println( rightDTOS );
    }

    @Test
    void getAll( ) {
        List< RightVO > allRight = rightService.getAllRight( );
        allRight.forEach( System.out::println );
    }

}
