


def solve(idx, sum):
    global _set
    if idx == K:
        if sum > 0:
            _set.add(sum)
        return

    solve(idx + 1, sum + arr[idx])


    solve(idx + 1, sum - arr[idx])

    solve(idx + 1, sum)

if __name__ == "__main__":
    K = int(input())
    arr = list(map(int,input().split()))
    S = sum(arr)

    _set = set()
    solve(0, 0)
    print(S - len(_set))