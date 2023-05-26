package com.yw.msmp.common.util;

import java.util.UUID;

/**
 * 生成默认的昵称
 *
 * @作者 Forest Hoo
 * @日期 2022/9/14 10:55
 */
public class RandomNickName {

    public static String randomNickNameByUUID( ) {
        String uuid = UUID.randomUUID( ).toString( );
        return "用户" + uuid;
    }

}
