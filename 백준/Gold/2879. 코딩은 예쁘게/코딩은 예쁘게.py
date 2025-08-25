

if __name__ == "__main__":
    N = int(input())
    src = list(map(int,input().split()))
    dst = list(map(int,input().split()))

    diff = [dst[i] - src[i] for i in range(N)]

    ans = 0
    prev = 0

    for i in range(N):
        cur = diff[i]

        if cur > 0 and prev >= 0:
            if cur > prev:
                ans += cur - prev

        elif cur <0 and prev <=0 :
            if cur < prev:
                ans += prev - cur
        else:
            ans += abs(cur)

        prev = cur

    print(ans)