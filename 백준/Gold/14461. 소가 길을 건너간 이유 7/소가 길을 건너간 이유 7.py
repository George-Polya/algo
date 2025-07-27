from dataclasses import dataclass
from heapq import heappush, heappop

@dataclass
class State:
    y: int
    x: int
    cnt: int
    time: int

    def __lt__(self, o):
        return self.time < o.time

dy = [-1,1,0,0]
dx = [0,0,-1,1]

def OOB(y, x):
    return y<= 0 or y > N or x<=0 or x>N

if __name__ == "__main__":
    N,T = map(int,input().split())
    board = [[] for _ in range(N+1)]
    INF = float('inf')
    dist = [[[INF for _ in range(N+1)] for _ in range(N+1)] for _ in range(3)]

    for y in range(1,N+1):
        board[y] = [0] + list(map(int,input().split()))

    pq = []
    heappush(pq,State(1,1,0,0))
    dist[0][1][1] = 0

    while pq:
        cur = heappop(pq)

        if dist[cur.cnt][cur.y][cur.x] < cur.time:
            continue

        for d in range(4):
            ny = cur.y + dy[d]
            nx = cur.x + dx[d]

            if OOB(ny,nx):
                continue

            temp = cur.time + T
            if cur.cnt == 2:
                temp += board[ny][nx]

            nxt = (cur.cnt + 1) % 3
            if(dist[nxt][ny][nx] > temp):
                dist[nxt][ny][nx] = temp
                heappush(pq, State(ny,nx,nxt,temp))


    ans = INF
    for i in range(3):
        ans = min(ans, dist[i][N][N])

    print(ans)
