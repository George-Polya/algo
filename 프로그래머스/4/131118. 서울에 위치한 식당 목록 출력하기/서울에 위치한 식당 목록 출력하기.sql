select I.rest_id,
       I.rest_name,
       I.food_type,
       I.favorites,
       I.address,
       round(avg(review_score),2) as review_score
from rest_info as I
join rest_review as R
on I.rest_id = R.rest_id
where I.address like '서울%'
GROUP BY I.REST_ID, I.REST_NAME, I.FOOD_TYPE, I.FAVORITES, I.ADDRESS
order by review_score desc, favorites desc;

    
