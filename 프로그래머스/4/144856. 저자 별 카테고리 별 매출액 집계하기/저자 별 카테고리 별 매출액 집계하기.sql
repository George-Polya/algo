select A.author_id, A.author_name, B.category, sum((B.price * S.sales)) as TOTAL_SALES
from book_sales as S
join book as B on S.book_id = B.book_id
join author as A on A.author_id = B.author_id
where date_format(S.sales_date,"%Y-%m") = '2022-01'
group by author_id, category
order by A.author_id asc, B.category desc;