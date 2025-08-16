
if __name__ == "__main__":
    N = int(input())

    arr = [0]


    for _ in range(N):
        arr.append(int(input()))


    arr.sort()
    ans = 0
    target = 1
    for i in range(1,N+1):
        if arr[i] >= target:
            ans += arr[i] - target
            target+=1

    print(ans)


