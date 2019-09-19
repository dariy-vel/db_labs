USE Labor_SQL;
SELECT Income_o.point, Income_o.date, inc, Outcome_o.out
FROM Income_o
LEFT OUTER JOIN Outcome_o ON Income_o.point = Outcome_o.point AND Income_o.date = Outcome_o.date
UNION
SELECT Outcome_o.point, Outcome_o.date, Income_o.inc, Outcome_o.out
FROM Outcome_o
LEFT OUTER JOIN Income_o ON Outcome_o.point = Income_o.point AND Income_o.date = Outcome_o.date
ORDER BY point, date
;