from collections import deque, namedtuple

def printBoard(board):
    for y in range(size):
        for x in range(size):
            if isinstance(board[y][x],str):
                print(f"{board[y][x]:3s}",end=' ')
            elif isinstance(board[y][x], int):
                print(f"{board[y][x]:3d}", end=' ')
        print()

def rotateAll(board):

    ret = [['.' for _ in range(size)] for _ in range(size)]

    half = size // 2
    for sy in range(0,size, half):
        for sx in range(0, size, half):
            ey,ex = sy + half - 1, sx + half - 1
            for y in range(sy, ey+1):
                for x in range(sx, ex+1):
                    y1, x1 = y - sy, x - sx
                    y2, x2 = x1, half - y1 - 1
                    ny, nx = y2 + sy, x2 + sx
                    ret[ny][nx] = board[y][x]

    return ret

dy = [0,1,-1,0,0]
dx = [0,0,0,1,-1]

def OOB(y,x):
    return y< 0 or y >= size or x< 0 or x >= size




def getDivision(y, x):
    if OOB(y,x):
        return -1
    return (y // 4) * 4 + (x // 4)


def getRotatePos(y, x):
    baseY, baseX = (y // 4) * 4, (x // 4) * 4

    y %= 4
    x %= 4

    return baseY + x, baseX + 3 - y


if __name__ == '__main__':
    N = int(input())
    size = 4 * N
    board = [[['.' for _ in range(size)] for _ in range(size)] for _ in range(4)]
    INF = float('inf')
    visited = [[[False for _ in range(size)] for _ in range(size)] for _ in range(4)]

    sy = sx = ey = ex = -1
    for y in range(size):
        line = input()
        for x in range(size):
            board[0][y][x] = line[x]
            if board[0][y][x] == 'S':
                sy,sx = y,x

            tmpY = y
            tmpX = x

            for rDir in range(1,4):
                tmpY, tmpX = getRotatePos(tmpY, tmpX)
                board[rDir][tmpY][tmpX] = board[0][y][x]

    # for rDir in range(1, 4):
    #     board[rDir] = rotateAll(board[rDir - 1])

    # printBoard(board[2])

    Tuple = namedtuple('Tuple', ['y','x','bDir', 'dist'])
    visited[0][sy][sx] = True
    q = deque([Tuple(sy,sx,0, 0)])

    while q:
        cur = q.popleft()

        if board[cur.bDir][cur.y][cur.x] == 'E':
            print(cur.dist)
            exit()

        cDiv = getDivision(cur.y, cur.x)
        for moveDir in range(5):
            ny = cur.y + dy[moveDir]
            nx = cur.x + dx[moveDir]
            nDiv = getDivision(ny, nx)

            if nDiv == -1:
                continue

            nxtbDir = (cur.bDir + 1) % 4 if cDiv == nDiv else 1

            ny, nx = getRotatePos(ny, nx)
            # print(nxtbDir, ny,nx)
            if board[nxtbDir][ny][nx] == '#' or visited[nxtbDir][ny][nx]:
                continue

            q.append(Tuple(ny,nx,nxtbDir, cur.dist+1))
            visited[nxtbDir][ny][nx] = True

    print(-1)