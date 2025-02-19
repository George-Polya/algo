left,right,back,K = map(int,input().split())
INF = float('inf')

dp = [ [INF for _ in range(K+1)] for _ in range(4)]

dp[0][0] = 0

for e in range(1,K+1):
    for dir in range(4):
        if e - left >= 0:
            dp[(dir + 3) % 4][e] = min(dp[(dir + 3) % 4][e], dp[dir][e-left] + 1)

        if e - right >= 0:
            dp[(dir + 1) % 4][e] = min(dp[(dir + 1) % 4][e], dp[dir][e-right] + 1)

        if e - back >= 0:
            dp[(dir + 2) % 4][e] = min(dp[(dir + 2) % 4][e] , dp[dir][e-back] + 1)


print( -1 if dp[0][K] == INF else dp[0][K])