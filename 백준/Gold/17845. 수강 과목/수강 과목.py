if __name__ == '__main__':
    N, K = map(int,input().split())
    importances = [0]
    times = [0]
    for _ in range(K):
        I, T = map(int,input().split())
        importances.append(I)
        times.append(T)

    INF = -float('inf')
    dp = [ [INF] * (N+1) for _ in range(K+1)]
    dp[0][0] = 0
    ans = 0
    for i in range(1, K+1):
        for t in range(N+1):
            dp[i][t] = dp[i-1][t]
            if t - times[i] >= 0 and dp[i-1][t-times[i]] != INF:
                dp[i][t] = max(dp[i][t], dp[i-1][t - times[i]] + importances[i])
                ans = max(ans, dp[i][t])

    print(ans)