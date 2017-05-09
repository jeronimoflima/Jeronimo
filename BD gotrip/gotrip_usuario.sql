-- MySQL dump 10.13  Distrib 5.6.24, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: gotrip
-- ------------------------------------------------------
-- Server version	5.5.46

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
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `tipo` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cpf` varchar(45) NOT NULL,
  `telefone` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `data_nascimento` date DEFAULT NULL,
  `id_endereco` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usuario_endereco2_idx` (`id_endereco`),
  CONSTRAINT `fk_usuario_endereco2` FOREIGN KEY (`id_endereco`) REFERENCES `endereco` (`id_endereco`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (21,1,'Jeronimo Fleith de Lima','65644543300','3322222334','jeronimoflima@yahoo.com.br','61c5c333020cc63da705c72a17377508',NULL,NULL),(32,2,'Du Bastos','77777777777','7876666666','duma@yahoo.com.br','61c5c333020cc63da705c72a17377508','2222-02-21',89),(33,2,'wwwww','44444444444','3444444444','www@yahoo.com.br','admin','2000-12-02',90),(35,2,'rrrr','55555555555','2133333333','jeronim323@yahoo.com.br','admin','2009-11-10',92),(36,2,'TRRR','55555555555','2133333333','trr@yahoo.com.br','admin','2009-11-10',93),(40,2,'Freddy','54545555555','3232332323','jgfg@gmail.com','admin','1998-12-31',96),(41,1,'Mariane Fulich','76454545444','4754444445','mari.fulich@gmail.com','61c5c333020cc63da705c72a17377508',NULL,NULL),(42,1,'Felipe Melo','65782344445','2454565556','flipea@yahoo.com.br','61c5c333020cc63da705c72a17377508',NULL,NULL),(43,2,'Bete Faria','00983321333','4755465646','bete@gmail.com','61c5c333020cc63da705c72a17377508','1978-08-04',97),(44,1,'Mario Conod de Lima','66666666666','5673333333','marion@gmail.com','61c5c333020cc63da705c72a17377508',NULL,NULL),(45,1,'Berta Fleith','00833827812','4738987272','berta@yahoo.com.br','61c5c333020cc63da705c72a17377508',NULL,NULL);
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

-- Dump completed on 2016-11-26 14:20:02
