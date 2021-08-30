-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Apr 22, 2020 at 09:06 AM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id13359216_covid19tn`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_covid_participant`
--

CREATE TABLE `tbl_covid_participant` (
  `id` int(100) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `age` int(10) DEFAULT NULL,
  `gender` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `cc` int(10) NOT NULL DEFAULT 91
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tbl_covid_participant`
--

INSERT INTO `tbl_covid_participant` (`id`, `name`, `age`, `gender`, `cc`) VALUES
(1, 'chn', 18, 'male', 91),
(2, 'csttn', 19, 'male', 91),
(3, 'cst', 20, 'female', 91),
(4, 'test', 20, 'male', 91),
(5, 'try', 17, 'female', 91),
(6, 'mdu', 18, 'male', 91),
(7, 'chn', 19, 'male', 91);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_covid_participant`
--
ALTER TABLE `tbl_covid_participant`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_covid_participant`
--
ALTER TABLE `tbl_covid_participant`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
