DROP TABLE IF EXISTS sys_brand;
CREATE TABLE sys_brand
(
    `brand_id`      INT auto_increment NOT NULL COMMENT '品牌id',
    `brand_name`    VARCHAR(255) unique COMMENT '品牌名称',
    `user_id`       INT COMMENT '联系人Id',
    `create_time`   DATETIME COMMENT '创建时间',
    `modified_time` DATETIME COMMENT '更新时间',
    PRIMARY KEY (brand_id)
) COMMENT = '品牌表';

insert into sys_brand
values (null, '三九企业集团', null, now(), now());