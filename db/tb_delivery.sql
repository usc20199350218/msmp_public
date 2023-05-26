DROP TABLE IF EXISTS tb_delivery;
CREATE TABLE tb_delivery
(
    `delivery_id`     INT NOT NULL AUTO_INCREMENT COMMENT '配送id',
    `store_id`        VARCHAR(255) COMMENT '店铺id',
    `area`            VARCHAR(255) COMMENT '区域',
    `delivery_status` VARCHAR(255) COMMENT '配送状态;创建、分配、取货、配送、到达',
    `batch_id`        INT COMMENT '仓库批次id',
    `store_batch_id`  INT COMMENT '店铺批次id',
    `drug_detail_id`  INT COMMENT '药品详情id',
    `delivery_num`    INT COMMENT '数量',
    `user_id`         BIGINT COMMENT '配送人员id',
    `create_time`     DATETIME COMMENT '创建时间',
    `modified_time`   DATETIME COMMENT '更新时间',
    PRIMARY KEY (delivery_id)
) COMMENT = '配送表';


select *
from tb_delivery td
where td.store_id = 5
  and td.delivery_status = 'CREATED';

# 药品名称、规格、品牌、批次编号来确定药品
# 店铺名称、店铺地址来处理配送
# 要连接店铺表、仓库批次表、药品详情表、药品表、品牌表5张
# 根据状态查询配送
select td.delivery_id,
       td.delivery_status,
       td.delivery_num,
       td.user_id,
       td.create_time,
       td.modified_time,
       ts.store_id,
       ts.store_name,
       ts.store_address,
       ts.contact_phone,
       ts.business_hours,
       ts.create_time,
       ts.area,
       ts.business_district,
       sdd.drug_detail_id,
       sdd.drug_specification,
       sd.drug_id,
       sd.drug_name,
       sd.is_rx,
       sb.batch_id,
       sb.batch_number,
       sb.batch_production_date,
       sb.batch_validity_period,
       s.brand_id,
       s.brand_name
from tb_delivery td
         left join tb_store ts on ts.store_id = td.store_id
         left join sys_drug_details sdd on td.drug_detail_id = sdd.drug_detail_id
         left join sys_drug sd on sdd.drug_id = sd.drug_id
         left join sys_batch sb on td.batch_id = sb.batch_id
         left join sys_brand s on sdd.brand_id = s.brand_id
where td.delivery_status = 'CREATED';


select *
from tb_delivery td
         left join tb_store ts on td.store_id = ts.store_id
         left join sys_drug_details sdd on td.drug_detail_id = sdd.drug_detail_id
         left join sys_batch sb on td.batch_id = sb.batch_id
where td.delivery_status = 'CREATED';
