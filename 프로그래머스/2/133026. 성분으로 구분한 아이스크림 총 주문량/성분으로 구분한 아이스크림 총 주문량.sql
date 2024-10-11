select I.ingredient_type, sum(total_order) as total_order
from first_half F
join icecream_info I
on F.flavor = I.flavor
group by I.ingredient_type
order by total_order asc;
