
def decide(dist):
    cnt = 1
    cur = arr[0]
    for i in range(1,N):
        if cur + dist <= arr[i]:
            cnt += 1
            cur = arr[i]

    return C <= cnt

def lower_bound(arr):
    l = 1
    r = 1_000_000_000;
    ret = 0
    while l<=r :
        mid = (l+r) // 2
        if(decide(mid)):
            ret = mid
            l = mid + 1
        else:
            r = mid - 1

    return ret

if __name__=="__main__":
    N,C = map(int,input().split())
    arr = list()
    for _ in range(N):
        arr.append(int(input()))

    arr.sort()
    print(lower_bound(arr))