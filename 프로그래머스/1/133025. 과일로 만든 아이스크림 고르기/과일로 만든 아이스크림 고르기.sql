select F.flavor
from `first_half` as F
join `icecream_info` as I on F.flavor = I.flavor
where F.total_order > 3000 and I.ingredient_type="fruit_based"
order by F.total_order desc;