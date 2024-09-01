
select E1.id, COALESCE(COUNT(E2.ID), 0) AS CHILD_COUNT
from ecoli_data as E1
left join ecoli_data as E2 
on E1.ID = E2.parent_id
group by E1.ID
order by E1.ID asc;