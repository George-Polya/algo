
def printBoard(board):
    for y in range(1,N+1):
        for x in range(1,M+1):
            print("%3d" % board[y][x], end='')
        print()

def calc(y1,x1, y2,x2):
    return pSum[y2][x2] - pSum[y1-1][x2] - pSum[y2][x1-1] + pSum[y1-1][x1-1]

if __name__ == '__main__':
    N,M = map(int,input().split())

    pSum = [[0] * (M+1) for _ in range(N+1)]

    for y in range(1,N+1):
        line = [ ord(ch) - ord('0') for ch in input()]

        for x in range(1,M+1):
            pSum[y][x] = pSum[y-1][x] + pSum[y][x-1] - pSum[y-1][x-1] + line[x-1]


    # printBoard(pSum)

    ans = 0

    for x1 in range(1,M+1):
        for x2 in range(x1+1, M+1):
            r1 = calc(1,1,N,x1)
            r2 = calc(1,x1+1, N,x2)
            r3 = calc(1,x2+1, N,M)
            ans = max(ans, r1*r2*r3)

    for y1 in range(1,N+1):
        for y2 in range(y1+1, N+1):
            r1 = calc(1,1,y1,M)
            r2 = calc(y1+1,1, y2,M)
            r3 = calc(y2+1,1,N,M)
            ans = max(ans, r1 * r2 * r3)


    for y in range(1,N+1):
        for x in range(1,M+1):
            r1 = calc(1,1,N,x)
            r2 = calc(1,x+1,y,M)
            r3 = calc(y+1,x+1, N,M)
            ans = max(ans, r1 * r2 * r3)

            r1 = calc(1,1,y,x)
            r2 = calc(y+1,1,N,x)
            r3 = calc(1,x+1, N,M)
            ans = max(ans, r1 * r2 * r3)

            r1 = calc(1,1,y,x)
            r2 = calc(1,x+1, y,M)
            r3 = calc(y+1,1,N,M)
            ans = max(ans, r1 * r2 * r3)

            r1 = calc(1,1,y,M)
            r2 = calc(y+1,1,N,x)
            r3 = calc(y+1,x+1,N,M)
            ans = max(ans, r1 * r2 * r3)

    print(ans)
