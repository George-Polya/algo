if __name__ == '__main__':
    N = int(input())
    K = int(input())

    MOD = int(1e9+3)
    dp = [[0 for _ in range(N+1)] for _ in range(N+1)]

    for i in range(N+1):
        dp[i][0] = 1
        if i >= 1:
            dp[i][1] = i

    for i in range(2, N+1):
        for j in range(2, K+1):
            dp[i][j] = (dp[i-1][j] + dp[i-2][j-1]) % MOD

    result = None

    if K == 1:
        result = N
    elif K * 2 > N:
        result = 0
    else:
        result = (dp[N-1][K] + dp[N-3][K-1]) % MOD

    print(result)