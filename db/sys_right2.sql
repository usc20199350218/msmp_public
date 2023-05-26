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

-- ----------------------------
-- Table structure for sys_right
-- ----------------------------
DROP TABLE IF EXISTS `sys_right`;
CREATE TABLE `sys_right`  (
  `right_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单权限Id',
  `right_text` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单权限标题',
  `right_type` int(11) NULL DEFAULT NULL COMMENT '0: 父节点 1:子节点',
  `right_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单权限路径',
  `right_parent_id` int(11) NULL DEFAULT 0 COMMENT '父节点时为空，子节点时 父节点Id',
  `right_menu` bit(1) NULL DEFAULT NULL COMMENT '1:菜单权限 0:button权限',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`right_id`) USING BTREE,
  UNIQUE INDEX `sys_right_right_text_uindex`(`right_text` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_right
-- ----------------------------
INSERT INTO `sys_right` VALUES (1, '菜单权限管理', 0, ' ', 0, b'1', '2023-03-10 13:33:14', '2023-03-10 13:33:14');
INSERT INTO `sys_right` VALUES (2, '菜单列表', 1, '/rights/manage', 1, b'1', '2023-03-10 16:01:45', '2023-05-19 01:15:28');
INSERT INTO `sys_right` VALUES (3, '人员管理', 0, '', 0, b'1', '2023-05-14 14:20:53', '2023-05-14 14:20:57');
INSERT INTO `sys_right` VALUES (4, '人员管理列表', 1, '/users/manage', 3, b'1', '2023-05-14 14:20:56', '2023-05-14 14:20:58');
INSERT INTO `sys_right` VALUES (5, '角色管理', 0, '', 0, b'1', '2023-03-13 13:55:32', '2023-03-13 13:55:32');
INSERT INTO `sys_right` VALUES (8, '仓库管理', 0, '', 0, b'1', '2023-03-13 15:21:27', '2023-03-13 15:21:27');
INSERT INTO `sys_right` VALUES (15, '角色列表', 1, '/role/manage', 5, b'1', '2023-03-14 19:16:29', '2023-03-14 19:16:29');
INSERT INTO `sys_right` VALUES (16, '药品信息管理', 0, '', 0, b'1', '2023-03-19 11:26:52', '2023-03-19 11:26:52');
INSERT INTO `sys_right` VALUES (17, '药品列表', 1, '/drug/manage', 16, b'1', '2023-03-19 11:27:14', '2023-03-19 11:27:14');
INSERT INTO `sys_right` VALUES (18, '药品详情管理', 1, '/drug/manage/details', 16, b'1', '2023-03-19 15:34:04', '2023-03-19 15:34:04');
INSERT INTO `sys_right` VALUES (19, '品牌管理', 1, '/drug/brand', 16, b'1', '2023-03-19 16:02:12', '2023-03-19 16:02:12');
INSERT INTO `sys_right` VALUES (20, '进货管理', 1, '/storehouse/purchase', 8, b'1', '2023-03-19 22:45:37', '2023-03-19 22:45:37');
INSERT INTO `sys_right` VALUES (21, '店铺管理', 0, '', 0, b'1', '2023-04-02 13:10:36', '2023-04-02 13:10:36');
INSERT INTO `sys_right` VALUES (22, '店铺列表', 1, '/store/manage', 21, b'1', '2023-04-02 13:11:50', '2023-04-02 13:11:50');
INSERT INTO `sys_right` VALUES (23, '职位管理', 1, '/store/position', 21, b'1', '2023-04-02 16:24:32', '2023-04-02 16:24:32');
INSERT INTO `sys_right` VALUES (24, '职员列表', 1, '/store/staff', 21, b'1', '2023-04-02 17:14:23', '2023-04-02 17:14:23');
INSERT INTO `sys_right` VALUES (25, '店铺批次', 1, '/store/batch', 21, b'1', '2023-04-03 10:27:50', '2023-04-03 10:27:50');
INSERT INTO `sys_right` VALUES (26, '品类管理', 1, '/drug/type', 16, b'1', '2023-04-05 13:04:28', '2023-04-05 13:04:28');
INSERT INTO `sys_right` VALUES (27, '店铺请求', 1, '/storehouse/store_apply', 8, b'1', '2023-04-05 16:42:54', '2023-04-05 16:42:54');
INSERT INTO `sys_right` VALUES (28, '配送管理', 0, '', 0, b'1', '2023-04-06 21:52:20', '2023-04-06 21:52:20');
INSERT INTO `sys_right` VALUES (29, '店铺配送', 1, '/delivery/store', 28, b'1', '2023-04-06 21:52:42', '2023-04-06 21:52:42');
INSERT INTO `sys_right` VALUES (30, '线下销售', 1, '/store/offline', 21, b'1', '2023-04-08 12:56:45', '2023-04-08 12:56:45');
INSERT INTO `sys_right` VALUES (32, '临时', 1, '/store/offline/order/details', 21, b'1', '2023-04-09 20:19:53', '2023-04-09 20:19:53');
INSERT INTO `sys_right` VALUES (35, '线上', 0, '', 0, b'1', '2023-04-30 14:55:22', '2023-04-30 14:55:22');
INSERT INTO `sys_right` VALUES (36, '线上购药', 1, '/online/store', 35, b'1', '2023-04-30 14:55:55', '2023-04-30 14:55:55');
INSERT INTO `sys_right` VALUES (37, '搜索', 1, '/online/search', 35, b'1', '2023-05-01 18:26:27', '2023-05-01 18:26:27');
INSERT INTO `sys_right` VALUES (38, '主页', 1, '/online/index', 35, b'1', '2023-05-01 18:46:04', '2023-05-01 18:46:04');
INSERT INTO `sys_right` VALUES (40, '我的', 0, '', 0, b'1', '2023-05-04 14:36:39', '2023-05-04 14:36:39');
INSERT INTO `sys_right` VALUES (41, '订单', 1, '/user/order', 40, b'1', '2023-05-04 14:36:54', '2023-05-04 14:36:54');
INSERT INTO `sys_right` VALUES (42, '店铺订单', 1, '/store/order', 21, b'1', '2023-05-04 14:38:36', '2023-05-04 14:38:36');
INSERT INTO `sys_right` VALUES (50, '随访', 0, '', 0, b'1', '2023-05-10 10:47:04', '2023-05-14 14:22:31');
INSERT INTO `sys_right` VALUES (51, '随访明细', 1, '/service/user', 50, b'1', '2023-05-10 10:47:41', '2023-05-14 14:25:57');
INSERT INTO `sys_right` VALUES (52, '随访项', 1, '/service/manage', 50, b'1', '2023-05-10 15:54:52', '2023-05-14 14:26:39');
INSERT INTO `sys_right` VALUES (53, '地址管理', 1, '/address/manage', 40, b'1', '2023-05-13 17:28:58', '2023-05-13 17:28:58');
INSERT INTO `sys_right` VALUES (58, '购物车', 1, '/online/shoppingcart', 40, b'1', '2023-05-18 23:25:12', '2023-05-18 23:25:12');
INSERT INTO `sys_right` VALUES (63, '个人信息', 1, '/user/info', 40, b'1', '2023-05-20 20:04:39', '2023-05-20 20:04:39');

SET FOREIGN_KEY_CHECKS = 1;
