SELECT 
    CAR_ID,
    CASE 
        WHEN car_id in (
            select car_id from car_rental_company_rental_history 
            where '2022-10-16' between start_date and end_date
        )
        THEN '대여중'
        ELSE '대여 가능'
    END AS AVAILABILITY
FROM 
    CAR_RENTAL_COMPANY_RENTAL_HISTORY
group by car_id
ORDER BY 
    CAR_ID DESC;
