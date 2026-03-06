-- MySQL dump 10.13  Distrib 9.4.0, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sauap_db
-- ------------------------------------------------------
-- Server version	9.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `asignacion`
--

DROP TABLE IF EXISTS `asignacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asignacion` (
  `id_asignacion` int NOT NULL AUTO_INCREMENT,
  `dia` varchar(15) NOT NULL,
  `hora_fin` time(6) NOT NULL,
  `hora_inicio` time(6) NOT NULL,
  `id_profesor` int NOT NULL,
  `id_unidad` bigint NOT NULL,
  PRIMARY KEY (`id_asignacion`),
  KEY `FK2d63fhl38p6sbq0ix1ipw5t8d` (`id_profesor`),
  KEY `FKgeb0gnm9fq5wcrac80ujcwww` (`id_unidad`),
  CONSTRAINT `FK2d63fhl38p6sbq0ix1ipw5t8d` FOREIGN KEY (`id_profesor`) REFERENCES `profesor` (`id_profesor`),
  CONSTRAINT `FKgeb0gnm9fq5wcrac80ujcwww` FOREIGN KEY (`id_unidad`) REFERENCES `unidad_aprendizaje` (`id_unidad`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignacion`
--

LOCK TABLES `asignacion` WRITE;
/*!40000 ALTER TABLE `asignacion` DISABLE KEYS */;
INSERT INTO `asignacion` VALUES (1,'Lunes','09:00:00.000000','07:00:00.000000',1,7),(2,'Lunes','08:00:00.000000','07:00:00.000000',4,6),(3,'Lunes','13:18:00.000000','10:18:00.000000',1,6),(4,'Miércoles','16:00:00.000000','14:00:00.000000',3,2),(5,'Viernes','17:00:00.000000','15:00:00.000000',2,5);
/*!40000 ALTER TABLE `asignacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesor`
--

DROP TABLE IF EXISTS `profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesor` (
  `id_profesor` int NOT NULL AUTO_INCREMENT,
  `apellido_materno` varchar(50) DEFAULT NULL,
  `apellido_paterno` varchar(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `rfc` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`id_profesor`),
  UNIQUE KEY `UK_3llfxv8ypcjlj9x9o1cv39yng` (`rfc`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor`
--

LOCK TABLES `profesor` WRITE;
/*!40000 ALTER TABLE `profesor` DISABLE KEYS */;
INSERT INTO `profesor` VALUES (1,'Diaz','Alvarado ','Aylin','YJCO501123FV0'),(2,'Castillo','Ramirez','Ashley','VTHY391206DZ7'),(3,'Melendrez','Castaños','Cristhian','LZTN840615WP3'),(4,'Castro','Avitia','Eduardo','QIHO141202TC2');
/*!40000 ALTER TABLE `profesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unidad_aprendizaje`
--

DROP TABLE IF EXISTS `unidad_aprendizaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unidad_aprendizaje` (
  `id_unidad` bigint NOT NULL AUTO_INCREMENT,
  `h_clase` int NOT NULL,
  `h_lab` int NOT NULL,
  `h_taller` int NOT NULL,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id_unidad`),
  CONSTRAINT `unidad_aprendizaje_chk_1` CHECK (((`h_clase` <= 4) and (`h_clase` >= 0))),
  CONSTRAINT `unidad_aprendizaje_chk_2` CHECK (((`h_lab` <= 4) and (`h_lab` >= 0))),
  CONSTRAINT `unidad_aprendizaje_chk_3` CHECK (((`h_taller` <= 4) and (`h_taller` >= 0)))
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unidad_aprendizaje`
--

LOCK TABLES `unidad_aprendizaje` WRITE;
/*!40000 ALTER TABLE `unidad_aprendizaje` DISABLE KEYS */;
INSERT INTO `unidad_aprendizaje` VALUES (1,3,1,4,'Desarrollo de software'),(2,3,2,3,'Calculo diferencial'),(3,3,2,1,'Requerimientos y diseño'),(4,2,1,2,'Conectividad'),(5,3,4,4,'Redes de computadoras'),(6,2,1,3,'Bases de datos'),(7,4,1,1,'Desarrollo web');
/*!40000 ALTER TABLE `unidad_aprendizaje` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `password` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `UK_471i15k6vbj1lfsfb19getcdi` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin','admin');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-05 17:27:33
