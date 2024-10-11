-- 코드를 입력하세요
SELECT U.user_id, U.nickname, sum(B.price) as `total_sales`
from used_goods_board B
join used_goods_user U
on B.writer_id = U.user_id
where B.status = 'DONE'
group by U.user_id, U.nickname
having sum(B.price) >= 700000
order by `total_sales` asc;
