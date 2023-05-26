select ssb.store_batch_id,
       ssb.store_batch_existing_quantity,
       sb.batch_id,
       sb.batch_number,
       sb.batch_production_date,
       sdd.drug_detail_id,
       sdd.create_time,
       sdd.drug_specification,
       sdd.drug_retail_price,
       sd.drug_id,
       sd.drug_name,
       sd.is_rx,
       s.brand_id,
       s.brand_name
from sys_store_batch ssb
         left join sys_batch sb on ssb.batch_id = sb.batch_id
         left join sys_drug_details sdd on ssb.drug_detail_id = sdd.drug_detail_id
         left join sys_drug sd on sdd.drug_id = sd.drug_id
         left join sys_brand s on sdd.brand_id = s.brand_id
where ssb.store_batch_status = 'SOLD'
  and ssb.store_id = 1
  and sd.drug_name like '阿莫西林%';

select ssb.store_batch_id,
       ssb.store_batch_existing_quantity,
       sb.batch_id,
       sb.batch_number,
       sb.batch_production_date,
       sdd.drug_detail_id,
       sdd.create_time,
       sdd.drug_specification,
       sdd.drug_retail_price,
       sd.drug_id,
       sd.drug_name,
       sd.is_rx,
       s.brand_id,
       s.brand_name
from sys_store_batch ssb
         left join sys_batch sb on ssb.batch_id = sb.batch_id
         left join sys_drug_details sdd on ssb.drug_detail_id = sdd.drug_detail_id
         left join sys_drug sd on sdd.drug_id = sd.drug_id
         left join sys_brand s on sdd.brand_id = s.brand_id
where ssb.store_batch_status = 'SOLD'
  and ssb.store_id = 1
  and sdd.drug_number like 'amxl%'