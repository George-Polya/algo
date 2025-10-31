
def dfs(cur, depth):
    if depth == 4:
        return True

    for nxt in adj[cur]:
        if visited[nxt]:
            continue
        visited[nxt] = True
        if dfs(nxt, depth + 1):
            return True
        visited[nxt] = False

    return False

def possible():
    for node in range(N):
        visited[node] = True
        if dfs(node, 0):
            return True
        visited[node] = False
    return False

if __name__ == "__main__":
    N, M = map(int,input().split())
    adj = [[] for _ in range(N)]

    visited = [False for _ in range(N)]

    for i in range(M):
        a,b = map(int,input().split())
        adj[a].append(b)
        adj[b].append(a)

    print(1 if possible() else 0)