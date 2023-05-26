DROP TABLE IF EXISTS tb_shopping_cart;
CREATE TABLE tb_shopping_cart
(
    `cart_id`        INT NOT NULL AUTO_INCREMENT COMMENT '购物车id',
    `user_id`        INT COMMENT '用户id',
    `drug_detail_id` INT COMMENT '详情id',
    `number`         INT COMMENT '数量',
    `store_id`       INT COMMENT '店铺id',
    `version`        INT COMMENT 'version',
    PRIMARY KEY (cart_id)
) COMMENT = '购物车';

insert into tb_shopping_cart
values (1, 1, 1, 2, 1, 1);

select *
from tb_shopping_cart tsc;

select tsc.cart_id,
       tsc.user_id,
       tsc.drug_detail_id,
       tsc.number,
       tsc.store_id,
       tsc.version,
       sdd.drug_detail_path,
       sdd.drug_retail_price,
       sd.drug_id,
       sd.drug_name
from tb_shopping_cart tsc
         left join sys_drug_details sdd on tsc.drug_detail_id = sdd.drug_detail_id
         left join sys_drug sd on sdd.drug_id = sd.drug_id
where tsc.user_id = 1
  and sdd.drug_number = ''
  and sd.drug_name like concat('%', '1', '%')
;

SELECT tsc.cart_id,
       tsc.user_id,
       tsc.drug_detail_id,
       tsc.number,
       tsc.store_id,
       tsc.version,
       sdd.drug_detail_path,
       sdd.drug_retail_price,
       sd.drug_id,
       sd.drug_name
FROM tb_shopping_cart tsc
         LEFT JOIN sys_drug_details sdd ON tsc.drug_detail_id = sdd.drug_detail_id
         LEFT JOIN sys_drug sd ON sdd.drug_id = sd.drug_id
;
SELECT tsc.cart_id,
       tsc.user_id,
       tsc.drug_detail_id,
       tsc.number,
       tsc.store_id,
       tsc.version,
       sdd.drug_detail_path,
       sdd.drug_retail_price,
       sd.drug_id,
       sd.drug_name
FROM tb_shopping_cart tsc
         LEFT JOIN sys_drug_details sdd ON tsc.drug_detail_id = sdd.drug_detail_id
         LEFT JOIN sys_drug sd ON sdd.drug_id = sd.drug_id <
                                  tsc.user_id = 1 AND sd.drug_name LIKE '%%' AND
                                  sdd.drug_number like '%%';

select sum(tsc.number)
from tb_shopping_cart tsc
where tsc.user_id = 1;

select *
from tb_shopping_cart tsc
