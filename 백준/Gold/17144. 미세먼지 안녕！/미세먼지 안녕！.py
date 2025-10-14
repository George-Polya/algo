import sys

# 빠른 입력을 위해 sys.stdin.readline 사용
input = sys.stdin.readline

def diffuse():
    """미세먼지를 확산시키는 함수"""
    # 전역 변수 board를 수정하기 위해 global 키워드 사용
    global board
    
    # 방향 벡터 (상, 하, 좌, 우)
    dy = [-1, 1, 0, 0]
    dx = [0, 0, -1, 1]
    
    # 확산 결과를 저장할 임시 격자
    # 공기청정기 위치는 -1, 나머지는 0으로 초기화
    nxt_board = [[0] * c for _ in range(r)]
    for y in range(r):
        for x in range(c):
            if board[y][x] == -1:
                nxt_board[y][x] = -1

    for y in range(r):
        for x in range(c):
            # 미세먼지가 있는 칸만 확산 시작
            if board[y][x] > 0:
                spread_amount = board[y][x] // 5
                spread_count = 0
                
                # 4방향으로 확산 시도
                for i in range(4):
                    ny, nx = y + dy[i], x + dx[i]
                    
                    # 격자 범위 안이고, 공기청정기가 없는 곳으로만 확산
                    if 0 <= ny < r and 0 <= nx < c and board[ny][nx] != -1:
                        nxt_board[ny][nx] += spread_amount
                        spread_count += 1
                
                # 남은 미세먼지 양을 현재 위치에 더함
                remaining_amount = board[y][x] - (spread_amount * spread_count)
                nxt_board[y][x] += remaining_amount
    
    # 확산이 완료된 격자를 원래 격자에 반영
    board = nxt_board

def purify():
    """공기청정기를 작동시켜 미세먼지를 순환시키는 함수"""
    global board
    
    # 공기청정기의 위쪽, 아래쪽 y좌표
    up_y = purifier_pos[0]
    down_y = purifier_pos[1]
    
    # 1. 위쪽 공기 순환 (반시계 방향)
    # 아래 -> 위 (왼쪽 열)
    for y in range(up_y - 1, 0, -1):
        board[y][0] = board[y - 1][0]
    # 오 -> 왼 (맨 위 행)
    for x in range(0, c - 1):
        board[0][x] = board[0][x + 1]
    # 위 -> 아래 (오른쪽 열)
    for y in range(0, up_y):
        board[y][c - 1] = board[y + 1][c - 1]
    # 왼 -> 오 (공기청정기 행)
    for x in range(c - 1, 1, -1):
        board[up_y][x] = board[up_y][x - 1]
    # 공기청정기에서 나온 깨끗한 공기
    board[up_y][1] = 0

    # 2. 아래쪽 공기 순환 (시계 방향)
    # 위 -> 아래 (왼쪽 열)
    for y in range(down_y + 1, r - 1):
        board[y][0] = board[y + 1][0]
    # 오 -> 왼 (맨 아래 행)
    for x in range(0, c - 1):
        board[r - 1][x] = board[r - 1][x + 1]
    # 아래 -> 위 (오른쪽 열)
    for y in range(r - 1, down_y, -1):
        board[y][c - 1] = board[y - 1][c - 1]
    # 왼 -> 오 (공기청정기 행)
    for x in range(c - 1, 1, -1):
        board[down_y][x] = board[down_y][x - 1]
    # 공기청정기에서 나온 깨끗한 공기
    board[down_y][1] = 0

# --- 메인 로직 ---

# r: 행, c: 열, t: 시간
r, c, t = map(int, input().split())

# 격자 정보 입력 (0-based 인덱싱 사용)
board = [list(map(int, input().split())) for _ in range(r)]
purifier_pos = []

# 공기청정기 위치(-1) 찾기
for i in range(r):
    if board[i][0] == -1:
        purifier_pos.append(i)

# t초 동안 시뮬레이션 반복
for _ in range(t):
    diffuse()
    purify()

# 남아있는 미세먼지 총량 계산
total_dust = 0
for y in range(r):
    for x in range(c):
        if board[y][x] > 0:
            total_dust += board[y][x]

print(total_dust)