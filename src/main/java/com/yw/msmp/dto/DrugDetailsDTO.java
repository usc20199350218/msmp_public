package com.yw.msmp.dto;

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
public class DrugDetailsDTO {

    /**
     * 药品详情id
     */
    @ApiModelProperty( name = "药品详情id", notes = "" )
    private Integer drugDetailId;
    /**
     * 药品id
     */
    @ApiModelProperty( name = "药品id", notes = "" )
    private Integer drugId;
    /**
     * 药品名称
     */
    @ApiModelProperty( name = "药品名称", notes = "" )
    private String drugName;
    /**
     * 品牌Id
     */
    @ApiModelProperty( name = "品牌Id", notes = "" )
    private Integer brandId;
    /**
     * 品牌名称
     */
    @ApiModelProperty( name = "品牌名称", notes = "" )
    private String brandName;
    /**
     * 品类
     */
    @ApiModelProperty( name = "品类", notes = "" )
    private Integer typeId;
    /**
     * 品类
     */
    @ApiModelProperty( name = "品类", notes = "" )
    private String typeName;
    /**
     * 是否非处方;0-非处方药/1-处方药
     */
    @ApiModelProperty( name = "是否非处方", notes = "0-非处方药/1-处方药" )
    private Integer isRx;
    /**
     * 规格
     */
    @ApiModelProperty( name = "规格", notes = "" )
    private String drugSpecification;
    /**
     * 规格
     */
    @ApiModelProperty( name = "编码", notes = "" )
    private String drugNumber;
    /**
     * 单价
     */
    @ApiModelProperty( name = "单价", notes = "" )
    private Double drugUnitPrice;
    /**
     * 零售价
     */
    @ApiModelProperty( name = "零售价", notes = "" )
    private Double drugRetailPrice;
    /**
     * 状态
     */
    @ApiModelProperty( name = "状态", notes = "" )
    private Integer drugDetailsStatus;
    /**
     * 图片
     */
    @ApiModelProperty( name = "图片", notes = "" )
    private String drugDetailPath;
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
