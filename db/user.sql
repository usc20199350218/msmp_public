select *
from sys_user_basis tu
         left join sys_role tr on tu.role_id = tr.role_id
where tu.user_gender = '男'
  and tu.user_status = 1
  and tu.user_vip = 1
  and tu.role_id = 1
  and tu.user_name like '%%'
  and tu.user_phone = ''
  and tu.user_real_name like '%%'