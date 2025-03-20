if __name__ == '__main__':
    N, K = map(int,input().split())
    arr = list(map(int, input().split()))

    diff = []
    prv = arr[0]
    for i in range(1, N):
        diff.append(arr[i] - prv)
        prv = arr[i]
    # print(diff)
    diff.sort()

    ans = 0
    for i in range(len(diff) - (K-1)):
        ans += diff[i]

    print(ans)