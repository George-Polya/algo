from collections import deque, namedtuple

Pair = namedtuple('Pair', ['y', 'x'])
def getNxtPos(cur):
    return [Pair(cur.y + 1, cur.x), Pair(cur.y-1, cur.x), Pair(cur.y + K, (cur.x + 1) % 2)]

def bfs():
    visited = [[False for _ in range(N+1)] for _ in range(2)]
    start = Pair(0,1)
    q = deque([start])
    visited[start.y][start.x] = True

    cnt = 0
    while q:
        # print("=" * 15)
        size = len(q)

        for _ in range(size):

            cur = q.popleft()

            if cur.x <= cnt:
                continue

            nxt = Pair(cur.y, cur.x + 1)
            if nxt.x > N:
                return 1

            if board[nxt.y][nxt.x] == 1 and not visited[nxt.y][nxt.x]:
                visited[nxt.y][nxt.x] = True
                q.append(nxt)

            nxt = Pair(cur.y, cur.x - 1)

            if nxt.x >= cnt and board[nxt.y][nxt.x] == 1 and not visited[nxt.y][nxt.x]:
                visited[nxt.y][nxt.x] = True
                q.append(nxt)

            nxt = Pair(1 - cur.y, cur.x + K)
            if nxt.x > N:
                return 1

            if board[nxt.y][nxt.x] == 1 and not visited[nxt.y][nxt.x]:
                visited[nxt.y][nxt.x] = True
                q.append(nxt)


        cnt+=1

    return 0



if __name__ == "__main__":
    N,K = map(int,input().split())

    board = [[-1 for _ in range(N+1)] for _ in range(2)]

    for y in range(2):
        line = input()
        for x in range(N+1):
            board[y][x] = int(line[x-1])

    print(bfs())

