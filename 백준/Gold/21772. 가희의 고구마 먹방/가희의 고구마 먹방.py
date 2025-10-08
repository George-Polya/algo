from collections import namedtuple,deque


dy = [-1,1,0,0]
dx = [0,0,-1,1]

def OOB(y, x):
    return y<=0 or y > R or x<=0 or x> C


def solve(y,x, cnt, t):
    if t == T:
        return cnt

    # 현재 위치 그대로
    ret = solve(y,x, cnt ,t + 1)

    # 다음 칸 이동
    for d in range(4):
        ny = y + dy[d]
        nx = x + dx[d]

        if OOB(ny,nx) or board[ny][nx] == '#':
            continue

        # 다음칸이 S라면
        if board[ny][nx] == 'S':
            board[ny][nx] = '.'
            ret = max(ret, solve(ny,nx, cnt + 1, t + 1))
            board[ny][nx] = 'S'
        else:
            ret = max(ret, solve(ny,nx,cnt, t + 1))
        

    return ret


if __name__ == "__main__":
    R,C,T = map(int,input().split())
    visited = [[False for _ in range(C+1)] for _ in range(R+1)]
    Pair = namedtuple('Pair', ['y', 'x',])
    board = [['.'] for _ in range(R+1)]
    start = None
    for y in range(1,R+1):
        line = input()
        for x in range(1, C+1):
            board[y].append(line[x-1])
            if board[y][x] == 'G':
                start = Pair(y,x)

    visited[start.y][start.x] = True
    print(solve(start.y, start.x, 0, 0))

