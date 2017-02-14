/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : nbit

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-02-14 15:35:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label` (
  `id` varchar(40) NOT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '名称',
  `ename` varchar(200) DEFAULT NULL COMMENT '英文名',
  `code` varchar(10) DEFAULT NULL COMMENT '编码',
  `description` text COMMENT '描述',
  `create_user_id` varchar(40) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modify_user_id` varchar(40) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `delete_flag` varchar(1) DEFAULT NULL COMMENT '删除标志,0表示正常，1表示删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签表';

-- ----------------------------
-- Records of label
-- ----------------------------
INSERT INTO `label` VALUES ('04698381-d4e8-11e6-b4df-74d02b1a2f8d', 'O2O', 'O2O', 'O2O', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:46:05', null, null, '0');
INSERT INTO `label` VALUES ('0aa12fe1-d4e9-11e6-b4df-74d02b1a2f8d', '弹性工作制', 'FlexibleWork', 'fexible', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:53:25', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:53:30', '0');
INSERT INTO `label` VALUES ('15ea3f0f-d4e8-11e6-b4df-74d02b1a2f8d', '媒体', 'Media', 'media', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:46:35', null, null, '0');
INSERT INTO `label` VALUES ('2453d77a-d4e9-11e6-b4df-74d02b1a2f8d', '轻松', 'Relaxed', 'relaxed', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:54:08', null, null, '0');
INSERT INTO `label` VALUES ('274eea97-d4e8-11e6-b4df-74d02b1a2f8d', '网络营销', 'NetworkMarketing', 'NM', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:47:04', null, null, '0');
INSERT INTO `label` VALUES ('35072b69-d4e9-11e6-b4df-74d02b1a2f8d', '环境好', 'GoodEnvironment', 'goodenv', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:54:36', null, null, '0');
INSERT INTO `label` VALUES ('3b83f430-d4e8-11e6-b4df-74d02b1a2f8d', '网络编辑', 'WebEditor', 'WE', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:47:38', null, null, '0');
INSERT INTO `label` VALUES ('3e1636c8-d4e7-11e6-b4df-74d02b1a2f8d', '攻城狮', 'Engineer', 'engineer', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:40:33', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:44:02', '0');
INSERT INTO `label` VALUES ('470062b9-d4e8-11e6-b4df-74d02b1a2f8d', '年轻', 'Young', 'young', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:47:57', null, null, '0');
INSERT INTO `label` VALUES ('5c16861a-d4e7-11e6-b4df-74d02b1a2f8d', '金融', 'Finance', 'finance', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:41:23', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:43:57', '0');
INSERT INTO `label` VALUES ('72e93edd-d4e7-11e6-b4df-74d02b1a2f8d', 'IT', 'IT', 'IT', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:42:01', null, null, '0');
INSERT INTO `label` VALUES ('78a9fb38-d4e7-11e6-b4df-74d02b1a2f8d', '电子商务', 'E_commerce', 'dzsw', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:42:11', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:42:48', '0');
INSERT INTO `label` VALUES ('902f488d-d4e8-11e6-b4df-74d02b1a2f8d', '加班少', 'LessOvertime', 'leovertime', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:50:00', null, null, '0');
INSERT INTO `label` VALUES ('b3937579-d4e7-11e6-b4df-74d02b1a2f8d', '广告', 'Advertisement', 'Adv', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:43:50', null, null, '0');
INSERT INTO `label` VALUES ('b83d20cc-d4e8-11e6-b4df-74d02b1a2f8d', '成长ing', 'GrowUping', 'growuping', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:51:07', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:53:38', '0');
INSERT INTO `label` VALUES ('cd30de20-d4e7-11e6-b4df-74d02b1a2f8d', '加班狂', 'Overtime', 'Overtime', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:44:33', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:44:39', '0');
INSERT INTO `label` VALUES ('cff9a94d-d4e8-11e6-b4df-74d02b1a2f8d', '学习', 'Study', 'Study', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:51:47', null, null, '0');
INSERT INTO `label` VALUES ('dbc5a834-d4e7-11e6-b4df-74d02b1a2f8d', '70后', '70s', '70s', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:44:57', null, null, '0');
INSERT INTO `label` VALUES ('e05c8675-d4e7-11e6-b4df-74d02b1a2f8d', '80后', '80s', '80s', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:45:05', null, null, '0');
INSERT INTO `label` VALUES ('e5c35e67-d4e8-11e6-b4df-74d02b1a2f8d', '平台', 'Platform', 'platform', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:52:23', null, null, '0');
INSERT INTO `label` VALUES ('e6aefc51-d4e7-11e6-b4df-74d02b1a2f8d', '90后', '90s', '90s', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:45:15', null, null, '0');
INSERT INTO `label` VALUES ('f22365cc-d4e8-11e6-b4df-74d02b1a2f8d', '晋升', 'Promotion', 'promotion', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:52:44', null, null, '0');
INSERT INTO `label` VALUES ('f7de459b-d4e7-11e6-b4df-74d02b1a2f8d', '移动互联网', 'MobileInternet', 'MI', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:45:44', null, null, '0');
INSERT INTO `label` VALUES ('fcb009f5-d4e7-11e6-b4df-74d02b1a2f8d', '互联网', 'Internet', 'Internet', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-07 22:45:52', null, null, '0');
