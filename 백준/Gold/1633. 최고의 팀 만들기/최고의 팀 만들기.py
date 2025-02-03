import sys

def solve(cur, b_cnt, w_cnt):
    if b_cnt == 15 and w_cnt == 15:
        return 0
    
    if cur == n:
        return 0
    
    if dp[cur][b_cnt][w_cnt] != -1:
        return dp[cur][b_cnt][w_cnt]
    
    # 현재 선수 선택 안 하는 경우
    ret = solve(cur + 1, b_cnt, w_cnt)
    
    # 현재 선수를 블랙팀에 선택하는 경우
    if b_cnt < 15:
        ret = max(ret, solve(cur + 1, b_cnt + 1, w_cnt) + blacks[cur])
        
    # 현재 선수를 화이트팀에 선택하는 경우
    if w_cnt < 15:
        ret = max(ret, solve(cur + 1, b_cnt, w_cnt + 1) + whites[cur])
    
    dp[cur][b_cnt][w_cnt] = ret
    return ret

# 입력 처리
whites = []
blacks = []

for line in sys.stdin:
    if not line.strip():
        break
    w, b = map(int, line.strip().split())
    whites.append(w)
    blacks.append(b)

n = len(whites)

# DP 테이블 초기화
dp = [[[-1] * 16 for _ in range(16)] for _ in range(n + 1)]

# 결과 출력
print(solve(0, 0, 0))