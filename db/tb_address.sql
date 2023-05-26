DROP TABLE IF EXISTS tb_address;
CREATE TABLE tb_address
(
    `address_id`      INT NOT NULL AUTO_INCREMENT COMMENT '地址id',
    `address_content` VARCHAR(900) COMMENT '地址内容',
    `user_id`         INT COMMENT '用户id',
    `is_default`      VARCHAR(1) COMMENT '默认',
    `create_time`     DATETIME COMMENT '创建时间',
    `modified_time`   DATETIME COMMENT '更新时间',
    PRIMARY KEY (address_id)
) COMMENT = '地址';


UPDATE tb_address
SET is_default = 1
WHERE user_id = 'USER_ID'
  AND create_time IN (SELECT MAX(create_time)
                      FROM tb_address
                      WHERE user_id = 'USER_ID');

select *
from tb_address ta
where ta.user_id = 1
order by ta.create_time desc
limit 1