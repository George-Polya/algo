from collections import defaultdict, namedtuple
from heapq import heappush, heappop

class State:
    def __init__(self, idx, cost):
        self.idx = idx
        self.cost = cost

    def __lt__(self, o):
        return self.cost < o.cost

    def __repr__(self):
        return f"{self.idx} {self.cost}"


if __name__ == '__main__':
    N, M, T = map(int,input().split())
    adj = [[] for _ in range(N+1)]
    Edge = namedtuple('Edge', ['idx', 'cost'])

    for _ in range(M):
        u,v,cost = map(int,input().split())
        adj[u].append(Edge(v,cost))
        adj[v].append(Edge(u,cost))

    pq = []
    visited = [False for _ in range(N+1)]
    heappush(pq, State(1, 0))
    cnt = 0
    sum = 0
    while pq:
        cur = heappop(pq)

        if visited[cur.idx]:
            continue

        cnt+=1
        visited[cur.idx] = True
        sum += cur.cost
            
        if cnt > 1:
            sum = sum + (cnt - 2) * T
        if cnt == N:
            break

        for nxt in adj[cur.idx]:
            if visited[nxt.idx]:
                continue

            heappush(pq, State(nxt.idx, nxt.cost))

    print(sum)