-- MySQL dump 10.13  Distrib 5.7.35, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: anz-accounts
-- ------------------------------------------------------
-- Server version	5.7.25

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `account_number` bigint(12) NOT NULL,
  `account_name` varchar(45) COLLATE utf8_bin NOT NULL,
  `account_type` enum('SAVINGS','CURRENT') COLLATE utf8_bin DEFAULT NULL,
  `balance_date` datetime NOT NULL,
  `currency` varchar(3) COLLATE utf8_bin NOT NULL,
  `opening_available_balance` decimal(13,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`account_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES 
(321143048,'AUCurrent433','CURRENT','2018-11-08 00:00:00','AUD',38010.62),
(347786244,'SGCurrent166','CURRENT','2018-11-08 00:00:00','SGD',50664.65),
(585309209,'SGSavings726','SAVINGS','2018-11-08 00:00:00','SGD',84327.51),
(791066619,'AUSavings933','SAVINGS','2018-11-08 00:00:00','AUD',88005.93);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `uuid` char(36) COLLATE utf8_bin NOT NULL,
  `value_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `debit_amount` decimal(13,2) DEFAULT NULL,
  `credit_amount` decimal(13,2) DEFAULT NULL,
  `transaction_type` enum('DEBIT','CREDIT') COLLATE utf8_bin NOT NULL,
  `transaction_narrative` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `account_number` bigint(12) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `FK_account_transaction_idx` (`account_number`),
  CONSTRAINT `FK_account_transaction` FOREIGN KEY (`account_number`) REFERENCES `account` (`account_number`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES 
('6adb64aa-e904-4b04-81a1-482ea59cdb79','2022-04-07 02:55:44',10000.00,NULL,'DEBIT','',321143048),
('db67eea8-114c-4388-9f27-abd08e1efa1f','2022-04-07 02:55:44',NULL,500.00,'CREDIT','',321143048),
('6adb64aa-e904-4b04-81a1-481ea59cdb79','2022-04-07 02:55:44',110000.00,NULL,'DEBIT','',347786244),
('db67eea8-114c-4388-9f27-abd08e1efa2f','2022-04-07 02:55:44',NULL,800.00,'CREDIT','',347786244),
('6adb64aa-e904-4b04-81a1-482ea69cdb79','2022-04-07 02:55:44',9000.00,NULL,'DEBIT','',347786244),
('db67eea8-114c-4388-9f27-abd08e2efa1f','2022-04-07 02:55:44',NULL,435.00,'CREDIT','',791066619),
('6adb64aa-e904-4b04-81a1-482ea59cdb19','2022-04-07 02:55:44',10200.00,NULL,'DEBIT','',791066619),
('db67eea8-114c-4388-9f27-abd07e1efa1f','2022-04-07 02:55:44',NULL,500.00,'CREDIT','',791066619);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-08  8:59:34
