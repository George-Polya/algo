import sys

def solve_baekjoon_1577_bottom_up():
    # 입력 처리
    input_line = sys.stdin.readline
    
    N_val, M_val = map(int, input_line().split())
    K = int(input_line())
    
    broken_roads = set()
    for _ in range(K):
        a, b, c, d = map(int, input_line().split())
        # 공사 중인 도로를 frozenset으로 저장
        # (a,b)와 (c,d)는 인접한 두 점
        point1 = (a, b)
        point2 = (c, d)
        broken_roads.add(frozenset({point1, point2}))
        
    # DP 테이블 초기화: dp[r][c]는 (0,0)에서 (r,c)까지 가는 경로의 수
    # N_val이 가로(x), M_val이 세로(y)
    dp = [[0] * (M_val + 1) for _ in range(N_val + 1)]
    
    # 기본 경우: (0,0)에 도달하는 방법은 1가지
    # 이 값을 설정하지 않으면, dp[0][0]에서 시작하는 경로가 계산되지 않음.
    # 하지만 아래 반복문에서 dp[r][c] += ... 형태로 누적하므로,
    # dp[0][0]이 0으로 시작하면 (0,0)에서 출발하는 경로가 제대로 전파되지 않음.
    # 따라서 dp[0][0] = 1로 설정하고, 반복문 내에서는 (0,0)을 특별 취급하지 않아도 됨.
    # 또는, 반복문 내에서 (r,c) == (0,0)일 때 dp[0][0] = 1로 설정하고 continue.
    # 여기서는 dp[0][0] = 1로 미리 설정하는 방식을 사용.
    
    dp[0][0] = 1 # 시작점 설정

    # DP 테이블 채우기
    for r in range(N_val + 1):  # x 좌표 (가로)
        for c in range(M_val + 1): # y 좌표 (세로)
            
            # (0,0)은 이미 1로 설정되어 있고, 아래 로직은 (0,0)으로 '오는' 경로를 더하는 것이므로
            # dp[0][0]에 대해서는 아래 if문들이 실행되지 않거나, 실행되어도 dp[-1][0] 등을 참조하지 않음.
            # 만약 dp[0][0]을 1로 설정하지 않고, 여기서 (r,c)==(0,0)일때 1을 할당하고 continue하면,
            # 다른 셀들이 dp[0][0]을 참조할 때 문제가 생길 수 있음.
            # 따라서 dp[0][0]=1을 먼저 설정하고, 모든 셀에 대해 동일한 로직으로 이전 셀로부터 값을 받아오는 것이 좋음.

            # 현재 셀 (r,c)로 올 수 있는 경로를 계산
            
            # 1. 왼쪽에서 오는 경우: (r-1, c)에서 (r, c)로 이동
            # (r-1, c)가 유효하고, 해당 도로가 공사 중이 아니어야 함.
            if r > 0: # (r-1, c)가 격자 내에 있는지 확인
                road_from_left = frozenset({(r - 1, c), (r, c)})
                if road_from_left not in broken_roads:
                    dp[r][c] += dp[r - 1][c]
            
            # 2. 아래쪽에서 오는 경우: (r, c-1)에서 (r, c)로 이동
            # (r, c-1)가 유효하고, 해당 도로가 공사 중이 아니어야 함.
            if c > 0: # (r, c-1)가 격자 내에 있는지 확인
                road_from_down = frozenset({(r, c - 1), (r, c)})
                if road_from_down not in broken_roads:
                    dp[r][c] += dp[r][c - 1]
            
            # 주의: dp[0][0]의 경우, r>0과 c>0 조건이 모두 false이므로 아무것도 더해지지 않음.
            # 만약 dp[0][0]을 0으로 초기화했다면, 여기서 dp[0][0]은 0으로 남게 됨.
            # 그래서 dp[0][0]=1 초기화가 중요.

    # 최종 결과 출력
    print(dp[N_val][M_val])

if __name__ == "__main__":
    solve_baekjoon_1577_bottom_up()