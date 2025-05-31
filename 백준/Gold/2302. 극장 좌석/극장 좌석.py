from collections import defaultdict

if __name__ == "__main__":
    N = int(input())
    M = int(input())
    arr = []
    for _ in range(M):
        arr.append(int(input()))

    dp = defaultdict(int)
    dp[0] = 1
    dp[1] = 1
    for i in range(2,N+1):
        dp[i] = dp[i-1] + dp[i-2]

    ans = 1
    start = 0
    for i in range(M):
        end = arr[i]
        ans *= dp[end - start - 1]
        start = end

    ans *= dp[N-start]
    print(ans)