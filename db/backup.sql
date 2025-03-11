-- MySQL dump 10.13  Distrib 9.2.0, for Linux (aarch64)
--
-- Host: localhost    Database: to_do
-- ------------------------------------------------------
-- Server version	9.2.0

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
-- Current Database: `to_do`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `to_do` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `to_do`;

--
-- Table structure for table `anuncios`
--

DROP TABLE IF EXISTS `anuncios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anuncios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data_criacao` datetime(6) NOT NULL,
  `descricao` varchar(1000) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `predio_id` bigint NOT NULL,
  `status` enum('CANCELADO','PENDENTE','PREVISTO','RESOLVIDO') DEFAULT NULL,
  `data` datetime(6) DEFAULT NULL,
  `predio` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnecnj37gi6khpe556hinrnqsk` (`predio_id`),
  CONSTRAINT `FKnecnj37gi6khpe556hinrnqsk` FOREIGN KEY (`predio_id`) REFERENCES `predio` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anuncios`
--

LOCK TABLES `anuncios` WRITE;
/*!40000 ALTER TABLE `anuncios` DISABLE KEYS */;
INSERT INTO `anuncios` VALUES (1,'2025-03-07 19:50:23.096395','Haverá manutenção nos elevadores na próxima segunda-feira.','Nova manutenção',1,'RESOLVIDO','2025-03-15 00:00:00.000000',1),(3,'2025-03-07 20:01:17.629800','Haverá manutenção nos elevadores na próxima segunda-feira.','Nova manutenção',1,'PENDENTE','2025-03-15 00:00:00.000000',NULL),(4,'2025-03-11 18:07:39.621355','','Nova manutenção',1,'PENDENTE','2025-03-11 00:00:00.000000',NULL),(5,'2025-03-11 18:13:03.167297','','Teste',1,'PENDENTE','2025-03-11 00:00:00.000000',NULL),(6,'2025-03-11 18:16:27.927040','','Teste',1,'PENDENTE','2025-03-12 00:00:00.000000',NULL),(7,'2025-03-11 18:17:18.072675','','Teste3',1,'PENDENTE','2025-03-13 00:00:00.000000',NULL);
/*!40000 ALTER TABLE `anuncios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartamento`
--

DROP TABLE IF EXISTS `apartamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `apartamento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `apnum` int NOT NULL,
  `status` enum('MANUTENCAO','OCUPADO','VAGO') DEFAULT NULL,
  `predio_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKas1k17xv4gv6k5s0i8a6xnlmr` (`predio_id`),
  CONSTRAINT `FKas1k17xv4gv6k5s0i8a6xnlmr` FOREIGN KEY (`predio_id`) REFERENCES `predio` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartamento`
--

LOCK TABLES `apartamento` WRITE;
/*!40000 ALTER TABLE `apartamento` DISABLE KEYS */;
INSERT INTO `apartamento` VALUES (1,110,'OCUPADO',1),(2,102,'VAGO',1),(6,103,'VAGO',1),(7,104,'OCUPADO',1),(8,120,'OCUPADO',1),(10,200,'VAGO',1);
/*!40000 ALTER TABLE `apartamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cpf` bigint NOT NULL,
  `email` varchar(255) NOT NULL,
  `nascimento` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `pagamento` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `profissao` varchar(255) DEFAULT NULL,
  `rg` bigint NOT NULL,
  `telefone` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr1u8010d60num5vc8fp0q1j2a` (`cpf`),
  UNIQUE KEY `UKcmxo70m08n43599l3h0h07cc6` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contrato`
--

DROP TABLE IF EXISTS `contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contrato` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `codigo_contrato` varchar(255) NOT NULL,
  `entrada` date NOT NULL,
  `status_pagamento` enum('ATRASADO','PAGO','PENDENTE') DEFAULT NULL,
  `valor_aluguel` decimal(38,2) NOT NULL,
  `valor_condominio` decimal(38,2) NOT NULL,
  `valor_internet` decimal(38,2) NOT NULL,
  `valor_iptu` decimal(38,2) NOT NULL,
  `apartamento_id` bigint NOT NULL,
  `inquilino_id` bigint NOT NULL,
  `data_aceite` date DEFAULT NULL,
  `data_criacao` date NOT NULL,
  `processo` varchar(255) NOT NULL,
  `referente_ano` int NOT NULL,
  `status` enum('CANCELADO','DEFERIDO','INDEFERIDO') DEFAULT NULL,
  `titular` varchar(255) NOT NULL,
  `email_admin` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKemjk0vwvjxrr5xbivt5k5i5fi` (`codigo_contrato`),
  KEY `idx_codigo_contrato` (`codigo_contrato`),
  KEY `FK33d1e8onykpfs2tjgw37i2bk9` (`apartamento_id`),
  KEY `FK10829vimb44qsod1qsh5hjkw` (`inquilino_id`),
  CONSTRAINT `FK10829vimb44qsod1qsh5hjkw` FOREIGN KEY (`inquilino_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK33d1e8onykpfs2tjgw37i2bk9` FOREIGN KEY (`apartamento_id`) REFERENCES `apartamento` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contrato`
--

LOCK TABLES `contrato` WRITE;
/*!40000 ALTER TABLE `contrato` DISABLE KEYS */;
INSERT INTO `contrato` VALUES (1,_binary '','a7a96299-0e48-42e7-8a72-b5b0458a48c6','2025-03-07','PENDENTE',2000.00,500.00,100.00,150.00,1,4,'2025-03-07','2025-03-07','PROC-2025-001',2025,'DEFERIDO','Bruno Nobre','backupnobre62@gmail.com'),(2,_binary '','3aadb626-e5c3-4b95-b366-fb009454c3a8','2025-03-07','PENDENTE',2000.00,500.00,100.00,150.00,1,4,'2025-03-07','2025-03-07','PROC-2025-001',2025,'DEFERIDO','Bruno Nobre','backupnobre62@gmail.com'),(3,_binary '','8cac3fe0-2775-41b4-8f76-d2e2b555ec99','2025-03-07','PENDENTE',2000.00,500.00,100.00,150.00,1,6,'2025-03-07','2025-03-07','PROC-2025-001',2025,'DEFERIDO','João da Silva Souza','backupnobre62@gmail.com'),(4,_binary '','5751b2d4-8b37-4c8e-9dd7-f442eb2706cb','2025-03-07','PENDENTE',2000.00,500.00,100.00,150.00,1,4,'2025-03-07','2025-03-08','PROC-2025-001',2025,'DEFERIDO','Bruno Nobre','backupnobre62@gmail.com'),(5,_binary '','bb11ed90-0765-410a-b637-4690d24cbb10','2025-03-07','PENDENTE',2000.00,500.00,100.00,150.00,1,4,'2025-03-07','2025-03-08','PROC-2025-001',2025,'DEFERIDO','Bruno Nobre','backupnobre62@gmail.com'),(6,_binary '','b8685b95-9bd0-4317-abd1-c53633a773a0','2025-03-07','PENDENTE',2000.00,500.00,100.00,150.00,1,4,'2025-03-07','2025-03-08','PROC-2025-001',2025,'DEFERIDO','Bruno Nobre','backupnobre62@gmail.com'),(7,_binary '','31f9f10d-5ed8-4bd2-950d-22743961e65e','2025-03-07','PENDENTE',2000.00,500.00,100.00,150.00,1,4,'2025-03-07','2025-03-08','PROC-2025-001',2025,'DEFERIDO','Bruno Nobre','backupnobre62@gmail.com'),(8,_binary '','cb30d572-0741-48e3-a2e3-eab9cf05a226','2025-03-11','PENDENTE',2000.00,600.00,100.00,60.00,7,6,'2025-03-11','2025-03-11','ALUGUEL',2025,'DEFERIDO','João da Silva Souza','backupnobre62@gmail.com'),(9,_binary '','b3716a4f-69f0-416f-8bc0-c00739705d8d','2025-03-11','PENDENTE',5000.00,3000.00,100.00,60.00,8,6,'2025-03-11','2025-03-11','ALUGUEL',2025,'DEFERIDO','João da Silva Souza','backupnobre62@gmail.com');
/*!40000 ALTER TABLE `contrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `action` varchar(255) NOT NULL,
  `entity_id` bigint NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `tabela` varchar(255) NOT NULL,
  `timestamp` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (1,'save',1,NULL,'anuncio','2025-03-07 19:32:12.871244'),(2,'save',2,NULL,'anuncio','2025-03-07 19:40:35.111939'),(3,'update',1,NULL,'anuncio','2025-03-07 19:50:23.135929'),(4,'save',3,NULL,'anuncio','2025-03-07 20:01:17.692352'),(5,'delete',2,NULL,'anuncio','2025-03-07 20:02:01.583804'),(6,'delete',3,NULL,'apartamento','2025-03-09 22:57:16.556875'),(7,'delete',4,NULL,'apartamento','2025-03-09 22:57:22.488537'),(8,'delete',5,NULL,'apartamento','2025-03-09 22:57:26.223340'),(9,'delete',9,NULL,'apartamento','2025-03-09 22:58:20.094232'),(10,'save',4,NULL,'anuncio','2025-03-11 18:07:39.738710'),(11,'save',5,NULL,'anuncio','2025-03-11 18:13:03.198367'),(12,'save',6,NULL,'anuncio','2025-03-11 18:16:27.971253'),(13,'save',7,NULL,'anuncio','2025-03-11 18:17:18.093228');
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamentos`
--

DROP TABLE IF EXISTS `pagamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagamentos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uh` double DEFAULT NULL,
  `referente` double DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `titular` varchar(255) DEFAULT NULL,
  `vencimento` date NOT NULL,
  `id_apartamento` bigint NOT NULL,
  `valor` decimal(38,2) NOT NULL,
  `contrato_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp3nmck0lgd719836e8lphng3r` (`contrato_id`),
  CONSTRAINT `FKp3nmck0lgd719836e8lphng3r` FOREIGN KEY (`contrato_id`) REFERENCES `contrato` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamentos`
--

LOCK TABLES `pagamentos` WRITE;
/*!40000 ALTER TABLE `pagamentos` DISABLE KEYS */;
INSERT INTO `pagamentos` VALUES (1,NULL,NULL,'PENDENTE','Bruno Nobre','2025-03-10',1,2750.00,7),(2,NULL,NULL,'PAGO','Bruno Nobre','2025-04-10',1,2750.00,7),(3,NULL,NULL,'A_VENCER','Bruno Nobre','2025-05-10',1,3000.00,7),(4,NULL,NULL,'PAGO','Bruno Nobre','2025-06-10',1,2750.00,7),(5,NULL,NULL,'FUTURO','Bruno Nobre','2025-07-10',1,2750.00,7),(6,NULL,NULL,'FUTURO','Bruno Nobre','2025-08-10',1,2750.00,7),(7,NULL,NULL,'FUTURO','Bruno Nobre','2025-09-10',1,2750.00,7),(8,NULL,NULL,'FUTURO','Bruno Nobre','2025-10-10',1,2750.00,7),(9,NULL,NULL,'FUTURO','Bruno Nobre','2025-11-10',1,2750.00,7),(10,NULL,NULL,'FUTURO','Bruno Nobre','2025-12-10',1,2750.00,7),(11,NULL,NULL,'PAGO','Bruno Nobre','2025-03-10',1,1354.53,7),(12,NULL,NULL,'FUTURO','João da Silva Souza','2025-03-10',7,2760.00,8),(13,NULL,NULL,'FUTURO','João da Silva Souza','2025-04-10',7,2760.00,8),(14,NULL,NULL,'FUTURO','João da Silva Souza','2025-05-10',7,2760.00,8),(15,NULL,NULL,'FUTURO','João da Silva Souza','2025-06-10',7,2760.00,8),(16,NULL,NULL,'FUTURO','João da Silva Souza','2025-07-10',7,2760.00,8),(17,NULL,NULL,'FUTURO','João da Silva Souza','2025-08-10',7,2760.00,8),(18,NULL,NULL,'FUTURO','João da Silva Souza','2025-09-10',7,2760.00,8),(19,NULL,NULL,'FUTURO','João da Silva Souza','2025-10-10',7,2760.00,8),(20,NULL,NULL,'FUTURO','João da Silva Souza','2025-11-10',7,2760.00,8),(21,NULL,NULL,'FUTURO','João da Silva Souza','2025-03-10',8,8160.00,9),(22,NULL,NULL,'FUTURO','João da Silva Souza','2025-04-10',8,8160.00,9),(23,NULL,NULL,'FUTURO','João da Silva Souza','2025-05-10',8,8160.00,9),(24,NULL,NULL,'FUTURO','João da Silva Souza','2025-06-10',8,8160.00,9),(25,NULL,NULL,'FUTURO','João da Silva Souza','2025-07-10',8,8160.00,9),(26,NULL,NULL,'FUTURO','João da Silva Souza','2025-08-10',8,8160.00,9),(27,NULL,NULL,'FUTURO','João da Silva Souza','2025-09-10',8,8160.00,9),(28,NULL,NULL,'FUTURO','João da Silva Souza','2025-10-10',8,8160.00,9),(29,NULL,NULL,'FUTURO','João da Silva Souza','2025-11-10',8,8160.00,9);
/*!40000 ALTER TABLE `pagamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `predio`
--

DROP TABLE IF EXISTS `predio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `predio` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `endereco` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `predio`
--

LOCK TABLES `predio` WRITE;
/*!40000 ALTER TABLE `predio` DISABLE KEYS */;
INSERT INTO `predio` VALUES (1,NULL,'backupnobre62@gmail.com','Av. Pedro Basso, 1200, Centro, Foz do Iguaçu','Esmeralda','4535359898');
/*!40000 ALTER TABLE `predio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `cpf` varchar(255) DEFAULT NULL,
  `nascimento` varchar(255) DEFAULT NULL,
  `pagamento` varchar(255) DEFAULT NULL,
  `profissao` varchar(255) DEFAULT NULL,
  `rg` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','USER') DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'backupnobre62@gmail.com','Bruno','Nobre','$2a$10$Dl0luSpWy9V.GAX/H8wnV.rg0GUgQsrLY3C7gX5M6.TiOgf2EYf5S','14740721945','20031214','11234','Recepcionista','123123123','ADMIN','45984135947'),(5,'user@gmail.com','Bruno','Nobre','$2a$10$Dl0luSpWy9V.GAX/H8wnV.rg0GUgQsrLY3C7gX5M6.TiOgf2EYf5S','14740721945','20031214','11234','Recepcionista','123123123','USER','45984135947');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nascimento` date DEFAULT NULL,
  `nome` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `profissao` varchar(255) DEFAULT NULL,
  `rg` varchar(255) NOT NULL,
  `role` enum('ADMIN','USER') DEFAULT NULL,
  `sobrenome` varchar(255) NOT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK7kqluf7wl0oxs7n90fpya03ss` (`cpf`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK4f8eudp6ftyiu14cbk31oq9f3` (`rg`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'00100200304','backupnobre62@gmail.com','2025-03-07','Bruno','$2a$10$Dl0luSpWy9V.GAX/H8wnV.rg0GUgQsrLY3C7gX5M6.TiOgf2EYf5S','LandLord','010203','ADMIN','Nobre','45999999999'),(4,'00200300405','user@gmail.com','2025-03-07','Bruno','$2a$10$Dl0luSpWy9V.GAX/H8wnV.rg0GUgQsrLY3C7gX5M6.TiOgf2EYf5S','LandLord','020304','USER','Nobre','45999999999'),(6,'12345678900','joao.souza@email.com','1990-05-15','João','$2a$10$CDmHm7HQVRgxnUCjOq9q.OEM6H6ntW2lYU8odQtZ/ykL9lEeUVaDy','Arquiteto','12.345.678-9','USER','da Silva Souza','(11) 99876-5432'),(8,'00400500607','user2@gmail.com','2003-11-14','Breno','$2a$10$YvmX.5NAXWKnlqMqROu8A.2Iggd4NUjOME3eKmi4fowvueA3/4Dja','Policial','13131314','USER','Noble','4599999991999');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-11 18:37:57
