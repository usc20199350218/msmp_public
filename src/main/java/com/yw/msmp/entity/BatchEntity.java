package com.yw.msmp.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yanhaoyuwen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel( value = "批次表", description = "" )
@TableName( "sys_batch" )
public class BatchEntity implements Serializable {

    /**
     * 批次Id
     */
    @ApiModelProperty( name = "批次Id", notes = "" )
    @TableId( value = "batch_id", type = IdType.AUTO )
    private Integer batchId;
    /**
     * 药品Id
     */
    @ApiModelProperty( name = "药品详情Id", notes = "" )
    private Integer drugDetailId;
    /**
     * 批号编号;由供应商提供
     */
    @ApiModelProperty( name = "批号编号", notes = "由供应商提供" )
    private String batchNumber;
    /**
     * 生产日期
     */
    @ApiModelProperty( name = "生产日期", notes = "" )
    @JsonFormat( pattern = "yyyy-MM-dd" )
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date batchProductionDate;
    /**
     * 进货数量
     */
    @ApiModelProperty( name = "进货数量", notes = "" )
    private Integer batchPurchaseQuantity;
    /**
     * 现存数量;出售从这里计算
     */
    @ApiModelProperty( name = "现存数量", notes = "出售从这里计算" )
    private Integer batchExistingQuantity;
    /**
     * 批次状态
     */
    @ApiModelProperty( name = "批次状态", notes = "批次状态/0-创建/1-正常/2-售完/3-过期/-2-禁止-后期增加/" )
    private String batchStatus;
    /**
     * 有效期
     */
    @ApiModelProperty( name = "有效期", notes = "" )
    private Integer batchValidityPeriod;
    /**
     * 备注
     */
    @ApiModelProperty( name = "备注", notes = "" )
    private String remark;
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
     * version
     */
    @ApiModelProperty( name = "version", notes = "" )
    @Version
    private Integer version;

}
