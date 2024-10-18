-- 코드를 입력하세요
SELECT year(sale.sales_date) as `year`, month(sale.sales_date) as `month`, info.gender, count(distinct info.user_id) as `users`
from user_info as info
join online_sale as sale
on info.user_id = sale.user_id
where info.gender is not null
group by info.gender, `year`, `month`
order by `year` asc, `month` asc, gender asc;