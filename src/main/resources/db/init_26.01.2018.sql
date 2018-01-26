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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `calories` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `activity_id` int(11) DEFAULT NULL,
  `time_spent` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='Tracks exercises of users';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_diary`
--

LOCK TABLES `activity_diary` WRITE;
/*!40000 ALTER TABLE `activity_diary` DISABLE KEYS */;
INSERT INTO `activity_diary` VALUES (1,2,4,30,'2018-01-01'),(2,4,5,45,'2017-12-01'),(3,7,1,120,'2018-01-16'),(4,1,6,15,'2018-01-17'),(5,9,8,50,'2018-01-17'),(6,9,3,34,'2018-01-20'),(7,9,2,35,'2018-01-21'),(8,9,6,75,'2018-01-21'),(9,9,7,60,'2018-01-21'),(10,9,1,60,'2018-01-15'),(11,9,3,75,'2018-01-15'),(12,9,1,30,'2018-01-21'),(13,9,9,280,'2018-01-21'),(14,9,1,30,'2018-01-23'),(15,9,6,45,'2018-01-23'),(16,9,5,30,'2018-01-25'),(17,9,4,45,'2018-01-26'),(18,9,5,30,'2018-01-25'),(19,9,5,30,'2018-01-27'),(20,9,5,30,'2018-01-25'),(21,9,1,45,'2018-01-25');
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
  `id` tinyint(1) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
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
  `user_id` int(11) unsigned DEFAULT NULL,
  `product_id` int(11) unsigned DEFAULT NULL,
  `weight` int(10) unsigned DEFAULT '0',
  `meal_type_id` int(10) unsigned DEFAULT '1',
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=251 DEFAULT CHARSET=utf8 COMMENT='Tracks users'' food consumption ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal_diary`
--

LOCK TABLES `meal_diary` WRITE;
/*!40000 ALTER TABLE `meal_diary` DISABLE KEYS */;
INSERT INTO `meal_diary` VALUES (183,9,31,100,1,'2018-01-10'),(184,9,31,100,1,'2018-01-10'),(185,9,4,100,1,'2017-10-25'),(186,9,27,200,5,'2017-10-25'),(187,9,22,333,5,'2018-08-10'),(188,9,31,100,1,'2018-01-11'),(189,9,31,100,1,'2018-01-12'),(190,9,31,100,1,'2018-01-12'),(191,9,31,100,1,'2018-01-12'),(192,9,31,100,1,'2018-01-12'),(193,9,31,100,1,'2018-01-12'),(194,9,31,100,1,'2018-01-12'),(195,9,31,100,1,'2018-01-12'),(196,9,31,100,1,'2018-01-12'),(197,9,31,100,1,'2018-01-12'),(198,9,31,100,1,'2018-01-12'),(199,8,31,100,1,'2018-01-12'),(200,9,31,100,3,'2018-01-15'),(201,9,31,100,1,'2018-01-15'),(202,9,3,100,1,'2018-01-18'),(203,9,3,100,3,'2018-01-18'),(204,9,3,100,6,'2018-01-18'),(205,9,3,100,4,'2018-01-18'),(206,9,3,100,1,'2018-01-18'),(207,9,3,100,1,'2018-01-18'),(208,9,7,234,4,'2018-01-18'),(209,9,21,234,1,'2018-01-18'),(210,9,3,100,1,'2018-01-19'),(211,9,3,100,1,'2018-01-19'),(212,9,3,100,3,'2018-01-19'),(213,9,3,100,4,'2018-01-19'),(214,9,3,100,5,'2018-01-19'),(215,9,27,130,2,'2018-01-19'),(216,9,31,543,3,'2018-01-19'),(217,9,3,100,1,'2018-01-20'),(218,9,4,234,2,'2018-01-20'),(219,9,3,100,1,'2018-01-20'),(220,9,3,100,1,'2018-01-21'),(221,9,3,100,1,'2018-01-21'),(222,9,7,100,3,'2018-01-21'),(223,9,7,100,3,'2018-01-21'),(224,9,7,100,3,'2018-01-21'),(225,9,7,100,3,'2018-01-21'),(226,9,7,100,3,'2018-01-21'),(227,9,3,100,1,'2018-01-21'),(228,9,3,100,1,'2018-01-21'),(229,9,3,100,1,'2018-01-21'),(230,9,3,100,1,'2018-01-21'),(231,9,20,345,3,'2018-01-23'),(232,9,7,100,1,'2018-01-20'),(233,9,3,100,1,'2018-01-24'),(234,9,18,234,1,'2018-01-24'),(235,9,3,100,3,'2018-01-24'),(236,9,19,233,5,'2018-01-24'),(237,9,30,300,6,'2018-01-24'),(238,9,34,100,6,'2018-01-24'),(239,9,35,100,1,'2018-01-24'),(240,9,3,10,1,'2018-01-24'),(241,9,3,100,1,'2018-01-25'),(242,9,3,100,1,'2018-01-25'),(243,9,3,100,1,'2018-01-25'),(244,9,3,100,1,'2018-01-25'),(245,9,3,100,1,'2018-01-25'),(246,9,3,100,1,'2018-01-26'),(247,9,30,234,4,'2018-01-26'),(248,9,3,999,1,'2018-01-16'),(249,9,3,100,1,'2018-01-25'),(250,9,37,100,1,'2018-01-25');
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
  `calories` decimal(4,0) unsigned DEFAULT '0',
  `protein` decimal(4,1) unsigned DEFAULT '0.0',
  `fat` decimal(4,1) unsigned DEFAULT '0.0',
  `carbs` decimal(4,1) unsigned DEFAULT '0.0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='User status (blocked/unblocked)	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'unblocked'),(2,'blocked');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `gender_id` int(11) DEFAULT NULL,
  `weight` decimal(5,1) DEFAULT NULL,
  `goal_weight` decimal(5,1) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `lifestyle_id` int(11) DEFAULT '2',
  `calorie_norm` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT '1',
  `status_id` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'spetrov','1','Сергей','Петров','srg@gmail.com','2001-05-11',1,70.0,NULL,175,1,2345,1,1),(2,'rtymchenko','1','Роман','Тимченко','roma@gmail.com','1991-03-08',1,80.0,NULL,180,1,2345,1,1),(3,'oteresch','1','Олег','Терещенко','oleg@gmail.com','2001-08-27',1,70.0,NULL,175,2,2345,1,1),(4,'ttymchenko','1','Татьяна','Тимченко','tanya@gmail.com','1975-01-02',2,55.0,NULL,167,1,2345,1,1),(5,'epiatochkina','1','Елена','Пяточкина','lenka@gmail.com','1991-03-08',2,50.0,NULL,163,2,2345,1,1),(6,'santonov','1','Сергей','Антонов','antoha@gmail.com','1985-12-08',1,85.0,NULL,184,3,2345,1,1),(7,'','','Jamie','Oliver','jamie@gmail.com','1975-01-02',1,83.0,80.0,180,2,2345,9,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `water_diary`
--

DROP TABLE IF EXISTS `water_diary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `water_diary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Tracks water consumption by users';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `water_diary`
--

LOCK TABLES `water_diary` WRITE;
/*!40000 ALTER TABLE `water_diary` DISABLE KEYS */;
INSERT INTO `water_diary` VALUES (1,9,1500,'2018-01-17'),(2,3,2000,'2018-01-17');
/*!40000 ALTER TABLE `water_diary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weight_diary`
--

DROP TABLE IF EXISTS `weight_diary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `weight_diary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Track weight change of users';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weight_diary`
--

LOCK TABLES `weight_diary` WRITE;
/*!40000 ALTER TABLE `weight_diary` DISABLE KEYS */;
INSERT INTO `weight_diary` VALUES (1,9,80,'2018-01-17'),(2,9,82,'2018-01-10');
/*!40000 ALTER TABLE `weight_diary` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-26 17:10:58
