if __name__ == "__main__":
    start,end = map(int, input().split())

    end -= start
    start -= start

    cnt = 0
    speed = 0

    while start < end:
        for s in range(1,-2,-1):
            nxt = speed + s
            if ((nxt * (nxt + 1)) // 2 ) + start <= end:
                speed = nxt
                start += speed
                cnt += 1
                break

    print(cnt)