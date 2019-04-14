
-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Apr 14, 2019 at 06:09 PM
-- Server version: 5.7.25
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `kostadinovic_benchara`
--

-- --------------------------------------------------------

--
-- Table structure for table `Connection`
--

CREATE TABLE `Connection` (
  `key_co` varchar(50) NOT NULL,
  `login` varchar(52) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `mode` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Connection`
--

INSERT INTO `Connection` (`key_co`, `login`, `time`, `mode`) VALUES
('olHXEAK8BtN5X6k9NlN12mS4TIpyOkSP', 'imaneB', '2019-04-14 17:38:22', 0),
('qjYAy1G7zdcdtQ4bEDehM8Bl4TRtSgB0', 'neecak', '2019-04-14 17:59:30', 0);

-- --------------------------------------------------------

--
-- Table structure for table `Friends`
--

CREATE TABLE `Friends` (
  `monLogin` varchar(32) NOT NULL,
  `friendLogin` varchar(32) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Friends`
--

INSERT INTO `Friends` (`monLogin`, `friendLogin`, `time`) VALUES
('neecak', 'imaneB', '2019-04-14 15:55:18');

-- --------------------------------------------------------

--
-- Table structure for table `LikeMessage`
--

CREATE TABLE `LikeMessage` (
  `id_message` varchar(32) NOT NULL,
  `login_user` varchar(32) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `LikeMessage`
--

INSERT INTO `LikeMessage` (`id_message`, `login_user`, `time`) VALUES
('123', 'imaneB', '2019-04-14 12:05:13'),
('2345555', 'neecak', '2019-04-14 15:59:03');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(20) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `mail` varchar(50) DEFAULT NULL,
  `login` varchar(32) NOT NULL,
  `password` blob,
  `age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `nom`, `prenom`, `mail`, `login`, `password`, `age`) VALUES
(16, 'benchara', 'imane', 'ben.imane@hotmail.fr', 'imaneB', 0x313233343536373839, 23),
(17, 'kostadinovic', 'nemanja', 'nemania-srb@live.fr', 'neecak', 0x313233343536373839, 22);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Connection`
--
ALTER TABLE `Connection`
  ADD UNIQUE KEY `login` (`login`);

--
-- Indexes for table `Friends`
--
ALTER TABLE `Friends`
  ADD UNIQUE KEY `monLogin` (`monLogin`,`friendLogin`);

--
-- Indexes for table `LikeMessage`
--
ALTER TABLE `LikeMessage`
  ADD PRIMARY KEY (`id_message`,`login_user`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `mail` (`mail`),
  ADD UNIQUE KEY `mail_2` (`mail`,`login`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
