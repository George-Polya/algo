import sys

# 재귀 깊이 설정 (필요한 만큼만 설정, 너무 크면 메모리 낭비)
sys.setrecursionlimit(5000)

def solve():
    # 입력을 한 번에 받아 처리 속도 향상
    input_data = sys.stdin.read().split()
    if not input_data:
        return

    iterator = iter(input_data)
    
    # 전역 변수처럼 사용할 리스트들 (함수 인자 최소화 목적)
    # board, visited 등은 case마다 갱신
    
    # 방향 벡터 (상, 우, 하, 좌)
    dy = [-1, 0, 1, 0]
    dx = [0, 1, 0, -1]
    
    idx = 1
    
    while True:
        try:
            # N, M 파싱
            N = int(next(iterator))
            M = int(next(iterator))
        except StopIteration:
            break
        
        # 데이터 초기화
        board = []
        total_empty = 0
        
        for r in range(N):
            row = next(iterator)
            board.append(row)
            # 문자열 메서드로 '.' 개수 카운트 (속도 향상)
            total_empty += row.count('.')
        
        # 결과 헤더 미리 출력
        print(f"Case {idx}: ", end="")
        idx += 1
        
        if total_empty == 1:
            print(0)
            continue
            
        ans = float('inf')
        visited = [[False] * M for _ in range(N)]
        
        # DFS 함수를 내부에 정의하되, 인자를 최소화
        # board, visited, N, M 등은 외부 스코프 참조
        def dfs(y, x, direction, visit_cnt, phase):
            nonlocal ans
            
            # 가지치기: 이미 찾은 최적해보다 더 많이 꺾었으면 중단
            if ans <= phase:
                return
            
            # 모든 빈 칸 방문 완료
            if visit_cnt == total_empty:
                ans = phase
                return

            # 1. 직진 시도
            ny, nx = y + dy[direction], x + dx[direction]
            
            # 직진 가능 여부 체크 (범위, 방문여부, 벽)
            can_go_straight = False
            if 0 <= ny < N and 0 <= nx < M:
                if not visited[ny][nx] and board[ny][nx] != '*':
                    can_go_straight = True
                    visited[ny][nx] = True
                    dfs(ny, nx, direction, visit_cnt + 1, phase)
                    visited[ny][nx] = False
            
            # 2. 직진 불가능하면 회전 (다른 방향 탐색)
            if not can_go_straight:
                for i in range(4):
                    # 이미 막힌 방향(direction)은 다시 체크할 필요 없지만
                    # 로직 단순화를 위해 전체 순회 (혹은 i != direction 조건 추가 가능)
                    if i == direction: continue 
                    
                    ny, nx = y + dy[i], x + dx[i]
                    if 0 <= ny < N and 0 <= nx < M:
                         if not visited[ny][nx] and board[ny][nx] != '*':
                            visited[ny][nx] = True
                            # 방향을 바꾸므로 phase + 1
                            dfs(ny, nx, i, visit_cnt + 1, phase + 1)
                            visited[ny][nx] = False

        # 시작점 찾기 및 탐색 시작
        for r in range(N):
            for c in range(M):
                # '.' 인 곳에서만 시작, '*'이나 이미 방문한 곳은 패스
                # (여기서는 시작점이므로 visited 체크는 불필요하지만 board 체크 필수)
                if board[r][c] == '.':
                    visited[r][c] = True
                    # 4방향으로 시작 시도
                    for d in range(4):
                        dfs(r, c, d, 1, 1) # phase 1부터 시작? 문제 조건 확인 필요. 
                                           # 보통 시작 시 방향 잡는 것을 1로 치지 않는다면 0, 
                                           # Java 코드 원본이 1,1로 호출했으므로 그대로 유지.
                    visited[r][c] = False
        
        # 정답 출력
        print(ans if ans != float('inf') else -1)

if __name__ == "__main__":
    solve()