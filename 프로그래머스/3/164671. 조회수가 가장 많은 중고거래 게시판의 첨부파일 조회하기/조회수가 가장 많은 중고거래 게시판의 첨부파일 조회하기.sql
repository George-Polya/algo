select concat("/home/grep/src/", file.board_id, "/", file.file_id, file.file_name, file.file_ext) as `file_path`
from used_goods_board as `board`
join used_goods_file as `file`
on board.board_id = file.board_id
where board.views = (select max(views) from used_goods_board)
order by file.file_id desc;