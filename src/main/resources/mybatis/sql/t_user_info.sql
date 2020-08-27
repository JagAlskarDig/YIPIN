/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : yipin

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2020-08-27 17:03:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id号',
  `userNumber` varchar(30) NOT NULL COMMENT '用户编号',
  `userName` varchar(255) NOT NULL COMMENT '用户昵称',
  `userDistrict` varchar(10) NOT NULL COMMENT '用户所在区服',
  `powerNum` varchar(50) NOT NULL COMMENT '用户总势力',
  `userVipLevel` varchar(5) DEFAULT NULL COMMENT '用户vip等级',
  `doormanNum` varchar(5) NOT NULL COMMENT '用户门客数量',
  `unionName` varchar(50) DEFAULT NULL COMMENT '用户所在联盟名称',
  `unionLeve` varchar(5) DEFAULT NULL COMMENT '用户联盟等级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user_info
-- ----------------------------
INSERT INTO `t_user_info` VALUES ('2', '52036925', '戰②⑨①⚔千叶千叶', '291', '16.452亿', '7', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('3', '52041259', '戰②⑨①⚔非凡', '291', '15.261亿', '7', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('4', '51997312', '戰②⑨①⚔复仇', '291', '14.859亿', '7', '31', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('5', '51983193', '戰②⑨①⚔瓜皮', '291', '7.415亿', '4', '26', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('6', '52550615', '过客、', '291', '7.917亿', '7', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('7', '51980756', '戰②⑨①⚔鲲鹏', '291', '12.653亿', '7', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('8', '51988122', '戰②⑨①⚔龍剑', '291', '10.999亿', '6', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('9', '52041475', '戰②⑨①⚔龍将', '291', '35.05亿', '8', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('10', '52026711', '戰②⑨①⚔魔皇', '291', '8.028亿', '6', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('11', '52034115', '戰②⑨①⚔宁随', '291', '71.304亿', '9', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('12', '52038745', '戰②⑨①⚔霹雳', '291', '8.501亿', '6', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('13', '51995406', '戰②⑨①⚔窮奇', '291', '9.405亿', '7', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('14', '51978416', '戰②⑨①⚔素颜', '291', '12.069亿', '6', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('15', '51982684', '戰②⑨①⚔太极', '291', '16.915亿', '7', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('16', '52044907', '戰②⑨①⚔饕餮', '291', '55.410亿', '9', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('17', '52038334', '戰②⑨①⚔梼杌', '291', '22.303亿', '8', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('18', '52010106', '戰②⑨①⚔天诚', '291', '16.645亿', '7', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('19', '52006480', '戰②⑨①⚔武魂', '291', '7.973亿', '6', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('20', '52984670', '戰②⑨①⚔熙苒', '291', '7.973亿', '5', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('21', '52038057', '戰②⑨①⚔夏侯', '291', '15.868亿', '7', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('22', '52023278', '戰②⑨①⚔秀才', '291', '10.127亿', '6', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('23', '51997078', '戰②⑨①⚔阎皇', '291', '15.168亿', '7', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('24', '51996315', '戰②⑨①⚔楊刚', '291', '20.627亿', '7', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('25', '52039030', '戰②⑨①⚔倚冰', '291', '7.541亿', '7', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('26', '52004507', '戰②⑨①⚔于昌', '291', '9.476亿', '6', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('27', '51981335', '戰②⑨①⚔誉', '291', '8.494亿', '4', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('28', '52004462', '戰②⑨①⚔云清', '291', '22.692亿', '8', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('29', '52006191', '戰②⑨①⚔战魂', '291', '9.952亿', '7', '30', '༄291~战', '10');
INSERT INTO `t_user_info` VALUES ('30', '52005246', '戰②⑨①⚔组长', '291', '7.492亿', '5', '30', '༄291~战', '10');
