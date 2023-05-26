-- 创建店内职位表 Position
CREATE TABLE tb_position
(
    position_id   INT            NOT NULL AUTO_INCREMENT COMMENT '职位ID',
    position_name VARCHAR(255)   NOT NULL COMMENT '职位名称',
    salary        DECIMAL(10, 2) NOT NULL COMMENT '标准薪资',
    PRIMARY KEY (Position_id)
) COMMENT = '店内职位表';

-- 创建店内负责人表 Manager
CREATE TABLE Manager
(
    Manager_id    INT            NOT NULL AUTO_INCREMENT COMMENT '负责人ID',
    Store_id      INT            NOT NULL COMMENT '店铺ID',
    User_id       INT            NOT NULL COMMENT '用户ID',
    Position_id   INT            NOT NULL COMMENT '职位ID',
    Actual_salary DECIMAL(10, 2) NOT NULL COMMENT '实际薪资',
    PRIMARY KEY (Manager_id),
    FOREIGN KEY (Store_id) REFERENCES store (store_id),
    FOREIGN KEY (User_id) REFERENCES User (user_id),
    FOREIGN KEY (Position_id) REFERENCES Position (Position_id)
) COMMENT = '店内负责人表';


SELECT s1.drug_detail_id,
       COALESCE(s1.total_created, 0)                   as total_created,
       COALESCE(s2.total_sold, 0)                      as total_sold,
       (COALESCE(s2.total_sold, 0) - s1.total_created) as total_balance,
       s1.drug_detail_id                               as s1_drug_detail_id, -- 新添加的列
       sd.drug_id,
       sd.drug_name,
       sdd.drug_specification,
       sd.is_rx,
       s.brand_id,
       s.brand_name,
       tt.type_id,
       tt.type_name
FROM (SELECT ssb.drug_detail_id,
             SUM(IF(ssb.store_batch_status = 'CREATED', ssb.store_batch_purchase_quantity, 0)) AS total_created
      FROM sys_store_batch ssb
      WHERE ssb.store_batch_status = 'CREATED'
        AND ssb.store_id = 1
      GROUP BY ssb.drug_detail_id) s1
         LEFT JOIN
     (SELECT sb.drug_detail_id,
             SUM(IF(sb.batch_status = 'SOLD', sb.batch_existing_quantity, 0)) AS total_sold
      FROM sys_batch sb
      WHERE sb.batch_status = 'SOLD'
      GROUP BY sb.drug_detail_id) s2 ON s1.drug_detail_id = s2.drug_detail_id
         LEFT JOIN sys_drug_details sdd ON sdd.drug_detail_id = s1.drug_detail_id
         LEFT JOIN sys_drug sd ON sdd.drug_id = sd.drug_id
         LEFT JOIN sys_brand s ON sdd.brand_id = s.brand_id
         LEFT JOIN tb_type tt ON sd.type_id = tt.type_id;


SELECT ssb.store_batch_id,
       ssb.drug_detail_id,
       ssb.store_batch_id,
       ssb.store_batch_purchase_quantity
FROM sys_store_batch ssb
WHERE ssb.store_batch_status = 'CREATED'
  AND ssb.store_id = 1;


select *
from sys_batch sb
where sb.drug_detail_id = 1
  and sb.batch_status = 'SOLD'
order by sb.batch_production_date;

select ssb.store_batch_id,
       ssb.batch_id,
       ssb.drug_detail_id,
       ssb.batch_number,
       ssb.store_batch_purchase_quantity,
       COALESCE(ssb.store_batch_existing_quantity, 0) as store_batch_existing_quantity,
       ssb.store_batch_status,
       ssb.version,
       ssb.create_time,
       ssb.modified_time,
       ssb.store_batch_remark,
       sb.batch_production_date,
       sb.batch_validity_period
from sys_store_batch ssb
         left join sys_batch sb on ssb.batch_id = sb.batch_id
where ssb.drug_detail_id = 1
  and ssb.store_batch_status = 'SOLD'
  and ssb.store_id = 1