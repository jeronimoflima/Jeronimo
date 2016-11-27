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
-- Table structure for table `excursao`
--

DROP TABLE IF EXISTS `excursao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `excursao` (
  `id_excursao` int(8) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `categoria` varchar(45) DEFAULT NULL,
  `local_de_partida` varchar(45) NOT NULL,
  `total_participantes` int(11) NOT NULL,
  `data_partida` date NOT NULL,
  `min_participantes` int(11) NOT NULL,
  `valor` decimal(7,2) NOT NULL,
  `descricao` text NOT NULL,
  `imagem1` varchar(80) DEFAULT NULL,
  `imagem2` varchar(80) DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  `id_usuario` int(8) NOT NULL,
  `id_cidade` int(8) NOT NULL,
  PRIMARY KEY (`id_excursao`),
  KEY `fk_excursao_usuario1_idx` (`id_usuario`),
  KEY `fk_excursao_cidade1_idx` (`id_cidade`),
  CONSTRAINT `fk_excursao_cidade1` FOREIGN KEY (`id_cidade`) REFERENCES `cidade` (`id_cidade`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_excursao_usuario1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `excursao`
--

LOCK TABLES `excursao` WRITE;
/*!40000 ALTER TABLE `excursao` DISABLE KEYS */;
INSERT INTO `excursao` VALUES (4,'Oktoberfest','Festas','Centreventos',45,'2016-12-17',40,250.00,'A Oktoberfest (também conhecida como \"Wiesn\" em Munique[1]) é um festival de cerveja em Munique, criado pelo rei bávaro Ludwig I para celebrar o seu casamento em 1810. A Oktoberfest é também uma feira de produtos e diversões celebrada em Munique (München), no estado da Baviera (Bayern), no sul da Alemanha, e disseminada por vários lugares do mundo. A Oktoberfest é frequentado anualmente por seis milhões de visitantes de todo o mundo e se inicia desde 1872 sempre no sábado depois do 15 de Setembro as 12.00 horas com a tradicional cerimonia de abertura \"O\'zapft is\". Termina duas semanas mais tarde, no primeiro domingo de Outubro - daí o nome Oktoberfest (em alemão, \"Oktober\" significa outubro, \"Fest\", festa ou festival, literalmente \"Festa de Outubro\"). O preço de um litro cerveja é tradicionalmente uma questão política: em 2015 o jarro chega a €10,40 (US$ 11,60).','a557a79d-6cab-40b7-9039-62574bd8dc2e.jpg','442720d0-59f1-4605-86e4-4b2fb0944719.jpg','Excursão Garantida',32,24),(5,'Festa do Pinhão','Festas','Shopping Muller',45,'2017-03-03',35,368.00,'A cidade de Lages vive a expectativa para o início da 28ª\nFesta Nacional do Pinhão, que ocorre de 20 a 29 de maio. Falta pouco\nmais de 20 dias e até mesmo o clima entrou em ritmo de festa e\ncomeçou a mudar. De mansinho, o inverno chega na Serra Catarinense\npara a alegria dos turistas que aproveitam para se divertir no\nevento que carrega o charme da estação mais fria do ano. Cerca de 80\natrações nacionais, regionais e locais vão animar o público durante os 10 dias de festa.','bf3cdb33-460a-4c3e-bf36-4e5a4b0ddcd8.jpg','5f480664-abf7-4a7c-8d5f-aa114d9b7486.jpg','Excursão Garantida',32,24),(6,'Bete Carrero','Parques Temáticos','Frente Mercado Municipal',45,'2016-11-19',40,200.00,'dfsdfsfsdfdfs\nfsdfsdfs\nsdfsdfsd','','','Excursão Garantida',43,29),(9,'Formula 1 GP Interlafos','Esportes','Expoville',38,'2016-12-10',38,899.00,'Grande Prêmio do Brasil','','','Excursão Garantida',43,24),(10,'Festa no Apé','Festas','Joinville',50,'2016-12-14',35,300.00,'Muita festa....','4488a2f4-0193-4547-bd25-55675f0751dd.jpg','148f52ec-6ba0-45d4-94eb-ccb54a5bc054.jpg','Excursão Garantida',32,31);
/*!40000 ALTER TABLE `excursao` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-26 14:20:07
