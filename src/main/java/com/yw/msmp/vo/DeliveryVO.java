package com.yw.msmp.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class DeliveryVO {

    /**
     * 配送id
     */
    @ApiModelProperty( name = "配送id", notes = "" )
    private Integer deliveryId;
    /**
     * 配送状态;创建、分配、取货、配送、到达
     */
    @ApiModelProperty( name = "配送状态", notes = "创建、分配、取货、配送、到达" )
    private String deliveryStatus;
    /**
     * 数量
     */
    @ApiModelProperty( name = "数量", notes = "" )
    private Integer deliveryNum;
    /**
     * 配送人员id
     */
    @ApiModelProperty( name = "配送人员id", notes = "" )
    private Integer userId;
    /**
     * 创建时间
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
     * 店铺ID
     */
    @ApiModelProperty( name = "店铺ID", notes = "" )
    private Integer storeId;
    /**
     * 店铺名称
     */
    @ApiModelProperty( name = "店铺名称", notes = "" )
    private String storeName;
    /**
     * 店铺地址
     */
    @ApiModelProperty( name = "店铺地址", notes = "" )
    private String storeAddress;
    /**
     * 联系电话
     */
    @ApiModelProperty( name = "联系电话", notes = "" )
    private String contactPhone;
    /**
     * 营业时间
     */
    @ApiModelProperty( name = "营业时间", notes = "" )
    private String businessHours;
    /**
     * 所属区域
     */
    @ApiModelProperty( name = "所属区域", notes = "" )
    private String area;
    /**
     * 所属商圈
     */
    @ApiModelProperty( name = "所属商圈", notes = "" )
    private String businessDistrict;

    /**
     * 药品详情id
     */
    @ApiModelProperty( name = "药品详情id", notes = "" )
    private Integer drugDetailId;
    /**
     * 规格
     */
    @ApiModelProperty( name = "规格", notes = "" )
    private String drugSpecification;

    /**
     * 药品ID
     */
    @ApiModelProperty( name = "药品ID", notes = "" )
    private Integer drugId;
    /**
     * 药品名称
     */
    @ApiModelProperty( name = "药品名称", notes = "" )
    private String drugName;
    /**
     * 是否非处方;0-非处方药/1-处方药
     */
    @ApiModelProperty( name = "是否非处方", notes = "0-非处方药/1-处方药" )
    private Integer isRx;

    /**
     * 批次Id
     */
    @ApiModelProperty( name = "批次Id", notes = "" )
    private Integer batchId;
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
     * 有效期
     */
    @ApiModelProperty( name = "有效期", notes = "" )
    private Integer batchValidityPeriod;

    /**
     * 品牌id
     */
    @ApiModelProperty( name = "品牌id", notes = "" )
    private Integer brandId;
    /**
     * 品牌名称
     */
    @ApiModelProperty( name = "品牌名称", notes = "" )
    private String brandName;

}

