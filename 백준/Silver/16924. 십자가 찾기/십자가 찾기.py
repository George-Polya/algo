dy = [-1,1,0,0]
dx = [0,0,-1,1]

def possible(cy,cx,sz):
    if target[cy][cx] == '.':
        return False

    for size in range(1,sz+1):
        for dir in range(4):
            ny = cy + dy[dir] * size
            nx = cx + dx[dir] * size

            if target[ny][nx] == '.':
                return False
    return True

def cover(sy,sx, sz):
    board[cy][cx] = '*'
    for size in range(1, sz+1):
        for dir in range(4):
            ny = cy + dy[dir] * size
            nx = cx + dx[dir] * size

            board[ny][nx] = '*'

def isEqual(target, board):
    for y in range(1,N+1):
        for x in range(1,M+1):
            if target[y][x] != board[y][x]:
                return False

    return True

if __name__ == '__main__':
    N,M = map(int, input().split())

    target = [ ['.'] * (M+1) for _ in range(N+1)]

    for y in range(1,N+1):
        line = input()
        for x in range(1, M+1):
            target[y][x] = line[x-1]

    maxSize = (min(N,M) - 1) // 2

    board = [['.'] * (M + 1) for _ in range(N + 1)]

    ans = []
    for sz in range(1, maxSize+1):
        for cy in range(1+sz, N - sz + 1):
            for cx in range(1 + sz, M - sz + 1):
                if possible(cy,cx, sz):
                    cover(cy,cx,sz)
                    ans.append((cy,cx,sz))

                    if isEqual(target, board):
                        print(len(ans))
                        for t in ans:
                            print(t[0], t[1], t[2])

                        exit()

    print(-1)