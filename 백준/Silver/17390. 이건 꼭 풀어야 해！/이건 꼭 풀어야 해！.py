if __name__ == '__main__':
    N,M = map(int, input().split())
    arr = [0]

    arr.extend(map(int, input().split()))
    # for e in list(map(int, input().split())):
    #     arr.append(e)
    arr.sort()
    b = [0 for _ in range(N+1)]

    for i in range(1,N+1):
        b[i] = b[i-1] + arr[i]

    ans = []
    for _ in range(M):
        s, e = map(int, input().split())
        ans.append(b[e] - b[s-1])

    print('\n'.join(map(str, ans)) + '\n')