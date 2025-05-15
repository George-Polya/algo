def solve(idx, section):
    if section == 0:
        return 0
    if idx < 0 :
        return INT_MIN

    if (idx, section) in dp:
        return dp[(idx, section)]

    dp[(idx, section)] = solve(idx-1, section)

    for i in range(idx,0,-1):
        dp[(idx, section)] = max(dp[(idx, section)], solve(i-2,section-1) + (pSum[idx] - pSum[i-1]))


    return dp[(idx, section)]

if __name__ == "__main__":
    N,M = map(int,input().split())
    INT_MIN = -float('inf')
    pSum = [0 for _ in range(N+1)]
    dp = {}
    for i in range(1,N+1):
        pSum[i] = pSum[i-1] + int(input())

    print(solve(N,M))