
dy = [0,1,1,0,-1,-1]
dx = [1,0,-1,-1,0,1]

def solve(y, x, dir, cnt):
    ny = y + dy[dir]
    nx = x + dx[dir]

    if visited[ny][nx]:
        if cnt == N:
            return 1
        return 0

    if cnt == N:
        return 0

    ret = 0
    visited[ny][nx] = True
    ret += solve(ny,nx, (dir + 1) % 6, cnt + 1)
    ret += solve(ny, nx, (dir + 5) % 6, cnt + 1)
    visited[ny][nx] = False
    return ret

if __name__ == "__main__":
    N = int(input())

    MAX_R = 50;
    visited = [[False for _ in range(MAX_R+1)] for _ in range(MAX_R + 1)]
    sy = MAX_R // 2
    sx = MAX_R // 2
    visited[sy][sx] = True

    print(solve(sy,sx,0,0))