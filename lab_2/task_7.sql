USE Labor_SQL;
SELECT maker FROM Product
WHERE maker NOT IN 
	(SELECT maker FROM Product WHERE model NOT IN (SELECT model FROM PC) AND type = 'PC')
    AND type = 'PC'
GROUP BY maker
;