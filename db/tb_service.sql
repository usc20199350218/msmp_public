DROP TABLE IF EXISTS tb_service;
CREATE TABLE tb_service
(
    `service_id`    INT NOT NULL AUTO_INCREMENT COMMENT '',
    `is_last`       varchar(1) COMMENT '',
    `is_normal`     VARCHAR(1) COMMENT '',
    `user_id`       INT COMMENT '',
    `create_time`   DATETIME COMMENT '',
    `modified_time` DATETIME COMMENT '',
    PRIMARY KEY (service_id)
) COMMENT = '服务表';

select *
from tb_service ts
         left join tb_service_detail tsd on ts.user_id = tsd.user_id;


select ts.service_id
from tb_service ts
where ts.user_id = 1
  and ts.is_last = 1;

select tse.service_entry_id, tse.service_entry_name
from tb_service_entry tse;



select *
from tb_service_entry tse
         left join tb_service_detail tsd on tsd.service_entry_id = tse.service_entry_id
         left join tb_service ts on tsd.service_id = ts.service_id
where ts.user_id = 1
  and ts.is_last = 1;


select ts.*
from tb_service ts
where ts.user_id = 1
  and ts.is_last = 1;

select *
from tb_service_entry tse;



select *
from tb_service_entry tse
