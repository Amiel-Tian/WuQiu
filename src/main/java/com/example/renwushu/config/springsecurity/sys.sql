/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : wq_renwushu

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 03/11/2022 12:47:47
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`
(
    `dict_data_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '字典数据主键',
    `dict_id`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '字典类型表',
    `dict_key`     varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标签',
    `dict_value`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '键值',
    `tree_sort`    decimal(10, 0) NULL DEFAULT NULL COMMENT '排序',
    `status`       char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '状态：0正常 1删除 2停用',
    `create_by`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建者',
    `create_date`  timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '创建时间',
    `update_by`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '最后一次更新者',
    `update_date`  timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '最后一次更新时间',
    `remarks`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
    `parent_id`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '上级ID',
    `parent_ids`   varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '上级ID合集',
    PRIMARY KEY (`dict_data_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`
(
    `dict_id`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '字典类型表',
    `dict_name`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '字典名称',
    `dict_type`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '字典类型',
    `is_sys`      char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '是否系统字典：0系统、1业务',
    `status`      char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '状态：0正常 1删除 2停用',
    `create_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '创建者',
    `create_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '创建时间',
    `update_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '最后一次更新者',
    `update_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP (0) COMMENT '最后一次更新时间',
    `remarks`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
    `parent_id`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '父级编号',
    `parent_ids`  varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '所有父级编号',
    `tree_sort`   decimal(10, 0) NULL DEFAULT NULL COMMENT '本级排序号（升序）',
    `cascaded`    char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '级联，0否，1是',
    PRIMARY KEY (`dict_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`           bigint(0) NOT NULL AUTO_INCREMENT,
    `parent_id`    int(0) NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
    `name`         varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
    `path`         varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
    `perms`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
    `component`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `type`         int(0) NOT NULL COMMENT '类型     0：目录   1：菜单   2：按钮',
    `icon`         varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
    `created_time` datetime(0) NOT NULL COMMENT '菜单创建时间',
    `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '菜单修改时间',
    `statu`        int(0) NOT NULL COMMENT '状态1可用|0不可用',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu`
VALUES (1, 0, '系统管理', '', 'sys:manage', '', 0, 'Setting', '2021-01-15 18:58:18', '2021-01-15 18:58:20', 1);
INSERT INTO `sys_menu`
VALUES (2, 1, '用户管理', '/sys/user', 'sys:user:list', 'sys/User', 1, 'User', '2021-01-15 19:03:45',
        '2021-01-15 19:03:48', 1);
INSERT INTO `sys_menu`
VALUES (3, 1, '角色管理', '/sys/roles', 'sys:role:list', 'sys/Role', 1, 'View', '2021-01-15 19:03:45',
        '2021-01-15 19:03:48', 1);
INSERT INTO `sys_menu`
VALUES (4, 1, '菜单管理', '/sys/menus', 'sys:menu:list', 'sys/Menu', 1, 'Grid', '2021-01-15 19:03:45',
        '2021-01-15 19:03:48', 1);
INSERT INTO `sys_menu`
VALUES (5, 0, '系统工具', '', 'sys:tools', NULL, 0, 'Operation', '2021-01-15 19:06:11', NULL, 1);
INSERT INTO `sys_menu`
VALUES (6, 5, '数字字典', '/sys/dicts', 'sys:dict:list', 'sys/Dict', 1, 'Collection', '2021-01-15 19:07:18',
        '2021-01-18 16:32:13', 1);
INSERT INTO `sys_menu`
VALUES (7, 3, '添加角色', '', 'sys:role:save', '', 2, '', '2021-01-15 23:02:25', '2021-01-17 21:53:14', 0);
INSERT INTO `sys_menu`
VALUES (8, 2, '添加用户', NULL, 'sys:user:save', NULL, 2, NULL, '2021-01-17 21:48:32', NULL, 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`           bigint(0) NOT NULL AUTO_INCREMENT COMMENT '角色id',
    `name`         varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
    `code`         varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色',
    `created_time` datetime(0) NULL DEFAULT NULL COMMENT '角色创建时间',
    `updated_time` datetime(0) NULL DEFAULT NULL COMMENT '角色修改时间',
    `statu`        int(0) NOT NULL COMMENT '角色状态 1可用|0不可用',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `name`(`name`) USING BTREE,
    UNIQUE INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`
VALUES (1, '超级管理员', 'admin', '2021-01-16 13:29:03', '2021-01-17 15:50:45', 1);
INSERT INTO `sys_role`
VALUES (2, '普通用户', 'normal', '2021-01-04 10:09:14', '2021-01-30 08:19:52', 1);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `id`      bigint(0) NOT NULL AUTO_INCREMENT,
    `role_id` bigint(0) NOT NULL,
    `menu_id` bigint(0) NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu`
VALUES (1, 1, 1);
INSERT INTO `sys_role_menu`
VALUES (2, 1, 2);
INSERT INTO `sys_role_menu`
VALUES (3, 1, 3);
INSERT INTO `sys_role_menu`
VALUES (4, 1, 4);
INSERT INTO `sys_role_menu`
VALUES (5, 1, 5);
INSERT INTO `sys_role_menu`
VALUES (6, 1, 6);
INSERT INTO `sys_role_menu`
VALUES (7, 1, 7);
INSERT INTO `sys_role_menu`
VALUES (8, 1, 8);
INSERT INTO `sys_role_menu`
VALUES (9, 2, 1);
INSERT INTO `sys_role_menu`
VALUES (10, 2, 2);
INSERT INTO `sys_role_menu`
VALUES (11, 2, 3);
INSERT INTO `sys_role_menu`
VALUES (12, 2, 4);
INSERT INTO `sys_role_menu`
VALUES (13, 2, 5);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`              bigint(0) NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username`        varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
    `loginname`       varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录名',
    `password`        varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
    `avatar`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
    `email`           varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
    `city`            varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
    `created_time`    datetime(0) NULL DEFAULT NULL COMMENT '用户创建时间',
    `updated_time`    datetime(0) NULL DEFAULT NULL COMMENT '用户修改时间',
    `last_login_time` datetime(0) NULL DEFAULT NULL COMMENT '用户上次登录时间',
    `statu`           int(0) NOT NULL COMMENT '用户状态 1可用|0不可用',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `UK_USERNAME`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES (1, '管理员', 'admin', '$2a$10$R7zegeWzOXPw871CmNuJ6upC0v8D373GuLuTw8jn6NET4BkPRZfgK',
        'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg',
        '123@qq.com', '广州', '2021-01-12 22:13:53', '2021-01-16 16:57:32', '2020-12-30 08:38:37', 1);
INSERT INTO `sys_user`
VALUES (2, '测试用户', 'test', '$2a$10$R7zegeWzOXPw871CmNuJ6upC0v8D373GuLuTw8jn6NET4BkPRZfgK',
        'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg',
        'test@qq.com', NULL, '2021-01-30 08:20:22', '2021-01-30 08:55:57', NULL, 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`      bigint(0) NOT NULL AUTO_INCREMENT,
    `user_id` bigint(0) NOT NULL COMMENT '用户id',
    `role_id` bigint(0) NOT NULL COMMENT '角色id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role`
VALUES (1, 1, 1);
INSERT INTO `sys_user_role`
VALUES (2, 1, 2);
INSERT INTO `sys_user_role`
VALUES (3, 2, 2);

SET
FOREIGN_KEY_CHECKS = 1;
