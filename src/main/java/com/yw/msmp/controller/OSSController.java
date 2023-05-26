package com.yw.msmp.controller;

import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.service.OSSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

@RestController
@CrossOrigin( "*" )
@RequestMapping( "/api/upload" )
@Log4j2
@Api( description = "用户文件上传的" )//描述类的
public class OSSController {

    @Resource
    private OSSService ossService;

    /**
     * 文件上传
     *
     * @param file
     */
    @ApiOperation( "文件上传" )
    @PostMapping
    public R upload(
            @ApiParam( value = "文件", required = true ) @RequestParam( "file" ) MultipartFile file,
            @ApiParam( value = "模块", required = true ) @RequestParam( "module" ) String module ) throws IOException {

        // 获得上传文件的 InputStream
        InputStream inputStream = file.getInputStream( );
        log.info( "" );
        log.warn( "inputStream:::" + inputStream );
        // 获得上传文件的名字
        String originalFilename = file.getOriginalFilename( );
        log.info( "" );
        log.warn( "originalFilename :" + originalFilename );
        String uploadUrl = ossService.upload( inputStream, module, originalFilename );
        log.info( "" );
        log.warn( "uploadUrl:" + uploadUrl );
        //返回r对象
        return new R( ResponseEnum.SUCCESS, uploadUrl );

    }

}
