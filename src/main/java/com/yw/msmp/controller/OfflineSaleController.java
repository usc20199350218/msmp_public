package com.yw.msmp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.service.StoreBatchService;
import com.yw.msmp.vo.OfflineSaleVO;
import com.yw.msmp.vo.OrderVo;
import com.yw.msmp.vo.PrepareVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping( "/admin/offline" )
@CrossOrigin( "*" )
@Log4j2
public class OfflineSaleController {

    @Resource
    private StoreBatchService storeBatchService;

    @GetMapping
    public R selectDrug( String mode, String input, Integer storeId, Integer current, Integer size ) {
        IPage< OfflineSaleVO > page = storeBatchService.selectByMode( mode, input, storeId, current, size );
        log.info( page );
        return new R( ResponseEnum.SUCCESS, page );
    }

    //    @PostMapping
    //    public R addOrder( @RequestBody List< Map< String, String > > tableData ) {
    //        log.info( tableData );
    //        return new R( ResponseEnum.SUCCESS, tableData );
    //    }

    //    @PostMapping
    //    public R addOrder(@RequestParam  List< OfflineSaleVO > offlineSaleVOList ) {
    //        log.info( offlineSaleVOList );
    //        return new R( ResponseEnum.SUCCESS, offlineSaleVOList );
    //    }

    @PostMapping( value = "/corder" )
    public R post2( OrderVo orderVo ) {
        log.info( "orderVo:" + orderVo );
        // 拿到id 减少库存，生成订单id，生成详情订单
        // 返回订单id，校验金额（预计金额）
        PrepareVO prepareVO = storeBatchService.addOrder( orderVo );
        return new R( ResponseEnum.SUCCESS, prepareVO );
    }

}
