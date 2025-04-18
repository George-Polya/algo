def check(start, len):
    cur = start

    i = (cur + 1) % M
    cnt = 1
    while i != start:
        if (arr[i] - arr[cur] + N) % N >= len:
            cur = i
            cnt += 1
            if cnt == K:
                return (arr[start] - arr[cur] + N) % N >= len

        i = (i + 1) % M

    return False
def decide(len):
    for i in range(M):
        if(check(i,len)):
            return True
    return False



if __name__ == '__main__':
    N,M,K = map(int,input().split())
    arr = []
    for i in range(M):
        arr.append(int(input()))

    if K == 1:
        print(N)
        exit()

    l = 1
    r = N
    ret = -1

    while l<=r :
        mid = (l + r) // 2
        if decide(mid):
            l = mid + 1
            ret = mid

        else:
            r = mid - 1

    print(ret)
    # print(ans)