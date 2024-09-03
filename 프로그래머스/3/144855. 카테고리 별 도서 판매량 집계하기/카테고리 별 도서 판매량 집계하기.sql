select B.category, sum(S.sales) as TOTAL_SALES
from book B
join book_sales S
on B.book_id = S.book_id
where date_format(S.sales_date, "%Y-%m") = "2022-01"
group by B.category
order by B.category asc;