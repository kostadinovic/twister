-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Jeu 07 Mars 2019 à 17:14
-- Version du serveur :  5.7.22
-- Version de PHP :  7.0.33-0+deb9u2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `kostadinovic_benchara`
--

-- --------------------------------------------------------

--
-- Structure de la table `Connection`
--

CREATE TABLE `Connection` (
  `key_co` varchar(50) NOT NULL,
  `login` varchar(52) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `mode` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `Connection`
--

INSERT INTO `Connection` (`key_co`, `login`, `time`, `mode`) VALUES
('p5RAQsLo5sP4VtpCSfYyh521rltvNwZf', 'neecak', '2019-03-07 16:06:09', 0);

-- --------------------------------------------------------

--
-- Structure de la table `Friends`
--

CREATE TABLE `Friends` (
  `monLogin` varchar(32) NOT NULL,
  `friendLogin` varchar(32) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `users`
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
-- Contenu de la table `users`
--

INSERT INTO `users` (`id`, `nom`, `prenom`, `mail`, `login`, `password`, `age`) VALUES
(5, 'nemanja', 'kosta', 'nem@live.fr', 'neecak', 0x313233343536373839, 22),
(6, 'imane', 'benchara', 'imane@live.fr', 'imane', 0x313233343536373839, 24);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `Connection`
--
ALTER TABLE `Connection`
  ADD UNIQUE KEY `login` (`login`);

--
-- Index pour la table `Friends`
--
ALTER TABLE `Friends`
  ADD UNIQUE KEY `monLogin` (`monLogin`,`friendLogin`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `mail` (`mail`),
  ADD UNIQUE KEY `mail_2` (`mail`,`login`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
