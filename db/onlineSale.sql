select sum(ssb.store_batch_existing_quantity)
from sys_store_batch ssb
where ssb.store_id = 1
  and ssb.drug_detail_id = 1
  and ssb.store_batch_status = 'SOLD'
group by ssb.drug_detail_id
;

select *
from sys_store_batch ssb
where ssb.store_id = 1
  and ssb.drug_detail_id = 1
  and ssb.store_batch_status = 'SOLD'
order by ssb.store_batch_id desc;