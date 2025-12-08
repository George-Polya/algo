from collections import deque, namedtuple

dy = [-1,1,0,0]
dx = [0,0,-1,1]

def OOB(y,x):
    return y<0 or y>=N or x<0 or x>=M

def printBoard(board):
    for y in range(N):
        for x in range(M):
            print(f"{board[y][x]:>3d}",end='')
        print()

def bfs(q, turn):
    global visited, board
    ret = []
    # print(f"len(q): {len(q)}")
    for y,x in q:
        visited[y][x] = turn
    q = deque(q)

    while q:
        cur = q.popleft()

        for d in range(4):
            ny,nx = cur.y + dy[d], cur.x + dx[d]

            if OOB(ny,nx) or visited[ny][nx] != -1:
                continue
            if board[ny][nx] == 1:
                ret.append(Pair(ny,nx))
            elif board[ny][nx] == 0:
                q.append(Pair(ny,nx))
                visited[ny][nx] = turn

    # for y,x in ret:
    #     board[y][x] = 0
    # print(f"len(ret): {len(ret)}")
    return ret

if __name__ == "__main__":
    N,M = map(int,input().split())

    visited = [[-1 for _ in range(M)] for _ in range(N)]
    board = [[] for _ in range(N)]
    q = []
    Pair = namedtuple('Pair', ['y', 'x'])
    for y in range(N):
        board[y] = list(map(int, input().split()))
        for x in range(M):
            if y == 0 or y == N - 1 or x == 0 or x == M - 1:
                q.append(Pair(y,x))

    t = 0
    ans = 0
    while q:
        q = bfs(q, t)
        t += 1

    # printBoard(visited)
    t -= 1
    print(t)
    cnt = 0
    for y in range(N):
        for x in range(M):
            if visited[y][x] == t and board[y][x] == 1:
                cnt += 1
    print(cnt)