from dataclasses import dataclass
from pprint import pprint
from heapq import heappush, heappop

@dataclass
class Edge:
    id : int
    cost : int

    def __lt__(self, o):
        return self.cost < o.cost

if __name__ == "__main__":
    N = int(input())
    adj = [[] for _ in range(N+1)]

    for y in range(1,N+1):
        arr = list(map(int,input().split()))
        for x in range(1,N+1):
            cost = arr[x - 1]
            if cost != 0:
                adj[y].append(Edge(x, cost))



    visited = [False for _ in range(N+1)]
    pq = []
    heappush(pq, Edge(1,0))

    ans = 0
    cnt = 0
    while pq:
        cur = heappop(pq)


        if visited[cur.id]:
            continue

        ans += cur.cost
        cnt += 1
        visited[cur.id] = True
        if cnt == N:
            break

        for nxt in adj[cur.id]:
            if visited[nxt.id]:
                continue
            heappush(pq, nxt)
    print(ans)







