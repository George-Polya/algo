def printBoard(board):
    for y in range(1,N+1):
        for x in range(1,N+1):
            print("%3d" % board[y][x], end='')
        print()

if __name__ == '__main__':
    N = int(input())
    dist = [[] for _ in range(N + 1)]

    for y in range(1, N+1):
        dist[y] = [0] + list(map(int, input().split()))

    ans = []
    board = [[True for _ in range(N+1)] for _ in range(N+1)]
    for k in range(1,N+1):
        for y in range(1,N+1):
            for x in range(1,N+1):
                if y == x or x == k or k == y:
                    continue
                shortest = dist[y][x]
                sum = dist[y][k] + dist[k][x]
                if shortest == sum:
                    board[y][x] = False
                if shortest > sum:
                    print(-1)
                    exit()

    # printBoard(board)
    ans = 0
    for y in range(1,N+1):
        for x in range(y+1, N+1):
            if(board[y][x]):
                ans += dist[y][x]
    print(ans)