def init(N):
    tetras = list()
    num = 1
    idx = 1

    while num <=N:
        tetras.append(num)
        idx+=1
        num = idx * (idx + 1) * (idx + 2) // 6

    return tetras

if __name__ == "__main__":
    N = int(input())
    tetras = init(N)
    INT_MAX = float('inf')
    dp = [INT_MAX for _ in range(N+1)]
    # dp = dict()
    dp[0] = 0

    for t in tetras:
        for i in range(t, N+1):
            if dp[i-t] != INT_MAX:
                dp[i] = min(dp[i], dp[i-t] + 1)
    print(dp[N])