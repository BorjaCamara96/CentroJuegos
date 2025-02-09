-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 10-01-2025 a las 23:49:24
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `centro_game`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consolas`
--

CREATE TABLE `consolas` (
  `id_consola` int(5) NOT NULL,
  `nombre_consola` varchar(30) NOT NULL,
  `generacion` varchar(30) NOT NULL,
  `compania` varchar(15) NOT NULL,
  `pot_cpu` varchar(20) NOT NULL,
  `pot_gpu` varchar(20) NOT NULL,
  `precio_consola` float NOT NULL,
  `stock_consola` int(10) NOT NULL,
  `imagen_consola` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `consolas`
--

INSERT INTO `consolas` (`id_consola`, `nombre_consola`, `generacion`, `compania`, `pot_cpu`, `pot_gpu`, `precio_consola`, `stock_consola`, `imagen_consola`) VALUES
(1, 'Xbox One', 'Xbox One', 'Microsoft', '50', '50', 179.99, 12, 'xbox_one'),
(2, 'Xbox Series X', 'Xbox Series XS', 'Microsoft', '50', '50', 549.99, 3, 'xbox_series_x'),
(3, 'Xbox Series S', 'Xbox Series XS', 'Microsoft', '50', '50', 249.99, 2, 'xbox_series_s'),
(4, 'Nintendo Switch', 'Nintendo Switch', 'Nintendo', '50', '50', 299.99, 0, 'nintendo_switch'),
(5, 'Nintendo Switch Lite', 'Nintendo Switch', 'Nintendo', '50', '50', 199.99, 0, 'nintendo_switch_lite'),
(6, 'PlayStation 4', 'PlayStation 4', 'Sony', '50', '50', 159.99, 0, 'ps4'),
(7, 'PlayStation 5 Lector', 'PlayStation 5', 'Sony', '50', '50', 529.99, 9, 'ps5_lector'),
(12, 'PlayStation5 Digital', 'PlayStation 5', 'Sony', '50', '50', 449.99, 50, 'ps5_digital');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `generaciones`
--

CREATE TABLE `generaciones` (
  `generacion` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `generaciones`
--

INSERT INTO `generaciones` (`generacion`) VALUES
('Nintendo Switch'),
('PlayStation 4'),
('PlayStation 5'),
('Xbox One'),
('Xbox Series XS');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `stock`
--

CREATE TABLE `stock` (
  `id_juego` int(11) NOT NULL,
  `nombre_juego` varchar(50) NOT NULL,
  `generacion` varchar(30) NOT NULL,
  `precio_juego` float NOT NULL,
  `stock_juego` int(11) NOT NULL,
  `imagen_juego` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `stock`
--

INSERT INTO `stock` (`id_juego`, `nombre_juego`, `generacion`, `precio_juego`, `stock_juego`, `imagen_juego`) VALUES
(1, 'Call of Duty: Black Ops 6', 'PlayStation 5', 79.99, 0, 'cod_black_ops_6_ps5'),
(2, 'Call of Duty: Black Ops 6', 'Xbox Series XS', 79.99, 0, 'cod_black_ops_6_xbox_series'),
(3, 'Dragon Ball: Sparking! Zero', 'PlayStation 5', 69.99, 56, 'dragonball_sparking_zero_ps5'),
(4, 'Dragon Ball: Sparking! Zero', 'Xbox Series XS', 69.99, 46, 'dragonball_sparking_zero_xbox_series'),
(5, 'EA Sports FC 25', 'PlayStation 4', 69.99, 57, 'ea_fc_ps4'),
(6, 'EA Sports FC 25', 'PlayStation 5', 69.99, 93, 'ea_fc_ps5'),
(7, 'EA Sports FC 25', 'Xbox One', 69.99, 44, 'ea_fc_xbox_one'),
(8, 'EA Sports FC 25', 'Xbox Series XS', 69.99, 42, 'ea_fc_xbox_series'),
(9, 'EA Sports FC 25', 'Nintendo Switch', 69.99, 46, 'ea_fc_switch'),
(10, 'Elden Ring', 'PlayStation 4', 39.99, 62, 'elden_ring_ps4'),
(11, 'Elden Ring', 'PlayStation 5', 54.99, 63, 'elden_ring_ps5'),
(12, 'Elden Ring', 'Xbox One', 49.99, 36, 'elden_ring_xbox_one'),
(13, 'Elden Ring', 'Xbox Series XS', 39.99, 20, 'elden_ring_xbox_series'),
(14, 'Final Fantasy VII Rebirth', 'PlayStation 5', 34.99, 68, 'final_fantasy_vii_ps5'),
(15, 'Forza Horizon 5', 'Xbox One', 64.99, 0, 'forza_horizon_5_xbox_one'),
(16, 'Forza Horizon 5', 'Xbox Series XS', 64.99, 0, 'forza_horizon_5_xbox_series'),
(17, 'Gears of War 4', 'Xbox One', 39.99, 20, 'gears_of_war_4_xbox_one'),
(18, 'God of War: Ragnarök', 'PlayStation 4', 69.99, 29, 'god_of_war_ragnarok_ps4'),
(19, 'God of War: Ragnarök', 'PlayStation 5', 69.99, 65, 'god_of_war_ragnarok_ps5'),
(20, 'Super Mario Odyssey', 'Nintendo Switch', 49.99, 0, 'super_mario_odyssey_switch'),
(21, 'The Last of Us Part II', 'PlayStation 4', 39.99, 23, 'the_last_of_us_ii_ps4'),
(25, 'The Legend of Zelda: Echoes of Wisdom', 'Nintendo Switch', 59.99, 60, 'zelda_eow_switch'),
(26, 'Prince of Persia The Lost Crown', 'Nintendo Switch', 19.99, 54, 'prince_of_persia_tlc_switch'),
(27, 'Prince of Persia The Lost Crown', 'PlayStation 4', 19.99, 5, 'prince_of_persia_tlc_ps4'),
(28, 'Prince of Persia The Lost Crown', 'PlayStation 5', 19.99, 5, 'prince_of_persia_tlc_ps5'),
(38, 'Prince of Persia The Lost Crown', 'Xbox One', 19.99, 6, 'prince_of_persia_tlc_xbox_one'),
(39, 'Prince of Persia The Lost Crown', 'Xbox Series XS', 19.99, 4, 'prince_of_persia_tlc_xbox_series'),
(40, 'Mario+Rabbids Kingdom Battle', 'Nintendo Switch', 29.99, 26, 'mario+rabits_kingdom_battle_switch'),
(41, 'Mario Kart 8 Deluxe', 'Nintendo Switch', 49.99, 65, 'mario_kart_8_deluxe_switch'),
(42, 'Super Smash Bros Ultimate', 'Nintendo Switch', 49.99, 50, 'super_smash_bros_ultimate_switch'),
(43, 'Crash Bandicoot N. Sane Trilogy', 'Nintendo Switch', 29.99, 15, 'crash_bandicoot_nsane_trilogy_switch'),
(44, 'Crash Bandicoot N. Sane Trilogy', 'PlayStation 4', 29.99, 31, 'crash_bandicoot_nsane_trilogy_ps4'),
(45, 'Crash Bandicoot N. Sane Trilogy', 'Xbox One', 29.99, 22, 'crash_bandicoot_nsane_trilogy_xbox_one'),
(46, 'LEGO Marvel Super Heroes 2', 'Nintendo Switch', 29.99, 3, 'lego_marvel_super_heroes_2_switch'),
(47, 'LEGO Marvel Super Heroes 2', 'PlayStation 4', 29.99, 4, 'lego_marvel_super_heroes_2_ps4'),
(48, 'LEGO Marvel Super Heroes 2', 'Xbox One', 29.99, 6, 'lego_marvel_super_heroes_2_xbox_one'),
(49, 'Attack On Titan: Wings of Freedom', 'Xbox One', 19.99, 9, 'aot_wings_of_freedom_xbox_one'),
(50, 'Attack On Titan: Wings of Freedom', 'PlayStation 4', 19.99, 20, 'aot_wings_of_freedom_ps4'),
(51, 'EA Sports WRC', 'PlayStation 5', 39.99, 19, 'ea_sports_wrc_ps5'),
(52, 'EA Sports WRC', 'Xbox Series XS', 39.99, 36, 'ea_sports_wrc_xbox_series'),
(53, 'Pokémon Púrpura', 'Nintendo Switch', 29.99, 52, 'pokemon_purpura_switch'),
(54, 'Pokémon Escarlata', 'Nintendo Switch', 29.99, 36, 'pokemon_escarlata_switch'),
(55, 'Grand Theft Auto: The Trilogy', 'Nintendo Switch', 29.99, 23, 'gta_the_trilogy_switch'),
(56, 'Grand Theft Auto: The Trilogy', 'PlayStation 4', 29.99, 23, 'gta_the_trilogy_ps4'),
(57, 'Grand Theft Auto: The Trilogy', 'Xbox One', 29.99, 21, 'gta_the_trilogy_xbox_one'),
(63, 'Red Dead Redemption II', 'PlayStation 4', 19.99, 40, 'red_dead_redemption_ii_ps4'),
(64, 'Red Dead Redemption II', 'Xbox One', 19.99, 51, 'red_dead_redemption_ii_xbox_one'),
(65, 'Avatar: Frontiers of Pandora', 'PlayStation 5', 29.99, 12, 'avatar_frontiers_of_pandora_ps5'),
(66, 'Avatar: Frontiers of Pandora', 'Xbox Series XS', 29.99, 15, 'avatar_frontiers_of_pandora_xbox_series'),
(67, 'Starfield', 'Xbox Series XS', 49.99, 50, 'starfield_xbox_series'),
(68, 'Star Wars Outlaws', 'PlayStation 5', 39.99, 6, 'star_wars_outlaws_ps5'),
(69, 'Star Wars Outlaws', 'Xbox Series XS', 39.99, 4, 'star_wars_outlaws_xbox_series');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `user` varchar(20) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `admin` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `user`, `pass`, `admin`) VALUES
(1, 'admin', 'admin1234', 1),
(2, 'usuario', '1234', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `videojuegos`
--

CREATE TABLE `videojuegos` (
  `nombre_juego` varchar(50) NOT NULL,
  `compania` varchar(50) NOT NULL,
  `genero` enum('Sandbox','Estrategia en tiempo real','Shooters','Arena de batalla multijugador en línea','Juegos de rol','Simulación y deportes','Puzzles y juegos de fiesta','Acción-aventura','Supervivencia y terror','Plataformas') NOT NULL,
  `puntuacion` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `videojuegos`
--

INSERT INTO `videojuegos` (`nombre_juego`, `compania`, `genero`, `puntuacion`) VALUES
('Attack On Titan: Wings of Freedom', 'Omega Force', 'Acción-aventura', 74),
('Avatar: Frontiers of Pandora', 'Massive Entertainment', 'Acción-aventura', 72),
('Call of Duty: Black Ops 6', 'Raven Software', 'Shooters', 83),
('Crash Bandicoot N. Sane Trilogy', 'Vicarious Visions', 'Plataformas', 80),
('Dragon Ball: Sparking! Zero', 'Spike Chunsoft', 'Arena de batalla multijugador en línea', 81),
('EA Sports FC 25', 'EA Sports', 'Simulación y deportes', 76),
('EA Sports WRC', 'EA Sports', 'Simulación y deportes', 80),
('Elden Ring', 'FromSoftware', 'Juegos de rol', 94),
('Final Fantasy VII Rebirth', 'Square Enix', 'Juegos de rol', 92),
('Forza Horizon 5', 'Playground Games', 'Simulación y deportes', 92),
('Gears of War 4', 'The Coalition', 'Shooters', 84),
('God of War: Ragnarök', 'Sony Interactive Entertainment', 'Acción-aventura', 94),
('Grand Theft Auto: The Trilogy', 'Rockstar Games', 'Sandbox', 54),
('LEGO Marvel Super Heroes 2', 'TT Games', 'Acción-aventura', 73),
('Mario Kart 8 Deluxe', 'Nintendo', 'Simulación y deportes', 92),
('Mario+Rabbids Kingdom Battle', 'Ubisoft', 'Plataformas', 85),
('Marvel´s Spider-Man 2', 'Insomniac Games', 'Sandbox', 90),
('Pokémon Escarlata', 'Game Freak', 'Juegos de rol', 72),
('Pokémon Let´s Go Pikachu', 'Game Freak', 'Juegos de rol', 79),
('Pokémon Púrpura', 'Game Freak', 'Juegos de rol', 71),
('Prince of Persia The Lost Crown', 'Ubisoft', 'Acción-aventura', 86),
('Red Dead Redemption II', 'Rockstar Games', 'Sandbox', 97),
('Star Wars Outlaws', 'Ubisoft', 'Acción-aventura', 75),
('Starfield', 'Bethesda Game Studios', 'Juegos de rol', 83),
('Super Mario Odyssey', 'Nintendo EPD', 'Plataformas', 97),
('Super Smash Bros Ultimate', 'Bandai Namco Entertainment', 'Arena de batalla multijugador en línea', 93),
('The Last of Us Part II', 'Naughty Dog', 'Supervivencia y terror', 93),
('The Legend of Zelda: Echoes of Wisdom', 'Grezzo', 'Acción-aventura', 85);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `consolas`
--
ALTER TABLE `consolas`
  ADD PRIMARY KEY (`id_consola`),
  ADD KEY `generacion` (`generacion`);

--
-- Indices de la tabla `generaciones`
--
ALTER TABLE `generaciones`
  ADD PRIMARY KEY (`generacion`);

--
-- Indices de la tabla `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`id_juego`),
  ADD KEY `stock_ibfk_1` (`nombre_juego`),
  ADD KEY `stock_ibfk_2` (`generacion`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `user` (`user`);

--
-- Indices de la tabla `videojuegos`
--
ALTER TABLE `videojuegos`
  ADD PRIMARY KEY (`nombre_juego`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `consolas`
--
ALTER TABLE `consolas`
  MODIFY `id_consola` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `stock`
--
ALTER TABLE `stock`
  MODIFY `id_juego` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `consolas`
--
ALTER TABLE `consolas`
  ADD CONSTRAINT `consolas_ibfk_1` FOREIGN KEY (`generacion`) REFERENCES `generaciones` (`generacion`);

--
-- Filtros para la tabla `stock`
--
ALTER TABLE `stock`
  ADD CONSTRAINT `stock_ibfk_1` FOREIGN KEY (`nombre_juego`) REFERENCES `videojuegos` (`nombre_juego`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `stock_ibfk_2` FOREIGN KEY (`generacion`) REFERENCES `generaciones` (`generacion`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
