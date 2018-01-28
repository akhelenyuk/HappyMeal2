CREATE DATABASE  IF NOT EXISTS `happy_meal3` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `happy_meal3`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: happy_meal
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `calories` int(11) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='List of exercises';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (1,'running',800),(2,'football',612),(3,'yoga',420),(4,'swimming',720),(5,'bicycling',560),(6,'jym',518),(7,'bowling',273),(8,'volleyball',364),(9,'skiing',391),(10,'tennis',728),(11,'Test1',999),(12,'TEst',123),(13,'МАрё\'яна',12),(14,'укеуке',123);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_diary`
--

DROP TABLE IF EXISTS `activity_diary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_diary` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `activity_id` int(11) unsigned NOT NULL,
  `time_spent` int(11) unsigned NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_userss_idx` (`user_id`),
  KEY `fk_user_idx` (`user_id`),
  KEY `fk_activity_idx` (`activity_id`),
  CONSTRAINT `fk_activity` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='Tracks exercises of users';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_diary`
--

LOCK TABLES `activity_diary` WRITE;
/*!40000 ALTER TABLE `activity_diary` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_diary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gender`
--

DROP TABLE IF EXISTS `gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gender` (
  `id` tinyint(1) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gender`
--

LOCK TABLES `gender` WRITE;
/*!40000 ALTER TABLE `gender` DISABLE KEYS */;
INSERT INTO `gender` VALUES (2,'female'),(1,'male');
/*!40000 ALTER TABLE `gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lifestyle`
--

DROP TABLE IF EXISTS `lifestyle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lifestyle` (
  `id` tinyint(1) unsigned NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lifestyle`
--

LOCK TABLES `lifestyle` WRITE;
/*!40000 ALTER TABLE `lifestyle` DISABLE KEYS */;
INSERT INTO `lifestyle` VALUES (1,'active'),(2,'average'),(3,'lazy');
/*!40000 ALTER TABLE `lifestyle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meal_diary`
--

DROP TABLE IF EXISTS `meal_diary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meal_diary` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `product_id` int(11) unsigned NOT NULL,
  `weight` int(10) unsigned NOT NULL DEFAULT '0',
  `meal_type_id` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_user_idx` (`user_id`),
  KEY `fk_user_meal_diary_idx` (`user_id`),
  KEY `fk_product_meal_diary_idx` (`product_id`),
  KEY `fk_meal_type_meal_diary_idx` (`meal_type_id`),
  CONSTRAINT `fk_meal_type_meal_diary` FOREIGN KEY (`meal_type_id`) REFERENCES `meal_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_meal_diary` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_meal_diary` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=264 DEFAULT CHARSET=utf8 COMMENT='Tracks users'' food consumption ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal_diary`
--

LOCK TABLES `meal_diary` WRITE;
/*!40000 ALTER TABLE `meal_diary` DISABLE KEYS */;
/*!40000 ALTER TABLE `meal_diary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meal_type`
--

DROP TABLE IF EXISTS `meal_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meal_type` (
  `id` tinyint(1) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal_type`
--

LOCK TABLES `meal_type` WRITE;
/*!40000 ALTER TABLE `meal_type` DISABLE KEYS */;
INSERT INTO `meal_type` VALUES (1,'Breakfast'),(5,'Dinner'),(4,'Early dinner'),(2,'Early lunch'),(6,'Late dinner'),(3,'Lunch');
/*!40000 ALTER TABLE `meal_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `calories` int(11) unsigned NOT NULL DEFAULT '0',
  `protein` decimal(4,1) unsigned NOT NULL DEFAULT '0.0',
  `fat` decimal(4,1) unsigned NOT NULL DEFAULT '0.0',
  `carbs` decimal(4,1) unsigned NOT NULL DEFAULT '0.0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Икра заморская баклажанная',132,1.1,11.9,5.5),(2,'Buckwheat (boiled)',89,3.6,0.9,17.6),(3,'BigMac hamburger (McDonald\'s)',495,27.0,25.0,40.0),(4,'BigTasty hamburger (McDonald\'s)',850,48.0,51.0,50.0),(7,'French fries (McDonald\'s)',323,3.0,16.0,43.0),(18,'Broccoli',28,3.0,0.4,5.2),(19,'Sweet pepper',29,1.0,0.0,7.0),(20,'Potato',76,2.0,0.4,16.1),(21,'Rice',116,2.2,0.5,24.9),(22,'Buckwheat (raw)',350,13.0,3.0,68.0),(27,'Muffin (chocolate)',350,6.0,12.0,55.0),(28,'Latte',125,6.0,7.0,10.0),(30,'Pasta',235,12.0,12.0,12.0),(31,'Seafood Paella',310,39.0,4.0,46.0),(32,'TTT',123,99.0,99.0,99.0),(33,'TTT2',12,99.0,2.0,12.0),(34,'TTT3',12,12.0,12.0,4.0),(35,'TTT4',122,12.3,12.3,12.0),(37,'Test3',12,12.0,12.0,12.0);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` tinyint(1) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (9,'admin'),(1,'user');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `id` tinyint(1) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='User status (blocked/unblocked)	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (2,'blocked'),(1,'unblocked');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `first_name` varchar(45) DEFAULT 'User',
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `birthday` date NOT NULL,
  `gender_id` tinyint(1) unsigned NOT NULL DEFAULT '2',
  `weight` decimal(5,1) unsigned NOT NULL,
  `goal_weight` decimal(5,1) unsigned NOT NULL,
  `height` int(11) unsigned NOT NULL,
  `lifestyle_id` tinyint(1) unsigned NOT NULL DEFAULT '2',
  `calorie_norm` int(11) unsigned NOT NULL,
  `role_id` tinyint(1) unsigned NOT NULL DEFAULT '1',
  `status_id` tinyint(1) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `fk_gender_idx` (`gender_id`),
  KEY `fk_role_idx` (`role_id`),
  KEY `fk_status_key_idx` (`status_id`),
  KEY `fk_lifestyle_idx` (`lifestyle_id`),
  CONSTRAINT `fk_gender` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_lifestyle` FOREIGN KEY (`lifestyle_id`) REFERENCES `lifestyle` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_status` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'spetrov','1','Сергей','Петров','srg@gmail.com','2001-05-11',1,70.0,67.0,175,1,2345,1,2),(2,'rtymchenko','1','Роман','Тимченко','roma@gmail.com','1991-03-08',1,80.0,0.0,180,1,2345,1,2),(3,'oteresch','1','Олег','Терещенко','oleg@gmail.com','2001-08-27',1,70.0,0.0,175,2,2345,1,1),(4,'ttymchenko','1','Татьяна','Тимченко','tanya@gmail.com','1975-01-02',2,55.0,0.0,167,1,2345,1,2),(5,'epiatochkina','1','Елена','Пяточкина','lenka@gmail.com','1991-03-08',2,50.0,0.0,163,2,2345,1,1),(6,'santonov','1','Сергей','Антонов','antoha@gmail.com','1985-12-08',1,85.0,0.0,184,3,2345,1,2),(7,'','','Jamie','Oliver','jamie@gmail.com','1984-10-01',1,90.0,80.0,180,3,2238,9,1),(8,'npane','1','Nick','1','npane@gmail.com','1992-06-09',1,100.0,90.0,196,1,3078,1,1);
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

-- Dump completed on 2018-01-28  1:10:53
