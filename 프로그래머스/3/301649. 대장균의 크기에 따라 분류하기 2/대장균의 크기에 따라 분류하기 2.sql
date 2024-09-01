WITH ecoli_ranked AS (
    SELECT id,
           size_of_colony,
           NTILE(4) OVER (ORDER BY size_of_colony DESC) AS _size
    FROM ecoli_data
)
SELECT id,
       CASE
           WHEN _size = 1 THEN 'CRITICAL'
           WHEN _size = 2 THEN 'HIGH'
           WHEN _size = 3 THEN 'MEDIUM'
           WHEN _size = 4 THEN 'LOW'
       END AS COLONY_NAME
FROM ecoli_ranked
ORDER BY id;