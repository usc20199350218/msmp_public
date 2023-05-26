package com.yw.msmp.dto;
/*
 * @PACKAGE_NAME com.yw.msmp.dto
 * @author  yanhaoyuwen
 * @version  1.0
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yanhaoyuwen
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchUserDTO {

    //    searchText: this.searchText,
    //    searchMethod: this.searchMethod,
    //    roleId: this.roleId,
    //    vip: this.vip,
    //    gender: this.gender,
    //    status: this.status,
    //    current: this.pageInfo.current,
    //    size: this.pageInfo.size
    private String userGender;
    private Integer roleId;
    private Integer userStatus;

    private Integer userVip;
    private Integer current;
    private Integer size;
    private String searchText;
    private String searchMethod;


}
