
def check(idx):
    left = 0
    right = N - 1
    target = arr[idx]
    while left < right:
        if left == idx:
            left += 1
        elif right == idx:
            right -= 1
        else:
            if arr[left] + arr[right] == target:
                return True
            if arr[left] + arr[right] > target:
                right -= 1
            else:
                left += 1

    return False

if __name__ == "__main__":
    N = int(input())
    arr = list(map(int,input().split()))

    arr.sort()
    cnt = 0
    for i in range(N):
        if check(i):
            cnt += 1

    print(cnt)