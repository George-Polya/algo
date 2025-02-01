def dfs(cur, cnt, sum):
    if cnt == 2:
        coins.append(sum)
        return

    if cur == M:
        return


    dfs(cur+1,cnt+1, sum + arr[cur])


    dfs(cur+1, cnt, sum)



if __name__ == '__main__':
    N,M = map(int,input().split())

    arr = list(map(int,input().split()))
    coins = [ num for num in arr]

    INF = int(1e10)
    dp = [INF for _ in range(N+1)]


    if M >= 2:
        dfs(0, 0, 0)

    # print(dp)
    dp[0] = 0
    for coin in coins:
        for s in range(coin, N+1):
            dp[s] = min(dp[s], dp[s-coin] + 1)

    
    print(dp[N] if dp[N] != INF else -1)