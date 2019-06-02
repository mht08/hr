/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : hr

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-07-13 09:30:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `employee_number` int(10) DEFAULT NULL,
  `day` date DEFAULT NULL,
  `time_type` enum('上午','下午','加班') DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `start_type` enum('正常','迟到','未签到') DEFAULT '未签到',
  `end_time` time DEFAULT NULL,
  `end_type` enum('正常','早退','未签到') DEFAULT '未签到',
  `work_type` enum('上班','请假') DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES ('1', '1001', '2017-07-07', '下午', '17:01:33', '迟到', '17:25:15', '早退', null, null);
INSERT INTO `attendance` VALUES ('2', '1001', '2017-07-08', '上午', '08:53:43', '正常', '11:20:46', '早退', null, null);
INSERT INTO `attendance` VALUES ('3', '1001', '2017-07-08', '下午', '14:25:17', '正常', null, '未签到', null, null);
INSERT INTO `attendance` VALUES ('4', '1009', '2017-07-10', '上午', '10:29:35', '迟到', null, '未签到', null, null);
INSERT INTO `attendance` VALUES ('5', '1009', '2017-07-10', '下午', '16:42:01', '迟到', '16:42:25', '早退', null, null);
INSERT INTO `attendance` VALUES ('6', '1009', '2017-07-10', '加班', '19:31:46', '正常', null, '未签到', null, null);
INSERT INTO `attendance` VALUES ('7', '1009', '2017-07-11', '上午', '09:21:13', '迟到', null, '未签到', null, null);
INSERT INTO `attendance` VALUES ('8', '1009', '2017-07-12', '上午', '09:09:53', '迟到', null, '未签到', null, null);
INSERT INTO `attendance` VALUES ('9', '1009', '2017-07-12', '下午', '15:31:03', '迟到', null, '未签到', null, null);
INSERT INTO `attendance` VALUES ('10', '1001', '2017-07-12', '下午', '15:34:58', '迟到', null, '未签到', null, null);
INSERT INTO `attendance` VALUES ('11', '1007', '2017-07-12', '下午', '15:51:24', '迟到', null, '未签到', null, null);
INSERT INTO `attendance` VALUES ('12', '1008', '2017-07-12', '下午', '16:48:03', '迟到', null, '未签到', null, null);
INSERT INTO `attendance` VALUES ('13', '1009', '2017-07-12', '加班', '21:02:35', '迟到', null, '未签到', null, null);
INSERT INTO `attendance` VALUES ('14', '1010', '2017-07-12', '加班', '21:24:34', '迟到', null, '未签到', null, null);

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `department_number` int(10) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `manager` varchar(10) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '2001', '急诊科', '王生安', '0923-3456180', '住院楼101', '');
INSERT INTO `department` VALUES ('2', '2002', '骨科', '贺易', '0923-3456324', '门诊楼304', '');
INSERT INTO `department` VALUES ('3', '2003', '内分泌科  ', '周卓浩', '0923-3456909', '门诊楼205', '');
INSERT INTO `department` VALUES ('4', '2004', '神经内科 ', '何刚名', '0923-3456231', '门诊楼109', '');
INSERT INTO `department` VALUES ('5', '2005', '神经外科', '王成文 ', '0923-3456782', '门诊楼102', '');
INSERT INTO `department` VALUES ('6', '2006', '消化内科 ', '严席华', '0923-3456098', '门诊楼201', '');
INSERT INTO `department` VALUES ('7', '2007', '检验科', '云介融 ', '0923-3456143', '医技楼104', '');
INSERT INTO `department` VALUES ('8', '2008', '体检中心 ', '范湖', '0923-3456677', '医技楼203', '');
INSERT INTO `department` VALUES ('9', '2009', '放射科  ', '吴敬序', '0923-3456489', '医技楼305', '');
INSERT INTO `department` VALUES ('10', '2010', '护理部    ', '凌月青', '0923-3456210', '住院楼109', '');
INSERT INTO `department` VALUES ('11', '2011', '康复理疗科 ', '丁频佟', '0923-3456724', '医技楼208', '');
INSERT INTO `department` VALUES ('12', '2012', '药剂科', '王缘', '0923-3456423', '医技楼302', '');
INSERT INTO `department` VALUES ('13', '2013', '人事部', '李烨', '0923-2456123', '办公楼108', '');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `employee_number` int(10) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `gender` enum('男','女') DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `telephone` varchar(15) DEFAULT '',
  `email` varchar(30) DEFAULT '',
  `address` varchar(50) DEFAULT NULL,
  `photo` varchar(50) DEFAULT '',
  `education` varchar(20) DEFAULT '',
  `department_number` int(10) DEFAULT NULL,
  `position_number` int(10) DEFAULT NULL,
  `in_time` date DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `notes` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `department_number` (`department_number`),
  KEY `title_number` (`position_number`),
  KEY `employee_number` (`employee_number`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '1001', 'admin', '男', '1995-10-18', '15678015439', '', '', '', '', '2013', '3009', '2017-02-22', '1001', '');
INSERT INTO `employee` VALUES ('2', '1007', '李烨', '女', '1996-03-04', '18907327612', '', null, '', '', '2001', '3003', '2017-01-10', '1007', '');
INSERT INTO `employee` VALUES ('3', '1008', '刘旭亮', '男', '1995-06-06', '13464238971', '', '', '', '', '2007', '3003', '2017-06-28', '1008', '');
INSERT INTO `employee` VALUES ('4', '1009', '张彤', '男', '1995-09-24', '15810239904', '', '', '', '', '2013', '3009', '2017-02-06', '1009', '');
INSERT INTO `employee` VALUES ('5', '1010', '杨杰', '男', '1995-01-26', '17871239756', '', '', '', '', '2013', '3010', '2017-05-12', '1010', '');
INSERT INTO `employee` VALUES ('6', '1011', '唐治涛', '男', '1995-03-29', '18832013916', '', '河北沧州', null, '大学本科', '2007', '3003', '2017-07-05', 'tzt4', '');
INSERT INTO `employee` VALUES ('7', '1012', '张璐', '男', '1997-03-04', '18832050264', '', '河北张家口', null, '大学本科', '2013', '3009', '2017-07-05', 'zhanglu', '');

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `employee_number` int(10) DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `gender` enum('男','女') DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `telephone` varchar(20) DEFAULT '',
  `email` varchar(30) DEFAULT '',
  `address` varchar(50) DEFAULT '',
  `photo` varchar(50) DEFAULT '',
  `education` varchar(20) DEFAULT '',
  `in_time` date DEFAULT NULL,
  `out_time` date DEFAULT NULL,
  `department_number` int(10) DEFAULT NULL,
  `position_number` int(10) DEFAULT NULL,
  `status` enum('离职','在职','退休') DEFAULT NULL,
  `home` varchar(100) DEFAULT '',
  `notes` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of history
-- ----------------------------
INSERT INTO `history` VALUES ('1', '1001', 'admin', '男', '1995-10-18', '15678015439', '', '', '', '', '2017-02-22', null, '2001', '3002', '在职', '', '');
INSERT INTO `history` VALUES ('2', '1002', '王秀英', '女', '1992-03-08', '15590678821', '', '', '', '', '2011-04-29', '2017-07-04', '2011', '3004', '离职', '', '');
INSERT INTO `history` VALUES ('3', '1003', '李强', '男', '1993-12-22', '18929778634', '', '', '', '', '2010-05-06', '2017-07-05', '2010', '3007', '退休', '', '');
INSERT INTO `history` VALUES ('4', '1004', '刘洋', '男', '1991-07-26', '13807987324', '', '', '', '', '2009-12-23', '2017-07-04', '2009', '3005', '退休', '', '');
INSERT INTO `history` VALUES ('5', '1005', '李敏', '女', '1991-01-03', '13791826142', '', '', '', '', '2010-03-29', '2017-07-05', '2008', '3004', '退休', '', '');
INSERT INTO `history` VALUES ('6', '1006', '王伟 ', '女', '1990-06-12', '13986207926', '', '', '', '', '2010-10-12', '2017-07-06', '2012', '3005', '离职', '', '');
INSERT INTO `history` VALUES ('7', '1007', '李烨', '女', '1996-03-04', '18907327612', '', '', '', '', '2017-01-10', null, '2001', '3003', '离职', '', '');
INSERT INTO `history` VALUES ('8', '1008', '刘旭亮', '男', '1995-06-00', '13464238971', '', '', '', '', '2017-06-28', null, '2002', '3001', '在职', '', '');
INSERT INTO `history` VALUES ('9', '1009', '张彤', '男', '1995-09-24', '15810239904', '', '', '', '', '2017-02-06', null, '2002', '3003', '在职', '', '');
INSERT INTO `history` VALUES ('10', '1010', '杨杰', '男', '1995-01-26', '17871239756', '', '', '', '', '2017-05-12', null, '2003', '3003', '在职', '', '');
INSERT INTO `history` VALUES ('11', '1011', '唐治涛', '男', '1995-03-29', '18832013916', '819564344@qq.com', '河北沧州', '', '大学本科', '2017-07-05', null, '2010', '3006', '在职', '', '');
INSERT INTO `history` VALUES ('12', '1012', '张璐', '男', '1997-03-11', '18832050264', '1215959210@qq.com', '河北省张家口', '', '本科', '2017-07-05', null, '2009', '3004', '在职', '', '');

-- ----------------------------
-- Table structure for lea
-- ----------------------------
DROP TABLE IF EXISTS `lea`;
CREATE TABLE `lea` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `employee_number` int(10) DEFAULT NULL,
  `department_number` int(10) DEFAULT NULL,
  `start_time` date DEFAULT NULL,
  `end_time` date DEFAULT NULL,
  `days` varchar(10) DEFAULT NULL,
  `reason` varchar(100) DEFAULT NULL,
  `type` enum('事假','病假') DEFAULT NULL,
  `manager` varchar(10) DEFAULT NULL,
  `status` enum('已批准','未批准') DEFAULT '未批准',
  `notes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lea
-- ----------------------------
INSERT INTO `lea` VALUES ('1', '1007', '2007', '2017-07-11', '2017-07-12', '1', '家中有事', '事假', null, '未批准', null);
INSERT INTO `lea` VALUES ('2', '1008', '2007', '2017-07-10', '2017-07-12', '2', '偶感风寒', '病假', null, '已批准', null);
INSERT INTO `lea` VALUES ('3', '1011', '2007', '2017-07-11', '2017-07-11', '1', '回家看看', '事假', null, '已批准', null);
INSERT INTO `lea` VALUES ('7', '1008', '2007', '2017-07-14', '2017-07-17', '3', '真的有点事', '事假', null, '已批准', null);
INSERT INTO `lea` VALUES ('8', '1009', '2013', '2017-07-05', '2017-07-06', '1', '回家看看', '事假', null, '已批准', null);
INSERT INTO `lea` VALUES ('9', '1012', '2013', '2017-07-08', '2017-07-08', '1', '摊上事了', '事假', null, '未批准', null);
INSERT INTO `lea` VALUES ('10', '1012', '2013', '2017-07-13', '2017-07-14', '1', '真的有点事', '事假', null, '已批准', null);

-- ----------------------------
-- Table structure for move
-- ----------------------------
DROP TABLE IF EXISTS `move`;
CREATE TABLE `move` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `employee_number` int(10) DEFAULT NULL,
  `before` int(10) DEFAULT NULL,
  `after` int(10) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `manager` varchar(10) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of move
-- ----------------------------
INSERT INTO `move` VALUES ('1', '1011', '2010', '2011', '2017-07-10 20:40:20', '张彤', null);
INSERT INTO `move` VALUES ('3', '1007', '2001', '2007', '2017-07-11 09:53:34', '张彤', null);

-- ----------------------------
-- Table structure for overtime
-- ----------------------------
DROP TABLE IF EXISTS `overtime`;
CREATE TABLE `overtime` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `department_number` int(10) DEFAULT NULL,
  `employee_number` int(10) DEFAULT NULL,
  `day` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of overtime
-- ----------------------------
INSERT INTO `overtime` VALUES ('1', '2007', '1007', '2017-07-12', null, null, null);
INSERT INTO `overtime` VALUES ('2', '2001', '1008', '2017-07-12', null, null, null);
INSERT INTO `overtime` VALUES ('3', '2013', '1012', '2017-07-12', null, null, null);
INSERT INTO `overtime` VALUES ('4', '2003', '1010', '2017-07-12', null, null, null);
INSERT INTO `overtime` VALUES ('8', '2011', '1011', '2017-07-14', null, null, null);

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `position_number` int(10) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `level` enum('部门主任','部门员工','人事部主任','人事部员工') DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `position_number` (`position_number`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES ('1', '3001', '主任医师', '部门主任', '');
INSERT INTO `position` VALUES ('2', '3002', '副主任医师', '部门员工', '');
INSERT INTO `position` VALUES ('3', '3003', '医师', '部门员工', '');
INSERT INTO `position` VALUES ('4', '3004', '主任技师', '部门主任', '');
INSERT INTO `position` VALUES ('5', '3005', '副主任技师', '部门员工', '');
INSERT INTO `position` VALUES ('6', '3006', '技师', '部门员工', '');
INSERT INTO `position` VALUES ('7', '3007', '护士长', '部门主任', '');
INSERT INTO `position` VALUES ('8', '3008', '护士', '部门员工', '');
INSERT INTO `position` VALUES ('9', '3009', '人事部主任', '人事部主任', '');
INSERT INTO `position` VALUES ('10', '3010', '人事部员工', '人事部员工', '');

-- ----------------------------
-- Table structure for rewards_punishment
-- ----------------------------
DROP TABLE IF EXISTS `rewards_punishment`;
CREATE TABLE `rewards_punishment` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `employee_number` int(10) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `reason` varchar(100) DEFAULT NULL,
  `money` float(8,0) DEFAULT NULL,
  `time` datetime(6) DEFAULT NULL,
  `manager` varchar(10) DEFAULT NULL,
  `notes` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `employee_number` (`employee_number`),
  CONSTRAINT `rewards_punishment_ibfk_1` FOREIGN KEY (`employee_number`) REFERENCES `employee` (`employee_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rewards_punishment
-- ----------------------------
