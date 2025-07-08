from pprint import pprint

def calc(bit , idx):
    sz = len(arr[idx])

    for i in range(sz):
        bit |= (1 << arr[idx][i])
    return bit

def solve(cur, cnt, bit):
    global ans
    if cur == M:
        if bit == (1 << (N+1)) - 2:
            ans = min(ans, cnt)
        return

    nxt = calc(bit, cur)
    solve(cur + 1, cnt + 1, nxt)

    solve(cur + 1, cnt, bit)



if __name__ == "__main__":
    N, M = map(int,input().split())
    arr = [[] for _ in range(M)]
    for y in range(M):
        arr[y] = list(map(int,input().split()))[1:]

    INF = float('inf')
    ans = INF

    solve(0,0,0)
    print(ans if ans != INF else -1)