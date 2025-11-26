from collections import deque, defaultdict, namedtuple


def possible(cur, nxt):
    return abs(cur.x - nxt.x) <= 2 and abs(cur.y - nxt.y) <= 2

if __name__ == "__main__":
    N, T = map(int,input().split())

    adj = defaultdict(list)
    dist = defaultdict(int)
    for _ in range(N):
        x,y = map(int,input().split())
        adj[y].append(x)

    for y in adj:
        adj[y].sort()

    Pair = namedtuple('Pair', ['x', 'y'])

    dist[(0,0)] = 0
    q = deque([Pair(0,0)])

    while q:
        cur : Pair = q.popleft()
        if cur.y == T:
            print(dist[(cur.x, cur.y)])
            exit(0)

        for ny in range(cur.y - 2, cur.y + 3):
            if ny < 0 or ny > T:
                continue

            for nx in adj[ny]:
                if abs(nx - cur.x) <= 2 and (nx,ny) not in dist:
                    dist[(nx, ny)] = dist[(cur.x, cur.y)] + 1
                    q.append(Pair(nx, ny))
    print(-1)

