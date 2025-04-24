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
-- Table structure for table `manpower`
--

DROP TABLE IF EXISTS `manpower`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manpower` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `additional_note` text DEFAULT NULL,
  `birthday` varchar(100) DEFAULT NULL,
  `career_years` varchar(100) DEFAULT NULL,
  `certifications` text DEFAULT NULL,
  `contact_number` varchar(20) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `education_level` varchar(50) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `evaluation` text DEFAULT NULL,
  `job_position` varchar(100) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `projects` text DEFAULT NULL,
  `proposal` text DEFAULT NULL,
  `remark` text DEFAULT NULL,
  `search_date` date DEFAULT NULL,
  `skills` text DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `work_area` varchar(100) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `jobkorea_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manpower`
--

LOCK TABLES `manpower` WRITE;
/*!40000 ALTER TABLE `manpower` DISABLE KEYS */;
INSERT INTO `manpower` VALUES (2,'x','05.09','x','x','010-4856-8456','2025-03-10','x','sjk6285@naver.com','x','C','쨰액','삼성','x','x','2025-03-12','x','2025-03-10','경기도 부천','손범규',NULL),(3,'0','96.08','x','0','0105214685','2025-03-10','x','asvc@naver.com','0','K','커피빈','천안프로젝트','0','0','2025-03-20','0','2025-03-10','서울','손범규',NULL),(4,'x','78','3년','x','01025469855','2025-03-10','대졸','qwe123@naver.com','x','C','블루썸','노트북프로젝트','200','커피빈프로젝트','2025-04-01','spring','2025-03-10','경기도 안양','관리자1',NULL),(5,'ㅂ','1888.09','ㅂ','ㅂ','010555551234','2025-03-10','ㅂ','sjk6285@naver.com','ㅂ','D','박버즈','핸드폰 프로젝트','ㅂ','ㅂ','2025-03-15','ㅂ','2025-03-13','ㅂ','관리자1',''),(7,'x','1998','4년','정처기','01055551111','2025-03-11','대졸','sjk6285@naver.com','x','K','정수기','','','삼성','2025-03-14','spring','2025-03-26','d','손범규',''),(8,'없음','89','없음','없음','01055554444','2025-03-12','없음','sjk6285@naver.com','없음','D','김솔아','','없음','없음','2025-04-02','react,vue','2025-03-26','경기도 부천','관리자1',''),(9,'학원에서 배움','89','있음','있음','01033334444','2025-03-12','있음','sjk6285@naver.com','있음','K','박진후','천안프로젝트','있음','있음','2025-03-21','java','2025-03-26','강남','관리자1',''),(10,'있을려나?','1990','있을려나?','있을려나?','01011111111','2025-03-12','있을려나?','sjk6285@naver.com','있을려나?','C','장안구','탐탐프로젝트','있을려나?','있을려나?','2025-03-15','있을려나?','2025-03-12','전주','관리자1',NULL),(11,'없음','75','없음','없음','01011112222','2025-03-12','없음','sjk6285@naver.com','없음','D','김후범','르노삼성','없음','없음','2025-03-07','java,spring,oracle','2025-03-26','경기도 안양','관리자1',''),(12,'x','85','3년','x','01054298458','2025-03-26','대졸','gka4f@naver.com','x','D','유다정','온양','400','삼성디스플레이','2025-03-07','vue,spring','2025-03-26','경북','관리자1','');
/*!40000 ALTER TABLE `manpower` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-24 15:32:01
