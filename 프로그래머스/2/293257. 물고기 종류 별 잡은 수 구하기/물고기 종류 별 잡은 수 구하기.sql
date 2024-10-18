select count(fish.id) as `fish_count`, name.fish_name as `fish_name`
from fish_name_info as name
join fish_info as fish
on name.fish_type = fish.fish_type
group by name.fish_name
order by `fish_count` desc;