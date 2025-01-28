from collections import deque, namedtuple

def OOB(x):
    return x <= 0 or x > N


if __name__ == '__main__':
    a,b = map(int, input().split())
    N, M = map(int, input().split())

    adj = [ [] for _ in range(N+1)]
    dist = [-1 for _ in range(N+1)]
    for _ in range(M):
        u, v = map(int,input().split())
        adj[u].append(v)
        adj[v].append(u)

    q = deque()
    q.append(a)
    dist[a] = 0

    while q:
        cur = q.popleft()

        for nxt in adj[cur]:
            if OOB(nxt) or dist[nxt] != -1:
                continue

            dist[nxt] = dist[cur] + 1
            q.append(nxt)

    print(dist[b])
