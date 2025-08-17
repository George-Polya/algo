
from dataclasses import dataclass

@dataclass
class Info:
    day : int
    fine : int


if __name__ == "__main__":
    N,T = map(int,input().split())

    arr = []
    total = 0
    for _ in range(N):
        d,m = map(int,input().split())
        arr.append(Info(d,m))
        total += m

    arr = [0] + arr

    INF = -float('inf')
    dp = [0 for _ in range(T+1)]

    dp[0] = 0

    for i in range(1,N+1):
        day = arr[i].day
        fine = arr[i].fine
        for t in range(T, day - 1, -1):
                dp[t] = max(dp[t], dp[t-day] + fine)


    ans = dp[T] if dp[T] != INF else 0
    print(total - ans)
