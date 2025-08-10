# BOJ 4179 - 불!
import sys
from collections import deque

input = sys.stdin.readline

r, c = map(int, input().split())
board = [list(input().strip()) for _ in range(r)]

dx = [1, 0, -1, 0]
dy = [0, 1, 0, -1]

dist1 = [[-1] * c for _ in range(r)]  # fire
dist2 = [[-1] * c for _ in range(r)]  # J

q1 = deque()  # fire queue
q2 = deque()  # J queue

for i in range(r):
    for j in range(c):
        if board[i][j] == 'F':
            q1.append((i, j))
            dist1[i][j] = 0
        elif board[i][j] == 'J':
            q2.append((i, j))
            dist2[i][j] = 0

def oob(y, x):
    return y < 0 or y >= r or x < 0 or x >= c

# spread fire first
while q1:
    y, x = q1.popleft()
    for k in range(4):
        ny, nx = y + dy[k], x + dx[k]
        if oob(ny, nx):
            continue
        if dist1[ny][nx] != -1 or board[ny][nx] == '#':
            continue
        dist1[ny][nx] = dist1[y][x] + 1
        q1.append((ny, nx))

# move J
while q2:
    y, x = q2.popleft()
    for k in range(4):
        ny, nx = y + dy[k], x + dx[k]
        if oob(ny, nx):
            print(dist2[y][x] + 1)
            sys.exit(0)
        if dist2[ny][nx] != -1 or board[ny][nx] == '#':
            continue
        if dist1[ny][nx] != -1 and dist1[ny][nx] <= dist2[y][x] + 1:
            continue
        dist2[ny][nx] = dist2[y][x] + 1
        q2.append((ny, nx))

print("IMPOSSIBLE")
