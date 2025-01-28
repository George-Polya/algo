from collections import deque, namedtuple


def printBoard(board):
    for y in range(1,N+1):
        for x in range(1, M+1):
            print("%3d" % board[y][x], end='')
        print()

dy = [-1,-1,0,1,1,1,0,-1]
dx = [0,1,1,1,0,-1,-1,-1]

def OOB(y, x):
    return y<=0 or y>N or x<=0 or x>M

if __name__ == '__main__':
    N,M = map(int, input().split())
    board = [[0 for _ in range(M+1)] for _ in range(N+1) ]
    dist = [[-1 for _ in range(M+1)] for _ in range(N+1) ]

    q = deque()
    Pair = namedtuple('Pair', ['y', 'x'])

    for y in range(1, N+1):
        board[y] = [0] + list(map(int, input().split()))
        for x in range(1, M+1):
            if board[y][x] == 1:
                q.append(Pair(y,x))
                dist[y][x] = 0

    ans = -1
    while q:
        cur = q.popleft()
        # print(dist[cur.y][cur.x])
        ans = max(ans, dist[cur.y][cur.x])
        for dir in range(8):
            ny = cur.y + dy[dir]
            nx = cur.x + dx[dir]

            if OOB(ny,nx) or dist[ny][nx] != -1:
                continue
            dist[ny][nx] = dist[cur.y][cur.x] + 1
            q.append(Pair(ny,nx))


    print(ans)