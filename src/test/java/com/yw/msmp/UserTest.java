package com.yw.msmp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yw.msmp.dto.SearchUserDTO;
import com.yw.msmp.dto.UserDTO;
import com.yw.msmp.entity.UserBasisEntity;
import com.yw.msmp.mapper.UserBasisMapper;
import com.yw.msmp.service.UserBasisService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Log4j2
@Component
@SpringBootTest
public class UserTest {

    @Resource
    private UserBasisService userBasisService;
    @Resource
    private UserBasisMapper userBasisMapper;

    @Test
    void login( ) {
        UserBasisEntity login = userBasisService.login( "admin", "admin" );
        System.out.println( login );
    }

    @Test
    void findUserPage( ) {
        IPage< UserDTO > userBasisEntityIPage = userBasisService.selectPage( 0, 1 );
        System.out.println( userBasisEntityIPage.getRecords( ) );
        System.out.println( userBasisEntityIPage.getCurrent( ) );
        System.out.println( userBasisEntityIPage.getPages( ) );
        System.out.println( userBasisEntityIPage.getSize( ) );
        System.out.println( userBasisEntityIPage.getTotal( ) );
    }

    @Test
    void addUser( ) {
        List< UserBasisEntity > list = userBasisService.list( );
        list.forEach( System.out::println );
    }

    @Test
    void getStaff( ) {
        List< UserBasisEntity > userBasisEntityList = userBasisService.selectStaff( );
        userBasisEntityList.forEach( System.out::println );
    }


    @Test
    void searchUser( ) {
        SearchUserDTO build = SearchUserDTO.builder( )
                                           .userGender( "男" )
                                           .userStatus( 1 )
                                           .userVip( 1 )
                                           .roleId( 1 )
                                           .searchText( "admin" )
                                           .searchMethod( "userName" )
                                           .current( 0 ).size( 5 ).build( );
        IPage< UserDTO > page = userBasisService.searchPage( build );
        log.info( page );
        log.info( page.getTotal( ) );

        log.info( page.getRecords( ) );
    }

}
