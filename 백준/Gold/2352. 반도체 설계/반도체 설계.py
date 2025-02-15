def lower_bound(arr, target):
    l = 0
    r = len(arr) - 1
    ret = r
    while l<=r :
        mid = (l+r)//2

        if target <= arr[mid]:
            # l = mid + 1
            r = mid - 1
            ret = mid
        else:
            l = mid + 1
            # r = mid - 1

    return ret


if __name__ == '__main__':
    N = int(input())
    arr = [0] + list(map(int,input().split()))
    # print(arr)

    dp = [arr[1]]

    for i in range(1,N+1):
        if arr[i] > dp[-1]:
            dp.append(arr[i])
        else:
            idx = lower_bound(dp, arr[i])
            dp[idx] = arr[i]

    print(len(dp))