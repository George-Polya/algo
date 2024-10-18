select sum(g.score) as `score`, g.emp_no, e.emp_name, e.position,e.email
from hr_employees as e
join hr_grade as g
on e.emp_no = g.emp_no
group by g.year, g.emp_no
having g.year = 2022
order by 1 desc
limit 1;