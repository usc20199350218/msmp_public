package com.yw.msmp.common.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一的返回结果
 * 不管正常 还是 异常 都返回给前端R
 *
 * @author yanhaoyuwen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class R< T > {

    private String code;
    private String message;
    private T data;

    public R( ResponseEnum responseEnum, T data ) {
        this.code = responseEnum.getCode( );
        this.message = responseEnum.getMessage( );
        this.data = data;
    }

}
