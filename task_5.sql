USE Labor_SQL;
SELECT maker
FROM Product
WHERE (model = ANY(SELECT model FROM Laptop WHERE speed > 750) OR model = ANY(SELECT model FROM PC WHERE speed > 750))
AND type = 'PC' AND maker IN (SELECT maker FROM Product WHERE type='Laptop')
;