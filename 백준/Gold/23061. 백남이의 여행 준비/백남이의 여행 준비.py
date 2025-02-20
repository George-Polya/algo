if __name__ == '__main__':
    N,M = map(int,input().split())
    weights = [0] * (N+1)
    values = [0] * (N+1)

    for i in range(1, N+1):
        weights[i], values[i] = map(int,input().split())

    bags = [0]
    for i in range(1,M+1):
        bags.append(int(input()))

    INT_MIN = -float('inf')

    K = max(bags)
    dp = [0] * ( K + 1)

    dp[0] = 0
    for i in range(1, N+1):
        for k in range(K, 0, -1):
            if k - weights[i] >= 0 and dp[k-weights[i]] != INT_MIN:
                dp[k] = max(dp[k], dp[k-weights[i]] + values[i])

    ans = INT_MIN
    idx = M + 2
    # print(dp)
    for i in range(1,M+1):
        bag = bags[i]
        effi = dp[bag] / bag
        # print(f"dp[{bag}] : {dp[bag]}, effi: {effi}")
        if ans < effi or (ans == effi and idx > i):
            ans = effi
            idx = i



    print(idx)