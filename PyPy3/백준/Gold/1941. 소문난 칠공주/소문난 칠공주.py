import sys
from itertools import combinations

# 입력을 빠르게 받기 위한 설정
input = sys.stdin.readline

# 5x5 보드 입력 받기
board = [input().strip() for _ in range(5)]

# 보드의 모든 좌표 (0,0) ~ (4,4)를 리스트에 저장
# 자바 코드의 arr 역할
coords = [(y, x) for y in range(5) for x in range(5)]

# 상하좌우 방향
dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]

def is_connected(selected):
    """
    선택된 7개의 좌표가 서로 연결되어 있는지 확인하는 함수 (BFS 사용)
    """
    # 빠른 조회를 위해 set으로 변환 (자바의 isUsed 역할)
    selected_set = set(selected)
    
    # 첫 번째 좌표에서 시작
    start = selected[0]
    q = [start]
    visited = {start}
    
    head = 0
    while head < len(q):
        y, x = q[head]
        head += 1
        
        for i in range(4):
            ny, nx = y + dy[i], x + dx[i]
            
            # 범위 내에 있고, 선택된 7명 중 하나이며, 아직 방문 안 했다면
            if (ny, nx) in selected_set and (ny, nx) not in visited:
                visited.add((ny, nx))
                q.append((ny, nx))
    
    # 방문한 노드 개수가 7개라면 모두 연결된 것
    return len(visited) == 7

def solve():
    ans = 0
    
    # 25개 좌표 중 7개를 뽑는 모든 조합 확인 (자바의 solve 재귀함수 대체)
    # 25C7 = 480,700가지 경우의 수라 시간 내 충분함
    for selected in combinations(coords, 7):
        
        # 1. 'S'(이다솜 파)가 4명 이상인지 확인
        s_count = 0
        for y, x in selected:
            if board[y][x] == 'S':
                s_count += 1
        
        # S가 4명 미만이면 조건 불충족
        if s_count < 4:
            continue
            
        # 2. 7명이 가로세로로 인접해 있는지 확인
        if is_connected(selected):
            ans += 1
            
    print(ans)

if __name__ == "__main__":
    solve()