package com.yw.msmp.vo;
/*
 * @PACKAGE_NAME com.yw.msmp.vo
 * @author  yanhaoyuwen
 * @version  1.0
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yanhaoyuwen
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceVO {

    @ApiModelProperty( name = "", notes = "" )
    @TableId( value = "service_id", type = IdType.AUTO )
    private Integer serviceId;
    /**
     *
     */
    @ApiModelProperty( name = "", notes = "" )
    private String isLast;
    /**
     *
     */
    @ApiModelProperty( name = "", notes = "" )
    private String isNormal;
    /**
     *
     */
    @ApiModelProperty( name = "", notes = "" )
    private Integer userId;
    /**
     *
     */
    @ApiModelProperty( name = "", notes = "" )
    @JsonFormat( pattern = "yyyy-MM-dd" )
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date serviceDate;

    @ApiModelProperty( name = "", notes = "" )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date createTime;

    @ApiModelProperty( value = "更新时间" )
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date modifiedTime;

    @ApiModelProperty( name = "", notes = "" )
    private Map< Object, Object > serviceMap;

    @ApiModelProperty( name = "", notes = "" )
    private List< ServiceDetailVO > serviceList;

}
