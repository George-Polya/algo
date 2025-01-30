from collections import defaultdict, deque


if __name__ == '__main__':
    N = int(input())

    adj = [[] for _ in range(N+1)]

    for _ in range(N-2):
        u,v = map(int,input().split())
        adj[u].append(v)
        adj[v].append(u)

    hs = set(range(2,N+1))
    # print(hs)

    q = deque()
    q.append(1)

    while q:
        cur = q.popleft()

        for nxt in adj[cur]:
            if nxt in hs:
                hs.remove(nxt)
                q.append(nxt)

    print(1, hs.pop())