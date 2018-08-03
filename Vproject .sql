-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.67-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema project
--

CREATE DATABASE IF NOT EXISTS project;
USE project;

--
-- Definition of table `candidate`
--

DROP TABLE IF EXISTS `candidate`;
CREATE TABLE `candidate` (
  `CNAME` varchar(20) NOT NULL,
  `CUSN` varchar(15) NOT NULL,
  `CCOURSE` varchar(15) default NULL,
  `CSEM` varchar(15) default NULL,
  `CAGE` varchar(3) default NULL,
  `CEMAIL` varchar(30) default NULL,
  `ACHIEVEMENTS` varchar(100) default NULL,
  `CSYMBOL` varchar(15) default NULL,
  `CNOV` varchar(10) default NULL,
  PRIMARY KEY  (`CUSN`),
  UNIQUE KEY `CSYMBOL` (`CSYMBOL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `candidate`
--

/*!40000 ALTER TABLE `candidate` DISABLE KEYS */;
INSERT INTO `candidate` (`CNAME`,`CUSN`,`CCOURSE`,`CSEM`,`CAGE`,`CEMAIL`,`ACHIEVEMENTS`,`CSYMBOL`,`CNOV`) VALUES 
 ('Sahil','1RE13CS167','BE','5','20','sahil@yahoo.com','champ','cycle','19'),
 ('Anand','1RE13ME137','BE','7','21','anand@gmail.com','champion','lotus','0');
/*!40000 ALTER TABLE `candidate` ENABLE KEYS */;


--
-- Definition of table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `name` varchar(50) default NULL,
  `pass` varchar(20) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

/*!40000 ALTER TABLE `login` DISABLE KEYS */;
/*!40000 ALTER TABLE `login` ENABLE KEYS */;


--
-- Definition of table `register`
--

DROP TABLE IF EXISTS `register`;
CREATE TABLE `register` (
  `NAME` varchar(20) NOT NULL,
  `USN` varchar(15) NOT NULL,
  `COURSE` varchar(15) default NULL,
  `SEM` varchar(15) default NULL,
  `AGE` varchar(3) default NULL,
  `EMAIL` varchar(30) default NULL,
  `VSYMBOL` varchar(15) default NULL,
  `PASS` varchar(15) default NULL,
  `VOTE` varchar(5) default NULL,
  PRIMARY KEY  (`USN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `register`
--

/*!40000 ALTER TABLE `register` DISABLE KEYS */;
INSERT INTO `register` (`NAME`,`USN`,`COURSE`,`SEM`,`AGE`,`EMAIL`,`VSYMBOL`,`PASS`,`VOTE`) VALUES 
 ('sid','088','ise','7','22','asd','cycle','qwer','true'),
 ('dev','1','c','s','a','d','not voted','d','false'),
 ('aaaaaaa','12','BE','3','24','','not voted','ffff','false'),
 ('Sagar','1RE13CS100','BE','7','21','sagar@gmail.com','cycle','100','true'),
 ('Sagara','1RE13CS101','BE','7','21','sagar@gmail.com','not voted','101','false'),
 ('Samar','1RE13CS200','BE','7','21','samar@gmail.com','not voted','200','false'),
 ('rahul','1re13is068','ise','7','20','rahuldev.kgh@gmail.com','cycle','qweerty','true');
/*!40000 ALTER TABLE `register` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
