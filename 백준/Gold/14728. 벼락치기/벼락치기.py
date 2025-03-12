if __name__ == '__main__':
    N, T = map(int, input().split())

    times = [0]
    scores = [0]
    for i in range(1,N+1):
        k,s = map(int,input().split())
        times.append(k)
        scores.append(s)

    dp = [-1] * (T+1)
    dp[0] = 0

    ans = -1
    for i in range(1,N+1):
        for t in range(T,0, -1):
            if t - times[i] >= 0 and dp[t-times[i]] != -1:
                dp[t] = max(dp[t], dp[t-times[i]] + scores[i])
                ans = max(ans,dp[t])


    print(ans if ans != -1 else 0)