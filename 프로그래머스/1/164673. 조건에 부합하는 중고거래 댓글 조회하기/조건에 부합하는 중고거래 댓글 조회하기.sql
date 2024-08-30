select B.title,
       B.board_id,
       R.reply_id,
       R.writer_id,
       R.contents,
       date_format(R.created_date,"%Y-%m-%d") as created_date
from used_goods_board as B
join used_goods_reply as R
on B.board_id = R.board_id
where date_format(B.created_date,"%Y-%m")= '2022-10'
order by R.created_date asc, B.title asc;