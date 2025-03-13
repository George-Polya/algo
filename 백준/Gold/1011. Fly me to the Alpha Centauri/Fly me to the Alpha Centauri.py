if __name__ == '__main__':
    T = int(input())

    ans = []
    for t in range(T):
        x,y = map(int,input().split())
        dist = y - x

        n = 0

        while True:
            if dist <= n * n:
                break;
            n += 1

        ans.append((2 * n - 2) if dist <= n * (n-1) else (2 * n - 1))

    print('\n'.join(map(str, ans)))