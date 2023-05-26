package com.yw.msmp.controller;

import com.yw.msmp.common.ModeEntity;
import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/admin/mode" )
@CrossOrigin( "*" )
@Log4j2
public class ModeController {

    @GetMapping( "/get1" )
    public R get1( Integer val1 ) {
        log.info( val1 );
        return new R( ResponseEnum.SUCCESS, val1 );
    }

    @GetMapping( "/get2" )
    public R get2( boolean val2 ) {
        log.info( val2 );
        return new R( ResponseEnum.SUCCESS, val2 );
    }

    @GetMapping( "/get3" )
    public R get3( double val3 ) {
        log.info( val3 );
        return new R( ResponseEnum.SUCCESS, val3 );
    }

    @GetMapping( "/get4" )
    public R get4( String val4 ) {
        log.info( val4 );
        return new R( ResponseEnum.SUCCESS, val4 );
    }

    @GetMapping( "/get5" )
    public R get5( ModeEntity modeEntity ) {
        log.info( modeEntity );
        return new R( ResponseEnum.SUCCESS, modeEntity );
    }

    @GetMapping( "/get6" )
    public R get6( List< ModeEntity > modeEntity ) {
        log.info( modeEntity );
        return new R( ResponseEnum.SUCCESS, modeEntity );
    }

    @PostMapping( "/post1" )
    public R post1( Integer val1 ) {
        log.info( val1 );
        return new R( ResponseEnum.SUCCESS, val1 );
    }

    @PostMapping( "/get2" )
    public R post2( boolean val2 ) {
        log.info( val2 );
        return new R( ResponseEnum.SUCCESS, val2 );
    }

    @PostMapping( "/get3" )
    public R post3( double val3 ) {
        log.info( val3 );
        return new R( ResponseEnum.SUCCESS, val3 );
    }

    @PostMapping( "/get4" )
    public R post4( String val4 ) {
        log.info( val4 );
        return new R( ResponseEnum.SUCCESS, val4 );
    }

    @PostMapping( "/get5" )
    public R post5( ModeEntity modeEntity ) {
        log.info( modeEntity );
        return new R( ResponseEnum.SUCCESS, modeEntity );
    }

    @PostMapping( "/get6" )
    public R post6( List< ModeEntity > modeEntity ) {
        log.info( modeEntity );
        return new R( ResponseEnum.SUCCESS, modeEntity );
    }

}
