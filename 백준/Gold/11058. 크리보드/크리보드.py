
def solve(n):
    if n <= 6:
        return n

    if dp[n] != -1:
        return dp[n]

    ret = solve(n - 1) + 1

    for prv in range(1,n-2):
        ret = max(ret, solve(prv) * (n - prv - 1))

    dp[n] = ret

    return ret

if __name__ == "__main__":
    N = int(input())
    dp = [-1 for _ in range(N+1)]

    print(solve(N))