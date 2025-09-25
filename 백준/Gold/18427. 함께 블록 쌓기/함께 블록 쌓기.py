from collections import defaultdict

def solve(cur, sum):
    # print(f"cur: {cur}, sum: {sum}")
    if sum == H:
        return 1

    if sum > H:
        return 0

    if cur == N:
        return 0

    if (cur,sum) in dp:
        return dp[(cur,sum)]

    ret = solve(cur + 1, sum)

    for block in blocks[cur]:
        ret = (ret + solve(cur+1, sum + block)) % MOD

    dp[(cur,sum)] = ret
    return ret


if __name__ == "__main__":
    N,M,H = map(int,input().split())
    blocks = [[] for _ in range(N)]
    MOD = 10007
    for i in range(N):
        blocks[i] = list(map(int,input().split()))

    dp = defaultdict(int)

    print(solve(0, 0))