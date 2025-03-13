def solve(cur):
    global cnt
    if cur > B:
        return

    if cur >= A and cur != 0:
        cnt+=1

    solve(cur * 10 + 4)
    solve(cur * 10 + 7)


if __name__ == '__main__':
    A,B = map(int, input().split())
    cnt = 0
    solve(0)

    print(cnt)