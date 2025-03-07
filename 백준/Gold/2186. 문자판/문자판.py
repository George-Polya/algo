dy = [-1,1,0,0]
dx = [0,0,-1,1]

def OOB(y, x):
    return y<0 or y>=N or x<0 or x>=M

def solve(y, x, idx):
    if idx == len(line) - 1:
        return 1

    if dp[y][x][idx] != -1:
        return dp[y][x][idx]

    ret = 0
    for dir in range(4):
        for k in range(1,K+1):
            ny = y + dy[dir] * k
            nx = x + dx[dir] * k

            if OOB(ny,nx):
                continue

            if board[ny][nx] == line[idx+1]:
                ret += solve(ny,nx, idx+1)

    dp[y][x][idx] = ret
    return ret;



if __name__ == '__main__':
    N,M,K = map(int,input().split())
    board = [['' for _ in range(M)] for _ in range(N)]

    for y in range(N):
        line = input()
        for x in range(M):
            board[y][x] = line[x]

    line = input()
    dp = [[[ -1 for _ in range(len(line))] for _ in range(M)] for _ in range(N)]


    ans = 0

    for y in range(N):
        for x in range(M):
            if board[y][x] == line[0]:
                ans += solve(y,x,0)

    print(ans)