
def solve(cur, sum, bit):
    global ans
    if bit == (1 << N) - 1:
        ans = min(ans, sum)
        return

    for nxt in range(N):
        if (bit & (1 << nxt)) != 0:
            continue
        solve(nxt, sum + adj[cur][nxt], bit | (1 << nxt))

if __name__ == "__main__":
    N,K = map(int,input().split())
    adj = [None for _ in range(N)]
    ans = float('inf')

    for y in range(N):
        adj[y]=list(map(int,input().split()))


    for z in range(N):
        for y in range(N):
            for x in range(N):
                adj[y][x] = min(adj[y][x], adj[y][z] + adj[z][x])

    solve(K,0, 1<< K)
    print(ans)