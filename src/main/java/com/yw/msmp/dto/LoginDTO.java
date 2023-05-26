package com.yw.msmp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {

    // id
    //    private Long userId;
    private Integer userId;
    // 头像
    private String userAvatarUrl;
    // 名称
    private String userName;
    // 角色id   // rbac  通过角色 确定权限的权限系统
    private Integer roleId;

    private String token;

}
