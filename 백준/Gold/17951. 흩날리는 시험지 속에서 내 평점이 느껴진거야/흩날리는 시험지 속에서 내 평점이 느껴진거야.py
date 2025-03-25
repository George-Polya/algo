def decide(mn):
    cnt = 0
    total = 0
    for score in arr:
        total += score

        if total >= mn:
            cnt+=1
            total = 0
    return cnt >= K

if __name__ == "__main__":
    N,K = map(int,input().split())
    arr = list(map(int,input().split()))

    L = 0
    R = sum(arr)  + 1
    ans = 0
    while L<=R:
        mid = (L + R) // 2

        if decide(mid):
            L = mid + 1
            ans = mid
        else:
            R = mid - 1



    print(ans)