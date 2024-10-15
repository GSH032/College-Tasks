-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Окт 15 2024 г., 11:51
-- Версия сервера: 8.0.30
-- Версия PHP: 8.0.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `Security`
--

-- --------------------------------------------------------

--
-- Структура таблицы `Devices`
--

CREATE TABLE `Devices` (
  `device_id` int NOT NULL,
  `device_name` text NOT NULL,
  `device_mark` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Дамп данных таблицы `Devices`
--

INSERT INTO `Devices` (`device_id`, `device_name`, `device_mark`) VALUES
(1, 'Компьютер 1', 'Нет');

-- --------------------------------------------------------

--
-- Структура таблицы `Hack`
--

CREATE TABLE `Hack` (
  `hack_id` int NOT NULL,
  `hack_date` date NOT NULL,
  `hack_description` text NOT NULL,
  `device_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Дамп данных таблицы `Hack`
--

INSERT INTO `Hack` (`hack_id`, `hack_date`, `hack_description`, `device_id`) VALUES
(1, '2024-09-09', ' Test', 1),
(2, '2024-09-09', 'Ntcn', 1),
(3, '2024-10-10', 'Тест', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `Logs`
--

CREATE TABLE `Logs` (
  `log_id` int NOT NULL,
  `log_date` date NOT NULL,
  `log_desription` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Дамп данных таблицы `Logs`
--

INSERT INTO `Logs` (`log_id`, `log_date`, `log_desription`) VALUES
(2, '2024-10-15', 'Кибератака зафиксированаТест'),
(3, '2024-10-15', 'Рекомендация добавлена! Не давайте свои данные 3-им лицам'),
(4, '2024-10-15', 'Рекомендация добавлена! Выходите из учетных записей ');

-- --------------------------------------------------------

--
-- Структура таблицы `Reccomend`
--

CREATE TABLE `Reccomend` (
  `recommend_id` int NOT NULL,
  `reccomend_description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Дамп данных таблицы `Reccomend`
--

INSERT INTO `Reccomend` (`recommend_id`, `reccomend_description`) VALUES
(2, 'Не давайте свои данные 3-им лицам'),
(3, 'Выходите из учетных записей ');

-- --------------------------------------------------------

--
-- Структура таблицы `Users`
--

CREATE TABLE `Users` (
  `user_id` int NOT NULL,
  `user_name` text NOT NULL,
  `user_surname` text NOT NULL,
  `user_middlename` text NOT NULL,
  `username` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `Devices`
--
ALTER TABLE `Devices`
  ADD PRIMARY KEY (`device_id`);

--
-- Индексы таблицы `Hack`
--
ALTER TABLE `Hack`
  ADD PRIMARY KEY (`hack_id`);

--
-- Индексы таблицы `Logs`
--
ALTER TABLE `Logs`
  ADD PRIMARY KEY (`log_id`);

--
-- Индексы таблицы `Reccomend`
--
ALTER TABLE `Reccomend`
  ADD PRIMARY KEY (`recommend_id`);

--
-- Индексы таблицы `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `Devices`
--
ALTER TABLE `Devices`
  MODIFY `device_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `Hack`
--
ALTER TABLE `Hack`
  MODIFY `hack_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `Logs`
--
ALTER TABLE `Logs`
  MODIFY `log_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `Reccomend`
--
ALTER TABLE `Reccomend`
  MODIFY `recommend_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `Users`
--
ALTER TABLE `Users`
  MODIFY `user_id` int NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
