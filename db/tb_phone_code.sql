DROP TABLE IF EXISTS tb_phone_code;
CREATE TABLE tb_phone_code
(
    `phone_code_id` INT NOT NULL AUTO_INCREMENT COMMENT '登录验证码id',
    `phone_num`     VARCHAR(255) COMMENT '手机号',
    `code`          VARCHAR(255) COMMENT '验证码',
    `createTime`    DATE COMMENT '创建时间',
    PRIMARY KEY (phone_code_id)
) COMMENT = '登录验证码';
