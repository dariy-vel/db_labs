USE Labor_SQL;
SELECT Product.maker, Product.type, Laptop.speed, Laptop.hd
FROM Product
JOIN Laptop ON Product.model=Laptop.model
WHERE Laptop.hd >= 10;