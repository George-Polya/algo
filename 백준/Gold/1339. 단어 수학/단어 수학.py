import sys
from collections import defaultdict

# 빠른 입력을 위해 sys.stdin.readline 사용 (선택 사항)
input = sys.stdin.readline

# 1. 입력 받기
n = int(input())
words = [input().strip() for _ in range(n)]

# 2. 알파벳별 가중치 계산
weights = defaultdict(int) # 각 알파벳의 총 가중치를 저장할 딕셔너리

for word in words:
    length = len(word)
    place_value = 1 # 1의 자리부터 시작
    # 단어의 오른쪽 끝(1의 자리)부터 왼쪽으로 이동하며 가중치 계산
    for i in range(length - 1, -1, -1):
        char = word[i]
        weights[char] += place_value
        place_value *= 10 # 다음 자릿수로 이동 (1 -> 10 -> 100 ...)

# 3. 가중치 값만 추출하여 내림차순 정렬
# weights.values()는 딕셔너리의 값들(계산된 가중치들)만 가져옴
sorted_weights = sorted(weights.values(), reverse=True)

# 4. 최대 합 계산
max_sum = 0
current_digit = 9 # 가장 높은 가중치에 할당될 숫자 9부터 시작
for weight in sorted_weights:
    max_sum += weight * current_digit
    current_digit -= 1 # 다음 가중치에는 1 작은 숫자 할당

# 결과 출력
print(max_sum)