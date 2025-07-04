def solve(idx, cnt, bit):
    global dp
    if cnt < 0:
        return 0

    if idx == 1:
        return 1 if cnt == 0 else 0

    if (idx, cnt, bit) in dp:
        return dp[(idx, cnt, bit)]

    ret = solve(idx - 1, cnt, 0)
    if bit == 0:
        ret += solve(idx - 1, cnt, 1)
    else:
        ret += solve(idx - 1, cnt - 1, 1)

    dp[(idx, cnt, bit)] = ret
    return ret


if __name__ == "__main__":
    T = int(input())
    MAX_K = 99
    MAX_N = 100
    dp = dict()
    ans = []
    for _ in range(T):
        N, K = map(int,input().split())


        ans.append(solve(N, K, 0) + solve(N,K,1))

    print('\n'.join(map(str, ans)))