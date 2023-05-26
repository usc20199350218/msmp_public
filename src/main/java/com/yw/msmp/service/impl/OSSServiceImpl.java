package com.yw.msmp.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.yw.msmp.common.config.OssConfig;
import com.yw.msmp.service.OSSService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.UUID;

@Service
public class OSSServiceImpl implements OSSService {

    @Resource
    private OssConfig ossConfig;

    @Override
    public String upload( InputStream inputStream, String module, String originalFilename ) {
        // 获得oss 服务器的信息
        String endpoint = ossConfig.getEndpoint( );
        String keyid = ossConfig.getKeyid( );
        String keysecret = ossConfig.getKeysecret( );
        String bucketname = ossConfig.getBucketname( );

        //判断oss实例是否存在：如果不存在则创建，如果存在则获取
        OSS ossClient = new OSSClientBuilder( ).build( endpoint, keyid, keysecret );
        if ( !ossClient.doesBucketExist( bucketname ) ) {
            //创建bucket
            ossClient.createBucket( bucketname );
            //设置oss实例的访问权限：公共读
            ossClient.setBucketAcl( bucketname, CannedAccessControlList.PublicRead );
        }

        //构建日期路径：avatar/2020/08/11/文件名
        String folder = new DateTime( ).toString( "yyyy/MM/dd" );

        //文件名：uuid.扩展名
        String fileName = UUID.randomUUID( )
                              .toString( );
        String fileExtension = originalFilename.substring( originalFilename.lastIndexOf( "." ) );
        String key = module + "/" + folder + "/" + fileName + fileExtension;

        //文件上传至阿里云
        ossClient.putObject( ossConfig.getBucketname( ), key, inputStream );

        // 关闭OSSClient。
        ossClient.shutdown( );

        //返回url地址
        return "https://" + bucketname + "." + endpoint + "/" + key;

    }

}

