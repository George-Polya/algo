import sys
from collections import deque

# 빠른 입력을 위한 설정
input = sys.stdin.readline

# n: 행, m: 열, r: 라운드 수
n, m, r = map(int, input().split())

# 보드 정보 (1-based 인덱싱을 위해 n+1, m+1 크기로 생성)
board = [[0] * (m + 1)]
for _ in range(n):
    board.append([0] + list(map(int, input().split())))

# 도미노 상태 (False: 서있음, True: 넘어져있음)
visited = [[False] * (m + 1) for _ in range(n + 1)]

# 방향 벡터 (E, W, S, N)
dy = [0, 0, 1, -1]
dx = [1, -1, 0, 0]

# 방향 문자를 인덱스로 변환하는 딕셔너리
dir_map = {'E': 0, 'W': 1, 'S': 2, 'N': 3}

# 공격 함수 (BFS)
def offense(y, x, d):
    q = deque([(y, x)])
    visited[y][x] = True
    count = 0
    
    while q:
        cur_y, cur_x = q.popleft()
        count += 1
        height = board[cur_y][cur_x]
        
        # 현재 도미노의 높이만큼 연쇄적으로 넘어뜨림
        for k in range(1, height):
            ny, nx = cur_y + dy[d] * k, cur_x + dx[d] * k
            
            # 보드 범위를 벗어나면 중단
            if not (1 <= ny <= n and 1 <= nx <= m):
                break
            
            # 아직 넘어지지 않은 도미노라면 큐에 추가
            if not visited[ny][nx]:
                visited[ny][nx] = True
                q.append((ny, nx))
    return count

# 수비 함수
def defense(y, x):
    # 넘어져 있는 도미노를 다시 세움
    visited[y][x] = False

score = 0
# r 라운드 동안 게임 진행
for _ in range(r):
    # 공격
    off_y, off_x, off_dir_char = input().split()
    off_y, off_x = int(off_y), int(off_x)
    off_dir = dir_map[off_dir_char]
    
    # 아직 넘어지지 않은 도미노인 경우에만 공격 수행
    if not visited[off_y][off_x]:
        score += offense(off_y, off_x, off_dir)
    
    # 수비
    def_y, def_x = map(int, input().split())
    defense(def_y, def_x)

# 최종 점수 출력
print(score)

# 최종 보드 상태 출력
for i in range(1, n + 1):
    for j in range(1, m + 1):
        print('F' if visited[i][j] else 'S', end=' ')
    print()