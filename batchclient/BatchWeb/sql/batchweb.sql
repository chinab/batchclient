/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50145
Source Host           : localhost:3306
Source Database       : batchweb

Target Server Type    : MYSQL
Target Server Version : 50145
File Encoding         : 65001

Date: 2010-05-18 01:01:54
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `bw_access_detail`
-- ----------------------------
DROP TABLE IF EXISTS `bw_access_detail`;
CREATE TABLE `bw_access_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(256) NOT NULL COMMENT '称名',
  `availble` bit(1) DEFAULT b'1' COMMENT '否是可用',
  `home_page` varchar(128) NOT NULL COMMENT '主页',
  `base_url` varchar(256) DEFAULT NULL COMMENT '下载跟路径',
  `use_proxy` bit(1) DEFAULT NULL COMMENT '是否使用代理',
  `proxy_host` varchar(128) DEFAULT NULL COMMENT '代理地址',
  `proxy_port` int(11) DEFAULT NULL COMMENT '代理端口',
  `proxy_username` varchar(64) DEFAULT NULL COMMENT '代理用户名',
  `proxy_password` varchar(64) DEFAULT NULL COMMENT '代理地址',
  `use_login` bit(1) DEFAULT NULL COMMENT '是否使用登录',
  `login_url` varchar(256) DEFAULT NULL COMMENT '登录地址',
  `login_username` varchar(64) DEFAULT NULL COMMENT '登录用户名',
  `login_password` varchar(64) DEFAULT NULL COMMENT '登录密码',
  `login_refresh_url` varchar(256) DEFAULT NULL COMMENT '录后登刷新地址',
  `use_authorization` bit(1) DEFAULT NULL COMMENT '否是使用安全',
  `authorization_username` varchar(64) DEFAULT NULL COMMENT '安全验证用户名',
  `authorization_password` varchar(64) DEFAULT NULL COMMENT '全安验证密码',
  `search_url` varchar(256) DEFAULT NULL COMMENT '索搜地址',
  `save_path` varchar(256) DEFAULT NULL COMMENT '存保路径',
  `queue_length` int(11) DEFAULT '100',
  `single_http_client` bit(1) DEFAULT b'1',
  `timeout` int(11) DEFAULT '30000' COMMENT '下载超时(毫秒)',
  `interval` int(11) DEFAULT '-1' COMMENT '下载间隔(毫秒)',
  `replace_exist` bit(1) DEFAULT NULL COMMENT '是否替换已存的文件',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bw_access_detail
-- ----------------------------
INSERT INTO `bw_access_detail` VALUES ('1', 'GipsAlpin', '', 'http://www.gips-alpin.com', 'http://www.gips-alpin.com/src/en/', '\0', 'dl-proxyall.neusoft.com', '8080', 'dipengfei', 'VicutU19*@', '', 'http://www.gips-alpin.com/src/en/checkpwd.php', 'dipengfei1982@gmail.com', 'Vicutu1982', 'http://www.gips-alpin.com/src/en/members/start.php?userid=9741', '\0', null, null, 'http://www.gips-alpin.com/src/en/listshootings.php', 'J:/sec/cast/GipsAlpin/picture/', '100', '', '30000', '-1', '\0');

-- ----------------------------
-- Table structure for `bw_download_detail`
-- ----------------------------
DROP TABLE IF EXISTS `bw_download_detail`;
CREATE TABLE `bw_download_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(128) DEFAULT NULL,
  `real_url` varchar(256) DEFAULT NULL,
  `real_path` varchar(256) DEFAULT NULL,
  `file_length` bigint(20) DEFAULT NULL,
  `length_info` varchar(64) DEFAULT NULL,
  `last_state` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `memo` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=220 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bw_download_detail
-- ----------------------------
INSERT INTO `bw_download_detail` VALUES ('66', 'pic1.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic1.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic1.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('67', 'pic2.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic2.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic2.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('68', 'pic3.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic3.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic3.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('69', 'pic4.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic4.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic4.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('70', 'pic5.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic5.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic5.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('71', 'pic6.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic6.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic6.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('72', 'pic7.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic7.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic7.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('73', 'pic8.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic8.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic8.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('74', 'pic9.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic9.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic9.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('75', 'pic10.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic10.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic10.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('76', 'pic11.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic11.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic11.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('77', 'pic12.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic12.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic12.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('78', 'pic13.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic13.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic13.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('79', 'pic14.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic14.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic14.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('80', 'pic15.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic15.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic15.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('81', 'pic16.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic16.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic16.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('82', 'pic17.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic17.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic17.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('83', 'pic18.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic18.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic18.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('84', 'pic19.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic19.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic19.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('85', 'pic20.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic20.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic20.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('86', 'pic21.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic21.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic21.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('87', 'pic22.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic22.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic22.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('88', 'pic23.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic23.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic23.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('89', 'pic24.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic24.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic24.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('90', 'pic25.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic25.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic25.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('91', 'pic26.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic26.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic26.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('92', 'pic27.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic27.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic27.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('93', 'pic28.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic28.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic28.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('94', 'pic29.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic29.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic29.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('95', 'pic30.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic30.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic30.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('96', 'pic31.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic31.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic31.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('97', 'pic32.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic32.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic32.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('98', 'pic33.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic33.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic33.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('99', 'pic34.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic34.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic34.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('100', 'pic35.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic35.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic35.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('101', 'pic36.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic36.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic36.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('102', 'pic37.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic37.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic37.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('103', 'pic38.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic38.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic38.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('104', 'pic39.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic39.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic39.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('105', 'pic40.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic40.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic40.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('106', 'pic41.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic41.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic41.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('107', 'pic42.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic42.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic42.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('108', 'pic43.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic43.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic43.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('109', 'pic44.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic44.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic44.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('110', 'pic45.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic45.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic45.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('111', 'pic46.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic46.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic46.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('112', 'pic47.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic47.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic47.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('113', 'pic48.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic48.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic48.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('114', 'pic49.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic49.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic49.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('115', 'pic50.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic50.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic50.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('116', 'pic51.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic51.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic51.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('117', 'pic52.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic52.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic52.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('118', 'pic53.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic53.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic53.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('119', 'pic54.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic54.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic54.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('120', 'pic55.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic55.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic55.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('121', 'pic56.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic56.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic56.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('122', 'pic57.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic57.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic57.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('123', 'pic58.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic58.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic58.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('124', 'pic59.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic59.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic59.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('125', 'pic60.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic60.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic60.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('126', 'pic61.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic61.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic61.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('127', 'pic62.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic62.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic62.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('128', 'pic63.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic63.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic63.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('129', 'pic64.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic64.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic64.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('130', 'pic65.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic65.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic65.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('131', 'pic66.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic66.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic66.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('132', 'pic67.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic67.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic67.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('133', 'pic68.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic68.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic68.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('134', 'pic69.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic69.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic69.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('135', 'pic70.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic70.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic70.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('136', 'pic71.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic71.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic71.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('137', 'pic72.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic72.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic72.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('138', 'pic73.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic73.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic73.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('139', 'pic74.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic74.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic74.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('140', 'pic75.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic75.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic75.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('141', 'pic76.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic76.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic76.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('142', 'pic77.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic77.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic77.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('143', 'pic78.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic78.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic78.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('144', 'pic79.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic79.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic79.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('145', 'pic80.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic80.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic80.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('146', 'pic81.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic81.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic81.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('147', 'pic82.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic82.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic82.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('148', 'pic83.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic83.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic83.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('149', 'pic84.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic84.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic84.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('150', 'pic85.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic85.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic85.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('151', 'pic86.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic86.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic86.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('152', 'pic87.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic87.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic87.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('153', 'pic88.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic88.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic88.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('154', 'pic89.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic89.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic89.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('155', 'pic90.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic90.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic90.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('156', 'pic91.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic91.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic91.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('157', 'pic92.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic92.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic92.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('158', 'pic93.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic93.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic93.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('159', 'pic94.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic94.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic94.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('160', 'pic95.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic95.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic95.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('161', 'pic96.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic96.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic96.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('162', 'pic97.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic97.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic97.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('163', 'pic98.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic98.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic98.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('164', 'pic99.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic99.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic99.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('165', 'pic100.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic100.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic100.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('166', 'pic101.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic101.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic101.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('167', 'pic102.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic102.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic102.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('168', 'pic103.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic103.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic103.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('169', 'pic104.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic104.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic104.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('170', 'pic105.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic105.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic105.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('171', 'pic106.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic106.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic106.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('172', 'pic107.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic107.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic107.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('173', 'pic108.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic108.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic108.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('174', 'pic109.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic109.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic109.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('175', 'pic110.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic110.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic110.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('176', 'pic111.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic111.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic111.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('177', 'pic112.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic112.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic112.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('178', 'pic113.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic113.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic113.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('179', 'pic114.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic114.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic114.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('180', 'pic115.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic115.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic115.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('181', 'pic116.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic116.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic116.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('182', 'pic117.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic117.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic117.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('183', 'pic118.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic118.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic118.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('184', 'pic119.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic119.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic119.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('185', 'pic120.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic120.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic120.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('186', 'pic121.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic121.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic121.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('187', 'pic122.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic122.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic122.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('188', 'pic123.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic123.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic123.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('189', 'pic124.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic124.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic124.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('190', 'pic125.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic125.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic125.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('191', 'pic126.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic126.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic126.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('192', 'pic127.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic127.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic127.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('193', 'pic128.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic128.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic128.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('194', 'pic129.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic129.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic129.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('195', 'pic130.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic130.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic130.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('196', 'pic1.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic1.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic1.jpg', '0', null, '0', null, null);
INSERT INTO `bw_download_detail` VALUES ('197', 'pic1.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic1.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic1.jpg', '156171', '152 KB', '2', null, null);
INSERT INTO `bw_download_detail` VALUES ('198', 'pic2.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic2.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic2.jpg', '154810', '151 KB', '2', null, null);
INSERT INTO `bw_download_detail` VALUES ('199', 'pic3.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic3.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic3.jpg', '146599', '143 KB', '2', null, null);
INSERT INTO `bw_download_detail` VALUES ('200', 'pic4.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic4.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic4.jpg', '152774', '149 KB', '2', null, null);
INSERT INTO `bw_download_detail` VALUES ('201', 'pic5.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic5.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic5.jpg', '0', null, '0', null, null);
INSERT INTO `bw_download_detail` VALUES ('202', 'pic1.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic1.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic1.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('203', 'pic2.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic2.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic2.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('204', 'pic3.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic3.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic3.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('205', 'pic4.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic4.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic4.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('206', 'pic5.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic5.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic5.jpg', '0', null, '5', null, null);
INSERT INTO `bw_download_detail` VALUES ('207', 'pic6.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic6.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic6.jpg', '139252', '135 KB', '2', null, null);
INSERT INTO `bw_download_detail` VALUES ('208', 'pic7.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic7.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic7.jpg', '151660', '148 KB', '2', null, null);
INSERT INTO `bw_download_detail` VALUES ('209', 'pic8.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic8.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic8.jpg', '133768', '130 KB', '2', null, null);
INSERT INTO `bw_download_detail` VALUES ('210', 'pic9.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic9.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic9.jpg', '154221', '150 KB', '2', null, null);
INSERT INTO `bw_download_detail` VALUES ('211', 'pic10.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic10.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic10.jpg', '126102', '123 KB', '2', null, null);
INSERT INTO `bw_download_detail` VALUES ('212', 'pic11.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic11.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic11.jpg', '138235', '134 KB', '2', null, null);
INSERT INTO `bw_download_detail` VALUES ('213', 'pic12.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic12.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic12.jpg', '141264', '137 KB', '2', null, null);
INSERT INTO `bw_download_detail` VALUES ('214', 'pic13.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic13.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic13.jpg', '128501', '125 KB', '2', null, null);
INSERT INTO `bw_download_detail` VALUES ('215', 'pic14.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic14.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic14.jpg', '146561', '143 KB', '2', null, null);
INSERT INTO `bw_download_detail` VALUES ('216', 'pic15.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic15.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic15.jpg', '148674', '145 KB', '2', null, null);
INSERT INTO `bw_download_detail` VALUES ('217', 'pic16.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic16.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic16.jpg', '163987', '160 KB', '2', null, null);
INSERT INTO `bw_download_detail` VALUES ('218', 'pic17.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic17.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic17.jpg', '137287', '134 KB', '2', null, null);
INSERT INTO `bw_download_detail` VALUES ('219', 'pic18.jpg', 'http://www.gips-alpin.com/shoots/pix/09f424a2a0aa7dbfa97503c4b9bbfb07/pic18.jpg', 'J:/sec/cast/GipsAlpin/picture/2010/May/JusztinaBigCastsShoulderSpicaMinervaeingipsenII/pic18.jpg', '0', null, '0', null, null);

-- ----------------------------
-- Table structure for `bw_search_status`
-- ----------------------------
DROP TABLE IF EXISTS `bw_search_status`;
CREATE TABLE `bw_search_status` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `access_name` varchar(256) DEFAULT NULL,
  `last_search_url` varchar(256) DEFAULT NULL,
  `last_search_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bw_search_status
-- ----------------------------
INSERT INTO `bw_search_status` VALUES ('1', 'GipsAlpin', 'show_month.php?aktMonat=5&txtMonat=May&aktJahr=2010', '2010-05-16 16:34:27');
