-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: rarasclm
-- ------------------------------------------------------
-- Server version	5.7.13-log

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
-- Table structure for table `caso`
--

DROP TABLE IF EXISTS `caso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caso` (
  `id_caso` varchar(10) NOT NULL,
  `paciente` int(11) DEFAULT '-1',
  `num_caso` smallint(6) DEFAULT NULL,
  `enfrara` varchar(10) NOT NULL DEFAULT '999999999',
  `declarada` smallint(6) DEFAULT '0',
  `usuario_declara` varchar(45) DEFAULT NULL,
  `fecha_declara` date DEFAULT NULL,
  `literal` varchar(255) DEFAULT NULL,
  `jucio_clinico` varchar(1014) DEFAULT NULL,
  `observaciones` varchar(4096) DEFAULT NULL,
  `hospital` varchar(6) DEFAULT NULL,
  `nhc` varchar(12) DEFAULT NULL,
  `base_diagnostico` tinyint(4) DEFAULT NULL,
  `fuente_informacion` varchar(20) DEFAULT NULL,
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
  `cod_cie9mc` varchar(6) DEFAULT NULL,
  `cod_cie10` varchar(6) DEFAULT NULL,
  `cod_snomed` varchar(20) DEFAULT NULL,
  `cod_omin` varchar(10) DEFAULT NULL,
  `cod_edta` varchar(10) DEFAULT NULL,
  `cod_otros` varchar(20) DEFAULT NULL,
  `cod_otro_deno` varchar(45) DEFAULT NULL,
  `tratamiento` varchar(4096) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `familiares_enfermedades_raras` tinyint(4) DEFAULT NULL,
  `otras_enfermedades_cronicas` smallint(6) DEFAULT NULL,
  `enfermedades_cronicas` varchar(1024) DEFAULT NULL,
  `fechahora_creacion` datetime DEFAULT NULL,
  `usuario_creacion` varchar(45) DEFAULT NULL,
  `fechahora_modificacion` datetime DEFAULT NULL,
  `usuario_modificacion` varchar(45) DEFAULT NULL,
  `borrado_logico` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id_caso`),
  KEY `fk_has_paciente_idx` (`paciente`),
  KEY `idx_paciente` (`paciente`),
  KEY `fk_has_enfrara` (`enfrara`),
  CONSTRAINT `fk_has_enfrara` FOREIGN KEY (`enfrara`) REFERENCES `enfermedad_rara` (`enfermedad_rara_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `fk_has_paciente` FOREIGN KEY (`paciente`) REFERENCES `paciente` (`id_paciente`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `caso_historia`
--

DROP TABLE IF EXISTS `caso_historia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caso_historia` (
  `id_version` int(11) NOT NULL,
  `id_caso` varchar(10) NOT NULL,
  `paciente` int(11) DEFAULT '-1',
  `num_caso` smallint(6) DEFAULT NULL,
  `enfrara` varchar(10) NOT NULL DEFAULT '999999999',
  `declarada` smallint(6) DEFAULT '0',
  `usuario_declara` varchar(45) DEFAULT NULL,
  `fecha_declara` date DEFAULT NULL,
  `literal` varchar(255) DEFAULT NULL,
  `jucio_clinico` varchar(1014) DEFAULT NULL,
  `observaciones` varchar(4096) DEFAULT NULL,
  `hospital` varchar(6) DEFAULT NULL,
  `nhc` varchar(12) DEFAULT NULL,
  `base_diagnostico` tinyint(4) DEFAULT NULL,
  `fuente_informacion` varchar(20) DEFAULT NULL,
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
  `cod_cie9mc` varchar(6) DEFAULT NULL,
  `cod_cie10` varchar(6) DEFAULT NULL,
  `cod_snomed` varchar(20) DEFAULT NULL,
  `cod_omin` varchar(10) DEFAULT NULL,
  `cod_edta` varchar(10) DEFAULT NULL,
  `cod_otros` varchar(20) DEFAULT NULL,
  `cod_otro_deno` varchar(45) DEFAULT NULL,
  `tratamiento` varchar(4096) CHARACTER SET utf8 COLLATE utf8_spanish_ci DEFAULT NULL,
  `familiares_enfermedades_raras` tinyint(4) DEFAULT NULL,
  `otras_enfermedades_cronicas` smallint(6) DEFAULT NULL,
  `enfermedades_cronicas` varchar(1024) DEFAULT NULL,
  `fechahora_creacion` datetime DEFAULT NULL,
  `usuario_creacion` varchar(45) DEFAULT NULL,
  `fechahora_modificacion` datetime DEFAULT NULL,
  `usuario_modificacion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_caso`,`id_version`),
  KEY `idx_paciente` (`paciente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ccaa`
--

DROP TABLE IF EXISTS `ccaa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ccaa` (
  `ccaa` varchar(2) NOT NULL,
  `deno` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`ccaa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `enfermedad_rara`
--

DROP TABLE IF EXISTS `enfermedad_rara`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enfermedad_rara` (
  `enfermedad_rara_id` varchar(10) CHARACTER SET utf8 NOT NULL,
  `literal` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `renal` varchar(2) CHARACTER SET utf8 DEFAULT NULL,
  `otro_cod` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `deno_otro` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `descripcion` varchar(4000) CHARACTER SET utf8 DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `ultima_actualizacion` timestamp NULL DEFAULT NULL,
  `notas` longtext CHARACTER SET utf8,
  PRIMARY KEY (`enfermedad_rara_id`),
  KEY `FK_ENFERMEDAD_RARA_ERENAL_CIE10` (`renal`),
  CONSTRAINT `FK_ENFERMEDAD_RARA_ERENAL_CIE10` FOREIGN KEY (`renal`) REFERENCES `enfermedad_renal` (`erenal_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `enfermedad_rara_cie10`
--

DROP TABLE IF EXISTS `enfermedad_rara_cie10`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enfermedad_rara_cie10` (
  `cie10_id` varchar(5) CHARACTER SET utf8 NOT NULL,
  `literal` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `notas` longtext CHARACTER SET utf8,
  PRIMARY KEY (`cie10_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `enfermedad_rara_cie9mc`
--

DROP TABLE IF EXISTS `enfermedad_rara_cie9mc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enfermedad_rara_cie9mc` (
  `cie9_id` varchar(6) NOT NULL,
  `literal` varchar(1255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `notas` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`cie9_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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

--
-- Table structure for table `enfermedad_rara_has_enfermedad_rara_cie9mc`
--

DROP TABLE IF EXISTS `enfermedad_rara_has_enfermedad_rara_cie9mc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enfermedad_rara_has_enfermedad_rara_cie9mc` (
  `enfermedad_rara_id` varchar(10) CHARACTER SET utf8 NOT NULL,
  `cie9_id` varchar(6) CHARACTER SET utf8 NOT NULL,
  `num_prioridad` int(11) DEFAULT '0',
  `notas` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`enfermedad_rara_id`,`cie9_id`),
  KEY `fk_enfermedad_rara_has_enfermedad_rara_cie9mc_enfermedad_ra_idx` (`cie9_id`),
  KEY `fk_enfermedad_rara_has_enfermedad_rara_cie9mc_enfermedad_ra_idx1` (`enfermedad_rara_id`),
  CONSTRAINT `fk_enfermedad_rara_has_enfermedad_rara_cie9mc_enfermedad_rara1` FOREIGN KEY (`enfermedad_rara_id`) REFERENCES `enfermedad_rara` (`enfermedad_rara_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_enfermedad_rara_has_enfermedad_rara_cie9mc_enfermedad_rara2` FOREIGN KEY (`cie9_id`) REFERENCES `enfermedad_rara_cie9mc` (`cie9_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `enfermedad_rara_has_enfermedad_rara_omin`
--

DROP TABLE IF EXISTS `enfermedad_rara_has_enfermedad_rara_omin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enfermedad_rara_has_enfermedad_rara_omin` (
  `enfermedad_rara_id` varchar(10) NOT NULL,
  `omin_id` varchar(10) NOT NULL,
  `num_prioridad` int(11) DEFAULT '0',
  PRIMARY KEY (`enfermedad_rara_id`,`omin_id`),
  KEY `fk_enfermedad_rara_has_enfermedad_rara_omin_enfermedad_rara_idx` (`omin_id`),
  KEY `fk_enfermedad_rara_has_enfermedad_rara_omin_enfermedad_rara_idx1` (`enfermedad_rara_id`),
  CONSTRAINT `fk_enfermedad_rara_has_enfermedad_rara_omin_enfermedad_rara1` FOREIGN KEY (`enfermedad_rara_id`) REFERENCES `enfermedad_rara` (`enfermedad_rara_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_enfermedad_rara_has_enfermedad_rara_omin_enfermedad_rara_o1` FOREIGN KEY (`omin_id`) REFERENCES `enfermedad_rara_omin` (`omin_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `enfermedad_rara_has_enfermedad_rara_orphanet`
--

DROP TABLE IF EXISTS `enfermedad_rara_has_enfermedad_rara_orphanet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enfermedad_rara_has_enfermedad_rara_orphanet` (
  `enfermedad_rara_id` varchar(10) NOT NULL,
  `orphanet_id` varchar(12) NOT NULL,
  `num_prioridad` int(11) DEFAULT NULL,
  PRIMARY KEY (`enfermedad_rara_id`,`orphanet_id`),
  KEY `fk_enfermedad_rara_has_enfermedad_rara_orphanet_enfermedad__idx` (`orphanet_id`),
  KEY `fk_enfermedad_rara_has_enfermedad_rara_orphanet_enfermedad__idx1` (`enfermedad_rara_id`),
  CONSTRAINT `fk_enfermedad_rara_has_enfermedad_rara_orphanet_enfermedad_ra1` FOREIGN KEY (`enfermedad_rara_id`) REFERENCES `enfermedad_rara` (`enfermedad_rara_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_enfermedad_rara_has_enfermedad_rara_orphanet_enfermedad_ra2` FOREIGN KEY (`orphanet_id`) REFERENCES `enfermedad_rara_orphanet` (`orphanet_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `enfermedad_rara_has_enfermedad_rara_snomed`
--

DROP TABLE IF EXISTS `enfermedad_rara_has_enfermedad_rara_snomed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enfermedad_rara_has_enfermedad_rara_snomed` (
  `enfermedad_rara_id` varchar(10) CHARACTER SET utf8 NOT NULL,
  `snomed_id` bigint(20) NOT NULL,
  `num_prioridad` int(11) DEFAULT NULL,
  PRIMARY KEY (`enfermedad_rara_id`,`snomed_id`),
  KEY `fk_enfermedad_rara_has_enfermedad_rara_snomed_enfermedad_ra_idx` (`snomed_id`),
  KEY `fk_enfermedad_rara_has_enfermedad_rara_snomed_enfermedad_ra_idx1` (`enfermedad_rara_id`),
  CONSTRAINT `fk_enfermedad_rara_has_enfermedad_rara_snomed_enfermedad_rara1` FOREIGN KEY (`enfermedad_rara_id`) REFERENCES `enfermedad_rara` (`enfermedad_rara_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_enfermedad_rara_has_enfermedad_rara_snomed_enfermedad_rara2` FOREIGN KEY (`snomed_id`) REFERENCES `enfermedad_rara_snomed` (`snomed_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `enfermedad_rara_omin`
--

DROP TABLE IF EXISTS `enfermedad_rara_omin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enfermedad_rara_omin` (
  `omin_id` varchar(10) CHARACTER SET utf8 NOT NULL,
  `literal` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`omin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `enfermedad_rara_orphanet`
--

DROP TABLE IF EXISTS `enfermedad_rara_orphanet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enfermedad_rara_orphanet` (
  `orphanet_id` varchar(12) CHARACTER SET utf8 NOT NULL,
  `literal` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`orphanet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `enfermedad_rara_snomed`
--

DROP TABLE IF EXISTS `enfermedad_rara_snomed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enfermedad_rara_snomed` (
  `snomed_id` bigint(20) NOT NULL,
  `literal` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`snomed_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `enfermedad_renal`
--

DROP TABLE IF EXISTS `enfermedad_renal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enfermedad_renal` (
  `erenal_id` varchar(2) CHARACTER SET utf8 NOT NULL,
  `literal` varchar(75) CHARACTER SET utf8 DEFAULT NULL,
  `grupo` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `cie_10_01` varchar(5) CHARACTER SET utf8 DEFAULT NULL,
  `cie_10_02` varchar(5) CHARACTER SET utf8 DEFAULT NULL,
  `cie_10_03` varchar(5) CHARACTER SET utf8 DEFAULT NULL,
  `cie_10_04` varchar(5) CHARACTER SET utf8 DEFAULT NULL,
  `cie_10_05` varchar(5) CHARACTER SET utf8 DEFAULT NULL,
  `cie_10_06` varchar(5) CHARACTER SET utf8 DEFAULT NULL,
  `cie_10_07` varchar(5) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`erenal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `historias_clinicas`
--

DROP TABLE IF EXISTS `historias_clinicas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historias_clinicas` (
  `paciente` int(11) NOT NULL,
  `hospital` varchar(6) NOT NULL DEFAULT '999999',
  `nhc` varchar(12) NOT NULL DEFAULT '999999999999',
  `id_unico_hc` varchar(18) DEFAULT '999999999999999999',
  PRIMARY KEY (`paciente`,`hospital`,`nhc`),
  KEY `fk_hospital_idx` (`hospital`),
  CONSTRAINT `fk_historia_es_de_hospital` FOREIGN KEY (`hospital`) REFERENCES `hospital` (`id_hospital`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_historia_es_de_un_paciente` FOREIGN KEY (`paciente`) REFERENCES `paciente` (`id_paciente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hospital`
--

DROP TABLE IF EXISTS `hospital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hospital` (
  `id_hospital` varchar(6) NOT NULL,
  `literal` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id_hospital`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `municipios`
--

DROP TABLE IF EXISTS `municipios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `municipios` (
  `municipio` varchar(5) NOT NULL,
  `deno` varchar(70) DEFAULT NULL,
  `provincia` varchar(2) DEFAULT NULL,
  `ccaa` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`municipio`),
  KEY `fk_municipio_has_provincia_idx` (`provincia`),
  CONSTRAINT `fk_municipio_has_provincia` FOREIGN KEY (`provincia`) REFERENCES `provincias` (`provincia`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paciente` (
  `id_paciente` int(11) NOT NULL,
  `idpacnac` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `cip` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `dni` varchar(12) COLLATE utf8_spanish_ci DEFAULT NULL,
  `numero_seg_social` varchar(25) CHARACTER SET utf8 DEFAULT NULL,
  `nombre` varchar(60) COLLATE utf8_spanish_ci DEFAULT NULL,
  `apellido_01` varchar(60) COLLATE utf8_spanish_ci DEFAULT NULL,
  `apellido_02` varchar(60) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `sexo` char(1) COLLATE utf8_spanish_ci DEFAULT NULL,
  `provincia_nacimiento` varchar(2) COLLATE utf8_spanish_ci DEFAULT '99',
  `municipio_nacimiento` varchar(5) CHARACTER SET utf8 DEFAULT '99999',
  `pais_nacimiento` varchar(3) CHARACTER SET utf8 DEFAULT '999',
  `dom_tipo_via` varchar(6) COLLATE utf8_spanish_ci DEFAULT NULL,
  `dom_nombre_via` varchar(60) COLLATE utf8_spanish_ci DEFAULT NULL,
  `dom_numero` varchar(4) COLLATE utf8_spanish_ci DEFAULT NULL,
  `dom_pisopuerta` varchar(4) COLLATE utf8_spanish_ci DEFAULT NULL,
  `dom_otros` varchar(60) COLLATE utf8_spanish_ci DEFAULT NULL,
  `dom_cp` varchar(5) COLLATE utf8_spanish_ci DEFAULT '99999',
  `provincia_residencia` varchar(2) CHARACTER SET utf8 DEFAULT '99',
  `municipio_residencia` varchar(5) CHARACTER SET utf8 DEFAULT '99999',
  `fallecido` tinyint(4) DEFAULT NULL,
  `fallecido_fecha` date DEFAULT NULL,
  `fallecido_fecha_comprobacion` datetime DEFAULT NULL,
  `fallecido_etiqueta_comprobacion` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fallecido_numbol` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono` varchar(12) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_paciente`),
  KEY `idx_apellidos` (`apellido_01`,`apellido_02`),
  KEY `idx_cip` (`cip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tabla de identificación de pacientes';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `paciente_historia`
--

DROP TABLE IF EXISTS `paciente_historia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paciente_historia` (
  `id_version` int(11) NOT NULL,
  `id_paciente` int(11) NOT NULL,
  `idpacnac` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `cip` varchar(16) COLLATE utf8_spanish_ci DEFAULT NULL,
  `dni` varchar(12) COLLATE utf8_spanish_ci DEFAULT NULL,
  `numero_seg_social` varchar(25) CHARACTER SET utf8 DEFAULT NULL,
  `nombre` varchar(60) COLLATE utf8_spanish_ci DEFAULT NULL,
  `apellido_01` varchar(60) COLLATE utf8_spanish_ci DEFAULT NULL,
  `apellido_02` varchar(60) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `sexo` char(1) COLLATE utf8_spanish_ci DEFAULT NULL,
  `provincia_nacimiento` varchar(2) COLLATE utf8_spanish_ci DEFAULT '99',
  `municipio_nacimiento` varchar(5) CHARACTER SET utf8 DEFAULT '99999',
  `pais_nacimiento` varchar(3) CHARACTER SET utf8 DEFAULT '999',
  `dom_tipo_via` varchar(6) COLLATE utf8_spanish_ci DEFAULT NULL,
  `dom_nombre_via` varchar(60) COLLATE utf8_spanish_ci DEFAULT NULL,
  `dom_numero` varchar(4) COLLATE utf8_spanish_ci DEFAULT NULL,
  `dom_pisopuerta` varchar(4) COLLATE utf8_spanish_ci DEFAULT NULL,
  `dom_otros` varchar(60) COLLATE utf8_spanish_ci DEFAULT NULL,
  `dom_cp` varchar(5) COLLATE utf8_spanish_ci DEFAULT '99999',
  `provincia_residencia` varchar(2) CHARACTER SET utf8 DEFAULT '99',
  `municipio_residencia` varchar(5) CHARACTER SET utf8 DEFAULT '99999',
  `fallecido` tinyint(4) DEFAULT NULL,
  `fallecido_fecha_comprobacion` datetime DEFAULT NULL,
  `fallecido_etiqueta_comprobacion` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fallecido_numbol` varchar(10) COLLATE utf8_spanish_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `telefono` varchar(12) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_paciente`,`id_version`),
  KEY `idx_apellidos` (`apellido_01`,`apellido_02`),
  KEY `idx_cip` (`cip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tabla de identificación de pacientes';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `provincias`
--

DROP TABLE IF EXISTS `provincias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `provincias` (
  `provincia` varchar(2) NOT NULL,
  `deno` varchar(70) DEFAULT NULL,
  `ccaa` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`provincia`),
  KEY `fk_provincia_has_ccaa_idx` (`ccaa`),
  CONSTRAINT `fk_provincia_has_ccaa` FOREIGN KEY (`ccaa`) REFERENCES `ccaa` (`ccaa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `deno` varchar(45) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `username` varchar(45) CHARACTER SET utf8 NOT NULL,
  `user_rol` int(11) NOT NULL,
  PRIMARY KEY (`username`,`user_rol`),
  KEY `fk_has_rol_idx` (`user_rol`),
  CONSTRAINT `fk_has_rol` FOREIGN KEY (`user_rol`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_has_user` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `username` varchar(45) CHARACTER SET utf8 NOT NULL,
  `password` varchar(128) CHARACTER SET utf8 NOT NULL,
  `enabled` tinyint(4) NOT NULL DEFAULT '1',
  `nombre` varchar(55) CHARACTER SET utf8 DEFAULT NULL,
  `apellido_01` varchar(55) CHARACTER SET utf8 DEFAULT NULL,
  `apellido_02` varchar(55) CHARACTER SET utf8 DEFAULT NULL,
  `seccion` int(11) DEFAULT NULL,
  `ultimo_acceso` datetime DEFAULT NULL,
  `num_intentos` int(11) DEFAULT '3',
  `puesto` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `v_paciente_caso`
--

DROP TABLE IF EXISTS `v_paciente_caso`;
/*!50001 DROP VIEW IF EXISTS `v_paciente_caso`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_paciente_caso` AS SELECT 
 1 AS `id_paciente`,
 1 AS `num_caso`,
 1 AS `idpacnac`,
 1 AS `cip`,
 1 AS `dni`,
 1 AS `numero_seg_social`,
 1 AS `nombre`,
 1 AS `apellido_01`,
 1 AS `apellido_02`,
 1 AS `fecha_nacimiento`,
 1 AS `sexo`,
 1 AS `provincia_nacimiento`,
 1 AS `municipio_nacimiento`,
 1 AS `pais_nacimiento`,
 1 AS `dom_tipo_via`,
 1 AS `dom_nombre_via`,
 1 AS `dom_numero`,
 1 AS `dom_pisopuerta`,
 1 AS `dom_otros`,
 1 AS `dom_cp`,
 1 AS `provincia_residencia`,
 1 AS `municipio_residencia`,
 1 AS `fallecido`,
 1 AS `fallecido_fecha`,
 1 AS `fallecido_fecha_comprobacion`,
 1 AS `fallecido_etiqueta_comprobacion`,
 1 AS `fallecido_numbol`,
 1 AS `email`,
 1 AS `telefono`,
 1 AS `enfrara`,
 1 AS `declarada`,
 1 AS `usuario_declara`,
 1 AS `literal_enfermedad`,
 1 AS `jucio_clinico`,
 1 AS `observaciones`,
 1 AS `hospital`,
 1 AS `numero_historia_clinica`,
 1 AS `base_diagnostico`,
 1 AS `fuente_informacion`,
 1 AS `fuente_pacientes_A`,
 1 AS `fuente_cmbd_C`,
 1 AS `fuente_defcong_D`,
 1 AS `fuente_edo_E`,
 1 AS `fuente_isociales_G`,
 1 AS `fuente_mhuerf_H`,
 1 AS `fuente_metabol_N`,
 1 AS `fuente_rinv_I`,
 1 AS `fuente_rmort_M`,
 1 AS `fuente_rcancer_T`,
 1 AS `fuente_renal_R`,
 1 AS `fuente_hc_primaria_V`,
 1 AS `fuente_hc_especializada_U`,
 1 AS `fuente_hc_primaria_masiva_P`,
 1 AS `fuente_hc_especializada_masiva_Q`,
 1 AS `fuente_otros_O`,
 1 AS `fecha_inicio_sintomas`,
 1 AS `fecha_deteccion`,
 1 AS `fecha_diagnostico`,
 1 AS `cod_cie9mc`,
 1 AS `cod_cie10`,
 1 AS `cod_snomed`,
 1 AS `cod_omin`,
 1 AS `cod_edta`,
 1 AS `cod_otros`,
 1 AS `cod_otro_deno`,
 1 AS `tratamiento`,
 1 AS `familiares_enfermedades_raras`,
 1 AS `otras_enfermedades_cronicas`,
 1 AS `enfermedades_cronicas`,
 1 AS `caso_fechahora_creacion`,
 1 AS `caso_fechahora_modificacion`,
 1 AS `caso_usuario_creacion`,
 1 AS `caso_usuario_modificacion`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `v_paciente_caso`
--

/*!50001 DROP VIEW IF EXISTS `v_paciente_caso`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_paciente_caso` AS select `paciente`.`id_paciente` AS `id_paciente`,`caso`.`num_caso` AS `num_caso`,`paciente`.`idpacnac` AS `idpacnac`,`paciente`.`cip` AS `cip`,`paciente`.`dni` AS `dni`,`paciente`.`numero_seg_social` AS `numero_seg_social`,`paciente`.`nombre` AS `nombre`,`paciente`.`apellido_01` AS `apellido_01`,`paciente`.`apellido_02` AS `apellido_02`,`paciente`.`fecha_nacimiento` AS `fecha_nacimiento`,`paciente`.`sexo` AS `sexo`,`paciente`.`provincia_nacimiento` AS `provincia_nacimiento`,`paciente`.`municipio_nacimiento` AS `municipio_nacimiento`,`paciente`.`pais_nacimiento` AS `pais_nacimiento`,`paciente`.`dom_tipo_via` AS `dom_tipo_via`,`paciente`.`dom_nombre_via` AS `dom_nombre_via`,`paciente`.`dom_numero` AS `dom_numero`,`paciente`.`dom_pisopuerta` AS `dom_pisopuerta`,`paciente`.`dom_otros` AS `dom_otros`,`paciente`.`dom_cp` AS `dom_cp`,`paciente`.`provincia_residencia` AS `provincia_residencia`,`paciente`.`municipio_residencia` AS `municipio_residencia`,`paciente`.`fallecido` AS `fallecido`,`paciente`.`fallecido_fecha` AS `fallecido_fecha`,`paciente`.`fallecido_fecha_comprobacion` AS `fallecido_fecha_comprobacion`,`paciente`.`fallecido_etiqueta_comprobacion` AS `fallecido_etiqueta_comprobacion`,`paciente`.`fallecido_numbol` AS `fallecido_numbol`,`paciente`.`email` AS `email`,`paciente`.`telefono` AS `telefono`,`caso`.`enfrara` AS `enfrara`,`caso`.`declarada` AS `declarada`,`caso`.`usuario_declara` AS `usuario_declara`,`caso`.`literal` AS `literal_enfermedad`,`caso`.`jucio_clinico` AS `jucio_clinico`,`caso`.`observaciones` AS `observaciones`,`caso`.`hospital` AS `hospital`,`caso`.`nhc` AS `numero_historia_clinica`,`caso`.`base_diagnostico` AS `base_diagnostico`,`caso`.`fuente_informacion` AS `fuente_informacion`,`caso`.`fuente_pacientes_A` AS `fuente_pacientes_A`,`caso`.`fuente_cmbd_C` AS `fuente_cmbd_C`,`caso`.`fuente_defcong_D` AS `fuente_defcong_D`,`caso`.`fuente_edo_E` AS `fuente_edo_E`,`caso`.`fuente_isociales_G` AS `fuente_isociales_G`,`caso`.`fuente_mhuerf_H` AS `fuente_mhuerf_H`,`caso`.`fuente_metabol_N` AS `fuente_metabol_N`,`caso`.`fuente_rinv_I` AS `fuente_rinv_I`,`caso`.`fuente_rmort_M` AS `fuente_rmort_M`,`caso`.`fuente_rcancer_T` AS `fuente_rcancer_T`,`caso`.`fuente_renal_R` AS `fuente_renal_R`,`caso`.`fuente_hc_primaria_V` AS `fuente_hc_primaria_V`,`caso`.`fuente_hc_especializada_U` AS `fuente_hc_especializada_U`,`caso`.`fuente_hc_primaria_masiva_P` AS `fuente_hc_primaria_masiva_P`,`caso`.`fuente_hc_especializada_masiva_Q` AS `fuente_hc_especializada_masiva_Q`,`caso`.`fuente_otros_O` AS `fuente_otros_O`,`caso`.`fecha_inicio_sintomas` AS `fecha_inicio_sintomas`,`caso`.`fecha_deteccion` AS `fecha_deteccion`,`caso`.`fecha_diagnostico` AS `fecha_diagnostico`,`caso`.`cod_cie9mc` AS `cod_cie9mc`,`caso`.`cod_cie10` AS `cod_cie10`,`caso`.`cod_snomed` AS `cod_snomed`,`caso`.`cod_omin` AS `cod_omin`,`caso`.`cod_edta` AS `cod_edta`,`caso`.`cod_otros` AS `cod_otros`,`caso`.`cod_otro_deno` AS `cod_otro_deno`,`caso`.`tratamiento` AS `tratamiento`,`caso`.`familiares_enfermedades_raras` AS `familiares_enfermedades_raras`,`caso`.`otras_enfermedades_cronicas` AS `otras_enfermedades_cronicas`,`caso`.`enfermedades_cronicas` AS `enfermedades_cronicas`,`caso`.`fechahora_creacion` AS `caso_fechahora_creacion`,`caso`.`fechahora_modificacion` AS `caso_fechahora_modificacion`,`caso`.`usuario_creacion` AS `caso_usuario_creacion`,`caso`.`usuario_modificacion` AS `caso_usuario_modificacion` from (`paciente` join `caso`) where (`caso`.`paciente` = `paciente`.`id_paciente`) order by `paciente`.`id_paciente`,`caso`.`num_caso` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-08 17:53:58
