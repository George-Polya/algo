from collections import deque, namedtuple

dy = [-1,1,0,0]
dx = [0,0,-1,1]

def OOB(y, x):
    return y <= 0 or y + H - 1 > N or x <= 0 or x + W  - 1> M

def printBoard(board):
    for y in range(1,N+1):
        for x in range(1,M+1):
            print(f"{pSum[y][x]:>3d}",end='')
        print()

def init():
    global pSum

    for y in range(1,N+1):
        for x in range(1, M+1):
            pSum[y][x] = pSum[y-1][x] + pSum[y][x-1] - pSum[y-1][x-1] + board[y][x]

def check(y,x):
    sy, sx = y,x
    ey, ex = y + H - 1, x + W - 1

    value = pSum[ey][ex] - pSum[ey][sx - 1] - pSum[sy - 1][ex] + pSum[sy-1][sx-1]

    return value > 0


def bfs(y,x):
    Pair = namedtuple('Pair', ['y', 'x'])
    q = deque([Pair(y,x)])
    dist = [[-1 for _ in range(M+1)] for _ in range(N+1)]
    dist[y][x] = 0

    while q:
        cur = q.popleft()


        if cur.y == ey and cur.x == ex:
            return dist[cur.y][cur.x]

        for d in range(4):
            ny = cur.y + dy[d]
            nx = cur.x + dx[d]
            # print("ny, nx : ", ny, nx)

            if OOB(ny,nx) or dist[ny][nx] != -1 or check(ny,nx):
                continue

            dist[ny][nx] = dist[cur.y][cur.x] + 1
            q.append(Pair(ny,nx))

    return -1




if __name__ == "__main__":
    N,M,H,W,K = map(int,input().split())
    board = [[0 for _ in range(M+1)] for _ in range(N+1)]

    for _ in range(K):
        y,x = map(int,input().split())
        board[y][x] = 1

    pSum =[[0 for _ in range(M+1)] for _ in range(N+1)]
    init()

    sy,sx = map(int,input().split())
    ey,ex = map(int,input().split())
    # printBoard(pSum)
    print(bfs(sy,sx))