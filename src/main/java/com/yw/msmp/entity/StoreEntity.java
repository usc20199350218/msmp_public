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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel( value = "店铺表", description = "" )
@TableName( "tb_store" )
public class StoreEntity implements Serializable {

    /**
     * 店铺ID
     */
    @ApiModelProperty( name = "店铺ID", notes = "" )
    @TableId( value = "store_id", type = IdType.AUTO )
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
    @ApiModelProperty( name = "店铺地址", notes = "" )
    private String storePath;
    /**
     * 创建时间
     */
    @ApiModelProperty( value = "创建时间" )
    @TableField( value = "create_time", fill = FieldFill.INSERT )
    @JsonFormat( pattern = "yyyy-MM-dd" )
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date createTime;
    /**
     * 状态
     */
    @ApiModelProperty( name = "状态", notes = "" )
    private Integer storeStatus;
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
     * 更新时间
     */
    @ApiModelProperty( value = "更新时间" )
    @TableField( value = "modified_time", fill = FieldFill.INSERT_UPDATE )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date modifiedTime;

}
