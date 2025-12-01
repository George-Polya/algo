import sys
from collections import deque
from itertools import permutations, product

input = sys.stdin.read

# 전역 상수 및 변수
N = 5
INF = 987654321
ans = INF

# 방향 벡터 (z, y, x)
dz = (0, 0, 0, 0, 1, -1)
dy = (0, 0, 1, -1, 0, 0)
dx = (1, -1, 0, 0, 0, 0)

def solve():
    global ans
    
    # 1. 입력 처리
    data = input().split()
    original_layers = []
    idx = 0
    for _ in range(N):
        layer = []
        for _ in range(N):
            row = []
            for _ in range(N):
                row.append(int(data[idx]))
                idx += 1
            layer.append(row)
        original_layers.append(layer)

    # 2. 회전된 판 미리 계산 (5개 층 * 4개 회전)
    # rotated_layers[layer_index][rotation_index] = 2D grid
    rotated_layers = [[[] for _ in range(4)] for _ in range(N)]

    for i in range(N):
        # 0도 (원본)
        rotated_layers[i][0] = original_layers[i]
        # 90, 180, 270도 생성
        for r in range(1, 4):
            prev = rotated_layers[i][r-1]
            # 시계방향 90도 회전: temp[x][N-1-y] = prev[y][x]
            # 또는 zip을 이용한 회전
            rotated_layers[i][r] = [list(row) for row in zip(*prev[::-1])]

    # BFS 함수 (내부 정의로 오버헤드 줄임 or 별도 정의)
    def bfs(maze):
        # 입구/출구 막힘 체크
        if maze[0][0][0] == 0 or maze[4][4][4] == 0:
            return INF
        
        # visited 배열 대신 dist 배열 사용 (-1로 초기화)
        dist = [[[-1] * N for _ in range(N)] for _ in range(N)]
        q = deque([(0, 0, 0)])
        dist[0][0][0] = 0
        
        while q:
            z, y, x = q.popleft()
            
            # 목적지 도달
            if z == 4 and y == 4 and x == 4:
                return dist[4][4][4]
            
            # 현재 최적해보다 길어지면 가지치기 (선택 사항, 효과 미미할 수 있음)
            if dist[z][y][x] >= ans:
                continue

            for i in range(6):
                nz, ny, nx = z + dz[i], y + dy[i], x + dx[i]
                
                if 0 <= nz < N and 0 <= ny < N and 0 <= nx < N:
                    if maze[nz][ny][nx] == 1 and dist[nz][ny][nx] == -1:
                        dist[nz][ny][nx] = dist[z][y][x] + 1
                        q.append((nz, ny, nx))
        return INF

    # 3. 층 쌓는 순서 (Permutations)
    for p in permutations(range(N)):
        # 4. 각 층의 회전 (Product) - itertools.product 사용이 재귀보다 빠름
        for rots in product(range(4), repeat=N):
            
            # 현재 층 순서(p)와 회전(rots)에 맞춰 미로 구성
            # 매번 새로운 3차원 배열을 deepcopy 하는 대신, 참조만 가져옴
            current_maze = [rotated_layers[p[i]][rots[i]] for i in range(N)]
            
            # 입구 막혀있으면 즉시 스킵 (가장 강력한 가지치기)
            if current_maze[0][0][0] == 0 or current_maze[4][4][4] == 0:
                continue

            # BFS 수행
            res = bfs(current_maze)
            
            if res != INF:
                ans = min(ans, res)
                # 이론적 최소값 12가 나오면 즉시 종료
                if ans == 12:
                    print(12)
                    return

    print(ans if ans != INF else -1)

if __name__ == "__main__":
    solve()