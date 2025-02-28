from operator import truediv


def check(cur, sz, f) -> bool:
    i = (cur + 1) % M
    cnt = 1
    while i != f:
        if (arr[i] - arr[cur] + N) % N >= sz:
            cur = i
            cnt += 1
            if cnt == K:
                return (arr[f] - arr[cur] + N) % N >= sz

        i = (i + 1) % M

    return False
def decide(len):
    for i in range(M):
        if(check(i,len, i)):
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

    lengths = set()
    for i in range(M):
        for j in range(i+1, M):
            lengths.add(abs(arr[i] - arr[j]))
    
    lengths = sorted(list(lengths))
    # print(lengths)

    l = 0
    r = len(lengths) - 1
    ret = -1

    while l<=r :
        mid = (l + r) // 2
        if decide(lengths[mid]):
            l = mid + 1
            ret = lengths[mid]

        else:
            r = mid - 1

    print(ret)
    # print(ans)