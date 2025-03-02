from collections import deque, namedtuple

# 상태를 나타내는 namedtuple 정의
State = namedtuple('State', ['x', 'y', 'direction', 'visited'])

def bfs(start_x, start_y, board, c_positions):
    n, m = len(board), len(board[0])
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]  # 상, 하, 좌, 우
    queue = deque()
    visited = [[[[False] * 4 for _ in range(4)] for _ in range(m)] for _ in range(n)]
    
    # 초기 상태 설정
    for d in range(4):
        queue.append(State(start_x, start_y, d, 0))
        visited[start_x][start_y][d][0] = True
    
    steps = 0
    while queue:
        for _ in range(len(queue)):
            state = queue.popleft()
            if state.visited == 3:  # 두 개의 'C' 지점을 모두 방문한 경우
                return steps
            
            for d, (dx, dy) in enumerate(directions):
                if state.direction == d:
                    continue  # 이전에 이동한 방향과 같은 방향으로는 이동하지 않음
                
                nx, ny = state.x + dx, state.y + dy
                if 0 <= nx < n and 0 <= ny < m and board[nx][ny] != '#':
                    new_visited = state.visited
                    if (nx, ny) in c_positions:
                        index = c_positions.index((nx, ny))
                        new_visited |= (1 << index)
                    
                    if not visited[nx][ny][d][new_visited]:
                        visited[nx][ny][d][new_visited] = True
                        queue.append(State(nx, ny, d, new_visited))
        steps += 1
    
    return -1  # 배달이 불가능한 경우

def main():
    import sys
    input = sys.stdin.read
    data = input().split()
    
    n, m = int(data[0]), int(data[1])
    board = []
    c_positions = []
    start_x = start_y = -1
    
    index = 2
    for i in range(n):
        row = data[index]
        index += 1
        board.append(row)
        for j in range(m):
            if row[j] == 'S':
                start_x, start_y = i, j
            elif row[j] == 'C':
                c_positions.append((i, j))
    
    result = bfs(start_x, start_y, board, c_positions)
    print(result)

if __name__ == "__main__":
    main()