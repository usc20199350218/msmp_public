DROP TABLE IF EXISTS tb_type;
CREATE TABLE tb_type
(
    `type_id`       INT NOT NULL AUTO_INCREMENT COMMENT '品类Id',
    `type_name`     VARCHAR(255) COMMENT '品类名称',
    `type_status`   INT DEFAULT 1 COMMENT '品类状态',
    `create_time`   DATETIME COMMENT '创建时间',
    `modified_time` DATETIME COMMENT '更新时间',
    PRIMARY KEY (type_id)
) COMMENT = '品类表';
