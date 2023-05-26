DROP TABLE IF EXISTS sys_store_batch;
CREATE TABLE sys_store_batch
(
    `store_batch_id`                INT NOT NULL AUTO_INCREMENT COMMENT '店内批次Id',
    `store_id`                      VARCHAR(255) COMMENT '店铺Id',
    `batch_id`                      INT COMMENT '对应批次Id',
    `drug_detail_id`                INT COMMENT '药品详情Id',
    `batch_number`                  VARCHAR(255) COMMENT '对应批次编号',
    `store_batch_purchase_quantity` INT COMMENT '请求数量',
    `store_batch_existing_quantity` INT COMMENT '现存数量',
    `store_batch_status`            VARCHAR(32) COMMENT '状态',
    `version`                       INT UNSIGNED COMMENT 'version',
    `create_time`                   DATETIME COMMENT '创建时间',
    `modified_time`                 DATETIME COMMENT '更新时间',
    `store_batch_remark`            VARCHAR(900) COMMENT '备注',
    PRIMARY KEY (store_batch_id)
) COMMENT = '店内批次';

insert into sys_store_batch
values (null, 1, 1, 1, 1, 200, 200, 'SOLD', 2, NOW(), NOW(), NULL);
insert into sys_store_batch
values (null, 2, 1, 1, 1, 100, 100, 'SOLD', 2, NOW(), NOW(), NULL);



select ssb.store_batch_id,
       ssb.store_batch_status,
       ssb.version,
       ssb.create_time,
       ssb.modified_time,
       ssb.store_batch_remark,
       sb.batch_id,
       sb.batch_number,
       sb.batch_production_date,
       sb.batch_validity_period,
       sdd.drug_detail_id,
       sdd.drug_specification,
       sdd.drug_unit_price,
       sdd.drug_retail_price,
       sd.drug_id,
       sd.drug_name,
       sd.is_rx,
       s.brand_id,
       s.brand_name,
       s.user_id
from sys_store_batch ssb
         left join sys_batch sb on ssb.batch_id = sb.batch_id
         left join sys_drug_details sdd on sb.drug_detail_id = sdd.drug_detail_id
         left join sys_drug sd on sdd.drug_id = sd.drug_id
         left join sys_brand s on sdd.brand_id = s.brand_id;


select ssb.store_batch_id,
       ssb.request_quantity,
       ssb.actual_amount,
       ssb.existing_quantity,
       ssb.store_batch_status,
       ssb.version,
       ssb.create_time,
       ssb.modified_time,
       ssb.store_batch_remark,
       sb.batch_id,
       sb.batch_number,
       sb.batch_production_date,
       sb.batch_validity_period,
       sdd.drug_detail_id,
       sdd.drug_specification,
       sdd.drug_unit_price,
       sdd.drug_retail_price,
       sd.drug_id,
       sd.drug_name,
       sd.is_rx,
       s.brand_id,
       s.brand_name,
       s.user_id,
       sum(case when sbb.store_batch_status = 'IN_STORAGE' then sbb.existing_quantity else 0 end) as total_in_storage,
       sum(case
               when sbb.store_batch_status = 'OUT_OF_STORAGE' then sbb.existing_quantity
               else 0 end)                                                                        as total_out_of_storage,
       sum(case
               when sbb.store_batch_status in ('IN_STORAGE', 'OUT_OF_STORAGE') then sbb.existing_quantity
               else 0 end)                                                                        as total_all
from sys_store_batch ssb
         left join sys_batch sb on ssb.batch_id = sb.batch_id
         left join sys_drug_details sdd on sb.drug_detail_id = sdd.drug_detail_id
         left join sys_drug sd on sdd.drug_id = sd.drug_id
         left join sys_brand s on sdd.brand_id = s.brand_id
         left join (select batch_id,
                           store_batch_status,
                           sum(existing_quantity) as existing_quantity
                    from sys_store_batch
                    where store_batch_status in ('IN_STORAGE', 'OUT_OF_STORAGE')
                    group by batch_id, store_batch_status) sbb on sb.batch_id = sbb.batch_id
group by ssb.store_batch_id,
         ssb.request_quantity,
         ssb.actual_amount,
         ssb.existing_quantity,
         ssb.store_batch_status,
         ssb.version,
         ssb.create_time,
         ssb.modified_time,
         ssb.store_batch_remark,
         sb.batch_id,
         sb.batch_number,
         sb.batch_production_date,
         sb.batch_validity_period,
         sdd.drug_detail_id,
         sdd.drug_specification,
         sdd.drug_unit_price,
         sdd.drug_retail_price,
         sd.drug_id,
         sd.drug_name,
         sd.is_rx,
         s.brand_id,
         s.brand_name,
         s.user_id;

select ssb.store_batch_id,
       ssb.drug_detail_id,
       num.total_created,
       num.total_sold,
       num.total_expired,
       ssb.batch_id
from sys_store_batch ssb
         inner join (select drug_detail_id,
                            sum(case
                                    when n.store_batch_status = 'CREATED' then n.store_batch_purchase_quantity
                                    else 0 end) as total_created,
                            sum(case
                                    when n.store_batch_status = 'SOLD' then n.store_batch_existing_quantity
                                    else 0 end) as total_sold,
                            sum(case
                                    when n.store_batch_status = 'NORMAL_PURCHASE' then n.store_batch_purchase_quantity
                                    else 0 end) as total_normal_purchase,
                            sum(case
                                    when n.store_batch_status = 'EXPIRED' then n.store_batch_purchase_quantity
                                    else 0 end) as total_expired,
                            sum(case
                                    when n.store_batch_status = 'FORBIDDEN' then n.store_batch_existing_quantity
                                    else 0 end) as total_normal_forbidden,
                            sum(case
                                    when n.store_batch_status != 'CREATED' then n.store_batch_existing_quantity
                                    else 0 end) as total_all
                     from sys_store_batch n
                     group by n.drug_detail_id) num on ssb.drug_detail_id = num.drug_detail_id
;

# from medical.sys_drug_details sdd
#          left join medical.sys_drug sd on sdd.drug_id = sd.drug_id
#          inner join (
#     select sb.drug_detail_id,
#            sum(case when sb.batch_status = 'CREATED' then sb.batch_existing_quantity else 0 end) as total_created,
#            sum(case when sb.batch_status = 'SOLD' then sb.batch_existing_quantity else 0 end)    as total_sold,
#            sum(case when sb.batch_status = 'NORMAL_PURCHASE' then sb.batch_existing_quantity else 0 end) as total_normal_purchase,
#            sum(case when sb.batch_status = 'FORBIDDEN' then sb.batch_existing_quantity else 0 end) as total_normal_forbidden,
#            sum(case when sb.batch_status <> 'CREATED' then sb.batch_existing_quantity else 0 end) as total_all
#     from medical.sys_batch sb
#     where sb.batch_status in ('CREATED', 'SOLD', 'NORMAL_PURCHASE', 'FORBIDDEN')
#     group by drug_detail_id
# ) s on sdd.drug_detail_id = s.drug_detail_id
#          left join medical.sys_brand b on sdd.brand_id = b.brand_id;


select sum(case
               when ssb.store_batch_status = 'CREATED' then ssb.store_batch_purchase_quantity
               else 0 end) as total_created,
       sum(case
               when ssb.store_batch_status = 'NORMAL_PURCHASE' then ssb.store_batch_purchase_quantity
               else 0 end) as total_normal_purchase,
       sum(case
               when ssb.store_batch_status = 'SOLD' then ssb.store_batch_existing_quantity
               else 0 end) as total_sold,
       sum(case
               when ssb.store_batch_status = 'EXPIRED' then ssb.store_batch_existing_quantity
               else 0 end) as total_expired,
       sum(case
               when ssb.store_batch_status = 'FORBIDDEN' then ssb.store_batch_existing_quantity
               else 0 end) as total_normal_forbidden,
       sum(case
               when ssb.store_batch_status != 'CREATED' then ssb.store_batch_existing_quantity
               else 0 end) as total_all,
       sdd.drug_detail_id,
       sdd.drug_specification,
       sdd.drug_unit_price,
       sdd.drug_retail_price,
       sd.drug_id,
       sd.drug_name,
       sd.is_rx,
       sb.brand_id,
       sb.brand_name,
       sb.user_id
from sys_store_batch ssb
         left join sys_drug_details sdd on ssb.drug_detail_id = sdd.drug_detail_id
         left join sys_drug sd on sdd.drug_id = sd.drug_id
         left join sys_brand sb on sdd.brand_id = sb.brand_id
group by ssb.drug_detail_id;

#  to do 修改店内批次DTO


select ssb.store_batch_id,
       ssb.batch_id,
       ssb.drug_detail_id,
       ssb.batch_number,
       ssb.store_batch_purchase_quantity,
       ssb.store_batch_existing_quantity,
       ssb.store_batch_status,
       ssb.version,
       ssb.create_time,
       ssb.modified_time,
       ssb.store_batch_remark,
       sb.batch_production_date,
       sb.batch_validity_period
from sys_store_batch ssb
         left join sys_batch sb on ssb.batch_id = sb.batch_id;



SELECT COUNT(*)
FROM (SELECT sum(CASE
                     WHEN ssb.store_batch_status = 'CREATED' THEN ssb.store_batch_purchase_quantity
                     ELSE 0 END) AS total_created,
             sum(CASE
                     WHEN ssb.store_batch_status = 'NORMAL_PURCHASE' THEN ssb.store_batch_purchase_quantity
                     ELSE 0 END) AS total_normal_purchase,
             sum(CASE
                     WHEN ssb.store_batch_status = 'SOLD' THEN ssb.store_batch_existing_quantity
                     ELSE 0 END) AS total_sold,
             sum(CASE
                     WHEN ssb.store_batch_status = 'EXPIRED' THEN ssb.store_batch_existing_quantity
                     ELSE 0 END) AS total_expired,
             sum(CASE
                     WHEN ssb.store_batch_status = 'FORBIDDEN' THEN ssb.store_batch_existing_quantity
                     ELSE 0 END) AS total_normal_forbidden,
             sum(CASE
                     WHEN ssb.store_batch_status != 'CREATED' THEN ssb.store_batch_existing_quantity
                     ELSE 0 END) AS total_all,
             sdd.drug_detail_id,
             sdd.drug_specification,
             sdd.drug_unit_price,
             sdd.drug_retail_price,
             sd.drug_id,
             sd.drug_name,
             sd.is_rx,
             sb.brand_id,
             sb.brand_name,
             sb.user_id
      FROM sys_store_batch ssb
               LEFT JOIN sys_drug_details sdd ON ssb.drug_detail_id = sdd.drug_detail_id
               LEFT JOIN sys_drug sd ON sdd.drug_id = sd.drug_id
               LEFT JOIN sys_brand sb ON sdd.brand_id = sb.brand_id
      WHERE ssb.store_id = 5
      GROUP BY ssb.drug_detail_id) TOTAL;
select *
from sys_store_batch ssb
where ssb.store_id = 5;

select *
from sys_store_batch ssb
where ssb.store_batch_status = 'CREATED';

select ssb.store_id, ts.store_name
from sys_store_batch ssb
         left join tb_store ts on ssb.store_id = ts.store_id
where ssb.store_batch_status = 'CREATED'
group by ssb.store_id;

SELECT ssb.store_id, ts.store_name
FROM sys_store_batch ssb
         JOIN tb_store ts ON ssb.store_id = ts.store_id
WHERE ssb.store_batch_status = 'CREATED';

SELECT ssb.store_id, ts.store_name
FROM sys_store_batch ssb
         JOIN tb_store ts ON ssb.store_id = ts.store_id
WHERE ssb.store_batch_status = 'CREATED'
GROUP BY ssb.store_id;

SELECT DISTINCT ssb.store_id, ts.store_name
FROM sys_store_batch ssb
         JOIN tb_store ts ON ssb.store_id = ts.store_id
WHERE ssb.store_batch_status = 'CREATED';


# 仓库查看店铺请求
# 根据storeId

select ssb.drug_detail_id,
       sum(case
               WHEN ssb.store_batch_status = 'CREATED' THEN ssb.store_batch_purchase_quantity
               ELSE 0 END) AS total_sold,
       sum(case
               WHEN ssb.store_batch_status = 'CREATED' THEN ssb.store_batch_purchase_quantity
               ELSE 0 END) - sb.batch_existing_quantity
from sys_store_batch ssb
         left join sys_drug_details sdd on ssb.drug_detail_id = sdd.drug_detail_id
         left join sys_batch sb on sdd.drug_detail_id = sb.drug_detail_id
where ssb.store_id = 1
  and ssb.store_batch_status = 'CREATED'
group by ssb.drug_detail_id;



select ssb.drug_detail_id,
       sum(case
               WHEN ssb.store_batch_status = 'CREATED' THEN ssb.store_batch_purchase_quantity
               ELSE 0 END) AS total_created
from sys_store_batch ssb
where ssb.store_id = 1
  and ssb.store_batch_status = 'CREATED'
group by ssb.drug_detail_id;

select sb.drug_detail_id,
       sum(CASE
               WHEN sb.batch_status = 'SOLD' THEN sb.batch_existing_quantity
               ELSE 0 END) AS total_sold
from sys_batch sb
where sb.batch_status = 'SOLD'
group by sb.drug_detail_id;



SELECT drug_detail_id,
       SUM(total_created)           AS total_created,
       SUM(total_sold)              AS total_sold,
       (total_sold - total_created) AS num
FROM (SELECT ssb.drug_detail_id,
             SUM(CASE
                     WHEN ssb.store_batch_status = 'CREATED' THEN ssb.store_batch_purchase_quantity
                     ELSE 0 END) AS total_created,
             0                   AS total_sold
      FROM sys_store_batch ssb
      WHERE ssb.store_batch_status = 'CREATED'
      GROUP BY ssb.drug_detail_id
      UNION ALL
      SELECT sb.drug_detail_id,
             0                   AS total_created,
             SUM(CASE
                     WHEN sb.batch_status = 'SOLD' THEN sb.batch_existing_quantity
                     ELSE 0 END) AS total_sold
      FROM sys_batch sb
      WHERE sb.batch_status = 'SOLD'
      GROUP BY sb.drug_detail_id) AS sales
GROUP BY drug_detail_id;



SELECT drug_detail_id,
       SUM(total_created)                   AS total_created,
       SUM(total_sold)                      AS total_sold,
       SUM(total_sold) - SUM(total_created) AS diff
FROM (SELECT ssb.drug_detail_id,
             SUM(CASE
                     WHEN ssb.store_batch_status = 'CREATED' THEN ssb.store_batch_purchase_quantity
                     ELSE 0 END) AS total_created,
             0                   AS total_sold
      FROM sys_store_batch ssb
      WHERE ssb.store_batch_status = 'CREATED'
      GROUP BY ssb.drug_detail_id
      UNION ALL
      SELECT sb.drug_detail_id,
             0                   AS total_created,
             SUM(CASE
                     WHEN sb.batch_status = 'SOLD' THEN sb.batch_existing_quantity
                     ELSE 0 END) AS total_sold
      FROM sys_batch sb
      WHERE sb.batch_status = 'SOLD'
      GROUP BY sb.drug_detail_id) AS sales
GROUP BY drug_detail_id;


# 左连接版本 ——最终确认版
select s1.drug_detail_id,
       COALESCE(s1.total_created, 0)                   as total_created,
       COALESCE(s2.total_sold, 0)                      as total_sold,
       (COALESCE(s2.total_sold, 0) - s1.total_created) as total_balance,
       sdd.drug_details_status,
       sdd.drug_specification,
       sd.drug_id,
       sd.drug_name,
       sd.is_rx,
       s.brand_id,
       s.brand_name,
       tt.type_id,
       tt.type_name
from (SELECT ssb.drug_detail_id,
             SUM(CASE
                     WHEN ssb.store_batch_status = 'CREATED' THEN ssb.store_batch_purchase_quantity
                     ELSE 0 END) AS total_created
      FROM sys_store_batch ssb
      WHERE ssb.store_batch_status = 'CREATED'
        and ssb.store_id = 1
      GROUP BY ssb.drug_detail_id) s1
         left join
     (SELECT sb.drug_detail_id,
             SUM(CASE
                     WHEN sb.batch_status = 'SOLD' THEN sb.batch_existing_quantity
                     ELSE 0 END) AS total_sold
      FROM sys_batch sb
      WHERE sb.batch_status = 'SOLD'
      GROUP BY sb.drug_detail_id) s2
     on s1.drug_detail_id = s2.drug_detail_id
         left join sys_drug_details sdd on sdd.drug_detail_id = s1.drug_detail_id
         left join sys_drug sd on sdd.drug_id = sd.drug_id
         left join sys_brand s on sdd.brand_id = s.brand_id
         left join tb_type tt on sd.type_id = tt.type_id;


# 总览
select s1.drug_detail_id,
       COALESCE(s1.total_created, 0)                   as total_created,
       COALESCE(s2.total_sold, 0)                      as total_sold,
       (COALESCE(s2.total_sold, 0) - s1.total_created) as total_balance,
       sd.drug_id,
       sd.drug_name,
       sdd.drug_specification,
       sd.is_rx,
       s.brand_id,
       s.brand_name,
       tt.type_id,
       tt.type_name
from (SELECT ssb.drug_detail_id,
             SUM(IF(ssb.store_batch_status = 'CREATED', ssb.store_batch_purchase_quantity, 0)) AS total_created
      FROM sys_store_batch ssb
      WHERE ssb.store_batch_status = 'CREATED'
        and ssb.store_id = 1
      GROUP BY ssb.drug_detail_id) s1
         left join
     (SELECT sb.drug_detail_id,
             SUM(IF(sb.batch_status = 'SOLD', sb.batch_existing_quantity, 0)) AS total_sold
      FROM sys_batch sb
      WHERE sb.batch_status = 'SOLD'
      GROUP BY sb.drug_detail_id) s2 on s1.drug_detail_id = s2.drug_detail_id
         left join sys_drug_details sdd on sdd.drug_detail_id = s1.drug_detail_id
         left join sys_drug sd on sdd.drug_id = sd.drug_id
         left join sys_brand s on sdd.brand_id = s.brand_id
         left join tb_type tt on sd.type_id = tt.type_id;

select *
from sys_store_batch ssb
where ssb.store_id = 1
  and ssb.store_batch_status = 'CREATED';


# 总览 备选
SELECT DISTINCT s1.drug_detail_id,
                COALESCE(s1.total_created, 0)                   as total_created,
                COALESCE(s2.total_sold, 0)                      as total_sold,
                (COALESCE(s2.total_sold, 0) - s1.total_created) as total_balance,
                sdd.drug_specification,
                sd.drug_id,
                sd.drug_name,
                sd.is_rx,
                s.brand_id,
                s.brand_name,
                tt.type_id,
                tt.type_name,
                b.store_batch_status
FROM (SELECT ssb.drug_detail_id,
             SUM(IF(ssb.store_batch_status = 'CREATED', ssb.store_batch_purchase_quantity, 0)) AS total_created
      FROM sys_store_batch ssb
      WHERE ssb.store_batch_status = 'CREATED'
        AND ssb.store_id = 1
      GROUP BY ssb.drug_detail_id) s1
         LEFT JOIN (SELECT sb.drug_detail_id,
                           SUM(IF(sb.batch_status = 'SOLD', sb.batch_existing_quantity, 0)) AS total_sold
                    FROM sys_batch sb
                    WHERE sb.batch_status = 'SOLD'
                    GROUP BY sb.drug_detail_id) s2 ON s1.drug_detail_id = s2.drug_detail_id
         LEFT JOIN sys_drug_details sdd ON sdd.drug_detail_id = s1.drug_detail_id
         LEFT JOIN sys_drug sd ON sdd.drug_id = sd.drug_id
         LEFT JOIN sys_brand s ON sdd.brand_id = s.brand_id
         LEFT JOIN tb_type tt ON sd.type_id = tt.type_id
         LEFT JOIN (SELECT store_batch_id,
                           store_id,
                           batch_id,
                           drug_detail_id,
                           batch_number,
                           store_batch_purchase_quantity,
                           store_batch_existing_quantity,
                           store_batch_status,
                           version,
                           create_time,
                           modified_time,
                           store_batch_remark
                    FROM sys_store_batch
                    WHERE store_id = 1) b ON s1.drug_detail_id = b.drug_detail_id
ORDER BY s1.drug_detail_id;

select *
from sys_store_batch ssb
where ssb.store_batch_status = 'CREATED'
  and ssb.store_id = 2;

# 联合查询版本——更优
SELECT drug_detail_id,
       SUM(total_created)                   AS total_created,
       SUM(total_sold)                      AS total_sold,
       SUM(total_sold) - SUM(total_created) AS diff
FROM (SELECT ssb.drug_detail_id,
             SUM(CASE
                     WHEN ssb.store_batch_status = 'CREATED' THEN ssb.store_batch_purchase_quantity
                     ELSE 0 END) AS total_created,
             0                   AS total_sold
      FROM sys_store_batch ssb
      WHERE ssb.store_batch_status = 'CREATED'
      GROUP BY ssb.drug_detail_id
      UNION ALL
      SELECT sb.drug_detail_id,
             0                   AS total_created,
             SUM(CASE
                     WHEN sb.batch_status = 'SOLD' THEN sb.batch_existing_quantity
                     ELSE 0 END) AS total_sold
      FROM sys_batch sb
      WHERE sb.batch_status = 'SOLD'
      GROUP BY sb.drug_detail_id) AS sales
GROUP BY drug_detail_id;

# 总览 去除零
SELECT drug_detail_id,
       SUM(total_created)                   AS total_created,
       SUM(total_sold)                      AS total_sold,
       SUM(total_sold) - SUM(total_created) AS diff
FROM (SELECT ssb.drug_detail_id,
             SUM(CASE
                     WHEN ssb.store_batch_status = 'CREATED' THEN ssb.store_batch_purchase_quantity
                     ELSE 0 END) AS total_created,
             0                   AS total_sold
      FROM sys_store_batch ssb
      WHERE ssb.store_batch_status = 'CREATED'
      GROUP BY ssb.drug_detail_id
      UNION ALL
      SELECT sb.drug_detail_id,
             0                   AS total_created,
             SUM(CASE
                     WHEN sb.batch_status = 'SOLD' THEN sb.batch_existing_quantity
                     ELSE 0 END) AS total_sold
      FROM sys_batch sb
      WHERE sb.batch_status = 'SOLD'
      GROUP BY sb.drug_detail_id) AS sales
where sales.total_created > 0
GROUP BY drug_detail_id;

SELECT drug_detail_id,
       SUM(created)                   AS created,
       SUM(total_sold)                AS total_sold,
       SUM(total_sold) - SUM(created) AS diff
FROM (SELECT ssb.drug_detail_id,
             ssb.store_batch_purchase_quantity AS created,
             0                                 AS total_sold
      FROM sys_store_batch ssb
      WHERE ssb.store_batch_status = 'CREATED'
      UNION ALL
      SELECT sb.drug_detail_id,
             0                   AS total_created,
             SUM(CASE
                     WHEN sb.batch_status = 'SOLD' THEN sb.batch_existing_quantity
                     ELSE 0 END) AS total_sold
      FROM sys_batch sb
      WHERE sb.batch_status = 'SOLD'
      GROUP BY sb.drug_detail_id) AS sales
GROUP BY drug_detail_id;

# 详情 不需要确认详情药品id的请求详情
SELECT s1.drug_detail_id,
       s1.total_created,
       COALESCE(s2.total_sold, 0)                      as total_sold,
       (COALESCE(s2.total_sold, 0) - s1.total_created) as num
FROM (SELECT ssb.drug_detail_id, ssb.store_batch_purchase_quantity AS total_created
      FROM sys_store_batch ssb
      WHERE ssb.store_batch_status = 'CREATED') s1
         LEFT JOIN (SELECT sb.drug_detail_id, SUM(sb.batch_existing_quantity) AS total_sold
                    FROM sys_batch sb
                    WHERE sb.batch_status = 'SOLD'
                    GROUP BY sb.drug_detail_id) s2 ON s1.drug_detail_id = s2.drug_detail_id;

# 缺陷版
select s1.drug_detail_id, s1.total_created, s2.total_sold, (s2.total_sold - s1.total_created) as num
from (SELECT ssb.drug_detail_id,
             ssb.store_batch_purchase_quantity AS total_created
      FROM sys_store_batch ssb
      WHERE ssb.store_batch_status = 'CREATED') s1
         left join (SELECT sb.drug_detail_id,
                           SUM(CASE
                                   WHEN sb.batch_status = 'SOLD' THEN sb.batch_existing_quantity
                                   ELSE 0 END) AS total_sold
                    FROM sys_batch sb
                    WHERE sb.batch_status = 'SOLD'
                    GROUP BY sb.drug_detail_id) s2 on s1.drug_detail_id = s2.drug_detail_id;


# 左连接 去零 详情
SELECT s1.drug_detail_id,
       s1.total_created,
       COALESCE(s2.total_sold, 0)                      as total_sold,
       (COALESCE(s2.total_sold, 0) - s1.total_created) as num
FROM (SELECT ssb.drug_detail_id, ssb.store_batch_purchase_quantity AS total_created
      FROM sys_store_batch ssb
      WHERE ssb.store_batch_status = 'CREATED'
        and ssb.store_id = 1) s1
         LEFT JOIN (SELECT sb.drug_detail_id, SUM(sb.batch_existing_quantity) AS total_sold
                    FROM sys_batch sb
                    WHERE sb.batch_status = 'SOLD'
                    GROUP BY sb.drug_detail_id) s2 ON s1.drug_detail_id = s2.drug_detail_id
where s1.total_created > 0;

# 联合查询 详情版本 false
SELECT sales.drug_detail_id,
       COALESCE(sales.total_created, 0)                                 AS total_created,
       COALESCE(sales.total_sold, 0)                                    AS total_sold,
       COALESCE(sales.total_sold, 0) - COALESCE(sales.total_created, 0) AS diff
FROM (SELECT ssb.drug_detail_id,
             SUM(CASE
                     WHEN ssb.store_batch_status = 'CREATED' THEN ssb.store_batch_purchase_quantity
                     ELSE 0 END) AS total_created,
             0                   AS total_sold
      FROM sys_store_batch ssb
      WHERE ssb.store_batch_status = 'CREATED'
      GROUP BY ssb.drug_detail_id
      UNION ALL
      SELECT sb.drug_detail_id,
             0                   AS total_created,
             SUM(CASE
                     WHEN sb.batch_status = 'SOLD' THEN sb.batch_existing_quantity
                     ELSE 0 END) AS total_sold
      FROM sys_batch sb
      WHERE sb.batch_status = 'SOLD'
      GROUP BY sb.drug_detail_id) AS sales
GROUP BY sales.drug_detail_id;


SELECT drug_detail_id,
       SUM(CASE
               WHEN store_batch_status = 'CREATED' THEN store_batch_purchase_quantity
               ELSE 0 END)                       AS total_created,
       SUM(CASE
               WHEN batch_status = 'SOLD' THEN batch_existing_quantity
               ELSE 0 END)                       AS total_sold,
       SUM(CASE
               WHEN batch_status = 'SOLD' THEN batch_existing_quantity
               ELSE 0 END) - SUM(CASE
                                     WHEN store_batch_status = 'CREATED' THEN store_batch_purchase_quantity
                                     ELSE 0 END) AS diff
FROM (SELECT ssb.drug_detail_id,
             ssb.store_batch_status,
             ssb.store_batch_purchase_quantity,
             0    AS batch_existing_quantity,
             NULL AS batch_status
      FROM sys_store_batch ssb
      UNION ALL
      SELECT sb.drug_detail_id,
             NULL AS store_batch_status,
             NULL AS store_batch_purchase_quantity,
             sb.batch_existing_quantity,
             sb.batch_status
      FROM sys_batch sb) AS sales
GROUP BY drug_detail_id;

# 查询总览
select s1.drug_detail_id,
       COALESCE(s1.total_created, 0)                   as total_created,
       COALESCE(s2.total_sold, 0)                      as total_sold,
       (COALESCE(s2.total_sold, 0) - s1.total_created) as total_balance,
       sd.drug_id,
       sd.drug_name,
       sdd.drug_specification,
       sd.is_rx,
       s.brand_id,
       s.brand_name,
       tt.type_id,
       tt.type_name
from (SELECT ssb.drug_detail_id,
             SUM(IF(ssb.store_batch_status = 'CREATED', ssb.store_batch_purchase_quantity, 0)) AS total_created
      FROM sys_store_batch ssb
      WHERE ssb.store_batch_status = 'CREATED'
        and ssb.store_id = 1
      GROUP BY ssb.drug_detail_id) s1
         left join
     (SELECT sb.drug_detail_id,
             SUM(IF(sb.batch_status = 'SOLD', sb.batch_existing_quantity, 0)) AS total_sold
      FROM sys_batch sb
      WHERE sb.batch_status = 'SOLD'
      GROUP BY sb.drug_detail_id) s2 on s1.drug_detail_id = s2.drug_detail_id
         left join sys_drug_details sdd on sdd.drug_detail_id = s1.drug_detail_id
         left join sys_drug sd on sdd.drug_id = sd.drug_id
         left join sys_brand s on sdd.brand_id = s.brand_id
         left join tb_type tt on sd.type_id = tt.type_id;

# 获取详情 —— 优化 —— 左连接
SELECT s1.drug_detail_id,
       s1.total_created,
       COALESCE(s2.total_sold, 0)                      as total_sold,
       (COALESCE(s2.total_sold, 0) - s1.total_created) as num
FROM (SELECT ssb.drug_detail_id, ssb.store_batch_purchase_quantity AS total_created
      FROM sys_store_batch ssb
      WHERE ssb.store_batch_status = 'CREATED'
        and ssb.store_id = 1) s1
         LEFT JOIN (SELECT sb.drug_detail_id, SUM(sb.batch_existing_quantity) AS total_sold
                    FROM sys_batch sb
                    WHERE sb.batch_status = 'SOLD'
                    GROUP BY sb.drug_detail_id) s2 ON s1.drug_detail_id = s2.drug_detail_id
where s1.total_created > 0;

# 查询详情 —— 左连接版本

select s1.store_batch_id,
       s1.drug_detail_id,
       COALESCE(s1.total_created, 0)                   as total_created,
       COALESCE(s2.total_sold, 0)                      as total_sold,
       (COALESCE(s2.total_sold, 0) - s1.total_created) as total_balance,
       sd.drug_id,
       sd.drug_name,
       sdd.drug_specification,
       sd.is_rx,
       s.brand_id,
       s.brand_name,
       tt.type_id,
       tt.type_name
from (SELECT ssb.store_batch_id,
             ssb.drug_detail_id,
             ssb.store_batch_purchase_quantity as total_created
      FROM sys_store_batch ssb
      WHERE ssb.store_batch_status = 'CREATED'
        and ssb.store_id = 2
        and ssb.drug_detail_id = 1) s1
         left join
     (SELECT sb.drug_detail_id,
             SUM(IF(sb.batch_status = 'SOLD', sb.batch_existing_quantity, 0)) AS total_sold
      FROM sys_batch sb
      WHERE sb.batch_status = 'SOLD'
      GROUP BY sb.drug_detail_id) s2 on s1.drug_detail_id = s2.drug_detail_id
         left join sys_drug_details sdd on sdd.drug_detail_id = s1.drug_detail_id
         left join sys_drug sd on sdd.drug_id = sd.drug_id
         left join sys_brand s on sdd.brand_id = s.brand_id
         left join tb_type tt on sd.type_id = tt.type_id;

select sum(case
               when ssb.store_batch_status = 'CREATED' then ssb.store_batch_purchase_quantity
               else 0 end) as total_created,
       sum(case
               when ssb.store_batch_status = 'NORMAL_PURCHASE' then ssb.store_batch_purchase_quantity
               else 0 end) as total_normal_purchase,
       sum(case
               when ssb.store_batch_status = 'SOLD' then ssb.store_batch_existing_quantity
               else 0 end) as total_sold,
       sum(case
               when ssb.store_batch_status = 'EXPIRED' then ssb.store_batch_existing_quantity
               else 0 end) as total_expired,
       sum(case
               when ssb.store_batch_status = 'FORBIDDEN' and ssb.store_batch_existing_quantity != 0
                   then ssb.store_batch_existing_quantity
               else 0 end) as total_normal_forbidden,
       sum(case
               when ssb.store_batch_status != 'CREATED' then ssb.store_batch_existing_quantity
               else 0 end) as total_all,
       sdd.drug_detail_id,
       sdd.drug_specification,
       sdd.drug_unit_price,
       sdd.drug_retail_price,
       sd.drug_id,
       sd.drug_name,
       sd.is_rx,
       sb.brand_id,
       sb.brand_name,
       sb.user_id
from sys_store_batch ssb
         left join sys_drug_details sdd on ssb.drug_detail_id = sdd.drug_detail_id
         left join sys_drug sd on sdd.drug_id = sd.drug_id
         left join sys_brand sb on sdd.brand_id = sb.brand_id
where ssb.store_id = 2
group by ssb.drug_detail_id;

select *
from sys_store_batch ssb
where ssb.store_id = 2;