DROP TABLE IF EXISTS tb_order_detail;
CREATE TABLE tb_order_detail
(
    `order_detail_id` INT NOT NULL AUTO_INCREMENT COMMENT '订单详情编号',
    `order_id`        INT COMMENT '订单编号',
    `drug_detail_id`  INT COMMENT '药品详情id',
    `drug_name`       VARCHAR(255) COMMENT '药品名称',
    `drug_price`      DECIMAL(24, 6) COMMENT '零售价',
    `total_price`     DECIMAL(24, 6) COMMENT '商品总价',
    `quantity`        INT COMMENT '商品数量',
    `create_time`     DATETIME COMMENT '创建时间',
    `modified_time`   DATETIME COMMENT '创建时间',
    PRIMARY KEY (order_detail_id)
) COMMENT = '订单详情表';
