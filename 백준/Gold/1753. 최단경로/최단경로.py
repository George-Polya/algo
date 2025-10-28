from heapq import heappush, heappop
from dataclasses import dataclass

@dataclass
class Edge:
    idx : int
    cost : int

    def __lt__(self, other):
        return self.cost < other.cost

if __name__ == "__main__":
    V,E = map(int,input().split())
    start = int(input())

    adj = [[] for _ in range(V + 1)]
    INF = float('inf')
    dist = [INF for _ in range(V+1)]


    for _ in range(E):
        u,v,w = map(int,input().split())
        adj[u].append(Edge(v, w))

    dist[start] = 0
    pq = [Edge(start, dist[start])]

    while pq:
        cur = heappop(pq)

        if dist[cur.idx] < cur.cost:
            continue

        for nxt in adj[cur.idx]:
            if dist[nxt.idx] > dist[cur.idx] + nxt.cost:
                dist[nxt.idx] = dist[cur.idx] + nxt.cost
                heappush(pq, Edge(nxt.idx, dist[nxt.idx]))

    ans = []
    for i in range(1,V+1):
        if dist[i] != INF:
            ans.append(str(dist[i]))
        else:
            ans.append("INF")

    print('\n'.join(ans))
