if __name__ == '__main__':
    N = int(input())
    arr = [0] + list(map(int, input().split()))

    pSum = [0] * (N+1)
    for i in range(1,N+1):
        pSum[i] = pSum[i-1] + arr[i]

    ans = []
    M = int(input())
    for _ in range(M):
        s, e = map(int,input().split())
        ans.append(pSum[e] - pSum[s-1])
    print('\n'.join(map(str, ans)))