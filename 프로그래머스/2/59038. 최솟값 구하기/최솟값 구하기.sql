select datetime
from animal_ins
where datetime = (
    select datetime
    from animal_ins
    order by datetime asc
    limit 1
);