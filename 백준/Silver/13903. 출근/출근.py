from collections import deque, namedtuple
dy = []
dx = []

def OOB(y, x):
    return y<=0 or y>R or x <= 0 or x> C

def printBoard(board):
    for y in range(1,R+1):
        for x in range(1,C+1):
            if isinstance(board[y][x], int):
                print(f"{board[y][x]:>5d}",end='')
            elif isinstance(board[y][x], float):
                s = "INF"
                print(f"{s:>5s}", end='')
        print()

if __name__ == "__main__":
    R, C = map(int,input().split())

    board = [[] for _ in range(R+1)]
    for y in range(1,R+1):
        board[y] = [0] + list(map(int,input().split()))

    N = int(input())

    for _ in range(N):
        y, x = map(int,input().split())
        dy.append(y)
        dx.append(x)

    INF  = float('inf')
    dist = [[INF for _ in range(C + 1)] for _ in range(R + 1)]

    q = deque()
    Pair = namedtuple('Pair', ['y','x'])
    for x in range(1, C+1):
        if board[1][x] == 1:
            q.append(Pair(1,x))
            dist[1][x] = 0

    ans = INF
    while q:
        cur = q.popleft()

        if cur.y == R:
            print(dist[cur.y][cur.x])
            exit(0)

        for d in range(N):
            ny = cur.y + dy[d]
            nx = cur.x + dx[d]

            if OOB(ny,nx) or dist[ny][nx] != INF or board[ny][nx] == 0:
                continue
            dist[ny][nx] = dist[cur.y][cur.x] + 1
            q.append(Pair(ny,nx))


    # printBoard(dist)
    print(-1)