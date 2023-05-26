package com.yw.msmp.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreBatchDetailVO {

    /**
     * 店内批次Id
     */
    @ApiModelProperty( name = "店内批次Id", notes = "" )
    private String storeBatchId;
    /**
     * 对应批次Id
     */
    @ApiModelProperty( name = "对应批次Id", notes = "" )
    private Integer batchId;
    /**
     * 对应批次编号
     */
    @ApiModelProperty( name = "对应批次编号", notes = "" )
    private String batchNumber;
    /**
     * 药品详情id
     */
    @ApiModelProperty( name = "药品详情id", notes = "" )
    private Integer drugDetailId;
    /**
     * 进货数量
     */
    @ApiModelProperty( name = "进货数量", notes = "" )
    private Integer storeBatchPurchaseQuantity;
    /**
     * 现存数量;出售从这里计算
     */
    @ApiModelProperty( name = "现存数量", notes = "" )
    private Integer storeBatchExistingQuantity;
    /**
     * 状态
     */
    @ApiModelProperty( name = "状态", notes = "" )
    private String storeBatchStatus;
    /**
     * version
     */
    @ApiModelProperty( name = "version", notes = "" )
    @Version
    private Integer version;
    /**
     * 创建日期
     */
    @ApiModelProperty( name = "创建时间", notes = "" )
    @TableField( value = "create_time", fill = FieldFill.INSERT )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty( name = "更新时间", notes = "" )
    @TableField( value = "modified_time", fill = FieldFill.INSERT_UPDATE )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date modifiedTime;
    /**
     * 备注
     */
    @ApiModelProperty( name = "备注", notes = "" )
    private String storeBatchRemark;
    /**
     * 有效期
     */
    @ApiModelProperty( name = "有效期", notes = "" )
    private Integer batchValidityPeriod;
    /**
     * 生产日期
     */
    @ApiModelProperty( name = "生产日期", notes = "" )
    @JsonFormat( pattern = "yyyy-MM-dd" )
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date batchProductionDate;

}
