MOD = 1000000007

def tile_fill(n):
    if n == 1:
        return 2
    elif n == 2:
        return 7

    dp = [0] * (n + 1)
    dp_sum = [0] * (n + 1)

    dp[1] = 2
    dp[2] = 7
    dp_sum[2] = 1  # dp[0]의 값은 1로 간주

    for i in range(3, n + 1):
        dp_sum[i] = (dp_sum[i - 1] + dp[i - 3]) % MOD
        dp[i] = (2 * dp[i - 1] + 3 * dp[i - 2] + 2 * dp_sum[i]) % MOD

    return dp[n]

# 예시 실행
n = int(input())
print(tile_fill(n))