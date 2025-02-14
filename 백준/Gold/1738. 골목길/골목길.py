from collections import namedtuple, deque

from pprint import pprint

def canUpdate(e):
    return dist[e.v] >= dist[e.u] + e.cost

def canReach(start, end):
    q = deque([start])
    visited = [False] * (N+1)
    visited[start] = True

    while q:
        cur = q.popleft()

        if cur == end:
            return True

        for e in edges:
            if e.u == cur and not visited[e.v]:
                visited[e.v] = True
                q.append(e.v)

    return False

if __name__ == '__main__':
    N,M = map(int,input().split())
    Edge = namedtuple('Edge', ['u', 'v', 'cost'])
    edges = []
    INF = -float('inf')
    for i in range(M):
        _from, _to, _cost = map(int,input().split())
        edges.append(Edge(_from,_to,_cost))


    dist = [INF] * (N+1)

    dist[1] = 0
    prev = [0] * (N+1)
    for i in range(N):
        for edge in edges:
            if dist[edge.u] == INF:
                continue

            if dist[edge.v] < dist[edge.u] + edge.cost:
                dist[edge.v] = dist[edge.u] + edge.cost
                prev[edge.v] = edge.u

    # pprint(dist)
    # pprint(prev)
    for edge in edges:
        if dist[edge.u] == INF or canUpdate(edge):
            continue

        if canReach(1, edge.u) and canReach(edge.u, N):
            # print(edge)
            print(-1)
            exit()


    ans = []
    cur = N
    while cur != 0:
        ans.append(cur)
        cur = prev[cur]
    print(' '.join(map(str, ans[::-1])))