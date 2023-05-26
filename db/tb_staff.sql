DROP TABLE IF EXISTS tb_staff;
CREATE TABLE tb_staff
(
    `staff_id`      INT NOT NULL AUTO_INCREMENT COMMENT '职员id',
    `store_id`      INT COMMENT '店铺id',
    `user_id`       BIGINT COMMENT '用户id',
    `position_id`   INT COMMENT '职位id',
    `status`        INT default 1 COMMENT '状态',
    `actual_salary` DECIMAL(24, 6) COMMENT '实际薪水',
    `create_time`   DATETIME COMMENT '创建时间',
    `modified_time` DATETIME COMMENT '更新时间',
    PRIMARY KEY (staff_id)
) COMMENT = '工作人员表';


insert into tb_staff
values (null, 1, 1, 1, 1, 10000, now(), now());

select ts.staff_id,
       ts.status,
       ts.actual_salary,
       ts.create_time,
       ts.modified_time,
       sub.user_id,
       sub.user_name,
       sub.user_phone,
       sub.user_real_name,
       sub.user_gender,
       sub.role_id,
       tp.position_id,
       tp.position_name,
       t.store_id,
       t.store_name
from tb_staff ts
         left join sys_user_basis sub on ts.user_id = sub.user_id
         left join tb_position tp on ts.position_id = tp.position_id
         left join tb_store t on ts.store_id = t.store_id;


select ts.staff_id,
       ts.store_id,
       ts.user_id,
       ts.position_id,
       ts.status,
       ts.actual_salary,
       sub.user_name,
       sub.user_phone,
       sub.user_real_name,
       sub.user_gender,
       sub.user_avatar_url,
       sub.user_nick_name
from tb_staff ts
         left outer join sys_user_basis sub on ts.user_id = sub.user_id
         left join tb_position tp on ts.position_id = tp.position_id
where ts.store_id = 2
  and tp.position_name <> '店长'
  and tp.position_name != ''
