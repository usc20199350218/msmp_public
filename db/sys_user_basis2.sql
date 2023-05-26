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

 Date: 26/05/2023 14:42:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user_basis
-- ----------------------------
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

-- ----------------------------
-- Records of sys_user_basis
-- ----------------------------
INSERT INTO `sys_user_basis`
VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', '17312344321', '胡靖', '男',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/19/bf82c79d-fe22-45bc-92bd-154c6099bc1a.png',
        1, 1, 1, '2023-03-10', '2023-05-19 01:15:40', '2023-03-10 16:24:07', 0, 'yuwenyanhao');
INSERT INTO `sys_user_basis`
VALUES (2, 'root', '63a9f0ea7bb98050796b649e85481845', '16673468281', '胡', '男',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/26/e21286ca-f121-4aba-866c-730228c3c923.jpg',
        1, 1, 1, '2023-03-13', '2023-05-26 13:37:37', '2023-03-13 19:41:59', 0, 'nick2');
INSERT INTO `sys_user_basis`
VALUES (3, '123123', '4297f44b13955235245b2497399d7a93', '17343042706', '宇文', '男',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/f3688529-bc25-4f30-9d9b-f224ecd194b8.jpeg',
        1, 1, 1, '2023-03-30', '2023-05-18 21:25:30', '2023-03-13 19:55:08', 0, 'nick3');
INSERT INTO `sys_user_basis`
VALUES (4, 'yuwenyanhao', '59d9b805c3396d2d4f08f76b7e69b860', '17343042707', '宇文延皓', '男',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/adb66554-8778-4a0d-bf67-06388fde05b5.jpeg',
        1, 1, 1, '2023-03-02', '2023-05-18 21:25:54', '2023-03-13 20:16:09', 0, 'nick4');
INSERT INTO `sys_user_basis`
VALUES (5, '1', 'fcea920f7412b5da7be0cf42b8c93759', '12341232342', '12', '男',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/19/9666b3e2-267e-49a7-9bb1-1a22486b7323.png',
        1, 1, 1, '2023-03-02', '2023-05-19 00:28:40', '2023-03-13 20:19:42', 0, '12341232342');
INSERT INTO `sys_user_basis`
VALUES (6, '2', 'c81e728d9d4c2f636f067f89cc14862c', '3', '胡', '未知',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg',
        1, 1, 1, '2023-03-19', '2023-03-13 20:20:17', '2023-03-13 20:20:17', 0, NULL);
INSERT INTO `sys_user_basis`
VALUES (7, '3', 'eccbc87e4b5ce2fe28308fd9f2a7baf3', '4', '4', '男',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg',
        1, 1, 1, '2023-03-04', '2023-03-13 20:22:45', '2023-03-13 20:22:45', 0, NULL);
INSERT INTO `sys_user_basis`
VALUES (8, 'storemanager', '4e02b0e68f049d9fd40b2013f7b637e2', '13344448888', '张三', '男',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg',
        2, 1, 1, '1997-07-01', '2023-04-02 20:55:17', '2023-04-02 20:55:17', 0, 'mr.zhang');
INSERT INTO `sys_user_basis`
VALUES (9, 'clerk', '34776981fa47aa6cf3f2915d11bae051', '19944449999', '李四', '男',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg',
        3, 1, 1, '2000-07-01', '2023-04-02 20:56:03', '2023-04-02 20:56:03', 0, 'mr.li');
INSERT INTO `sys_user_basis`
VALUES (10, 'storemanager2', '11544c7ce1cf247bbfcd8050ddec8257', '17344445555', '王五', '男',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg',
        2, 1, 1, '1991-04-04', '2023-04-03 09:21:38', '2023-04-03 09:21:38', 0, 'mr.wang');
INSERT INTO `sys_user_basis`
VALUES (11, 'clerk2', '331c8a338939ee7853c1e66182673242', '16677773333', '赵六', '男',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg',
        3, 1, 1, '1997-07-01', '2023-04-03 09:22:24', '2023-04-03 09:22:24', 0, 'mr.zhao');
INSERT INTO `sys_user_basis`
VALUES (12, 'supplier', '99b0e8da24e29e4ccb5d7d76e677c2ac', '12333334444', '供应商1', '男',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg',
        4, 1, 1, '1990-04-01', '2023-04-04 00:02:45', '2023-04-04 00:02:45', 0, 'su1');
INSERT INTO `sys_user_basis`
VALUES (13, 'supplier2', 'f9e95ee553f7954b8bd113060450720d', '11133334444', '供应商2', '男',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/adb66554-8778-4a0d-bf67-06388fde05b5.jpeg',
        4, 1, 1, '1995-04-04', '2023-04-04 00:03:15', '2023-04-04 00:03:15', 0, 'su2');
INSERT INTO `sys_user_basis`
VALUES (14, 'user1', '24c9e15e52afc47c225b757e7bee1f9d', '19944448888', '用户1', '女',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg',
        5, 1, 1, '2023-03-06', '2023-04-04 00:03:52', '2023-04-04 00:03:52', 0, 'u1');
INSERT INTO `sys_user_basis`
VALUES (15, 'user2', '7e58d63b60197ceb55a1c487989a3720', '18811118888', '用户2', '男',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/adb66554-8778-4a0d-bf67-06388fde05b5.jpeg',
        5, 1, 0, '2023-04-04', '2023-04-04 00:04:26', '2023-04-04 00:04:26', 0, 'u2');
INSERT INTO `sys_user_basis`
VALUES (16, 'supplier3', 'a9f10294d6a1f965fad9924ae613d999', '11122223333', '供应商3', '男',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/adb66554-8778-4a0d-bf67-06388fde05b5.jpeg',
        4, 1, 1, '2023-04-04', '2023-04-04 00:08:18', '2023-04-04 00:08:18', 0, 'su3');
INSERT INTO `sys_user_basis`
VALUES (17, 'supplier4', '02adbc4ce4aa2e4ae759e998a0ebf8fb', '12255556666', '供应商4', '女',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg',
        4, 1, 1, '2023-04-04', '2023-04-04 00:08:48', '2023-04-04 00:08:48', 0, 'su4');
INSERT INTO `sys_user_basis`
VALUES (18, 'supplier5', '172737c71a344666428dae1ec30836a6', '13644443333', '供应商5', '女',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg',
        4, 1, 0, '2023-04-04', '2023-04-04 00:09:22', '2023-04-04 00:09:22', 0, 'su5');
INSERT INTO `sys_user_basis`
VALUES (19, 'supplier6', '43d221f8b11c49e254e56f3447378bd8', '16644443333', '供应商6', '男',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/adb66554-8778-4a0d-bf67-06388fde05b5.jpeg',
        4, 1, 1, '2023-04-04', '2023-04-04 00:09:48', '2023-04-04 00:09:48', 0, 'su6');
INSERT INTO `sys_user_basis`
VALUES (20, 'supplier10', '6501f691b698b2dcbcd638073ac28ec7', '10100001111', '供应商7', '男',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg',
        4, 1, 0, '2023-04-04', '2023-04-04 00:10:12', '2023-04-04 00:10:12', 0, 'su7');
INSERT INTO `sys_user_basis`
VALUES (21, 'delivery1', '763d21e2fadf44104db6c222d54a15af', '19934458888', 'delivery11', '男',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg',
        6, 1, 1, '1996-04-01', '2023-04-07 16:26:31', '2023-04-07 16:26:31', 0, 'delivery1');
INSERT INTO `sys_user_basis`
VALUES (22, 'qweqwe', 'qweqwe', '17872133305', '张三', '男',
        'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/09/85c28e0f-3a62-4a72-ae5f-78780b4433af.png',
        6, 1, 1, '2023-05-01', '2023-05-09 17:54:34', '2023-05-09 17:04:21', 0, '张三家');

SET FOREIGN_KEY_CHECKS = 1;
