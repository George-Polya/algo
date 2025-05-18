from collections import defaultdict

def init():
    for y in range(N):
        for x in range(M):
            dfs(y,x,1, board[y][x])

dy = [-1,-1,0,1,1,1,0,-1]
dx = [0,1,1,1,0,-1,-1,-1]

def dfs(y, x, cnt, string):

    hs[string] = hs.get(string, 0) + 1
    if cnt == 5:
        return

    for dir in range(8):
        ny = y + dy[dir]
        nx = x + dx[dir]

        ny = (ny + N) % N
        nx = (nx + M) % M

        dfs(ny, nx, cnt + 1, string + board[ny][nx])

if __name__ == "__main__":
    N,M,K = map(int, input().split())
    board = [[] for _ in range(N)]
    hs = dict()
    for y in range(N):
        line = input()
        for x in range(M):
            board[y].append(line[x])



    init()
    ans = []
    for i in range(K):
        line = input()
        ans.append(hs[line] if line in hs else 0)
    print('\n'.join(map(str, ans)))