import sys
from collections import deque

# 빠른 입력을 위해 sys.stdin.readline 사용
input = sys.stdin.readline

m, n = map(int, input().split())
# 토마토 상자 정보 입력
board = [list(map(int, input().split())) for _ in range(n)]

# 날짜(거리)를 저장하며 방문 여부를 확인하는 배열
# 0: 이미 익었거나(-1), 토마토가 없는 경우(1)
# -1: 익지 않은 토마토(0)가 있는 경우
dist = [[0] * m for _ in range(n)]
q = deque()

# 초기 상태 설정: 익은 토마토는 큐에 넣고, 익지 않은 토마토는 dist를 -1로 설정
for i in range(n):
    for j in range(m):
        if board[i][j] == 1:
            q.append((i, j))
        elif board[i][j] == 0:
            dist[i][j] = -1

# 상하좌우 이동을 위한 방향 벡터
dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

# BFS 시작
while q:
    x, y = q.popleft() # 큐에서 현재 위치를 꺼냄
    
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        
        # 상자 범위를 벗어나는지 확인
        if nx < 0 or nx >= n or ny < 0 or ny >= m:
            continue
        
        # 이미 방문했거나 토마토가 없는 칸인지 확인 (익지 않은 토마토가 아니면 패스)
        if dist[nx][ny] >= 0:
            continue
            
        # 새로운 토마토를 익게 함 (날짜 업데이트 후 큐에 추가)
        dist[nx][ny] = dist[x][y] + 1
        q.append((nx, ny))

# 결과 확인
ans = 0
for i in range(n):
    for j in range(m):
        # 만약 익지 않은 토마토가 하나라도 있다면
        if dist[i][j] == -1:
            print(-1)
            exit(0) # 프로그램 종료
        # 최대 일수 갱신
        ans = max(ans, dist[i][j])

print(ans)