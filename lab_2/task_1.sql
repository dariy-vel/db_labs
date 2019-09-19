USE Labor_SQL;
SELECT Laptop.model, Laptop.ram, Laptop.screen 
FROM Product
JOIN Laptop ON Product.model = Laptop.model
WHERE Laptop.price > 1000
ORDER BY Laptop.ram ASC, Laptop.price DESC
;