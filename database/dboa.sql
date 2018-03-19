/*
Navicat MySQL Data Transfer

Source Server         : 139.196.72.225
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : dboa

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-03-19 09:46:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for calendar
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
-- Records of calendar
-- ----------------------------

-- ----------------------------
-- Table structure for departments
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
-- Records of departments
-- ----------------------------
INSERT INTO `departments` VALUES ('1', '管理员', '2018-02-13 22:28:22', '2018-02-13 22:32:19');
INSERT INTO `departments` VALUES ('2', '人事部', '2018-02-13 22:28:49', null);
INSERT INTO `departments` VALUES ('3', '财务部', '2018-02-13 22:28:54', null);
INSERT INTO `departments` VALUES ('4', '市场部', '2018-02-13 22:29:10', null);
INSERT INTO `departments` VALUES ('5', '开发部', '2018-02-13 22:29:18', null);

-- ----------------------------
-- Table structure for messages
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of messages
-- ----------------------------
INSERT INTO `messages` VALUES ('7', '渣渣', '渣渣渣渣渣渣', '8', '1', null, '2018-02-27 10:10:41', null);
INSERT INTO `messages` VALUES ('8', '渣渣', '渣渣渣渣渣渣', '8', '1', null, '2018-02-27 10:10:48', null);
INSERT INTO `messages` VALUES ('9', '渣渣', '渣渣渣渣渣渣', '8', '1', null, '2018-02-27 10:10:51', null);
INSERT INTO `messages` VALUES ('10', '渣渣', '渣渣渣渣渣渣', '8', '1', null, '2018-02-27 10:10:53', null);
INSERT INTO `messages` VALUES ('11', '喂喂喂', '喂喂喂喂喂喂喂喂喂喂喂喂', '8', '1', null, '2018-02-27 11:18:06', null);
INSERT INTO `messages` VALUES ('12', '喂喂喂', '喂喂喂喂喂喂喂喂喂喂喂喂', '8', '1', null, '2018-02-27 11:18:07', null);
INSERT INTO `messages` VALUES ('13', '喂喂喂', '喂喂喂喂喂喂喂喂喂喂喂喂', '8', '1', null, '2018-02-27 11:18:09', null);
INSERT INTO `messages` VALUES ('14', '我我我我我我我我我我', '我我我我', '1', '8', null, '2018-02-27 12:08:40', null);
INSERT INTO `messages` VALUES ('15', '我我我我我我我我我', '我我我我', '1', '8', null, '2018-02-27 12:08:45', null);
INSERT INTO `messages` VALUES ('16', '你好管理员', '我是你爸爸', '8', '1', null, '2018-02-28 11:27:20', null);
INSERT INTO `messages` VALUES ('17', '在吗在吗', '我是你爸爸', '8', '1', null, '2018-02-28 11:27:27', null);
INSERT INTO `messages` VALUES ('18', '黑黑黑', '我是你爸爸', '8', '1', null, '2018-02-28 11:27:34', null);
INSERT INTO `messages` VALUES ('19', '你好本尊', '再见本尊', '1', '8', null, '2018-02-28 11:34:01', null);
INSERT INTO `messages` VALUES ('20', '你好本尊123', '再见本尊123', '1', '8', null, '2018-02-28 11:34:05', null);
INSERT INTO `messages` VALUES ('21', '你好本尊456', '再见本尊456', '1', '8', null, '2018-02-28 11:34:10', null);
INSERT INTO `messages` VALUES ('22', '维护', '维护123', '8', '1', null, '2018-02-28 16:35:23', null);
INSERT INTO `messages` VALUES ('23', '管理员大人你好', '管理员大人你好', '10', '1', null, '2018-02-28 17:11:13', null);
INSERT INTO `messages` VALUES ('24', '你好 请问', '我是谁？我在哪？发生了什么？', '8', '1', null, '2018-03-05 11:05:51', null);
INSERT INTO `messages` VALUES ('25', '你好 请问', '我是谁？我在哪？发生了什么？', '8', '3', null, '2018-03-05 11:05:51', null);
INSERT INTO `messages` VALUES ('26', '你好 请问', '我是谁？我在哪？发生了什么？', '8', '7', null, '2018-03-05 11:05:51', null);
INSERT INTO `messages` VALUES ('27', '你好 请问', '我是谁？我在哪？发生了什么？', '8', '1', null, '2018-03-05 11:05:54', null);
INSERT INTO `messages` VALUES ('28', '你好 请问', '我是谁？我在哪？发生了什么？', '8', '3', null, '2018-03-05 11:05:54', null);
INSERT INTO `messages` VALUES ('29', '你好 请问', '我是谁？我在哪？发生了什么？', '8', '7', null, '2018-03-05 11:05:54', null);
INSERT INTO `messages` VALUES ('30', '你好管理员', 'http://zhangminghui.iok.la/projectoa', '8', '1', null, '2018-03-05 11:06:07', null);
INSERT INTO `messages` VALUES ('31', '你好管理员', '管理员大人 我的电脑坏了！！！！', '8', '1', null, '2018-03-05 11:06:40', null);

-- ----------------------------
-- Table structure for notices
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notices
-- ----------------------------
INSERT INTO `notices` VALUES ('1', '新年快乐', '新年快乐', '1', null, '2018-02-28 16:24:22', null);
INSERT INTO `notices` VALUES ('2', '万事如意', '万事如意万事如意万事如意', '1', null, '2018-02-28 16:27:32', null);
INSERT INTO `notices` VALUES ('3', '大吉大利', '大吉大利', '1', null, '2018-03-01 11:20:18', null);
INSERT INTO `notices` VALUES ('4', '今晚吃鸡', '今晚吃鸡', '1', null, '2018-03-01 11:20:25', null);
INSERT INTO `notices` VALUES ('5', '测试一下公告的长度可以有多长', '一二三四五六七九十一二三四五六七九十一二三四五六七九十一二三四五六七九十一二三四五六七九十一二三四五六七九十一二三四五六七九十一二三四五六七九十一二三四五六七九十一二三四五六七九十一二三四五六七九十一二三四五六七九十一二三四五六七九十一二三四五六七九十', '1', null, '2018-03-01 13:12:07', null);
INSERT INTO `notices` VALUES ('6', '祝大家元宵节快乐', '祝大家元宵节快乐！', '1', null, '2018-03-01 13:45:51', null);
INSERT INTO `notices` VALUES ('7', '新年快乐', '万事如意', '1', null, '2018-03-01 13:50:44', null);
INSERT INTO `notices` VALUES ('8', '新年快乐', '万事如意', '1', null, '2018-03-01 13:51:06', null);
INSERT INTO `notices` VALUES ('9', '重要通知', '2018年3月8日 全公司女同志放假1天', '1', null, '2018-03-05 11:01:37', null);
INSERT INTO `notices` VALUES ('10', '震惊！', '震惊！公司居然发生如此骇人听闻之事！', '1', null, '2018-03-05 11:02:21', null);
INSERT INTO `notices` VALUES ('11', '重要通知', '即日起 公司网站正式上线！http://zhangminghui.iok.la/projectoa\n欢迎访问', '1', null, '2018-03-05 11:03:01', null);
INSERT INTO `notices` VALUES ('12', '测试', '测试 2018-3-5 11:03:17', '1', null, '2018-03-05 11:03:44', null);
INSERT INTO `notices` VALUES ('13', '测试测试', '2018年3月5日11:03:36', '1', null, '2018-03-05 11:03:58', null);
INSERT INTO `notices` VALUES ('14', '测试测', '2018-3-5 11:03:49', '1', null, '2018-03-05 11:04:12', null);
INSERT INTO `notices` VALUES ('15', '测试测是我才是', '2018年3月5日11:04:02', '1', null, '2018-03-05 11:04:25', null);
INSERT INTO `notices` VALUES ('16', '喜大普奔！', '本公司女员工三八妇女节 2018年3月8日放假一整天', '1', null, '2018-03-05 11:10:54', null);

-- ----------------------------
-- Table structure for positions
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
-- Records of positions
-- ----------------------------
INSERT INTO `positions` VALUES ('1', '管理员', '2018-02-13 22:31:17', '2018-02-13 22:32:11');
INSERT INTO `positions` VALUES ('2', '经理', '2018-02-13 22:31:45', null);
INSERT INTO `positions` VALUES ('3', '主管', '2018-02-13 22:31:50', null);
INSERT INTO `positions` VALUES ('4', '普通', '2018-02-13 22:31:56', null);

-- ----------------------------
-- Table structure for userinfo
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', '1', '1', '1970-01-02', '39', '310123456789451234', 'admin@projectoa.com', '123456789', '123456789', '123456789', '12345678901', null, '2018-02-13 22:32:56', '2018-03-08 12:54:11');
INSERT INTO `userinfo` VALUES ('3', '3', '2', '1970-01-01', null, '', '', null, '', '', '', null, '2018-02-14 22:27:04', '2018-03-05 11:09:02');
INSERT INTO `userinfo` VALUES ('5', '7', '3', '2018-01-30', '25', '222222222222222222', '420120577@qq.com', '420120577', 'weichat', 'weibo', '13919191919', '1', '2018-02-14 22:47:02', '2018-02-25 19:57:25');
INSERT INTO `userinfo` VALUES ('6', '8', '1', '1992-04-02', '18', '313132121313313', '', null, '', '', '', null, '2018-02-15 13:25:56', '2018-02-26 11:27:44');
INSERT INTO `userinfo` VALUES ('7', '9', null, null, null, null, null, null, null, null, null, '1', '2018-02-15 13:30:31', '2018-02-26 11:41:07');
INSERT INTO `userinfo` VALUES ('8', '10', null, null, null, null, null, null, null, null, null, '0', '2018-02-18 11:56:03', '2018-02-22 22:37:31');
INSERT INTO `userinfo` VALUES ('9', '11', '2', null, null, '', '', null, '', '', '', null, '2018-02-20 22:17:30', '2018-03-02 11:50:22');
INSERT INTO `userinfo` VALUES ('10', '12', null, null, null, null, null, null, null, null, null, null, '2018-03-02 15:10:42', null);
INSERT INTO `userinfo` VALUES ('11', '13', null, null, null, null, null, null, null, null, null, null, '2018-03-02 15:10:47', null);
INSERT INTO `userinfo` VALUES ('12', '14', null, null, null, null, null, null, null, null, null, null, '2018-03-02 15:11:00', null);
INSERT INTO `userinfo` VALUES ('13', '15', null, null, null, null, null, null, null, null, null, null, '2018-03-02 15:11:03', null);
INSERT INTO `userinfo` VALUES ('14', '16', null, null, null, null, null, null, null, null, null, null, '2018-03-02 15:11:05', null);
INSERT INTO `userinfo` VALUES ('15', '17', null, null, null, null, null, null, null, null, null, null, '2018-03-02 15:11:07', null);
INSERT INTO `userinfo` VALUES ('16', '18', null, null, null, null, null, null, null, null, null, '1', '2018-03-02 15:11:09', '2018-03-02 15:58:27');

-- ----------------------------
-- Table structure for users
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '管理员', '1', '1', '0', '2018-03-17 22:09:30', '2018-02-13 22:32:48', '2018-03-17 22:09:29');
INSERT INTO `users` VALUES ('3', 'chengshanyunduo', 'e10adc3949ba59abbe56e057f20f883e', '程杉耘朵', '2', '4', '0', '2018-03-05 11:08:38', '2018-02-14 22:27:04', '2018-03-05 11:09:32');
INSERT INTO `users` VALUES ('7', 'csyd1028', 'e10adc3949ba59abbe56e057f20f883e', '耘朵', '2', '2', '1', null, '2018-02-14 22:47:02', '2018-03-01 13:49:17');
INSERT INTO `users` VALUES ('8', 'zhangminghui', 'e10adc3949ba59abbe56e057f20f883e', '张明辉', '3', '3', '0', '2018-03-08 16:14:12', '2018-02-15 13:25:56', '2018-03-08 16:14:12');
INSERT INTO `users` VALUES ('9', 'zmh0403', 'e10adc3949ba59abbe56e057f20f883e', '张大大', '2', '1', '0', '2018-03-01 13:18:30', '2018-02-15 13:30:31', '2018-03-01 13:18:29');
INSERT INTO `users` VALUES ('10', 'zhangdabao', 'e10adc3949ba59abbe56e057f20f883e', '张大宝', '5', '4', '0', '2018-03-01 13:22:16', '2018-02-18 11:56:02', '2018-03-01 13:22:16');
INSERT INTO `users` VALUES ('11', 'csyd11', 'e10adc3949ba59abbe56e057f20f883e', '程宝宝', '4', '3', '0', '2018-03-02 11:50:12', '2018-02-20 22:17:30', '2018-03-02 11:50:11');
INSERT INTO `users` VALUES ('12', 'testman', 'e10adc3949ba59abbe56e057f20f883e', '张三', '1', '1', '0', '2018-03-02 15:12:31', '2018-03-02 15:10:41', '2018-03-05 10:16:23');
INSERT INTO `users` VALUES ('13', 'testman123', 'e10adc3949ba59abbe56e057f20f883e', '李四', '5', '4', '0', null, '2018-03-02 15:10:47', '2018-03-05 10:16:26');
INSERT INTO `users` VALUES ('14', 'testman1', 'e10adc3949ba59abbe56e057f20f883e', '王五', '5', '4', '0', null, '2018-03-02 15:11:00', '2018-03-05 10:16:29');
INSERT INTO `users` VALUES ('15', 'testman11', 'e10adc3949ba59abbe56e057f20f883e', '赵六', '5', '4', '0', null, '2018-03-02 15:11:02', '2018-03-05 10:16:35');
INSERT INTO `users` VALUES ('16', 'testman111', 'e10adc3949ba59abbe56e057f20f883e', '测试员', '5', '4', '0', null, '2018-03-02 15:11:05', '2018-03-05 10:16:40');
INSERT INTO `users` VALUES ('17', 'testman1111', 'e10adc3949ba59abbe56e057f20f883e', '测试员', '2', '4', '0', null, '2018-03-02 15:11:07', '2018-03-11 10:44:16');
INSERT INTO `users` VALUES ('18', 'testman11111', 'e10adc3949ba59abbe56e057f20f883e', '测试员', '5', '4', '1', null, '2018-03-02 15:11:09', '2018-03-05 10:16:44');
