from pprint import pprint

if __name__ == '__main__':
    N, M = map(int,input().split())
    board = [[0 for _ in range(M+1)] for _ in range(N+1)]
    for y in range(1, N+1):
        line = list(map(int,input().split()))
        for x in range(0, M+1):
            board[y][x] = line[x];



    dp = [[0 for _ in range(N+1)] for _ in range(M+1)]



    path = [[0 for _ in range(N+1)] for _ in range(M+1)]

    for company in range(1,M+1):
        for cost in range(1, N+1):
            for cur in range(cost+1):
                # print(f"company: {company}, cost: {cost}, cur: {cur}")
                if dp[company][cost] < dp[company - 1][cost - cur] + board[cur][company]:
                   dp[company][cost] =  dp[company - 1][cost - cur] + board[cur][company]
                   path[company][cost] = cur


    stk = []
    company = M
    cost = N
    while company > 0:
        money = path[company][cost]
        stk.append(money)
        cost -= money
        company-=1

    ans = []
    while stk:
        ans.append(stk.pop())
    print(dp[M][N])
    print(' '.join(map(str, ans)))