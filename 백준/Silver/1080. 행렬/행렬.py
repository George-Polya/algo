def solve():
    n, m = map(int, input().split())
    a = [list(map(int, list(input()))) for _ in range(n)]
    b = [list(map(int, list(input()))) for _ in range(n)]

    if n < 3 or m < 3:
        if a == b:
            print(0)
        else:
            print(-1)
        return

    count = 0
    for r in range(n - 2):
        for c in range(m - 2):
            if a[r][c] != b[r][c]:
                count += 1
                for i in range(r, r + 3):
                    for j in range(c, c + 3):
                        a[i][j] = 1 - a[i][j]

    if a == b:
        print(count)
    else:
        print(-1)

solve()