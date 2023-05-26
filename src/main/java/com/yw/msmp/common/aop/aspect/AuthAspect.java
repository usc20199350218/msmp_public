package com.yw.msmp.common.aop.aspect;


import com.yw.msmp.common.aop.anno.CheckActionRignt;
import com.yw.msmp.common.config.JWTConfig;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.dto.LoginDTO;
import com.yw.msmp.service.BatchService;
import com.yw.msmp.service.RightService;
import com.yw.msmp.service.ShoppingCartService;
import com.yw.msmp.service.StoreBatchService;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author yanhaoyuwen
 */
@Component
@Aspect
@Log4j2
public class AuthAspect {

    @Resource
    private JWTConfig jwtConfig;
    @Resource
    private RightService rightService;

    @Resource
    private BatchService batchService;

    @Resource
    private StoreBatchService storeBatchService;

    @Resource
    private ShoppingCartService shoppingCartService;

    /**
     * 进入之前需要做登录验证的增强
     */
    @Before( "@annotation(com.yw.msmp.common.aop.anno.CheckLogin)" )
    public void befroeCheckLogin( JoinPoint joinpoint ) {
        log.info( "开始检查是否登录" );
        // 1 从request 中获得token
        // 1.1 获得当前的token
        ServletRequestAttributes requestAttributes =
                ( ServletRequestAttributes ) RequestContextHolder.getRequestAttributes( );
        HttpServletRequest request = requestAttributes.getRequest( );
        String token = request.getHeader( "X-Token" );
        log.info( "获取的token为：" + token );
        // 2 验证token 是否为空  抛出异常
        if ( StringUtils.isEmpty( token ) ) {
            log.warn( "token为空，没有登录数据" );
            throw new AppException( ResponseEnum.NO_LOGIN );
        }
        // 3 验证token
        log.info( "开始验证token" );
        LoginDTO loginDTO = jwtConfig.checkJwt( token );
        log.info( "验证token成功" );
        // 4 把roleid 拿出 继续往后传
        Integer roleid = loginDTO.getRoleId( );
        // RequestContextHolder.getRequestAttributes().
        //        RequestContextHolder.getRequestAttributes ().setAttribute ("roleId", roleid, 0);

        //        LoginDto loginDto = jwtConfig.checkJwt(token);//如果token不为null就解析,如果解析抛出异常的话,还是去异常增强
        //        Integer roleid = loginDto.getRoleid();      //如果走得到这一句,说明没有抛出异常
        Integer id = loginDTO.getUserId( );
        //        Long id = loginDTO.getUserId( );
        String userName = loginDTO.getUserName( );
        /*
        拿到roleId了以后是不是就往下传
         */
        request.setAttribute( "roleId", roleid );
        request.setAttribute( "id", id );
        request.setAttribute( "userName", userName );
    }

    // 加上这个注解方法 执行之前 必须增强
    @Before( "@annotation(com.yw.msmp.common.aop.anno.CheckActionRignt)" )
    public void beaforeCheckActionRignt( JoinPoint joinpoint ) {
        // 获取注解的权限码，
        // 1 获得切入点方法 如果要执行 必须拥有的权限码   获得 code  String
        MethodSignature signature = ( MethodSignature ) joinpoint.getSignature( );
        Method method = signature.getMethod( );
        CheckActionRignt annotation = method.getAnnotation( CheckActionRignt.class );
        // 要执行切入点方法 必须具备的权限码
        String code = annotation.code( );

        // 2 从请求头中获得 token
        // 1.1 获得当前的清欠
        ServletRequestAttributes requestAttributes =
                ( ServletRequestAttributes ) RequestContextHolder.getRequestAttributes( );
        HttpServletRequest request = requestAttributes.getRequest( );
        String token = request.getHeader( "X-Token" );

        // 2 验证token 是否为空  抛出异常
        if ( StringUtils.isEmpty( token ) ) {
            throw new AppException( ResponseEnum.NO_LOGIN );
        }
        // 3 验证token
        LoginDTO loginDTO = jwtConfig.checkJwt( token );
        // 4 把roleid 拿出 继续往后传
        Integer roleId = loginDTO.getRoleId( );

        // 5 查询出该角色id  拥有的动作权限码  codes   String[]
        List< String > codes = rightService.getRightCodesByRoleId( roleId );
        // 6  判读 第5步的数组 是否包含 第一步的code
        // 6.1 如果没有就跑出异常
        if ( !codes.contains( code ) ) {
            throw new AppException( ResponseEnum.HAS_NO_ACTIONRIGHT );
        }
    }

    @Before( "@annotation(com.yw.msmp.common.aop.anno.CheckDrugBatch)" )
    public void beforeCheckDrugBatch( JoinPoint joinpoint ) {
        log.info( "开始检查仓储是否过期" );
        batchService.checkDrugBatch( );
    }

    @Before( "@annotation(com.yw.msmp.common.aop.anno.CheckStoreBatch)" )
    public void beforeCheckStoreBatch( JoinPoint joinpoint ) {
        storeBatchService.checkStoreBatch( );
    }

    @Before( "@annotation(com.yw.msmp.common.aop.anno.CheckEfficient)" )
    public void beforeCheckEfficient( JoinPoint joinpoint ) {
        ServletRequestAttributes requestAttributes =
                ( ServletRequestAttributes ) RequestContextHolder.getRequestAttributes( );
        HttpServletRequest request = requestAttributes.getRequest( );
        String token = request.getHeader( "X-Token" );
        log.info( "获取的token为：" + token );
        // 2 验证token 是否为空  抛出异常
        if ( StringUtils.isEmpty( token ) ) {
            log.warn( "token为空，没有登录数据" );
            throw new AppException( ResponseEnum.NO_LOGIN );
        }
        // 3 验证token
        log.info( "开始验证token" );
        LoginDTO loginDTO = jwtConfig.checkJwt( token );
        log.info( "验证token成功" );
        // 4 把userId 拿出 继续往后传
        Integer userId = loginDTO.getUserId( );

        shoppingCartService.checkEfficient( userId );
    }

}
