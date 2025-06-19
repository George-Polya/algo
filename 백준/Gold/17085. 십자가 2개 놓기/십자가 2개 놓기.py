from collections import namedtuple
from pprint import pprint


dy = [-1,1,0,0]
dx = [0,0,-1,1]
def OOB(y, x):
    return y<0 or y>=N or x<0 or x>=M

def solve(cnt, area):
    global ans
    if cnt == 2:
        if ans < area:
            ans = max(ans, area)
        return

    for y,x in pairs:
        for n in range(MAX_N+1):
            if possible(y,x, n):
                cover(y,x,n, '*')
                solve(cnt + 1, area * calc(n))
                cover(y,x,n, '#')

def possible(cy, cx, n):
    if board[cy][cx] != '#':
        return False

    for num in range(1, n+1):
        for d in range(4):
            ny = cy + dy[d] * num
            nx = cx + dx[d] * num
            if OOB(ny,nx) or board[ny][nx] != '#':
                return False

    return True

def calc(num):
    return 4 * num + 1

def cover(cy, cx, n, ch):
    global board
    board[cy][cx] = ch
    for num in range(1, n+1):
        for d in range(4):
            ny = cy + dy[d] * num
            nx = cx + dx[d] * num

            board[ny][nx] = ch

if __name__ == "__main__":
    N,M = map(int,input().split())
    board = [[] for _ in range(N)]
    pairs = []
    Pair = namedtuple('Pair', ['y', 'x'])
    MAX_N = min(N,M) // 2
    ans = 0
    for y in range(N):
        line = input()
        for x in range(M):
            board[y].append(line[x])
            if board[y][x] == '#':
                pairs.append(Pair(y,x))

    # pprint(board)
    solve(0,1)
    print(ans)