-- 코드를 입력하세요
SELECT p.product_code as `product_code`, sum(s.sales_amount * p.price) as `sales`
from product as p
join offline_sale as s
on p.product_id = s.product_id
group by p.product_code
order by `sales` desc, `product_code` asc;