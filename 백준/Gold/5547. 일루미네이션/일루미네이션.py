import sys
from collections import deque

# 빠른 입력을 위한 설정
input = sys.stdin.readline

def bfs():
    """
    (0, 0)에서 시작하여 외부 공간을 탐색하며 건물과 맞닿은 벽의 개수를 센다.
    """
    q = deque([(0, 0)])
    visited[0][0] = True
    count = 0

    # 짝수 행일 때의 x 이동 방향
    dx_even = [-1, 0, 1, 0, -1, -1]
    # 홀수 행일 때의 x 이동 방향
    dx_odd = [0, 1, 1, 1, 0, -1]
    # y 이동 방향 (짝수/홀수 행 공통)
    dy = [-1, -1, 0, 1, 1, 0]

    while q:
        y, x = q.popleft()

        # 현재 행이 홀수인지 짝수인지에 따라 사용할 dx를 선택
        current_dx = dx_odd if y % 2 == 1 else dx_even

        # 6가지 방향으로 탐색
        for i in range(6):
            ny, nx = y + dy[i], x + current_dx[i]

            # 격자 범위를 벗어나는지 확인
            if not (0 <= ny < h + 2 and 0 <= nx < w + 2):
                continue
            
            # 다음 위치가 건물(벽)인 경우
            if board[ny][nx] == 1:
                count += 1
            # 다음 위치가 방문하지 않은 빈 공간인 경우
            elif not visited[ny][nx]:
                visited[ny][nx] = True
                q.append((ny, nx))
                
    return count

# 1. 입력 처리
w, h = map(int, input().split())

# 2. 격자 및 방문 배열 초기화 (상하좌우 패딩 추가)
board = [[0] * (w + 2) for _ in range(h + 2)]
visited = [[False] * (w + 2) for _ in range(h + 2)]

# 3. 격자 정보 입력 (패딩된 공간 안쪽에 배치)
for i in range(1, h + 1):
    row = list(map(int, input().split()))
    for j in range(1, w + 1):
        board[i][j] = row[j - 1]

# 4. BFS 실행 및 결과 출력
result = bfs()
print(result)