import heapq
from collections import namedtuple
INF = int(1e7)
def decide(cost):
    pq = []
    dist = [INF for _ in range(N+1)]
    heapq.heappush(pq, Edge(1, 0))
    dist[1] = 0

    while pq:
        # print("pq: ",pq)
        cur = heapq.heappop(pq)

        if dist[cur.idx] < cur.cost:
            continue

        for nxt in adj[cur.idx]:
            temp = dist[cur.idx] + (1 if nxt.cost > cost else 0)
            if dist[nxt.idx] > temp:
                dist[nxt.idx] = temp
                heapq.heappush(pq,Edge(nxt.idx, dist[nxt.idx]))

    # print(dist)
    return dist[N] <= K



class Edge:
    def __init__(self, idx, cost):
        self.idx = idx
        self.cost = cost

    def __lt__(self, o):
        return self.cost < o.cost
    def __repr__(self):
        return f"idx: {self.idx} cost: {self.cost}"


if __name__ == '__main__':
    N,P,K = map(int, input().split())
    adj = [[] for _ in range(N+1)]

    for _ in range(P):
        a, b, cost = map(int, input().split())
        adj[a].append(Edge(b, cost))
        adj[b].append(Edge(a, cost))

    l = 0
    r = int(1e6)
    ret = -1
    # print(decide(4))
    while(l<=r):
        mid = (l+r) // 2

        if(decide(mid)):
            r = mid - 1
            ret = mid
        else:
            l = mid + 1

    print(ret)