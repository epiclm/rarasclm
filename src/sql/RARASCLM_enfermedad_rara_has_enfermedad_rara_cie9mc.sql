CREATE DATABASE  IF NOT EXISTS `RARASCLM` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `RARASCLM`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: RARASCLM
-- ------------------------------------------------------
-- Server version	5.6.27-0ubuntu1

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
-- Table structure for table `enfermedad_rara_has_enfermedad_rara_cie9mc`
--

DROP TABLE IF EXISTS `enfermedad_rara_has_enfermedad_rara_cie9mc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enfermedad_rara_has_enfermedad_rara_cie9mc` (
  `enfermedad_rara_id` varchar(10) NOT NULL,
  `cie9_id` varchar(6) NOT NULL,
  `num_prioridad` int(11) DEFAULT '0',
  `notas` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`enfermedad_rara_id`,`cie9_id`),
  KEY `fk_enfermedad_rara_has_enfermedad_rara_cie9mc_enfermedad_ra_idx` (`cie9_id`),
  KEY `fk_enfermedad_rara_has_enfermedad_rara_cie9mc_enfermedad_ra_idx1` (`enfermedad_rara_id`),
  CONSTRAINT `fk_enfermedad_rara_has_enfermedad_rara_cie9mc_enfermedad_rara1` FOREIGN KEY (`enfermedad_rara_id`) REFERENCES `enfermedad_rara` (`enfermedad_rara_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_enfermedad_rara_has_enfermedad_rara_cie9mc_enfermedad_rara2` FOREIGN KEY (`cie9_id`) REFERENCES `enfermedad_rara_cie9mc` (`cie9_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enfermedad_rara_has_enfermedad_rara_cie9mc`
--

LOCK TABLES `enfermedad_rara_has_enfermedad_rara_cie9mc` WRITE;
/*!40000 ALTER TABLE `enfermedad_rara_has_enfermedad_rara_cie9mc` DISABLE KEYS */;
INSERT INTO `enfermedad_rara_has_enfermedad_rara_cie9mc` VALUES ('0000000001','275.09',1,''),('0000000001','282.3',-1,''),('0000000001','347',-1,''),('0000022222','264.1',1,'');
/*!40000 ALTER TABLE `enfermedad_rara_has_enfermedad_rara_cie9mc` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-17 18:41:18
