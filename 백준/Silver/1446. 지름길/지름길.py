from collections import namedtuple
from heapq import heappush, heappop

def OOB(x):
    return x<0 or x>MAX_D

class State:
    def __init__(self, idx, cost):
        self.idx = idx
        self.cost = cost

    def __lt__(self, o):
        return cost < o.cost

    def __repr__(self):
        return f"{self.idx} {self.cost}"


if __name__ == '__main__':
    N,D = map(int, input().split())
    MAX_D = 10000
    adj = [ [] for _ in range(MAX_D+1)]
    Edge = namedtuple('Edge', ['idx', 'cost'])
    INF = int(1e10)
    for cur in range(MAX_D+1):
        for dir in [1]:
            nxt = cur + dir
            if OOB(nxt):
                continue
            adj[cur].append(Edge(nxt, 1))



    for _ in range(N):
        s, e, cost = map(int, input().split())
        adj[s].append(Edge(e, cost))

    dist = [INF for _ in range(MAX_D+1)]

    pq = []
    dist[0] = 0
    heappush(pq, State(0, 0))

    while pq:
        # print("pq: ", pq)
        cur = heappop(pq)

        if dist[cur.idx] < cur.cost:
            continue

        for nxt in adj[cur.idx]:
            # print(nxt)
            if dist[nxt.idx] > dist[cur.idx] + nxt.cost:
                dist[nxt.idx] = dist[cur.idx] + nxt.cost
                heappush(pq, State(nxt.idx, dist[nxt.idx]))


    print(dist[D])