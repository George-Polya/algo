from collections import deque

class Graph:
    def __init__(self, n):
        self.n = n
        self.adj = [[] for _ in range(n+1)]

    def link(self, u, v):
        self.adj[u].append(v)


    def bfs(self, x):
        q = deque([x])
        visited = [False for _ in range(self.n+1)]
        visited[x] = True
        ret = 0

        while q:
            cur = q.popleft()

            for nxt in self.adj[cur]:
                if visited[nxt]:
                    continue
                visited[nxt] = True
                q.append(nxt)
                ret += 1

        return ret


if __name__ == "__main__":
    N,M,X = map(int,input().split())


    g1 = Graph(N)
    g2 = Graph(N)

    for _ in range(M):
        u,v = map(int,input().split())

        g1.link(u, v)
        g2.link(v, u)

    print((g2.bfs(X) + 1), (N - g1.bfs(X)))