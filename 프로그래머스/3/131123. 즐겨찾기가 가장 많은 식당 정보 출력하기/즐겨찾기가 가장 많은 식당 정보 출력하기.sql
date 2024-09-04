select A.food_type, rest_id, rest_name, favorites
from rest_info A
join 
(
    select food_type, max(favorites) max_favorite
    from rest_info
    group by food_type
) B
on A.food_type = B.food_type and A.favorites = B.max_favorite
order by A.food_type desc;