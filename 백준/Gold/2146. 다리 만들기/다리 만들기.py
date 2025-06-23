from collections import deque, namedtuple

dy = [0,1,0,-1]
dx = [1,0,-1,0]

def OOB(y, x):
    return y<0 or y>=N or x<0 or x>=N

def init():

    global visited
    idx = 1
    for y in range(N):
        for x in range(N):

            if visited[y][x] or board[y][x] == 0:
                continue
            q = deque([Pair(y,x)])
            visited[y][x] = True

            while q:
                cur = q.popleft()
                board[cur.y][cur.x] = idx

                for d in range(4):
                    ny = cur.y + dy[d]
                    nx = cur.x + dx[d]
                    if OOB(ny,nx) or board[ny][nx] == 0 or visited[ny][nx]:
                        continue

                    q.append(Pair(ny,nx))
                    visited[ny][nx] = True
            idx += 1


if __name__ == "__main__":
    N = int(input())
    board = [[] for _ in range(N)]
    for y in range(N):
        board[y] = list(map(int,input().split()))

    visited = [[False for _ in range(N)] for _ in range(N)]
    Pair = namedtuple('Pair', ['y', 'x'])
    init()
    dist = [[-1 for _ in range(N)] for _ in range(N)]
    q = deque()

    for y in range(N):
        for x in range(N):
            if board[y][x] != 0:
                dist[y][x] = 0
                q.append(Pair(y,x))

    ans = int(1e10)
    while q:
        cur = q.popleft()

        for d in range(4):
            ny = cur.y + dy[d]
            nx = cur.x + dx[d]

            if OOB(ny,nx) or board[ny][nx] == board[cur.y][cur.x]:
                continue

            if board[ny][nx] != 0:
                ans = min(ans, dist[ny][nx] + dist[cur.y][cur.x])
            else:
                board[ny][nx] = board[cur.y][cur.x]
                dist[ny][nx] = dist[cur.y][cur.x] + 1
                q.append(Pair(ny,nx))

    print(ans)