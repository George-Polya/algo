from heapq import heappush, heappop

class Edge:
    def __init__(self, idx, cost):
        self.idx = idx
        self.cost = cost

    def __lt__(self, other):
        return self.cost < other.cost


def decide(cost):
    dist = [INF for _ in range(N+1)]
    maxCost = [0 for _ in range(N+1)]
    pq = []
    dist[S] = 0
    heappush(pq, Edge(S,0))

    while pq:
        cur = heappop(pq)

        if dist[cur.idx] < cur.cost:
            continue

        for nxt in adj[cur.idx]:
            if nxt.cost > cost:
                continue

            temp = dist[cur.idx] + nxt.cost
            if temp > COST:
                continue

            if dist[nxt.idx] > temp:
                dist[nxt.idx] = temp
                maxCost[nxt.idx] = max(maxCost[nxt.idx], nxt.cost)
                heappush(pq, Edge(nxt.idx, dist[nxt.idx]))

    return dist[E] <= COST and maxCost[E] <= cost;



if __name__ == "__main__":
    N,M,S,E,COST = map(int,input().split())
    INF = 1e14 + 9
    adj = [[] for _ in range(N+1)]
    l = 1
    r = -1
    for _ in range(M):
        u,v,cost = map(int, input().split())
        adj[u].append(Edge(v, cost))
        adj[v].append(Edge(u, cost))
        r = max(r, cost)

    ans = INF
    while l<=r :
        mid = (l + r) // 2

        if decide(mid):
            r = mid - 1
            ans = mid
        else:
            l = mid + 1

    print(ans if ans != INF else -1)