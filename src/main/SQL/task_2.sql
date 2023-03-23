-- Задача 2
-- На примере таблицы 1 предложить способы безопасного удаления дубликатов марки и дилера.


-- все 3 запроса вернут только уникальные значения
-- при этом оригинальная таблица останется не тронутой
SELECT DISTINCT Марка, Дилер FROM Таблица1;
SELECT Марка, Дилер FROM Таблица1 GROUP BY Марка, Дилер;
SELECT DISTINCT ON (Марка, Дилер) * FROM Таблица1

-- если необходимо изменить саму таблицу
-- выбирает только уникальные комбинации марки и дилера
-- и использует их для создания новой таблицы без дубликатов
CREATE TABLE new_table AS
SELECT DISTINCT Марка, Дилер FROM test_table;
-- удаляем исходную таблицу и переименовываем новую в исходное имя таблицы:
DROP TABLE test_table;
ALTER TABLE new_table RENAME TO test_table;
select * from test_table;