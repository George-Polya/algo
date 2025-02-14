from collections import namedtuple, deque
from heapq import heappush, heappop
from pprint import pprint

dy = [-1,1,0,0]
dx = [0,0,-1,1]

def OOB(y, x):
    return y< 0 or y>=N or x<0 or x>=N

def findStart(start):
    for dir in range(4):
        ny = start[0] + dy[dir]
        nx = start[1] + dx[dir]

        if OOB(ny,nx) or board[ny][nx] == '*':
            continue
        return (ny,nx,dir)

    return None

def getNxtDirs(dir):
    if dir == 0 or dir == 1:
        return [2,3]

    if dir == 2 or dir == 3:
        return [0,1]


class State:
    def __init__(self, y,x,dir, cnt):
        self.y = y
        self.x = x
        self.dir = dir
        self.cnt = cnt

    def __lt__(self, o):
        return self.cnt < o.cnt

    def __repr__(self):
        return f"y: {self.y}, x: {self.x}, dir: {self.dir}, cnt: {self.cnt}"

if __name__ == '__main__':
    N = int(input())
    board = [[] for _ in range(N)]

    start = None
    end = None
    for y in range(N):
        line = input()
        for x in range(len(line)):
            board[y].append(line[x])
            if board[y][x] == '#':
                if start == None:
                    start = (y,x)
                elif end == None:
                    end = (y,x)



    # pprint(board)
    # print("start", start)
    # print("end", end)

    # State = namedtuple('State', ['y', 'x','dir','cnt'])

    # start = findStart(start)

    # pprint(start)
    # q = deque()
    q = []
    for dir in range(4):
        state = State(start[0]+dy[dir], start[1]+dx[dir], dir, 0)
        heappush(q, state)

    # pprint(q)
    while q:
        # cur = q.popleft()
        cur = heappop(q)
        # print("cur: ", cur)

        if OOB(cur.y,cur.x):
            continue

        # pprint(cur)
        if board[cur.y][cur.x] == '.':
            ny = cur.y + dy[cur.dir]
            nx = cur.x + dx[cur.dir]
            heappush(q, State(ny,nx,cur.dir, cur.cnt))
            # q.append(State(ny,nx,cur.dir, cur.cnt))

        elif board[cur.y][cur.x] == '!':
            ny = cur.y + dy[cur.dir]
            nx = cur.x + dx[cur.dir]
            heappush(q, State(ny,nx,cur.dir, cur.cnt))
            # q.append(State(ny, nx, cur.dir, cur.cnt))

            nxtDirs = getNxtDirs(cur.dir)
            for nDir in nxtDirs:
                ny = cur.y + dy[nDir]
                nx = cur.x + dx[nDir]
                heappush(q, State(ny,nx,nDir, cur.cnt+1))
                # q.append(State(ny,nx, nDir, cur.cnt+1))

        elif cur.y == end[0] and cur.x == end[1]:
            print(cur.cnt)
            break