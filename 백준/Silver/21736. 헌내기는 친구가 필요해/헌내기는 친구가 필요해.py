from collections import deque, namedtuple

dy = [-1,1,0,0]
dx = [0,0,-1,1]
def OOB(y, x):
    return y<0 or y>=N or x<0 or x>=M

if __name__ == '__main__':
    N,M = map(int, input().split())

    board = [[] for _ in range(N)]
    sy = sx = -1
    for y in range(N):
        line = input()
        for x in range(M):
            board[y].append(line[x])
            if board[y][x] == 'I':
                sy = y
                sx = x

    ans = 0
    visited = [[False for _ in range(M)] for _ in range(N)]

    Pair = namedtuple('Pair', ['y','x'])
    q = deque([Pair(sy,sx)])
    visited[sy][sx] = True

    while q:
        cur = q.popleft()
        if board[cur.y][cur.x] == 'P':
            ans += 1

        for d in range(4):
            ny = cur.y + dy[d]
            nx = cur.x + dx[d]

            if OOB(ny,nx) or visited[ny][nx] or board[ny][nx] == 'X':
                continue
            visited[ny][nx] = True
            q.append(Pair(ny,nx))

    print('TT' if ans == 0 else ans)