USE Labor_SQL;
SELECT AVG(price) as average_price
FROM (
	SELECT price FROM Laptop
	WHERE model IN (SELECT model FROM Product WHERE maker = 'A' AND type = 'Laptop')
	UNION 
	SELECT price FROM PC
	WHERE model IN (SELECT model FROM Product WHERE maker = 'A' AND type = 'PC')
) a
;