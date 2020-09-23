-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: catalogoJuegos
-- ------------------------------------------------------
-- Server version	5.7.31-0ubuntu0.18.04.1

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
-- Current Database: `catalogoJuegos`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `catalogoJuegos` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci */;

USE `catalogoJuegos`;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categorias` (
  `id_categoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id_categoria`),
  UNIQUE KEY `categorias_UN` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (3,'accion'),(4,'aventura'),(8,'estrategia'),(5,'plataformas'),(1,'rol'),(2,'rpg'),(6,'shooter'),(7,'simulacion');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `juegos`
--

DROP TABLE IF EXISTS `juegos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `juegos` (
  `nombre` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `precio` decimal(5,2) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  `imagen` varchar(255) COLLATE utf8_spanish_ci DEFAULT 'https://picsum.photos/200',
  `fecha_creacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_validado` datetime DEFAULT NULL COMMENT 'si tiene valor NULL este producto no es visible en la parte publica, ha de ser validado por un administrador',
  `id_usuario` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `juegos_UN` (`nombre`),
  KEY `FK_juegos_categoria` (`id_categoria`),
  KEY `juegos_FK` (`id_usuario`),
  CONSTRAINT `FK_juegos_categoria` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id_categoria`),
  CONSTRAINT `juegos_FK` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Listado de juegos';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `juegos`
--

LOCK TABLES `juegos` WRITE;
/*!40000 ALTER TABLE `juegos` DISABLE KEYS */;
INSERT INTO `juegos` VALUES ('super mario land 22',15,5.53,5,'https://images-na.ssl-images-amazon.com/images/I/91x1gsV0G3L._SX342_.jpg','2020-07-13 12:31:02',NULL,2),('super mario land 3',16,7.51,5,'https://photos.wowroms.com/emulators-roms-logo/26/10377/420-420/Wario-Land---Super-Mario-Land-3-(World).jpg','2020-07-13 12:31:02','2020-07-30 12:48:35',2),('     super mario land 4',17,5.00,5,'https://picsum.photos/100','2020-07-13 12:31:02','2020-08-18 11:39:29',1),('mario bros 2',18,0.00,5,'https://picsum.photos/100','2020-07-13 12:31:02','2020-07-13 12:31:02',1),('warriors orochi 3',19,20.00,4,'https://picsum.photos/100','2020-07-13 12:31:02','2020-07-30 12:48:35',1),('mighty n9',20,20.00,1,'','2020-07-13 12:31:02','2020-07-13 12:31:02',2),('just cause',22,0.00,4,'https://picsum.photos/100','2020-07-13 12:31:02','2020-08-18 11:39:29',1),('gta liberty city',23,0.00,4,'https://picsum.photos/100','2020-07-13 12:31:02','2020-07-13 12:31:02',1),('so',24,0.00,3,'https://picsum.photos/100','2020-07-13 12:31:02','2020-07-13 12:31:02',1),('fallout 3',25,5.30,6,'https://picsum.photos/100','2020-07-13 12:31:02','2020-07-13 12:31:02',2),('turok 2',26,55.60,4,'https://picsum.photos/100','2020-07-13 12:31:02','2020-08-18 11:44:49',1),('State of Decay 2',29,10.34,4,'https://avatarfiles.alphacoders.com/140/140854.jpg','2020-07-13 12:31:02','2020-08-18 11:44:49',1),('farcry 4',30,14.50,4,'https://static.truetrophies.com/boxart/Game_2842.png','2020-07-13 12:31:02','2020-08-18 11:48:06',2),('far cry primal',31,5.00,4,'https://images-na.ssl-images-amazon.com/images/I/71GYXIFRkcL._CR0,204,1224,1224_UX256.jpg','2020-07-13 12:31:02','2020-08-18 11:49:28',1),('pac-man',33,2.00,3,'','2020-07-13 12:31:02','2020-08-18 11:49:46',1),('fallout 4',34,3.00,3,'','2020-07-13 12:31:02',NULL,1),('batman arkam city',35,36.50,3,'https://www.yambalu.com/img/juegos/portadas/5488_batman_arkham_city_xbox_360_xxxl.jpg','2020-07-23 09:59:13',NULL,2),('age of empires 2',36,10.88,8,'https://www.gamereactor.es/media/39/ageempires2_3013963b.jpg','2020-07-23 12:07:54','2020-07-13 12:31:02',2),('me2',37,1.00,7,'','2020-07-24 08:32:07','2020-07-13 12:31:02',2),('super mario land 17',41,2.00,7,'','2020-07-24 09:35:33',NULL,2),('realtimeStrategy',42,5.00,2,'https://picsum.photos/100','2020-07-24 12:02:40','2020-07-13 12:31:02',1);
/*!40000 ALTER TABLE `juegos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id_rol` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_rol` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'usuario'),(2,'administrador');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `imagen` varchar(255) COLLATE utf8_spanish_ci NOT NULL DEFAULT 'https://picsum.photos/200',
  `pass` varchar(100) COLLATE utf8_spanish_ci NOT NULL DEFAULT 'e10adc3949ba59abbe56e057f20f883e',
  `id_rol` int(11) NOT NULL DEFAULT '1',
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `usuarios_UN` (`nombre`),
  KEY `FK_usuarios_roles` (`id_rol`),
  CONSTRAINT `FK_usuarios_roles` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tabla para gestionar usuarios';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'admin','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',2,NULL),(2,'alvaro','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(3,'Magee','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(4,'Kermit','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(5,'Oleg','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(6,'Cassidy','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(7,'Aaron','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(8,'Hedwig','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(9,'Alan','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(10,'Emery','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(11,'Macy','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(12,'Walker','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(13,'Xavier','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(14,'Dale','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(15,'Cole','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(16,'Zephr','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(17,'Charissa','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(18,'Lance','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(19,'David','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(20,'Ezra','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(21,'Quinn','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(22,'Cherokee','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(23,'Cody','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(24,'Katelyn','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(25,'Carly','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(26,'Asher','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(27,'Larissa','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(28,'Teegan','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(29,'Courtney','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(30,'Rhona','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(31,'Caldwell','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(32,'Wendy','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(33,'Rajah','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(34,'Evan','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(35,'Danielle','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(36,'Camilla','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(37,'Tana','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(38,'Lunea','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(39,'Leslie','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(40,'Cruz','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(41,'Eden','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(42,'Byron','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(43,'Ryder','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(44,'Ronan','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(45,'Norman','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(46,'Whoopi','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(47,'Honorato','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(48,'Tallulah','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(49,'Gloria','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(50,'Ira','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(51,'Barry','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(52,'Callum','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(53,'Zia','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(54,'Logan','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(55,'Xena','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(56,'Paul','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(57,'Elmo','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(58,'Gil','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(59,'Eliana','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(60,'Ursula','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(61,'Colby','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(62,'Basil','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(63,'Justine','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(64,'Craig','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(65,'Candace','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(66,'Burton','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(67,'Sean','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(68,'Urielle','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(69,'Tanek','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(70,'Guy','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(71,'Uta','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(72,'Desiree','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(73,'Kenneth','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(74,'Talon','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(75,'Yuli','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(76,'Jennifer','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(77,'Camille','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(78,'Amos','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(79,'Fallon','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(80,'Micah','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(81,'Darius','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(82,'Audra','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(83,'Jonas','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(85,'Daryl','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(86,'Xyla','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(87,'Caesar','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(88,'Carolyn','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(89,'Wanda','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(90,'Porter','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(91,'Jordan','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(92,'Cadman','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(93,'Hiram','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(95,'Oscar','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(96,'Levi','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(97,'Bell','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(98,'Shoshana','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL),(99,'Lani','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1,NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `v_usuarios_juegos`
--

DROP TABLE IF EXISTS `v_usuarios_juegos`;
/*!50001 DROP VIEW IF EXISTS `v_usuarios_juegos`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_usuarios_juegos` AS SELECT 
 1 AS `id_usuario`,
 1 AS `total`,
 1 AS `validados`,
 1 AS `pendientes`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'catalogoJuegos'
--
/*!50003 DROP PROCEDURE IF EXISTS `pa_juego_num_total` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`debian-sys-maint`@`localhost` PROCEDURE `pa_juego_num_total`(OUT salida INT)
BEGIN
	SET salida=(SELECT COUNT(id) FROM catalogoJuegos.juegos);
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_juego_num_totalPendiente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`debian-sys-maint`@`localhost` PROCEDURE `pa_juego_num_totalPendiente`(OUT numJuegosPendientes INT)
BEGIN
	SET numJuegosPendientes= (SELECT COUNT(j.id) FROM catalogoJuegos.juegos j WHERE j.fecha_validado IS NULL); 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_usuario_getAll` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`debian-sys-maint`@`localhost` PROCEDURE `pa_usuario_getAll`()
BEGIN
	SELECT u.nombre,u.id,u.imagen ,u.pass ,r.id_rol ,r.nombre_rol 
	FROM usuarios u INNER JOIN roles r ON u.id_rol =r.id_rol ;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_usuario_num_total` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`debian-sys-maint`@`localhost` PROCEDURE `pa_usuario_num_total`(OUT totalUsuarios INT)
BEGIN
	SET totalUsuarios =(SELECT COUNT(u.id) FROM usuarios u);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Current Database: `catalogoJuegos`
--

USE `catalogoJuegos`;

--
-- Final view structure for view `v_usuarios_juegos`
--

/*!50001 DROP VIEW IF EXISTS `v_usuarios_juegos`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`debian-sys-maint`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `v_usuarios_juegos` AS select `j`.`id_usuario` AS `id_usuario`,count(`j`.`fecha_creacion`) AS `total`,count(`j`.`fecha_validado`) AS `validados`,sum(isnull(`j`.`fecha_validado`)) AS `pendientes` from `juegos` `j` group by `j`.`id_usuario` */;
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

-- Dump completed on 2020-09-23 14:11:42
