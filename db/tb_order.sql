DROP TABLE IF EXISTS tb_order;
CREATE TABLE tb_order
(
    `order_id`         INT NOT NULL AUTO_INCREMENT COMMENT '订单编号，主键',
    `order_num`        VARCHAR(255) COMMENT '订单编码',
    `user_id`          INT COMMENT '用户Id',
    `operate_user_id`  INT COMMENT '操作人Id',
    `amount`           DECIMAL(24, 6) COMMENT '订单总金额',
    `status`           VARCHAR(255) COMMENT '订单状态',
    `payment_method`   VARCHAR(255) COMMENT '支付方式',
    `payment_time`     DATETIME COMMENT '支付时间',
    `delivery_address` VARCHAR(900) COMMENT '送货地址',
    `remark`           VARCHAR(900) COMMENT '备注',
    `create_time`      DATETIME COMMENT '创建时间',
    `modified_time`    DATETIME COMMENT '创建时间',
    PRIMARY KEY (order_id)
) COMMENT = '订单表';

select *
from tb_order t
where t.user_id = 1
  and t.store_id = 1;


select *
from tb_store ts
where ts.store_id in
      (select distinct t.store_id
       from tb_order t
       where t.user_id = 1);

select *
from tb_order t
where t.user_id = 1
  and t.store_id = 1
  and t.order_type = 'OFFLINE'
  and t.order_id in
      (select tod.order_id
       from tb_order_detail tod
       where tod.drug_detail_id in
             (select sdd.drug_detail_id
              from sys_drug_details sdd
                       left join sys_drug sd on sdd.drug_id = sd.drug_id
              where sdd.drug_number = 'gmlkl123'
                 or sd.drug_name like '%感冒灵颗粒%'));

SELECT t.*
FROM tb_order t
         JOIN tb_order_detail tod ON t.order_id = tod.order_id
         JOIN sys_drug_details sdd ON tod.drug_detail_id = sdd.drug_detail_id
         JOIN sys_drug sd ON sdd.drug_id = sd.drug_id
WHERE t.user_id = 1
  AND t.store_id = 1
  AND t.order_type = 'OFFLINE'
  AND (sdd.drug_number = 'gmlkl123' OR sd.drug_name LIKE '%感冒灵颗粒%');


SELECT t.*
FROM tb_order t
         JOIN tb_order_detail tod ON t.order_id = tod.order_id
         JOIN sys_drug_details sdd ON tod.drug_detail_id = sdd.drug_detail_id
         JOIN sys_drug sd ON sdd.drug_id = sd.drug_id
where t.user_id = 1
  AND t.store_id = 1
  AND t.order_type = 'OFFLINE'
  AND t.status = 'SUCCESS'
  AND t.create_time between now() and now()
  AND t.payment_method = 'ALIPAY'
  AND sdd.drug_number = 'gmlkl123'
  AND sd.drug_name LIKE '%感冒灵颗粒%'
  AND t.order_num = '';



SELECT t.*
FROM tb_order t
where t.user_id = 1
  AND t.store_id = 1
  AND t.order_type = 'OFFLINE'
  AND t.status = 'SUCCESS'
  AND t.payment_method = 'ALIPAY'
  AND t.create_time > '2020-01-01'
  AND t.create_time < now()
  AND t.order_num = ''
  AND t.order_id in
      (select distinct order_id
       from tb_order_detail tod
                LEFT JOIN sys_drug_details sdd ON tod.drug_detail_id = sdd.drug_detail_id
       where sdd.drug_number = ''
         AND tod.drug_name LIKE '')

