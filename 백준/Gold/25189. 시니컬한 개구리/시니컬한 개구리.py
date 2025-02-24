from collections import deque, namedtuple
from pprint import pprint

def printBoard(board):
    for y in range(1,N+1):
        for x in range(1, M+1):
            print(f"{board[y][x]:3d}",end=' ')
        print()

dy = [-1,0,1,0]
dx = [0,-1,0,1]
def OOB(y, x):
    return y<= 0 or y > N or x<=0 or x>M

def getMaxDistance(y, x, dir):
    if dir == 0:
        return y - 1
    elif dir == 1:
        return x - 1
    elif dir == 2:
        return N - y
    return M - x

if __name__=='__main__':
    N, M = map(int, input().split())
    sy,sx,ey,ex = map(int,input().split())
    board = [[0 for _ in range(M+1)] for _ in range(N+1)]
    for y in range(1,N+1):
        line = list(map(int,input().split()))
        for x in range(1,M+1):
            board[y][x] = line[x-1]

    INT_MAX = float('inf')
    dist = [[[INT_MAX for _ in range(M+1)] for _ in range(N+1)] for _ in range(2)]

    State = namedtuple('State', ['y','x','limit'])
    q = deque([State(sy,sx,0)])
    dist[0][sy][sx] = 0
    y_visited = [False for _ in range(N+1)]
    x_visited = [False for _ in range(M+1)]

    while q:
        cur = q.popleft()
        if(cur.y == ey and cur.x == ex):
            print(dist[cur.limit][cur.y][cur.x])
            exit()

        # 점프 X
        distance = board[cur.y][cur.x]
        for dir in range(4):
            ny = cur.y + dy[dir] * distance
            nx = cur.x + dx[dir] * distance
            if OOB(ny, nx) or dist[cur.limit][ny][nx] != INT_MAX:
                continue
            dist[cur.limit][ny][nx] = dist[cur.limit][cur.y][cur.x] + 1
            q.append(State(ny, nx, cur.limit))

        if cur.limit == 0:
            if not y_visited[cur.y]:
                y_visited[cur.y] = True
                ny = cur.y
                for x in range(1,M+1):
                    nx = x
                    if dist[1][ny][nx] == INT_MAX:
                        dist[1][ny][nx] = dist[0][cur.y][cur.x] + 1
                        q.append(State(ny,nx,1))

            if not x_visited[cur.x]:
                x_visited[cur.x] = True
                nx = cur.x
                for y in range(1, N+1):
                    ny = y
                    if dist[1][ny][nx] == INT_MAX:
                        dist[1][ny][nx] = dist[0][cur.y][cur.x] + 1
                        q.append(State(ny,nx,1))
            # # 점프 O
            # for dir in range(4):
            #     maxDistance = getMaxDistance(cur.y, cur.x, dir)
            #     for v in range(1, maxDistance + 1):
            #         ny = cur.y + dy[dir] * v
            #         nx = cur.x + dx[dir] * v
            #         if OOB(ny,nx) or dist[1][ny][nx] != INT_MAX:
            #             continue
            #         dist[1][ny][nx] = dist[0][cur.y][cur.x] + 1
            #         q.append(State(ny,nx,1))

    print(-1)