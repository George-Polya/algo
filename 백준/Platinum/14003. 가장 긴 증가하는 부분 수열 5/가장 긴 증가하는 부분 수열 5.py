from bisect import bisect_left

if __name__ == '__main__':
    N = int(input())
    arr = [0] + list(map(int,input().split()))

    dp = [arr[1]]
    trace = [-1] * (N+1)
    trace[1] = 0

    for i in range(2,N+1):
        if arr[i] > dp[-1]:
            dp.append(arr[i])
            trace[i] = len(dp) - 1
        else:
            idx = bisect_left(dp, arr[i])
            dp[idx] = arr[i]
            trace[i] = idx



    length = len(dp) - 1


    ans = []
    for i in range(N,0, -1):
        if trace[i] == length:
            ans.append(arr[i])
            length -= 1

    print(len(dp))
    print(' '.join(map(str, ans[::-1])))