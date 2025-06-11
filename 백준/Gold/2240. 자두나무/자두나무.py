from collections import defaultdict


def solve(idx, cnt):

    if idx > T:
        return 0
    if cnt > W:
        return -float('inf')


    if (idx, cnt) in dp:
        return dp[(idx,cnt)]

    pos = cnt % 2 + 1

    plum = 1 if pos == arr[idx] else 0

    dp[(idx,cnt)] = max(solve(idx + 1, cnt), solve(idx + 1, cnt + 1)) + plum
    return dp[(idx,cnt)]

if __name__ == "__main__":
    T,W = map(int,input().split())
    arr = [0]

    for _ in range(T):
        arr.append(int(input()))

    dp = defaultdict(int)


    print(max(solve(1,0), solve(1,1)))