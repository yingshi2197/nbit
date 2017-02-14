/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : nbit

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-02-14 15:35:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` varchar(40) NOT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `photo` varchar(400) DEFAULT NULL COMMENT '头像',
  `sex` varchar(40) DEFAULT NULL COMMENT '性别，0表示男，1表示女',
  `email` varchar(400) DEFAULT NULL COMMENT '邮箱',
  `customer_id` varchar(40) DEFAULT NULL COMMENT '客户',
  `login_id` varchar(40) DEFAULT NULL COMMENT '账号',
  `password` varchar(512) DEFAULT NULL COMMENT '密码，加密',
  `description` text COMMENT '描述',
  `create_user_id` varchar(40) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modify_user_id` varchar(40) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `delete_flag` varchar(1) DEFAULT NULL COMMENT '删除标志,0表示正常，1表示删除',
  `role` varchar(40) DEFAULT NULL COMMENT '角色，暂时放这',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息';

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '管理员', '18888888888', null, '0', '888888@88.com', null, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '', '10000', '2016-12-26 11:48:30', '10000', '2016-12-26 11:50:32', '0', 'admin');