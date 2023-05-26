/*
 Navicat Premium Data Transfer

 Source Server         : 3305
 Source Server Type    : MySQL
 Source Server Version : 80012 (8.0.12)
 Source Host           : localhost:3305
 Source Schema         : medical

 Target Server Type    : MySQL
 Target Server Version : 80012 (8.0.12)
 File Encoding         : 65001

 Date: 25/05/2023 13:09:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `sys_right`;
CREATE TABLE `sys_right`
(
    `right_id`        int(11)                                                 NOT NULL AUTO_INCREMENT COMMENT '菜单权限Id',
    `right_text`      varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单权限标题',
    `right_type`      int(11)                                                 NULL DEFAULT NULL COMMENT '0: 父节点 1:子节点',
    `right_url`       varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单权限路径',
    `right_parent_id` int(11)                                                 NULL DEFAULT 0 COMMENT '父节点时为空，子节点时 父节点Id',
    `right_menu`      bit(1)                                                  NULL DEFAULT NULL COMMENT '1:菜单权限 0:button权限',
    `create_time`     datetime                                                NULL DEFAULT NULL COMMENT '创建时间',
    `modified_time`   datetime                                                NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`right_id`) USING BTREE,
    UNIQUE INDEX `sys_right_right_text_uindex` (`right_text` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 64
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '菜单权限表'
  ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `sys_role_right`;
CREATE TABLE `sys_role_right`
(
    `role_right_id` int(11)  NOT NULL AUTO_INCREMENT COMMENT '角色菜单权限Id',
    `role_id`       int(11)  NOT NULL COMMENT '角色Id',
    `right_id`      int(11)  NOT NULL COMMENT '菜单权限Id',
    `create_time`   datetime NULL DEFAULT NULL COMMENT '创建时间',
    `modified_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`role_right_id`) USING BTREE,
    INDEX `rightid` (`right_id` ASC) USING BTREE,
    INDEX `roleid` (`role_id` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 391
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '角色菜单权限表'
  ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `role_id`       int(11)                                                 NOT NULL AUTO_INCREMENT COMMENT '角色Id',
    `role_name`     varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
    `deleted`       int(11)                                                 NULL DEFAULT 0 COMMENT '逻辑删除',
    `create_time`   datetime                                                NULL DEFAULT NULL COMMENT '创建时间',
    `modified_time` datetime                                                NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`role_id`) USING BTREE,
    UNIQUE INDEX `sys_role_role_name_uindex` (`role_name` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '角色表'
  ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `sys_user_basis`;
CREATE TABLE `sys_user_basis`
(
    `user_id`         bigint(20)                                                               NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `user_name`       varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci             NOT NULL COMMENT '用户名',
    `user_password`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci            NOT NULL COMMENT '用户密码',
    `user_phone`      varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci             NULL     DEFAULT NULL COMMENT '手机号',
    `user_real_name`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci             NULL     DEFAULT NULL,
    `user_gender`     enum ('男','女','未知') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '未知' COMMENT '性别:男/女/',
    `user_avatar_url` varchar(900) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci            NULL     DEFAULT 'https://www.mingpian.red/avatar/images/07.jpg' COMMENT '头像地址',
    `role_id`         int(11)                                                                  NOT NULL DEFAULT 2 COMMENT '角色id;1-管理员/2-普通用户',
    `user_status`     int(11)                                                                  NOT NULL DEFAULT 1 COMMENT '用户状态;1-正常/0-封禁',
    `user_vip`        int(11)                                                                  NOT NULL DEFAULT 0 COMMENT 'vip;0-普通/1-vip',
    `user_birthday`   date                                                                     NULL     DEFAULT NULL COMMENT '生日',
    `modified_time`   datetime                                                                 NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_time`     datetime                                                                 NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `deleted`         int(11)                                                                  NOT NULL DEFAULT 0 COMMENT '逻辑删除;1-删除/0-正常',
    `user_nick_name`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci             NULL     DEFAULT NULL,
    PRIMARY KEY (`user_id`) USING BTREE,
    UNIQUE INDEX `user_name` (`user_name` ASC) USING BTREE,
    UNIQUE INDEX `user_phone` (`user_phone` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 24
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = '人员基础信息表'
  ROW_FORMAT = Dynamic;


INSERT INTO `sys_right`
VALUES (1, '菜单权限管理', 0, ' ', 0, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (2, '菜单列表', 1, '/rights/manage', 1, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (3, '人员管理', 0, '', 0, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (4, '人员管理列表', 1, '/users/manage', 3, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (5, '角色管理', 0, '', 0, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (8, '仓库管理', 0, '', 0, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (15, '角色列表', 1, '/role/manage', 5, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (16, '药品信息管理', 0, '', 0, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (17, '药品列表', 1, '/drug/manage', 16, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (18, '药品详情管理', 1, '/drug/manage/details', 16, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (19, '品牌管理', 1, '/drug/brand', 16, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (20, '进货管理', 1, '/storehouse/purchase', 8, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (21, '店铺管理', 0, '', 0, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (22, '店铺列表', 1, '/store/manage', 21, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (23, '职位管理', 1, '/store/position', 21, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (24, '职员列表', 1, '/store/staff', 21, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (25, '店铺批次', 1, '/store/batch', 21, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (26, '品类管理', 1, '/drug/type', 16, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (27, '店铺请求', 1, '/storehouse/store_apply', 8, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (28, '配送管理', 0, '', 0, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (29, '店铺配送', 1, '/delivery/store', 28, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (30, '线下销售', 1, '/store/offline', 21, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (32, '临时', 1, '/store/offline/order/details', 21, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (35, '线上', 0, '', 0, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (36, '线上购药', 1, '/online/store', 35, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (37, '搜索', 1, '/online/search', 35, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (38, '主页', 1, '/online/index', 35, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (40, '我的', 0, '', 0, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (41, '订单', 1, '/user/order', 40, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (42, '店铺订单', 1, '/store/order', 21, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (50, '随访', 0, '', 0, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (51, '随访明细', 1, '/service/user', 50, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (52, '随访项', 1, '/service/manage', 50, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (53, '地址管理', 1, '/address/manage', 40, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (58, '购物车', 1, '/online/shoppingcart', 40, b'1', now(), now());
INSERT INTO `sys_right`
VALUES (63, '个人信息', 1, '/user/info', 40, b'1', now(), now());


INSERT INTO `sys_role_right`
VALUES (null, 1, 1, now(), now());
INSERT INTO `sys_role_right`
VALUES (null, 1, 2, now(), now());

INSERT INTO `sys_role`
VALUES (null, '管理员', 0, now(), now());

INSERT INTO `sys_user_basis`
VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', '17312344321', 'name', '男',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/19/bf82c79d-fe22-45bc-92bd-154c6099bc1a.png',
        1, 1, 1, '2023-03-10', now(), now(), 0, 'yuwenyanhao');
