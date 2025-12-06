from collections import deque, namedtuple, defaultdict

def printBoard(board):
    for y in range(N):
        for x in range(M):
            print(f"{board[y][x]}",end='')
        print()

dy = [-1,1,0,0]
dx = [0,0,-1,1]

def OOB(y, x):
    return y<0 or y>=N or x<0 or x>=M

def bfs(y,x, id):
    global visited

    q = deque([Pair(y,x)])
    visited[y][x] = id
    cnt = 0
    while q:
        cur = q.popleft()
        cnt += 1
        for d in range(4):
            ny = cur.y + dy[d]
            nx = cur.x + dx[d]

            if OOB(ny,nx) or visited[ny][nx] != 0 or board[ny][nx] == 1:
                continue

            q.append(Pair(ny,nx))
            visited[ny][nx] = id

    return cnt


if __name__ == "__main__":
    N,M = map(int,input().split())
    board = [[] for _ in range(N)]
    for y in range(N):
        line = input()
        for x in range(M):
            board[y].append(int(line[x]))
    Pair = namedtuple('Pair', ['y', 'x'])
    visited = [[0 for _ in range(M)] for _ in range(N)]

    id = 1
    counts = defaultdict(int)
    for y in range(N):
        for x in range(M):
            if visited[y][x] != 0 or board[y][x] == 1:
                continue
            cnt = bfs(y,x,id)
            counts[id] = cnt
            id+=1

    cBoard = [[0 for _ in range(M)] for _ in range(N)]
    for y in range(N):
        for x in range(M):
            if board[y][x] == 0:
                continue
            cnt = 1
            s = set()
            for d in range(4):
                ny = y + dy[d]
                nx = x + dx[d]
                if OOB(ny,nx) or board[ny][nx] == 1:
                    continue

                id = visited[ny][nx]
                if id not in s:
                    s.add(id)
                    cnt += counts[id]
            cBoard[y][x] = cnt % 10

    printBoard(cBoard)



