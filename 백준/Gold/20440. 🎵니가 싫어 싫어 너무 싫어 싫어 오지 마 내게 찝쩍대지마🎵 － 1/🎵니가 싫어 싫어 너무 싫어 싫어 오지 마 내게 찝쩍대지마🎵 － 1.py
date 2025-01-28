from collections import defaultdict

n = int(input())
events = defaultdict(int)

for _ in range(n):
    start, end = map(int, input().split())
    events[start] += 1
    events[end] -= 1

max_value = 0
cur_value = 0
start,end = -1, -1
flag = False
prv_value = 0

for i in sorted(events):
    prv_value = cur_value
    cur_value += events[i]
    
    if cur_value > max_value:
        max_value = cur_value
        start = i
        flag = True
    
    if flag and prv_value == max_value and cur_value < max_value:
        end = i
        flag = False
        
print(max_value)
print(start, end)