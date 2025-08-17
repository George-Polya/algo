
from dataclasses import dataclass

@dataclass
class Info:
    d : int
    m : int

    def __lt__(self, other):
        return self.d < other.d

if __name__ == "__main__":
    N,T = map(int,input().split())

    arr = []
    sm = 0
    for _ in range(N):
        d,m = map(int,input().split())
        arr.append(Info(d,m))
        sm += m

    arr = [0] + sorted(arr)

    INF = -float('inf')
    dp = [INF for _ in range(T+1)]

    dp[0] = 0

    for i in range(1,N+1):
        for t in range(T, 0, -1):
            if t - arr[i].d >= 0 and dp[t-arr[i].d] != INF:
                dp[t] = max(dp[t], dp[t-arr[i].d] + arr[i].m)


    print(sm - max(dp))

