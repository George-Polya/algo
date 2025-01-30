from collections import defaultdict, namedtuple

def printBoard(board):
    for y in range(1,N+1):
        for x in range(1, M+1):
            print("%3d" % board[y][x],end='')
        print()

def getSum(y1, x1, y2, x2):
    return board[y2][x2] - board[y2][x1-1] - board[y1-1][x2] + board[y1-1][x1-1]

if __name__ == '__main__':
    T = int(input())
    ans = []
    for tc in range(1, T+1):

        N,M = map(int,input().split())
        board = [[0 for _ in range(N+1) ] for _ in range(N+1)]
        for y in range(1,N+1):
            line = list(map(int, input().split()))
            for x in range(1,N+1):
                value = line[x-1]
                board[y][x] = board[y][x-1] + board[y-1][x] - board[y-1][x-1] + value

        horizon_sum = [getSum(y, 1, y, N) for y in range(1, N + 1)]
        vertical_sum =  [getSum(1, x, N, x) for x in range(1, N + 1)]

        for _ in range(M):
            y1,x1,y2,x2, v = map(int,input().split())

            horizon_len = abs(x2-x1) + 1
            vertical_len = abs(y2-y1) + 1
            for y in range(y1,y2+1):
                horizon_sum[y-1] += v * horizon_len

            for x in range(x1, x2+1):
                vertical_sum[x-1] += v * vertical_len

        temp = [' '.join(map(str, horizon_sum))]
        temp.append(' '.join(map(str, vertical_sum)))
        # print(temp)
        ans.extend(temp)
        # print('\n'.join(temp))
        # print(horizon_sum, vertical_sum)

    print('\n'.join(ans))