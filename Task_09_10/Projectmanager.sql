-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Окт 09 2024 г., 13:22
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
-- База данных: `Projectmanager`
--

-- --------------------------------------------------------

--
-- Структура таблицы `Departament`
--

CREATE TABLE `Departament` (
  `id_departament` int NOT NULL,
  `departament_name` text COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `Departament`
--

INSERT INTO `Departament` (`id_departament`, `departament_name`) VALUES
(1, '\"Отдел 1\"'),
(2, '\"Отдел 2\"');

-- --------------------------------------------------------

--
-- Структура таблицы `Projects`
--

CREATE TABLE `Projects` (
  `id_project` int NOT NULL,
  `project_name` text COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `Projects`
--

INSERT INTO `Projects` (`id_project`, `project_name`) VALUES
(1, '\"Проект 1\"'),
(2, '\"Проект 2\"');

-- --------------------------------------------------------

--
-- Структура таблицы `Tasks`
--

CREATE TABLE `Tasks` (
  `id_task` int NOT NULL,
  `id_project` int NOT NULL,
  `task_name` text COLLATE utf8mb4_general_ci NOT NULL,
  `status` int NOT NULL,
  `id_worker` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `Tasks`
--

INSERT INTO `Tasks` (`id_task`, `id_project`, `task_name`, `status`, `id_worker`) VALUES
(1, 1, 'Задача 1', 1, 1),
(2, 2, 'Задача 2', 0, 2),
(3, 1, 'Задча 3', 0, 1),
(4, 1, 'Задача 4', 0, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `Time`
--

CREATE TABLE `Time` (
  `id_time` int NOT NULL,
  `id_project` int NOT NULL,
  `project_start` date NOT NULL,
  `project_end` date NOT NULL,
  `id_task` int NOT NULL,
  `task_start` date NOT NULL,
  `task_end` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `Time`
--

INSERT INTO `Time` (`id_time`, `id_project`, `project_start`, `project_end`, `id_task`, `task_start`, `task_end`) VALUES
(1, 1, '2024-02-01', '2024-03-01', 1, '2024-02-01', '2024-02-07'),
(2, 2, '2024-05-01', '2024-06-30', 2, '2024-05-01', '2024-05-07'),
(3, 1, '2024-02-01', '2024-03-01', 3, '2024-02-08', '2024-02-10'),
(4, 2, '2024-05-01', '2024-06-30', 4, '2024-05-08', '2024-05-12');

-- --------------------------------------------------------

--
-- Структура таблицы `Workers`
--

CREATE TABLE `Workers` (
  `id_worker` int NOT NULL,
  `worker_surname` text COLLATE utf8mb4_general_ci NOT NULL,
  `worker_name` text COLLATE utf8mb4_general_ci NOT NULL,
  `worker_middlename` text COLLATE utf8mb4_general_ci NOT NULL,
  `id_departament` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Дамп данных таблицы `Workers`
--

INSERT INTO `Workers` (`id_worker`, `worker_surname`, `worker_name`, `worker_middlename`, `id_departament`) VALUES
(1, 'Иванов', 'Иван', 'Иванович', 1),
(2, 'Иванова', 'Ольга', 'Николаевна', 2);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `Departament`
--
ALTER TABLE `Departament`
  ADD PRIMARY KEY (`id_departament`);

--
-- Индексы таблицы `Projects`
--
ALTER TABLE `Projects`
  ADD PRIMARY KEY (`id_project`);

--
-- Индексы таблицы `Tasks`
--
ALTER TABLE `Tasks`
  ADD PRIMARY KEY (`id_task`);

--
-- Индексы таблицы `Time`
--
ALTER TABLE `Time`
  ADD PRIMARY KEY (`id_time`);

--
-- Индексы таблицы `Workers`
--
ALTER TABLE `Workers`
  ADD PRIMARY KEY (`id_worker`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `Departament`
--
ALTER TABLE `Departament`
  MODIFY `id_departament` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `Projects`
--
ALTER TABLE `Projects`
  MODIFY `id_project` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `Tasks`
--
ALTER TABLE `Tasks`
  MODIFY `id_task` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT для таблицы `Time`
--
ALTER TABLE `Time`
  MODIFY `id_time` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT для таблицы `Workers`
--
ALTER TABLE `Workers`
  MODIFY `id_worker` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
