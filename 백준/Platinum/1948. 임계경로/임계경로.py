from collections import deque, namedtuple,defaultdict


if __name__ == '__main__':
    N = int(input())
    M = int(input())

    adj = [[] for _ in range(N+1)]
    rAdj = [[] for _ in range(N+1)]

    hm = defaultdict(int)

    idx = 1
    Edge = namedtuple('Edge', ['idx', 'cost'])
    inDegree = [ 0 for _ in range(N+1)]

    for _ in range(M):
        u,v,cost = map(int,input().split())
        if u not in hm:
            hm[u] = idx
            idx+=1

        if v not in hm:
            hm[v] = idx
            idx+=1

        u = hm[u]
        v = hm[v]

        adj[u].append(Edge(v, cost))
        inDegree[v] += 1
        rAdj[v].append(Edge(u, cost))

    s, e = map(int,input().split())
    s = hm[s]
    e = hm[e]
    # print(inDegree)
    # for i in range(1,N+1):
    #     print(f"{i} : {adj[i]}")

    dist = [-1 for _ in range(N+1)]
    q = deque()
    q.append(s)
    dist[s] = 0

    while q:
        cur = q.popleft()

        for nxt in adj[cur]:
            inDegree[nxt.idx] -= 1
            dist[nxt.idx] = max(dist[nxt.idx], dist[cur] + nxt.cost)
            if inDegree[nxt.idx] == 0:
                q.append(nxt.idx)



    # print(dist)
    visited = [False for _ in range(N+1)]
    q = deque()
    q.append(Edge(e, dist[e]))
    visited[e] = True
    cnt = 0
    hs = set()
    while q:
        cur = q.popleft()

        for nxt in rAdj[cur.idx]:


            if cur.cost - nxt.cost == dist[nxt.idx]:
                # print(f"cur: {cur.idx}, nxt: {nxt.idx}")
                hs.add((cur.idx, nxt.idx))
                if visited[nxt.idx]:
                    continue
                visited[nxt.idx] = True
                q.append(Edge(nxt.idx, cur.cost - nxt.cost))

    print(dist[e],len(hs),sep='\n')