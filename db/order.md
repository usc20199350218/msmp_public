# 线下

## 下单：

传入

1. 操作员id（记录在线下订单表中，由于业绩展示）
2. 店铺批次idList（传对象太难了，退而求其次传id，通过id计数）

操作
先减少店内库存，之后有一个历史订单可以查看、付款、退回

传回

1. 订单概览id，校验金额

## 确认前的准备

优惠券
传入

1. 优惠券id

传回

