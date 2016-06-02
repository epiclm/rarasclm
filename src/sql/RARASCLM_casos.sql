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
-- Table structure for table `casos`
--

DROP TABLE IF EXISTS `casos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `casos` (
  `caso` varchar(10) CHARACTER SET latin1 NOT NULL,
  `paciente` int(11) DEFAULT '-1',
  `num_caso` smallint(6) DEFAULT NULL,
  `enfrara` varchar(10) CHARACTER SET latin1 DEFAULT '999999999',
  `declarada` smallint(6) DEFAULT '0',
  `usuario_declara` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `fecha_declara` date DEFAULT NULL,
  `literal` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `jucio_clinico` varchar(1014) CHARACTER SET latin1 DEFAULT NULL,
  `observaciones` varchar(4096) CHARACTER SET latin1 DEFAULT NULL,
  `hospital` varchar(6) CHARACTER SET latin1 DEFAULT NULL,
  `nhc` varchar(12) CHARACTER SET latin1 DEFAULT NULL,
  `base_diagnostico` tinyint(4) DEFAULT NULL,
  `fuente_informacion` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `fuente_pacientes_A` bit(1) DEFAULT b'0',
  `fuente_cmbd_C` bit(1) DEFAULT b'0',
  `fuente_defcong_D` bit(1) DEFAULT b'0',
  `fuente_edo_E` bit(1) DEFAULT b'0',
  `fuente_isociales_G` bit(1) DEFAULT b'0',
  `fuente_mhuerf_H` bit(1) DEFAULT b'0',
  `fuente_metabol_N` bit(1) DEFAULT b'0',
  `fuente_rinv_I` bit(1) DEFAULT b'0',
  `fuente_rmort_M` bit(1) DEFAULT b'0',
  `fuente_rcancer_T` bit(1) DEFAULT b'0',
  `fuente_renal_R` bit(1) DEFAULT b'0',
  `fuente_hc_primaria_V` bit(1) DEFAULT b'0',
  `fuente_hc_especializada_U` bit(1) DEFAULT b'0',
  `fuente_hc_primaria_masiva_P` bit(1) DEFAULT b'0',
  `fuente_hc_especializada_masiva_Q` bit(1) DEFAULT b'0',
  `fuente_otros_O` bit(1) DEFAULT b'0',
  `fecha_inicio_sintomas` date DEFAULT NULL,
  `fecha_deteccion` date DEFAULT NULL,
  `fecha_diagnostico` date DEFAULT NULL,
  `cod_cie9mc` varchar(6) CHARACTER SET latin1 DEFAULT NULL,
  `cod_cie10` varchar(6) CHARACTER SET latin1 DEFAULT NULL,
  `cod_snomed` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `cod_omin` varchar(10) CHARACTER SET latin1 DEFAULT NULL,
  `cod_edta` varchar(10) CHARACTER SET latin1 DEFAULT NULL,
  `cod_otros` varchar(20) CHARACTER SET latin1 DEFAULT NULL,
  `cod_otro_deno` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `tratamiento` varchar(4096) COLLATE utf8_spanish_ci DEFAULT NULL,
  `familiares_enfermedades_raras` tinyint(4) DEFAULT NULL,
  `otras_enfermedades_cronicas` smallint(6) DEFAULT NULL,
  `enfermedades_cronicas` varchar(1024) CHARACTER SET latin1 DEFAULT NULL,
  `fechahora_creacion` datetime DEFAULT NULL,
  `usuario_creacion` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `fechahora_modificacion` datetime DEFAULT NULL,
  `usuario_modificacion` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`caso`),
  KEY `fk_has_paciente_idx` (`paciente`),
  KEY `idx_paciente` (`paciente`),
  CONSTRAINT `fk_has_paciente` FOREIGN KEY (`paciente`) REFERENCES `pacientes` (`paciente`) ON DELETE CASCADE ON UPDATE CASCADE
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

-- Dump completed on 2016-06-01 20:04:43
