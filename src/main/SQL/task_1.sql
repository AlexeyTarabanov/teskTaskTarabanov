-- Задача 1
-- Дано:
-- В таблице 1 собраны все продажи автомобилей
-- В таблице 2 находятся ставки комиссий дилера с продаж конкретного автомобиля у конкретного дилера
-- В таблице 3 курс валют на конкретную дату.
--
-- Нужно написать SQL запрос, который выдает результат в формате таблицы 4.
-- *- Задача с повышенной сложностью
-- **- Задача сложнее

SELECT
    sub.total_count,
    "Тип",
    sum("Стоимость") as "Итоговые сборы",
    SUM(s."Стоимость" * (1 - c.commission)) AS "Итоговые сборы после вычета комиссии",
    (SELECT distinct on (s."Дилер") s."Дилер"
FROM car_sales
WHERE s."Дата" = s."Дата"
GROUP BY s."Дилер"
ORDER BY s."Дилер", COUNT(*) DESC LIMIT 1) AS "Дилер с наибольшим кол-вом продаж",
    SUM(s."Стоимость" / e."Значение") AS "Итоговая сумма в валюте",
    s."Дата"
FROM
    car_sales s
    JOIN commission_rates c ON s."Дилер" ||'_'|| s."Марка" = c.primary_key
    JOIN exchange_rates e ON s."Дата" = e."Дата" AND e."Валюта" = 'USD'
    JOIN (
    SELECT count("Тип") as total_count
    FROM car_sales
    WHERE "Дата"='2022-02-02'
    ) sub ON true
WHERE
    s."Дата"='2022-02-02' and s."Дилер" = (SELECT distinct on (s."Дилер") s."Дилер"
    FROM car_sales
    WHERE s."Дата" = s."Дата"
    GROUP BY s."Дилер"
    ORDER BY s."Дилер", COUNT(*)
    DESC LIMIT 1)
GROUP BY
    s."Дата", "Тип", "Дилер", sub.total_count
order by count("Тип") desc
    limit 1;