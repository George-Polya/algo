select u.user_id, u.nickname, concat(city,' ',u.street_address1,' ', u.street_address2) as '전체주소', CONCAT(SUBSTRING(TLNO, 1, 3), '-', SUBSTRING(TLNO, 4, 4), '-', SUBSTRING(TLNO, 8, 4)) as `tlno`
from used_goods_board as b
join used_goods_user as u
on b.writer_id = u.user_id
group by u.user_id
having count(b.board_id) >= 3
order by u.user_id desc;