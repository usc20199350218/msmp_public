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
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel( value = "药品详情表", description = "" )
@TableName( "sys_drug_details" )
public class DrugDetailsEntity implements Serializable {

    /**
     * 药品详情id
     */
    @ApiModelProperty( name = "药品详情id", notes = "" )
    @TableId( value = "drug_detail_id", type = IdType.AUTO )
    private Integer drugDetailId;
    /**
     * 药品id
     */
    @ApiModelProperty( name = "药品id", notes = "" )
    private Integer drugId;
    /**
     * 品牌Id
     */
    @ApiModelProperty( name = "品牌Id", notes = "" )
    private Integer brandId;
    /**
     * 规格
     */
    @ApiModelProperty( name = "规格", notes = "" )
    private String drugSpecification;
    /**
     * 图片
     */
    @ApiModelProperty( name = "图片", notes = "" )
    private String drugDetailPath;
    /**
     * 状态
     */
    @ApiModelProperty( name = "状态", notes = "" )
    private Integer drugDetailsStatus;
    /**
     * 单价
     */
    @ApiModelProperty( name = "单价", notes = "" )
    private BigDecimal drugUnitPrice;
    /**
     * 零售价
     */
    @ApiModelProperty( name = "零售价", notes = "" )
    private BigDecimal drugRetailPrice;
    /**
     * 编码
     */
    @ApiModelProperty( name = "编码", notes = "" )
    private String drugNumber;
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

}
