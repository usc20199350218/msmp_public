SELECT user_id,
       user_name,
       user_password,
       user_phone,
       user_gender,
       user_avatar_url,
       role_id,
       user_status,
       user_nick_name,
       user_real_name,
       user_vip,
       modified_time,
       create_time,
       deleted,
       user_birthday
FROM sys_user_basis
WHERE deleted = 0
  AND (role_id = ? AND role_id = ? AND role_id = ? OR role_id = ?)

select *
from sys_user_basis sub
where sub.user_real_name like '%供应商%'
  and sub.user_phone like '123'
