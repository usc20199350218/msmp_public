# 建表
DROP TABLE IF EXISTS sys_batch;
CREATE TABLE sys_batch
(
    `batch_id`                INT NOT NULL AUTO_INCREMENT COMMENT '批次Id',
    `drug_detail_id`          INT COMMENT '药品Id',
    `batch_number`            VARCHAR(255) COMMENT '批号编号;由供应商提供',
    `batch_production_date`   DATE COMMENT '生产日期',
    `batch_purchase_quantity` INT COMMENT '进货数量',
    `batch_existing_quantity` INT COMMENT '现存数量;出售从这里计算',
    `batch_status`            VARCHAR(32) COMMENT '批次状态',
    `batch_validity_period`   INT UNSIGNED COMMENT '有效期',
    `create_time`             DATETIME COMMENT '创建日期',
    `remark`                  VARCHAR(900) COMMENT '备注',
    `modified_time`           DATETIME COMMENT '更新时间',
    PRIMARY KEY (batch_id)
) COMMENT = '仓库表';

insert into medical.sys_batch (batch_id, drug_detail_id, batch_number, batch_production_date, batch_purchase_quantity,
                               batch_existing_quantity, batch_status, batch_validity_period, create_time, remark,
                               modified_time)
values (1, 1, '12222', '2023-03-22', 100, 80, 1, 3, '2023-03-22 19:34:15', null, '2023-03-22 19:34:18'),
       (2, 1, '12234', '2023-03-22', 100, 100, 1, 3, '2023-03-22 19:35:00', null, '2023-03-22 19:35:02');

# 初版查询
select *
from sys_drug sd1
         inner join
     (select sd.drug_name, sum(sb.batch_existing_quantity) as num
      from sys_drug sd
               left join
           sys_batch sb
           on sd.drug_id = sb.drug_detail_id
      group by drug_name) s on sd1.drug_name = s.drug_name;

# 确认版
# 语句使用了子查询，先对 sys_batch 表按照药品 ID 进行分组，并计算每个药品的总库存量。
# 然后再将这个子查询的结果与 sys_drug 表进行连接，以获取每种药品的名称、价格等其他信息，并添加上总库存量作为新的一列数据。
# select *
# from sys_drug sd1
#          inner join
#      (select sb.drug_detail_id, sum(sb.batch_existing_quantity) as num
#       from sys_batch sb
#       where sb.batch_status = 1
#       group by drug_detail_id
#      ) s on sd1.drug_detail_id = s.drug_detail_id;

# 精简版
# 语句则是直接将两个表进行连接，并使用 sum() 聚合函数计算每个药品的总库存量。
# 由于没有使用子查询，所以在结果中药品的信息会重复出现，需要使用 DISTINCT 或 GROUP BY 来去重。
select sd.*, sum(sb.batch_existing_quantity)
from sys_drug sd
         inner join sys_batch sb on sd.drug_id = sb.drug_detail_id
where sb.batch_status = 1;

select sdd.drug_detail_id,
       sdd.drug_details_status,
       sdd.drug_specification,
       sdd.drug_unit_price,
       sdd.drug_retail_price,
       sd.drug_id,
       sd.drug_name,
       sd.is_rx,
       sd.drug_status,
       s.quantity,
       b.brand_id,
       b.brand_name
from sys_drug_details sdd
         left join sys_drug sd on sdd.drug_id = sd.drug_id
         inner join (select sb.drug_detail_id, sum(sb.batch_existing_quantity) as quantity
                     from sys_batch sb
                     where sb.batch_status = 1
                     group by drug_id) s on sdd.drug_id = s.drug_id
         left join sys_brand b on sdd.brand_id = b.brand_id;


select sdd.drug_detail_id,
       sdd.drug_details_status,
       sdd.drug_specification,
       sdd.drug_unit_price,
       sdd.drug_retail_price,
       sd.drug_id,
       sd.drug_name,
       sd.is_rx,
       sd.drug_status,
       s.quantity,
       b.brand_id,
       b.brand_name
from sys_drug_details sdd
         left join sys_drug sd on sdd.drug_id = sd.drug_id
         inner join (select sb.drug_detail_id, sum(sb.batch_existing_quantity) as quantity
                     from sys_batch sb
                     where sb.batch_status = 1
                     group by drug_id) s on sdd.drug_id = s.drug_detail_id
         left join sys_brand b on sdd.brand_id = b.brand_id;

select sdd.drug_detail_id,
       sdd.drug_details_status,
       sdd.drug_specification,
       sdd.drug_unit_price,
       sdd.drug_retail_price,
       sd.drug_id,
       sd.drug_name,
       sd.is_rx,
       sd.drug_status,
       s.quantity,
       b.brand_id,
       b.brand_name
from medical.sys_drug_details sdd
         left join medical.sys_drug sd on sdd.drug_id = sd.drug_id
         inner join (select sb.drug_detail_id, sum(sb.batch_existing_quantity) as quantity
                     from medical.sys_batch sb <
                     where
                         >
                         < if test="batchStatus != null and batchStatus != ''"
                         >
                     where sb.batch_status = #{batchStatus}</if>
                         </
                     where
                         >
                         // 获得切入点方法 如果要执行 必须
                     group by drug_id) s on sdd.drug_id = s.drug_detail_id
         left join medical.sys_brand b on sdd.brand_id = b.brand_id;


# 修改版 最终版
select sdd.drug_detail_id,
       sdd.drug_specification,
       sdd.drug_unit_price,
       sdd.drug_retail_price,
       sd.drug_id,
       sd.drug_name,
       sd.is_rx,
       s.quantity,
       b.brand_id,
       b.brand_name
from medical.sys_drug_details sdd
         left join medical.sys_drug sd on sdd.drug_id = sd.drug_id
         inner join (select sb.drug_detail_id, sum(sb.batch_existing_quantity) as quantity
                     from medical.sys_batch sb
                     where sb.batch_status = 1
                     group by drug_detail_id) s on sdd.drug_detail_id = s.drug_detail_id
         left join medical.sys_brand b on sdd.brand_id = b.brand_id;


# to do 将BatchDTO 增加一个总（可用+进货）——total，对数量进行精确，变为可用——available，加一个进货num——purchase
select sdd.drug_detail_id,
       sdd.drug_specification,
       sdd.drug_unit_price,
       sdd.drug_retail_price,
       sd.drug_id,
       sd.drug_name,
       sd.is_rx,
       s.total,
       b.brand_id,
       b.brand_name
from medical.sys_drug_details sdd
         left join medical.sys_drug sd on sdd.drug_id = sd.drug_id
         inner join (select sb.drug_detail_id, sum(sb.batch_existing_quantity) as total
                     from medical.sys_batch sb
                     where sb.batch_status = 'SOLD'
                     group by drug_detail_id) s on sdd.drug_detail_id = s.drug_detail_id
         left join medical.sys_brand b on sdd.brand_id = b.brand_id;


select sdd.drug_detail_id,
       sdd.drug_specification,
       sdd.drug_unit_price,
       sdd.drug_retail_price,
       sd.drug_id,
       sd.drug_name,
       sd.is_rx,
       s.total_sold,
       s.total_normal_purchase, -- new field
       b.brand_id,
       b.brand_name
from medical.sys_drug_details sdd
         left join medical.sys_drug sd on sdd.drug_id = sd.drug_id
         inner join (select sb.drug_detail_id,
                            sum(case
                                    when sb.batch_status = 'SOLD' then sb.batch_existing_quantity
                                    else 0 end) as total_sold,
                            sum(case
                                    when sb.batch_status = 'NORMAL_PURCHASE' then sb.batch_existing_quantity
                                    else 0 end) as total_normal_purchase -- new field
                     from medical.sys_batch sb
                     where sb.batch_status in ('SOLD', 'NORMAL_PURCHASE') -- modified to include both statuses
                     group by drug_detail_id) s on sdd.drug_detail_id = s.drug_detail_id
         left join medical.sys_brand b on sdd.brand_id = b.brand_id;

select sdd.drug_detail_id,
       sdd.drug_specification,
       sdd.drug_unit_price,
       sdd.drug_retail_price,
       sd.drug_id,
       sd.drug_name,
       sd.is_rx,
       s.total_sold,
       s.total_normal_purchase,
       s.total_all, -- new field
       b.brand_id,
       b.brand_name
from medical.sys_drug_details sdd
         left join medical.sys_drug sd on sdd.drug_id = sd.drug_id
         inner join (select sb.drug_detail_id,
                            sum(case
                                    when sb.batch_status = 'SOLD' then sb.batch_existing_quantity
                                    else 0 end)             as total_sold,
                            sum(case
                                    when sb.batch_status = 'NORMAL_PURCHASE' then sb.batch_existing_quantity
                                    else 0 end)             as total_normal_purchase,
                            sum(sb.batch_existing_quantity) as total_all -- new field
                     from medical.sys_batch sb
                     where sb.batch_status in ('SOLD', 'NORMAL_PURCHASE')
                     group by drug_detail_id) s on sdd.drug_detail_id = s.drug_detail_id
         left join medical.sys_brand b on sdd.brand_id = b.brand_id;

select sdd.drug_detail_id,
       sdd.drug_specification,
       sdd.drug_unit_price,
       sdd.drug_retail_price,
       sd.drug_id,
       sd.drug_name,
       sd.is_rx,
       s.total_created,
       s.total_sold,
       s.total_normal_purchase,
       s.total_all,
       b.brand_id,
       b.brand_name
from medical.sys_drug_details sdd
         left join medical.sys_drug sd on sdd.drug_id = sd.drug_id
         inner join (select sb.drug_detail_id,
                            sum(case
                                    when sb.batch_status = 'CREATED' then sb.batch_existing_quantity
                                    else 0 end)             as total_created,
                            sum(case
                                    when sb.batch_status = 'SOLD' then sb.batch_existing_quantity
                                    else 0 end)             as total_sold,
                            sum(case
                                    when sb.batch_status = 'NORMAL_PURCHASE' then sb.batch_existing_quantity
                                    else 0 end)             as total_normal_purchase,
                            sum(sb.batch_existing_quantity) as total_all
                     from medical.sys_batch sb
                     where sb.batch_status in ('CREATED', 'SOLD', 'NORMAL_PURCHASE', 'FORBIDDEN')
                     group by drug_detail_id) s on sdd.drug_detail_id = s.drug_detail_id
         left join medical.sys_brand b on sdd.brand_id = b.brand_id;

select sdd.drug_detail_id,
       sdd.drug_specification,
       sdd.drug_unit_price,
       sdd.drug_retail_price,
       sd.drug_id,
       sd.drug_name,
       sd.is_rx,
       s.total_created,
       s.total_sold,
       s.total_normal_purchase,
       total_normal_forbidden,
       s.total_all,
       b.brand_id,
       b.brand_name
from medical.sys_drug_details sdd
         left join medical.sys_drug sd on sdd.drug_id = sd.drug_id
         inner join (select sb.drug_detail_id,
                            sum(case
                                    when sb.batch_status = 'CREATED' then sb.batch_existing_quantity
                                    else 0 end) as total_created,
                            sum(case
                                    when sb.batch_status = 'SOLD' then sb.batch_existing_quantity
                                    else 0 end) as total_sold,
                            sum(case
                                    when sb.batch_status = 'NORMAL_PURCHASE' then sb.batch_existing_quantity
                                    else 0 end) as total_normal_purchase,
                            sum(case
                                    when sb.batch_status = 'FORBIDDEN' then sb.batch_existing_quantity
                                    else 0 end) as total_normal_forbidden,
                            sum(case
                                    when sb.batch_status <> 'CREATED' then sb.batch_existing_quantity
                                    else 0 end) as total_all
                     from medical.sys_batch sb
                     where sb.batch_status in ('CREATED', 'SOLD', 'NORMAL_PURCHASE', 'FORBIDDEN')
                     group by drug_detail_id) s on sdd.drug_detail_id = s.drug_detail_id
         left join medical.sys_brand b on sdd.brand_id = b.brand_id;



select *
from sys_store_batch ssb
where ssb.store_batch_status = 'CREATED';



select sdd.drug_detail_id,
       sdd.drug_specification,
       sdd.drug_unit_price,
       sdd.drug_retail_price,
       sd.drug_id,
       sd.drug_name,
       sd.is_rx,
       s.total_created,
       s.total_sold,
       s.total_normal_purchase,
       s.total_normal_forbidden,
       s.total_all,
       b.brand_id,
       b.brand_name
from sys_drug_details sdd
         left join sys_drug sd on sdd.drug_id = sd.drug_id
         inner join (select sb.drug_detail_id,
                            sum(IF(sb.batch_status = 'CREATED', sb.batch_existing_quantity, 0))   as total_created,
                            sum(IF(sb.batch_status = 'SOLD', sb.batch_existing_quantity, 0))      as total_sold,
                            sum(IF(sb.batch_status = 'NORMAL_PURCHASE', sb.batch_existing_quantity,
                                   0))                                                            as total_normal_purchase,
                            sum(IF(sb.batch_status = 'FORBIDDEN', sb.batch_existing_quantity, 0)) as total_normal_forbidden,
                            sum(IF(sb.batch_status != 'CREATED', sb.batch_existing_quantity, 0))  as total_all
                     from sys_batch sb
                     where sb.batch_status in ('CREATED', 'SOLD', 'NORMAL_PURCHASE', 'FORBIDDEN')
                     group by drug_detail_id) s on sdd.drug_detail_id = s.drug_detail_id
         left join sys_brand b on sdd.brand_id = b.brand_id
where sdd.brand_id = 1
  and sd.is_rx = 0
  and sd.drug_name like '%%'
  and sd.drug_name like '%%'
  and sdd.drug_unit_price = 10
  and sdd.drug_retail_price = 12
  and sdd.drug_detail_id like '%%'
  and s.total_sold > 100
  and sd.type_id = 1;


select *
from sys_batch sb
where sb.batch_production_date;


update sys_batch
set batch_status ='EXPIRED'
where batch_id in
      (SELECT sb.batch_id
       FROM sys_batch sb
       WHERE DATE_ADD(sb.batch_production_date, INTERVAL sb.batch_validity_period MONTH) < CURDATE());


select *
from sys_batch sb
WHERE DATE_ADD(sb.batch_production_date, INTERVAL sb.batch_validity_period MONTH) < CURDATE()
