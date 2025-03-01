import bisect

if __name__ == '__main__':
    N = int(input())
    arr = list(map(int, input().split()))

    dp = [arr[0]]

    for i in range(1,N):
        if arr[i] > dp[-1]:
            dp.append(arr[i])
        else:
            idx = bisect.bisect(dp, arr[i])
            dp[idx] = arr[i]
    print(N - len(dp))