-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Окт 10 2024 г., 11:34
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
-- База данных: `Library`
--

-- --------------------------------------------------------

--
-- Структура таблицы `Authors`
--

CREATE TABLE `Authors` (
  `id_author` int NOT NULL,
  `author_surname` text NOT NULL,
  `auhor_name` text NOT NULL,
  `author_middlename` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `Authors`
--

INSERT INTO `Authors` (`id_author`, `author_surname`, `auhor_name`, `author_middlename`) VALUES
(1, 'Толстой', 'Лев', 'Николаевич'),
(2, 'Джоан', 'Кэтлин', 'Роулинг'),
(3, 'Лавкрафт', 'Говард', 'Филлипс'),
(4, 'Артур', 'Эрик', 'Блэр'),
(5, 'Тургенев', 'Иван', 'Сергеевич');

-- --------------------------------------------------------

--
-- Структура таблицы `Books`
--

CREATE TABLE `Books` (
  `id_book` int NOT NULL,
  `book_name` text NOT NULL,
  `id_author` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `Books`
--

INSERT INTO `Books` (`id_book`, `book_name`, `id_author`) VALUES
(1, '\"Война и мир\"', 1),
(2, '\"Гарри Поттер\"', 2),
(3, '\"Зов Ктулху\"', 3),
(4, '\"Анна Каренина\"', 1),
(5, '\"Муму\"', 5),
(6, '\"Отцы и дети\"', 5);

-- --------------------------------------------------------

--
-- Структура таблицы `Time`
--

CREATE TABLE `Time` (
  `id_time` int NOT NULL,
  `book_taken` date DEFAULT NULL,
  `book_returned` date DEFAULT NULL,
  `id_book` int NOT NULL,
  `id_visitor` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `Time`
--

INSERT INTO `Time` (`id_time`, `book_taken`, `book_returned`, `id_book`, `id_visitor`) VALUES
(1, '2024-08-01', '2024-08-17', 2, 1),
(2, '2024-09-01', '2024-09-30', 1, 3),
(3, '2024-10-01', '2024-10-04', 4, 2),
(4, '2024-10-01', '2024-10-10', 1, 4),
(5, '2024-04-01', '2024-04-17', 5, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `Visitors`
--

CREATE TABLE `Visitors` (
  `id_visitor` int NOT NULL,
  `visitor_surname` text NOT NULL,
  `visitor_name` text NOT NULL,
  `visitor_middlename` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Дамп данных таблицы `Visitors`
--

INSERT INTO `Visitors` (`id_visitor`, `visitor_surname`, `visitor_name`, `visitor_middlename`) VALUES
(1, 'Иванов', 'Иван', 'Иванович'),
(2, 'Иванова', 'Ольга', 'Николаевна'),
(3, 'Авдеев', 'Николай', 'Сергеевич'),
(4, 'Дервяшкин', 'Григорий', 'Васильевич');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `Authors`
--
ALTER TABLE `Authors`
  ADD PRIMARY KEY (`id_author`);

--
-- Индексы таблицы `Books`
--
ALTER TABLE `Books`
  ADD PRIMARY KEY (`id_book`);

--
-- Индексы таблицы `Time`
--
ALTER TABLE `Time`
  ADD PRIMARY KEY (`id_time`);

--
-- Индексы таблицы `Visitors`
--
ALTER TABLE `Visitors`
  ADD PRIMARY KEY (`id_visitor`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `Authors`
--
ALTER TABLE `Authors`
  MODIFY `id_author` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `Books`
--
ALTER TABLE `Books`
  MODIFY `id_book` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT для таблицы `Time`
--
ALTER TABLE `Time`
  MODIFY `id_time` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
