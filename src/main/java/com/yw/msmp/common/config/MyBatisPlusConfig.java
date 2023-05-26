package com.yw.msmp.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j//日志
@Component//丢到springboot里   一定不要忘记把处理器加到Ioc容器中!
public class MyBatisPlusConfig implements MetaObjectHandler {//extends??

    @Override//插入时的填充策略
    public void insertFill( MetaObject metaObject ) {
        log.info( "==start insert ······==" );
        //setFieldValByName(java.lang.String fieldName, java.lang.Object fieldVal, org.apache.ibatis.reflection
        // .MetaObject metaObject)
        this.setFieldValByName( "createTime", new Date( ), metaObject );
        this.setFieldValByName( "modifiedTime", new Date( ), metaObject );
    }

    @Override//更新时的填充策略
    public void updateFill( MetaObject metaObject ) {
        log.info( "==start update ······==" );
        this.setFieldValByName( "modifiedTime", new Date( ), metaObject );
    }

    /**
     * 分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor( ) {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor( );
        interceptor.addInnerInterceptor( new PaginationInnerInterceptor( DbType.H2 ) );
        interceptor.addInnerInterceptor( new OptimisticLockerInnerInterceptor( ) );
        return interceptor;
    }

}
