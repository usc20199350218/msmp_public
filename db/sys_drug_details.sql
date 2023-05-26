DROP TABLE IF EXISTS sys_drug_details;
CREATE TABLE sys_drug_details
(
    `drug_details_id`     INT auto_increment NOT NULL COMMENT '药品详情id',
    `drug_id`             INT COMMENT '药品id',
    `brand_id`            INT COMMENT '品牌Id',
    `drug_details_status` INT COMMENT '状态',
    `drug_specification`  VARCHAR(255) COMMENT '规格',
#     `drug_shelf_life`    VARCHAR(255) COMMENT '保质期',
    `drug_unit_price`     DECIMAL(24, 6) COMMENT '单价',
    `drug_retail_price`   DECIMAL(24, 6) COMMENT '零售价',
    `create_time`         DATETIME COMMENT '创建时间',
    `modified_time`       DATETIME COMMENT '更新时间',
    PRIMARY KEY (drug_details_id)
) COMMENT = '药品详情表';


insert into sys_drug_details
values (null, 1, 1, 1, '0.25g*20', 10, 12, now(), now());

select *
from sys_drug_details sdd;

select sdd.drug_detail_id,
       sdd.drug_details_status,
       sdd.drug_specification,
       sdd.drug_unit_price,
       sdd.drug_retail_price,
       sdd.create_time,
       sdd.modified_time,
       sd.drug_id,
       sd.drug_name,
       sd.is_rx,
       sb.brand_id,
       sb.brand_name,
       tt.type_id,
       tt.type_name
from medical.sys_drug_details sdd
         left join medical.sys_drug sd on sdd.drug_id = sd.drug_id
         left join medical.sys_brand sb on sdd.brand_id = sb.brand_id
         left join tb_type tt on sd.type_id = tt.type_id