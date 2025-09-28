from collections import deque, namedtuple


dy = [0,1,0,-1]
dx = [1,0,-1,0]

def OOB(y,x):
    return y<=0 or y>N or x<=0 or x>N

def collide(y,x):
    for pair in deq:
        if y == pair.y and x == pair.x:
            return True

    return False

def getNxtPos(y,x,dir):
    ny = y + dy[dir]
    nx = x + dx[dir]

    return Pair(ny,nx)

def appleExist(y,x):
    return board[y][x] == 'A'

def update(dir, time):
    if time not in directions:
        return dir

    direction = directions[time]
    if direction == 'D':
        return (dir + 1) % 4
    else:
        return (dir + 3) % 4

def end(y, x):
    return OOB(y,x) or collide(y,x)

if __name__ == "__main__":
    N = int(input())
    K = int(input())
    board = [['#' for _ in range(N+1)]for _ in range(N+1)]

    for _ in range(K):
        y,x = map(int,input().split())
        board[y][x] = 'A'

    L = int(input())
    directions = {}
    for _ in range(L):
        time, d = input().split()
        directions[int(time)] = d

    Pair = namedtuple('Pair', ['y', 'x'])
    deq = deque([Pair(1,1)])
    dir = 0
    time = 0
    while True:
        cur = deq[0]

        # print("-"*10)
        #
        # print(f"cur: {cur}")


        ny,nx = getNxtPos(cur.y, cur.x, dir)
        if end(ny, nx):
            break

        deq.appendleft(Pair(ny,nx))

        if appleExist(ny,nx):
            board[ny][nx] = '#'
        else:
            deq.pop()

        time += 1
        dir = update(dir, time)


    print(time + 1)
