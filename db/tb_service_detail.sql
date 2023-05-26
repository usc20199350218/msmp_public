DROP TABLE IF EXISTS tb_service_detail;
CREATE TABLE tb_service_detail
(
    `service_detail_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
    `service_entry_id`  INT COMMENT '',
    `user_id`           INT COMMENT '',
    `content`           VARCHAR(900) COMMENT '',
    `service_id`        INT COMMENT '',
    `create_time`       DATETIME COMMENT '',
    PRIMARY KEY (service_detail_id)
) COMMENT = '服务详情';

insert into tb_service_detail
values (null, 1, 1, 160, 1, now());
insert into tb_service_detail
values (null, 2, 1, 120, 1, now());
insert into tb_service_detail
values (null, 1, 1, 161, 2, now());
insert into tb_service_detail
values (null, 2, 1, 123, 2, now());

