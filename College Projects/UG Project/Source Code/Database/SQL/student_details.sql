-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 09, 2018 at 08:56 AM
-- Server version: 10.1.29-MariaDB
-- PHP Version: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `student_details`
--

-- --------------------------------------------------------

--
-- Table structure for table `absent`
--

CREATE TABLE `absent` (
  `absent_title_1` varchar(50) NOT NULL,
  `leave_from` date NOT NULL,
  `leave_to` date NOT NULL,
  `reason` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `absent`
--

INSERT INTO `absent` (`absent_title_1`, `leave_from`, `leave_to`, `reason`) VALUES
('Semester Seven', '2018-03-08', '2018-03-15', 'Viral Fever');

-- --------------------------------------------------------

--
-- Table structure for table `details`
--

CREATE TABLE `details` (
  `detaila_title_1` varchar(50) NOT NULL,
  `date_1` date NOT NULL,
  `period_1` int(11) NOT NULL,
  `subject_1` varchar(50) NOT NULL,
  `period_2` int(11) NOT NULL,
  `subject_2` varchar(50) NOT NULL,
  `period_3` int(11) NOT NULL,
  `subject_3` varchar(50) NOT NULL,
  `period_4` int(11) NOT NULL,
  `subject_4` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `details`
--

INSERT INTO `details` (`detaila_title_1`, `date_1`, `period_1`, `subject_1`, `period_2`, `subject_2`, `period_3`, `subject_3`, `period_4`, `subject_4`) VALUES
('Semester Seven', '2018-03-02', 1, 'AI', 2, 'CNS', 3, 'IR', 8, 'SOA');

-- --------------------------------------------------------

--
-- Table structure for table `fees`
--

CREATE TABLE `fees` (
  `fees_title_1` varchar(50) NOT NULL,
  `year_1` varchar(50) NOT NULL,
  `semester` varchar(50) NOT NULL,
  `due_date_1` date NOT NULL,
  `fees_amount_1` bigint(20) NOT NULL,
  `concession_amount_1` bigint(20) NOT NULL,
  `net_amount_1` bigint(20) NOT NULL,
  `fees_title_2` varchar(50) NOT NULL,
  `year_2` varchar(50) NOT NULL,
  `semester_2` varchar(50) NOT NULL,
  `due_date_2` date NOT NULL,
  `fees_amount_2` bigint(20) NOT NULL,
  `concession_amount_2` bigint(20) NOT NULL,
  `net_amount_2` bigint(20) NOT NULL,
  `fees_title_3` varchar(50) NOT NULL,
  `year_3` varchar(50) NOT NULL,
  `semester_3` varchar(50) NOT NULL,
  `due_date_3` date NOT NULL,
  `fees_amount_3` bigint(20) NOT NULL,
  `concession_amount_3` bigint(20) NOT NULL,
  `net_amount_3` bigint(20) NOT NULL,
  `total` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fees`
--

INSERT INTO `fees` (`fees_title_1`, `year_1`, `semester`, `due_date_1`, `fees_amount_1`, `concession_amount_1`, `net_amount_1`, `fees_title_2`, `year_2`, `semester_2`, `due_date_2`, `fees_amount_2`, `concession_amount_2`, `net_amount_2`, `fees_title_3`, `year_3`, `semester_3`, `due_date_3`, `fees_amount_3`, `concession_amount_3`, `net_amount_3`, `total`) VALUES
('Tuition Fees', 'Fourth Year', 'Seven', '2017-03-14', 40000, 20000, 20000, 'Transport Fees', 'Fourth Year', 'Seven', '2017-03-15', 12000, 0, 12000, 'Other Fees', 'Fourth', 'Seven', '2018-03-30', 9500, 0, 9500, 41500);

-- --------------------------------------------------------

--
-- Table structure for table `general`
--

CREATE TABLE `general` (
  `id` int(11) NOT NULL,
  `NAME` varchar(30) NOT NULL,
  `roll_no` varchar(30) NOT NULL,
  `admission_date` date NOT NULL,
  `batch` varchar(30) NOT NULL,
  `course` varchar(30) NOT NULL,
  `semester` varchar(10) NOT NULL,
  `department` varchar(30) NOT NULL,
  `age` int(11) NOT NULL,
  `dob` date NOT NULL,
  `blood_group` varchar(10) NOT NULL,
  `height` int(11) NOT NULL,
  `weight` int(11) NOT NULL,
  `health_status` varchar(20) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `mother_tongue` varchar(20) NOT NULL,
  `nationality` varchar(20) NOT NULL,
  `religion` varchar(20) NOT NULL,
  `community` varchar(30) NOT NULL,
  `caste` varchar(30) NOT NULL,
  `category` varchar(30) NOT NULL,
  `medium_of_study` varchar(30) NOT NULL,
  `address` varchar(200) NOT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL,
  `pincode` int(11) NOT NULL,
  `mobile_no` bigint(20) NOT NULL,
  `father_name` varchar(50) NOT NULL,
  `f_occupation` varchar(50) NOT NULL,
  `f_monthly_income` bigint(20) NOT NULL,
  `f_contact_no` bigint(20) NOT NULL,
  `mother_name` varchar(50) NOT NULL,
  `m_occupation` varchar(50) NOT NULL,
  `m_monthly_income` bigint(20) NOT NULL,
  `m_contact_no` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `general`
--

INSERT INTO `general` (`id`, `NAME`, `roll_no`, `admission_date`, `batch`, `course`, `semester`, `department`, `age`, `dob`, `blood_group`, `height`, `weight`, `health_status`, `gender`, `mother_tongue`, `nationality`, `religion`, `community`, `caste`, `category`, `medium_of_study`, `address`, `city`, `state`, `country`, `pincode`, `mobile_no`, `father_name`, `f_occupation`, `f_monthly_income`, `f_contact_no`, `mother_name`, `m_occupation`, `m_monthly_income`, `m_contact_no`) VALUES
(1, 'Bajaji R', '14rbcs006', '2014-08-15', '2014-2018', 'BE CSE', 'Seven', 'Computer Science Engineering', 18, '1996-10-02', 'B+', 165, 50, 'G', 'Male', 'Tamil', 'Indian', 'Hindu', 'BC', 'Nadar', 'MGT', 'Tamil', '15/4 Thoddrayan Koil Street, Kattor', 'Coimbatore', 'Tamil Nadu', 'India', 641009, 9542351565, 'Mohan', 'Coolie', 100000, 9542351569, 'Rani', 'House Wife', 0, 9542351561);

-- --------------------------------------------------------

--
-- Table structure for table `internal`
--

CREATE TABLE `internal` (
  `internal_title` varchar(50) NOT NULL,
  `sub_code_1` varchar(50) NOT NULL,
  `sub_dis_1` varchar(50) NOT NULL,
  `sub_mark_1` int(11) NOT NULL,
  `sub_result_1` varchar(50) NOT NULL,
  `sub_code_2` varchar(50) NOT NULL,
  `sub_dis_2` varchar(50) NOT NULL,
  `sub_mark_2` int(11) NOT NULL,
  `sub_result_2` varchar(50) NOT NULL,
  `sub_code_3` varchar(50) NOT NULL,
  `sub_dis_3` varchar(50) NOT NULL,
  `sub_mark_3` int(11) NOT NULL,
  `sub_result_3` varchar(50) NOT NULL,
  `sub_code_4` varchar(50) NOT NULL,
  `sub_dis_4` varchar(50) NOT NULL,
  `sub_mark_4` int(11) NOT NULL,
  `sub_result_4` varchar(50) NOT NULL,
  `sub_code_5` varchar(50) NOT NULL,
  `sub_dis_5` varchar(50) NOT NULL,
  `sub_mark_5` int(11) NOT NULL,
  `sub_result_5` varchar(50) NOT NULL,
  `sub_code_6` varchar(50) NOT NULL,
  `sub_dis_6` varchar(50) NOT NULL,
  `sub_mark_6` int(11) NOT NULL,
  `sub_result_6` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `internal`
--

INSERT INTO `internal` (`internal_title`, `sub_code_1`, `sub_dis_1`, `sub_mark_1`, `sub_result_1`, `sub_code_2`, `sub_dis_2`, `sub_mark_2`, `sub_result_2`, `sub_code_3`, `sub_dis_3`, `sub_mark_3`, `sub_result_3`, `sub_code_4`, `sub_dis_4`, `sub_mark_4`, `sub_result_4`, `sub_code_5`, `sub_dis_5`, `sub_mark_5`, `sub_result_5`, `sub_code_6`, `sub_dis_6`, `sub_mark_6`, `sub_result_6`) VALUES
('Internal Assesment 1', 'CS6701', 'CNS', 70, 'pass', 'CS6702', 'GTA', 80, 'pass', 'CS6703', 'GCC', 70, 'pass', 'CS6704', 'RMT', 60, 'pass', 'CS6706', 'SOA', 80, 'pass', 'IT6001', 'Game Theory', 70, 'pass');

-- --------------------------------------------------------

--
-- Table structure for table `library`
--

CREATE TABLE `library` (
  `book_title` varchar(50) NOT NULL,
  `book_ac_no` bigint(20) NOT NULL,
  `book_resource_type` varchar(50) NOT NULL,
  `book_issue_date` date NOT NULL,
  `book_due_date` date NOT NULL,
  `book_return_date` date NOT NULL,
  `book_status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `library`
--

INSERT INTO `library` (`book_title`, `book_ac_no`, `book_resource_type`, `book_issue_date`, `book_due_date`, `book_return_date`, `book_status`) VALUES
('Computer Graphics', 131351, 'Book', '2018-03-01', '2018-03-15', '2018-03-29', 'Issued'),
('Physics', 131351, 'Book', '2018-03-01', '2018-03-15', '2018-03-29', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `roll_no` varchar(50) NOT NULL,
  `password` text NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`roll_no`, `password`, `name`) VALUES
('14rbcs006', 'balajipass', 'Balaji R');

-- --------------------------------------------------------

--
-- Table structure for table `onduty`
--

CREATE TABLE `onduty` (
  `onduty_title_1` varchar(50) NOT NULL,
  `from_date` date NOT NULL,
  `to_date` date NOT NULL,
  `total_days` int(11) NOT NULL,
  `period` varchar(50) NOT NULL,
  `subject` varchar(250) NOT NULL,
  `reason` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `onduty`
--

INSERT INTO `onduty` (`onduty_title_1`, `from_date`, `to_date`, `total_days`, `period`, `subject`, `reason`) VALUES
('semester Seven', '2018-03-08', '2018-03-09', 1, '1,2,3,4', 'SOA,CNS,GTA,GRAPH THEORY', 'Kabadi Tornament');

-- --------------------------------------------------------

--
-- Table structure for table `pending`
--

CREATE TABLE `pending` (
  `fees_title_1` varchar(50) NOT NULL,
  `year_1` varchar(50) NOT NULL,
  `semester_1` varchar(50) NOT NULL,
  `due_date_1` date NOT NULL,
  `pending_amount_1` bigint(20) NOT NULL,
  `fees_title_2` varchar(50) NOT NULL,
  `year_2` varchar(50) NOT NULL,
  `semester_2` varchar(50) NOT NULL,
  `due_date_2` date NOT NULL,
  `pending_amount_2` bigint(20) NOT NULL,
  `total` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pending`
--

INSERT INTO `pending` (`fees_title_1`, `year_1`, `semester_1`, `due_date_1`, `pending_amount_1`, `fees_title_2`, `year_2`, `semester_2`, `due_date_2`, `pending_amount_2`, `total`) VALUES
('Tuition Fees', 'Fourth Year', 'Seven', '2018-03-06', 12000, 'Other Fees', 'Fourth Year', 'Seven', '2018-03-15', 9500, 21500);

-- --------------------------------------------------------

--
-- Table structure for table `receipt`
--

CREATE TABLE `receipt` (
  `fees_title_1` varchar(50) NOT NULL,
  `receipt_no` varchar(50) NOT NULL,
  `date_1` date NOT NULL,
  `year_1` varchar(50) NOT NULL,
  `amount_1` bigint(20) NOT NULL,
  `net_amount_1` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `receipt`
--

INSERT INTO `receipt` (`fees_title_1`, `receipt_no`, `date_1`, `year_1`, `amount_1`, `net_amount_1`) VALUES
('Tuition Fees Receipt', 'BD/16-15643', '2018-03-15', 'Fourth Year', 20000, 20000);

-- --------------------------------------------------------

--
-- Table structure for table `summary`
--

CREATE TABLE `summary` (
  `summary_title_1` varchar(50) NOT NULL,
  `month_1` varchar(50) NOT NULL,
  `days_1` int(11) NOT NULL,
  `absent_1` int(11) NOT NULL,
  `present_1` int(11) NOT NULL,
  `month_2` varchar(50) NOT NULL,
  `days_2` int(11) NOT NULL,
  `absent_2` int(11) NOT NULL,
  `present_2` int(11) NOT NULL,
  `month_3` varchar(50) NOT NULL,
  `days_3` int(11) NOT NULL,
  `absent_3` int(11) NOT NULL,
  `present_3` int(11) NOT NULL,
  `total` varchar(50) NOT NULL,
  `days_4` int(11) NOT NULL,
  `absent_4` int(11) NOT NULL,
  `present_4` int(11) NOT NULL,
  `present` varchar(50) NOT NULL,
  `days_5` int(11) NOT NULL,
  `absent_5` int(11) NOT NULL,
  `present_5` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `summary`
--

INSERT INTO `summary` (`summary_title_1`, `month_1`, `days_1`, `absent_1`, `present_1`, `month_2`, `days_2`, `absent_2`, `present_2`, `month_3`, `days_3`, `absent_3`, `present_3`, `total`, `days_4`, `absent_4`, `present_4`, `present`, `days_5`, `absent_5`, `present_5`) VALUES
('Semester Seven', 'January', 22, 2, 20, 'February', 18, 3, 15, 'March', 20, 2, 18, 'Total', 60, 7, 53, 'Present', 100, 10, 90);

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE `test` (
  `test_title` varchar(50) NOT NULL,
  `sub_code_1` varchar(50) NOT NULL,
  `sub_dis_1` varchar(50) NOT NULL,
  `sub_mark_1` int(11) NOT NULL,
  `sub_result_1` varchar(50) NOT NULL,
  `sub_code_2` varchar(50) NOT NULL,
  `sub_dis_2` varchar(50) NOT NULL,
  `sub_mark_2` int(11) NOT NULL,
  `sub_result_2` varchar(50) NOT NULL,
  `sub_code_3` varchar(50) NOT NULL,
  `sub_dis_3` varchar(50) NOT NULL,
  `sub_mark_3` int(11) NOT NULL,
  `sub_result_3` varchar(50) NOT NULL,
  `sub_code_4` varchar(50) NOT NULL,
  `sub_dis_4` varchar(50) NOT NULL,
  `sub_mark_4` int(11) NOT NULL,
  `sub_result_4` varchar(50) NOT NULL,
  `sub_code_5` varchar(50) NOT NULL,
  `sub_dis_5` varchar(50) NOT NULL,
  `sub_mark_5` int(11) NOT NULL,
  `sub_result_5` varchar(50) NOT NULL,
  `sub_code_6` varchar(50) NOT NULL,
  `sub_dis_6` varchar(50) NOT NULL,
  `sub_mark_6` int(11) NOT NULL,
  `sub_result_6` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`test_title`, `sub_code_1`, `sub_dis_1`, `sub_mark_1`, `sub_result_1`, `sub_code_2`, `sub_dis_2`, `sub_mark_2`, `sub_result_2`, `sub_code_3`, `sub_dis_3`, `sub_mark_3`, `sub_result_3`, `sub_code_4`, `sub_dis_4`, `sub_mark_4`, `sub_result_4`, `sub_code_5`, `sub_dis_5`, `sub_mark_5`, `sub_result_5`, `sub_code_6`, `sub_dis_6`, `sub_mark_6`, `sub_result_6`) VALUES
('Slip Test 1', 'CS6701', 'CNS', 50, 'pass', 'CS6702', 'GTA', 40, 'fail', 'CS6703', 'GCC', 80, 'pass', 'CS6704', 'RMT', 70, 'pass', 'CS6706', 'SOA', 30, 'fail', 'IT6001', 'Game Theory', 80, 'pass');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`roll_no`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
