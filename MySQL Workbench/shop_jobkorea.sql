-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: shop
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.6-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `jobkorea`
--

DROP TABLE IF EXISTS `jobkorea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jobkorea` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `area` varchar(100) DEFAULT NULL,
  `birthday` varchar(20) DEFAULT NULL,
  `carrer` varchar(50) DEFAULT NULL,
  `contact_num` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `hope_price` varchar(50) DEFAULT NULL,
  `job_korea_id` varchar(100) DEFAULT NULL,
  `job_korea_type_cd` varchar(30) DEFAULT NULL,
  `manpower_id` bigint(20) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `out_date` varchar(30) DEFAULT NULL,
  `progress_desc` varchar(2000) DEFAULT NULL,
  `project_nm` varchar(50) DEFAULT NULL,
  `search_date` date DEFAULT NULL,
  `skill` varchar(1000) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  `last_modified` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8laoae76pnrdep7o6q93arh77` (`member_id`),
  CONSTRAINT `FK8laoae76pnrdep7o6q93arh77` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobkorea`
--

LOCK TABLES `jobkorea` WRITE;
/*!40000 ALTER TABLE `jobkorea` DISABLE KEYS */;
INSERT INTO `jobkorea` VALUES (1,'전주','1990/0','초급','01011111111','sjk6285@naver.com','200','51651','프리랜서',5,'장안구','2025-03-21','ALTER TABLE shop.board\r\nADD FULLTEXT INDEX fulltext_index (boardtitle);\r\n\r\nALTER TABLE shop.item\r\nADD FULLTEXT INDEX fulltext_index (title);\r\n\r\nALTER TABLE shop.customer_member\r\nADD FULLTEXT INDEX fulltext_index (name);','lg','2025-03-01','vue,spring,react','관리자1',NULL,'2025-03-12 12:34:07.000000'),(2,'전주','90.02','고급','01024584975','asd12@naver.com','400','15948','프리랜서',NULL,'김민희','2025-03-28','현재 협상중','삼성반도체','2025-03-14','react spring vue ','관리자1',NULL,'2025-03-26 13:46:10.000000'),(5,'전주','2009.02','고급','01011111111','sjk6285@naver.com','2001','8888','프리랜서',99,'송차이','2025-04-09','협상진행중','삼성반도체1','2025-03-22','SPRING','관리자1',NULL,'2025-03-26 13:46:28.000000'),(6,'전주','2009.02','고급','01011111111','sjk6285@naver.com','2001','8888','프리랜서',99,'박노을','2025-04-09','협상진행중','삼성반도체1','2025-03-22','SPRING','관리자1',NULL,'2025-03-26 13:46:21.000000'),(7,'강남','90','고급','0104569915','asjdkawd@naver.com','500','78549517','프리랜서',50,'박후진','2025-03-21','입찰제안때문에 고민중이라고함','르노삼성','2025-04-16','react spring vue,mysql,html,java','손범규',NULL,'2025-03-26 13:12:48.000000'),(12,'강남','1990/09/06','있음','0101478523698','sjk6285@naver.com','있음','22222','정규직',7,'전보성','2025-03-21','손','있음','2025-03-14','있음','관리자1',NULL,'2025-03-26 13:46:38.000000'),(13,'판교','79','2년','01075915475','kfsd22wd@naver.com','300','28943231','프리랜서',22,'구태영','','인터뷰봤음','x','2025-03-14','react,mysql,spring','관리자1',NULL,'2025-03-26 13:46:59.000000');
/*!40000 ALTER TABLE `jobkorea` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-24 15:32:00
