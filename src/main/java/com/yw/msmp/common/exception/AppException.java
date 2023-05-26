package com.yw.msmp.common.exception;

import com.yw.msmp.common.result.ResponseEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 业务异常
 * // 1 项目有关的业务异常
 *
 * @author yanhaoyuwen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppException extends RuntimeException {

    private String code;
    private String message;

    public AppException( ResponseEnum responseEnum ) {
        this.code = responseEnum.getCode( );
        this.message = responseEnum.getMessage( );
    }

}
