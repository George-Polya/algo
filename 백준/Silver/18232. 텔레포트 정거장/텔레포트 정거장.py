from collections import deque

N,M = map(int, input().split())

adj = [[] for _ in range(N+1)]

S, E = map(int,input().split())

for i in range(M):
    x, y = map(int, input().split())

    adj[x].append(y)
    adj[y].append(x)
dist = [-1 for _ in range(N+1)]

q = deque()
dist[S] = 0
q.append(S)
def OOB(x : int):
    return x<= 0 or x> N

while q:
    # print(f"q: {q}, dist: {dist}")
    cur = q.popleft()

    for dir in [-1,1]:
        nxt = cur + dir
        if OOB(nxt) or dist[nxt] != -1:
            continue

        dist[nxt] = dist[cur] + 1
        q.append(nxt)

    for nxt in adj[cur]:
        if OOB(nxt) or dist[nxt] != -1:
            continue

        dist[nxt] = dist[cur] + 1
        q.append(nxt)
print(dist[E])