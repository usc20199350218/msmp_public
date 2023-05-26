package com.yw.msmp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author yanhaoyuwen
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchOrderDTO {

    private String orderType;
    private Integer storeId;
    private String payStatus;
    private String payment;
    private String searchMethod;
    private String searchText;
    @JsonFormat( pattern = "yyyy-MM-dd" )
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date startDate;
    @JsonFormat( pattern = "yyyy-MM-dd" )
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    private Date endDate;
    private Integer current;
    private Integer size;

}
