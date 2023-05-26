select sdd.drug_detail_id,
       sdd.drug_id,
       sdd.brand_id,
       sdd.drug_details_status,
       sdd.drug_specification,
       sdd.drug_unit_price,
       sdd.drug_retail_price,
       sdd.create_time,
       sdd.modified_time,
       sdd.drug_number,
       sdd.drug_detail_path,
       sd.drug_id,
       sd.drug_name,
       sd.is_rx,
       sd.type_id,
       sb.brand_name,
       tt.type_name,
       ssb2.num
from sys_drug_details sdd
         right join
     (select ssb.drug_detail_id, sum(ssb.drug_detail_id) as num
      from sys_store_batch ssb
      where ssb.store_id = 1
        and ssb.store_batch_status = 'SOLD'
      group by ssb.drug_detail_id) as ssb2
     on sdd.drug_detail_id = ssb2.drug_detail_id
         inner join sys_drug sd on sdd.drug_id = sd.drug_id
         left join sys_brand sb on sdd.brand_id = sb.brand_id
         left join tb_type tt on sd.type_id = tt.type_id
where sd.type_id = 1
  and sd.drug_name like '%胶囊%'
  and sdd.drug_number = 'amxl123'


