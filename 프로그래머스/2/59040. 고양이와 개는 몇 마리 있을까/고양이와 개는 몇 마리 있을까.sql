select animal_type, count(animal_id) as `count`
from animal_ins
where animal_type in ('Cat', 'Dog')
group by animal_type
ORDER BY 
    CASE WHEN ANIMAL_TYPE = 'Cat' THEN 1
         WHEN ANIMAL_TYPE = 'Dog' THEN 2
    END;