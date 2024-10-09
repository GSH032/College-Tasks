-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Окт 09 2024 г., 11:30
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
-- База данных: `Shop`
--

-- --------------------------------------------------------

--
-- Структура таблицы `Author`
--

CREATE TABLE `Author` (
  `id_author` int NOT NULL,
  `author_surname` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `author_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `author_middlename` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `Author`
--

INSERT INTO `Author` (`id_author`, `author_surname`, `author_name`, `author_middlename`) VALUES
(1, 'Лавкрафт', 'Говард', 'Филлипс'),
(2, 'Толстой', 'Лев', 'Николаевич'),
(3, 'Уильям', 'Томас', 'Харрис'),
(4, 'Лютвидж', 'Чарльз', 'Доджсон');

-- --------------------------------------------------------

--
-- Структура таблицы `Books`
--

CREATE TABLE `Books` (
  `id_book` int NOT NULL,
  `title` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `id_author` int NOT NULL,
  `id_genre` int NOT NULL,
  `year` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `Books`
--

INSERT INTO `Books` (`id_book`, `title`, `id_author`, `id_genre`, `year`) VALUES
(1, '\"Алхимик\"', 1, 1, 1903),
(2, '\"Война и мир\"', 2, 2, 1988),
(3, '\"Молчание ягнят\"', 3, 2, 1989),
(4, '\"Хроники Нарнии\"', 4, 3, 2009),
(5, '\"Некрономикон\"', 1, 1, 2008);

-- --------------------------------------------------------

--
-- Структура таблицы `Customers`
--

CREATE TABLE `Customers` (
  `id_customer` int NOT NULL,
  `customer_surname` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `customer_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `customer_middlename` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `purcase_count` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `Customers`
--

INSERT INTO `Customers` (`id_customer`, `customer_surname`, `customer_name`, `customer_middlename`, `purcase_count`) VALUES
(1, 'Иванов', 'Иван', 'Иванович', 1),
(2, 'Иванова', 'Ольга', 'Николаевна', 8);

-- --------------------------------------------------------

--
-- Структура таблицы `Genre`
--

CREATE TABLE `Genre` (
  `id_genre` int NOT NULL,
  `genre_name` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `Genre`
--

INSERT INTO `Genre` (`id_genre`, `genre_name`) VALUES
(1, 'Хоррор'),
(2, 'Роман'),
(3, 'Фэнтези');

-- --------------------------------------------------------

--
-- Структура таблицы `Orders`
--

CREATE TABLE `Orders` (
  `id_order` int NOT NULL,
  `id_book` int NOT NULL,
  `id_customer` int NOT NULL,
  `sum` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `Orders`
--

INSERT INTO `Orders` (`id_order`, `id_book`, `id_customer`, `sum`) VALUES
(1, 1, 2, 4000),
(2, 2, 1, 1000),
(3, 4, 1, 2000),
(4, 3, 2, 3000);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `Author`
--
ALTER TABLE `Author`
  ADD PRIMARY KEY (`id_author`);

--
-- Индексы таблицы `Customers`
--
ALTER TABLE `Customers`
  ADD PRIMARY KEY (`id_customer`);

--
-- Индексы таблицы `Orders`
--
ALTER TABLE `Orders`
  ADD PRIMARY KEY (`id_order`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `Author`
--
ALTER TABLE `Author`
  MODIFY `id_author` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
