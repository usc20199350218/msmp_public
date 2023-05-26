package com.yw.msmp;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @author yanhaoyuwen
 */
@Configuration
@MapperScan( "com.yw.msmp.mapper" )
@SpringBootApplication
//@EnableEncryptableProperties
@EncryptablePropertySource( "classpath:application.yml" )
public class MsmpApplication {

    public static void main( String[] args ) {
        SpringApplication.run( MsmpApplication.class, args );
    }

}
