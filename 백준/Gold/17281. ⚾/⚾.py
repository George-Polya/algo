import sys
from itertools import permutations

# 빠른 입력을 위해 사용
input = sys.stdin.readline

def solve():
    n = int(input())
    # innings[i][j]: i번째 이닝에서 j번 선수의 결과
    # Java 코드와 달리 0-index를 사용합니다 (선수 1번 -> 인덱스 0)
    innings = [list(map(int, input().split())) for _ in range(n)]

    ans = 0
    
    # 1번 선수(index 0)를 제외한 나머지 8명(index 1~8)의 순열 생성
    # Java 코드의 solve/initialize 부분과 동일한 역할
    for perm in permutations(range(1, 9)):
        # 타순 결정: 4번 타자(인덱스 3) 자리에 1번 선수(0)를 고정
        # perm[:3] (1,2,3번 타자) + [0] (4번 타자) + perm[3:] (5~9번 타자)
        order = list(perm[:3]) + [0] + list(perm[3:])
        
        score = 0
        hitter_idx = 0 # 현재 타순 (0 ~ 8)
        
        # 이닝 시작
        for i in range(n):
            out_cnt = 0
            # 1루, 2루, 3루 주자 유무 (0: 없음, 1: 있음)
            b1, b2, b3 = 0, 0, 0 
            
            while out_cnt < 3:
                curr_player = order[hitter_idx] # 현재 타자 번호
                res = innings[i][curr_player] # 이번 이닝에서 해당 선수의 결과
                
                if res == 0: # 아웃
                    out_cnt += 1
                elif res == 1: # 안타
                    score += b3
                    b1, b2, b3 = 1, b1, b2
                elif res == 2: # 2루타
                    score += b2 + b3
                    b1, b2, b3 = 0, 1, b1
                elif res == 3: # 3루타
                    score += b1 + b2 + b3
                    b1, b2, b3 = 0, 0, 1
                elif res == 4: # 홈런
                    score += b1 + b2 + b3 + 1
                    b1, b2, b3 = 0, 0, 0
                
                # 다음 타자로 넘어감 (9번 타자 다음은 다시 1번 타자)
                hitter_idx = (hitter_idx + 1) % 9
        
        ans = max(ans, score)

    print(ans)

if __name__ == "__main__":
    solve()