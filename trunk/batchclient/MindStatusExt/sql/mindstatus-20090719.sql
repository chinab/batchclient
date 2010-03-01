/*
MySQL Data Transfer
Source Host: localhost
Source Database: mindstatus
Target Host: localhost
Target Database: mindstatus
Date: 2009/7/19 21:44:46
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for ms_employee
-- ----------------------------
DROP TABLE IF EXISTS `ms_employee`;
CREATE TABLE `ms_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL COMMENT '姓名',
  `sex` int(2) DEFAULT NULL COMMENT '性别',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `edu_qualification` int(2) DEFAULT NULL COMMENT '学历',
  `political_appearance` int(2) DEFAULT NULL COMMENT '政治面貌',
  `category` int(2) DEFAULT NULL COMMENT '工作类别(主业、集体、农电、其它)',
  `station` int(2) DEFAULT NULL COMMENT '岗位',
  `kidney` int(2) DEFAULT NULL COMMENT '性格',
  `interest` int(2) DEFAULT NULL COMMENT '爱好',
  `associate_relation` int(2) DEFAULT NULL COMMENT '同事关系',
  `family_relation` int(2) DEFAULT NULL COMMENT '夫妻关系',
  `community` int(2) DEFAULT NULL COMMENT '社会交往程度',
  `award` int(2) DEFAULT NULL COMMENT '奖励状况',
  `study_attitude` int(2) DEFAULT NULL COMMENT '学习态度',
  `responsibility` int(2) DEFAULT NULL COMMENT '履行职责',
  `campaign` int(2) DEFAULT NULL COMMENT '社会活动',
  `economy` int(2) DEFAULT NULL COMMENT '家庭生活状况',
  `after_hours_business` int(2) DEFAULT NULL COMMENT '是否业余经商',
  `morality` int(2) DEFAULT NULL COMMENT '道德水平',
  `security` int(2) DEFAULT NULL COMMENT '安全意识',
  `service_attitude` int(2) DEFAULT NULL COMMENT '服务态度',
  `skill` int(2) DEFAULT NULL COMMENT '岗位技能',
  `performance` int(2) DEFAULT NULL COMMENT '近期综合表现',
  `model_effect` int(2) DEFAULT NULL COMMENT '模范作用发挥',
  `enlighten` int(2) DEFAULT NULL COMMENT '是否需要做工作',
  `mind_pattern` int(2) DEFAULT NULL COMMENT '近期思想工作方式',
  `job_result` int(2) DEFAULT NULL COMMENT '工作结果',
  `promise` int(2) DEFAULT NULL COMMENT '践行承诺',
  `rule` int(2) DEFAULT NULL COMMENT '遵守规定',
  `honesty` int(2) DEFAULT NULL COMMENT '诚实严谨',
  `faith` int(2) DEFAULT NULL COMMENT '忠实守信',
  `duty` int(2) DEFAULT NULL COMMENT '责任意识',
  `technology` int(2) DEFAULT NULL COMMENT '术技',
  `management` int(2) DEFAULT NULL COMMENT '理管',
  `art` int(2) DEFAULT NULL COMMENT '文艺',
  `sports` int(2) DEFAULT NULL COMMENT '体育',
  `memo` varchar(128) DEFAULT NULL COMMENT '注备',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=628 DEFAULT CHARSET=gbk ROW_FORMAT=COMPRESSED;

-- ----------------------------
-- Table structure for ms_main_menu
-- ----------------------------
DROP TABLE IF EXISTS `ms_main_menu`;
CREATE TABLE `ms_main_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(256) DEFAULT NULL,
  `memo` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for ms_menu
-- ----------------------------
DROP TABLE IF EXISTS `ms_menu`;
CREATE TABLE `ms_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增列,主键',
  `text` varchar(100) DEFAULT NULL COMMENT '关键字名(节点名)',
  `parent_id` int(11) DEFAULT NULL COMMENT '父节点的编号',
  `leaf` tinyint(1) DEFAULT NULL COMMENT '否是为叶子节点',
  `link_url` varchar(100) DEFAULT NULL COMMENT '链接的url',
  `iconCls` varchar(100) DEFAULT NULL COMMENT '图标样式',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for ms_prop_list
-- ----------------------------
DROP TABLE IF EXISTS `ms_prop_list`;
CREATE TABLE `ms_prop_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prop_type_id` int(11) DEFAULT NULL,
  `prop_list_id` int(11) DEFAULT NULL,
  `prop_list_name` varchar(128) DEFAULT NULL,
  `memo` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for ms_prop_type
-- ----------------------------
DROP TABLE IF EXISTS `ms_prop_type`;
CREATE TABLE `ms_prop_type` (
  `prop_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `prop_type_name` varchar(128) DEFAULT NULL,
  `prop_type_label` varchar(256) DEFAULT NULL,
  `memo` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`prop_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=gbk ROW_FORMAT=REDUNDANT;

-- ----------------------------
-- Table structure for ms_sub_menu
-- ----------------------------
DROP TABLE IF EXISTS `ms_sub_menu`;
CREATE TABLE `ms_sub_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `main_menu_id` int(11) NOT NULL,
  `parent_menu_id` int(11) NOT NULL,
  `title` varchar(256) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `memo` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Table structure for ms_user
-- ----------------------------
DROP TABLE IF EXISTS `ms_user`;
CREATE TABLE `ms_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_name` varchar(128) DEFAULT NULL COMMENT '姓名',
  `real_name` varchar(128) DEFAULT NULL,
  `sex` int(2) DEFAULT '0' COMMENT '性别',
  `password` varchar(256) DEFAULT NULL COMMENT '密码',
  `reg_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `login_times` int(5) DEFAULT '0' COMMENT '登录次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk ROW_FORMAT=COMPRESSED;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `ms_employee` VALUES ('1', '孙燕姿', '2', '1985-09-01 00:00:00', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '1', '1', '2', '2', '1', '1', '1', null, null, null, null, '');
INSERT INTO `ms_employee` VALUES ('2', '张韶涵', '2', '1983-09-01 00:00:00', '1', '1', '3', '2', '1', '1', '2', '1', '2', '1', '1', '1', '2', '2', '1', '2', '2', '2', '2', '2', '2', '2', '1', '1', '1', '1', '1', '1', '1', null, null, null, null, '');
INSERT INTO `ms_employee` VALUES ('3', '郭采洁', '2', '1984-09-01 00:00:00', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('4', '周杰伦', '1', '1985-09-01 00:00:00', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('5', '潘玮柏', '1', '1982-09-01 00:00:00', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('7', '梁静茹', '2', '1977-04-09 00:00:00', '2', '1', '2', '1', '1', '1', '2', '1', '2', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '3', '2', '2', '2', '3', '3', '3', '2', '1', null, null, null, null, '');
INSERT INTO `ms_employee` VALUES ('8', '刘德华', '1', '1986-04-12 00:00:00', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '1', '2', '1', '1', '1', '1', '1', '1', '99', '1', null, null, null, null, null, null, null, null, null, '');
INSERT INTO `ms_employee` VALUES ('9', '张学友', '1', '1966-04-12 00:00:00', '3', '3', '1', '1', '2', '2', '1', '2', '3', '4', '2', '2', '1', '2', '1', '2', '2', '2', '2', '1', '1', '2', '2', '1', '3', '3', '1', '1', '1', null, '1', '1', '2', '雪狼湖');
INSERT INTO `ms_employee` VALUES ('10', '郭富城', '1', '1964-04-12 00:00:00', '3', '2', '2', '1', '2', '3', '1', '1', '2', '4', '2', '2', '2', '2', '1', '2', '2', '2', '2', '2', '1', '2', '1', '1', '2', '2', '1', '3', '1', '1', '1', '2', '2', '');
INSERT INTO `ms_employee` VALUES ('11', '林志玲', '2', '1980-04-12 00:00:00', '1', '2', '2', '2', '2', '2', '1', '2', '1', '2', '2', '2', '1', '2', '2', '2', '1', '1', '2', '2', '1', '1', '1', '1', '2', '2', '2', '2', '1', '2', '2', '3', '2', '林志玲');
INSERT INTO `ms_employee` VALUES ('19', '李慧珍', '2', '1977-04-12 00:00:00', '6', '1', '1', '1', '1', '1', '1', '1', '2', '4', '1', '3', '2', '2', '2', '2', '1', '2', '1', '1', '2', '1', '4', '1', '2', '1', '1', '1', '2', '3', '2', '1', '1', '寻找李慧珍');
INSERT INTO `ms_employee` VALUES ('21', '古天乐', '1', '1981-07-03 00:00:00', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', '1', '1', null, null, null, null, null, null, null, null, null, '');
INSERT INTO `ms_employee` VALUES ('22', '陈奕迅', '1', '1979-04-12 00:00:00', '1', '1', '3', '2', '1', '2', '1', '2', '1', '1', '1', '2', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '1', '2', '1', '1', '1', '2', '2', '1', '1', '1', '');
INSERT INTO `ms_employee` VALUES ('23', '张惠妹', '2', '1982-07-03 00:00:00', '1', '1', '2', '2', '2', '3', '1', '1', '3', '4', '1', '1', '1', '1', '1', '2', '3', '3', '1', '1', '1', '1', '1', '1', '2', '1', '2', '1', '1', '1', '2', '1', '2', '');
INSERT INTO `ms_employee` VALUES ('24', '王菲', '1', '1977-04-09 00:00:00', '1', '1', '2', '3', '1', '1', '1', '2', '1', '1', '2', '1', '2', '2', '2', '2', '1', '2', '2', '2', '2', '2', '1', '1', '2', '2', '2', '1', '2', null, null, null, null, '王菲');
INSERT INTO `ms_employee` VALUES ('25', '杨丞琳', '2', '1983-04-14 00:00:00', '1', '2', '1', '1', '1', '1', '2', '3', '1', '1', '2', '2', '1', '1', '2', '1', '2', '3', '1', '1', '2', '2', '1', '1', '2', '1', '1', '1', '2', '2', '1', '1', '3', '');
INSERT INTO `ms_employee` VALUES ('27', '芮铁柱', '1', '1978-04-17 00:00:00', '7', '3', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '好');
INSERT INTO `ms_employee` VALUES ('328', 'z0', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('329', 'z1', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('330', 'z2', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('331', 'z3', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('332', 'z4', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('333', 'z5', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('334', 'z6', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('335', 'z7', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('336', 'z8', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('337', 'z9', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('338', 'z10', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('339', 'z11', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('340', 'z12', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('341', 'z13', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('342', 'z14', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('343', 'z15', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('344', 'z16', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('345', 'z17', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('346', 'z18', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('347', 'z19', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('348', 'z20', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('349', 'z21', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('350', 'z22', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('351', 'z23', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('352', 'z24', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('353', 'z25', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('354', 'z26', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('355', 'z27', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('356', 'z28', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('357', 'z29', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('358', 'z30', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('359', 'z31', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('360', 'z32', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('361', 'z33', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('362', 'z34', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('363', 'z35', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('364', 'z36', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('365', 'z37', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('366', 'z38', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('367', 'z39', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('368', 'z40', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('369', 'z41', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('370', 'z42', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('371', 'z43', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('372', 'z44', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('373', 'z45', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('374', 'z46', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('375', 'z47', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('376', 'z48', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('377', 'z49', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('378', 'z50', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('379', 'z51', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('380', 'z52', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('381', 'z53', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('382', 'z54', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('383', 'z55', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('384', 'z56', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('385', 'z57', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('386', 'z58', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('387', 'z59', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('388', 'z60', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('389', 'z61', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('390', 'z62', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('391', 'z63', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('392', 'z64', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('393', 'z65', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('394', 'z66', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('395', 'z67', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('396', 'z68', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('397', 'z69', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('398', 'z70', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('399', 'z71', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('400', 'z72', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('401', 'z73', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('402', 'z74', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('403', 'z75', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('404', 'z76', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('405', 'z77', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('406', 'z78', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('407', 'z79', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('408', 'z80', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('409', 'z81', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('410', 'z82', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('411', 'z83', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('412', 'z84', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('413', 'z85', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('414', 'z86', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('415', 'z87', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('416', 'z88', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('417', 'z89', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('418', 'z90', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('419', 'z91', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('420', 'z92', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('421', 'z93', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('422', 'z94', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('423', 'z95', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('424', 'z96', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('425', 'z97', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('426', 'z98', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('427', 'z99', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('428', 'z100', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('429', 'z101', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('430', 'z102', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('431', 'z103', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('432', 'z104', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('433', 'z105', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('434', 'z106', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('435', 'z107', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('436', 'z108', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('437', 'z109', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('438', 'z110', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('439', 'z111', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('440', 'z112', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('441', 'z113', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('442', 'z114', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('443', 'z115', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('444', 'z116', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('445', 'z117', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('446', 'z118', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('447', 'z119', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('448', 'z120', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('449', 'z121', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('450', 'z122', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('451', 'z123', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('452', 'z124', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('453', 'z125', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('454', 'z126', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('455', 'z127', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('456', 'z128', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('457', 'z129', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('458', 'z130', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('459', 'z131', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('460', 'z132', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('461', 'z133', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('462', 'z134', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('463', 'z135', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('464', 'z136', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('465', 'z137', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('466', 'z138', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('467', 'z139', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('468', 'z140', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('469', 'z141', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('470', 'z142', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('471', 'z143', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('472', 'z144', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('473', 'z145', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('474', 'z146', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('475', 'z147', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('476', 'z148', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('477', 'z149', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('478', 'z150', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('479', 'z151', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('480', 'z152', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('481', 'z153', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('482', 'z154', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('483', 'z155', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('484', 'z156', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('485', 'z157', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('486', 'z158', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('487', 'z159', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('488', 'z160', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('489', 'z161', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('490', 'z162', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('491', 'z163', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('492', 'z164', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('493', 'z165', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('494', 'z166', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('495', 'z167', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('496', 'z168', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('497', 'z169', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('498', 'z170', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('499', 'z171', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('500', 'z172', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('501', 'z173', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('502', 'z174', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('503', 'z175', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('504', 'z176', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('505', 'z177', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('506', 'z178', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('507', 'z179', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('508', 'z180', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('509', 'z181', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('510', 'z182', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('511', 'z183', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('512', 'z184', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('513', 'z185', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('514', 'z186', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('515', 'z187', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('516', 'z188', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('517', 'z189', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('518', 'z190', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('519', 'z191', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('520', 'z192', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('521', 'z193', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('522', 'z194', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('523', 'z195', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('524', 'z196', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('525', 'z197', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('526', 'z198', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('527', 'z199', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('528', 'z200', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('529', 'z201', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('530', 'z202', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('531', 'z203', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('532', 'z204', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('533', 'z205', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('534', 'z206', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('535', 'z207', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('536', 'z208', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('537', 'z209', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('538', 'z210', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('539', 'z211', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('540', 'z212', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('541', 'z213', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('542', 'z214', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('543', 'z215', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('544', 'z216', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('545', 'z217', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('546', 'z218', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('547', 'z219', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('548', 'z220', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('549', 'z221', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('550', 'z222', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('551', 'z223', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('552', 'z224', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('553', 'z225', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('554', 'z226', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('555', 'z227', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('556', 'z228', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('557', 'z229', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('558', 'z230', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('559', 'z231', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('560', 'z232', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('561', 'z233', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('562', 'z234', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('563', 'z235', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('564', 'z236', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('565', 'z237', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('566', 'z238', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('567', 'z239', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('568', 'z240', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('569', 'z241', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('570', 'z242', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('571', 'z243', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('572', 'z244', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('573', 'z245', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('574', 'z246', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('575', 'z247', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('576', 'z248', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('577', 'z249', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('578', 'z250', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('579', 'z251', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('580', 'z252', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('581', 'z253', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('582', 'z254', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('583', 'z255', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('584', 'z256', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('585', 'z257', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('586', 'z258', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('587', 'z259', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('588', 'z260', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('589', 'z261', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('590', 'z262', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('591', 'z263', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('592', 'z264', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('593', 'z265', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('594', 'z266', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('595', 'z267', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('596', 'z268', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('597', 'z269', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('598', 'z270', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('599', 'z271', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('600', 'z272', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('601', 'z273', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('602', 'z274', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('603', 'z275', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('604', 'z276', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('605', 'z277', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('606', 'z278', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('607', 'z279', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('608', 'z280', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('609', 'z281', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('610', 'z282', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('611', 'z283', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('612', 'z284', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('613', 'z285', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('614', 'z286', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('615', 'z287', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('616', 'z288', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('617', 'z289', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('618', 'z290', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('619', 'z291', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('620', 'z292', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('621', 'z293', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('622', 'z294', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('623', 'z295', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('624', 'z296', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('625', 'z297', '2', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('626', 'z298', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_employee` VALUES ('627', 'z299', '1', '2009-07-19 00:00:00', null, null, null, null, null, null, null, null, '2', null, null, null, '1', null, null, null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `ms_main_menu` VALUES ('1', '系统管理', null);
INSERT INTO `ms_main_menu` VALUES ('2', '员工管理', null);
INSERT INTO `ms_menu` VALUES ('1', '功能菜单', '0', '0', '', null);
INSERT INTO `ms_menu` VALUES ('2', '思想状态管理', '1', '0', '', 'houfei-treeNodePackageIcon');
INSERT INTO `ms_menu` VALUES ('3', '员工信息登记', '2', '1', '/employee/showAdd.do', 'houfei-treeNodeLeafIcon');
INSERT INTO `ms_menu` VALUES ('4', '员工信息维护', '2', '1', '/employee/showEdit.do', 'houfei-treeNodeLeafIcon');
INSERT INTO `ms_menu` VALUES ('5', '生理周期管理', '1', '0', '', 'houfei-treeNodePackageIcon');
INSERT INTO `ms_menu` VALUES ('6', '生理周期预测', '5', '1', '/static/view/test.jsp', 'houfei-treeNodeLeafIcon');
INSERT INTO `ms_prop_list` VALUES ('1', '1', '1', '男', null);
INSERT INTO `ms_prop_list` VALUES ('2', '1', '2', '女', null);
INSERT INTO `ms_prop_list` VALUES ('3', '2', '1', '小学', null);
INSERT INTO `ms_prop_list` VALUES ('4', '2', '2', '初中', null);
INSERT INTO `ms_prop_list` VALUES ('5', '2', '3', '技校', null);
INSERT INTO `ms_prop_list` VALUES ('6', '2', '4', '高中', null);
INSERT INTO `ms_prop_list` VALUES ('7', '2', '5', '中专', null);
INSERT INTO `ms_prop_list` VALUES ('8', '2', '6', '大学专科', null);
INSERT INTO `ms_prop_list` VALUES ('9', '2', '7', '大学本科', null);
INSERT INTO `ms_prop_list` VALUES ('10', '2', '8', '研究生', null);
INSERT INTO `ms_prop_list` VALUES ('11', '3', '1', '群众', null);
INSERT INTO `ms_prop_list` VALUES ('12', '3', '2', '团员', null);
INSERT INTO `ms_prop_list` VALUES ('13', '3', '3', '党员', null);
INSERT INTO `ms_prop_list` VALUES ('14', '4', '1', '主业', null);
INSERT INTO `ms_prop_list` VALUES ('15', '4', '2', '集体', null);
INSERT INTO `ms_prop_list` VALUES ('16', '4', '3', '农电', null);
INSERT INTO `ms_prop_list` VALUES ('17', '4', '99', '其它', null);
INSERT INTO `ms_prop_list` VALUES ('18', '5', '1', '管理', null);
INSERT INTO `ms_prop_list` VALUES ('19', '5', '2', '班组长', null);
INSERT INTO `ms_prop_list` VALUES ('20', '5', '3', '技术员', null);
INSERT INTO `ms_prop_list` VALUES ('21', '5', '4', '一般工人', null);
INSERT INTO `ms_prop_list` VALUES ('22', '6', '1', '外向', null);
INSERT INTO `ms_prop_list` VALUES ('23', '6', '2', '内向', null);
INSERT INTO `ms_prop_list` VALUES ('24', '6', '3', '综合', null);
INSERT INTO `ms_prop_list` VALUES ('25', '7', '1', '唱歌', null);
INSERT INTO `ms_prop_list` VALUES ('26', '7', '2', '跳舞', null);
INSERT INTO `ms_prop_list` VALUES ('27', '7', '3', '足球', null);
INSERT INTO `ms_prop_list` VALUES ('28', '7', '4', '乒乓球', null);
INSERT INTO `ms_prop_list` VALUES ('29', '7', '5', '台球', null);
INSERT INTO `ms_prop_list` VALUES ('30', '7', '6', '篮球', null);
INSERT INTO `ms_prop_list` VALUES ('31', '7', '7', '跑', null);
INSERT INTO `ms_prop_list` VALUES ('32', '7', '8', '跳', null);
INSERT INTO `ms_prop_list` VALUES ('33', '7', '99', '其它', null);
INSERT INTO `ms_prop_list` VALUES ('34', '8', '1', '好', null);
INSERT INTO `ms_prop_list` VALUES ('35', '8', '2', '一般', null);
INSERT INTO `ms_prop_list` VALUES ('36', '8', '3', '差', null);
INSERT INTO `ms_prop_list` VALUES ('37', '9', '1', '好', null);
INSERT INTO `ms_prop_list` VALUES ('38', '9', '2', '一般', null);
INSERT INTO `ms_prop_list` VALUES ('39', '9', '3', '差', null);
INSERT INTO `ms_prop_list` VALUES ('40', '10', '1', '多', null);
INSERT INTO `ms_prop_list` VALUES ('41', '10', '2', '少', null);
INSERT INTO `ms_prop_list` VALUES ('42', '10', '3', '无', null);
INSERT INTO `ms_prop_list` VALUES ('43', '11', '1', '劳模', null);
INSERT INTO `ms_prop_list` VALUES ('44', '11', '2', '先进', null);
INSERT INTO `ms_prop_list` VALUES ('45', '11', '3', '积极分子', null);
INSERT INTO `ms_prop_list` VALUES ('46', '11', '4', '忠诚企业先进', null);
INSERT INTO `ms_prop_list` VALUES ('47', '11', '5', '成果', null);
INSERT INTO `ms_prop_list` VALUES ('48', '11', '6', '论文', null);
INSERT INTO `ms_prop_list` VALUES ('49', '11', '99', '其它', null);
INSERT INTO `ms_prop_list` VALUES ('50', '12', '1', '主动', null);
INSERT INTO `ms_prop_list` VALUES ('51', '12', '2', '被动', null);
INSERT INTO `ms_prop_list` VALUES ('52', '13', '1', '好', null);
INSERT INTO `ms_prop_list` VALUES ('53', '13', '2', '一般', null);
INSERT INTO `ms_prop_list` VALUES ('54', '13', '3', '差', null);
INSERT INTO `ms_prop_list` VALUES ('55', '14', '1', '好', null);
INSERT INTO `ms_prop_list` VALUES ('56', '14', '2', '一般', null);
INSERT INTO `ms_prop_list` VALUES ('57', '14', '3', '差', null);
INSERT INTO `ms_prop_list` VALUES ('58', '15', '1', '积极', null);
INSERT INTO `ms_prop_list` VALUES ('59', '15', '2', '一般', null);
INSERT INTO `ms_prop_list` VALUES ('60', '15', '3', '差', null);
INSERT INTO `ms_prop_list` VALUES ('61', '16', '1', '好', null);
INSERT INTO `ms_prop_list` VALUES ('62', '16', '2', '一般', null);
INSERT INTO `ms_prop_list` VALUES ('63', '16', '3', '差', null);
INSERT INTO `ms_prop_list` VALUES ('64', '17', '1', '有', null);
INSERT INTO `ms_prop_list` VALUES ('65', '17', '2', '无', null);
INSERT INTO `ms_prop_list` VALUES ('66', '18', '1', '专业', null);
INSERT INTO `ms_prop_list` VALUES ('67', '18', '2', '管理', null);
INSERT INTO `ms_prop_list` VALUES ('68', '18', '3', '一般从业', null);
INSERT INTO `ms_prop_list` VALUES ('69', '19', '1', '高', null);
INSERT INTO `ms_prop_list` VALUES ('70', '19', '2', '一般', null);
INSERT INTO `ms_prop_list` VALUES ('71', '19', '3', '差', null);
INSERT INTO `ms_prop_list` VALUES ('72', '20', '1', '强', null);
INSERT INTO `ms_prop_list` VALUES ('73', '20', '2', '一般', null);
INSERT INTO `ms_prop_list` VALUES ('74', '20', '3', '差', null);
INSERT INTO `ms_prop_list` VALUES ('75', '21', '1', '好', null);
INSERT INTO `ms_prop_list` VALUES ('76', '21', '2', '一般', null);
INSERT INTO `ms_prop_list` VALUES ('77', '21', '3', '差', null);
INSERT INTO `ms_prop_list` VALUES ('78', '22', '1', '好', null);
INSERT INTO `ms_prop_list` VALUES ('79', '22', '2', '一般', null);
INSERT INTO `ms_prop_list` VALUES ('80', '22', '3', '差', null);
INSERT INTO `ms_prop_list` VALUES ('81', '23', '1', '好', null);
INSERT INTO `ms_prop_list` VALUES ('82', '23', '2', '一般', null);
INSERT INTO `ms_prop_list` VALUES ('83', '23', '3', '差', null);
INSERT INTO `ms_prop_list` VALUES ('84', '24', '1', '好', null);
INSERT INTO `ms_prop_list` VALUES ('85', '24', '2', '一般', null);
INSERT INTO `ms_prop_list` VALUES ('86', '24', '3', '差', null);
INSERT INTO `ms_prop_list` VALUES ('87', '25', '1', '是', null);
INSERT INTO `ms_prop_list` VALUES ('88', '25', '2', '否', null);
INSERT INTO `ms_prop_list` VALUES ('89', '26', '1', '谈心', null);
INSERT INTO `ms_prop_list` VALUES ('90', '26', '2', '教育', null);
INSERT INTO `ms_prop_list` VALUES ('91', '26', '3', '引导', null);
INSERT INTO `ms_prop_list` VALUES ('92', '26', '4', '劝慰', null);
INSERT INTO `ms_prop_list` VALUES ('93', '26', '5', '关爱', null);
INSERT INTO `ms_prop_list` VALUES ('94', '26', '6', '培训', null);
INSERT INTO `ms_prop_list` VALUES ('95', '26', '7', '技能竞赛', null);
INSERT INTO `ms_prop_list` VALUES ('96', '26', '8', '提醒', null);
INSERT INTO `ms_prop_list` VALUES ('97', '26', '9', '救助', null);
INSERT INTO `ms_prop_list` VALUES ('98', '26', '10', '表扬', null);
INSERT INTO `ms_prop_list` VALUES ('99', '26', '11', '奖励', null);
INSERT INTO `ms_prop_list` VALUES ('100', '26', '12', '宣传', null);
INSERT INTO `ms_prop_list` VALUES ('101', '26', '13', '满足需求', null);
INSERT INTO `ms_prop_list` VALUES ('102', '26', '99', '其它', null);
INSERT INTO `ms_prop_list` VALUES ('103', '27', '1', '完成', null);
INSERT INTO `ms_prop_list` VALUES ('104', '27', '2', '待办', null);
INSERT INTO `ms_prop_list` VALUES ('105', '28', '5', '5', null);
INSERT INTO `ms_prop_list` VALUES ('106', '28', '10', '10', null);
INSERT INTO `ms_prop_list` VALUES ('107', '28', '20', '20', null);
INSERT INTO `ms_prop_list` VALUES ('108', '28', '50', '50', null);
INSERT INTO `ms_prop_list` VALUES ('109', '28', '100', '100', null);
INSERT INTO `ms_prop_list` VALUES ('110', '29', '1', '好', null);
INSERT INTO `ms_prop_list` VALUES ('111', '29', '2', '一般', null);
INSERT INTO `ms_prop_list` VALUES ('112', '29', '3', '差', null);
INSERT INTO `ms_prop_list` VALUES ('113', '30', '1', '好', null);
INSERT INTO `ms_prop_list` VALUES ('114', '30', '2', '一般', null);
INSERT INTO `ms_prop_list` VALUES ('115', '30', '3', '差', null);
INSERT INTO `ms_prop_list` VALUES ('116', '31', '1', '好', null);
INSERT INTO `ms_prop_list` VALUES ('117', '31', '2', '一般', null);
INSERT INTO `ms_prop_list` VALUES ('118', '31', '3', '差', null);
INSERT INTO `ms_prop_list` VALUES ('119', '32', '1', '好', null);
INSERT INTO `ms_prop_list` VALUES ('120', '32', '2', '一般', null);
INSERT INTO `ms_prop_list` VALUES ('121', '32', '3', '差', null);
INSERT INTO `ms_prop_list` VALUES ('122', '33', '1', '好', null);
INSERT INTO `ms_prop_list` VALUES ('123', '33', '2', '一般', null);
INSERT INTO `ms_prop_list` VALUES ('124', '33', '3', '差', null);
INSERT INTO `ms_prop_list` VALUES ('125', '34', '1', '软件开发', null);
INSERT INTO `ms_prop_list` VALUES ('126', '34', '2', '计量技术', null);
INSERT INTO `ms_prop_list` VALUES ('127', '34', '3', '专题攻关', null);
INSERT INTO `ms_prop_list` VALUES ('128', '35', '1', '组织协调', null);
INSERT INTO `ms_prop_list` VALUES ('129', '35', '2', '改进创新', null);
INSERT INTO `ms_prop_list` VALUES ('130', '36', '1', '唱歌', null);
INSERT INTO `ms_prop_list` VALUES ('131', '36', '2', '乐器', null);
INSERT INTO `ms_prop_list` VALUES ('132', '36', '3', '舞蹈', null);
INSERT INTO `ms_prop_list` VALUES ('133', '37', '1', '中长跑', null);
INSERT INTO `ms_prop_list` VALUES ('134', '37', '2', '游泳', null);
INSERT INTO `ms_prop_list` VALUES ('135', '37', '3', '足球', null);
INSERT INTO `ms_prop_type` VALUES ('1', 'sex', '性别', '');
INSERT INTO `ms_prop_type` VALUES ('2', 'edu_qualification', '学历', '');
INSERT INTO `ms_prop_type` VALUES ('3', 'political_appearance', '政治面貌', null);
INSERT INTO `ms_prop_type` VALUES ('4', 'category', '工作类别', null);
INSERT INTO `ms_prop_type` VALUES ('5', 'station', '岗位', null);
INSERT INTO `ms_prop_type` VALUES ('6', 'kidney', '性格', null);
INSERT INTO `ms_prop_type` VALUES ('7', 'interest', '爱好', null);
INSERT INTO `ms_prop_type` VALUES ('8', 'associate_relation', '同事关系', null);
INSERT INTO `ms_prop_type` VALUES ('9', 'family_relation', '夫妻关系', null);
INSERT INTO `ms_prop_type` VALUES ('10', 'community', '社会交往程度', null);
INSERT INTO `ms_prop_type` VALUES ('11', 'award', '奖励状况', null);
INSERT INTO `ms_prop_type` VALUES ('12', 'study_attitude', '学习态度', null);
INSERT INTO `ms_prop_type` VALUES ('13', 'responsibility', '履行职责', null);
INSERT INTO `ms_prop_type` VALUES ('14', 'discipline', '遵守纪律', null);
INSERT INTO `ms_prop_type` VALUES ('15', 'campaign', '社会活动', null);
INSERT INTO `ms_prop_type` VALUES ('16', 'economy', '家庭生活情况', null);
INSERT INTO `ms_prop_type` VALUES ('17', 'after_hours_business', '是否业余经商', null);
INSERT INTO `ms_prop_type` VALUES ('18', 'work_advice', '人才储备', null);
INSERT INTO `ms_prop_type` VALUES ('19', 'morality', '道德水品', null);
INSERT INTO `ms_prop_type` VALUES ('20', 'security', '安全意识', null);
INSERT INTO `ms_prop_type` VALUES ('21', 'service_attitude', '服务态度', null);
INSERT INTO `ms_prop_type` VALUES ('22', 'skill', '岗位技能', null);
INSERT INTO `ms_prop_type` VALUES ('23', 'performance', '近期综合表现', null);
INSERT INTO `ms_prop_type` VALUES ('24', 'model_effect', '模范作用发挥', null);
INSERT INTO `ms_prop_type` VALUES ('25', 'enlighten', '是否需要做工作', null);
INSERT INTO `ms_prop_type` VALUES ('26', 'mind_pattern', '近期思想工作方式', null);
INSERT INTO `ms_prop_type` VALUES ('27', 'job_result', '工作结果', null);
INSERT INTO `ms_prop_type` VALUES ('28', 'page_size', '分页记录数', null);
INSERT INTO `ms_prop_type` VALUES ('29', 'promise', '践行承诺', null);
INSERT INTO `ms_prop_type` VALUES ('30', 'rule', '遵守规定', null);
INSERT INTO `ms_prop_type` VALUES ('31', 'honesty', '诚实严谨', null);
INSERT INTO `ms_prop_type` VALUES ('32', 'faith', '忠实守信', null);
INSERT INTO `ms_prop_type` VALUES ('33', 'duty', '责任意识', null);
INSERT INTO `ms_prop_type` VALUES ('34', 'technology', '技术', null);
INSERT INTO `ms_prop_type` VALUES ('35', 'management', '管理', null);
INSERT INTO `ms_prop_type` VALUES ('36', 'art', '文艺', null);
INSERT INTO `ms_prop_type` VALUES ('37', 'sports', '体育', null);
INSERT INTO `ms_sub_menu` VALUES ('3', '1', '1', '新建用户', 'http://www.google.cn', null);
INSERT INTO `ms_sub_menu` VALUES ('4', '1', '1', '用户信息维护', 'http://www.google.cn', null);
INSERT INTO `ms_sub_menu` VALUES ('5', '2', '2', '新建员工', 'http://www.google.cn', null);
INSERT INTO `ms_sub_menu` VALUES ('6', '2', '2', '员工信息维护', 'http://www.google.cn', null);
INSERT INTO `ms_user` VALUES ('2', 'manager', '系统管理员', '1', '8$S_EQO{$4GEP+N}', '2008-04-15 11:26:12', '17');
INSERT INTO `ms_user` VALUES ('3', 'admin', '超级用户', '1', ';#3CP+*0MO*0[#<J', '2008-04-15 11:38:37', '59');
INSERT INTO `ms_user` VALUES ('4', 'user', 'user', '1', 'TNCSAF[#U(/`W&4N', '2009-04-17 15:11:38', '3');
