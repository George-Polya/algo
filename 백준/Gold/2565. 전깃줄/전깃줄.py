from collections import namedtuple

def solve(cur, last):
    if cur == N + 1:
        return 0

    if dp[cur][last] != -1:
        return  dp[cur][last]

    ret = solve(cur + 1, last)

    if arr[cur].b > arr[last].b:
        ret = max(ret, solve(cur+1, cur) + 1)

    dp[cur][last] = ret
    return ret

if __name__ == '__main__':
    N = int(input())

    Pair = namedtuple('Pair', ['a','b'])
    INF = int(1e10)
    arr = []
    for i in range(N):
        a,b = map(int,input().split())
        arr.append(Pair(a,b))

    arr = [Pair(0,-INF)] + arr

    arr.sort(key=lambda pair : pair.a)
    dp = [[-1] * (N+1) for _ in range(N+1)]
    print(N - solve(1, 0))