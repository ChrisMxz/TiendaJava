-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-08-2022 a las 01:39:23
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tienda`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(5) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `appaterno` varchar(15) NOT NULL,
  `apmaterno` varchar(15) NOT NULL,
  `edad` int(3) NOT NULL,
  `sexo` varchar(10) NOT NULL,
  `direccion` varchar(500) NOT NULL,
  `telefono` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `nombre`, `appaterno`, `apmaterno`, `edad`, `sexo`, `direccion`, `telefono`) VALUES
(1, 'David Cristofert', 'Dorantes', 'Martinez', 23, 'M', 'Calle 4', '1234567890'),
(2, 'Laura', 'Apolinar', 'Lopez', 21, 'F', 'calle 7', '5612913482'),
(3, 'Jesus', 'Dorantes ', 'Martinez', 22, 'M', 'calle 8', '5612913476'),
(4, 'Paula', 'Martinez', 'Hernandez', 47, 'F', 'Calle \n34', '3434556567'),
(52694, 'Miguel', 'Dorantes', 'Cruz', 48, 'M', 'calle Manzana', '4353456346');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `concepto`
--

CREATE TABLE `concepto` (
  `id_concepto` int(10) NOT NULL,
  `concepto` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `concepto`
--

INSERT INTO `concepto` (`id_concepto`, `concepto`) VALUES
(1, 'Papel Baño'),
(5, 'Auto Clasico'),
(9, 'Memoria'),
(12, 'Celular'),
(123, 'Teclado'),
(987, 'Jabon Baño'),
(79359, 'Escoba Piso');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `item`
--

CREATE TABLE `item` (
  `id_item` int(5) NOT NULL,
  `id_pedido` int(5) NOT NULL,
  `id_producto` int(5) NOT NULL,
  `cantidad` int(5) NOT NULL,
  `monto` double(10,4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `item`
--

INSERT INTO `item` (`id_item`, `id_pedido`, `id_producto`, `cantidad`, `monto`) VALUES
(30, 42, 66735, 4, 18000.0000),
(31, 41, 41, 2, 50.0000),
(43, 45, 66735, 1, 4500.0000),
(44, 45, 122, 2, 100000.0000),
(48, 50, 122, 1, 50000.0000),
(49, 50, 66735, 1, 4500.0000),
(50, 50, 66738, 5, 1250.0000),
(51, 50, 41, 2, 50.0000),
(52, 50, 16133, 1, 3500.0000),
(53, 51, 66735, 1, 4500.0000),
(54, 51, 41, 2, 50.0000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `id_pedido` int(5) NOT NULL,
  `id_cliente` int(5) NOT NULL,
  `cantidadproductos` int(5) DEFAULT NULL,
  `total` double(10,4) DEFAULT NULL,
  `fecha` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `estado` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`id_pedido`, `id_cliente`, `cantidadproductos`, `total`, `fecha`, `estado`) VALUES
(41, 4, 2, 50.0000, '2022-08-08 16:21:10', 'Pagado'),
(42, 4, 4, 18000.0000, '2022-08-08 16:11:18', 'Pagado'),
(45, 1, 3, 104500.0000, '2022-08-09 12:27:13', 'Pagado'),
(50, 2, 10, 59300.0000, '2022-08-10 18:38:28', 'Pendiente'),
(51, 52694, 3, 4550.0000, '2022-08-10 18:38:53', 'Pendiente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_producto` int(5) NOT NULL,
  `id_concepto` int(10) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `stock` int(10) NOT NULL,
  `precio` double(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `id_concepto`, `nombre`, `descripcion`, `stock`, `precio`) VALUES
(41, 987, 'Escudo', 'Jabon antibacterial', 8, 25.00),
(122, 5, 'Bocho', 'modelo 2000', 10, 50000.00),
(16133, 12, 'Samsung', 'Galaxy s10', 24, 3500.00),
(66735, 12, 'Samsung', 'Galaxy A52', 4, 4500.00),
(66738, 9, 'Kingston', 'Memoria de 12GB\nUSB 3.0\nAlto rendimineto\n', 30, 250.00);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`);

--
-- Indices de la tabla `concepto`
--
ALTER TABLE `concepto`
  ADD PRIMARY KEY (`id_concepto`),
  ADD KEY `concepto` (`id_concepto`) USING BTREE;

--
-- Indices de la tabla `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id_item`),
  ADD UNIQUE KEY `item` (`id_item`),
  ADD KEY `pedido` (`id_pedido`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id_pedido`),
  ADD KEY `cliente` (`id_cliente`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `id_concepto` (`id_concepto`),
  ADD KEY `concepto` (`id_concepto`) USING BTREE;

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52700;

--
-- AUTO_INCREMENT de la tabla `item`
--
ALTER TABLE `item`
  MODIFY `id_item` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=55;

--
-- AUTO_INCREMENT de la tabla `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id_pedido` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66739;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`) ON UPDATE CASCADE,
  ADD CONSTRAINT `item_ibfk_3` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`id_concepto`) REFERENCES `concepto` (`id_concepto`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
