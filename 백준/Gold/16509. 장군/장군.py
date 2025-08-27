from collections import deque, namedtuple


directions = [
    [[-1,-1,-1],
     [0,1,1]],
    [[0,-1,-1],
     [1,1,1]],
    [[0,1,1],
     [1,1,1]],
    [[1,1,1],
     [0,1,1]],
    [[1,1,1],
     [0,-1,-1]],
    [[0,1,1],
     [-1,-1,-1]],
    [[0,-1,-1],
     [-1,-1,-1]],
    [[-1,-1,-1],
     [0,-1,-1]]
]


def OOB(y, x):
    return y < 0 or y >= 10 or x < 0 or x >= 9

def getNxtPos(y,x,d):
    direction = directions[d]
    dy = direction[0]
    dx = direction[1]


    for i in range(3):
        y = y + dy[i]
        x = x + dx[i]
        if OOB(y,x):
            return NO_POS
        if (i == 0 or i == 1) and (y == ey and x == ex): # 중간단계에서 왕을 만나면 그쪽으로 갈 수 없음
            return NO_POS



    return Pair(y,x)
def bfs(y, x):
    dist = [[-1 for _ in range(9)] for _ in range(10)]
    q = deque([Pair(y,x)])
    dist[y][x] = 0
    level = 0
    while q:
        # print(f"----level: {level}----")
        size = len(q)
        for _ in range(size):

            cur = q.popleft()

            if cur.y == ey and cur.x == ex:
                return dist[cur.y][cur.x]

            for d in range(8):
                nxt = getNxtPos(cur.y, cur.x, d)

                if nxt == NO_POS:
                    continue
                if dist[nxt.y][nxt.x] != -1:
                    continue

                q.append(nxt)
                dist[nxt.y][nxt.x] = dist[cur.y][cur.x] + 1
        level += 1
    return -1

if __name__ == "__main__":
    sy,sx = map(int,input().split())
    ey,ex = map(int,input().split())

    Pair = namedtuple('Pair', ['y', 'x'])
    NO_POS = Pair(-1,-1)

    print(bfs(sy,sx))