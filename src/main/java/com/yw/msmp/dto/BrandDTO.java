package com.yw.msmp.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class BrandDTO {

    /**
     * 品牌id
     */
    @ApiModelProperty( name = "品牌id", notes = "" )
    @TableId
    private Integer brandId;
    /**
     * 品牌名称
     */
    @ApiModelProperty( name = "品牌名称", notes = "" )
    private String brandName;
    /**
     * 联系人Id
     */
    @ApiModelProperty( name = "联系人Id", notes = "" )
    private Integer userId;
    @ApiModelProperty( value = "联系人手机号" )
    private Long userPhone;
    @ApiModelProperty( value = "联系人真实姓名" )
    private String userRealName;
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
