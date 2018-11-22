-- phpMyAdmin SQL Dump
-- version 4.8.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-11-2018 a las 19:41:18
-- Versión del servidor: 10.1.31-MariaDB
-- Versión de PHP: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `acceso_a_datos_3`
--
CREATE DATABASE IF NOT EXISTS `acceso_a_datos_3` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `acceso_a_datos_3`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actores`
--

DROP TABLE IF EXISTS `actores`;
CREATE TABLE `actores` (
  `Id` int(200) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Descripcion` varchar(2000) NOT NULL,
  `Pelo` varchar(2000) NOT NULL,
  `Ojos` varchar(2000) NOT NULL,
  `Representante` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `actores`
--

INSERT INTO `actores` (`Id`, `Nombre`, `Descripcion`, `Pelo`, `Ojos`, `Representante`) VALUES
(1, 'Johnny', 'Actor', 'Negro', 'CafÃ©s', '1'),
(2, 'Kaya', 'Actriz', 'CafÃ© oscuro', 'Azul', '2'),
(3, 'Javier', 'Actor', 'CafÃ© oscuro', 'CafÃ©s claro', '3');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `representantes`
--

DROP TABLE IF EXISTS `representantes`;
CREATE TABLE `representantes` (
  `Id` varchar(100) NOT NULL,
  `Nombre` varchar(2000) NOT NULL,
  `Edad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `representantes`
--

INSERT INTO `representantes` (`Id`, `Nombre`, `Edad`) VALUES
('1', 'David', 21),
('2', 'Alberto', 20),
('3', 'Javier', 18);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actores`
--
ALTER TABLE `actores`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Representante` (`Representante`);

--
-- Indices de la tabla `representantes`
--
ALTER TABLE `representantes`
  ADD PRIMARY KEY (`Id`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `actores`
--
ALTER TABLE `actores`
  ADD CONSTRAINT `actores_ibfk_1` FOREIGN KEY (`Representante`) REFERENCES `representantes` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
