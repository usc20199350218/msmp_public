select sr.*
     , (select srr.role_id
        from sys_role_right srr
        where srr.right_id = sr.right_id) as role_id
from sys_right sr
;
select srr.role_id
from sys_role_right srr
where srr.right_id = sr.right_id;

select sr.*
from sys_right sr
         left join sys_role_right srr on sr.right_id = srr.right_id
where sr.right_type = 1
  and srr.role_id = 5;
