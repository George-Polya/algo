MAX_R = 1_000_000_000 * 4
def printBoard(board, width=5):

    for y in range(8):
        for x in range(8):
            if board[y][x] == MAX_R:
                print(f"{'INF':>{width}}", end='')
            else:
                print(f"{board[y][x]:>{width}}",end='')
        print()
if __name__ == "__main__":

    idx2pair = dict()
    idx = 0

    sx, sy = map(int,input().split())

    idx2pair[idx] = (sx,sy)
    idx += 1
    ex, ey = map(int,input().split())
    idx2pair[idx] = (ex,ey)
    idx += 1

    dist = [[MAX_R for _ in range(8)] for _ in range(8)]

    for _ in range(3):
        x1, y1, x2, y2 = map(int,input().split())
        idx2pair[idx] = (x1,y1)
        i = idx
        idx += 1

        j = idx
        idx2pair[idx] = (x2,y2)
        idx += 1

        dist[i][j] = 10
        dist[j][i] = 10


    # printBoard(dist)
    # print('----')
    for i in range(8):
        for j in range(8):
            if i == j:
                dist[i][j] = dist[j][i] = 0
                continue
            x1,y1 = idx2pair[i]
            x2,y2 = idx2pair[j]
            distance = abs(x1-x2) + abs(y1-y2)

            dist[i][j] = min(dist[i][j], distance)
            dist[j][i] = min(dist[j][i], distance)

    # printBoard(dist)
    # print('----')
    #
    for k in range(8):
        for i in range(8):
            for j in range(8):
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])

    # printBoard(dist)

    print(dist[0][1])







