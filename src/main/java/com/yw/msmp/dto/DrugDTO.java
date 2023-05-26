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
public class DrugDTO {

    /**
     * 药品ID
     */
    @ApiModelProperty( name = "药品ID", notes = "" )
    private Long drugId;
    /**
     * 药品名称
     */
    @ApiModelProperty( name = "药品名称", notes = "" )
    private String drugName;
    /**
     * 药品规格
     */
    @ApiModelProperty( name = "药品规格", notes = "" )
    private String drugSpecification;
    /**
     * 药品品牌Id
     */
    @ApiModelProperty( name = "药品品牌Id", notes = "" )
    private String brandId;
    /**
     * 保质期（月）
     */
    @ApiModelProperty( name = "保质期（月）", notes = "" )
    private Integer drugShelfLife;
    /**
     * 是否非处方药 ；0-否/1-是
     */
    @ApiModelProperty( name = "是否非处方药", notes = "0-否/1-是" )
    private Integer isOtc;
    /**
     * 状态;0-禁用/1-正常
     */
    @ApiModelProperty( name = "状态", notes = "0-禁用/1-正常" )
    private Integer drugStatus;
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
     * 药品品牌名称
     */
    @ApiModelProperty( name = "药品品牌名称", notes = "" )
    private String brandName;

}
