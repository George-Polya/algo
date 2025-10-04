
def printBoard(board):
    for y in range(1,M+1):
        for x in range(1,N+1):
            print(f"{board[y][x]:>5d}",end='')
        print()

def is_white(y, x):
    if (y+x) % 2 == 0 and board[y][x] == 0:
        return True

    if (y+x) % 2 == 1 and board[y][x] == 1:
        return True

    return False

def preprocess():
    for y in range(1,M+1):
        for x in range(1,N+1):
            if not is_white(y, x):
                cost[0][y][x] = 1
            else:
                cost[1][y][x] = 1


    for y in range(1, M + 1):
        for x in range(1, N + 1):
            for i in range(2):
                pSum[i][y][x] = pSum[i][y-1][x] + pSum[i][y][x-1] - pSum[i][y-1][x-1] + cost[i][y][x]

def calc(sy,sx, ey,ex, pSum):
    return pSum[ey][ex] - pSum[ey][sx - 1] - pSum[sy - 1][ex] + pSum[sy-1][sx-1]

if __name__ == "__main__":
    M,N,K = map(int,input().split())

    board = [[0] for _ in range(M+1)]

    for y in range(1,M+1):
        line = input()
        for x in range(1,N+1):
            if line[x-1] == 'B':
                board[y].append(0)
            else:
                board[y].append(1)
    cost = [[[0 for _ in range(N+1)] for _ in range(M+1)] for _ in range(2)]
    pSum = [[[0 for _ in range(N+1)] for _ in range(M+1)] for _ in range(2)]

    preprocess()

        

    ans = float('inf')
    for sy in range(1, M - K + 2):
        for sx in range(1, N - K + 2):
            ey = sy + K - 1
            ex = sx + K - 1

            for i in range(2):
                ans = min(ans, calc(sy,sx,ey,ex, pSum[i]))

    print(ans)





