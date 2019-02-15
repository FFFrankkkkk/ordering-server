-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: localhost    Database: ordering
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `orderId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(10) unsigned NOT NULL,
  `productsId` varchar(255) NOT NULL DEFAULT '',
  `ProductsNum` varchar(255) NOT NULL DEFAULT '',
  `orderTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` enum('unconfirmed','confirmed') NOT NULL DEFAULT 'unconfirmed',
  `address` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`orderId`),
  KEY `userId1` (`userId`),
  CONSTRAINT `userId1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `productId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `productType` varchar(255) NOT NULL,
  `price` varchar(255) NOT NULL,
  `productName` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `url` varchar(255) NOT NULL DEFAULT '',
  `imgUrl` varchar(255) NOT NULL DEFAULT '',
  `staticHtml` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否生成过静态html网页',
  PRIMARY KEY (`productId`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (47,'促销产品和热卖产品','10','元气满分双人早餐','好吃','','http://localhost:8080/ordering/upload/images/products/1000.png-.jpg','\0'),(48,'促销产品和热卖产品','12','鲜美烟肉早晨全餐','好吃','','http://localhost:8080/ordering/upload/images/products/1010.png-.jpg','\0'),(49,'促销产品和热卖产品','11','周末全家满分早餐','好吃','','http://localhost:8080/ordering/upload/images/products/1088.png-.jpg','\0'),(50,'促销产品和热卖产品','13','麦满分心水早餐','好吃','','http://localhost:8080/ordering/upload/images/products/1106.png-.jpg','\0'),(51,'促销产品和热卖产品','14','吉士炒双蛋堡','好吃','','http://localhost:8080/ordering/upload/images/products/1119.png-.jpg','\0'),(52,'小食','10','元气满分双人早餐','好吃','','http://localhost:8080/ordering/upload/images/products/1000.png-.jpg','\0');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producttype`
--

DROP TABLE IF EXISTS `producttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producttype` (
  `productTypeId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`productTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producttype`
--

LOCK TABLES `producttype` WRITE;
/*!40000 ALTER TABLE `producttype` DISABLE KEYS */;
INSERT INTO `producttype` VALUES (6,'促销产品和热卖产品'),(7,'主食和超值早晨套餐'),(8,'小食'),(9,'甜品'),(10,'饮品'),(11,'开心乐园餐');
/*!40000 ALTER TABLE `producttype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` enum('manager','user') NOT NULL DEFAULT 'user',
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `headIconUrl` varchar(255) NOT NULL DEFAULT '',
  `registerDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `enable` enum('stop','use') NOT NULL DEFAULT 'stop',
  `email` varchar(255) NOT NULL DEFAULT '',
  `phone` varchar(255) NOT NULL DEFAULT '',
  `salt` varchar(255) NOT NULL,
  `openId` varchar(255) NOT NULL DEFAULT '',
  `accessToken` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `name` (`name`),
  KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'manager','a123123123','VZH0zl+9i/AJ8vPORJxJKg==','/ordering/upload/images/headIcon/0.jpg','2018-10-28 12:42:15','use','9582256@qq.com','13422892533','540v_8z6q6x(cjc','','');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userinformation`
--

DROP TABLE IF EXISTS `userinformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userinformation` (
  `userInformationId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userId` int(10) unsigned NOT NULL,
  `sex` enum('男','女') NOT NULL,
  `address` varchar(100) NOT NULL,
  PRIMARY KEY (`userInformationId`),
  KEY `userid` (`userId`),
  CONSTRAINT `userid` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userinformation`
--

LOCK TABLES `userinformation` WRITE;
/*!40000 ALTER TABLE `userinformation` DISABLE KEYS */;
/*!40000 ALTER TABLE `userinformation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-15 23:06:19
