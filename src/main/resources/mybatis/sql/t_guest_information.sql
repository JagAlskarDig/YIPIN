/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : yipin

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2020-08-27 14:49:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_guest_information
-- ----------------------------
DROP TABLE IF EXISTS `t_guest_information`;
CREATE TABLE `t_guest_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id号',
  `userNumber` varchar(30) NOT NULL COMMENT '用户编号',
  `doormanName` varchar(255) NOT NULL COMMENT '门客名称',
  `qualification` varchar(20) NOT NULL COMMENT '门客资质',
  `bloodNum` varchar(50) NOT NULL COMMENT '门客血量',
  `isSuit` varchar(2) NOT NULL COMMENT '是否是套装门客（1五虎、2四奸、3五义、4四杰、5红颜门客）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
