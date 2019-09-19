USE Labor_SQL;
SELECT maker FROM Product
WHERE model IN (SELECT model FROM PC)
GROUP BY maker
;