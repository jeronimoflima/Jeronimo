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
-- Table structure for table `participante`
--

DROP TABLE IF EXISTS `participante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participante` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `data_nascimento` date NOT NULL,
  `cpf` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `telefone` varchar(45) NOT NULL,
  `sexo` char(9) NOT NULL,
  `rg` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `id_endereco` int(8) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_participante_endereco1_idx` (`id_endereco`),
  CONSTRAINT `fk_participante_endereco1` FOREIGN KEY (`id_endereco`) REFERENCES `endereco` (`id_endereco`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participante`
--

LOCK TABLES `participante` WRITE;
/*!40000 ALTER TABLE `participante` DISABLE KEYS */;
INSERT INTO `participante` VALUES (46,'Felipe','1989-10-17','33344433322','jeronimoflima@yahoo.com.br','1134667777','Masculino','44444','Ativo',45),(78,'Zé Comeia','2098-02-08','55555555555','jeronimoflima@yahoo.com.br','4734559909','Opção','43434343','Ativo',78),(79,'Kalel','2000-07-01','44444444444','klale@yahoo.com.br','2133333333','Masculino','2333333','Ativo',80),(80,'Caio Lessin','1996-06-12','98949939933','caio@yahoo.com.br','5444444443','Masculino','4977777','Ativo',98),(81,'ZZZaa','2000-11-10','34343222222','jeronimoflima@yahoo.com.br','2134343434','Masculino','34343434','Ativo',99),(85,'Gilmar','1980-09-20','45433333333','jeronimoflima@yahoo.com.br','2134343343','Masculino','343434343','Ativo',103),(86,'Francisco Coco','1968-07-22','00499399999','franci@yahoo.com.br','3233333334','Masculino','111111','Ativo',104),(87,'Vicente','1899-08-21','34343434343','vice@yahoo.com.br','4434343434','Masculino','454545455','Ativo',105),(88,'Thiago Fernando','1989-05-11','32323232323','jeronimoflima@yahoo.com.br','3234343434','Masculino','87689999','Ativo',106),(89,'Fabiane Mira','2000-11-10','56565656566','jeronimoflima@yahoo.com.br','4345465656','Feminino','3434343434','Ativo',107),(90,'Luana Maria','1969-12-31','45454544545','jeronimoflima@yahoo.com.br','4545454545','Masculino','22222222','Ativo',108),(92,'Laiz Maria','1998-06-20','23232323232','lala@yahoo.com.br','2323232323','Feminino','343434343','Ativo',110),(94,'João Vitor','1988-11-03','88899988800','joao@gmail.com','4756778899','Masculino','455566','Ativo',112),(95,'João Vitor','1988-11-03','88899988800','joao@gmail.com','4756778899','Masculino','455566','Ativo',113),(96,'Antonio','1978-03-20','45454545454','jeronimoflima@yahoo.com.br','3434343434','Opção','343434343','Ativo',114),(97,'Carlos FisherAAA','1987-07-20','88989898989','jeronimoflima@yahoo.com.br','1213434343','Masculino','56565656','Ativo',115),(98,'Alemão','2000-02-10','33333333333','alema@yahoo.com.br','2134550949','Masculino','3333333','Ativo',116),(99,'teste','1990-02-20','34343434434','jeronimoflima@yahoo.com.br','2143434343','Masculino','34343434','Ativo',117),(100,'Mppp','2000-06-10','34343343343','mpa@yahoo.com.br','2154545454','Masculino','dfdfdf','Ativo',118),(101,'Mppp','2000-06-10','34343343343','mpa@yahoo.com.br','2154545454','Masculino','dfdfdf','Ativo',119),(102,'Mppp','2000-06-10','34343343343','mpa@yahoo.com.br','2154545454','Masculino','434343','Ativo',120),(103,'Mppp','2016-06-10','34343343343','jeronimoflima@yahoo.com.br','2154545454','Masculino','434343','Ativo',121),(104,'Jonhy','2003-11-10','56565656565','jona@yahoo.com.br','3233333333','Opção','3333333','Ativo',122),(105,'JERO','1988-09-19','33333333333','jeronimoflima@yahoo.com.br','3232323232','Feminino','212121221','Ativo',123),(106,'JERO','1988-09-19','33333333333','hhh@yahoo.com.br','3232323232','Feminino','212121221','Ativo',124);
/*!40000 ALTER TABLE `participante` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-26 14:20:00
