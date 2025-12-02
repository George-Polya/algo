from heapq import heappush, heappop
from dataclasses import dataclass

@dataclass
class Edge:
    idx: int
    cost: int

    def __lt__(self, other):
        return self.cost < other.cost


def dijkstra(start):
    dist = [INF] * (V+1)
    dist[start] = 0
    pq = []
    heappush(pq, Edge(start,0))

    while pq:
        cur = heappop(pq)

        if dist[cur.idx] < cur.cost:
            continue

        for nxt in adj[cur.idx]:
            if dist[nxt.idx] > dist[cur.idx] + nxt.cost:
                dist[nxt.idx] = dist[cur.idx] + nxt.cost
                heappush(pq, Edge(nxt.idx, dist[nxt.idx]))


    return dist


if __name__ == "__main__":
    V,M = map(int,input().split())
    adj= [[] for _ in range(V+1)]
    INF = int(1e9)
    for _ in range(M):
        a,b,cost = map(int,input().split())
        adj[a].append(Edge(b,cost))
        adj[b].append(Edge(a,cost))

    start1, start2 = map(int,input().split())
    dist1 = dijkstra(start1)
    dist2 = dijkstra(start2)

    mn = INF

    for i in range(1,V+1):
        if i == start1 or i == start2:
            continue
        mn = min(mn, dist1[i] + dist2[i])


    candidates = []

    for i in range(1,V+1):
        if i == start1 or i == start2:
            continue

        sm = dist1[i] + dist2[i]

        if sm == mn and dist1[i] <= dist2[i]:
            candidates.append(Edge(i, dist1[i]))


    if not candidates:
        print(-1)
    else:
        candidates.sort()
        print(candidates[0].idx)