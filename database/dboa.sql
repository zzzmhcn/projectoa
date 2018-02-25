/*
 Navicat MySQL Data Transfer

 Source Server         : local
 Source Server Version : 50720
 Source Host           : localhost
 Source Database       : dboa

 Target Server Version : 50720
 File Encoding         : utf-8

 Date: 02/25/2018 20:28:14 PM
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `userinfo`
-- ----------------------------
BEGIN;
INSERT INTO `userinfo` VALUES ('1', '1', null, null, null, null, null, null, null, null, null, null, '2018-02-13 22:32:56', null), ('3', '3', null, null, null, null, null, null, null, null, null, null, '2018-02-14 22:27:04', null), ('5', '7', '3', '2018-01-30', '25', '222222222222222222', '420120577@qq.com', '420120577', 'weichat', 'weibo', '13919191919', null, '2018-02-14 22:47:02', '2018-02-25 19:57:25'), ('6', '8', null, null, null, null, null, null, null, null, null, null, '2018-02-15 13:25:56', null), ('7', '9', null, null, null, null, null, null, null, null, null, null, '2018-02-15 13:30:31', null), ('8', '10', null, null, null, null, null, null, null, null, null, '0', '2018-02-18 11:56:03', '2018-02-22 22:37:31'), ('9', '11', null, null, null, null, null, null, null, null, null, null, '2018-02-20 22:17:30', null);
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `users`
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '1', '1', '0', null, '2018-02-13 22:32:48', '2018-02-22 22:59:07'), ('3', 'chengshanyunduo', 'e10adc3949ba59abbe56e057f20f883e', '程杉耘朵', '2', '1', '0', null, '2018-02-14 22:27:04', '2018-02-22 22:15:53'), ('7', 'csyd1028', 'e10adc3949ba59abbe56e057f20f883e', '程杉耘朵', '2', '2', '0', null, '2018-02-14 22:47:02', '2018-02-22 22:12:31'), ('8', 'zhangminghui', 'e10adc3949ba59abbe56e057f20f883e', '张明辉', '3', '3', '0', null, '2018-02-15 13:25:56', '2018-02-22 22:12:37'), ('9', 'zmh0403', 'e10adc3949ba59abbe56e057f20f883e', '张明辉', '2', '1', '0', null, '2018-02-15 13:30:31', null), ('10', 'zhangdabao', 'e10adc3949ba59abbe56e057f20f883e', '张大宝', '5', '4', '0', null, '2018-02-18 11:56:02', '2018-02-22 22:38:06'), ('11', 'csyd11', 'e10adc3949ba59abbe56e057f20f883e', '程宝宝', '4', '3', '0', null, '2018-02-20 22:17:30', '2018-02-22 22:37:44');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
