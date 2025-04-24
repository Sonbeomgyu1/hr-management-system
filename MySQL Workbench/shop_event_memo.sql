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
-- Table structure for table `event_memo`
--

DROP TABLE IF EXISTS `event_memo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event_memo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf4sfy514vj9nwow1fm7s3ts70` (`member_id`),
  CONSTRAINT `FKf4sfy514vj9nwow1fm7s3ts70` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_memo`
--

LOCK TABLES `event_memo` WRITE;
/*!40000 ALTER TABLE `event_memo` DISABLE KEYS */;
INSERT INTO `event_memo` VALUES (1,'2025-03-03','관리자1','LG 투입 김루비',1),(2,'2025-03-04','관리자1','믹스업체 미팅 오후12시',1),(3,'2025-03-07','관리자1','커피사야되는날',1),(5,'2025-03-14','관리자1','기흥 오후3시 미팅',1),(6,'2025-03-18','관리자1','삼성중공업 미팅 오후4시',1),(7,'2025-03-20','관리자1','당산 유지보수 투입 증권',1),(10,'2025-03-20','관리자1','개발자 미팅',1),(11,'2025-03-14','관리자1','스포애니 미팅',1),(13,'2025-02-13','관리자1','사용자 미팅',1),(14,'2025-02-21','관리자1','삼성 미팅 및 개발자 미팅',1),(15,'2025-03-10','관리자1','볼펜 마우스 사는날',1),(16,'2025-02-10','관리자1','메이젠 미팅',1),(17,'2025-02-01','관리자1','홍길동 생일!',1),(18,'2025-02-18','관리자1','한국 농수산물 물류시스템 구축',1),(19,'2025-02-07','관리자1','소방 방제 시스템 구축/유지보수 상의',1),(20,'2025-02-05','관리자1','기흥 투입\n홍길동.박떙떙,유떙떙,김떙떙',1),(23,'2025-03-12','관리자1','KB증권 여의도 투입 박세리,한떙땡',1),(24,'2025-03-26','관리자1','강남 삼성개발건 대체투입당근',1),(27,'2025-03-05','손범규','이제 수정될려나',3),(28,'2025-03-26','손범규','softbank 실사',3),(29,'2025-03-25','관리자1','IBK 인터뷰',1),(30,'2025-03-31','손범규','손범규 월차',3),(31,'2025-04-07','관리자1','밥먹는날',1),(32,'2025-04-18','관리자1','손범규 월차',1),(33,'2025-04-21','관리자1','삼성생명 인터뷰',1);
/*!40000 ALTER TABLE `event_memo` ENABLE KEYS */;
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
