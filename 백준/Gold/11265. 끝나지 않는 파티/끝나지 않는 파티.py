
if __name__ == "__main__":
    N,M = map(int,input().split())
    board = [[] for _ in range(N+1)]

    for y in range(1,N+1):
        board[y] = [0] + list(map(int,input().split()))

    for k in range(1, N+1):
        for y in range(1, N+1):
            for x in range(1, N+1):
                if board[y][x] > board[y][k] + board[k][x]:
                    board[y][x] = board[y][k] + board[k][x]

    ans = []
    for _ in range(M):
        a,b,cost = map(int,input().split())

        ans.append("Enjoy other party" if board[a][b] <= cost else "Stay here")

    print('\n'.join(ans))