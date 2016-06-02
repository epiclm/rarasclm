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
-- Table structure for table `enfermedad_rara_has_enfermedad_rara_cie10`
--

DROP TABLE IF EXISTS `enfermedad_rara_has_enfermedad_rara_cie10`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enfermedad_rara_has_enfermedad_rara_cie10` (
  `enfermedad_rara_id` varchar(10) CHARACTER SET utf8 NOT NULL,
  `cie10_id` varchar(5) CHARACTER SET utf8 NOT NULL,
  `num_prioridad` int(11) DEFAULT NULL,
  `notas` varchar(2048) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`enfermedad_rara_id`,`cie10_id`),
  KEY `fk_enfermedad_rara_has_enfermedad_rara_cie10_enfermedad_rar_idx` (`cie10_id`),
  KEY `fk_enfermedad_rara_has_enfermedad_rara_cie10_enfermedad_rar_idx1` (`enfermedad_rara_id`),
  CONSTRAINT `fk_enfermedad_rara_has_enfermedad_rara_cie10_enfermedad_rara1` FOREIGN KEY (`enfermedad_rara_id`) REFERENCES `enfermedad_rara` (`enfermedad_rara_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_enfermedad_rara_has_enfermedad_rara_cie10_enfermedad_rara_1` FOREIGN KEY (`cie10_id`) REFERENCES `enfermedad_rara_cie10` (`cie10_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-01 20:04:44
