package com.yw.msmp.common.exception;

import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yanhaoyuwen
 */
@ControllerAdvice  // controller 增强
@Log4j2
public class ControllerExceptionHandler {

    /**
     *  如果controller 抛出了 AppException  那么统一跳到这个方法中处理
     *
     */
    //    @ExceptionHandler(AppException.class)
    //    public String appExceptionHandler(AppException ex, HttpServletRequest request){
    //        System.out.println("轻轻我来了。。。");
    //        request.setAttribute("code",ex.getCode());
    //        request.setAttribute("message",ex.getMessage());
    //        return "error";
    //    }
    //
    //    @ExceptionHandler(Exception.class)
    //    public String exceptionHandler(Exception ex, HttpServletRequest request){
    //        System.out.println("轻轻我来了。。。");
    //        request.setAttribute("code", ResponseEnum.SYSTEM_ERROR.getCode());
    //        request.setAttribute("message",ResponseEnum.SYSTEM_ERROR.getMessage());
    //        return "error";
    //    }

    /**
     * 如果controller 抛出了 SignatureException  那么统一跳到这个方法中处理
     */
    @ExceptionHandler( SignatureException.class )
    @ResponseBody
    public R appExceptionHandler( SignatureException ex,
                                  HttpServletRequest request ) {
        ex.printStackTrace( );
        return new R( ResponseEnum.TOKEN_INVALID, null );
    }

    @ExceptionHandler( DuplicateKeyException.class )
    @ResponseBody
    public R SQLIntegrityConstraintViolationException( DuplicateKeyException ex, HttpServletRequest request ) {
        ex.printStackTrace( );
        return new R( ResponseEnum.Repeating_Field_ERROR, null );
    }

    @ExceptionHandler( ExpiredJwtException.class )
    @ResponseBody
    public R appExceptionHandler( ExpiredJwtException ex, HttpServletRequest request ) {
        ex.printStackTrace( );
        ex.printStackTrace( );
        return new R( ResponseEnum.TOKEN_EXPIRE, null );
    }

    /**
     * 令牌非法的异常
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler( AppException.class )
    @ResponseBody
    public R appExceptionHandler( AppException ex,
                                  HttpServletRequest request ) {
        ex.printStackTrace( );
        return new R( ex.getCode( ), ex.getMessage( ), null );
    }

    @ExceptionHandler( Exception.class )
    @ResponseBody
    public R exceptionHandler( Exception ex, HttpServletRequest request ) {
        ex.printStackTrace( );
        log.warn( "request" + request );
        log.info( "" );
        log.warn( "ex" + ex );
        return new R( ResponseEnum.SYSTEM_ERROR, null );
    }

}
