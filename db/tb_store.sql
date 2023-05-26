DROP TABLE IF EXISTS tb_store;
CREATE TABLE tb_store
(
    `store_id`          INT NOT NULL AUTO_INCREMENT COMMENT '店铺ID',
    `store_name`        VARCHAR(255) COMMENT '店铺名称',
    `store_address`     VARCHAR(255) COMMENT '店铺地址',
    `contact_phone`     VARCHAR(255) COMMENT '联系电话',
    `business_hours`    VARCHAR(255) COMMENT '营业时间',
    `create_time`       DATE COMMENT '创建时间',
    `store_status`      INT DEFAULT 1 COMMENT '状态',
    `area`              VARCHAR(255) COMMENT '所属区域',
    `business_district` VARCHAR(255) COMMENT '所属商圈',
    `modified_time`     DATE COMMENT '更新时间',
    PRIMARY KEY (store_id)
) COMMENT = '店铺表';

insert into tb_store
values (null, '金匮堂大药房', '上海宝山区湄星路8-2号', '13601638180', '9:00-21:00', now(), null, '宝山杨行', null,
        now());