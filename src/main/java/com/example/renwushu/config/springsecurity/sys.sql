-- ----------------------------
-- Table structure for sys_user
-- ----------------------------

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '用户密码',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `email` varchar(64) DEFAULT NULL COMMENT '用户邮箱',
  `city` varchar(64) DEFAULT NULL COMMENT '城市',
  `created_time` datetime DEFAULT NULL COMMENT '用户创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '用户修改时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '用户上次登录时间',
  `statu` int(5) NOT NULL COMMENT '用户状态 1可用|0不可用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_USERNAME` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$R7zegeWzOXPw871CmNuJ6upC0v8D373GuLuTw8jn6NET4BkPRZfgK', 'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg', '123@qq.com', '广州', '2021-01-12 22:13:53', '2021-01-16 16:57:32', '2020-12-30 08:38:37', '1');

INSERT INTO `sys_user` VALUES ('2', 'test', '$2a$10$0ilP4ZD1kLugYwLCs4pmb.ZT9cFqzOZTNaMiHxrBnVIQUGUwEvBIO', 'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg', 'test@qq.com', null, '2021-01-30 08:20:22', '2021-01-30 08:55:57', null, '1');


-- ----------------------------
-- Table structure for sys_role
-- ----------------------------

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `name` varchar(64) NOT NULL COMMENT '角色名称',
  `code` varchar(64) NOT NULL COMMENT '角色',
  `created_time` datetime DEFAULT NULL COMMENT'角色创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '角色修改时间',
  `statu` int(5) NOT NULL COMMENT '角色状态 1可用|0不可用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING BTREE,
  UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级管理员', 'admin', '2021-01-16 13:29:03', '2021-01-17 15:50:45', '1');

INSERT INTO `sys_role` VALUES ('2', '普通用户', 'normal', '2021-01-04 10:09:14', '2021-01-30 08:19:52', '1');


-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;


-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '1', '2');
INSERT INTO `sys_user_role` VALUES ('3', '2', '2');
-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `parent_id` int(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(64) NOT NULL COMMENT '菜单名称',
  `path` varchar(255) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(255) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `component` varchar(255) DEFAULT NULL,
  `type` int(5) NOT NULL COMMENT '类型     0：目录   1：菜单   2：按钮',
  `icon` varchar(32) DEFAULT NULL COMMENT '菜单图标',
  `created_time` datetime NOT NULL COMMENT '菜单创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '菜单修改时间',
  `statu` int(5) NOT NULL COMMENT '状态1可用|0不可用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', '', 'sys:manage', '', '0', 'el-icon-s-operation', '2021-01-15 18:58:18', '2021-01-15 18:58:20', '1');

INSERT INTO `sys_menu` VALUES ('2', '1', '用户管理', '/sys/users', 'sys:user:list', 'sys/User', '1', 'el-icon-s-custom', '2021-01-15 19:03:45', '2021-01-15 19:03:48', '1');

INSERT INTO `sys_menu` VALUES ('3', '1', '角色管理', '/sys/roles', 'sys:role:list', 'sys/Role', '1', 'el-icon-rank', '2021-01-15 19:03:45', '2021-01-15 19:03:48', '1');

INSERT INTO `sys_menu` VALUES ('4', '1', '菜单管理', '/sys/menus', 'sys:menu:list', 'sys/Menu', '1', 'el-icon-menu', '2021-01-15 19:03:45', '2021-01-15 19:03:48', '1');

INSERT INTO `sys_menu` VALUES ('5', '0', '系统工具', '', 'sys:tools', null, '0', 'el-icon-s-tools', '2021-01-15 19:06:11', null, '1');

INSERT INTO `sys_menu` VALUES ('6', '5', '数字字典', '/sys/dicts', 'sys:dict:list', 'sys/Dict', '1', 'el-icon-s-order', '2021-01-15 19:07:18', '2021-01-18 16:32:13', '1');

INSERT INTO `sys_menu` VALUES ('7', '3', '添加角色', '', 'sys:role:save', '', '2', '', '2021-01-15 23:02:25', '2021-01-17 21:53:14', '0');

INSERT INTO `sys_menu` VALUES ('8', '2', '添加用户', null, 'sys:user:save', null, '2', null, '2021-01-17 21:48:32', null, '1');


-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) NOT NULL,
  `menu_id` int(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1', '1');
INSERT INTO `sys_role_menu` VALUES ('2', '1', '2');
INSERT INTO `sys_role_menu` VALUES ('3', '1', '3');
INSERT INTO `sys_role_menu` VALUES ('4', '1', '4');
INSERT INTO `sys_role_menu` VALUES ('5', '1', '5');
INSERT INTO `sys_role_menu` VALUES ('6', '1', '6');
INSERT INTO `sys_role_menu` VALUES ('7', '1', '7');
INSERT INTO `sys_role_menu` VALUES ('8', '1', '8');
INSERT INTO `sys_role_menu` VALUES ('9', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('10', '2', '2');
INSERT INTO `sys_role_menu` VALUES ('11', '2', '3');
INSERT INTO `sys_role_menu` VALUES ('12', '2', '4');
INSERT INTO `sys_role_menu` VALUES ('13', '2', '5');

