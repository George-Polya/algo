from collections import namedtuple
from heapq import heappush, heappop

class State:
    def __init__(self,y,x,d,cnt):
        self.y = y
        self.x = x
        self.d = d
        self.cnt = cnt

    def __lt__(self, o):
        return self.cnt < o.cnt

dy = [-1,1,0,0]
dx = [0,0,-1,1]
def OOB(y,x):
    return y<0 or y>= N or x<0 or x>=N

def getNxtDirs(d):
    if d == 0 or d == 1:
        return [2,3]
    return [0,1]

if __name__ == "__main__":
    N = int(input())

    board = [[] for _ in range(N)]
    start = end = None
    for y in range(N):
        line = input()
        for x in range(N):
            board[y].append(line[x])
            if board[y][x] == '#':
                if start == None:
                    start = (y,x)
                else:
                    end = (y,x)



    pq = []
    visited = [ [[False for _ in range(N)] for _ in range(N)]for _ in range(4)]
    for d in range(4):
        heappush(pq, State(start[0], start[1], d, 0))

    while pq:
        cur : State = heappop(pq)
        visited[cur.d][cur.y][cur.x] = True

        if cur.y == end[0] and cur.x == end[1]:
            print(cur.cnt)
            exit(0)

        if board[cur.y][cur.x] == '.' or board[cur.y][cur.x] == '#':
            ny = cur.y + dy[cur.d]
            nx = cur.x + dx[cur.d]
            if not OOB(ny,nx) and board[ny][nx] != '*' and not visited[cur.d][ny][nx]:
                heappush(pq, State(ny,nx,cur.d, cur.cnt))
        elif board[cur.y][cur.x] == '!':
            ny = cur.y + dy[cur.d]
            nx = cur.x + dx[cur.d]

            if not OOB(ny,nx) and board[ny][nx] != '*' and not visited[cur.d][ny][nx]:
                heappush(pq, State(ny,nx,cur.d, cur.cnt))

            for nd in getNxtDirs(cur.d):
                ny = cur.y + dy[nd]
                nx = cur.x + dx[nd]
                if not OOB(ny,nx) and board[ny][nx] != '*' and not visited[nd][ny][nx]:
                    heappush(pq, State(ny,nx,nd,cur.cnt + 1))
