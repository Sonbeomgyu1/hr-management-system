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
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `boardcontent` varchar(255) DEFAULT NULL,
  `boarddate` datetime(6) DEFAULT NULL,
  `boardtitle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (3,'모니터\r\n제품목록\r\nASLJD2DLASD\r\nLGLKMLA2DMA\r\nLKGMLKCD35D\r\n\r\n노트북\r\nASLJNDASALSW2\r\nHRGECWDASDW23\r\nLVLMLKFSDFA26','2025-03-04 10:06:33.000000','마우스/노트북 목록'),(4,'뭐가 필요할지 논의를 해봐야함.\n이름, 주소, 전화번호, 생년월일, 사진, 기술, 등급, 가격 등 여러 가지 필요','2025-03-04 10:07:37.000000','SOFTBANK 프로그램'),(5,'1. 점심 밥값\n2. 볼펜, 공책 등 여러 가지\n3. 노트북, 모니터 등 개발자 여러 가지 필요함','2025-03-04 10:08:35.000000','법카 사용내용'),(6,'1. 신규 프로젝트 기획\n2. 개발 일정 확정\n3. 요구사항 분석 진행\n4. www.google.com 참고','2025-03-04 11:00:10.000000','신규 프로젝트'),(7,'3월10일 내부 테스트\n3월15일 고객 시연\n3월25일 정식 배포','2025-03-04 11:02:45.000000','프로젝트 일정'),(8,'키보드\n제품목록\nKBDX1234\nLOGI-KB900\nHP-WIRELESS-KB\n\n마우스\nMOUSE-GAMING1\nAPPLE-MAGIC-MOUSE2\nDELL-BASIC-MOUSE','2025-03-04 11:05:20.000000','키보드/마우스 목록'),(9,'기능 추가 논의 필요.\n로그인 시스템 개선\n결제 시스템 추가 여부 검토\n보안 강화 방안 논의','2025-03-04 11:08:30.000000','시스템 개선안'),(10,'1. 개발 장비 구매\n2. 회의비 사용\n3. 서버 유지보수 비용\n4. 클라우드 서비스 비용','2025-03-04 11:10:55.000000','경비 사용 내역'),(11,'1. 홈페이지 UI 개선 검토\n2. 반응형 디자인 적용 필요\n3. 최신 트렌드 반영','2025-03-04 11:15:20.000000','홈페이지 리뉴얼 논의'),(12,'4월5일 프로젝트 시작\n4월20일 중간 점검\n4월30일 완료 예정','2025-03-04 11:18:45.000000','4월 프로젝트 일정'),(13,'스마트폰\n제품목록\nGALAXY-S24\nIPHONE-15PRO\nXIAOMI-REDMI13\n\n태블릿\nIPAD-PRO-2024\nGALAXY-TAB-S9\nLENOVO-TAB-P12','2025-03-04 11:22:10.000000','스마트폰/태블릿 목록'),(14,'보안 업데이트 필요.\n로그인 이중 인증 추가 검토\n데이터 암호화 방식 변경 논의','2025-03-04 11:25:30.000000','보안 시스템 개선'),(15,'1. 직원 교육 비용\n2. 사무용 가구 구입\n3. 신규 소프트웨어 라이선스 비용\n4. 출장 경비','2025-03-04 11:30:00.000000','운영비 사용 내역'),(16,'1. 회사 블로그 개설 필요\n2. SNS 마케팅 전략 수립\n3. 홍보 영상 제작 논의','2025-03-04 11:35:15.000000','마케팅 전략 회의'),(17,'4월1일 제품 출시 준비\n4월10일 내부 테스트 완료\n4월15일 시장 반응 조사','2025-03-04 11:40:30.000000','신제품 출시 계획'),(18,'1. 회사 워크숍 일정 조율\n2. 직원 복지 프로그램 추가 논의\n3. 연말 보너스 지급 기준 검토','2025-03-04 11:45:50.000000','사내 복지 개선 논의'),(20,'1. 사무실 리모델링 일정 확정\n2. 가구 배치 변경\n3. 인터넷 회선 증설 검토','2025-03-04 11:55:10.000000','사무실 환경 개선 계획'),(22,'휴우','2025-04-11 14:06:05.000000','휴우');
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
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
