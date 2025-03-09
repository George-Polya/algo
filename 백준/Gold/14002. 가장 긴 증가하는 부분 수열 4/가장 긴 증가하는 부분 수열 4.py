if __name__ == '__main__':
    N = int(input())
    arr = [0] + list(map(int,input().split()))
    trace = [-1 for _ in range(N+1)]
    dp = [-1 for _ in range(N+1)]
    dp[0] = 0
    trace[1] = 0

    ans = -1
    for i in range(1, N+1):
        for j in range(i):
            if(arr[i] > arr[j] and dp[j] != -1):
                dp[i] = max(dp[i], dp[j] + 1)
                ans = max(ans, dp[i])
                trace[i] = dp[i] - 1

    
    print(ans)

    length = ans - 1
    ans = []
    for i in range(N, 0, -1):
        if trace[i] == length:
            ans.append(arr[i])
            length -= 1

    print(' '.join(map(str,ans[::-1])))