/*
 Navicat MySQL Data Transfer

 Source Server         : local
 Source Server Version : 50720
 Source Host           : localhost
 Source Database       : dboa

 Target Server Version : 50720
 File Encoding         : utf-8

 Date: 02/15/2018 13:09:47 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `calendar`
-- ----------------------------
DROP TABLE IF EXISTS `calendar`;
CREATE TABLE `calendar` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `departments`
-- ----------------------------
DROP TABLE IF EXISTS `departments`;
CREATE TABLE `departments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `department` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `departments`
-- ----------------------------
BEGIN;
INSERT INTO `departments` VALUES ('1', '管理员', '2018-02-13 22:28:22', '2018-02-13 22:32:19'), ('2', '人事部', '2018-02-13 22:28:49', null), ('3', '财务部', '2018-02-13 22:28:54', null), ('4', '市场部', '2018-02-13 22:29:10', null), ('5', '开发部', '2018-02-13 22:29:18', null);
COMMIT;

-- ----------------------------
--  Table structure for `messages`
-- ----------------------------
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `message` text,
  `send_id` int(11) NOT NULL,
  `receive_id` int(11) NOT NULL,
  `is_del` varchar(1) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `notices`
-- ----------------------------
DROP TABLE IF EXISTS `notices`;
CREATE TABLE `notices` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `notice` text,
  `send_id` int(11) NOT NULL,
  `is_del` varchar(1) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `positions`
-- ----------------------------
DROP TABLE IF EXISTS `positions`;
CREATE TABLE `positions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `position` varchar(255) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `positions`
-- ----------------------------
BEGIN;
INSERT INTO `positions` VALUES ('1', '管理员', '2018-02-13 22:31:17', '2018-02-13 22:32:11'), ('2', '经理', '2018-02-13 22:31:45', null), ('3', '主管', '2018-02-13 22:31:50', null), ('4', '普通', '2018-02-13 22:31:56', null);
COMMIT;

-- ----------------------------
--  Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `identity_card` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `qq` int(11) DEFAULT NULL,
  `wechat` varchar(255) DEFAULT NULL,
  `weibo` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `is_del` varchar(1) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `userinfo`
-- ----------------------------
BEGIN;
INSERT INTO `userinfo` VALUES ('1', '1', null, null, null, null, null, null, null, null, null, null, '2018-02-13 22:32:56', null), ('3', '3', null, null, null, null, null, null, null, null, null, null, '2018-02-14 22:27:04', null), ('5', '7', null, null, null, null, null, null, null, null, null, null, '2018-02-14 22:47:02', null);
COMMIT;

-- ----------------------------
--  Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(31) NOT NULL,
  `password` varchar(255) NOT NULL,
  `realname` varchar(31) NOT NULL,
  `department_id` int(11) NOT NULL,
  `position_id` int(11) NOT NULL,
  `is_del` varchar(1) NOT NULL DEFAULT '0',
  `last_login_time` timestamp NULL DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `users`
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('1', 'admin', 'admin', '管理员', '1', '1', '0', null, '2018-02-13 22:32:48', null), ('3', 'chengshanyunduo', 'e10adc3949ba59abbe56e057f20f883e', '程杉耘朵', '2', '1', '0', null, '2018-02-14 22:27:04', null), ('7', 'csyd1028', 'e10adc3949ba59abbe56e057f20f883e', '程杉耘朵', '2', '2', '0', null, '2018-02-14 22:47:02', null);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
