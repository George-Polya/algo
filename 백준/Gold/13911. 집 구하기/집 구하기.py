from heapq import heappop, heappush

INF = float('inf')
class Edge:
    def __init__(self, id, cost):
        self.id = id
        self.cost = cost

    def __lt__(self, other):
        return self.cost < other.cost

    def __repr__(self):
        return f"id: {self.id} cost: {self.cost}"

class Dijkstra:
    def __init__(self, starts : set):
        self.starts = starts
        self.dist = [INF for _ in range(V+1)]
        self.pq = []

        for start in starts:
            self.dist[start] = 0
            heappush(self.pq, Edge(start, 0))

        self.build()

    def build(self):
        while self.pq:
            cur: Edge = heappop(self.pq)

            if self.dist[cur.id] < cur.cost:
                continue

            for nxt in adj[cur.id]:
                if self.dist[nxt.id] > self.dist[cur.id] + nxt.cost:
                    self.dist[nxt.id] = self.dist[cur.id] + nxt.cost
                    heappush(self.pq, Edge(nxt.id, self.dist[nxt.id]))


    def find(self, limit):
        ret = set()
        for i in range(1, V+1):
            if self.dist[i] > limit or i in self.starts:
                continue
            ret.add(i)
        return ret

if __name__ == "__main__":

    V, E = map(int,input().split())
    adj = [[] for _ in range(V+1)]

    for _ in range(E):
        u,v,w = map(int,input().split())
        adj[u].append(Edge(v,w))
        adj[v].append(Edge(u,w))

    M, X = map(int,input().split())
    mcdonalds = set(list(map(int,input().split())))

    S, Y = map(int,input().split())
    starbucks = set(list(map(int, input().split())))

    mcDijkstra = Dijkstra(mcdonalds)
    starDijkstra = Dijkstra(starbucks)

    mcArea = mcDijkstra.find(X)
    starArea = starDijkstra.find(Y)

    ans = INF
    for i in range(1, V+1):
        if i in mcArea and i in starArea:
            total = mcDijkstra.dist[i] + starDijkstra.dist[i]
            ans = min(ans, total)
    print(ans if ans != INF else -1)