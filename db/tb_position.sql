DROP TABLE IF EXISTS tb_position;
CREATE TABLE tb_position
(
    `position_id`    INT NOT NULL AUTO_INCREMENT COMMENT '职位id',
    `position_name`  VARCHAR(255) COMMENT '职位名称',
    `salary`         VARCHAR(255) COMMENT '标准薪水',
    `responsibility` VARCHAR(255) COMMENT '职责',
    `create_time`    DATETIME COMMENT '创建时间',
    `modified_time`  DATETIME COMMENT '更新时间',
    PRIMARY KEY (position_id)
) COMMENT = '职位表';

insert into tb_position
values (null, '店长', '9000', null, now(), now());
