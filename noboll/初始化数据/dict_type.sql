/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : nbit

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-02-14 15:34:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dict_type
-- ----------------------------
DROP TABLE IF EXISTS `dict_type`;
CREATE TABLE `dict_type` (
  `id` varchar(40) NOT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '名称',
  `ename` varchar(400) DEFAULT NULL COMMENT '英文名',
  `code` varchar(40) DEFAULT NULL COMMENT '编码',
  `description` text COMMENT '项目描述',
  `create_user_id` varchar(40) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modify_user_id` varchar(40) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `delete_flag` varchar(1) DEFAULT NULL COMMENT '删除标志,0表示正常，1表示删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典类型表';

-- ----------------------------
-- Records of dict_type
-- ----------------------------
INSERT INTO `dict_type` VALUES ('01af82bd-cb73-11e6-8856-74d02b1a2f8d', '现场面试结果', null, 'xcms', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2016-12-26 21:56:07', null, null, '0');
INSERT INTO `dict_type` VALUES ('08e65866-bb82-11e6-acae-74d02b1a2f8d', '级别', null, 'requirement_level', '需求级别', '10000', '2016-12-06 15:03:10', null, null, '0');
INSERT INTO `dict_type` VALUES ('10be0f6b-bb82-11e6-acae-74d02b1a2f8d', '周期', null, 'requirement_period', '需求周期', '10000', '2016-12-06 15:03:23', null, null, '0');
INSERT INTO `dict_type` VALUES ('1b78ef72-bb82-11e6-acae-74d02b1a2f8d', '地址', null, 'address', '地址\n', '10000', '2016-12-06 15:03:41', null, null, '0');
INSERT INTO `dict_type` VALUES ('35f53b5b-bb90-11e6-acae-74d02b1a2f8d', '性别', null, 'sex', '性别', '10000', '2016-12-06 16:44:39', '10000', '2016-12-06 16:45:19', '0');
INSERT INTO `dict_type` VALUES ('3dd38df4-c5f4-11e6-b508-74d02b1a2f8d', '企业规模', null, 'scale', '', '10000', '2016-12-19 22:06:04', null, null, '0');
INSERT INTO `dict_type` VALUES ('54741f08-ce3f-11e6-859c-74d02b1a2f8d', '投递状态', null, 'deliver_status', '', '0531f914-c6bd-11e6-8535-74d02b1a2f8d', '2016-12-30 11:23:36', null, null, '0');
INSERT INTO `dict_type` VALUES ('7532b841-bb90-11e6-acae-74d02b1a2f8d', '学历', null, 'degree', '学历', '10000', '2016-12-06 16:46:25', null, null, '0');
INSERT INTO `dict_type` VALUES ('8683639c-c5f3-11e6-b508-74d02b1a2f8d', '企业性质', null, 'nature', '', '10000', '2016-12-19 22:00:56', null, null, '0');
INSERT INTO `dict_type` VALUES ('9e5d6e4e-bb91-11e6-acae-74d02b1a2f8d', '薪资', null, 'pay', '', '10000', '2016-12-06 16:54:43', null, null, '0');
INSERT INTO `dict_type` VALUES ('b25901cf-bb90-11e6-acae-74d02b1a2f8d', '工作年限', null, 'work_life', '', '10000', '2016-12-06 16:48:07', null, null, '0');
INSERT INTO `dict_type` VALUES ('d8e8fd10-cb72-11e6-8856-74d02b1a2f8d', '面试类型', null, 'interview_type', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2016-12-26 21:54:58', null, null, '0');
INSERT INTO `dict_type` VALUES ('dcd3f3f4-c5f4-11e6-b508-74d02b1a2f8d', '企业行业', null, 'industry', '', '10000', '2016-12-19 22:10:30', null, null, '0');
INSERT INTO `dict_type` VALUES ('ef33f454-bb81-11e6-acae-74d02b1a2f8d', '职位类型', null, 'position_type', '需求职位\n', '10000', '2016-12-06 15:02:27', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2016-12-27 15:47:11', '0');
INSERT INTO `dict_type` VALUES ('f8d9db3d-cb72-11e6-8856-74d02b1a2f8d', '电话面试结果', null, 'dhms', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2016-12-26 21:55:52', null, null, '0');
