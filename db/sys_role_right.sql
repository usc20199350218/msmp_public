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

 Date: 25/05/2023 13:12:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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

SET FOREIGN_KEY_CHECKS = 1;
