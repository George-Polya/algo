from collections import defaultdict

def solve(sum, cnt):

    if sum < 0 or cnt < 0:
        return 0

    if sum == 0 and cnt == 0:
        return 1

    if (sum, cnt) in dp:
        return dp[(sum, cnt)]

    ret = solve(sum - 1, cnt - 1)
    ret = (ret + solve(sum - 2, cnt - 1) ) % MOD
    ret = (ret + solve(sum - 3, cnt - 1) ) % MOD

    dp[(sum, cnt)] = ret
    return ret

if __name__ == "__main__":
    T = int(input())
    ans = []
    MOD = 1e9 + 9
    dp = defaultdict(int)

    for _ in range(T):
        n, m = map(int,input().split())
        ans.append(int(solve(n,m) % MOD))

    print('\n'.join(map(str, ans)))