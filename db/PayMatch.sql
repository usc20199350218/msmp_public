DROP TABLE IF EXISTS tb_pay_match;
CREATE TABLE tb_pay_match
(
    `out_trade_no` VARCHAR(255) NOT NULL COMMENT '支付宝订单号',
    `order_num`    VARCHAR(255) NOT NULL COMMENT 'order订单号',
    PRIMARY KEY (out_trade_no)
) COMMENT = '支付订单对应表';
