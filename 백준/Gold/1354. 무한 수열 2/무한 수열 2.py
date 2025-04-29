from collections import deque, namedtuple,defaultdict
import math
def solve(cur):
    if cur <= 0:
        return 1

    if cur in dp:
        return dp[cur]

    ret = solve(int(math.floor(cur / P) - X)) + solve(int(math.floor(cur / Q) - Y))
    dp[cur] = ret
    return ret
if __name__ == '__main__':
    N, P,Q,X,Y = map(int,input().split())

    dp = defaultdict()
    print(solve(N))