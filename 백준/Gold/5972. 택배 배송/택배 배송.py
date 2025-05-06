from heapq import heappush, heappop

class Node:
    def __init__(self, idx, cost):
        self.idx = idx
        self.cost = cost

    def __lt__(self, other):
        return self.cost < other.cost

    def __repr__(self):
        return f"({self.idx}, {self.cost})"

if __name__ == '__main__':
    N,M = map(int,input().split())
    INF = float('inf')
    adj = [[] for _ in range(N+1)]
    dist = [INF for _ in range(N+1)]

    for _ in range(M):
        u,v,cost = map(int,input().split())
        adj[u].append(Node(v,cost))
        adj[v].append(Node(u,cost))

    pq = []
    dist[1] = 0
    heappush(pq, Node(1, 0))

    while pq:
        cur = heappop(pq)

        if dist[cur.idx] < cur.cost:
            continue

        for nxt in adj[cur.idx]:
            if dist[nxt.idx] > dist[cur.idx] + nxt.cost:
                dist[nxt.idx] = dist[cur.idx] + nxt.cost
                heappush(pq, Node(nxt.idx, dist[nxt.idx]))

    print(dist[N])