DROP TABLE IF EXISTS sys_drug;
CREATE TABLE sys_drug
(
    `drug_id`       int auto_increment NOT NULL COMMENT '药品ID',
    `drug_name`     VARCHAR(255) COMMENT '药品名称',
    `is_rx`         INT DEFAULT 0 COMMENT '是否非处方;0-非处方药/1-处方药',
    `drug_status`   INT DEFAULT 1 COMMENT '药品状态;0-否/1-是',
    `create_time`   DATETIME COMMENT '创建时间',
    `modified_time` DATETIME COMMENT '更新时间',
    PRIMARY KEY (drug_id)
) COMMENT = '药品信息表';



insert into sys_drug
values (null, '阿莫西林胶囊', 0, 1, now(), now());

select *
from sys_drug sd
         left join tb_type tt on sd.type_id = tt.type_id;

select sd.drug_id,
       sd.drug_name,
       sd.is_rx,
       tt.type_id,
       tt.type_name,
       sd.drug_status,
       sd.create_time,
       sd.modified_time
from sys_drug sd
         left join tb_type tt on sd.type_id = tt.type_id;

select *
from sys_drug sd;