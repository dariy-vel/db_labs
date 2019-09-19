USE Labor_SQL;
SELECT DISTINCT maker
FROM Product
WHERE type = 'PC' AND maker <> ALL(SELECT maker FROM Product WHERE type = 'Laptop')
;