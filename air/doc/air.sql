/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : air

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 21/05/2021 15:41:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_attachment
-- ----------------------------
DROP TABLE IF EXISTS `sys_attachment`;
CREATE TABLE `sys_attachment`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `moudle` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模块',
  `business_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务id',
  `file_type` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件类型',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名称',
  `file_size` bigint(30) NULL DEFAULT NULL COMMENT '文件大小',
  `origin_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原来文件名称',
  `file_suffix` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_attachment
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `menu_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `menu_url` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '菜单url',
  `parentId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级',
  `menu_icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标class',
  `menu_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单编码',
  `sort_num` int(11) NULL DEFAULT NULL COMMENT '顺序',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型，0菜单，1按钮',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统设置', NULL, NULL, NULL, 'sys_setting', 1, 0, 'admin', '2021-03-04 15:22:17', 'admin', '2021-03-04 15:22:23');
INSERT INTO `sys_menu` VALUES ('2', '字典管理', NULL, '1', NULL, 'sys_dict', 1, 0, 'admin', '2021-03-04 15:22:17', 'admin', '2021-03-04 15:22:23');
INSERT INTO `sys_menu` VALUES ('3', '菜单管理', NULL, '1', NULL, 'sys_menu', 2, 0, 'admin', '2021-03-04 15:22:17', 'admin', '2021-03-04 15:22:23');
INSERT INTO `sys_menu` VALUES ('4', '用户管理', '/user', NULL, NULL, 'sys_user', 2, 0, 'admin', '2021-03-04 15:22:17', 'admin', '2021-03-04 15:22:23');
INSERT INTO `sys_menu` VALUES ('5', '部门管理', '/dept', NULL, NULL, 'sys_dept', 3, 0, 'admin', '2021-03-04 15:22:17', 'admin', '2021-03-04 15:22:23');
INSERT INTO `sys_menu` VALUES ('6', '个人资料', '/user/info', '4', NULL, 'sys_user_info', 1, 0, 'admin', '2021-03-04 15:22:17', 'admin', '2021-03-04 15:22:23');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `permission_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `descripe` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '描述',
  `permission` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `menu_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单id',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', 'sys:user:info', '22', 'sys:user:info', '1', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `role_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `descripe` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '角色描述',
  `role_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色标识',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', 'admin', 'admin', NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES ('735ce39b111ca24ce46dae69973b8917', '普通用户', '普通用户', 'nomal', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  `menu_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单id',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '1', 'admin', '2021-03-04 15:33:50', 'admin', '2021-03-04 15:33:50');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '2', 'admin', '2021-03-04 15:33:50', 'admin', '2021-03-04 15:33:50');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '3', 'admin', '2021-03-04 15:33:50', 'admin', '2021-03-04 15:33:50');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '4', 'admin', '2021-03-04 15:33:50', 'admin', '2021-03-04 15:33:50');
INSERT INTO `sys_role_menu` VALUES ('5', '1', '5', 'admin', '2021-03-04 15:33:50', 'admin', '2021-03-04 15:33:50');
INSERT INTO `sys_role_menu` VALUES ('6', '1', '6', 'admin', '2021-03-04 15:33:50', 'admin', '2021-03-04 15:33:50');
INSERT INTO `sys_role_menu` VALUES ('7', '735ce39b111ca24ce46dae69973b8917', '4', 'admin', '2021-03-04 15:33:50', 'admin', '2021-03-04 15:33:50');
INSERT INTO `sys_role_menu` VALUES ('8', '735ce39b111ca24ce46dae69973b8917', '5', 'admin', '2021-03-04 15:33:50', 'admin', '2021-03-04 15:33:50');
INSERT INTO `sys_role_menu` VALUES ('9', '735ce39b111ca24ce46dae69973b8917', '6', 'admin', '2021-03-04 15:33:50', 'admin', '2021-03-04 15:33:50');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  `permission_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限id',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_3`(`role_id`) USING BTREE,
  INDEX `FK_Reference_4`(`permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES ('2', '735ce39b111ca24ce46dae69973b8917', '1', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键id',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `nick_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `fail_time` timestamp(0) NULL DEFAULT NULL COMMENT '失败时间',
  `head_thumb` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `phone` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `login_time` timestamp(0) NULL DEFAULT NULL COMMENT '登录时间',
  `login_number` int(11) NULL DEFAULT NULL COMMENT '登录次数',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `current_role` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '当前角色',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('0a6063f54a85ab5e10112c77e6af2bd4', 'bobo', '$2a$10$TrAQt1G1rmRQYzCqnT9rH./B7ZNF2HVRpnMe0HsOWkstkL7SHIUMG', 'haha', NULL, NULL, NULL, NULL, NULL, 1, NULL, 'Guest', '2021-03-04 14:16:37', 'Guest', '2021-03-04 14:16:37');
INSERT INTO `sys_user` VALUES ('12045a197d384f409942d43a546b49fe', 'wdnmd', '0b07112419ace850bf683efe55e46eba', 'wdnmd', NULL, NULL, '15913164654', '2020-03-16 18:11:49', 1, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('761974100d98d6604d65c62c467948e1', 'jayjay', '$2a$10$NoUsrG0sgmV2gwj6me3b8O6dVlifCuvPixfdlXVG5roGuYDeZs0iS', NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('7635472ab0bee13cef276057b7600a26', 'bobo', '$2a$10$9JulfgzRgMi7C5Jl5.i/1eILu5J4S4QflXIOfI9s91PubafO65Eza', 'haha', NULL, NULL, NULL, NULL, NULL, 1, NULL, 'Guest', '2021-03-04 14:50:35', 'Guest', '2021-03-04 14:50:35');
INSERT INTO `sys_user` VALUES ('860956ce88a61b9fa181787a11688674', 'fsd', '0b07112419ace850bf683efe55e46eba', 'wqe', NULL, 'bgo-wiki/uploadImg/467bbb970be3d0015df992a20e2e718a.jpg', '555', NULL, NULL, 1, '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('9787c6032d03723fa381396ec1787ef2', 'yaoyao', '$2a$10$SXhAk5CLpbA9giw9LCGKuu52y4JjUyrmQtINY7fU4waVVCXKYjyMO', 'haha', NULL, NULL, NULL, NULL, NULL, 1, NULL, 'Guest', '2021-03-04 13:58:41', 'Guest', '2021-03-04 13:58:41');
INSERT INTO `sys_user` VALUES ('ac355bdc2fe54c87a085bf2845bc272b', 'admin', '$2a$10$NoUsrG0sgmV2gwj6me3b8O6dVlifCuvPixfdlXVG5roGuYDeZs0iS', 'admin', NULL, NULL, '13025696628', '2020-03-16 18:11:52', 2, 1, '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('e20e1fb6978afab2b2b605195a7b9253', 'dsa', '0b07112419ace850bf683efe55e46eba', 'sadas', NULL, 'bgo-wiki/uploadImg/56abd55ff70d8894bd70ba552a321701.jpg', '123213', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES ('fb9789a00d538203a5402bff0fa87166', 'lisi', '0b07112419ace850bf683efe55e46eba', '李四', NULL, 'bgo-wiki/uploadImg/ea6ae1c72f72a37e3c03605b7fd54ed8.jpg', '13025696628', NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `role_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  `create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Reference_1`(`user_id`) USING BTREE,
  INDEX `FK_Reference_2`(`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', 'ac355bdc2fe54c87a085bf2845bc272b', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` VALUES ('1b45badfec64e2cb4840e1bc80647ef4', '860956ce88a61b9fa181787a11688674', '735ce39b111ca24ce46dae69973b8917', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` VALUES ('1c1716e78b9dbaa505e1fddc8de322f7', '860956ce88a61b9fa181787a11688674', '1', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` VALUES ('443a2494c91c10800f995694e575d369', '761974100d98d6604d65c62c467948e1', '735ce39b111ca24ce46dae69973b8917', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` VALUES ('4d07cf084aac1902d5b00b7547804c62', '7bd61e45b92562aa13be6c4c3034eadc', '735ce39b111ca24ce46dae69973b8917', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` VALUES ('51ddc2173b769126f4a06888d3231d1b', '9787c6032d03723fa381396ec1787ef2', '735ce39b111ca24ce46dae69973b8917', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` VALUES ('537265a1550ea2673647496fde5f3953', '0a6063f54a85ab5e10112c77e6af2bd4', '735ce39b111ca24ce46dae69973b8917', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` VALUES ('820265c4f349754dd044559636141bc0', 'ccae250a9371f42381410fd87dcd3e2f', '735ce39b111ca24ce46dae69973b8917', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` VALUES ('8aa42802b257193bcd72b5f38cdb681c', '8350bbf26b70cad17cfdaa4607380f0c', '735ce39b111ca24ce46dae69973b8917', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` VALUES ('b27100c3550da282e7802d1258d3614e', '7f69e4cbfe3cc59e5a997d145a81bba7', '735ce39b111ca24ce46dae69973b8917', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` VALUES ('d673f5c95c9e128a81cee1c3455ae8f3', 'ac3040502cef63d5ecdf26134aebda44', '735ce39b111ca24ce46dae69973b8917', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` VALUES ('e028dc4e3f6bad00b311f7ca7f12591f', 'e22e4e1678db53aa885782fc9c5c554d', '735ce39b111ca24ce46dae69973b8917', NULL, NULL, NULL, NULL);
INSERT INTO `sys_user_role` VALUES ('f65d20eb14ac2932e20f426337e8d39a', '7635472ab0bee13cef276057b7600a26', '735ce39b111ca24ce46dae69973b8917', NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
