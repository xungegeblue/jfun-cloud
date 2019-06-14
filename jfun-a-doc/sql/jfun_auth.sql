/*
 Navicat Premium Data Transfer

 Source Server         : mac
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : jfun_auth

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 04/05/2019 13:45:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for clientdetails
-- ----------------------------
DROP TABLE IF EXISTS `clientdetails`;
CREATE TABLE `clientdetails` (
  `appId` varchar(128) NOT NULL,
  `resourceIds` varchar(256) DEFAULT NULL,
  `appSecret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `grantTypes` varchar(256) DEFAULT NULL,
  `redirectUrl` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additionalInformation` varchar(4096) DEFAULT NULL,
  `autoApproveScopes` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for gateway_routes
-- ----------------------------
DROP TABLE IF EXISTS `gateway_routes`;
CREATE TABLE `gateway_routes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `route_id` varchar(100) NOT NULL COMMENT '路由id',
  `uri` varchar(100) NOT NULL COMMENT 'uri路径',
  `predicates` text NOT NULL COMMENT '判定器',
  `filters` text COMMENT '过滤器',
  `orders` int(11) DEFAULT NULL COMMENT '排序',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `status` varchar(1) DEFAULT 'Y' COMMENT '状态：Y-有效，N-无效',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_gateway_routes_uri` (`uri`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8 COMMENT='网关路由表';

-- ----------------------------
-- Records of gateway_routes
-- ----------------------------
BEGIN;
INSERT INTO `gateway_routes` VALUES (101, 'authorization-server', 'lb://authorization-server:8000', '[{\"label\":\"Path\",\"args\":{\"pattern\":\"/authorization-server/**\"}}]', '[{\"label\":\"StripPrefix\",\"args\":{\"parts\":\"1\"}}]', 100, '授权认证服务网关注册', 'Y', '2019-05-03 11:28:15', '2019-05-03 11:28:15', 'system', 'system');
INSERT INTO `gateway_routes` VALUES (102, 'authentication-server', 'lb://authentication-server:8001', '[{\"label\":\"Path\",\"args\":{\"pattern\":\"/authentication-server/**\"}}]', '[{\"label\":\"StripPrefix\",\"args\":{\"parts\":\"1\"}}]', 100, '签权服务网关注册', 'Y', '2019-05-03 11:28:15', '2019-05-03 11:28:15', 'system', 'system');
COMMIT;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp NULL DEFAULT NULL,
  `lastModifiedAt` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('client_1', '', '123456', 'select', 'password', '', 'test', NULL, NULL, '{}', '');
COMMIT;

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, '2018-12-18 15:11:29', b'0', '系统管理', NULL, 0, 1, 'system', 'system');
INSERT INTO `sys_menu` VALUES (2, '2018-12-18 15:14:44', b'0', '用户管理', 'system/user/index', 1, 2, 'peoples', 'user');
INSERT INTO `sys_menu` VALUES (3, '2018-12-18 15:16:07', b'0', '角色管理', 'system/role/index', 1, 3, 'role', 'role');
INSERT INTO `sys_menu` VALUES (4, '2018-12-18 15:16:45', b'0', '权限管理', 'system/permission/index', 1, 4, 'permission', 'permission');
INSERT INTO `sys_menu` VALUES (5, '2018-12-18 15:17:28', b'0', '菜单管理', 'system/menu/index', 1, 5, 'menu', 'menu');
INSERT INTO `sys_menu` VALUES (6, '2018-12-18 15:17:48', b'0', '系统监控', NULL, 0, 10, 'monitor', 'monitor');
INSERT INTO `sys_menu` VALUES (7, '2018-12-18 15:18:26', b'0', '操作日志', 'monitor/log/index', 6, 11, 'log', 'logs');
INSERT INTO `sys_menu` VALUES (8, '2018-12-18 15:19:01', b'0', '系统缓存', 'monitor/redis/index', 6, 13, 'redis', 'redis');
INSERT INTO `sys_menu` VALUES (9, '2018-12-18 15:19:34', b'1', 'SQL监控', NULL, 6, 14, 'sqlMonitor', 'http://api.auauz.net/druid');
INSERT INTO `sys_menu` VALUES (10, '2018-12-19 13:38:16', b'0', '组件管理', NULL, 0, 50, 'zujian', 'components');
INSERT INTO `sys_menu` VALUES (11, '2018-12-19 13:38:49', b'0', '图标库', 'components/IconSelect', 10, 51, 'icon', 'icon');
INSERT INTO `sys_menu` VALUES (12, '2018-12-24 20:37:35', b'0', '实时控制台', 'monitor/log/msg', 6, 15, 'codeConsole', 'msg');
INSERT INTO `sys_menu` VALUES (13, '2018-12-27 10:11:26', b'0', '三方工具', '', 0, 30, 'tools', 'tools');
INSERT INTO `sys_menu` VALUES (14, '2018-12-27 10:13:09', b'0', '邮件工具', 'tools/email/index', 13, 31, 'email', 'email');
INSERT INTO `sys_menu` VALUES (15, '2018-12-27 11:58:25', b'0', '富文本', 'components/Editor', 10, 52, 'fwb', 'tinymce');
INSERT INTO `sys_menu` VALUES (16, '2018-12-28 09:36:53', b'0', 'SM.MS图床', 'tools/picture/index', 13, 32, 'image', 'pictures');
INSERT INTO `sys_menu` VALUES (17, '2018-12-28 15:09:49', b'1', '项目地址', '', 0, 0, 'github', 'https://github.com/elunez/eladmin');
INSERT INTO `sys_menu` VALUES (18, '2018-12-31 11:12:15', b'0', '七牛云存储', 'tools/qiniu/index', 13, 33, 'qiniu', 'qiniu');
INSERT INTO `sys_menu` VALUES (19, '2018-12-31 14:52:38', b'0', '支付宝工具', 'tools/aliPay/index', 13, 34, 'alipay', 'aliPay');
INSERT INTO `sys_menu` VALUES (21, '2019-01-04 16:22:03', b'0', '多级菜单', '', 0, 900, 'menu', 'nested');
INSERT INTO `sys_menu` VALUES (22, '2019-01-04 16:23:29', b'0', '二级菜单1', 'nested/menu1/index', 21, 999, 'menu', 'menu1');
INSERT INTO `sys_menu` VALUES (23, '2019-01-04 16:23:57', b'0', '测试学生', 'system/testStudent/index', 21, 999, 'menu', 'menu2');
INSERT INTO `sys_menu` VALUES (24, '2019-01-04 16:24:48', b'0', '三级菜单1', 'nested/menu1/menu1-1', 22, 999, 'menu', 'menu1-1');
INSERT INTO `sys_menu` VALUES (27, '2019-01-07 17:27:32', b'0', '三级菜单2', 'nested/menu1/menu1-2', 22, 999, 'menu', 'menu1-2');
INSERT INTO `sys_menu` VALUES (28, '2019-01-07 20:34:40', b'0', '定时任务', 'system/timing/index', 1, 6, 'timing', 'timing');
INSERT INTO `sys_menu` VALUES (30, '2019-01-11 15:45:55', b'0', '代码生成', 'generator/index', 1, 8, 'dev', 'generator');
INSERT INTO `sys_menu` VALUES (32, '2019-01-13 13:49:03', b'0', '异常日志', 'monitor/log/errorLog', 6, 12, 'error', 'errorLog');
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
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES (1, '用户管理', 0, 'USER_ALL', '2019-03-28 11:15:49');
INSERT INTO `sys_permission` VALUES (2, '用户添加', 1, 'USER_ADD', '2019-03-28 11:15:51');
INSERT INTO `sys_permission` VALUES (3, '用户删除', 1, 'USER_DEL', '2019-03-28 11:15:53');
INSERT INTO `sys_permission` VALUES (4, '用户编辑', 1, 'USER_EDIT', '2019-03-28 11:15:54');
INSERT INTO `sys_permission` VALUES (5, '角色管理', 0, 'ROLE_ALL', '2019-03-28 11:15:56');
INSERT INTO `sys_permission` VALUES (6, '角色添加', 5, 'ROLE_ADD', '2019-03-29 10:00:51');
INSERT INTO `sys_permission` VALUES (7, '角色编辑', 5, 'ROLE_EDIT', '2019-03-29 10:01:13');
INSERT INTO `sys_permission` VALUES (8, '角色删除', 5, 'ROLE_DEL', '2019-03-29 10:01:36');
INSERT INTO `sys_permission` VALUES (9, '用户查询', 1, 'USER_VIEW', '2019-03-29 10:05:41');
INSERT INTO `sys_permission` VALUES (10, '角色查看', 5, 'ROLE_VIEW', '2019-03-29 10:10:20');
INSERT INTO `sys_permission` VALUES (11, '权限管理', 0, 'PERMISSION_ALL', '2019-03-29 13:03:06');
INSERT INTO `sys_permission` VALUES (12, '权限查看', 11, 'PERMISSION_VIEW', '2019-03-29 13:04:42');
INSERT INTO `sys_permission` VALUES (13, '权限添加', 11, 'PERMISSION_ADD', '2019-03-29 13:04:45');
INSERT INTO `sys_permission` VALUES (14, '权限编辑', 11, 'PERMISSION_EDIT', '2019-03-29 13:04:47');
INSERT INTO `sys_permission` VALUES (15, '权限删除', 11, 'PERMISSION_DEL', '2019-03-29 13:04:48');
INSERT INTO `sys_permission` VALUES (20, '菜单查询', 19, 'MENU_VIEW', '2019-03-29 15:35:44');
INSERT INTO `sys_permission` VALUES (19, '菜单管理', 0, 'MENU_ALL', '2019-03-29 15:35:20');
INSERT INTO `sys_permission` VALUES (21, '菜单编辑', 19, 'MENU_EDIT', '2019-03-29 15:36:06');
INSERT INTO `sys_permission` VALUES (22, '菜单删除', 19, 'MENU_DEL', '2019-03-29 15:36:36');
INSERT INTO `sys_permission` VALUES (23, '菜单添加', 19, 'MENU_ADD', '2019-03-29 15:36:56');
INSERT INTO `sys_permission` VALUES (25, '定时任务', 0, 'JOB_ALL', '2019-04-01 16:34:50');
INSERT INTO `sys_permission` VALUES (26, '定时任务添加', 25, 'JOB_ADD', '2019-04-01 16:35:50');
INSERT INTO `sys_permission` VALUES (27, '定时任务修改', 25, 'JOB_EDIT', '2019-04-01 16:36:07');
INSERT INTO `sys_permission` VALUES (28, '定时任务查询', 25, 'JOB_VIEW', '2019-04-01 16:36:25');
INSERT INTO `sys_permission` VALUES (29, '定时任务删除', 25, 'JOB_DEL', '2019-04-01 16:36:39');
INSERT INTO `sys_permission` VALUES (30, '代码生成', 0, 'GENERATOR_CODE', '2019-04-08 11:48:35');
INSERT INTO `sys_permission` VALUES (31, '系统缓存', 0, 'REDIS_ALL', '2019-04-10 15:01:58');
INSERT INTO `sys_permission` VALUES (32, '添加缓存', 31, 'REDIS_ADD', '2019-04-10 15:02:24');
INSERT INTO `sys_permission` VALUES (33, '查询缓存', 31, 'REDIS_VIEW', '2019-04-10 15:02:43');
INSERT INTO `sys_permission` VALUES (34, '修改缓存', 31, 'REDIS_EDIT', '2019-04-10 15:02:58');
INSERT INTO `sys_permission` VALUES (35, '删除缓存', 31, 'REDIS_DEL', '2019-04-10 15:03:13');
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
  PRIMARY KEY (`id`)
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
INSERT INTO `sys_role_permission` VALUES (4, 2);
INSERT INTO `sys_role_permission` VALUES (1, 2);
INSERT INTO `sys_role_permission` VALUES (3, 2);
INSERT INTO `sys_role_permission` VALUES (2, 2);
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
COMMIT;

-- ----------------------------
-- Table structure for sys_roles_menus
-- ----------------------------
DROP TABLE IF EXISTS `sys_roles_menus`;
CREATE TABLE `sys_roles_menus` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`menu_id`,`role_id`),
  KEY `FKcngg2qadojhi3a651a5adkvbq` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_roles_menus
-- ----------------------------
BEGIN;
INSERT INTO `sys_roles_menus` VALUES (1, 1);
INSERT INTO `sys_roles_menus` VALUES (1, 2);
INSERT INTO `sys_roles_menus` VALUES (1, 3);
INSERT INTO `sys_roles_menus` VALUES (1, 4);
INSERT INTO `sys_roles_menus` VALUES (1, 5);
INSERT INTO `sys_roles_menus` VALUES (1, 6);
INSERT INTO `sys_roles_menus` VALUES (1, 7);
INSERT INTO `sys_roles_menus` VALUES (1, 8);
INSERT INTO `sys_roles_menus` VALUES (1, 9);
INSERT INTO `sys_roles_menus` VALUES (1, 10);
INSERT INTO `sys_roles_menus` VALUES (1, 11);
INSERT INTO `sys_roles_menus` VALUES (1, 12);
INSERT INTO `sys_roles_menus` VALUES (1, 13);
INSERT INTO `sys_roles_menus` VALUES (1, 14);
INSERT INTO `sys_roles_menus` VALUES (1, 15);
INSERT INTO `sys_roles_menus` VALUES (1, 16);
INSERT INTO `sys_roles_menus` VALUES (1, 17);
INSERT INTO `sys_roles_menus` VALUES (1, 18);
INSERT INTO `sys_roles_menus` VALUES (1, 19);
INSERT INTO `sys_roles_menus` VALUES (1, 21);
INSERT INTO `sys_roles_menus` VALUES (1, 22);
INSERT INTO `sys_roles_menus` VALUES (1, 23);
INSERT INTO `sys_roles_menus` VALUES (1, 24);
INSERT INTO `sys_roles_menus` VALUES (1, 27);
INSERT INTO `sys_roles_menus` VALUES (1, 28);
INSERT INTO `sys_roles_menus` VALUES (1, 30);
INSERT INTO `sys_roles_menus` VALUES (1, 32);
INSERT INTO `sys_roles_menus` VALUES (2, 1);
INSERT INTO `sys_roles_menus` VALUES (2, 2);
INSERT INTO `sys_roles_menus` VALUES (2, 3);
INSERT INTO `sys_roles_menus` VALUES (2, 4);
INSERT INTO `sys_roles_menus` VALUES (2, 5);
INSERT INTO `sys_roles_menus` VALUES (2, 6);
INSERT INTO `sys_roles_menus` VALUES (2, 7);
INSERT INTO `sys_roles_menus` VALUES (2, 8);
INSERT INTO `sys_roles_menus` VALUES (2, 9);
INSERT INTO `sys_roles_menus` VALUES (2, 12);
INSERT INTO `sys_roles_menus` VALUES (2, 28);
INSERT INTO `sys_roles_menus` VALUES (2, 30);
INSERT INTO `sys_roles_menus` VALUES (2, 32);
INSERT INTO `sys_roles_menus` VALUES (11, 1);
INSERT INTO `sys_roles_menus` VALUES (11, 2);
INSERT INTO `sys_roles_menus` VALUES (11, 3);
INSERT INTO `sys_roles_menus` VALUES (11, 4);
INSERT INTO `sys_roles_menus` VALUES (11, 5);
INSERT INTO `sys_roles_menus` VALUES (11, 6);
INSERT INTO `sys_roles_menus` VALUES (11, 7);
INSERT INTO `sys_roles_menus` VALUES (11, 8);
INSERT INTO `sys_roles_menus` VALUES (11, 9);
INSERT INTO `sys_roles_menus` VALUES (11, 10);
INSERT INTO `sys_roles_menus` VALUES (11, 11);
INSERT INTO `sys_roles_menus` VALUES (11, 12);
INSERT INTO `sys_roles_menus` VALUES (11, 13);
INSERT INTO `sys_roles_menus` VALUES (11, 14);
INSERT INTO `sys_roles_menus` VALUES (11, 15);
INSERT INTO `sys_roles_menus` VALUES (11, 16);
INSERT INTO `sys_roles_menus` VALUES (11, 18);
INSERT INTO `sys_roles_menus` VALUES (11, 19);
INSERT INTO `sys_roles_menus` VALUES (11, 28);
INSERT INTO `sys_roles_menus` VALUES (11, 30);
INSERT INTO `sys_roles_menus` VALUES (11, 32);
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
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (43, 'admin', '44c071210f82b42b1dda7bf3dcad348d', '2bbf27adcf3c259df147643d6fe70a23', 1, '787824374@qq.com', 'https://s2.ax1x.com/2019/03/23/AGO1q1.jpg', '2019-03-27 10:15:54');
INSERT INTO `sys_user` VALUES (39, 'qqq', 'ba256b383020d3ccaa3c92e76499c45b', '79d485fa7dfab115fcf9f9eb56a9fc07', 1, 'nihao@qq.com', 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1956623052,720822838&fm=26&gp=0.jpg', '2019-03-27 10:15:54');
INSERT INTO `sys_user` VALUES (1, 'miv', '$2a$10$AmZyWDuq5xQd27NkjpvmrObJ6g6Z5E7.lhrMqdsGZbOwpulhe/Dku', '2bbf27adcf3c259df147643d6fe70a23', 1, 'miv@qq.com', 'https://i.loli.net/2018/12/06/5c08894d8de21.jpg', '2019-03-27 11:44:03');
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
INSERT INTO `sys_user_role` VALUES (39, 2);
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (39, 1);
INSERT INTO `sys_user_role` VALUES (42, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
