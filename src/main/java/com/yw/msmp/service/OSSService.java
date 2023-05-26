package com.yw.msmp.service;

import java.io.InputStream;

/**
 * @author yanhaoyuwen
 */
public interface OSSService {

    /**
     * 指定的文件上传的阿里云的oss 服务器
     *
     * @param inputStream      要上传文件的 stream
     * @param module           上传到目标服务器的文件夹
     * @param originalFilename 文件原始的名字
     * @return 文件在阿里云保存的路径
     */
    String upload( InputStream inputStream, String module, String originalFilename );

}
