from collections import deque

def solve():
    board = ""
    sy, sx = -1, -1
    for y in range(3):
        row = input().split()
        for x in range(3):
            if row[x] == '0':
                sy, sx = y, x
            board += row[x]

    start = (sy, sx, board)
    q = deque([start])
    visited = {start[2]}  # 문자열을 저장하는 set
    dist = {start[2]: 0}

    dy = [-1, 1, 0, 0]
    dx = [0, 0, -1, 1]

    def oob(y, x):
        return y < 0 or y >= 3 or x < 0 or x >= 3

    def swap(cur_y, cur_x, ny, nx, board_str):
        board_list = list(board_str)
        cur_idx = cur_y * 3 + cur_x
        nxt_idx = ny * 3 + nx
        board_list[cur_idx], board_list[nxt_idx] = board_list[nxt_idx], board_list[cur_idx]
        return (ny, nx, "".join(board_list))

    while q:
        cur_y, cur_x, cur_board = q.popleft()

        if cur_board == "123456780":
            print(dist[cur_board])
            return

        for i in range(4):
            ny, nx = cur_y + dy[i], cur_x + dx[i]

            if oob(ny, nx):
                continue

            nxt = swap(cur_y, cur_x, ny, nx, cur_board)
            if nxt[2] in visited:
                continue

            visited.add(nxt[2])
            dist[nxt[2]] = dist[cur_board] + 1
            q.append(nxt)

    print(-1)

if __name__ == "__main__":
    solve()