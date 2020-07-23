-- MySQL dump 10.13  Distrib 5.7.30, for Linux (x86_64)
--
-- Host: localhost    Database: catalogoJuegos
-- ------------------------------------------------------
-- Server version	5.7.30-0ubuntu0.18.04.1

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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Listado de juegos';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `juegos`
--

LOCK TABLES `juegos` WRITE;
/*!40000 ALTER TABLE `juegos` DISABLE KEYS */;
INSERT INTO `juegos` VALUES ('super mario land 2',15,5.53,5,'https://images-na.ssl-images-amazon.com/images/I/91x1gsV0G3L._SX342_.jpg','2020-07-13 12:31:02',NULL,2),('super mario land 3',16,7.51,5,'https://photos.wowroms.com/emulators-roms-logo/26/10377/420-420/Wario-Land---Super-Mario-Land-3-(World).jpg','2020-07-13 12:31:02',NULL,2),('     super mario land 4',17,5.00,5,'https://picsum.photos/100','2020-07-13 12:31:02',NULL,1),('mario bros 2',18,0.00,5,'https://picsum.photos/100','2020-07-13 12:31:02','2020-07-13 12:31:02',1),('warriors orochi 3',19,20.00,4,'https://picsum.photos/100','2020-07-13 12:31:02',NULL,1),('mighty n9',20,20.00,1,'','2020-07-13 12:31:02',NULL,2),('just cause',22,0.00,4,'https://picsum.photos/100','2020-07-13 12:31:02',NULL,1),('gta liberty city',23,0.00,4,'https://picsum.photos/100','2020-07-13 12:31:02',NULL,1),('so',24,0.00,3,'https://picsum.photos/100','2020-07-13 12:31:02',NULL,1),('fallout 3',25,5.30,6,'https://picsum.photos/100','2020-07-13 12:31:02','2020-07-13 12:31:02',2),('turok 2',26,55.60,4,'https://picsum.photos/100','2020-07-13 12:31:02',NULL,1),('State of Decay 2',29,10.34,4,'https://avatarfiles.alphacoders.com/140/140854.jpg','2020-07-13 12:31:02',NULL,1),('farcry 4',30,14.50,4,'https://static.truetrophies.com/boxart/Game_2842.png','2020-07-13 12:31:02',NULL,2),('far cry primal',31,5.00,4,'https://images-na.ssl-images-amazon.com/images/I/71GYXIFRkcL._CR0,204,1224,1224_UX256.jpg','2020-07-13 12:31:02',NULL,1),('pac-man',33,2.00,3,'','2020-07-13 12:31:02',NULL,1),('fallout 4',34,3.00,3,'','2020-07-13 12:31:02',NULL,1);
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
  PRIMARY KEY (`id`),
  KEY `FK_usuarios_roles` (`id_rol`),
  CONSTRAINT `FK_usuarios_roles` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tabla para gestionar usuarios';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'admin','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',2),(2,'alvaro','https://picsum.photos/200','e10adc3949ba59abbe56e057f20f883e',1);
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

-- Dump completed on 2020-07-23  8:44:33
