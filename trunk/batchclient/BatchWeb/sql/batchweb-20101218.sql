/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50507
Source Host           : localhost:3306
Source Database       : batchweb

Target Server Type    : MYSQL
Target Server Version : 50507
File Encoding         : 65001

Date: 2010-12-18 19:25:20
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bw_access_detail
-- ----------------------------
INSERT INTO `bw_access_detail` VALUES ('1', 'GipsAlpin', '\0', 'http://www.gips-alpin.com', 'http://www.gips-alpin.com/src/en/', '\0', 'dl-proxyall.neusoft.com', '8080', 'dipengfei', 'VicutU19*@', '', 'http://www.gips-alpin.com/src/en/checkpwd.php', 'dipengfei1982@gmail.com', 'Vicutu1982', 'http://www.gips-alpin.com/src/en/members/start.php?userid=9741', '\0', null, null, 'http://www.gips-alpin.com/src/en/listshootings.php', 'J:/sec/cast/GipsAlpin/picture/', '100', '', '30000', '-1', '\0');
INSERT INTO `bw_access_detail` VALUES ('2', 'BeautyLeg', '\0', 'http://www.beautyleg.cc', 'http://www.beautyleg.cc', '\0', 'dl-proxyall.neusoft.com', '8080', 'dipengfei', 'VicutU19*@', '\0', null, null, null, null, '\0', null, null, 'http://www.beautyleg.cc/2005', 'J:/sec/leg/beautyleg/', '100', '', '30000', '-1', '\0');
INSERT INTO `bw_access_detail` VALUES ('3', 'BostonBigPiture', '\0', 'http://www.boston.com/bigpicture/', 'http://www.boston.com/bigpicture/', '\0', 'dl-proxyall.neusoft.com', '8080', 'dipengfei', 'VicutU19*@', '\0', null, null, null, null, '\0', null, null, 'http://www.boston.com/bigpicture/2008/05/', 'D:/test/', '100', '', '30000', '-1', '\0');
INSERT INTO `bw_access_detail` VALUES ('4', 'OrientalCastGirls', '', 'http://www.orientalcastgirls.com/', 'http://www.orientalcastgirls.com/Member/new/', '\0', 'dl-proxyall.neusoft.com', '8080', 'dipengfei', 'Vicutu198@', '\0', null, null, null, null, '', 'dipengfei', 'Nfd[BIJ0HZ7J', 'http://www.orientalcastgirls.com/Member/new/', 'J:/sec/cast/oriental/picture/', '100', '', '30000', '-1', '\0');

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
) ENGINE=InnoDB AUTO_INCREMENT=3359 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bw_download_detail
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=883 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bw_search_status
-- ----------------------------
INSERT INTO `bw_search_status` VALUES ('1', 'GipsAlpin', 'show_month.php?aktMonat=10&txtMonat=October&aktJahr=2010', '2010-11-07 01:37:43');
INSERT INTO `bw_search_status` VALUES ('3', 'BeautyLeg', 'http://www.beautyleg.cc/news', '2010-09-10 06:20:23');
