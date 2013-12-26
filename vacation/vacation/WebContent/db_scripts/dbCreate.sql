-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.32 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             8.1.0.4645
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for nofson
DROP DATABASE IF EXISTS `nofson`;
CREATE DATABASE IF NOT EXISTS `nofson` /*!40100 DEFAULT CHARACTER SET hebrew */;
USE `nofson`;


-- Dumping structure for table nofson.airlines
DROP TABLE IF EXISTS `airlines`;
CREATE TABLE IF NOT EXISTS `airlines` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=hebrew;

-- Data exporting was unselected.


-- Dumping structure for table nofson.airports
DROP TABLE IF EXISTS `airports`;
CREATE TABLE IF NOT EXISTS `airports` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `City_ID` int(50) DEFAULT NULL,
  `Name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_airports_cities` (`City_ID`),
  CONSTRAINT `FK_airports_cities` FOREIGN KEY (`City_ID`) REFERENCES `cities` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=hebrew;

-- Data exporting was unselected.


-- Dumping structure for table nofson.bookings
DROP TABLE IF EXISTS `bookings`;
CREATE TABLE IF NOT EXISTS `bookings` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Order_ID` int(11) DEFAULT NULL,
  `Depart_Flight_ID` int(11) DEFAULT NULL,
  `Return_Flight_ID` int(11) DEFAULT NULL,
  `Hotel_ID` int(11) DEFAULT NULL,
  `Number_Of_Nights` int(11) DEFAULT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `Passport_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Bookings_Hotels` (`Hotel_ID`),
  KEY `FK_Bookings_Flights_Depart` (`Depart_Flight_ID`),
  KEY `FK_Bookings_Flight_Return` (`Return_Flight_ID`),
  KEY `FK_bookings_orders` (`Order_ID`),
  CONSTRAINT `FK_Bookings_Hotels` FOREIGN KEY (`Hotel_ID`) REFERENCES `hotels` (`ID`),
  CONSTRAINT `FK_Bookings_Flights_Depart` FOREIGN KEY (`Depart_Flight_ID`) REFERENCES `flights` (`ID`),
  CONSTRAINT `FK_Bookings_Flight_Return` FOREIGN KEY (`Return_Flight_ID`) REFERENCES `flights` (`ID`),
  CONSTRAINT `FK_bookings_orders` FOREIGN KEY (`Order_ID`) REFERENCES `orders` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=hebrew;

-- Data exporting was unselected.


-- Dumping structure for table nofson.cities
DROP TABLE IF EXISTS `cities`;
CREATE TABLE IF NOT EXISTS `cities` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Country_ID` int(11) DEFAULT NULL,
  `Name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_cities_countries` (`Country_ID`),
  CONSTRAINT `FK_cities_countries` FOREIGN KEY (`Country_ID`) REFERENCES `countries` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=hebrew;

-- Data exporting was unselected.


-- Dumping structure for table nofson.countries
DROP TABLE IF EXISTS `countries`;
CREATE TABLE IF NOT EXISTS `countries` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=hebrew;

-- Data exporting was unselected.


-- Dumping structure for table nofson.flights
DROP TABLE IF EXISTS `flights`;
CREATE TABLE IF NOT EXISTS `flights` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Departure_Time` datetime DEFAULT NULL,
  `Arrival_Time` datetime DEFAULT NULL,
  `From_Airport` int(11) DEFAULT NULL,
  `To_Airport` int(11) DEFAULT NULL,
  `Airline_ID` int(11) DEFAULT NULL,
  `Cost` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Flights_Airlines` (`Aireline_ID`),
  KEY `FK_flights_airports_From` (`From_Airport`),
  KEY `FK_flights_airports_to` (`To_Airport`),
  CONSTRAINT `FK_Flights_Airlines` FOREIGN KEY (`Aireline_ID`) REFERENCES `airlines` (`ID`),
  CONSTRAINT `FK_flights_airports_From` FOREIGN KEY (`From_Airport`) REFERENCES `airports` (`ID`),
  CONSTRAINT `FK_flights_airports_to` FOREIGN KEY (`To_Airport`) REFERENCES `airports` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=hebrew;

-- Data exporting was unselected.


-- Dumping structure for table nofson.hotels
DROP TABLE IF EXISTS `hotels`;
CREATE TABLE IF NOT EXISTS `hotels` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  `City_ID` int(11) DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Cost` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=hebrew;

-- Data exporting was unselected.


-- Dumping structure for table nofson.orders
DROP TABLE IF EXISTS `orders`;
CREATE TABLE IF NOT EXISTS `orders` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `User_ID` int(11) DEFAULT NULL,
  `Method_ID` int(11) DEFAULT NULL,
  `Order_Time` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_orders_users` (`User_ID`),
  KEY `FK_orders_payment_methods` (`Method_ID`),
  CONSTRAINT `FK_orders_users` FOREIGN KEY (`User_ID`) REFERENCES `users` (`ID`),
  CONSTRAINT `FK_orders_payment_methods` FOREIGN KEY (`Method_ID`) REFERENCES `payment_methods` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=hebrew;

-- Data exporting was unselected.


-- Dumping structure for table nofson.payment_methods
DROP TABLE IF EXISTS `payment_methods`;
CREATE TABLE IF NOT EXISTS `payment_methods` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=hebrew;

-- Data exporting was unselected.


-- Dumping structure for table nofson.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `ID` int(50) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  `Registration_Date` date DEFAULT NULL,
  `First_Name` varchar(50) DEFAULT NULL,
  `Last_Name` varchar(50) DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Phone` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=hebrew;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
