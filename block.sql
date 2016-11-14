-- phpMyAdmin SQL Dump
-- version 4.4.3
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 14, 2016 at 08:34 
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `bluej_exercises`
--

-- --------------------------------------------------------

--
SET FOREIGN_KEY_CHECKS=0;
TRUNCATE block;


CREATE TABLE IF NOT EXISTS `block` (
  `idblock` int(11) NOT NULL,
  `blockname` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `block`
--

INSERT INTO `block` (`idblock`, `blockname`) VALUES
(1, '1A'),
(2, '1B'),
(3, '2A'),
(4, '2B'),
(5, '2C'),
(6, '2D'),
(7, '2E'),
(8, '3A'),
(9, '3B'),
(10, '3C'),
(11, '4A'),
(12, '4B'),
(13, '4C'),
(14, '4D'),
(15, '4E'),
(16, '5A'),
(17, '5B'),
(18, '5C'),
(19, '5D'),
(20, '6A'),
(21, '6B'),
(22, '6C'),
(23, '7A'),
(24, '7B'),
(25, '8'),
(26, '9'),
(27, '10A'),
(28, '10B'),
(29, '10C'),
(30, '10D'),
(31, '11A'),
(32, '11B'),
(33, '11C'),
(34, '11D'),
(35, '12A'),
(36, '12B'),
(37, '12C'),
(38, '13'),
(39, '14A'),
(40, '14B');

SET FOREIGN_KEY_CHECKS=1;

