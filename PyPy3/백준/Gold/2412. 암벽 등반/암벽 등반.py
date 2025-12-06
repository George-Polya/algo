from collections import deque, namedtuple, defaultdict

def inRange(ny,nx, cur_y, cur_x):
    return


def bfs():
    Pair = namedtuple('Pair', ['y','x'])
    dist = defaultdict(int)
    start = Pair(0,0)
    q = deque([start])
    dist[start] = 0

    while q:
        cur = q.popleft()
        if cur.y == T:
            return dist[(cur.y, cur.x)]

        for ny in range(cur.y-2, cur.y+3):

            for nx in adj[ny]:
                if cur.x -2 <= nx and nx <= cur.x + 2:
                    # print(ny,nx)
                    if (ny,nx) in dist:
                        continue
                    q.append(Pair(ny,nx))
                    dist[(ny,nx)] = dist[cur] + 1
    return -1



if __name__ == "__main__":
    N,T = map(int,input().split())
    adj = defaultdict(list)

    for _ in range(N):
        x,y = map(int,input().split())

        adj[y].append(x)

    print(bfs())