-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: base_manage_system
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT 'ID',
  `username` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `userpassword` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '用户密码',
  `realname` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '用户姓名',
  `mobile` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '用户手机号码',
  `userstatus` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '用户状态，1：正常',
  `credentialssalt` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '加密盐',
  `email` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '用户邮箱',
  `headimage` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '用户头像',
  `create_tm` datetime DEFAULT NULL COMMENT '创建时间',
  `update_tm` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;
-- ----------------------------
-- Table structure for sys_work_day
-- ----------------------------
DROP TABLE IF EXISTS `sys_work_day`;
CREATE TABLE `sys_work_day` (
  `id` varchar(32) NOT NULL,
  `create_tm` datetime DEFAULT NULL,
  `update_tm` datetime DEFAULT NULL,
  `date` varchar(32) DEFAULT NULL COMMENT '日期',
  `is_work_day` tinyint(1) DEFAULT NULL COMMENT '是否工作日',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sys_user`
--