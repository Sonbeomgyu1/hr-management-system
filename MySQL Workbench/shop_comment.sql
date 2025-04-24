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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'회사에서 어지럽다고함',9,'customer','test2',NULL),(2,'맛있어요',5,'item','test2',NULL),(3,'맛있나?',1,'item','test2',NULL),(14,'치즈',1,'jobkorea','test1','2025-03-11 14:13:02.000000'),(15,'현재 방재 프로젝트에 투입되어 3월달에 끝난다고함.',1,'jobkorea','test1','2025-03-11 14:15:44.000000'),(16,'치즈 먹음',7,'manPower','test1','2025-03-11 14:17:18.000000'),(17,'매운돈가스 오늘 먹었는데 맛있었음.',6,'manPower','test1','2025-03-11 14:18:24.000000'),(18,'볼펜이 없다고함',1,'jobkorea','test1','2025-03-12 12:18:45.000000'),(19,'ㅁㄴㅇ',1,'jobkorea','test1','2025-03-12 14:46:32.000000'),(20,'sk하이닉스 인터뷰',1,'jobkorea','test1','2025-03-12 15:21:34.000000'),(21,'삼성화재?',11,'manPower','son','2025-03-12 16:09:36.000000'),(22,'버즈 사야되나',11,'manPower','son','2025-03-12 16:09:47.000000'),(23,'500 제안받음',7,'jobkorea','test1','2025-03-26 13:15:56.000000'),(24,'react를 할줄 몰라서 고민됨.',4,'customer','test1','2025-03-26 13:16:38.000000'),(25,'ㅁㄴㅇ',12,'manPower','test1','2025-04-11 13:41:28.000000'),(26,'111',12,'manPower','son','2025-04-23 14:32:35.000000');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
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
