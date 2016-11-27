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
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `endereco` (
  `id_endereco` int(8) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `numero` int(6) NOT NULL,
  `bairro` varchar(45) NOT NULL,
  `cep` int(8) NOT NULL,
  `id_cidade` int(8) NOT NULL,
  `complemento` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_endereco`),
  KEY `fk_endereco_Cidade1_idx` (`id_cidade`),
  CONSTRAINT `fk_endereco_Cidade1` FOREIGN KEY (`id_cidade`) REFERENCES `cidade` (`id_cidade`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (6,'Rua teste',22,'Guanabara',9999999,1,'teste'),(44,'Rua AAA',988,'Seuuuu',44455554,25,'asssss'),(45,'Rua Buaaaa',89,'Centro',44366666,20,'asssss'),(47,'ASASASASASAS',765,'ererereer',55555555,1,'jhjhjhjhjhj'),(50,'rtrtrtrtrtrt',55,'rtrtrtrtrtr',44444444,1,'trtrtrtrtr'),(51,'rtrtrtrtrtrt',55,'rtrtrtrtrtr',44444444,1,'trtrtrtrtr'),(53,'Rrrrrrr',21,'bvvvvvv',78999993,22,'fdee'),(67,'dfdfdddfd',21,'dfdfdfdf',55555555,24,'dfdfdfdf'),(78,'545454',454,'4545',66666666,24,'45454'),(79,'xxx',32,'xxx',55555555,24,'xxx'),(80,'xxx',21,'Centro',45555555,24,'xxx'),(84,'hghg',12,'ghgh',31222222,24,'ghhgh'),(85,'hghg',12,'ghgh',31222222,24,'ghhgh'),(86,'bbb',45,'bbb',65556565,26,'bbb'),(87,'vvv',4,'vvvv',33333333,24,'vvv'),(89,'dddd',3,'centro',77777777,24,'ddd'),(90,'www',54,'yyy',55555555,24,'wwww'),(92,'Rua Videira',3,'eeee',66666666,24,'rrr'),(93,'Rua Videira',3,'eeee',66666666,24,'rrr'),(94,'Rua Videira',44,'ddd',66666666,24,'xx'),(95,'xxx',32,'xxx',55555555,24,'xxx'),(96,'rerere',21,'dfdfd',54545454,24,'fgfg'),(97,'Rua Videira',33,'Atiradores',89338777,24,'tr'),(98,'Rua Bananal',23,'América',89332222,24,'cvcvc'),(99,'fgfgfgfgf',32,'fgfgfgf',43433334,24,'fdfd'),(103,'eereerer',4,'ererere',66666666,24,''),(104,'Rua Nenhuma',23,'Algum',56778777,30,''),(105,'sdsd',43,'sdsds',34343434,24,''),(106,'fdfdfdfdf',21,'dfdfdfdf',34343435,24,''),(107,'asasas',11,'asasas',76767676,24,''),(108,'weewewewe',55,'wewewewew',45454545,24,''),(110,'dfdferere',22,'fdfdfdfdf',34343434,24,'fundos'),(112,'Rua teste',22,'Guanabara',9999999,1,'teste'),(113,'Rua teste',22,'Guanabara',9999999,1,'teste'),(114,'sdsdsdd',33,'sdsdsdsd',34343434,24,'erer'),(115,'dfdfdf',32,'dfdfdf',65656565,26,''),(116,'Rua asssim',32,'São Pedro',32323232,24,''),(117,'dsdsds',232,'dsdsd',34343232,24,'wewewew'),(118,'Rua Videira',55,'dfdfdfdfdf',34343434,24,''),(119,'ideira',55,'dfdfdfdfdf',34343434,24,'wewewewe'),(120,'343434343',55,'dfdfdfdfdf',34343434,24,'wewewewe'),(121,'343434343',55,'dfdfdfdfdf',34343434,24,'wewewewe'),(122,'dsdsds',33,'sdsdsdsd',44343434,24,''),(123,'hhhhh',22,'2121212121',32333333,24,''),(124,'hhhhh',22,'2121212121',32333333,31,'');
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-26 14:19:58
