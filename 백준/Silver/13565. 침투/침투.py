from collections import deque, namedtuple

def OOB(y, x):
    return y<= 0 or y>N or x<=0 or x>M

dy = [-1,1,0,0]
dx = [0,0,-1,1]

def printBoard(board):
    N = len(board[1:])
    M = len(board[1][1:])
    for y in range(1,N+1):
        for x in range(1, M+1):
            print("%3d" % board[y][x], end='')
        print()

def check(board):

    for x in range(1,M+1):
        if(board[N][x] == 0 and visited[N][x]):
            return True
    return False


if __name__ == '__main__':
    N,M = map(int, input().split())
    board = [[0] for _ in range(N+1)]
    visited = [[False for _ in range(M+1)] for _ in range(N+1)]
    Pair = namedtuple('Pair', ('y' ,'x'))
    q = deque()
    for y in range(1,N+1):
        line = input()
        for x in range(1,M+1):
            board[y].append(ord(line[x-1]) - ord('0'))
            if board[y][x] == 0 and y == 1:
                visited[y][x] = True
                q.append(Pair(y,x))

    while q:
        cur = q.popleft()

        for dir in range(4):
            ny = cur.y + dy[dir]
            nx = cur.x + dx[dir]

            if OOB(ny,nx) or visited[ny][nx] or board[ny][nx] == 1:
                continue
            visited[ny][nx] = True
            q.append(Pair(ny,nx))
    # printBoard(board)


    print("YES" if check(board) else "NO")