/*
 Navicat Premium Data Transfer

 Source Server         : mac
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : jfun_user

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 05/07/2019 13:31:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `i_frame` bit(1) DEFAULT NULL COMMENT '是否外链',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `component` varchar(255) DEFAULT NULL COMMENT '组件',
  `pid` bigint(20) NOT NULL COMMENT '上级菜单ID',
  `sort` bigint(20) NOT NULL COMMENT '排序',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `path` varchar(255) DEFAULT NULL COMMENT '链接地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, '2018-12-18 15:11:29', b'0', '系统管理', NULL, 0, 1, 'system', 'system');
INSERT INTO `sys_menu` VALUES (2, '2018-12-18 15:14:44', b'0', '用户管理', 'system/user/index', 1, 2, 'peoples', 'user');
INSERT INTO `sys_menu` VALUES (3, '2018-12-18 15:16:07', b'0', '角色管理', 'system/role/index', 1, 3, 'role', 'role');
INSERT INTO `sys_menu` VALUES (4, '2018-12-18 15:16:45', b'0', '权限管理', 'system/permission/index', 1, 4, 'permission', 'permission');
INSERT INTO `sys_menu` VALUES (5, '2018-12-18 15:17:28', b'0', '菜单管理', 'system/menu/index', 1, 5, 'menu', 'menu');
INSERT INTO `sys_menu` VALUES (17, '2018-12-28 15:09:49', b'1', '项目地址', '', 0, 3, 'github', 'https://github.com/xungegeblue/jfun-cloud-web');
INSERT INTO `sys_menu` VALUES (33, NULL, b'1', '监控中心', '', 0, 2, 'eye-open', 'monitor');
INSERT INTO `sys_menu` VALUES (34, NULL, b'1', 'Nacos监控', '', 33, 1, 'fwb', 'http://localhost:8848/nacos');
COMMIT;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `alias` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=latin1 COMMENT='请求方法';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES (1, '用户管理', 0, 'USER_ALL', '2019-03-28 11:15:49', '', '');
INSERT INTO `sys_permission` VALUES (2, '用户添加', 1, 'USER_ADD', '2019-03-28 11:15:51', 'POST', '/api-user/user/**');
INSERT INTO `sys_permission` VALUES (3, '用户删除', 1, 'USER_DEL', '2019-03-28 11:15:53', 'DELETE', '/api-user/user/**');
INSERT INTO `sys_permission` VALUES (4, '用户编辑', 1, 'USER_EDIT', '2019-03-28 11:15:54', 'PUT', '/api-user/user/**');
INSERT INTO `sys_permission` VALUES (5, '角色管理', 0, 'ROLE_ALL', '2019-03-28 11:15:56', NULL, NULL);
INSERT INTO `sys_permission` VALUES (6, '角色添加', 5, 'ROLE_ADD', '2019-03-29 10:00:51', 'POST', '/api-user/role/**');
INSERT INTO `sys_permission` VALUES (7, '角色编辑', 5, 'ROLE_EDIT', '2019-03-29 10:01:13', 'PUT', '/api-user/role/**');
INSERT INTO `sys_permission` VALUES (8, '角色删除', 5, 'ROLE_DEL', '2019-03-29 10:01:36', 'DELETE', '/api-user/role/**');
INSERT INTO `sys_permission` VALUES (9, '用户查询', 1, 'USER_VIEW', '2019-03-29 10:05:41', 'GET', '/api-user/user/**');
INSERT INTO `sys_permission` VALUES (10, '角色查看', 5, 'ROLE_VIEW', '2019-03-29 10:10:20', 'GET', '/api-user/role/**');
INSERT INTO `sys_permission` VALUES (11, '权限管理', 0, 'PERMISSION_ALL', '2019-03-29 13:03:06', NULL, NULL);
INSERT INTO `sys_permission` VALUES (12, '权限查看', 11, 'PERMISSION_VIEW', '2019-03-29 13:04:42', 'GET', '/api-user/permission/**');
INSERT INTO `sys_permission` VALUES (13, '权限添加', 11, 'PERMISSION_ADD', '2019-03-29 13:04:45', 'POST', '/api-user/permission/**');
INSERT INTO `sys_permission` VALUES (14, '权限编辑', 11, 'PERMISSION_EDIT', '2019-03-29 13:04:47', 'PUT', '/api-user/permission/**');
INSERT INTO `sys_permission` VALUES (15, '权限删除', 11, 'PERMISSION_DEL', '2019-03-29 13:04:48', 'DELETE', '/api-user/permission/**');
INSERT INTO `sys_permission` VALUES (20, '菜单查询', 19, 'MENU_VIEW', '2019-03-29 15:35:44', 'GET', '/api-user/menu/**');
INSERT INTO `sys_permission` VALUES (19, '菜单管理', 0, 'MENU_ALL', '2019-03-29 15:35:20', NULL, NULL);
INSERT INTO `sys_permission` VALUES (21, '菜单编辑', 19, 'MENU_EDIT', '2019-03-29 15:36:06', 'PUT', '/api-user/menu/**');
INSERT INTO `sys_permission` VALUES (22, '菜单删除', 19, 'MENU_DEL', '2019-03-29 15:36:36', 'DELETE', '/api-user/menu/**');
INSERT INTO `sys_permission` VALUES (23, '菜单添加', 19, 'MENU_ADD', '2019-03-29 15:36:56', 'POST', '/api-user/menu/**');
INSERT INTO `sys_permission` VALUES (25, '定时任务', 0, 'JOB_ALL', '2019-04-01 16:34:50', NULL, NULL);
INSERT INTO `sys_permission` VALUES (26, '定时任务添加', 25, 'JOB_ADD', '2019-04-01 16:35:50', NULL, NULL);
INSERT INTO `sys_permission` VALUES (27, '定时任务修改', 25, 'JOB_EDIT', '2019-04-01 16:36:07', NULL, NULL);
INSERT INTO `sys_permission` VALUES (28, '定时任务查询', 25, 'JOB_VIEW', '2019-04-01 16:36:25', NULL, NULL);
INSERT INTO `sys_permission` VALUES (29, '定时任务删除', 25, 'JOB_DEL', '2019-04-01 16:36:39', NULL, NULL);
INSERT INTO `sys_permission` VALUES (30, '代码生成', 0, 'GENERATOR_CODE', '2019-04-08 11:48:35', NULL, NULL);
INSERT INTO `sys_permission` VALUES (31, '系统缓存', 0, 'REDIS_ALL', '2019-04-10 15:01:58', NULL, NULL);
INSERT INTO `sys_permission` VALUES (32, '添加缓存', 31, 'REDIS_ADD', '2019-04-10 15:02:24', NULL, NULL);
INSERT INTO `sys_permission` VALUES (33, '查询缓存', 31, 'REDIS_VIEW', '2019-04-10 15:02:43', NULL, NULL);
INSERT INTO `sys_permission` VALUES (34, '修改缓存', 31, 'REDIS_EDIT', '2019-04-10 15:02:58', NULL, NULL);
INSERT INTO `sys_permission` VALUES (35, '删除缓存', 31, 'REDIS_DEL', '2019-04-10 15:03:13', NULL, NULL);
INSERT INTO `sys_permission` VALUES (36, 'test', 0, 'test', '2019-04-10 15:02:58', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '管理员', 'admin', '2019-03-28 15:27:48');
INSERT INTO `sys_role` VALUES (2, 'VIP会员23', 'vip12', '2019-03-28 15:27:50');
INSERT INTO `sys_role` VALUES (12, 'AAA', 'AAA', '2019-03-29 10:24:36');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`menu_id`,`role_id`) USING BTREE,
  KEY `FKcngg2qadojhi3a651a5adkvbq` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (1, 1);
INSERT INTO `sys_role_menu` VALUES (1, 2);
INSERT INTO `sys_role_menu` VALUES (1, 3);
INSERT INTO `sys_role_menu` VALUES (1, 4);
INSERT INTO `sys_role_menu` VALUES (1, 5);
INSERT INTO `sys_role_menu` VALUES (1, 17);
INSERT INTO `sys_role_menu` VALUES (1, 33);
INSERT INTO `sys_role_menu` VALUES (1, 34);
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 5);
INSERT INTO `sys_role_menu` VALUES (2, 17);
INSERT INTO `sys_role_menu` VALUES (11, 1);
INSERT INTO `sys_role_menu` VALUES (11, 2);
INSERT INTO `sys_role_menu` VALUES (11, 3);
INSERT INTO `sys_role_menu` VALUES (11, 4);
INSERT INTO `sys_role_menu` VALUES (11, 5);
INSERT INTO `sys_role_menu` VALUES (11, 6);
INSERT INTO `sys_role_menu` VALUES (11, 7);
INSERT INTO `sys_role_menu` VALUES (11, 8);
INSERT INTO `sys_role_menu` VALUES (11, 9);
INSERT INTO `sys_role_menu` VALUES (11, 10);
INSERT INTO `sys_role_menu` VALUES (11, 11);
INSERT INTO `sys_role_menu` VALUES (11, 12);
INSERT INTO `sys_role_menu` VALUES (11, 13);
INSERT INTO `sys_role_menu` VALUES (11, 14);
INSERT INTO `sys_role_menu` VALUES (11, 15);
INSERT INTO `sys_role_menu` VALUES (11, 16);
INSERT INTO `sys_role_menu` VALUES (11, 18);
INSERT INTO `sys_role_menu` VALUES (11, 19);
INSERT INTO `sys_role_menu` VALUES (11, 28);
INSERT INTO `sys_role_menu` VALUES (11, 30);
INSERT INTO `sys_role_menu` VALUES (11, 32);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `permission_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` VALUES (32, 1);
INSERT INTO `sys_role_permission` VALUES (28, 1);
INSERT INTO `sys_role_permission` VALUES (20, 1);
INSERT INTO `sys_role_permission` VALUES (35, 1);
INSERT INTO `sys_role_permission` VALUES (9, 1);
INSERT INTO `sys_role_permission` VALUES (25, 1);
INSERT INTO `sys_role_permission` VALUES (2, 17);
INSERT INTO `sys_role_permission` VALUES (2, 3);
INSERT INTO `sys_role_permission` VALUES (2, 4);
INSERT INTO `sys_role_permission` VALUES (2, 1);
INSERT INTO `sys_role_permission` VALUES (19, 1);
INSERT INTO `sys_role_permission` VALUES (10, 1);
INSERT INTO `sys_role_permission` VALUES (2, 1);
INSERT INTO `sys_role_permission` VALUES (34, 1);
INSERT INTO `sys_role_permission` VALUES (26, 1);
INSERT INTO `sys_role_permission` VALUES (33, 1);
INSERT INTO `sys_role_permission` VALUES (7, 1);
INSERT INTO `sys_role_permission` VALUES (4, 1);
INSERT INTO `sys_role_permission` VALUES (1, 1);
INSERT INTO `sys_role_permission` VALUES (13, 1);
INSERT INTO `sys_role_permission` VALUES (27, 1);
INSERT INTO `sys_role_permission` VALUES (12, 1);
INSERT INTO `sys_role_permission` VALUES (31, 1);
INSERT INTO `sys_role_permission` VALUES (5, 1);
INSERT INTO `sys_role_permission` VALUES (15, 1);
INSERT INTO `sys_role_permission` VALUES (21, 1);
INSERT INTO `sys_role_permission` VALUES (14, 1);
INSERT INTO `sys_role_permission` VALUES (6, 1);
INSERT INTO `sys_role_permission` VALUES (30, 1);
INSERT INTO `sys_role_permission` VALUES (22, 1);
INSERT INTO `sys_role_permission` VALUES (3, 1);
INSERT INTO `sys_role_permission` VALUES (8, 1);
INSERT INTO `sys_role_permission` VALUES (29, 1);
INSERT INTO `sys_role_permission` VALUES (23, 1);
INSERT INTO `sys_role_permission` VALUES (11, 1);
INSERT INTO `sys_role_permission` VALUES (2, 5);
INSERT INTO `sys_role_permission` VALUES (2, 1);
INSERT INTO `sys_role_permission` VALUES (2, 4);
INSERT INTO `sys_role_permission` VALUES (2, 3);
INSERT INTO `sys_role_permission` VALUES (2, 17);
INSERT INTO `sys_role_permission` VALUES (2, 5);
INSERT INTO `sys_role_permission` VALUES (2, 1);
INSERT INTO `sys_role_permission` VALUES (2, 4);
INSERT INTO `sys_role_permission` VALUES (2, 3);
INSERT INTO `sys_role_permission` VALUES (2, 17);
INSERT INTO `sys_role_permission` VALUES (2, 5);
INSERT INTO `sys_role_permission` VALUES (2, 1);
INSERT INTO `sys_role_permission` VALUES (2, 4);
INSERT INTO `sys_role_permission` VALUES (2, 3);
INSERT INTO `sys_role_permission` VALUES (2, 17);
INSERT INTO `sys_role_permission` VALUES (2, 5);
INSERT INTO `sys_role_permission` VALUES (2, 17);
INSERT INTO `sys_role_permission` VALUES (2, 17);
INSERT INTO `sys_role_permission` VALUES (2, 1);
INSERT INTO `sys_role_permission` VALUES (2, 4);
INSERT INTO `sys_role_permission` VALUES (2, 3);
INSERT INTO `sys_role_permission` VALUES (2, 17);
INSERT INTO `sys_role_permission` VALUES (2, 5);
INSERT INTO `sys_role_permission` VALUES (2, 17);
INSERT INTO `sys_role_permission` VALUES (2, 4);
INSERT INTO `sys_role_permission` VALUES (2, 1);
INSERT INTO `sys_role_permission` VALUES (2, 7);
INSERT INTO `sys_role_permission` VALUES (2, 3);
INSERT INTO `sys_role_permission` VALUES (2, 9);
INSERT INTO `sys_role_permission` VALUES (2, 6);
INSERT INTO `sys_role_permission` VALUES (2, 8);
INSERT INTO `sys_role_permission` VALUES (2, 10);
INSERT INTO `sys_role_permission` VALUES (2, 7);
INSERT INTO `sys_role_permission` VALUES (2, 5);
INSERT INTO `sys_role_permission` VALUES (2, 6);
INSERT INTO `sys_role_permission` VALUES (2, 8);
INSERT INTO `sys_role_permission` VALUES (2, 10);
INSERT INTO `sys_role_permission` VALUES (2, 7);
INSERT INTO `sys_role_permission` VALUES (2, 5);
INSERT INTO `sys_role_permission` VALUES (2, 15);
INSERT INTO `sys_role_permission` VALUES (2, 6);
INSERT INTO `sys_role_permission` VALUES (2, 8);
INSERT INTO `sys_role_permission` VALUES (2, 14);
INSERT INTO `sys_role_permission` VALUES (2, 11);
INSERT INTO `sys_role_permission` VALUES (2, 10);
INSERT INTO `sys_role_permission` VALUES (2, 12);
INSERT INTO `sys_role_permission` VALUES (2, 7);
INSERT INTO `sys_role_permission` VALUES (2, 5);
INSERT INTO `sys_role_permission` VALUES (2, 13);
INSERT INTO `sys_role_permission` VALUES (2, 6);
INSERT INTO `sys_role_permission` VALUES (2, 8);
INSERT INTO `sys_role_permission` VALUES (2, 10);
INSERT INTO `sys_role_permission` VALUES (2, 7);
INSERT INTO `sys_role_permission` VALUES (2, 5);
INSERT INTO `sys_role_permission` VALUES (2, 6);
INSERT INTO `sys_role_permission` VALUES (2, 8);
INSERT INTO `sys_role_permission` VALUES (2, 10);
INSERT INTO `sys_role_permission` VALUES (2, 7);
INSERT INTO `sys_role_permission` VALUES (2, 5);
INSERT INTO `sys_role_permission` VALUES (2, 6);
INSERT INTO `sys_role_permission` VALUES (2, 8);
INSERT INTO `sys_role_permission` VALUES (2, 10);
INSERT INTO `sys_role_permission` VALUES (2, 7);
INSERT INTO `sys_role_permission` VALUES (2, 5);
INSERT INTO `sys_role_permission` VALUES (2, 15);
INSERT INTO `sys_role_permission` VALUES (2, 6);
INSERT INTO `sys_role_permission` VALUES (2, 8);
INSERT INTO `sys_role_permission` VALUES (2, 14);
INSERT INTO `sys_role_permission` VALUES (2, 11);
INSERT INTO `sys_role_permission` VALUES (2, 10);
INSERT INTO `sys_role_permission` VALUES (2, 12);
INSERT INTO `sys_role_permission` VALUES (2, 7);
INSERT INTO `sys_role_permission` VALUES (2, 5);
INSERT INTO `sys_role_permission` VALUES (2, 13);
INSERT INTO `sys_role_permission` VALUES (2, 6);
INSERT INTO `sys_role_permission` VALUES (2, 8);
INSERT INTO `sys_role_permission` VALUES (2, 10);
INSERT INTO `sys_role_permission` VALUES (11, 2);
INSERT INTO `sys_role_permission` VALUES (2, 5);
INSERT INTO `sys_role_permission` VALUES (14, 2);
INSERT INTO `sys_role_permission` VALUES (8, 2);
INSERT INTO `sys_role_permission` VALUES (6, 2);
INSERT INTO `sys_role_permission` VALUES (15, 2);
INSERT INTO `sys_role_permission` VALUES (7, 2);
INSERT INTO `sys_role_permission` VALUES (10, 2);
INSERT INTO `sys_role_permission` VALUES (12, 2);
INSERT INTO `sys_role_permission` VALUES (2, 2);
INSERT INTO `sys_role_permission` VALUES (5, 2);
INSERT INTO `sys_role_permission` VALUES (13, 2);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `state` tinyint(4) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (43, 'admin', '$2a$10$wSyY8JDpYsm5Mk9riU6ccOGwt7SBSFQR8Y4D1v/B0gshlMHMBOeli', '2bbf27adcf3c259df147643d6fe70a23', 1, '787824374@qq.com', 'https://s2.ax1x.com/2019/03/23/AGO1q1.jpg', '2019-03-27 10:15:54');
INSERT INTO `sys_user` VALUES (39, 'qqq22', 'ba256b383020d3ccaa3c92e76499c45b', '79d485fa7dfab115fcf9f9eb56a9fc07', 0, 'nihao@qq.com', 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1956623052,720822838&fm=26&gp=0.jpg', '2019-03-27 10:15:54');
INSERT INTO `sys_user` VALUES (1, 'miv', '{bcrypt}$2a$10$bLz5u/6UEHmqJQPlEGzTHejrYF2636RRszEqEv.GXuiH0Qa71SNMi', '', 1, 'miv@qq.com', 'https://i.loli.net/2018/12/06/5c08894d8de21.jpg', '2019-03-27 11:44:03');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `uid` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`) USING BTREE,
  KEY `FKgkmyslkrfeyn9ukmolvek8b8f` (`uid`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (39, 12);
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (39, 2);
INSERT INTO `sys_user_role` VALUES (42, 1);
INSERT INTO `sys_user_role` VALUES (43, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
