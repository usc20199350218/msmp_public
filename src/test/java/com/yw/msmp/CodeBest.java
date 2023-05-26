package com.yw.msmp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yw.msmp.common.exception.AppException;
import com.yw.msmp.common.result.ResponseEnum;
import com.yw.msmp.dto.FastStoreBatchDTO;
import com.yw.msmp.entity.BatchEntity;
import com.yw.msmp.entity.DeliveryEntity;
import com.yw.msmp.entity.StoreBatchEntity;
import com.yw.msmp.enums.BatchStatusEnum;
import com.yw.msmp.enums.DeliveryStatusEnum;
import com.yw.msmp.mapper.BatchMapper;
import com.yw.msmp.mapper.DeliveryMapper;
import com.yw.msmp.mapper.StoreBatchMapper;

import javax.annotation.Resource;
import java.awt.*;
import java.net.URI;
import java.util.List;

public class CodeBest {

    /**
     * 这是店铺serviceImpl中fastApproval对应的执行过程
     */

    @Resource
    private StoreBatchMapper storeBatchMapper;

    @Resource
    private BatchMapper batchMapper;

    @Resource
    private DeliveryMapper deliveryMapper;

    public void main( String[] args ) {
        FastStoreBatchDTO fastStoreBatchDTO = new FastStoreBatchDTO( );

        List< Integer > idList = fastStoreBatchDTO.getApplyIdList( );
        boolean isPass = "pass".equals( fastStoreBatchDTO.getStatus( ) );
        String storeBatchStatus = isPass ? "NORMAL_PURCHASE" : "FORBIDDEN";
        int updateCount = 0;
        // 分情况执行更新
        if ( isPass ) {
            // 正常
            updateCount = processPassedStatus( idList, fastStoreBatchDTO );
        }
        else {
            // 退回
            updateCount = processFailedStatus( idList, storeBatchStatus );
        }
        if ( updateCount != idList.size( ) ) {
            throw new AppException( ResponseEnum.UPDATE_ERROR );
        }
    }

    // pass
    //    private int processPassedStatus( List< Integer > idList, FastStoreBatchDTO fastStoreBatchDTO ) {
    //        int updateCount = 0;
    //        // 获取对应药品详情id，且可用的仓库批次
    //        List< BatchEntity > batchEntityList = getSortedBatchEntityList( fastStoreBatchDTO );
    //        // 遍历店铺批次id列表
    //        for ( Integer storeBatchId : idList ) {
    //            // 根据idList的值，获取店铺批次
    //            StoreBatchEntity storeBatch = storeBatchMapper.selectById( storeBatchId );
    //            // 获取该批次请求的数量
    //            int remainingQuantity = storeBatch.getStoreBatchPurchaseQuantity( );
    //            // 遍历仓库批次，根据 仓库批次数量 对比 店铺批次请求数量 对比计算
    //            boolean firstBool = true;
    //            int firstNum = 0;
    //            for ( int i = 0; i < batchEntityList.size( ); i++ ) {
    //                BatchEntity batchEntity = batchEntityList.get( i );
    //                // 仓库批次对应可用数量大于零的
    //                if ( batchEntity.getBatchExistingQuantity( ) > 0 ) {
    //                    // 大于零，说明当前批次没有被中断
    //                    int deliveredQuantity = 0;
    //                    // 第一次时
    //                    if ( i == 0 ) {
    //                        // 比较大小并分情况更新仓库批次
    //                        deliveredQuantity = processBatchEntity( storeBatch, remainingQuantity, batchEntity );
    //                        firstNum = deliveredQuantity;
    //                    } else {
    //                        // 更新Boolean
    //                        firstBool = false;
    //                        // 之后的执行，都要同时在店铺批次请求那里生成新的对应请求
    //                        deliveredQuantity = processBatchEntityBefore( storeBatch, remainingQuantity, batchEntity );
    //                    }
    //                    // 减去此次批次
    //                    remainingQuantity -= deliveredQuantity;
    //                    // 判断是否处理完
    //                    if ( remainingQuantity <= 0 ) {
    //                        break;
    //                    }
    //                }
    //            }
    //            // 更新店铺批次状态
    //            if ( !firstBool ) {
    //                // 涉及多批次
    //                // 第一次处理的数量
    //                storeBatch.setStoreBatchPurchaseQuantity( firstNum );
    //                // 更新备注
    //                storeBatch.setStoreBatchRemark( "涉及多批次，已经自动新建批次" );
    //            }
    //            storeBatch.setStoreBatchStatus( "NORMAL_PURCHASE" );
    //            updateCount += storeBatchMapper.updateById( storeBatch );
    //        }
    //        // 返回更新数量
    //        return updateCount;
    //    }

    private int processPassedStatus( List< Integer > idList, FastStoreBatchDTO fastStoreBatchDTO ) {
        int updateCount = 0;
        List< BatchEntity > batchEntityList = getSortedBatchEntityList( fastStoreBatchDTO );
        for ( Integer storeBatchId : idList ) {// 遍历店铺批次id列表
            StoreBatchEntity storeBatch = storeBatchMapper.selectById( storeBatchId );
            int remainingQuantity = storeBatch.getStoreBatchPurchaseQuantity( );
            int deliveredQuantity = remainingQuantity; // initialize to remainingQuantity
            for ( BatchEntity batchEntity : batchEntityList ) {
                if ( batchEntity.getBatchExistingQuantity( ) > 0 ) {
                    deliveredQuantity = processBatchEntity( storeBatch, remainingQuantity, batchEntity );
                    remainingQuantity -= deliveredQuantity;
                    if ( remainingQuantity <= 0 ) {
                        break;
                    }
                }
            }
            if ( deliveredQuantity == remainingQuantity ) { // only one batch was processed
                storeBatch.setStoreBatchStatus( "NORMAL_PURCHASE" );
            }
            else { // multiple batches were processed
                storeBatch.setStoreBatchPurchaseQuantity( deliveredQuantity );
                storeBatch.setStoreBatchRemark( "涉及多批次，已经自动新建批次" );
                storeBatch.setStoreBatchStatus( "NORMAL_PURCHASE" );
            }
            updateCount += storeBatchMapper.updateById( storeBatch );
        }
        return updateCount;
    }


    private int processFailedStatus( List< Integer > idList, String storeBatchStatus ) {
        int updateCount = 0;
        // 依次对店铺批次进行更新
        for ( Integer storeBatchId : idList ) {
            StoreBatchEntity storeBatch = storeBatchMapper.selectById( storeBatchId );
            storeBatch.setStoreBatchStatus( storeBatchStatus );
            updateCount += storeBatchMapper.updateById( storeBatch );
        }
        return updateCount;
    }

    private List< BatchEntity > getSortedBatchEntityList( FastStoreBatchDTO fastStoreBatchDTO ) {
        QueryWrapper< BatchEntity > batchEntityQueryWrapper = new QueryWrapper<>( );
        batchEntityQueryWrapper
                .eq( "drug_detail_id", fastStoreBatchDTO.getDrugDetailId( ) )
                .eq( "batch_status", "SOLD" )
                .orderByAsc( "batch_production_date" );
        return batchMapper.selectList( batchEntityQueryWrapper );
    }

    private int processBatchEntityBefore( StoreBatchEntity storeBatch,
                                          int remainingQuantity,
                                          BatchEntity batchEntity ) {
        // 因为批次对应关系不是一一对应，执行之前先进行店铺批次的新增
        storeBatchMapper.insert(
                StoreBatchEntity.builder( )
                                .batchId( batchEntity.getBatchId( ) )
                                .batchNumber( batchEntity.getBatchNumber( ) )
                                .storeId( storeBatch.getStoreId( ) )
                                .drugDetailId( storeBatch.getDrugDetailId( ) )
                                .storeBatchPurchaseQuantity( Math.min( batchEntity.getBatchExistingQuantity( ),
                                                                       remainingQuantity ) )
                                .storeBatchStatus( BatchStatusEnum.CREATED.toString( ) )
                                .version( 1 )
                                .build( ) );
        return processBatchEntity( storeBatch, remainingQuantity, batchEntity );
    }

    private int processBatchEntity( StoreBatchEntity storeBatch,
                                    int storeBatchPurchaseQuantity,
                                    BatchEntity batchEntity ) {
        // 仓库批次可用数量、店铺批次请求数量中   取最小
        int deliveryQuantity = Math.min( batchEntity.getBatchExistingQuantity( ), storeBatchPurchaseQuantity );
        // 录入配送表
        createDeliveryEntity( storeBatch, deliveryQuantity, batchEntity );
        // 更新仓库批次 字段
        batchEntity.setBatchExistingQuantity( batchEntity.getBatchExistingQuantity( ) - deliveryQuantity );
        if ( batchEntity.getBatchExistingQuantity( ) == 0 ) {
            batchEntity.setBatchStatus( "SOLD_OUT" );
        }
        batchMapper.updateById( batchEntity );
        return deliveryQuantity;
    }

    // 录入配送表
    private void createDeliveryEntity( StoreBatchEntity storeBatch, int deliveryQuantity, BatchEntity batchEntity ) {
        DeliveryEntity deliveryEntity = DeliveryEntity.builder( )
                                                      .storeId( storeBatch.getStoreId( ) )
                                                      .deliveryStatus( DeliveryStatusEnum.CREATED.toString( ) )
                                                      .batchId( batchEntity.getBatchId( ) )
                                                      .drugDetailId( storeBatch.getDrugDetailId( ) )
                                                      .storeBatchId( storeBatch.getStoreBatchId( ) )
                                                      .deliveryNum( deliveryQuantity )
                                                      .build( );
        deliveryMapper.insert( deliveryEntity );
    }


    public void tes( ) {
        try {
            Desktop.getDesktop( ).browse( new URI( "https://www.example.com" ) );
        } catch ( Exception e ) {
            e.printStackTrace( );
        }
    }

}
