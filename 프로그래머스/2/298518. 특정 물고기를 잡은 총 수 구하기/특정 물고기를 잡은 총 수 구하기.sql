select count(*) as fish_count
from fish_info as fInfo
join fish_name_info as nInfo
on fInfo.fish_type = nInfo.fish_type
where nInfo.fish_name in ('BASS', 'SNAPPER');
