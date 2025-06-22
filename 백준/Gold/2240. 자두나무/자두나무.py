
def solve(cur, cnt):
    if cur > T:
        return 0

    if cnt > W:
        return -float('inf')

    if (cur, cnt) in dp:
        return dp[(cur, cnt)]

    pos = cnt % 2 + 1

    plus = 1 if arr[cur] == pos else 0
    temp1 = solve(cur + 1, cnt + 1)

    temp2 = solve(cur + 1, cnt)
    ret = max(temp1, temp2) + plus
    dp[(cur, cnt)] = ret

    return ret

if __name__ == "__main__":
    T,W = map(int,input().split())
    arr = [0]
    dp = dict()
    for _ in range(T):
        arr.append(int(input()))
    # print(arr)

    print(max(solve(1,0), solve(1,1)))