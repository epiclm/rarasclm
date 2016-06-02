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
-- Table structure for table `pacientes`
--

DROP TABLE IF EXISTS `pacientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pacientes` (
  `paciente` int(11) NOT NULL,
  `idpacnac` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `cip` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `dni` varchar(12) COLLATE utf8_spanish_ci DEFAULT NULL,
  `numero_seg_social` varchar(25) CHARACTER SET utf8 DEFAULT NULL,
  `nombre` varchar(60) COLLATE utf8_spanish_ci DEFAULT NULL,
  `apellido_01` varchar(60) COLLATE utf8_spanish_ci DEFAULT NULL,
  `apellido_02` varchar(60) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `sexo` char(1) COLLATE utf8_spanish_ci DEFAULT NULL,
  `municipio_nacimiento` varchar(5) CHARACTER SET utf8 DEFAULT '99999',
  `pais_nacimiento` varchar(3) CHARACTER SET utf8 DEFAULT '999',
  `dom_tipo_via` varchar(6) COLLATE utf8_spanish_ci DEFAULT NULL,
  `dom_nombre_via` varchar(60) COLLATE utf8_spanish_ci DEFAULT NULL,
  `dom_numero` varchar(4) COLLATE utf8_spanish_ci DEFAULT NULL,
  `dom_pisopuerta` varchar(4) COLLATE utf8_spanish_ci DEFAULT NULL,
  `dom_otros` varchar(60) COLLATE utf8_spanish_ci DEFAULT NULL,
  `municipio_residencia` varchar(5) CHARACTER SET utf8 DEFAULT '99999',
  `fallecido` tinyint(4) DEFAULT NULL,
  `fallecido_fecha_comprobacion` datetime DEFAULT NULL,
  `fallecido_etiqueta_comprobacion` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fallecido_numbol` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono` varchar(12) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`paciente`),
  KEY `idx_apellidos` (`apellido_01`,`apellido_02`),
  KEY `idx_cip` (`cip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tabla de identificación de pacientes';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-01 20:04:40
