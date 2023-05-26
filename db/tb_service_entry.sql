DROP TABLE IF EXISTS tb_service_entry;
CREATE TABLE tb_service_entry
(
    `service_entry_id`   INT NOT NULL AUTO_INCREMENT COMMENT '服务条目id',
    `service_entry_name` VARCHAR(255) COMMENT '服务条目内容',
    `defaults`           VARCHAR(255) COMMENT '默认值',
    `remark`             VARCHAR(900) COMMENT '说明',
    `create_time`        DATETIME COMMENT '创建时间',
    `modified_time`      DATETIME COMMENT '更新时间',
    PRIMARY KEY (service_entry_id)
) COMMENT = '记录条目';

