from collections import deque

def bfs(start):
    colors[i] = 1
    q = deque([start])

    while q:
        cur = q.popleft()

        for nxt in adj[cur]:
            if colors[nxt] == 0:
                colors[nxt] = -colors[cur]
                q.append(nxt)
            elif colors[nxt] == colors[cur]:
                return False

    return True





if __name__ == '__main__':
    N,M = map(int,input().split())
    adj = [[] for _ in range(N+1)]

    for _ in range(M):
        u,v = map(int,input().split())
        adj[u].append(v)
        adj[v].append(u)

    colors = [0 for _ in range(N+1)]



    for i in range(1,N+1):
        # print(f"{i}")
        if colors[i] == 0:
            if not bfs(i):
                print(0)
                exit()
    print(1)