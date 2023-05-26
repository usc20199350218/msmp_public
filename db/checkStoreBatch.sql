select sum(ssb.store_batch_existing_quantity)
from sys_store_batch ssb
where ssb.store_batch_status = 'SOLD'
  and ssb.store_id = 5
  and ssb.drug_detail_id = 6
;
select *
from sys_store_batch ssb
where ssb.store_batch_status = 'SOLD'
  and ssb.store_id = 5
  and ssb.drug_detail_id = 6;



select *
from sys_batch sb
WHERE DATE_ADD(sb.batch_production_date, INTERVAL sb.batch_validity_period MONTH) < CURDATE()
  and sb.batch_status = 'SOLD';


select ssb.*
from sys_store_batch ssb
         left join sys_batch sb on ssb.batch_id = sb.batch_id
WHERE DATE_ADD(sb.batch_production_date, INTERVAL sb.batch_validity_period MONTH) < CURDATE();
