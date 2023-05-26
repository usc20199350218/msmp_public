package com.yw.msmp;
/*
 * @PACKAGE_NAME com.yw.msmp
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stu {

    private String name;
    private Integer age;
    private String phone;
    private String email;

}
