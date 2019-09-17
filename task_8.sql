USE Labor_SQL;
SELECT DISTINCT maker, 
	(SELECT AVG(screen) 
    FROM Laptop 
    JOIN Product ON Laptop.model = Product.model 
    WHERE Product.maker = P.maker)
    AS average_screen
FROM Product AS P
WHERE type = 'Laptop';