-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 192.168.1.43    Database: LastEpochItemDB
-- ------------------------------------------------------
-- Server version	8.0.20-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Affix`
--

DROP TABLE IF EXISTS `Affix`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Affix` (
  `idAffix` int NOT NULL,
  `affixName` varchar(100) DEFAULT NULL,
  `affixDisplayName` varchar(100) DEFAULT NULL,
  `affixTitle` varchar(100) DEFAULT NULL,
  `affixId` int DEFAULT NULL,
  `levelRequirement` int DEFAULT NULL,
  `rollsOn` int DEFAULT NULL,
  `classSpecificity` int DEFAULT NULL,
  `type` int DEFAULT NULL,
  `standardAffixEffectModifier` decimal(10,0) DEFAULT NULL,
  `rerollChance` decimal(10,0) DEFAULT NULL,
  `weaponEffect` int DEFAULT NULL,
  `group` int DEFAULT NULL,
  `shardHueShift` decimal(10,0) DEFAULT NULL,
  `shardSaturationModifier` decimal(10,0) DEFAULT NULL,
  `canRollOn` varchar(100) DEFAULT NULL,
  `property` int DEFAULT NULL,
  `specialTag` int DEFAULT NULL,
  `tags` int DEFAULT NULL,
  `modifierType` int DEFAULT NULL,
  `setProperty` int DEFAULT NULL,
  PRIMARY KEY (`idAffix`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `BaseItem`
--

DROP TABLE IF EXISTS `BaseItem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `BaseItem` (
  `idBaseItem` int NOT NULL,
  `baseTypeName` varchar(45) DEFAULT NULL,
  `baseDisplayName` varchar(45) DEFAULT NULL,
  `baseTypeID` varchar(45) DEFAULT NULL,
  `maximumAffixes` varchar(45) DEFAULT NULL,
  `maxSockets` varchar(45) DEFAULT NULL,
  `affixEffectModifier` varchar(45) DEFAULT NULL,
  `gridSizeX` varchar(45) DEFAULT NULL,
  `gridSizeY` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `isWeapon` varchar(45) DEFAULT NULL,
  `minimumDropLevel` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idBaseItem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Item`
--

DROP TABLE IF EXISTS `Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Item` (
  `idItem` int NOT NULL,
  `baseTypeName` varchar(100) DEFAULT NULL,
  `baseDisplayName` varchar(100) DEFAULT NULL,
  `baseTypeID` int DEFAULT NULL,
  `maximumAffixes` int DEFAULT NULL,
  `maxSockets` int DEFAULT NULL,
  `affixEffectModifier` decimal(10,0) DEFAULT NULL,
  `gridSizeX` int DEFAULT NULL,
  `gridSizeY` int DEFAULT NULL,
  `type` int DEFAULT NULL,
  `isWeapon` int DEFAULT NULL,
  `minimumDropLevel` int DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `displayName` varchar(100) DEFAULT NULL,
  `subTypeID` int DEFAULT NULL,
  `levelRequirement` int DEFAULT NULL,
  `cannotDrop` int DEFAULT NULL,
  `itemTags` int DEFAULT NULL,
  `classRequirement` int DEFAULT NULL,
  `subClassRequirement` int DEFAULT NULL,
  `attackRate` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`idItem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ItemProperty`
--

DROP TABLE IF EXISTS `ItemProperty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ItemProperty` (
  `idItemProperty` int NOT NULL,
  `idItem` int DEFAULT NULL,
  `idProperty` int DEFAULT NULL,
  `specialTag` int DEFAULT NULL,
  `tags` int DEFAULT NULL,
  `type` int DEFAULT NULL,
  `implicitValue` decimal(10,0) DEFAULT NULL,
  `implicitMaxValue` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`idItemProperty`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Property`
--

DROP TABLE IF EXISTS `Property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Property` (
  `idProperty` int NOT NULL,
  `propertyName` varchar(100) DEFAULT NULL,
  `defaultAltText` varchar(100) DEFAULT NULL,
  `roundAddedToInt` int DEFAULT NULL,
  `displayAddedAsPercentage` int DEFAULT NULL,
  `displayAddedAsTenthOfValue` int DEFAULT NULL,
  `displayAsPercentageOf` int DEFAULT NULL,
  `displayAsAddedTo` int DEFAULT NULL,
  `dontDisplayPlus` int DEFAULT NULL,
  `lessIsBetter` int DEFAULT NULL,
  `property` int DEFAULT NULL,
  `specialTag` int DEFAULT NULL,
  `tags` int DEFAULT NULL,
  `modType` int DEFAULT NULL,
  `overrideAltText` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`idProperty`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Tier`
--

DROP TABLE IF EXISTS `Tier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Tier` (
  `idTier` int NOT NULL,
  `idAffix` int DEFAULT NULL,
  `tierLevel` int DEFAULT NULL,
  `requiredLevel` int DEFAULT NULL,
  `minRoll` decimal(10,0) DEFAULT NULL,
  `maxRoll` decimal(10,0) DEFAULT NULL,
  `extraRollMinRoll` decimal(10,0) DEFAULT NULL,
  `extraRollMaxRoll` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`idTier`),
  KEY `FK_ID_AFFIX_idx` (`idAffix`),
  CONSTRAINT `FK_ID_AFFIX` FOREIGN KEY (`idAffix`) REFERENCES `Affix` (`idAffix`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-05 13:21:11
