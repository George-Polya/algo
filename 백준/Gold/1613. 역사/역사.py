
def solve():
    print(N)

if __name__ == '__main__':
    N, K = map(int, input().split())
    INF = int(1e10)

    dist = [ [INF if y != x else 0 for x in range(N+1)] for y in range(N+1)]

    for _ in range(K):
        u,v = map(int, input().split())
        dist[u][v] = 1

    for k in range(1,N+1):
        for a in range(1,N+1):
            for b in range(1,N+1):
                dist[a][b] = min(dist[a][b], dist[a][k] + dist[k][b])

    Q = int(input())
    ans = []
    for _ in range(Q):
        u, v = map(int, input().split())

        first = dist[u][v]
        second = dist[v][u]
        if first == INF and second == INF:
            ans.append("0")
        elif first != INF:
            ans.append("-1")
        else:
            ans.append("1")

    print('\n'.join(ans))