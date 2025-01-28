from heapq import heappop, heappush
# from collections import namedtuple
# from typing import *

class State:
    def __init__(self, idx, cost):
        self.idx = idx
        self.cost = cost

    def __lt__(self, o):
        return self.cost < o.cost

if __name__ == '__main__':
    N = int(input())
    A, B, C = map(int,input().split())
    INF = int(1e10)
    adj = [[] for _ in range(N+1)]
    dist = [ INF for _ in range(N+1)]
    # Edge = namedtuple('Edge', ('idx', 'cost'))
    M = int(input())
    for _ in range(M):
        u,v,cost = map(int,input().split())
        adj[u].append((v,cost))
        adj[v].append((u, cost))


    dist[A] = dist[B] = dist[C] = 0
    pq = []
    heappush(pq, State(A, 0))
    heappush(pq, State(B, 0))
    heappush(pq, State(C, 0))

    while pq:
        cur = heappop(pq)

        if(dist[cur.idx] < cur.cost):
            continue

        for nxt in adj[cur.idx]:

            if dist[nxt[0]] > dist[cur.idx] + nxt[1]:
                dist[nxt[0]] = dist[cur.idx] + nxt[1]
                heappush(pq, State(nxt[0], dist[nxt[0]]))

    _max = max(dist[1:])

    ans = 0
    for i in range(1, N+1):
        if _max == dist[i]:
            ans = i
    print(ans)