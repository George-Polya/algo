from heapq import heappush, heappop
from dataclasses import dataclass

@dataclass
class Edge:
    idx :int
    cost :int

    def __lt__(self, other):
        return self.cost < other.cost


if __name__ == "__main__":
    T = int(input())
    sb = []
    INF = float('inf')
    for t in range(1,T+1):
       ans = f"Case #{t}: "

       N,M = map(int,input().split())
       dist = []
       trace = []
       adj = []

       for i in range(M):
           dist.append(INF)
           trace.append(-1)
           adj.append([])

       for i in range(N):
            u,v,cost = map(int,input().split())
            adj[u].append(Edge(v, cost))
            adj[v].append(Edge(u, cost))

       pq = []
       dist[0] = 0
       heappush(pq, Edge(0,0))

       while pq:
           cur = heappop(pq)

           if dist[cur.idx] < cur.cost:
               continue

           for nxt in adj[cur.idx]:
                if dist[nxt.idx] > dist[cur.idx] + nxt.cost:
                    dist[nxt.idx] = dist[cur.idx] + nxt.cost
                    trace[nxt.idx] = cur.idx
                    heappush(pq, Edge(nxt.idx, dist[nxt.idx]))

       if dist[M-1] == INF:
           ans += '-1'
           sb.append(ans)
       else:
           stk = []
           cur = M - 1
           while cur != 0:
               stk.append(cur)
               cur = trace[cur]
           stk.append(cur)

           while stk:
               ans += f"{stk.pop()} "
           sb.append(ans)

    print('\n'.join(sb))