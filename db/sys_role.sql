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

 Date: 25/05/2023 13:10:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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

SET FOREIGN_KEY_CHECKS = 1;
