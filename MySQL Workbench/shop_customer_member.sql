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
-- Table structure for table `customer_member`
--

DROP TABLE IF EXISTS `customer_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` text DEFAULT NULL,
  `created_date` date NOT NULL,
  `grade` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `region` varchar(100) NOT NULL,
  `skill` varchar(100) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_member`
--

LOCK TABLES `customer_member` WRITE;
/*!40000 ALTER TABLE `customer_member` DISABLE KEYS */;
INSERT INTO `customer_member` VALUES (1,'비싸요','2025-03-04','초급','손범규','01098499006','서울시','react spring vue ',1),(2,'볼펜이 없다고함','2025-03-04','초급','홍길동','01011111111','경남','SPRING',1),(3,'이 사람은 뭐가 필요하다고함','2025-03-07','중급','춘향이','01099999999','경기도 성남','REACT',1),(4,'비싸요','2025-03-04','초급','손범규','01098499006','서울시','react spring vue',1),(5,'서비스가 좋아요','2025-03-04','중급','김민수','01012345678','부산시','java spring',1),(6,'배송이 느려요','2025-03-04','고급','이영희','01098765432','대전시','python django',1),(7,'재구매 의사 있어요','2025-03-04','초급','박서준','01055556666','광주시','vue.js node.js',1),(8,'친절한 상담 감사합니다','2025-03-19','중급','정수빈','01044443333','대구시','angular typescript',1),(10,'사용법이 어려워요','2025-03-04','초급','김다현','01099992222','서울시','c++ embedded',2),(11,'디자인이 마음에 들어요','2025-03-04','중급','윤아름','01011112222','울산시','swift ios',2),(12,'기능이 많아서 좋아요','2025-03-04','고급','오세훈','01033334444','세종시','go rust',2),(15,'','2025-03-13','커피빈프로젝트','블루썸','01025469855','경기도 안양','spring',1),(16,'','2025-03-13','커피빈프로젝트','블루썸','01025469855','경기도 안양','spring',3),(17,'프로젝트 하나있음','2025-04-30','고급','마이비데','01099998888','전주송천동','잡기술',1);
/*!40000 ALTER TABLE `customer_member` ENABLE KEYS */;
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
