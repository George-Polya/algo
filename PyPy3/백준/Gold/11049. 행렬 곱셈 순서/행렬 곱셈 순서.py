from collections import defaultdict

def solve(y,x):
    if y == x:
        return 0

    if dp[y][x] != -1:
        return dp[y][x]

    ret = float('inf')
    for i in range(y, x):
        ret = min(ret, solve(y, i) + solve(i+1, x) + arr[y][0] * arr[i][1] * arr[x][1])

    dp[y][x] = ret
    return ret

if __name__ == "__main__":
    N = int(input())
    arr = []
    for _ in range(N):
        r,c = map(int,input().split())
        arr.append((r,c))

    dp = [[-1] * N for _ in range(N)]

    print(solve(0, N - 1))