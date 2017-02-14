/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : nbit

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-02-14 15:34:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `id` varchar(40) NOT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '名称',
  `ename` varchar(400) DEFAULT NULL COMMENT '英文名',
  `type_id` varchar(40) DEFAULT NULL COMMENT '字典类型id',
  `code` varchar(40) DEFAULT NULL COMMENT '编码',
  `seq` int(3) DEFAULT NULL COMMENT '顺序',
  `description` text COMMENT '描述',
  `create_user_id` varchar(40) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_modify_user_id` varchar(40) DEFAULT NULL,
  `last_modify_time` datetime DEFAULT NULL,
  `delete_flag` varchar(1) DEFAULT NULL COMMENT '删除标志,0表示正常，1表示删除',
  PRIMARY KEY (`id`),
  KEY `INX_SDICT_CODE` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of dict
-- ----------------------------
INSERT INTO `dict` VALUES ('03fe19a1-bb83-11e6-acae-74d02b1a2f8d', '中级', null, '08e65866-bb82-11e6-acae-74d02b1a2f8d', 'level_middle', '20', '', '10000', '2016-12-06 15:10:11', null, null, '0');
INSERT INTO `dict` VALUES ('0b7a8a03-cb73-11e6-8856-74d02b1a2f8d', '电话面试通过', null, 'f8d9db3d-cb72-11e6-8856-74d02b1a2f8d', 'dhmstg', '10', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2016-12-26 21:56:23', null, null, '0');
INSERT INTO `dict` VALUES ('0dd1ecfc-c5f4-11e6-b508-74d02b1a2f8d', '集体所有制企业', null, '8683639c-c5f3-11e6-b508-74d02b1a2f8d', 'jitisuoyouzhi', '20', '', '10000', '2016-12-19 22:04:43', null, null, '0');
INSERT INTO `dict` VALUES ('0f495882-bb83-11e6-acae-74d02b1a2f8d', '高级', null, '08e65866-bb82-11e6-acae-74d02b1a2f8d', 'level_high', '30', '', '10000', '2016-12-06 15:10:30', null, null, '0');
INSERT INTO `dict` VALUES ('13f07a0a-cb73-11e6-8856-74d02b1a2f8d', '电话面试不通过', null, 'f8d9db3d-cb72-11e6-8856-74d02b1a2f8d', 'dhmsbtg', '20', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2016-12-26 21:56:37', null, null, '0');
INSERT INTO `dict` VALUES ('19ef5c20-c5f4-11e6-b508-74d02b1a2f8d', '三资企业', null, '8683639c-c5f3-11e6-b508-74d02b1a2f8d', 'sanzi', '30', '三资企业 （其中：中外合作企业、中外合资企业、 外商独资企业） ', '10000', '2016-12-19 22:05:03', null, null, '0');
INSERT INTO `dict` VALUES ('1a8a7cfc-cb73-11e6-8856-74d02b1a2f8d', '电话未接听', null, 'f8d9db3d-cb72-11e6-8856-74d02b1a2f8d', 'dhwjt', '30', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2016-12-26 21:56:49', null, null, '0');
INSERT INTO `dict` VALUES ('1b11726c-bb83-11e6-acae-74d02b1a2f8d', '开发类', null, 'ef33f454-bb81-11e6-acae-74d02b1a2f8d', 'position_develop', '10', '', '10000', '2016-12-06 15:10:50', '10000', '2016-12-12 17:52:35', '0');
INSERT INTO `dict` VALUES ('236f6c4a-cb73-11e6-8856-74d02b1a2f8d', '现场面试通过', null, '01af82bd-cb73-11e6-8856-74d02b1a2f8d', 'xcmstg', '10', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2016-12-26 21:57:03', null, null, '0');
INSERT INTO `dict` VALUES ('2a558034-cb73-11e6-8856-74d02b1a2f8d', '现场面试不通过', null, '01af82bd-cb73-11e6-8856-74d02b1a2f8d', 'xcmsbtg', '20', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2016-12-26 21:57:15', null, null, '0');
INSERT INTO `dict` VALUES ('2b4a0382-c5f4-11e6-b508-74d02b1a2f8d', '私营企业', null, '8683639c-c5f3-11e6-b508-74d02b1a2f8d', 'siying', '40', '', '10000', '2016-12-19 22:05:33', null, null, '0');
INSERT INTO `dict` VALUES ('30da6067-bb83-11e6-acae-74d02b1a2f8d', '设计类', null, 'ef33f454-bb81-11e6-acae-74d02b1a2f8d', 'position_design', '20', '', '10000', '2016-12-06 15:11:26', '10000', '2016-12-12 17:52:45', '0');
INSERT INTO `dict` VALUES ('314ad7fb-c5f4-11e6-b508-74d02b1a2f8d', '其他企业', null, '8683639c-c5f3-11e6-b508-74d02b1a2f8d', 'qitaqiye', '50', '', '10000', '2016-12-19 22:05:43', null, null, '0');
INSERT INTO `dict` VALUES ('387af8a5-bb83-11e6-acae-74d02b1a2f8d', '文员类', null, 'ef33f454-bb81-11e6-acae-74d02b1a2f8d', 'position_clerk', '30', '', '10000', '2016-12-06 15:11:39', '10000', '2016-12-12 17:53:41', '0');
INSERT INTO `dict` VALUES ('3f96e4b8-bb90-11e6-acae-74d02b1a2f8d', '男', null, '35f53b5b-bb90-11e6-acae-74d02b1a2f8d', 'sex_man', '10', '', '10000', '2016-12-06 16:44:55', '10000', '2016-12-06 16:45:11', '0');
INSERT INTO `dict` VALUES ('4578cefa-bb90-11e6-acae-74d02b1a2f8d', '女', null, '35f53b5b-bb90-11e6-acae-74d02b1a2f8d', 'sex_women', '20', '', '10000', '2016-12-06 16:45:05', null, null, '0');
INSERT INTO `dict` VALUES ('63b31788-ce3f-11e6-859c-74d02b1a2f8d', '待确认', null, '54741f08-ce3f-11e6-859c-74d02b1a2f8d', 'deliver_daiqueren', '10', '', '0531f914-c6bd-11e6-8535-74d02b1a2f8d', '2016-12-30 11:24:02', null, null, '0');
INSERT INTO `dict` VALUES ('7ee69ff1-bb90-11e6-acae-74d02b1a2f8d', '高中', null, '7532b841-bb90-11e6-acae-74d02b1a2f8d', 'degree_highschool', '10', '', '10000', '2016-12-06 16:46:41', null, null, '0');
INSERT INTO `dict` VALUES ('8618e800-c5f4-11e6-b508-74d02b1a2f8d', '大型企业', null, '3dd38df4-c5f4-11e6-b508-74d02b1a2f8d', 'daxingqiye', '10', '', '10000', '2016-12-19 22:08:05', null, null, '0');
INSERT INTO `dict` VALUES ('862f4b1a-ce3f-11e6-859c-74d02b1a2f8d', '投递失败', null, '54741f08-ce3f-11e6-859c-74d02b1a2f8d', 'deliver_fail', '20', '', '0531f914-c6bd-11e6-8535-74d02b1a2f8d', '2016-12-30 11:25:00', null, null, '0');
INSERT INTO `dict` VALUES ('89a1b87b-bb90-11e6-acae-74d02b1a2f8d', '高职', null, '7532b841-bb90-11e6-acae-74d02b1a2f8d', 'degree_gaozhi', '20', '', '10000', '2016-12-06 16:46:59', null, null, '0');
INSERT INTO `dict` VALUES ('90dd431a-c5f4-11e6-b508-74d02b1a2f8d', '中型企业', null, '3dd38df4-c5f4-11e6-b508-74d02b1a2f8d', 'zhongxingqiye', '20', '', '10000', '2016-12-19 22:08:23', null, null, '0');
INSERT INTO `dict` VALUES ('9108870f-bb90-11e6-acae-74d02b1a2f8d', '大专', null, '7532b841-bb90-11e6-acae-74d02b1a2f8d', 'degree_dazhuan', '30', '', '10000', '2016-12-06 16:47:11', null, null, '0');
INSERT INTO `dict` VALUES ('9112e5c1-c5f5-11e6-b508-74d02b1a2f8d', '计算机/互联网/通信/电子', null, 'dcd3f3f4-c5f4-11e6-b508-74d02b1a2f8d', 'computer', '10', '', '10000', '2016-12-19 22:15:33', '10000', '2016-12-19 22:16:05', '0');
INSERT INTO `dict` VALUES ('94d3ec7c-ce3f-11e6-859c-74d02b1a2f8d', '已入职', null, '54741f08-ce3f-11e6-859c-74d02b1a2f8d', 'deliver_entrant', '30', '', '0531f914-c6bd-11e6-8535-74d02b1a2f8d', '2016-12-30 11:25:25', null, null, '0');
INSERT INTO `dict` VALUES ('9667122b-bb90-11e6-acae-74d02b1a2f8d', '本科', null, '7532b841-bb90-11e6-acae-74d02b1a2f8d', 'degree_benke', '40', '', '10000', '2016-12-06 16:47:20', null, null, '0');
INSERT INTO `dict` VALUES ('97226d79-c5f4-11e6-b508-74d02b1a2f8d', '小型企业', null, '3dd38df4-c5f4-11e6-b508-74d02b1a2f8d', 'xiaoxingqiye', '30', '', '10000', '2016-12-19 22:08:34', null, null, '0');
INSERT INTO `dict` VALUES ('9b3551f4-bb82-11e6-acae-74d02b1a2f8d', '北京', null, '1b78ef72-bb82-11e6-acae-74d02b1a2f8d', 'address_beijing', '10', '', '10000', '2016-12-06 15:07:15', null, null, '0');
INSERT INTO `dict` VALUES ('9d5bbdd7-c5f5-11e6-b508-74d02b1a2f8d', '会计/金融/银行/保险', null, 'dcd3f3f4-c5f4-11e6-b508-74d02b1a2f8d', 'money', '20', '', '10000', '2016-12-19 22:15:53', null, null, '0');
INSERT INTO `dict` VALUES ('9eb8cfb1-bb90-11e6-acae-74d02b1a2f8d', '博士', null, '7532b841-bb90-11e6-acae-74d02b1a2f8d', 'degree_boshi', '50', '', '10000', '2016-12-06 16:47:34', null, null, '0');
INSERT INTO `dict` VALUES ('a0ccf75e-bb82-11e6-acae-74d02b1a2f8d', '上海', null, '1b78ef72-bb82-11e6-acae-74d02b1a2f8d', 'address_shanghai', '20', '', '10000', '2016-12-06 15:07:25', null, null, '0');
INSERT INTO `dict` VALUES ('a1e5a549-c5f4-11e6-b508-74d02b1a2f8d', '微型企业', null, '3dd38df4-c5f4-11e6-b508-74d02b1a2f8d', 'weixingqiye', '40', '', '10000', '2016-12-19 22:08:52', null, null, '0');
INSERT INTO `dict` VALUES ('a5eec09c-bb90-11e6-acae-74d02b1a2f8d', '硕士', null, '7532b841-bb90-11e6-acae-74d02b1a2f8d', 'degree_shuoshi', '60', '', '10000', '2016-12-06 16:47:46', null, null, '0');
INSERT INTO `dict` VALUES ('a9c948e9-bb82-11e6-acae-74d02b1a2f8d', '广州', null, '1b78ef72-bb82-11e6-acae-74d02b1a2f8d', 'address_guangzhou', '30', '', '10000', '2016-12-06 15:07:40', null, null, '0');
INSERT INTO `dict` VALUES ('ae1c1d51-c5f5-11e6-b508-74d02b1a2f8d', '房地产/建筑', null, 'dcd3f3f4-c5f4-11e6-b508-74d02b1a2f8d', 'build', '30', '', '10000', '2016-12-19 22:16:22', null, null, '0');
INSERT INTO `dict` VALUES ('aedd7529-bb82-11e6-acae-74d02b1a2f8d', '深圳', null, '1b78ef72-bb82-11e6-acae-74d02b1a2f8d', 'address_shenzhen', '40', '', '10000', '2016-12-06 15:07:48', null, null, '0');
INSERT INTO `dict` VALUES ('b40d7804-bb82-11e6-acae-74d02b1a2f8d', '长沙', null, '1b78ef72-bb82-11e6-acae-74d02b1a2f8d', 'address_changsha', '50', '', '10000', '2016-12-06 15:07:57', null, null, '0');
INSERT INTO `dict` VALUES ('baaae245-bb82-11e6-acae-74d02b1a2f8d', '杭州', null, '1b78ef72-bb82-11e6-acae-74d02b1a2f8d', 'address_hangzhou', '60', '', '10000', '2016-12-06 15:08:08', null, null, '0');
INSERT INTO `dict` VALUES ('bd961eba-bb90-11e6-acae-74d02b1a2f8d', '1-3年', null, 'b25901cf-bb90-11e6-acae-74d02b1a2f8d', 'work_life_13', '10', '', '10000', '2016-12-06 16:48:26', null, null, '0');
INSERT INTO `dict` VALUES ('c300b383-bb91-11e6-acae-74d02b1a2f8d', '4999以下', null, '9e5d6e4e-bb91-11e6-acae-74d02b1a2f8d', 'pay_lt4999', '10', '', '10000', '2016-12-06 16:55:45', null, null, '0');
INSERT INTO `dict` VALUES ('c7d52289-ce3f-11e6-859c-74d02b1a2f8d', '处理中', null, '54741f08-ce3f-11e6-859c-74d02b1a2f8d', 'deliver_inhand', '15', '', '0531f914-c6bd-11e6-8535-74d02b1a2f8d', '2016-12-30 11:26:50', null, null, '0');
INSERT INTO `dict` VALUES ('c8e7f587-bb90-11e6-acae-74d02b1a2f8d', '3-5年', null, 'b25901cf-bb90-11e6-acae-74d02b1a2f8d', 'work_life_35', '20', '', '10000', '2016-12-06 16:48:45', null, null, '0');
INSERT INTO `dict` VALUES ('ca26ec48-c5f5-11e6-b508-74d02b1a2f8d', '专业服务/教育/培训', null, 'dcd3f3f4-c5f4-11e6-b508-74d02b1a2f8d', 'education', '40', '', '10000', '2016-12-19 22:17:09', null, null, '0');
INSERT INTO `dict` VALUES ('cd0f606b-bb82-11e6-acae-74d02b1a2f8d', '半年', null, '10be0f6b-bb82-11e6-acae-74d02b1a2f8d', 'period_midyear', '10', '', '10000', '2016-12-06 15:08:39', '10000', '2016-12-06 15:09:32', '0');
INSERT INTO `dict` VALUES ('cd4395a8-d735-11e6-abdd-74d02b1a2f8d', '已离职', null, '54741f08-ce3f-11e6-859c-74d02b1a2f8d', 'deliver_leave', '40', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2017-01-10 21:07:57', null, null, '0');
INSERT INTO `dict` VALUES ('cef540fa-bb90-11e6-acae-74d02b1a2f8d', '5年以上', null, 'b25901cf-bb90-11e6-acae-74d02b1a2f8d', 'work_life_510', '30', '', '10000', '2016-12-06 16:48:55', null, null, '0');
INSERT INTO `dict` VALUES ('d2aaba42-bb91-11e6-acae-74d02b1a2f8d', '4999-5999', null, '9e5d6e4e-bb91-11e6-acae-74d02b1a2f8d', 'pay_gt4999lt5999', '20', '', '10000', '2016-12-06 16:56:11', null, null, '0');
INSERT INTO `dict` VALUES ('d5921b67-bb90-11e6-acae-74d02b1a2f8d', '10年以上', null, 'b25901cf-bb90-11e6-acae-74d02b1a2f8d', 'work_life_10', '40', '', '10000', '2016-12-06 16:49:06', null, null, '0');
INSERT INTO `dict` VALUES ('d6f0e236-bb82-11e6-acae-74d02b1a2f8d', '一年', null, '10be0f6b-bb82-11e6-acae-74d02b1a2f8d', 'period_oneyear', '20', '', '10000', '2016-12-06 15:08:55', null, null, '0');
INSERT INTO `dict` VALUES ('dcfe7d3f-bb91-11e6-acae-74d02b1a2f8d', '5999-9999', null, '9e5d6e4e-bb91-11e6-acae-74d02b1a2f8d', 'pay_gt5999lt9999', '30', '', '10000', '2016-12-06 16:56:28', null, null, '0');
INSERT INTO `dict` VALUES ('e0b3bc73-bb82-11e6-acae-74d02b1a2f8d', '长期', null, '10be0f6b-bb82-11e6-acae-74d02b1a2f8d', 'period_long', '30', '', '10000', '2016-12-06 15:09:12', null, null, '0');
INSERT INTO `dict` VALUES ('e2d33ab3-cb72-11e6-8856-74d02b1a2f8d', '电话面试', null, 'd8e8fd10-cb72-11e6-8856-74d02b1a2f8d', 'dhms', '10', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2016-12-26 21:55:15', null, null, '0');
INSERT INTO `dict` VALUES ('e7e15156-bb91-11e6-acae-74d02b1a2f8d', '9999以上', null, '9e5d6e4e-bb91-11e6-acae-74d02b1a2f8d', 'pay_gt9999', '40', '', '10000', '2016-12-06 16:56:47', null, null, '0');
INSERT INTO `dict` VALUES ('e89870a7-cb72-11e6-8856-74d02b1a2f8d', '现场面试', null, 'd8e8fd10-cb72-11e6-8856-74d02b1a2f8d', 'xcms', '20', '', '206c7f24-cb1e-11e6-8856-74d02b1a2f8d', '2016-12-26 21:55:25', null, null, '0');
INSERT INTO `dict` VALUES ('ef150615-ce40-11e6-859c-74d02b1a2f8d', '面试通过', null, '54741f08-ce3f-11e6-859c-74d02b1a2f8d', 'deliver_interviewtg', '25', '', '0531f914-c6bd-11e6-8535-74d02b1a2f8d', '2016-12-30 11:35:06', '0531f914-c6bd-11e6-8535-74d02b1a2f8d', '2016-12-30 11:35:15', '0');
INSERT INTO `dict` VALUES ('effc8ae1-c5f3-11e6-b508-74d02b1a2f8d', '国有企业', null, '8683639c-c5f3-11e6-b508-74d02b1a2f8d', 'guoyou', '10', '可以分为国有独资公司、国有控股公司和国有参股公司。', '10000', '2016-12-19 22:03:53', '10000', '2016-12-19 22:05:23', '0');
INSERT INTO `dict` VALUES ('ff41e879-bb82-11e6-acae-74d02b1a2f8d', '初级', null, '08e65866-bb82-11e6-acae-74d02b1a2f8d', 'level_short', '10', '', '10000', '2016-12-06 15:10:03', null, null, '0');
