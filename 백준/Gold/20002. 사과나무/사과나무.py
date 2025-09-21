
def printBoard(board):
    for y in range(1,N+1):
        for x in range(1,N+1):
            if board[y][x] == INF:
                temp = "INF"
                print(f"{temp:>5s}",end='')
            else:
                print(f"{board[y][x]:>5d}",end='')

        print()

def OOB(y, x):
    return y <= 0 or y > N or x <= 0 or x> N

def calc(sy,sx,ey,ex):
    return pSum[ey][ex] - pSum[ey][sx-1] - pSum[sy-1][ex] + pSum[sy-1][sx-1]

def getSum(ey, ex, k):
    sy = ey - k + 1
    sx = ex - k + 1

    if OOB(sy,sx):
        return INF

    return calc(sy,sx,ey,ex)

if __name__ == "__main__":
    N = int(input())
    board = [[] for _ in range(N+1)]
    for i in range(1,N+1):
        board[i] = [0] + list(map(int,input().split()))

    # printBoard(board)
    INF = -float('inf')
    pSum = [[0 for _ in range(N+1)] for _ in range(N+1)]

    for y in range(1,N+1):

        for x in range(1,N+1):
            pSum[y][x] = pSum[y-1][x] + pSum[y][x-1] - pSum[y-1][x-1] + board[y][x]

    # printBoard(pSum)

    ans = INF
    for k in range(1,N+1):
        for ey in range(1,N+1):
            for ex in range(1,N+1):
                sum = getSum(ey,ex,k)
                ans = max(ans,sum)

    print(ans)





    