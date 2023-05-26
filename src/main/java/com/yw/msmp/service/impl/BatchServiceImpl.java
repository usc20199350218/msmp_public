package com.yw.msmp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.dto.BatchDTO;
import com.yw.msmp.entity.BatchEntity;
import com.yw.msmp.entity.DrugDetailsEntity;
import com.yw.msmp.entity.SearchDrugDTO;
import com.yw.msmp.enums.BatchStatusEnum;
import com.yw.msmp.mapper.BatchMapper;
import com.yw.msmp.mapper.DrugDetailsMapper;
import com.yw.msmp.service.BatchService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Log4j2
@Service
public class BatchServiceImpl extends ServiceImpl< BatchMapper, BatchEntity > implements BatchService {

    @Resource
    private BatchMapper batchMapper;

    @Resource
    private DrugDetailsMapper drugDetailsMapper;

    /**
     * 取消状态在于已经展示了其他数据，所有不再设置状态
     *
     * @param current 位置
     * @param size    大小
     * @return 分页数据
     */
    @Override
    public IPage< BatchDTO > findPageOverAll( int current, int size
                                              //            , String batchStatus
                                            ) {
        Page< BatchDTO > batchDTOPage = new Page<>( current, size );
        //        log.info( "查询的状态为：" + batchStatus );
        //        String kong = "kong";
        //        if ( !Objects.equals( batchStatus, kong ) ) {
        //            log.info( "此时不为空，为batchStatus：" + batchStatus );
        //            // 对状态进行一个转换——string——>BatchStatusEnum
        //            IPage< BatchDTO > page = batchMapper.selectMyPage( batchDTOPage, BatchStatusEnum.fromString(
        //            batchStatus ).toString( ) );
        //            log.info( "drug分页查询:" + page.getRecords( ) );
        //            return page;
        //        } else {
        //            IPage< BatchDTO > page = batchMapper.selectMyPage( batchDTOPage, BatchStatusEnum.SOLD.toString( ) );
        IPage< BatchDTO > page = batchMapper.selectMyPage( batchDTOPage );
        log.info( "drug分页查询:" + page.getRecords( ) );
        return page;
        //        }
    }

    @Override
    public void addBatch( BatchEntity batchEntity ) {
        // 进货前药品的状态检查
        DrugDetailsEntity drugDetails = drugDetailsMapper.selectById( batchEntity.getDrugDetailId( ) );
        log.info( "即将对" + drugDetails + "进货" );

        if ( drugDetails.getDrugDetailsStatus( ) == 0 ) {
            log.error( drugDetails.getDrugDetailsStatus( ) + "药品详情状态禁用" );
            throw new AppException( ResponseEnum.ADD_DRUG_ERROR );
        }
        // 调整现存数量
        batchEntity.setBatchExistingQuantity( batchEntity.getBatchPurchaseQuantity( ) );
        batchEntity.setBatchStatus( BatchStatusEnum.CREATED.toString( ) );
        batchMapper.insert( batchEntity );
    }

    @Override
    public Page< BatchEntity > getPageBatchDetail( int current, int size, Integer drugDetailId ) {
        Page< BatchEntity > batchEntityPage = new Page<>( current, size );
        QueryWrapper< BatchEntity > batchEntityQueryWrapper = new QueryWrapper<>( );
        batchEntityQueryWrapper.eq( "drug_detail_id", drugDetailId );
        Page< BatchEntity > entityPage = batchMapper.selectPage( batchEntityPage, batchEntityQueryWrapper );
        return entityPage;
    }

    @Override
    public void changeBatch( BatchEntity batchEntity ) {
        log.info( "batchEntity:" + batchEntity );
        int i = batchMapper.updateById( batchEntity );
        if ( i != 1 ) {
            throw new AppException( ResponseEnum.UPDATE_ERROR );
        }
    }

    @Override
    public List< BatchEntity > getBatchDetail( Integer drugDetailId, String active ) {
        QueryWrapper< BatchEntity > batchEntityQueryWrapper = new QueryWrapper<>( );
        batchEntityQueryWrapper.eq( "drug_detail_id", drugDetailId );
        BatchStatusEnum batchStatusEnum;
        String string = "-1";
        if ( !string.equals( active ) && ( !active.isEmpty( ) || active != null ) ) {
            // 转换active
            log.info( "转换active:" + active );
            batchStatusEnum = BatchStatusEnum.fromString( active );
            batchEntityQueryWrapper.eq( "batch_status", batchStatusEnum );
        }
        return batchMapper.selectList( batchEntityQueryWrapper );
    }

    @Override
    public int removeByDrugDetailId( Integer drugDetailId ) {
        QueryWrapper< BatchEntity > batchEntityQueryWrapper = new QueryWrapper<>( );
        batchEntityQueryWrapper.eq( "drug_detail_id", drugDetailId );
        int delete = batchMapper.delete( batchEntityQueryWrapper );
        if ( delete == 0 ) {
            throw new AppException( ResponseEnum.DEL_ERROR );
        }
        return delete;
    }

    /**
     * @param searchDrugDTO 分页查询条件信息 筛选条件为空时执行全部
     * @return page
     */
    @Override
    public IPage< BatchDTO > searchPage( SearchDrugDTO searchDrugDTO ) {
        Page< BatchDTO > batchDTOPage = new Page<>( searchDrugDTO.getCurrent( ), searchDrugDTO.getSize( ) );
        IPage< BatchDTO > page = batchMapper.searchPage( searchDrugDTO, batchDTOPage );
        log.info( "成功" );
        return page;
    }

    /**
     *
     */
    @Override
    public void checkDrugBatch( ) {
        List< BatchEntity > list = batchMapper.getExpired( );
        for ( BatchEntity batch : list ) {
            batch.setBatchStatus( BatchStatusEnum.EXPIRED.toString( ) );
            batchMapper.updateById( batch );
        }
    }


    //    @Override
    //    public void myAddBatch( BatchAddDTO batchAddDTO ) {
    //        //
    //        // old 通过药品id、品牌id，确定药品详情id--废弃——药品id、品牌id不能确定药品详情id，需要一个规格
    //
    //
    //
    ////        drugDetailsMapper.selectOne( );
    //    }
}
