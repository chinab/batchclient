/*
MySQL Data Transfer
Source Host: localhost
Source Database: mindstatus
Target Host: localhost
Target Database: mindstatus
Date: 2009-7-15 17:46:28
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=gbk ROW_FORMAT=COMPRESSED;

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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=gbk;

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
INSERT INTO `ms_main_menu` VALUES ('1', '系统管理', null);
INSERT INTO `ms_main_menu` VALUES ('2', '员工管理', null);
INSERT INTO `ms_menu` VALUES ('1', '功能菜单', '0', '0', '', null);
INSERT INTO `ms_menu` VALUES ('2', '思想状态管理', '1', '0', '', 'houfei-treeNodePackageIcon');
INSERT INTO `ms_menu` VALUES ('3', '员工信息登记', '2', '1', '/static/view/test.jsp', 'houfei-treeNodeLeafIcon');
INSERT INTO `ms_menu` VALUES ('4', '员工信息维护', '2', '1', '/static/view/test.jsp', 'houfei-treeNodeLeafIcon');
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