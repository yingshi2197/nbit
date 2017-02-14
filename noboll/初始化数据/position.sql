/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : nbit

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-02-14 15:35:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `id` varchar(40) NOT NULL,
  `type` varchar(40) DEFAULT NULL COMMENT '职位类型，来源数据字典',
  `name` varchar(200) DEFAULT NULL COMMENT '职位名称',
  `ename` char(10) DEFAULT NULL COMMENT '职位英文名',
  `code` varchar(40) DEFAULT NULL COMMENT '职位编码',
  `description` text COMMENT '描述',
  `create_user_id` varchar(40) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modify_user_id` varchar(40) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `delete_flag` varchar(1) DEFAULT NULL COMMENT '删除标志,0表示正常，1表示删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职位表';

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES ('23cc0167-c051-11e6-a5bb-74d02b1a2f8d', '1b11726c-bb83-11e6-acae-74d02b1a2f8d', 'JAVA工程师', null, 'develop_java', 'java', '10000', '2016-12-12 17:55:57', '10000', '2016-12-12 17:56:51', '0');
INSERT INTO `position` VALUES ('2d25bcf6-c051-11e6-a5bb-74d02b1a2f8d', '1b11726c-bb83-11e6-acae-74d02b1a2f8d', 'IOS工程师', null, 'develop_ios', 'ios', '10000', '2016-12-12 17:56:13', '10000', '2016-12-12 17:56:47', '0');
INSERT INTO `position` VALUES ('327c6ffc-c051-11e6-a5bb-74d02b1a2f8d', '1b11726c-bb83-11e6-acae-74d02b1a2f8d', 'Android工程师', null, 'develop_android', 'android', '10000', '2016-12-12 17:56:21', '10000', '2016-12-12 17:56:40', '0');
