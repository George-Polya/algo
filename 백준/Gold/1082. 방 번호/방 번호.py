import sys

# 입력 받기
n = int(sys.stdin.readline())
p = list(map(int, sys.stdin.readline().split()))
m = int(sys.stdin.readline())

# dp[cost] = 비용 cost로 만들 수 있는 가장 큰 수 (문자열)
# 초기값은 None (만들 수 없음)
dp = [None] * (m + 1)
dp[0] = "" # 비용 0으로는 빈 문자열을 만들 수 있음 (기저 상태)

def is_better(s1, s2):
    """s1이 s2보다 더 좋은(큰) 숫자인지 판별"""
    if s1 is None: # s1이 없으면 s2가 더 좋음 (False)
        return False
    if s2 is None: # s2가 없으면 s1이 더 좋음 (True)
        return True

    len1 = len(s1)
    len2 = len(s2)

    if len1 > len2: # 길이가 길면 더 좋음
        return True
    if len1 < len2: # 길이가 짧으면 더 좋지 않음
        return False
    # 길이가 같으면 사전 순으로 비교
    return s1 > s2

# DP 테이블 채우기
for c in range(1, m + 1): # 1부터 M까지의 비용에 대해
    for i in range(n):    # 0부터 N-1까지의 숫자를 사용해 봄
        cost_i = p[i]
        if c >= cost_i:   # 현재 비용으로 숫자 i를 살 수 있다면
            prev_cost = c - cost_i
            prev_num_str = dp[prev_cost]

            if prev_num_str is not None: # 이전 비용으로 만든 숫자가 존재한다면
                # 후보 숫자 문자열 생성
                candidate_num_str = ""
                if prev_num_str == "0": # 이전 숫자가 "0" 이었다면
                    candidate_num_str = str(i) # 새 숫자는 그냥 i (예: "0"+"1" -> "1")
                elif prev_num_str == "": # 이전 비용이 0 이었다면 (처음 숫자를 사는 경우)
                    candidate_num_str = str(i) # 새 숫자는 그냥 i (예: ""+"1" -> "1")
                else: # 일반적인 경우
                    candidate_num_str = prev_num_str + str(i) # 이어 붙임 (예: "1"+"0" -> "10")

                # 현재 dp[c]에 저장된 값보다 후보가 더 좋은지 확인하고 업데이트
                if is_better(candidate_num_str, dp[c]):
                    dp[c] = candidate_num_str

# 최종 결과 찾기: dp[1]부터 dp[M] 중 가장 좋은 값
max_num_str = None
for c in range(1, m + 1):
    if is_better(dp[c], max_num_str):
        max_num_str = dp[c]

# 결과 출력 (만들 수 있는 숫자가 없거나 "0"만 가능하면 "0" 출력)
print(max_num_str if max_num_str is not None else "0")