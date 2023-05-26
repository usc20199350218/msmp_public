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

 Date: 25/05/2023 13:07:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_batch
-- ----------------------------
DROP TABLE IF EXISTS `sys_batch`;
CREATE TABLE `sys_batch`  (
  `batch_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '批次Id',
  `drug_detail_id` int(11) NULL DEFAULT NULL COMMENT '药品Id',
  `batch_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '批号编号;由供应商提供',
  `batch_production_date` date NULL DEFAULT NULL COMMENT '生产日期',
  `batch_purchase_quantity` int(11) NULL DEFAULT NULL COMMENT '进货数量',
  `batch_existing_quantity` int(11) NULL DEFAULT NULL COMMENT '现存数量;出售从这里计算',
  `batch_status` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '批次状态',
  `batch_validity_period` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '有效期',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `remark` varchar(900) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `modified_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(11) UNSIGNED NOT NULL DEFAULT 1,
  PRIMARY KEY (`batch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '仓库表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_batch
-- ----------------------------
INSERT INTO `sys_batch` VALUES (1, 1, 'amxl230401', '2023-03-31', 1000, 210, 'EXPIRED', 1, '2023-04-30 21:05:48', '', '2023-05-19 17:40:30', 131);
INSERT INTO `sys_batch` VALUES (2, 2, 'gml230401', '2023-03-31', 1000, 460, 'SOLD', 12, '2023-04-30 21:05:56', '', '2023-05-19 01:08:57', 11);
INSERT INTO `sys_batch` VALUES (3, 3, 'blg230401', '2023-03-31', 1000, 650, 'SOLD', 12, '2023-04-30 21:06:01', '', '2023-04-30 21:51:51', 7);
INSERT INTO `sys_batch` VALUES (4, 3, 'blg230402', '2023-04-01', 1000, 1000, 'SOLD', 12, '2023-04-30 21:06:09', '', '2023-04-30 21:24:15', 3);
INSERT INTO `sys_batch` VALUES (5, 4, 'blf230401', '2023-03-31', 1000, 680, 'SOLD', 12, '2023-04-30 21:06:14', '', '2023-04-30 21:57:17', 7);
INSERT INTO `sys_batch` VALUES (6, 5, 'cbppg230401', '2023-03-31', 1000, 560, 'SOLD', 11, '2023-04-30 21:06:21', '', '2023-05-19 01:19:10', 10);
INSERT INTO `sys_batch` VALUES (7, 6, 'hxzqs230401', '2023-03-31', 1000, 560, 'SOLD', 10, '2023-04-30 21:06:37', '', '2023-05-19 00:52:30', 9);
INSERT INTO `sys_batch` VALUES (8, 7, 'ynbyqwj230401', '2023-03-31', 1000, 600, 'SOLD', 12, '2023-04-30 21:06:43', '', '2023-05-19 00:53:01', 7);
INSERT INTO `sys_batch` VALUES (9, 1, 'amxl230402', '2023-04-01', 100, 0, 'SOLD_OUT', 10, '2023-04-30 21:30:22', '', '2023-05-19 00:45:56', 4);
INSERT INTO `sys_batch` VALUES (10, 1, 'amxl230403', '2023-04-02', 100, 0, 'SOLD_OUT', 10, '2023-04-30 21:30:31', '', '2023-05-19 00:46:22', 4);
INSERT INTO `sys_batch` VALUES (11, 1, 'amxl230404', '2023-04-03', 100, 0, 'SOLD_OUT', 10, '2023-04-30 21:30:36', '', '2023-05-19 00:46:22', 4);
INSERT INTO `sys_batch` VALUES (12, 1, '', NULL, 100, 100, 'FORBIDDEN', NULL, '2023-04-30 21:30:41', '备注内容', '2023-04-30 21:31:05', 2);
INSERT INTO `sys_batch` VALUES (13, 1, 'amxl230405', '2023-04-04', 200, 70, 'SOLD', 12, '2023-04-30 21:31:26', '', '2023-05-19 01:19:13', 8);
INSERT INTO `sys_batch` VALUES (14, 1, 'amxl230501', '2023-05-01', 500, 500, 'SOLD', 10, '2023-05-18 22:43:46', '', '2023-05-18 23:01:31', 4);
INSERT INTO `sys_batch` VALUES (15, 3, 'blg230501', '2023-05-01', 100, 100, 'SOLD', 12, '2023-05-19 00:42:10', '', '2023-05-19 00:42:41', 3);
INSERT INTO `sys_batch` VALUES (16, 9, 'qlppg230501', '2023-05-01', 100, 80, 'SOLD', 12, '2023-05-19 00:56:23', '下架', '2023-05-19 00:59:40', 7);
INSERT INTO `sys_batch` VALUES (17, 7, 'ynby230501', '2023-05-01', 100, 100, 'SOLD', 10, '2023-05-19 01:05:19', 'ynby230501', '2023-05-19 01:06:10', 5);
INSERT INTO `sys_batch` VALUES (18, 6, 'hxzqs2305011', '2023-05-01', 100, 100, 'SOLD', 12, '2023-05-19 01:16:40', '123', '2023-05-19 01:17:12', 5);

-- ----------------------------
-- Table structure for sys_brand
-- ----------------------------
DROP TABLE IF EXISTS `sys_brand`;
CREATE TABLE `sys_brand`  (
  `brand_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '品牌id',
  `brand_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '品牌名称',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '联系人Id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`brand_id`) USING BTREE,
  UNIQUE INDEX `sys_brand_brand_name_uindex`(`brand_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '品牌表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_brand
-- ----------------------------
INSERT INTO `sys_brand` VALUES (1, '三九企业集团', 12, '2023-03-19 17:43:07', '2023-04-04 00:06:03');
INSERT INTO `sys_brand` VALUES (2, '德上', 13, '2023-03-19 17:43:21', '2023-04-04 00:06:09');
INSERT INTO `sys_brand` VALUES (4, '亨迪药业', 16, '2023-03-19 17:52:34', '2023-04-04 00:11:43');
INSERT INTO `sys_brand` VALUES (5, '芬必得', 17, '2023-04-03 23:59:57', '2023-04-04 00:11:48');
INSERT INTO `sys_brand` VALUES (6, '京都念慈菴', 18, '2023-04-04 00:06:38', '2023-04-04 00:11:52');
INSERT INTO `sys_brand` VALUES (7, '禾邦', 19, '2023-04-04 00:07:02', '2023-04-04 00:11:57');
INSERT INTO `sys_brand` VALUES (8, '云南白药', 20, '2023-04-04 00:07:30', '2023-04-04 00:12:01');

-- ----------------------------
-- Table structure for sys_drug
-- ----------------------------
DROP TABLE IF EXISTS `sys_drug`;
CREATE TABLE `sys_drug`  (
  `drug_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '药品ID',
  `drug_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '药品名称',
  `is_rx` int(11) NULL DEFAULT 0 COMMENT '是否非处方;0-非处方药/1-处方药',
  `drug_status` int(11) NULL DEFAULT 1 COMMENT '药品状态;0-否/1-是',
  `type_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`drug_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '药品信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_drug
-- ----------------------------
INSERT INTO `sys_drug` VALUES (1, '阿莫西林胶囊', 0, 1, 1, '2023-03-19 15:44:14', '2023-05-19 00:57:23');
INSERT INTO `sys_drug` VALUES (2, '感冒灵颗粒', 0, 1, 2, '2023-04-03 23:47:56', '2023-04-05 15:50:16');
INSERT INTO `sys_drug` VALUES (3, '板蓝根颗粒', 0, 1, 2, '2023-04-03 23:48:46', '2023-04-03 23:48:46');
INSERT INTO `sys_drug` VALUES (4, '布洛芬胶囊', 0, 1, 3, '2023-04-03 23:50:31', '2023-04-03 23:50:31');
INSERT INTO `sys_drug` VALUES (5, '川贝枇杷膏', 0, 1, 3, '2023-04-03 23:52:01', '2023-04-03 23:52:01');
INSERT INTO `sys_drug` VALUES (6, '藿香正气水', 0, 1, 2, '2023-04-03 23:52:41', '2023-04-03 23:52:41');
INSERT INTO `sys_drug` VALUES (7, '云南白药气雾剂', 0, 1, 1, '2023-04-03 23:53:32', '2023-04-03 23:53:32');
INSERT INTO `sys_drug` VALUES (8, '头孢克洛干混悬剂', 0, 1, 1, '2023-04-05 15:53:54', '2023-04-05 15:53:54');
INSERT INTO `sys_drug` VALUES (9, '强力枇杷露', 1, 1, 5, '2023-05-01 12:41:52', '2023-05-18 23:06:32');

-- ----------------------------
-- Table structure for sys_drug_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_drug_details`;
CREATE TABLE `sys_drug_details`  (
  `drug_detail_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '药品详情id',
  `drug_id` int(11) NULL DEFAULT NULL COMMENT '药品id',
  `brand_id` int(11) NULL DEFAULT NULL COMMENT '品牌Id',
  `drug_details_status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `drug_specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '规格',
  `drug_unit_price` decimal(24, 6) NULL DEFAULT NULL COMMENT '单价',
  `drug_retail_price` decimal(24, 6) NULL DEFAULT NULL COMMENT '零售价',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `drug_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `drug_detail_path` varchar(999) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`drug_detail_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '药品详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_drug_details
-- ----------------------------
INSERT INTO `sys_drug_details` VALUES (1, 1, 1, 1, '0.25g*20', 10.000000, 12.000000, '2023-03-22 18:39:09', '2023-05-19 01:17:31', 'amxl123', 'https://image.jianke.com/mall/product/202101/89e62c46cb654868930a657215bc07c2.jpg');
INSERT INTO `sys_drug_details` VALUES (2, 2, 1, 1, '10g*9', 15.000000, 20.000000, '2023-04-04 00:13:33', '2023-04-08 15:46:13', 'gmlkl123', 'https://ts1.cn.mm.bing.net/th/id/R-C.7bcdc8f198c581c2daff833fa14c0c89?rik=pnP7WlgJz8Xc%2bg&riu=http%3a%2f%2f1001.365jingdu.com%2fwebimg%2fimg_product%2fproduct%2fP118_01.jpg&ehk=VA1ShOKxyH7NNelX%2fSuM%2fDfNKTuAqetL%2bbhszFWaCOw%3d&risl=&pid=ImgRaw&r=0');
INSERT INTO `sys_drug_details` VALUES (3, 3, 1, 1, '10g*20', 10.000000, 12.000000, '2023-04-04 00:18:36', '2023-04-08 15:46:22', 'blgkl123', 'https://img01.hstyf360.com/group1/M00/04/23/wKgLOFpAaq6ATfZIAAEI2E3VS7g394.jpg');
INSERT INTO `sys_drug_details` VALUES (4, 4, 5, 1, '0.4*20', 20.000000, 22.000000, '2023-04-04 00:19:22', '2023-04-08 15:46:41', 'blfjn123', 'https://ts1.cn.mm.bing.net/th/id/R-C.976a95f77081de9cd069cbef8ed54a4f?rik=Z1liy1GcjXaDTg&riu=http%3a%2f%2fimg01.hstyf360.com%2fgroup1%2fM00%2f00%2fD5%2fwKgLOFZdcryAMhfbAARcNz9VuQg046.jpg&ehk=xr%2bK3fAhDpmRhR058HrLX%2fdz8ataw2y695m%2fgJ9umwY%3d&risl=&pid=ImgRaw&r=0');
INSERT INTO `sys_drug_details` VALUES (5, 5, 6, 1, '75ml', 30.000000, 35.000000, '2023-04-04 00:20:07', '2023-04-08 15:46:56', 'cbppg123', 'https://c1.yaofangwang.net/common/upload/medicine/629/629546/68958597-b21f-4f1e-88d3-c8ada29aa8b88677.jpg_syp.jpg');
INSERT INTO `sys_drug_details` VALUES (6, 6, 7, 1, '10ml*10', 5.000000, 6.000000, '2023-04-04 00:20:34', '2023-04-08 15:47:09', 'hxzqs123', 'https://c1.yaofangwang.net/common/upload/medicine/264/264799/a79d4609-b72f-4667-8a62-635ab859a4b82347.jpg_syp.jpg');
INSERT INTO `sys_drug_details` VALUES (7, 7, 8, 1, '60g+50g', 22.000000, 30.000000, '2023-04-04 00:20:55', '2023-04-08 15:47:19', 'ynbyqwj123', 'https://img0.miaoshoucdn.com/product/watermark/e/d3/ed32e7b6b96a3511432b1661be67071a.jpg');
INSERT INTO `sys_drug_details` VALUES (9, 9, 1, 1, '225ml', 30.000000, 35.000000, '2023-05-01 13:26:33', '2023-05-01 13:26:52', '81460770324352784557', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/drugpath/2023/05/01/0b1df9ed-fcf0-43b6-9fac-1effa369c1ab.jpg');

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

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色Id',
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `deleted` int(11) NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `sys_role_role_name_uindex`(`role_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 0, '2023-03-10 14:51:18', '2023-05-18 22:43:06');
INSERT INTO `sys_role` VALUES (2, '店长', 0, '2023-04-02 20:52:45', '2023-04-02 20:52:45');
INSERT INTO `sys_role` VALUES (3, '店员', 0, '2023-04-02 20:53:46', '2023-04-02 20:53:46');
INSERT INTO `sys_role` VALUES (4, '供应商', 0, '2023-04-04 00:00:39', '2023-04-04 00:00:39');
INSERT INTO `sys_role` VALUES (5, '用户', 0, '2023-04-04 00:01:55', '2023-05-19 01:16:19');
INSERT INTO `sys_role` VALUES (6, '配送', 0, '2023-04-07 16:21:09', '2023-04-07 16:21:09');

-- ----------------------------
-- Table structure for sys_role_right
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_right`;
CREATE TABLE `sys_role_right`  (
  `role_right_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色菜单权限Id',
  `role_id` int(11) NOT NULL COMMENT '角色Id',
  `right_id` int(11) NOT NULL COMMENT '菜单权限Id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`role_right_id`) USING BTREE,
  INDEX `rightid`(`right_id` ASC) USING BTREE,
  INDEX `roleid`(`role_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 391 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_right
-- ----------------------------
INSERT INTO `sys_role_right` VALUES (21, 2, 3, '2023-04-02 20:52:45', '2023-04-02 20:52:45');
INSERT INTO `sys_role_right` VALUES (22, 2, 4, '2023-04-02 20:52:45', '2023-04-02 20:52:45');
INSERT INTO `sys_role_right` VALUES (23, 2, 5, '2023-04-02 20:52:45', '2023-04-02 20:52:45');
INSERT INTO `sys_role_right` VALUES (24, 2, 15, '2023-04-02 20:52:45', '2023-04-02 20:52:45');
INSERT INTO `sys_role_right` VALUES (26, 2, 8, '2023-04-02 20:52:45', '2023-04-02 20:52:45');
INSERT INTO `sys_role_right` VALUES (27, 2, 20, '2023-04-02 20:52:45', '2023-04-02 20:52:45');
INSERT INTO `sys_role_right` VALUES (28, 2, 16, '2023-04-02 20:52:45', '2023-04-02 20:52:45');
INSERT INTO `sys_role_right` VALUES (29, 2, 17, '2023-04-02 20:52:45', '2023-04-02 20:52:45');
INSERT INTO `sys_role_right` VALUES (30, 2, 18, '2023-04-02 20:52:45', '2023-04-02 20:52:45');
INSERT INTO `sys_role_right` VALUES (31, 2, 19, '2023-04-02 20:52:45', '2023-04-02 20:52:45');
INSERT INTO `sys_role_right` VALUES (32, 2, 21, '2023-04-02 20:52:45', '2023-04-02 20:52:45');
INSERT INTO `sys_role_right` VALUES (33, 2, 22, '2023-04-02 20:52:45', '2023-04-02 20:52:45');
INSERT INTO `sys_role_right` VALUES (34, 2, 23, '2023-04-02 20:52:45', '2023-04-02 20:52:45');
INSERT INTO `sys_role_right` VALUES (35, 2, 24, '2023-04-02 20:52:45', '2023-04-02 20:52:45');
INSERT INTO `sys_role_right` VALUES (36, 3, 3, '2023-04-02 20:53:46', '2023-04-02 20:53:46');
INSERT INTO `sys_role_right` VALUES (37, 3, 4, '2023-04-02 20:53:46', '2023-04-02 20:53:46');
INSERT INTO `sys_role_right` VALUES (39, 3, 17, '2023-04-02 20:53:46', '2023-04-02 20:53:46');
INSERT INTO `sys_role_right` VALUES (40, 3, 18, '2023-04-02 20:53:46', '2023-04-02 20:53:46');
INSERT INTO `sys_role_right` VALUES (41, 3, 16, '2023-04-02 20:53:46', '2023-04-02 20:53:46');
INSERT INTO `sys_role_right` VALUES (43, 2, 25, '2023-04-03 10:27:50', '2023-04-03 10:27:50');
INSERT INTO `sys_role_right` VALUES (44, 3, 25, '2023-04-03 10:27:50', '2023-04-03 10:27:50');
INSERT INTO `sys_role_right` VALUES (45, 4, 16, '2023-04-04 00:00:39', '2023-04-04 00:00:39');
INSERT INTO `sys_role_right` VALUES (46, 4, 17, '2023-04-04 00:00:39', '2023-04-04 00:00:39');
INSERT INTO `sys_role_right` VALUES (47, 4, 18, '2023-04-04 00:00:39', '2023-04-04 00:00:39');
INSERT INTO `sys_role_right` VALUES (48, 4, 19, '2023-04-04 00:00:39', '2023-04-04 00:00:39');
INSERT INTO `sys_role_right` VALUES (50, 2, 26, '2023-04-05 13:04:29', '2023-04-05 13:04:29');
INSERT INTO `sys_role_right` VALUES (52, 2, 27, '2023-04-05 16:42:55', '2023-04-05 16:42:55');
INSERT INTO `sys_role_right` VALUES (54, 2, 28, '2023-04-06 21:52:20', '2023-04-06 21:52:20');
INSERT INTO `sys_role_right` VALUES (56, 2, 29, '2023-04-06 21:52:42', '2023-04-06 21:52:42');
INSERT INTO `sys_role_right` VALUES (57, 6, 22, '2023-04-07 16:21:09', '2023-04-07 16:21:09');
INSERT INTO `sys_role_right` VALUES (58, 6, 28, '2023-04-07 16:21:09', '2023-04-07 16:21:09');
INSERT INTO `sys_role_right` VALUES (59, 6, 29, '2023-04-07 16:21:09', '2023-04-07 16:21:09');
INSERT INTO `sys_role_right` VALUES (60, 6, 21, '2023-04-07 16:21:09', '2023-04-07 16:21:09');
INSERT INTO `sys_role_right` VALUES (62, 2, 30, '2023-04-08 12:56:45', '2023-04-08 12:56:45');
INSERT INTO `sys_role_right` VALUES (63, 3, 30, '2023-04-08 12:56:45', '2023-04-08 12:56:45');
INSERT INTO `sys_role_right` VALUES (74, 2, 35, '2023-04-30 14:55:22', '2023-04-30 14:55:22');
INSERT INTO `sys_role_right` VALUES (75, 3, 35, '2023-04-30 14:55:22', '2023-04-30 14:55:22');
INSERT INTO `sys_role_right` VALUES (76, 4, 35, '2023-04-30 14:55:22', '2023-04-30 14:55:22');
INSERT INTO `sys_role_right` VALUES (78, 6, 35, '2023-04-30 14:55:22', '2023-04-30 14:55:22');
INSERT INTO `sys_role_right` VALUES (80, 2, 36, '2023-04-30 14:55:55', '2023-04-30 14:55:55');
INSERT INTO `sys_role_right` VALUES (81, 3, 36, '2023-04-30 14:55:55', '2023-04-30 14:55:55');
INSERT INTO `sys_role_right` VALUES (82, 4, 36, '2023-04-30 14:55:55', '2023-04-30 14:55:55');
INSERT INTO `sys_role_right` VALUES (84, 6, 36, '2023-04-30 14:55:55', '2023-04-30 14:55:55');
INSERT INTO `sys_role_right` VALUES (86, 2, 37, '2023-05-01 18:26:27', '2023-05-01 18:26:27');
INSERT INTO `sys_role_right` VALUES (87, 3, 37, '2023-05-01 18:26:27', '2023-05-01 18:26:27');
INSERT INTO `sys_role_right` VALUES (88, 4, 37, '2023-05-01 18:26:27', '2023-05-01 18:26:27');
INSERT INTO `sys_role_right` VALUES (90, 6, 37, '2023-05-01 18:26:27', '2023-05-01 18:26:27');
INSERT INTO `sys_role_right` VALUES (99, 2, 40, '2023-05-04 14:36:39', '2023-05-04 14:36:39');
INSERT INTO `sys_role_right` VALUES (100, 3, 40, '2023-05-04 14:36:39', '2023-05-04 14:36:39');
INSERT INTO `sys_role_right` VALUES (101, 4, 40, '2023-05-04 14:36:39', '2023-05-04 14:36:39');
INSERT INTO `sys_role_right` VALUES (103, 6, 40, '2023-05-04 14:36:39', '2023-05-04 14:36:39');
INSERT INTO `sys_role_right` VALUES (105, 2, 41, '2023-05-04 14:36:54', '2023-05-04 14:36:54');
INSERT INTO `sys_role_right` VALUES (106, 3, 41, '2023-05-04 14:36:54', '2023-05-04 14:36:54');
INSERT INTO `sys_role_right` VALUES (107, 4, 41, '2023-05-04 14:36:54', '2023-05-04 14:36:54');
INSERT INTO `sys_role_right` VALUES (109, 6, 41, '2023-05-04 14:36:54', '2023-05-04 14:36:54');
INSERT INTO `sys_role_right` VALUES (111, 2, 42, '2023-05-04 14:38:36', '2023-05-04 14:38:36');
INSERT INTO `sys_role_right` VALUES (112, 3, 42, '2023-05-04 14:38:36', '2023-05-04 14:38:36');
INSERT INTO `sys_role_right` VALUES (144, 4, 53, '2023-05-13 17:28:58', '2023-05-13 17:28:58');
INSERT INTO `sys_role_right` VALUES (145, 3, 53, '2023-05-13 17:28:58', '2023-05-13 17:28:58');
INSERT INTO `sys_role_right` VALUES (146, 2, 53, '2023-05-13 17:28:58', '2023-05-13 17:28:58');
INSERT INTO `sys_role_right` VALUES (148, 6, 53, '2023-05-13 17:28:58', '2023-05-13 17:28:58');
INSERT INTO `sys_role_right` VALUES (152, 2, 50, '2023-05-14 14:22:31', '2023-05-14 14:22:31');
INSERT INTO `sys_role_right` VALUES (153, 3, 50, '2023-05-14 14:22:31', '2023-05-14 14:22:31');
INSERT INTO `sys_role_right` VALUES (155, 2, 51, '2023-05-14 14:25:57', '2023-05-14 14:25:57');
INSERT INTO `sys_role_right` VALUES (156, 3, 51, '2023-05-14 14:25:57', '2023-05-14 14:25:57');
INSERT INTO `sys_role_right` VALUES (158, 2, 52, '2023-05-14 14:26:39', '2023-05-14 14:26:39');
INSERT INTO `sys_role_right` VALUES (159, 3, 52, '2023-05-14 14:26:39', '2023-05-14 14:26:39');
INSERT INTO `sys_role_right` VALUES (227, 1, 1, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (229, 1, 3, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (230, 1, 4, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (231, 1, 5, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (232, 1, 15, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (233, 1, 8, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (234, 1, 20, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (235, 1, 27, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (236, 1, 16, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (237, 1, 17, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (238, 1, 18, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (239, 1, 19, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (240, 1, 26, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (241, 1, 21, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (242, 1, 22, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (243, 1, 23, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (244, 1, 24, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (245, 1, 25, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (246, 1, 30, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (247, 1, 32, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (248, 1, 42, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (249, 1, 28, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (250, 1, 29, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (251, 1, 35, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (252, 1, 36, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (253, 1, 37, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (254, 1, 38, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (255, 1, 40, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (256, 1, 41, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (257, 1, 53, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (260, 1, 50, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (261, 1, 51, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (262, 1, 52, '2023-05-18 22:43:06', '2023-05-18 22:43:06');
INSERT INTO `sys_role_right` VALUES (275, 1, 58, '2023-05-18 23:25:12', '2023-05-18 23:25:12');
INSERT INTO `sys_role_right` VALUES (276, 2, 58, '2023-05-18 23:25:12', '2023-05-18 23:25:12');
INSERT INTO `sys_role_right` VALUES (277, 3, 58, '2023-05-18 23:25:12', '2023-05-18 23:25:12');
INSERT INTO `sys_role_right` VALUES (278, 4, 58, '2023-05-18 23:25:12', '2023-05-18 23:25:12');
INSERT INTO `sys_role_right` VALUES (280, 6, 58, '2023-05-18 23:25:12', '2023-05-18 23:25:12');
INSERT INTO `sys_role_right` VALUES (376, 1, 2, '2023-05-19 01:15:28', '2023-05-19 01:15:28');
INSERT INTO `sys_role_right` VALUES (377, 5, 35, '2023-05-19 01:16:19', '2023-05-19 01:16:19');
INSERT INTO `sys_role_right` VALUES (378, 5, 36, '2023-05-19 01:16:19', '2023-05-19 01:16:19');
INSERT INTO `sys_role_right` VALUES (379, 5, 37, '2023-05-19 01:16:19', '2023-05-19 01:16:19');
INSERT INTO `sys_role_right` VALUES (380, 5, 38, '2023-05-19 01:16:19', '2023-05-19 01:16:19');
INSERT INTO `sys_role_right` VALUES (381, 5, 40, '2023-05-19 01:16:19', '2023-05-19 01:16:19');
INSERT INTO `sys_role_right` VALUES (382, 5, 41, '2023-05-19 01:16:19', '2023-05-19 01:16:19');
INSERT INTO `sys_role_right` VALUES (383, 5, 53, '2023-05-19 01:16:19', '2023-05-19 01:16:19');
INSERT INTO `sys_role_right` VALUES (384, 5, 58, '2023-05-19 01:16:19', '2023-05-19 01:16:19');
INSERT INTO `sys_role_right` VALUES (385, 1, 63, '2023-05-20 20:04:39', '2023-05-20 20:04:39');
INSERT INTO `sys_role_right` VALUES (386, 2, 63, '2023-05-20 20:04:39', '2023-05-20 20:04:39');
INSERT INTO `sys_role_right` VALUES (387, 3, 63, '2023-05-20 20:04:39', '2023-05-20 20:04:39');
INSERT INTO `sys_role_right` VALUES (388, 4, 63, '2023-05-20 20:04:39', '2023-05-20 20:04:39');
INSERT INTO `sys_role_right` VALUES (389, 5, 63, '2023-05-20 20:04:39', '2023-05-20 20:04:39');
INSERT INTO `sys_role_right` VALUES (390, 6, 63, '2023-05-20 20:04:39', '2023-05-20 20:04:39');

-- ----------------------------
-- Table structure for sys_store_batch
-- ----------------------------
DROP TABLE IF EXISTS `sys_store_batch`;
CREATE TABLE `sys_store_batch`  (
  `store_batch_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '店内批次Id',
  `store_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '店铺Id',
  `batch_id` int(11) NULL DEFAULT NULL COMMENT '对应批次Id',
  `drug_detail_id` int(11) NULL DEFAULT NULL COMMENT '药品详情Id',
  `batch_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '对应批次编号',
  `store_batch_purchase_quantity` int(11) NULL DEFAULT 0 COMMENT '请求数量',
  `store_batch_existing_quantity` int(11) NULL DEFAULT 0 COMMENT '现存数量',
  `store_batch_status` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'CREATED' COMMENT '状态',
  `version` int(10) UNSIGNED NOT NULL DEFAULT 1 COMMENT 'version',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `store_batch_remark` varchar(900) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`store_batch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '店内批次' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_store_batch
-- ----------------------------
INSERT INTO `sys_store_batch` VALUES (1, '1', NULL, 1, NULL, 200, 0, 'FORBIDDEN', 2, '2023-04-30 21:46:11', '2023-04-30 21:48:26', '123');
INSERT INTO `sys_store_batch` VALUES (2, '1', 2, 2, 'gml230401', 200, 178, 'SOLD', 27, '2023-04-30 21:46:18', '2023-05-23 15:00:20', '');
INSERT INTO `sys_store_batch` VALUES (3, '1', 3, 3, 'blg230401', 100, 97, 'SOLD', 6, '2023-04-30 21:46:26', '2023-05-19 01:24:04', '');
INSERT INTO `sys_store_batch` VALUES (4, '1', 5, 4, 'blf230401', 100, 97, 'SOLD', 6, '2023-04-30 21:46:32', '2023-05-12 16:17:19', '');
INSERT INTO `sys_store_batch` VALUES (5, '1', 6, 5, 'cbppg230401', 100, 97, 'SOLD', 7, '2023-04-30 21:46:37', '2023-05-19 01:23:41', '');
INSERT INTO `sys_store_batch` VALUES (6, '1', 7, 6, 'hxzqs230401', 100, 99, 'SOLD', 4, '2023-04-30 21:46:48', '2023-05-08 22:35:16', '');
INSERT INTO `sys_store_batch` VALUES (7, '2', 1, 1, 'amxl230401', 100, 100, 'EXPIRED', 22, '2023-04-30 21:46:54', '2023-05-24 16:23:36', '');
INSERT INTO `sys_store_batch` VALUES (8, '2', 2, 2, 'gml230401', 100, 99, 'SOLD', 5, '2023-04-30 21:46:59', '2023-05-24 11:13:12', '');
INSERT INTO `sys_store_batch` VALUES (9, '2', 3, 3, 'blg230401', 100, 99, 'SOLD', 5, '2023-04-30 21:47:03', '2023-05-08 14:26:28', '');
INSERT INTO `sys_store_batch` VALUES (10, '2', 5, 4, 'blf230401', 100, 99, 'SOLD', 5, '2023-04-30 21:47:08', '2023-05-06 10:59:31', '');
INSERT INTO `sys_store_batch` VALUES (11, '2', 6, 5, 'cbppg230401', 100, 99, 'SOLD', 4, '2023-04-30 21:47:13', '2023-05-06 10:59:31', '');
INSERT INTO `sys_store_batch` VALUES (12, '5', 1, 1, 'amxl230401', 100, 100, 'EXPIRED', 21, '2023-04-30 21:47:19', '2023-05-24 16:23:37', '');
INSERT INTO `sys_store_batch` VALUES (13, '5', 2, 2, 'gml230401', 100, 99, 'SOLD', 4, '2023-04-30 21:47:24', '2023-05-08 16:14:39', '');
INSERT INTO `sys_store_batch` VALUES (14, '6', 7, 6, 'hxzqs230401', 100, 99, 'SOLD', 4, '2023-04-30 21:47:31', '2023-05-08 16:18:10', '');
INSERT INTO `sys_store_batch` VALUES (15, '6', 1, 1, 'amxl230401', 20, 20, 'EXPIRED', 22, '2023-04-30 21:47:36', '2023-05-24 16:23:37', '');
INSERT INTO `sys_store_batch` VALUES (16, '6', 3, 3, 'blg230401', 50, 49, 'SOLD', 4, '2023-04-30 21:47:42', '2023-05-06 11:01:49', '');
INSERT INTO `sys_store_batch` VALUES (17, '1', 1, 1, 'amxl230401', 20, 0, 'EXPIRED', 41, '2023-04-30 21:52:30', '2023-05-24 16:23:37', '');
INSERT INTO `sys_store_batch` VALUES (18, '1', 1, 1, 'amxl230401', 20, 19, 'EXPIRED', 22, '2023-04-30 21:52:36', '2023-05-24 16:23:37', '');
INSERT INTO `sys_store_batch` VALUES (19, '1', 1, 1, 'amxl230401', 100, 99, 'EXPIRED', 22, '2023-04-30 21:53:37', '2023-05-24 16:23:37', '');
INSERT INTO `sys_store_batch` VALUES (20, '1', 1, 1, 'amxl230401', 100, 100, 'EXPIRED', 21, '2023-04-30 21:53:46', '2023-05-24 16:23:37', '');
INSERT INTO `sys_store_batch` VALUES (21, '1', 1, 1, 'amxl230401', 100, 100, 'EXPIRED', 21, '2023-04-30 21:56:34', '2023-05-24 16:23:37', '');
INSERT INTO `sys_store_batch` VALUES (22, '1', 5, 4, 'blf230401', 20, 20, 'SOLD', 3, '2023-04-30 21:57:10', '2023-04-30 22:04:37', '');
INSERT INTO `sys_store_batch` VALUES (23, '1', 1, 1, 'amxl230401', 110, 110, 'EXPIRED', 21, '2023-04-30 21:58:17', '2023-05-24 16:23:37', '');
INSERT INTO `sys_store_batch` VALUES (24, '1', 6, 5, 'cbppg230401', 100, 100, 'SOLD', 3, '2023-04-30 21:58:25', '2023-04-30 22:25:02', '');
INSERT INTO `sys_store_batch` VALUES (25, '5', 7, 6, 'hxzqs230401', 10, 9, 'SOLD', 4, '2023-04-30 22:13:00', '2023-05-06 11:00:43', '');
INSERT INTO `sys_store_batch` VALUES (26, '5', 7, 6, 'hxzqs230401', 30, 30, 'SOLD', 3, '2023-04-30 22:13:06', '2023-04-30 22:27:51', '');
INSERT INTO `sys_store_batch` VALUES (27, '1', 6, 5, 'cbppg230401', 20, 20, 'SOLD', 3, '2023-05-02 23:08:07', '2023-05-19 01:21:47', '');
INSERT INTO `sys_store_batch` VALUES (28, '1', 8, 7, 'ynbyqwj230401', 100, 0, 'NORMAL_PURCHASE', 2, '2023-05-14 23:28:48', '2023-05-18 23:02:39', NULL);
INSERT INTO `sys_store_batch` VALUES (29, '1', 8, 7, 'ynbyqwj230401', 100, 0, 'NORMAL_PURCHASE', 2, '2023-05-14 23:28:59', '2023-05-18 23:05:52', NULL);
INSERT INTO `sys_store_batch` VALUES (30, '1', 9, 1, 'amxl230402', 100, 100, 'SOLD', 3, '2023-05-19 00:45:22', '2023-05-19 01:21:43', '');
INSERT INTO `sys_store_batch` VALUES (31, '2', 10, 1, 'amxl230403', 100, 0, 'NORMAL_PURCHASE', 2, '2023-05-19 00:45:49', '2023-05-19 00:46:22', NULL);
INSERT INTO `sys_store_batch` VALUES (32, '2', 13, 1, 'amxl230405', 100, 0, 'NORMAL_PURCHASE', 2, '2023-05-19 00:46:14', '2023-05-19 00:50:23', NULL);
INSERT INTO `sys_store_batch` VALUES (33, '2', 11, 1, 'amxl230404', 100, 0, 'NORMAL_PURCHASE', 1, '2023-05-19 00:46:22', '2023-05-19 00:46:22', NULL);
INSERT INTO `sys_store_batch` VALUES (34, '5', 7, 6, 'hxzqs230401', 100, 0, 'NORMAL_PURCHASE', 2, '2023-05-19 00:52:02', '2023-05-19 00:52:30', NULL);
INSERT INTO `sys_store_batch` VALUES (35, '5', 7, 6, 'hxzqs230401', 100, 0, 'NORMAL_PURCHASE', 2, '2023-05-19 00:52:08', '2023-05-19 00:52:30', NULL);
INSERT INTO `sys_store_batch` VALUES (36, '6', 8, 7, 'ynbyqwj230401', 100, 0, 'NORMAL_PURCHASE', 2, '2023-05-19 00:52:14', '2023-05-19 00:53:01', NULL);
INSERT INTO `sys_store_batch` VALUES (37, '6', 8, 7, 'ynbyqwj230401', 100, 0, 'NORMAL_PURCHASE', 2, '2023-05-19 00:52:22', '2023-05-19 00:53:01', NULL);
INSERT INTO `sys_store_batch` VALUES (38, '1', 16, 9, 'qlppg230501', 10, 0, 'NORMAL_PURCHASE', 2, '2023-05-19 00:59:07', '2023-05-19 00:59:40', NULL);
INSERT INTO `sys_store_batch` VALUES (39, '1', 16, 9, 'qlppg230501', 10, 0, 'NORMAL_PURCHASE', 2, '2023-05-19 00:59:12', '2023-05-19 00:59:40', NULL);
INSERT INTO `sys_store_batch` VALUES (40, '2', 13, 1, 'amxl230405', 10, 0, 'NORMAL_PURCHASE', 2, '2023-05-19 00:59:21', '2023-05-19 00:59:35', NULL);
INSERT INTO `sys_store_batch` VALUES (41, '1', 2, 2, 'gml230401', 10, 10, 'SOLD', 3, '2023-05-19 01:08:10', '2023-05-19 01:12:39', '');
INSERT INTO `sys_store_batch` VALUES (42, '1', 2, 2, 'gml230401', 10, 10, 'SOLD', 3, '2023-05-19 01:08:18', '2023-05-19 01:12:40', '');
INSERT INTO `sys_store_batch` VALUES (43, '2', 2, 2, 'gml230401', 10, 10, 'SOLD', 3, '2023-05-19 01:08:28', '2023-05-19 01:21:53', '');
INSERT INTO `sys_store_batch` VALUES (44, '2', 2, 2, 'gml230401', 10, 10, 'SOLD', 3, '2023-05-19 01:08:35', '2023-05-19 01:21:54', '');
INSERT INTO `sys_store_batch` VALUES (45, '1', 6, 5, 'cbppg230401', 10, 10, 'SOLD', 3, '2023-05-19 01:18:31', '2023-05-19 01:21:47', '');
INSERT INTO `sys_store_batch` VALUES (46, '1', 6, 5, 'cbppg230401', 10, 10, 'SOLD', 3, '2023-05-19 01:18:37', '2023-05-19 01:21:48', '');
INSERT INTO `sys_store_batch` VALUES (47, '2', 13, 1, 'amxl230405', 10, 0, 'NORMAL_PURCHASE', 2, '2023-05-19 01:18:45', '2023-05-19 01:19:13', NULL);
INSERT INTO `sys_store_batch` VALUES (48, '2', 13, 1, 'amxl230405', 10, 0, 'NORMAL_PURCHASE', 2, '2023-05-19 01:18:52', '2023-05-19 01:19:13', NULL);
INSERT INTO `sys_store_batch` VALUES (49, '1', NULL, 1, NULL, 10, 0, 'CREATED', 1, '2023-05-19 17:40:54', '2023-05-19 17:40:54', NULL);
INSERT INTO `sys_store_batch` VALUES (50, '1', NULL, 1, NULL, 10, 0, 'CREATED', 1, '2023-05-19 17:40:59', '2023-05-19 17:40:59', NULL);

-- ----------------------------
-- Table structure for sys_user_basis
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_basis`;
CREATE TABLE `sys_user_basis`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `user_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户密码',
  `user_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `user_real_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `user_gender` enum('男','女','未知') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '未知' COMMENT '性别:男/女/',
  `user_avatar_url` varchar(900) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'https://www.mingpian.red/avatar/images/07.jpg' COMMENT '头像地址',
  `role_id` int(11) NOT NULL DEFAULT 2 COMMENT '角色id;1-管理员/2-普通用户',
  `user_status` int(11) NOT NULL DEFAULT 1 COMMENT '用户状态;1-正常/0-封禁',
  `user_vip` int(11) NOT NULL DEFAULT 0 COMMENT 'vip;0-普通/1-vip',
  `user_birthday` date NULL DEFAULT NULL COMMENT '生日',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `deleted` int(11) NOT NULL DEFAULT 0 COMMENT '逻辑删除;1-删除/0-正常',
  `user_nick_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_name`(`user_name` ASC) USING BTREE,
  UNIQUE INDEX `user_phone`(`user_phone` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '人员基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_basis
-- ----------------------------
INSERT INTO `sys_user_basis` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', '17312344321', '胡靖', '男', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/19/bf82c79d-fe22-45bc-92bd-154c6099bc1a.png', 1, 1, 1, '2023-03-10', '2023-05-19 01:15:40', '2023-03-10 16:24:07', 0, 'yuwenyanhao');
INSERT INTO `sys_user_basis` VALUES (2, 'root', '63a9f0ea7bb98050796b649e85481845', '16673468281', '胡', '男', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/19/fe8a2a63-ff71-4372-9d4b-1bd6b58bdaf5.png', 1, 1, 1, '2023-03-13', '2023-05-19 00:54:46', '2023-03-13 19:41:59', 0, 'nick2');
INSERT INTO `sys_user_basis` VALUES (3, '123123', '4297f44b13955235245b2497399d7a93', '17343042706', '宇文', '男', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/f3688529-bc25-4f30-9d9b-f224ecd194b8.jpeg', 1, 1, 1, '2023-03-30', '2023-05-18 21:25:30', '2023-03-13 19:55:08', 0, 'nick3');
INSERT INTO `sys_user_basis` VALUES (4, 'yuwenyanhao', '59d9b805c3396d2d4f08f76b7e69b860', '17343042707', '宇文延皓', '男', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/adb66554-8778-4a0d-bf67-06388fde05b5.jpeg', 1, 1, 1, '2023-03-02', '2023-05-18 21:25:54', '2023-03-13 20:16:09', 0, 'nick4');
INSERT INTO `sys_user_basis` VALUES (5, '1', 'fcea920f7412b5da7be0cf42b8c93759', '12341232342', '12', '男', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/19/9666b3e2-267e-49a7-9bb1-1a22486b7323.png', 1, 1, 1, '2023-03-02', '2023-05-19 00:28:40', '2023-03-13 20:19:42', 0, '12341232342');
INSERT INTO `sys_user_basis` VALUES (6, '2', 'c81e728d9d4c2f636f067f89cc14862c', '3', '胡', '未知', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg', 1, 1, 1, '2023-03-19', '2023-03-13 20:20:17', '2023-03-13 20:20:17', 0, NULL);
INSERT INTO `sys_user_basis` VALUES (7, '3', 'eccbc87e4b5ce2fe28308fd9f2a7baf3', '4', '4', '男', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg', 1, 1, 1, '2023-03-04', '2023-03-13 20:22:45', '2023-03-13 20:22:45', 0, NULL);
INSERT INTO `sys_user_basis` VALUES (8, 'storemanager', '4e02b0e68f049d9fd40b2013f7b637e2', '13344448888', '张三', '男', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg', 2, 1, 1, '1997-07-01', '2023-04-02 20:55:17', '2023-04-02 20:55:17', 0, 'mr.zhang');
INSERT INTO `sys_user_basis` VALUES (9, 'clerk', '34776981fa47aa6cf3f2915d11bae051', '19944449999', '李四', '男', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg', 3, 1, 1, '2000-07-01', '2023-04-02 20:56:03', '2023-04-02 20:56:03', 0, 'mr.li');
INSERT INTO `sys_user_basis` VALUES (10, 'storemanager2', '11544c7ce1cf247bbfcd8050ddec8257', '17344445555', '王五', '男', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg', 2, 1, 1, '1991-04-04', '2023-04-03 09:21:38', '2023-04-03 09:21:38', 0, 'mr.wang');
INSERT INTO `sys_user_basis` VALUES (11, 'clerk2', '331c8a338939ee7853c1e66182673242', '16677773333', '赵六', '男', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg', 3, 1, 1, '1997-07-01', '2023-04-03 09:22:24', '2023-04-03 09:22:24', 0, 'mr.zhao');
INSERT INTO `sys_user_basis` VALUES (12, 'supplier', '99b0e8da24e29e4ccb5d7d76e677c2ac', '12333334444', '供应商1', '男', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg', 4, 1, 1, '1990-04-01', '2023-04-04 00:02:45', '2023-04-04 00:02:45', 0, 'su1');
INSERT INTO `sys_user_basis` VALUES (13, 'supplier2', 'f9e95ee553f7954b8bd113060450720d', '11133334444', '供应商2', '男', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/adb66554-8778-4a0d-bf67-06388fde05b5.jpeg', 4, 1, 1, '1995-04-04', '2023-04-04 00:03:15', '2023-04-04 00:03:15', 0, 'su2');
INSERT INTO `sys_user_basis` VALUES (14, 'user1', '24c9e15e52afc47c225b757e7bee1f9d', '19944448888', '用户1', '女', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg', 5, 1, 1, '2023-03-06', '2023-04-04 00:03:52', '2023-04-04 00:03:52', 0, 'u1');
INSERT INTO `sys_user_basis` VALUES (15, 'user2', '7e58d63b60197ceb55a1c487989a3720', '18811118888', '用户2', '男', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/adb66554-8778-4a0d-bf67-06388fde05b5.jpeg', 5, 1, 0, '2023-04-04', '2023-04-04 00:04:26', '2023-04-04 00:04:26', 0, 'u2');
INSERT INTO `sys_user_basis` VALUES (16, 'supplier3', 'a9f10294d6a1f965fad9924ae613d999', '11122223333', '供应商3', '男', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/adb66554-8778-4a0d-bf67-06388fde05b5.jpeg', 4, 1, 1, '2023-04-04', '2023-04-04 00:08:18', '2023-04-04 00:08:18', 0, 'su3');
INSERT INTO `sys_user_basis` VALUES (17, 'supplier4', '02adbc4ce4aa2e4ae759e998a0ebf8fb', '12255556666', '供应商4', '女', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg', 4, 1, 1, '2023-04-04', '2023-04-04 00:08:48', '2023-04-04 00:08:48', 0, 'su4');
INSERT INTO `sys_user_basis` VALUES (18, 'supplier5', '172737c71a344666428dae1ec30836a6', '13644443333', '供应商5', '女', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg', 4, 1, 0, '2023-04-04', '2023-04-04 00:09:22', '2023-04-04 00:09:22', 0, 'su5');
INSERT INTO `sys_user_basis` VALUES (19, 'supplier6', '43d221f8b11c49e254e56f3447378bd8', '16644443333', '供应商6', '男', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/adb66554-8778-4a0d-bf67-06388fde05b5.jpeg', 4, 1, 1, '2023-04-04', '2023-04-04 00:09:48', '2023-04-04 00:09:48', 0, 'su6');
INSERT INTO `sys_user_basis` VALUES (20, 'supplier10', '6501f691b698b2dcbcd638073ac28ec7', '10100001111', '供应商7', '男', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg', 4, 1, 0, '2023-04-04', '2023-04-04 00:10:12', '2023-04-04 00:10:12', 0, 'su7');
INSERT INTO `sys_user_basis` VALUES (21, 'delivery1', '763d21e2fadf44104db6c222d54a15af', '19934458888', 'delivery11', '男', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/18/7517f435-b00f-4a38-bf18-821efddf95ef.jpeg', 6, 1, 1, '1996-04-01', '2023-04-07 16:26:31', '2023-04-07 16:26:31', 0, 'delivery1');
INSERT INTO `sys_user_basis` VALUES (22, 'qweqwe', 'qweqwe', '17872133305', '张三', '男', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/userpath/2023/05/09/85c28e0f-3a62-4a72-ae5f-78780b4433af.png', 6, 1, 1, '2023-05-01', '2023-05-09 17:54:34', '2023-05-09 17:04:21', 0, '张三家');

-- ----------------------------
-- Table structure for tb_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_address`;
CREATE TABLE `tb_address`  (
  `address_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '地址id',
  `address_content` varchar(900) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址内容',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `user_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `is_default` int(11) NULL DEFAULT NULL COMMENT '默认',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`address_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_address
-- ----------------------------
INSERT INTO `tb_address` VALUES (2, '地址2', 1, '姓名2', '17343042705', 1, '2023-05-13 18:39:05', '2023-05-15 23:31:08');
INSERT INTO `tb_address` VALUES (3, '地址3', 1, '姓名3', '17343042705', 0, '2023-05-13 18:39:10', '2023-05-15 23:31:08');
INSERT INTO `tb_address` VALUES (4, '地址4', 1, '姓名4', '17343042705', 0, '2023-05-13 18:39:45', '2023-05-15 23:31:08');

-- ----------------------------
-- Table structure for tb_delivery
-- ----------------------------
DROP TABLE IF EXISTS `tb_delivery`;
CREATE TABLE `tb_delivery`  (
  `delivery_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '配送id',
  `store_id` int(11) NULL DEFAULT NULL COMMENT '店铺id',
  `delivery_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '配送状态;创建、分配、取货、配送、到达',
  `batch_id` int(11) NULL DEFAULT NULL COMMENT '仓库批次id',
  `store_batch_id` int(11) NULL DEFAULT NULL COMMENT '店铺批次id',
  `drug_detail_id` int(11) NULL DEFAULT NULL COMMENT '药品详情id',
  `delivery_num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '配送人员id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`delivery_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '配送表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_delivery
-- ----------------------------
INSERT INTO `tb_delivery` VALUES (1, 1, 'ARRIVE', 2, 2, 2, 200, 21, '2023-04-30 21:49:10', '2023-04-30 22:02:57');
INSERT INTO `tb_delivery` VALUES (2, 1, 'ARRIVE', 3, 3, 3, 100, 21, '2023-04-30 21:51:16', '2023-04-30 22:02:57');
INSERT INTO `tb_delivery` VALUES (3, 1, 'ARRIVE', 5, 4, 4, 100, 21, '2023-04-30 21:51:22', '2023-04-30 22:02:57');
INSERT INTO `tb_delivery` VALUES (4, 1, 'ARRIVE', 6, 5, 5, 100, 21, '2023-04-30 21:51:23', '2023-04-30 22:02:58');
INSERT INTO `tb_delivery` VALUES (6, 1, 'ARRIVE', 7, 6, 6, 100, 21, '2023-04-30 21:51:25', '2023-04-30 22:02:58');
INSERT INTO `tb_delivery` VALUES (7, 2, 'ARRIVE', 1, 7, 1, 100, 21, '2023-04-30 21:51:32', '2023-04-30 22:02:58');
INSERT INTO `tb_delivery` VALUES (9, 2, 'ARRIVE', 2, 8, 2, 100, 21, '2023-04-30 21:51:34', '2023-04-30 22:02:58');
INSERT INTO `tb_delivery` VALUES (11, 2, 'ARRIVE', 3, 9, 3, 100, 21, '2023-04-30 21:51:35', '2023-04-30 22:02:59');
INSERT INTO `tb_delivery` VALUES (13, 2, 'ARRIVE', 5, 10, 4, 100, 21, '2023-04-30 21:51:36', '2023-04-30 22:02:59');
INSERT INTO `tb_delivery` VALUES (15, 2, 'ARRIVE', 6, 11, 5, 100, 21, '2023-04-30 21:51:37', '2023-04-30 22:03:00');
INSERT INTO `tb_delivery` VALUES (16, 5, 'ARRIVE', 1, 12, 1, 100, 21, '2023-04-30 21:51:41', '2023-04-30 22:03:00');
INSERT INTO `tb_delivery` VALUES (17, 5, 'ARRIVE', 2, 13, 2, 100, 21, '2023-04-30 21:51:42', '2023-04-30 22:03:00');
INSERT INTO `tb_delivery` VALUES (18, 6, 'ARRIVE', 7, 14, 6, 100, 21, '2023-04-30 21:51:46', '2023-04-30 22:03:00');
INSERT INTO `tb_delivery` VALUES (19, 6, 'ARRIVE', 1, 15, 1, 20, 21, '2023-04-30 21:51:47', '2023-04-30 22:03:00');
INSERT INTO `tb_delivery` VALUES (21, 6, 'ARRIVE', 3, 16, 3, 50, 21, '2023-04-30 21:51:51', '2023-04-30 22:03:01');
INSERT INTO `tb_delivery` VALUES (22, 1, 'ARRIVE', 1, 17, 1, 20, 21, '2023-04-30 21:52:44', '2023-04-30 22:03:01');
INSERT INTO `tb_delivery` VALUES (23, 1, 'ARRIVE', 1, 18, 1, 20, 21, '2023-04-30 21:52:44', '2023-04-30 22:03:01');
INSERT INTO `tb_delivery` VALUES (24, 1, 'ARRIVE', 1, 19, 1, 100, 21, '2023-04-30 21:53:50', '2023-04-30 22:03:02');
INSERT INTO `tb_delivery` VALUES (25, 1, 'ARRIVE', 1, 20, 1, 100, 21, '2023-04-30 21:53:50', '2023-04-30 22:03:02');
INSERT INTO `tb_delivery` VALUES (26, 1, 'ARRIVE', 1, 21, 1, 100, 21, '2023-04-30 21:56:39', '2023-04-30 22:03:02');
INSERT INTO `tb_delivery` VALUES (27, 1, 'ARRIVE', 5, 22, 4, 20, 21, '2023-04-30 21:57:17', '2023-04-30 22:03:02');
INSERT INTO `tb_delivery` VALUES (28, 1, 'ARRIVE', 1, 23, 1, 110, 21, '2023-04-30 21:58:29', '2023-04-30 22:03:03');
INSERT INTO `tb_delivery` VALUES (29, 1, 'ARRIVE', 6, 24, 5, 100, 21, '2023-04-30 21:58:32', '2023-04-30 22:24:49');
INSERT INTO `tb_delivery` VALUES (30, 5, 'ARRIVE', 7, 25, 6, 10, 21, '2023-04-30 22:16:56', '2023-04-30 22:24:50');
INSERT INTO `tb_delivery` VALUES (31, 5, 'ARRIVE', 7, 26, 6, 30, 21, '2023-04-30 22:16:56', '2023-04-30 22:24:50');
INSERT INTO `tb_delivery` VALUES (32, 1, 'ARRIVE', 6, 27, 5, 20, 21, '2023-05-02 23:08:13', '2023-05-19 01:11:50');
INSERT INTO `tb_delivery` VALUES (33, 1, 'ARRIVE', 8, 28, 7, 100, 21, '2023-05-18 23:02:39', '2023-05-19 01:11:51');
INSERT INTO `tb_delivery` VALUES (34, 1, 'ARRIVE', 8, 29, 7, 100, 21, '2023-05-18 23:05:52', '2023-05-19 01:12:33');
INSERT INTO `tb_delivery` VALUES (35, 1, 'ARRIVE', 9, 30, 1, 100, 21, '2023-05-19 00:45:56', '2023-05-19 01:12:33');
INSERT INTO `tb_delivery` VALUES (36, 2, 'ARRIVE', 10, 31, 1, 100, 21, '2023-05-19 00:46:22', '2023-05-19 01:12:34');
INSERT INTO `tb_delivery` VALUES (37, 2, 'ARRIVE', 11, 32, 1, 100, 21, '2023-05-19 00:46:22', '2023-05-19 01:21:26');
INSERT INTO `tb_delivery` VALUES (38, 2, 'ARRIVE', 13, 32, 1, 100, 21, '2023-05-19 00:50:23', '2023-05-19 01:21:27');
INSERT INTO `tb_delivery` VALUES (39, 5, 'ARRIVE', 7, 34, 6, 100, 21, '2023-05-19 00:52:30', '2023-05-19 01:21:27');
INSERT INTO `tb_delivery` VALUES (40, 5, 'ARRIVE', 7, 35, 6, 100, 21, '2023-05-19 00:52:30', '2023-05-19 01:21:27');
INSERT INTO `tb_delivery` VALUES (41, 6, 'ARRIVE', 8, 36, 7, 100, 21, '2023-05-19 00:53:01', '2023-05-19 01:21:27');
INSERT INTO `tb_delivery` VALUES (42, 6, 'ARRIVE', 8, 37, 7, 100, 21, '2023-05-19 00:53:01', '2023-05-19 01:21:27');
INSERT INTO `tb_delivery` VALUES (43, 2, 'ARRIVE', 13, 40, 1, 10, 21, '2023-05-19 00:59:35', '2023-05-19 01:21:28');
INSERT INTO `tb_delivery` VALUES (44, 1, 'ARRIVE', 16, 38, 9, 10, 21, '2023-05-19 00:59:40', '2023-05-19 01:21:28');
INSERT INTO `tb_delivery` VALUES (45, 1, 'ARRIVE', 16, 39, 9, 10, 21, '2023-05-19 00:59:40', '2023-05-19 01:12:34');
INSERT INTO `tb_delivery` VALUES (46, 1, 'ARRIVE', 2, 41, 2, 10, 21, '2023-05-19 01:08:47', '2023-05-19 01:12:34');
INSERT INTO `tb_delivery` VALUES (47, 1, 'ARRIVE', 2, 42, 2, 10, 21, '2023-05-19 01:08:49', '2023-05-19 01:12:34');
INSERT INTO `tb_delivery` VALUES (48, 2, 'ARRIVE', 2, 43, 2, 10, 21, '2023-05-19 01:08:57', '2023-05-19 01:12:35');
INSERT INTO `tb_delivery` VALUES (49, 2, 'ARRIVE', 2, 44, 2, 10, 21, '2023-05-19 01:08:57', '2023-05-19 01:12:35');
INSERT INTO `tb_delivery` VALUES (50, 1, 'ARRIVE', 6, 45, 5, 10, 21, '2023-05-19 01:19:09', '2023-05-19 01:21:28');
INSERT INTO `tb_delivery` VALUES (51, 1, 'ARRIVE', 6, 46, 5, 10, 21, '2023-05-19 01:19:10', '2023-05-19 01:21:28');
INSERT INTO `tb_delivery` VALUES (52, 2, 'ARRIVE', 13, 47, 1, 10, 21, '2023-05-19 01:19:13', '2023-05-19 01:21:28');
INSERT INTO `tb_delivery` VALUES (53, 2, 'ARRIVE', 13, 48, 1, 10, 21, '2023-05-19 01:19:13', '2023-05-19 01:21:29');

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号，主键',
  `order_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '订单编码',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户Id',
  `operate_user_id` int(11) NULL DEFAULT NULL COMMENT '操作人Id',
  `amount` decimal(24, 6) NULL DEFAULT NULL COMMENT '订单总金额',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '订单状态',
  `payment_method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '支付方式',
  `payment_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `delivery_address` varchar(900) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '送货地址',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `user_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `remark` varchar(900) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `order_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `store_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES (33, '20230506105754472741', 1, 1, 32.000000, 'SUCCESS', 'ALIPAY', '2023-05-06 10:58:48', '', NULL, NULL, NULL, '2023-05-06 10:57:54', '2023-05-06 10:58:48', 'OFFLINE', 1);
INSERT INTO `tb_order` VALUES (34, '20230506105930473286', 1, 1, 57.000000, 'SUCCESS', 'ALIPAY', '2023-05-06 10:59:57', '', NULL, NULL, NULL, '2023-05-06 10:59:31', '2023-05-06 10:59:57', 'OFFLINE', 2);
INSERT INTO `tb_order` VALUES (35, '20230506110042451184', 1, 1, 6.000000, 'SUCCESS', 'ALIPAY', '2023-05-06 11:01:32', '', NULL, NULL, NULL, '2023-05-06 11:00:43', '2023-05-06 11:01:32', 'OFFLINE', 5);
INSERT INTO `tb_order` VALUES (36, '20230506110149779020', 1, 1, 12.000000, 'SUCCESS', 'ALIPAY', '2023-05-06 11:02:14', '', NULL, NULL, NULL, '2023-05-06 11:01:49', '2023-05-06 11:02:14', 'OFFLINE', 6);
INSERT INTO `tb_order` VALUES (37, '20230506110336328867', 1, 1, 57.000000, 'SUCCESS', 'CASH', '2023-05-06 11:03:38', '', NULL, NULL, NULL, '2023-05-06 11:03:36', '2023-05-06 11:03:38', 'OFFLINE', 1);
INSERT INTO `tb_order` VALUES (38, '202305081426273915', 1, 1, 12.000000, 'SUCCESS', 'ALIPAY', '2023-05-08 21:11:50', '', NULL, NULL, NULL, '2023-05-08 14:26:28', '2023-05-08 14:26:57', 'OFFLINE', 2);
INSERT INTO `tb_order` VALUES (39, '20230508145807968058', 1, 1, 20.000000, 'SUCCESS', 'ALIPAY', '2023-05-08 21:11:53', '', NULL, NULL, NULL, '2023-05-08 14:58:07', '2023-05-08 15:26:47', 'OFFLINE', 1);
INSERT INTO `tb_order` VALUES (40, '20230508161438149718', 1, 1, 20.000000, 'SUCCESS', 'ALIPAY', '2023-05-08 21:11:54', '', NULL, NULL, NULL, '2023-05-08 16:14:39', '2023-05-08 16:15:01', 'OFFLINE', 5);
INSERT INTO `tb_order` VALUES (41, '20230508161809167792', 1, 1, 6.000000, 'SUCCESS', 'ALIPAY', '2023-05-08 21:11:55', '', NULL, NULL, NULL, '2023-05-08 16:18:10', '2023-05-08 16:18:27', 'OFFLINE', 6);
INSERT INTO `tb_order` VALUES (42, '20230508162350574709', 1, 1, 32.000000, 'SUCCESS', 'ALIPAY', '2023-05-08 16:24:09', '', NULL, NULL, NULL, '2023-05-08 16:23:50', '2023-05-08 16:24:09', 'OFFLINE', 1);
INSERT INTO `tb_order` VALUES (43, '20230508172156172378', 1, 1, 22.000000, 'SUCCESS', 'ALIPAY', '2023-05-08 17:22:19', '', NULL, NULL, NULL, '2023-05-08 17:21:56', '2023-05-08 17:22:19', 'OFFLINE', 1);
INSERT INTO `tb_order` VALUES (44, '20230508223516851967', 1, 1, 6.000000, 'SUCCESS', 'ALIPAY', '2023-05-08 22:35:47', '', NULL, NULL, NULL, '2023-05-08 22:35:16', '2023-05-08 22:35:47', 'OFFLINE', 1);
INSERT INTO `tb_order` VALUES (45, '20230508225919273312', 1, 1, 12.000000, 'REFUND', 'ALIPAY', '2023-05-08 23:00:15', '', NULL, NULL, NULL, '2023-05-08 22:59:20', '2023-05-09 00:57:22', 'OFFLINE', 1);
INSERT INTO `tb_order` VALUES (46, '20230512161719211243', 1, 1, 22.000000, 'SUCCESS', 'ALIPAY', '2023-05-12 16:17:36', '自取', NULL, NULL, '', '2023-05-12 16:17:19', '2023-05-12 16:17:36', 'ONLINE', 1);
INSERT INTO `tb_order` VALUES (47, '20230512162148285073', 1, 1, 40.000000, 'SUCCESS', 'ALIPAY', '2023-05-12 16:21:59', '自取', NULL, NULL, '', '2023-05-12 16:21:48', '2023-05-12 16:21:59', 'ONLINE', 1);
INSERT INTO `tb_order` VALUES (48, '20230514011114379072', 1, 1, 12.000000, 'SUCCESS', 'ALIPAY', '2023-05-14 01:11:45', '地址2', '姓名2', '17343042705', '', '2023-05-14 01:11:14', '2023-05-14 01:11:45', 'ONLINE', 1);
INSERT INTO `tb_order` VALUES (49, '20230519010046401186', NULL, 1, 12.000000, 'CREATED', NULL, NULL, '', NULL, NULL, NULL, '2023-05-19 01:00:47', '2023-05-19 01:00:47', NULL, 1);
INSERT INTO `tb_order` VALUES (50, '20230519010948696498', 1, 1, 12.000000, 'SUCCESS', 'CASH', '2023-05-19 01:10:01', '', NULL, NULL, NULL, '2023-05-19 01:09:49', '2023-05-19 01:10:01', 'OFFLINE', 1);
INSERT INTO `tb_order` VALUES (53, '20230519011422820157', 1, 1, 35.000000, 'CREATED', 'ALIPAY', NULL, '地址2', '姓名2', '17343042705', '', '2023-05-19 01:14:22', '2023-05-19 01:14:22', 'ONLINE', 1);
INSERT INTO `tb_order` VALUES (54, '20230519011955744324', 1, 1, 20.000000, 'SUCCESS', 'ALIPAY', '2023-05-19 01:20:40', '', NULL, NULL, NULL, '2023-05-19 01:19:55', '2023-05-19 01:20:40', 'OFFLINE', 1);
INSERT INTO `tb_order` VALUES (55, '2023051901234087865', 1, 1, 35.000000, 'SUCCESS', 'ALIPAY', '2023-05-19 01:38:05', '地址4', '姓名4', '17343042705', '', '2023-05-19 01:23:41', '2023-05-19 01:38:05', 'ONLINE', 1);
INSERT INTO `tb_order` VALUES (56, '2023051901240466348', 1, 1, 52.000000, 'SUCCESS', 'ALIPAY', '2023-05-19 01:37:50', '地址2', '姓名2', '17343042705', '', '2023-05-19 01:24:04', '2023-05-19 01:37:50', 'ONLINE', 1);
INSERT INTO `tb_order` VALUES (57, '20230523145526435851', NULL, 1, 20.000000, 'GIVE_UP', NULL, NULL, '', NULL, NULL, NULL, '2023-05-23 14:55:27', '2023-05-23 15:00:20', NULL, 1);
INSERT INTO `tb_order` VALUES (58, '20230524111312970080', 1, 1, 20.000000, 'SUCCESS', 'ALIPAY', '2023-05-24 11:14:41', '地址2', '姓名2', '17343042705', '', '2023-05-24 11:13:12', '2023-05-24 11:14:41', 'ONLINE', 2);

-- ----------------------------
-- Table structure for tb_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_detail`;
CREATE TABLE `tb_order_detail`  (
  `order_detail_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单详情编号',
  `order_id` int(11) NULL DEFAULT NULL COMMENT '订单编号',
  `drug_detail_id` int(11) NULL DEFAULT NULL COMMENT '药品详情id',
  `drug_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '药品名称',
  `drug_price` decimal(24, 6) NULL DEFAULT NULL COMMENT '零售价',
  `total_price` decimal(24, 6) NULL DEFAULT NULL COMMENT '商品总价',
  `quantity` int(11) NULL DEFAULT NULL COMMENT '商品数量',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `store_batch_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`order_detail_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_order_detail
-- ----------------------------
INSERT INTO `tb_order_detail` VALUES (37, 33, 2, '感冒灵颗粒', 20.000000, 20.000000, 1, '2023-05-06 10:57:54', '2023-05-06 10:57:54', 2);
INSERT INTO `tb_order_detail` VALUES (38, 33, 3, '板蓝根颗粒', 12.000000, 12.000000, 1, '2023-05-06 10:57:54', '2023-05-06 10:57:54', 3);
INSERT INTO `tb_order_detail` VALUES (39, 34, 4, '布洛芬胶囊', 22.000000, 22.000000, 1, '2023-05-06 10:59:31', '2023-05-06 10:59:31', 10);
INSERT INTO `tb_order_detail` VALUES (40, 34, 5, '川贝枇杷膏', 35.000000, 35.000000, 1, '2023-05-06 10:59:31', '2023-05-06 10:59:31', 11);
INSERT INTO `tb_order_detail` VALUES (41, 35, 6, '藿香正气水', 6.000000, 6.000000, 1, '2023-05-06 11:00:43', '2023-05-06 11:00:43', 25);
INSERT INTO `tb_order_detail` VALUES (42, 36, 3, '板蓝根颗粒', 12.000000, 12.000000, 1, '2023-05-06 11:01:49', '2023-05-06 11:01:49', 16);
INSERT INTO `tb_order_detail` VALUES (43, 37, 4, '布洛芬胶囊', 22.000000, 22.000000, 1, '2023-05-06 11:03:36', '2023-05-06 11:03:36', 4);
INSERT INTO `tb_order_detail` VALUES (44, 37, 5, '川贝枇杷膏', 35.000000, 35.000000, 1, '2023-05-06 11:03:36', '2023-05-06 11:03:36', 5);
INSERT INTO `tb_order_detail` VALUES (45, 38, 3, '板蓝根颗粒', 12.000000, 12.000000, 1, '2023-05-08 14:26:28', '2023-05-08 14:26:28', 9);
INSERT INTO `tb_order_detail` VALUES (46, 39, 2, '感冒灵颗粒', 20.000000, 20.000000, 1, '2023-05-08 14:58:07', '2023-05-08 14:58:07', 2);
INSERT INTO `tb_order_detail` VALUES (47, 40, 2, '感冒灵颗粒', 20.000000, 20.000000, 1, '2023-05-08 16:14:39', '2023-05-08 16:14:39', 13);
INSERT INTO `tb_order_detail` VALUES (48, 41, 6, '藿香正气水', 6.000000, 6.000000, 1, '2023-05-08 16:18:10', '2023-05-08 16:18:10', 14);
INSERT INTO `tb_order_detail` VALUES (49, 42, 1, '阿莫西林胶囊', 12.000000, 12.000000, 1, '2023-05-08 16:23:50', '2023-05-08 16:23:50', 17);
INSERT INTO `tb_order_detail` VALUES (50, 42, 2, '感冒灵颗粒', 20.000000, 20.000000, 1, '2023-05-08 16:23:50', '2023-05-08 16:23:50', 2);
INSERT INTO `tb_order_detail` VALUES (51, 43, 4, '布洛芬胶囊', 22.000000, 22.000000, 1, '2023-05-08 17:21:56', '2023-05-08 17:21:56', 4);
INSERT INTO `tb_order_detail` VALUES (52, 44, 6, '藿香正气水', 6.000000, 6.000000, 1, '2023-05-08 22:35:16', '2023-05-08 22:35:16', 6);
INSERT INTO `tb_order_detail` VALUES (53, 45, 1, '阿莫西林胶囊', 12.000000, 12.000000, 1, '2023-05-08 22:59:20', '2023-05-08 22:59:20', 19);
INSERT INTO `tb_order_detail` VALUES (54, 46, 4, '布洛芬胶囊', 22.000000, 22.000000, 1, '2023-05-12 16:17:19', '2023-05-12 16:17:19', 4);
INSERT INTO `tb_order_detail` VALUES (55, 47, 2, '感冒灵颗粒', 20.000000, 40.000000, 2, '2023-05-12 16:21:48', '2023-05-12 16:21:48', 2);
INSERT INTO `tb_order_detail` VALUES (56, 48, 3, '板蓝根颗粒', 12.000000, 12.000000, 1, '2023-05-14 01:11:14', '2023-05-14 01:11:14', 3);
INSERT INTO `tb_order_detail` VALUES (57, 49, 1, '阿莫西林胶囊', 12.000000, 12.000000, 1, '2023-05-19 01:00:47', '2023-05-19 01:00:47', 17);
INSERT INTO `tb_order_detail` VALUES (58, 50, 1, '阿莫西林胶囊', 12.000000, 12.000000, 1, '2023-05-19 01:09:49', '2023-05-19 01:09:49', 18);
INSERT INTO `tb_order_detail` VALUES (59, 53, 5, '川贝枇杷膏', 35.000000, 35.000000, 1, '2023-05-19 01:14:22', '2023-05-19 01:14:22', 5);
INSERT INTO `tb_order_detail` VALUES (60, 54, 2, '感冒灵颗粒', 20.000000, 20.000000, 1, '2023-05-19 01:19:55', '2023-05-19 01:19:55', 2);
INSERT INTO `tb_order_detail` VALUES (61, 55, 5, '川贝枇杷膏', 35.000000, 35.000000, 1, '2023-05-19 01:23:41', '2023-05-19 01:23:41', 5);
INSERT INTO `tb_order_detail` VALUES (62, 56, 2, '感冒灵颗粒', 20.000000, 40.000000, 2, '2023-05-19 01:24:04', '2023-05-19 01:24:04', 2);
INSERT INTO `tb_order_detail` VALUES (63, 56, 3, '板蓝根颗粒', 12.000000, 12.000000, 1, '2023-05-19 01:24:04', '2023-05-19 01:24:04', 3);
INSERT INTO `tb_order_detail` VALUES (64, 57, 2, '感冒灵颗粒', 20.000000, 20.000000, 1, '2023-05-23 14:55:27', '2023-05-23 14:55:27', 2);
INSERT INTO `tb_order_detail` VALUES (65, 58, 2, '感冒灵颗粒', 20.000000, 20.000000, 1, '2023-05-24 11:13:12', '2023-05-24 11:13:12', 8);

-- ----------------------------
-- Table structure for tb_pay_match
-- ----------------------------
DROP TABLE IF EXISTS `tb_pay_match`;
CREATE TABLE `tb_pay_match`  (
  `out_trade_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '支付宝订单号',
  `order_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'order订单号',
  `trade_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`out_trade_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '支付订单对应表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_pay_match
-- ----------------------------
INSERT INTO `tb_pay_match` VALUES ('202305061058280001', '20230506105754472741', NULL);
INSERT INTO `tb_pay_match` VALUES ('202305061059400001', '20230506105930473286', NULL);
INSERT INTO `tb_pay_match` VALUES ('202305061101180001', '20230506110042451184', NULL);
INSERT INTO `tb_pay_match` VALUES ('202305061102010001', '20230506110149779020', NULL);
INSERT INTO `tb_pay_match` VALUES ('202305081426370001', '202305081426273915', NULL);
INSERT INTO `tb_pay_match` VALUES ('202305081458160001', '20230508145807968058', NULL);
INSERT INTO `tb_pay_match` VALUES ('202305081614430001', '20230508161438149718', NULL);
INSERT INTO `tb_pay_match` VALUES ('202305081618150001', '20230508161809167792', NULL);
INSERT INTO `tb_pay_match` VALUES ('202305081623550001', '20230508162350574709', NULL);
INSERT INTO `tb_pay_match` VALUES ('202305081722010001', '20230508172156172378', '2023050822001460590500084265');
INSERT INTO `tb_pay_match` VALUES ('202305082235240001', '20230508223516851967', '2023050822001460590500085644');
INSERT INTO `tb_pay_match` VALUES ('202305082259250001', '20230508225919273312', '2023050822001460590500078394');
INSERT INTO `tb_pay_match` VALUES ('202305121617190001', '20230512161719211243', '2023051222001460590500095825');
INSERT INTO `tb_pay_match` VALUES ('202305121621480001', '20230512162148285073', '2023051222001460590500097562');
INSERT INTO `tb_pay_match` VALUES ('202305140111140001', '20230514011114379072', '2023051422001460590500106447');
INSERT INTO `tb_pay_match` VALUES ('202305190114220001', '20230519011422820157', NULL);
INSERT INTO `tb_pay_match` VALUES ('202305190120020001', '20230519011955744324', '2023051922001460590500130701');
INSERT INTO `tb_pay_match` VALUES ('202305190123400001', '2023051901234087865', '2023051922001460590500129029');
INSERT INTO `tb_pay_match` VALUES ('202305190124040001', '2023051901240466348', '2023051922001460590500119366');
INSERT INTO `tb_pay_match` VALUES ('202305241113120001', '20230524111312970080', '2023052422001460590500145780');

-- ----------------------------
-- Table structure for tb_phone_code
-- ----------------------------
DROP TABLE IF EXISTS `tb_phone_code`;
CREATE TABLE `tb_phone_code`  (
  `phone_code_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '登录验证码id',
  `phone_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '验证码',
  `createTime` date NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`phone_code_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '登录验证码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_phone_code
-- ----------------------------

-- ----------------------------
-- Table structure for tb_position
-- ----------------------------
DROP TABLE IF EXISTS `tb_position`;
CREATE TABLE `tb_position`  (
  `position_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '职位id',
  `position_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '职位名称',
  `salary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标准薪水',
  `responsibility` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`position_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '职位表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_position
-- ----------------------------
INSERT INTO `tb_position` VALUES (1, '店长', '9000', '123', '2023-04-02 16:28:24', '2023-05-19 01:18:00');
INSERT INTO `tb_position` VALUES (2, '销售', '4100', '日常销售，分组外出营销', '2023-04-02 17:01:37', '2023-05-19 00:44:54');
INSERT INTO `tb_position` VALUES (4, '药师', '10000', '开药', '2023-04-02 17:07:18', '2023-04-02 17:07:18');

-- ----------------------------
-- Table structure for tb_service
-- ----------------------------
DROP TABLE IF EXISTS `tb_service`;
CREATE TABLE `tb_service`  (
  `service_id` int(11) NOT NULL AUTO_INCREMENT,
  `is_last` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `is_normal` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `service_date` date NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`service_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '服务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_service
-- ----------------------------
INSERT INTO `tb_service` VALUES (5, '0', '0', 1, '2023-05-12', '2023-05-12 01:07:40', '2023-05-12 01:34:18');
INSERT INTO `tb_service` VALUES (6, '0', '1', 1, '2023-05-12', '2023-05-12 01:34:18', '2023-05-12 01:34:59');
INSERT INTO `tb_service` VALUES (7, '0', '1', 1, '2023-05-12', '2023-05-12 01:34:59', '2023-05-19 01:22:46');
INSERT INTO `tb_service` VALUES (8, '0', '1', 1, '2023-05-19', '2023-05-19 01:22:46', '2023-05-19 01:22:53');
INSERT INTO `tb_service` VALUES (9, '1', '1', 1, '2023-05-19', '2023-05-19 01:22:53', '2023-05-19 01:22:53');

-- ----------------------------
-- Table structure for tb_service_detail
-- ----------------------------
DROP TABLE IF EXISTS `tb_service_detail`;
CREATE TABLE `tb_service_detail`  (
  `service_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `service_entry_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `content` varchar(900) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `service_id` int(11) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `modified_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`service_detail_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '服务详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_service_detail
-- ----------------------------
INSERT INTO `tb_service_detail` VALUES (13, 1, 1, '111', 5, '2023-05-12 01:07:40', '2023-05-12 01:24:02');
INSERT INTO `tb_service_detail` VALUES (14, 2, 1, '222', 5, '2023-05-12 01:07:40', '2023-05-12 01:24:02');
INSERT INTO `tb_service_detail` VALUES (15, 3, 1, '333', 5, '2023-05-12 01:07:40', '2023-05-12 01:24:02');
INSERT INTO `tb_service_detail` VALUES (16, 1, 1, '111', 6, '2023-05-12 01:34:18', '2023-05-12 01:34:18');
INSERT INTO `tb_service_detail` VALUES (17, 2, 1, '111', 6, '2023-05-12 01:34:18', '2023-05-12 01:34:18');
INSERT INTO `tb_service_detail` VALUES (18, 3, 1, '5', 6, '2023-05-12 01:34:18', '2023-05-12 01:34:18');
INSERT INTO `tb_service_detail` VALUES (19, 1, 1, '1113', 7, '2023-05-12 01:34:59', '2023-05-14 14:54:04');
INSERT INTO `tb_service_detail` VALUES (20, 2, 1, '122', 7, '2023-05-12 01:34:59', '2023-05-14 14:54:04');
INSERT INTO `tb_service_detail` VALUES (21, 3, 1, '1234', 7, '2023-05-12 01:34:59', '2023-05-14 14:54:04');
INSERT INTO `tb_service_detail` VALUES (22, 1, 1, '123', 8, '2023-05-19 01:22:46', '2023-05-19 01:22:46');
INSERT INTO `tb_service_detail` VALUES (23, 2, 1, '456', 8, '2023-05-19 01:22:46', '2023-05-19 01:22:46');
INSERT INTO `tb_service_detail` VALUES (24, 3, 1, '789', 8, '2023-05-19 01:22:46', '2023-05-19 01:22:46');
INSERT INTO `tb_service_detail` VALUES (25, 4, 1, '123', 8, '2023-05-19 01:22:46', '2023-05-19 01:22:46');
INSERT INTO `tb_service_detail` VALUES (26, 6, 1, '456', 8, '2023-05-19 01:22:46', '2023-05-19 01:22:46');
INSERT INTO `tb_service_detail` VALUES (27, 1, 1, '123', 9, '2023-05-19 01:22:53', '2023-05-19 01:22:53');
INSERT INTO `tb_service_detail` VALUES (28, 2, 1, '456', 9, '2023-05-19 01:22:53', '2023-05-19 01:22:53');
INSERT INTO `tb_service_detail` VALUES (29, 3, 1, '789', 9, '2023-05-19 01:22:53', '2023-05-19 01:22:53');
INSERT INTO `tb_service_detail` VALUES (30, 4, 1, '123', 9, '2023-05-19 01:22:53', '2023-05-19 01:22:53');
INSERT INTO `tb_service_detail` VALUES (31, 6, 1, '456', 9, '2023-05-19 01:22:53', '2023-05-19 01:22:53');

-- ----------------------------
-- Table structure for tb_service_entry
-- ----------------------------
DROP TABLE IF EXISTS `tb_service_entry`;
CREATE TABLE `tb_service_entry`  (
  `service_entry_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '服务条目id',
  `service_entry_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '服务条目内容',
  `remark` varchar(900) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '说明',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`service_entry_id`) USING BTREE,
  UNIQUE INDEX `service_entry_name`(`service_entry_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '记录条目' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_service_entry
-- ----------------------------
INSERT INTO `tb_service_entry` VALUES (1, '身高', '记录身高', '2023-05-11 01:36:25', '2023-05-11 01:36:27');
INSERT INTO `tb_service_entry` VALUES (2, '体重', '体重单位KG', '2023-05-11 00:38:18', '2023-05-11 00:39:32');
INSERT INTO `tb_service_entry` VALUES (3, '视力', '记录左右眼（左|右）', '2023-05-11 00:39:11', '2023-05-11 00:39:11');
INSERT INTO `tb_service_entry` VALUES (4, '是否吸烟', '记录日均吸烟量（支）', '2023-05-12 01:40:46', '2023-05-12 01:40:46');
INSERT INTO `tb_service_entry` VALUES (6, '测试1', '123', '2023-05-19 01:22:20', '2023-05-19 01:22:20');

-- ----------------------------
-- Table structure for tb_shopping_cart
-- ----------------------------
DROP TABLE IF EXISTS `tb_shopping_cart`;
CREATE TABLE `tb_shopping_cart`  (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `drug_detail_id` int(11) NULL DEFAULT NULL COMMENT '详情id',
  `number` int(11) NULL DEFAULT NULL COMMENT '数量',
  `store_id` int(11) NULL DEFAULT NULL COMMENT '店铺id',
  `max_num` int(11) NULL DEFAULT NULL COMMENT '最大数量',
  PRIMARY KEY (`cart_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '购物车' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_shopping_cart
-- ----------------------------
INSERT INTO `tb_shopping_cart` VALUES (1, 1, 1, 5, 1, 100);
INSERT INTO `tb_shopping_cart` VALUES (2, 1, 1, 1, 5, 0);
INSERT INTO `tb_shopping_cart` VALUES (3, 1, 2, 2, 1, 198);
INSERT INTO `tb_shopping_cart` VALUES (4, 1, 3, 2, 1, 97);

-- ----------------------------
-- Table structure for tb_staff
-- ----------------------------
DROP TABLE IF EXISTS `tb_staff`;
CREATE TABLE `tb_staff`  (
  `staff_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '职员id',
  `store_id` int(11) NULL DEFAULT NULL COMMENT '店铺id',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `position_id` int(11) NULL DEFAULT NULL COMMENT '职位id',
  `status` int(11) NULL DEFAULT 1 COMMENT '状态',
  `actual_salary` decimal(24, 6) NULL DEFAULT NULL COMMENT '实际薪水',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`staff_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '工作人员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_staff
-- ----------------------------
INSERT INTO `tb_staff` VALUES (1, 1, 1, 1, 1, 10000.000000, '2023-04-02 18:41:35', '2023-04-02 18:41:35');
INSERT INTO `tb_staff` VALUES (2, 2, 8, 1, 1, 9000.000000, '2023-04-03 09:20:13', '2023-04-03 09:20:13');
INSERT INTO `tb_staff` VALUES (3, 2, 9, 2, 1, 4000.000000, '2023-04-03 09:20:40', '2023-04-03 09:20:40');
INSERT INTO `tb_staff` VALUES (4, 5, 10, 1, 1, 9500.000000, '2023-04-03 09:24:15', '2023-04-03 09:24:15');
INSERT INTO `tb_staff` VALUES (6, 5, 11, 4, 1, 14500.000000, '2023-04-03 00:00:00', '2023-04-03 10:22:17');
INSERT INTO `tb_staff` VALUES (7, 6, 8, 1, 1, 10000.000000, '2023-04-07 17:16:24', '2023-04-07 17:16:24');

-- ----------------------------
-- Table structure for tb_store
-- ----------------------------
DROP TABLE IF EXISTS `tb_store`;
CREATE TABLE `tb_store`  (
  `store_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '店铺ID',
  `store_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '店铺名称',
  `store_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '店铺地址',
  `contact_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
  `store_path` varchar(900) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `business_hours` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '营业时间',
  `create_time` date NULL DEFAULT NULL COMMENT '创建时间',
  `store_status` int(11) NULL DEFAULT 1 COMMENT '状态',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所属区域',
  `business_district` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所属商圈',
  `modified_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`store_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '店铺表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_store
-- ----------------------------
INSERT INTO `tb_store` VALUES (1, '金匮堂大药房', '上海宝山区湄星路8-2号', '13601638181', 'https://yuwenyanhaorbac1.oss-cn-hangzhou.aliyuncs.com/storepath/2023/05/17/604d42e4-1402-40e2-9127-70ad28544871.png', '9:00-21:00', '2023-04-02', 1, '宝山杨行123', '宝山杨行', '2023-05-19 01:17:48');
INSERT INTO `tb_store` VALUES (2, '上海国际医药', '上海市宝山区蕰川公路与宝田路交叉口北260米', '123123123123', 'https://gratisography.com/wp-content/uploads/2023/04/gratisography-wooden-taino-02-stock-photo-1168x780.jpg', '9:00-18:00', '2023-04-02', 1, '宝山杨行', '友谊西路', '2023-04-02 15:17:04');
INSERT INTO `tb_store` VALUES (5, '益丰大药房(海德店)', '上海市宝山区蕰川路2432号106室-2(富锦路地铁站1号口步行60米)', '02156671826', 'https://gratisography.com/wp-content/uploads/2023/04/gratisography-wooden-taino-02-stock-photo-1168x780.jpg', '7:30-24:00', '2023-04-02', 1, '宝山杨行', '富锦路', '2023-04-06 19:11:06');
INSERT INTO `tb_store` VALUES (6, '好药师大药房(乐源康店)', '上海市浦东新区乐昌路27-29号(金领国际西门)', '13564220590', 'https://gratisography.com/wp-content/uploads/2023/04/gratisography-wooden-taino-02-stock-photo-1168x780.jpg', '9:00-18:00', '2023-04-07', 1, '浦东新区乐昌路27-29', '浦东新区乐昌路27-29号(金领国际西门)', '2023-04-07 17:15:39');

-- ----------------------------
-- Table structure for tb_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_type`;
CREATE TABLE `tb_type`  (
  `type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '品类Id',
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '品类名称',
  `type_status` int(11) NULL DEFAULT 1 COMMENT '品类状态',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '品类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_type
-- ----------------------------
INSERT INTO `tb_type` VALUES (1, '抗菌消炎', 1, '2023-04-05 14:54:39', '2023-04-05 14:54:39');
INSERT INTO `tb_type` VALUES (2, '感冒', 1, '2023-04-05 14:56:30', '2023-04-05 14:56:30');
INSERT INTO `tb_type` VALUES (3, '解热镇痛', 1, '2023-04-05 14:57:04', '2023-04-05 14:57:04');
INSERT INTO `tb_type` VALUES (5, '止咳祛痰', 1, '2023-05-01 12:41:14', '2023-05-01 12:41:14');

SET FOREIGN_KEY_CHECKS = 1;
