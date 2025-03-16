def solve():
    t = int(input())
    fibonacci = [0, 1]
    while fibonacci[-1] <= 1000000000:
        fibonacci.append(fibonacci[-1] + fibonacci[-2])

    for _ in range(t):
        n = int(input())
        result = []
        i = len(fibonacci) - 1
        while n > 0:
            if fibonacci[i] <= n:
                result.append(fibonacci[i])
                n -= fibonacci[i]
            i -= 1
        print(*sorted(result))

solve()