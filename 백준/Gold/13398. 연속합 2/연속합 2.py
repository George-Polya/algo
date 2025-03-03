def solve(flag, cur):
    if cur == 1:
        return arr[1] if flag == 0 else INF

    if dp[flag][cur] != INF:
        return dp[flag][cur]

    ret = INF
    if flag == 0:
        ret = max(solve(0, cur - 1), 0) + arr[cur]
    else:
        ret = max(solve(1,cur - 1) + arr[cur], solve(0, cur-1))

    dp[flag][cur] = ret

    return dp[flag][cur]

if __name__ == "__main__":
    N = int(input())
    arr = [0] + list(map(int,input().split()))
    INF = -1e10
    dp = [[INF for _ in range(N+1)] for _ in range(2)]

    ans = INF
    for i in range(1,N+1):
        ans = max(ans, solve(0,i))
        ans = max(ans, solve(1,i))
    print(ans)