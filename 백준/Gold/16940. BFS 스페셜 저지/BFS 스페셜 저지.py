from collections import deque

def check():
    for i in range(1,N+1):
        if sequences[i] != result[i-1]:
            return False

    return True

if __name__ == "__main__":
    N = int(input())
    adj = [[] for _ in range(N+1)]

    for _ in range(N-1):
        u, v = map(int,input().split())
        adj[u].append(v)
        adj[v].append(u)

    sequences = [0] + list(map(int,input().split()))
    order = [ 0 for _ in range(N+1)]
    for i in range(1,N+1):
        order[sequences[i]] = i

    for i in range(1,N+1):
        adj[i].sort(key = lambda idx : order[idx])


    visited = [False for _ in range(N+1)]
    result = []

    q = deque([1])
    visited[1] = True
    result.append(1)
    while q:
        cur = q.popleft()

        for nxt in adj[cur]:
            if visited[nxt]:
                continue

            visited[nxt] = True
            result.append(nxt)
            q.append(nxt)
    print( 1 if check() else 0)
