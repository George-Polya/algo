from collections import deque


def bfs(start, adj):
    visited = [False] * (N+1)
    q = deque([start])
    visited[start] = True

    ret = 0

    while q:
        cur = q.popleft()

        for nxt in adj[cur]:
            if visited[nxt]:
                continue
            visited[nxt] = True
            ret += 1
            q.append(nxt)
    return ret

if __name__ == "__main__":
    N = int(input())
    M = int(input())

    adj = [[] for _ in range(N+1)]
    rev_adj = [[] for _ in range(N+1)]

    for _ in range(M):
        u,v = map(int,input().split())
        adj[u].append(v)
        rev_adj[v].append(u)

    ans = []
    for i in range(1,N+1):
        smaller = bfs(i, adj)
        bigger = bfs(i, rev_adj)

        ans.append(N - 1 - (smaller + bigger))

    print('\n'.join(map(str, ans)))
