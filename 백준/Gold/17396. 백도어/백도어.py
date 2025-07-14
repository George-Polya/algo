from heapq import heappush, heappop
from dataclasses import dataclass

@dataclass
class Edge:
    id: int
    cost : int

    def __lt__(self, other):
        return self.cost < other.cost


if __name__ == "__main__":
    N, M = map(int,input().split())
    paths = list(map(int,input().split()))
    INF = float('inf')
    dist = [INF for _ in range(N)]

    adj = [[] for _ in range(N)]

    for _ in range(M):
        u, v, cost = map(int,input().split())
        adj[u].append(Edge(v, cost))
        adj[v].append(Edge(u, cost))


    dist[0] = 0
    pq = []
    heappush(pq, Edge(0, dist[0]))

    while pq:
        cur : Edge = heappop(pq)

        if dist[cur.id] < cur.cost:
            continue

        for nxt in adj[cur.id]:
            if paths[nxt.id] == 1 and nxt.id != (N-1):
                continue

            if dist[nxt.id] > dist[cur.id] + nxt.cost:
                dist[nxt.id] = dist[cur.id] + nxt.cost
                heappush(pq, Edge(nxt.id, dist[nxt.id]))

    print(dist[N-1] if dist[N-1] != INF else -1)

