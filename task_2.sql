USE Labor_SQL;
SELECT name 
FROM Ships
WHERE Ships.class RLIKE '[^g]o$'
;