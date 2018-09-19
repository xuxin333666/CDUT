-- MySQL dump 10.13  Distrib 5.6.29, for Win64 (x86_64)
--
-- Host: localhost    Database: cdut
-- ------------------------------------------------------
-- Server version	5.6.29

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

--
-- Table structure for table `t_group`
--

DROP TABLE IF EXISTS `t_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_group` (
  `id` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `group_manager` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `male_count` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `female_count` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `pro_id` varchar(20) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_group`
--

LOCK TABLES `t_group` WRITE;
/*!40000 ALTER TABLE `t_group` DISABLE KEYS */;
INSERT INTO `t_group` VALUES ('20180914094552213428','java1802','2018-05-20 00:00:00','老吴','18','1','01','20180913104230312939'),('20180914101624973113','土木5班','2012-09-01 00:00:00','老胡','28','3','01','20180913104531157311'),('20180914101707378111','会计15班','2018-09-14 00:00:00','徐鑫','10','15','01','20180913104609954598'),('20180918145612609306','快速入门','2018-02-15 00:00:00','徐鑫','40','30','01','20180913110205239500'),('20180918145644766307','进阶培训','2018-06-07 00:00:00','徐鑫','40','30','01','20180913110205239500'),('20180918145708053542','工程地质1班','2018-01-20 00:00:00','胡','30','2','01','20180913110529881321');
/*!40000 ALTER TABLE `t_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_perms`
--

DROP TABLE IF EXISTS `t_perms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_perms` (
  `id` varchar(20) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `icon_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `tag_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `data_url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `p_id` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_perms`
--

LOCK TABLES `t_perms` WRITE;
/*!40000 ALTER TABLE `t_perms` DISABLE KEYS */;
INSERT INTO `t_perms` VALUES ('100','基础数据',NULL,'01','2018-09-19 09:42:36','baseDate:query','main?command=baseDate:query',NULL),('10000','新增',NULL,'03','2018-09-19 14:25:26','pro:add',NULL,'111'),('10001','修改',NULL,'03','2018-09-19 14:26:22','pro:modify',NULL,'111'),('10002','删除',NULL,'03','2018-09-19 14:26:49','pro:del',NULL,'111'),('10003','保存',NULL,'03','2018-09-19 14:27:13','pro:save',NULL,'111'),('10004','启用',NULL,'03','2018-09-19 14:27:33','pro:enable',NULL,'111'),('10005','停用',NULL,'03','2018-09-19 14:28:02','pro:disable',NULL,'111'),('110','基础设置','#icon-users','02','2018-09-19 09:43:12','base:main',NULL,'100'),('111','专业管理',NULL,'02','2018-09-19 09:43:48','professional:main','permissions/professional/mainTable','110'),('112','班级管理',NULL,'02','2018-09-19 09:44:38','group:main','permissions/group/mainTable','110'),('113','学生档案',NULL,'02','2018-09-19 09:45:14','student:main','permissions/student/mainTable','110'),('200','学生日常',NULL,'01','2018-09-19 09:46:14','studentDay:query','main?command=studentDay:query',NULL),('300','学籍管理',NULL,'01','2018-09-19 09:46:59','studentStatus:query','main?command=studentStatus:query',NULL),('310','学生业务','#icon-users','02','2018-09-19 09:47:29','service_student:main','','300'),('311','学生报道',NULL,'02','2018-09-19 09:48:18','student_report:main','permissions/student/student_report','310'),('312','学生注册',NULL,'02','2018-09-19 09:49:10','student_regist:main','permissions/student/student_regist','310'),('320','统计查询','#icon-users','02','2018-09-19 09:50:00','statisticalQuery:main',NULL,'300'),('321','专业人数查询',NULL,'02','2018-09-19 09:51:12','professional_num_statistical:main','permissions/professional/statisticalQuery.jsp','320'),('400','系统管理',NULL,'01','2018-09-19 09:52:01','systemManagement:query','main?command=systemManagement:query',NULL),('410','角色管理','#icon-users','02','2018-09-19 14:33:21','roleManagement:main',NULL,'400'),('411','资源管理',NULL,'02','2018-09-19 14:34:13','permsManagement:main','permissions/perms/mainTable','410'),('412','绑定资源',NULL,'02','2018-09-19 14:35:37','permsToRole:main','permissions/perms/permsToRole','410'),('420','用户管理','#icon-users','02','2018-09-19 14:37:07','userManagement:main',NULL,'400'),('421','查看用户',NULL,'02','2018-09-19 14:37:47','userQuery:main','permissions/user/mainTable','420'),('422','绑定角色',NULL,'02','2018-09-19 14:38:44','roleToUser:main','permissions/user/roleToUser','420');
/*!40000 ALTER TABLE `t_perms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_professional`
--

DROP TABLE IF EXISTS `t_professional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_professional` (
  `id` varchar(20) COLLATE utf8_bin NOT NULL,
  `code` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `name_en` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `eductional_systme` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `total_score` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `teather_count` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` varchar(2) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_professional`
--

LOCK TABLES `t_professional` WRITE;
/*!40000 ALTER TABLE `t_professional` DISABLE KEYS */;
INSERT INTO `t_professional` VALUES ('20180913104230312939','10001','java高级','java','2018-09-13 00:00:00','02','300','30','01'),('20180913104531157311','10000','建筑工程','','2018-09-13 00:00:00','02','300','25','01'),('20180913104609954598','10002','会计学','','2018-09-13 00:00:00','03','450','60','01'),('20180913110205239500','10003','用友培训班','','2018-09-13 00:00:00','01','120','10','01'),('20180913110529881321','10000','岩土工程','','2012-07-20 00:00:00','04','600','50','01'),('20180913110611880825','10002','财务管理','',NULL,'04','','','02');
/*!40000 ALTER TABLE `t_professional` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role` (
  `id` varchar(20) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `desc` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES ('1','管理员','全部权限','2018-09-19 10:29:40'),('2','普通用户','专业，班级，学生，报表查看','2018-09-19 10:30:19');
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_perms`
--

DROP TABLE IF EXISTS `t_role_perms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role_perms` (
  `id` varchar(20) COLLATE utf8_bin NOT NULL,
  `role_id` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `perms_id` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_perms`
--

LOCK TABLES `t_role_perms` WRITE;
/*!40000 ALTER TABLE `t_role_perms` DISABLE KEYS */;
INSERT INTO `t_role_perms` VALUES ('1','1','100','2018-09-19 10:37:34'),('10','1','312','2018-09-19 10:42:15'),('11','1','320','2018-09-19 10:43:08'),('12','1','321','2018-09-19 10:43:14'),('13','1','400','2018-09-19 10:43:31'),('14','2','100','2018-09-19 11:04:35'),('15','2','300','2018-09-19 11:28:18'),('16','2','110','2018-09-19 11:28:31'),('17','2','111','2018-09-19 11:28:45'),('18','2','112','2018-09-19 11:28:50'),('19','2','113','2018-09-19 11:28:55'),('2','1','110','2018-09-19 10:40:36'),('20','2','320','2018-09-19 11:29:08'),('21','2','321','2018-09-19 11:29:13'),('22','1','10001','2018-09-19 14:29:40'),('23','1','10002','2018-09-19 14:29:51'),('24','1','10003','2018-09-19 14:29:59'),('25','1','10005','2018-09-19 14:30:14'),('26','1','10004','2018-09-19 14:30:23'),('27','1','10000','2018-09-19 14:30:50'),('28','1','410','2018-09-19 14:40:41'),('29','1','411','2018-09-19 14:40:49'),('3','1','111','2018-09-19 10:40:54'),('30','1','412','2018-09-19 14:40:55'),('31','1','420','2018-09-19 14:41:02'),('32','1','421','2018-09-19 14:41:08'),('33','1','422','2018-09-19 14:41:13'),('4','1','112','2018-09-19 10:41:09'),('5','1','113','2018-09-19 10:41:15'),('6','1','200','2018-09-19 10:41:27'),('7','1','300','2018-09-19 10:41:41'),('8','1','310','2018-09-19 10:42:01'),('9','1','311','2018-09-19 10:42:08');
/*!40000 ALTER TABLE `t_role_perms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_student`
--

DROP TABLE IF EXISTS `t_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_student` (
  `id` varchar(20) COLLATE utf8_bin NOT NULL,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `photo_url` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `gender` char(2) COLLATE utf8_bin DEFAULT NULL,
  `registration_no` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `registered_residence` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `national` char(2) COLLATE utf8_bin DEFAULT NULL,
  `idcard_type` char(2) COLLATE utf8_bin DEFAULT NULL,
  `idcard` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `birthplace` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `native_place` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `registered_type` char(2) COLLATE utf8_bin DEFAULT NULL,
  `blood_type` char(2) COLLATE utf8_bin DEFAULT NULL,
  `source_school` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `admission_date` datetime DEFAULT NULL,
  `education_background` char(2) COLLATE utf8_bin DEFAULT NULL,
  `stady_status` char(2) COLLATE utf8_bin DEFAULT NULL,
  `name_en` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `used_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `marital_status` char(2) COLLATE utf8_bin DEFAULT NULL,
  `health_status` char(2) COLLATE utf8_bin DEFAULT NULL,
  `nationality` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `phone_num` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `political_status` char(2) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `specialty` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `report_status` char(2) COLLATE utf8_bin DEFAULT NULL,
  `report_date` datetime DEFAULT NULL,
  `residence_status` char(2) COLLATE utf8_bin DEFAULT NULL,
  `regist_status` char(2) COLLATE utf8_bin DEFAULT NULL,
  `regist_date` datetime DEFAULT NULL,
  `group_id` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_student`
--

LOCK TABLES `t_student` WRITE;
/*!40000 ALTER TABLE `t_student` DISABLE KEYS */;
INSERT INTO `t_student` VALUES ('1221312','4','http://127.0.0.1:8080/upload/unkown.jpg','02','','','01','01','',NULL,'','','01','01','',NULL,'01','01','','','01','01','','','01','','','02',NULL,'01','02',NULL,'20180918145708053542'),('123','徐鑫','http://127.0.0.1:8080/upload/123touxiang.jpg\n','01','','','01','01','',NULL,'','','01','02','','2018-09-13 00:00:00','01','01','','','01','01','','','01','','<p>fdsfdsf<img alt=\"laugh\" src=\"http://127.0.0.1:8080/CDUT/ckeditor/plugins/smiley/images/teeth_smile.png\" style=\"height:23px; width:23px\" title=\"laugh\" /></p>\n','02',NULL,'01','02',NULL,'20180914094552213428'),('3124','xx','http://127.0.0.1:8080/upload/unkown.jpg','01','','','01','01','',NULL,'','','01','01','',NULL,'01','01','','','01','01','','','01','','','02',NULL,'01','02',NULL,'20180914094552213428'),('412231314','','http://127.0.0.1:8080/upload/unkown.jpg','01','','','01','01','',NULL,'','','01','01','',NULL,'01','01','','','01','01','','','01','','','02',NULL,'01','02',NULL,'20180918145708053542'),('412312','','http://127.0.0.1:8080/upload/unkown.jpg','01','','','01','01','',NULL,'','','01','01','',NULL,'01','01','','','01','01','','','01','','','02',NULL,'01','02',NULL,'20180918145612609306'),('4123123','12312412','http://127.0.0.1:8080/upload/unkown.jpg','01','','','01','01','',NULL,'','','01','01','',NULL,'01','01','','','01','01','','','01','','','02',NULL,'01','02',NULL,'20180914101624973113'),('4124123','','http://127.0.0.1:8080/upload/unkown.jpg','02','','','01','01','',NULL,'','','01','01','',NULL,'01','01','','','01','01','','','01','','','02',NULL,'01','02',NULL,'20180914101707378111'),('4125123','1231253','http://127.0.0.1:8080/upload/unkown.jpg','02','','','01','01','',NULL,'','','01','01','',NULL,'01','01','','','01','01','','','01','','','02',NULL,'01','02',NULL,'20180914094552213428'),('41513','315312','http://127.0.0.1:8080/upload/unkown.jpg','01','','','01','01','',NULL,'','','01','01','',NULL,'01','01','','','01','01','','','01','','','02',NULL,'01','02',NULL,'20180914101707378111');
/*!40000 ALTER TABLE `t_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `username` varchar(20) COLLATE utf8_bin NOT NULL,
  `password` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `realName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` char(2) COLLATE utf8_bin DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES ('123','9c3b5c0672cd599ccf1019bddaa8089b','xx','1','2018-09-18 16:09:28'),('admin','038bdaf98f2037b31f1e75b5b4c9b26e','徐鑫','1','2018-09-11 11:44:39');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `t_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user_role` (
  `id` varchar(20) COLLATE utf8_bin NOT NULL,
  `user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `role_id` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
INSERT INTO `t_user_role` VALUES ('1','admin','1','2018-09-19 10:34:42'),('2','123','2','2018-09-19 10:35:01'),('3','admin','2','2018-09-19 11:03:41');
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-19 19:07:02
