package com.yw.msmp.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.R;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.dto.BrandDTO;
import com.yw.msmp.entity.BrandEntity;
import com.yw.msmp.service.BrandService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping( "/admin/brand" )
@CrossOrigin
@Log4j2
@Api( tags = "通用使用的 品牌信息 接口类" )
public class BrandController {

    @Resource
    private BrandService brandService;

    /**
     * 获取全部品牌
     *
     * @return 品牌列表
     */
    @GetMapping
    public R getAll( ) {
        return new R( ResponseEnum.SUCCESS, brandService.list( ) );
    }

    /**
     * 分页获取品牌
     *
     * @param current 当前页
     * @param size    每页显示条数
     * @return 品牌分页
     */
    @GetMapping( "/page" )
    public R getPage( int current, int size ) {
        IPage< BrandDTO > page = brandService.findPage( current, size );
        return new R( ResponseEnum.SUCCESS, page );
    }

    /**
     * 添加品牌
     *
     * @param brand 品牌
     * @return R
     */
    @PostMapping
    public R addDrug( BrandEntity brand ) {
        log.info( "添加" + brand );
        boolean save = brandService.save( brand );
        if ( !save ) {
            throw new AppException( ResponseEnum.ADD_ERROR );
        }
        return new R( ResponseEnum.SUCCESS, save );
    }

    /**
     * 更新品牌
     *
     * @param brand 品牌
     * @return R
     */
    @PutMapping
    public R changeDrug( BrandEntity brand ) {
        log.info( "更新" + brand );
        boolean b = brandService.updateById( brand );
        return new R( ResponseEnum.SUCCESS, b );
    }

    /**
     * 删除品牌
     *
     * @param brandId 品牌id
     * @return R
     */
    @DeleteMapping( "{brandId}" )
    public R delDrug( @PathVariable( "brandId" ) Integer brandId ) {
        boolean b = brandService.removeById( brandId );
        if ( !b ) {
            throw new AppException( ResponseEnum.DEL_ERROR );
        }
        return new R( ResponseEnum.SUCCESS, b );
    }

}
