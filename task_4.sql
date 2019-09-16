USE Labor_SQL;
SELECT DISTINCT maker
FROM Product
WHERE type = 'PC' and maker NOT IN (SELECT maker FROM Product WHERE type = 'Laptop')
;