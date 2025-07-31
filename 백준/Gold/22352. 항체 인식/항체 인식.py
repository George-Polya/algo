from collections import deque, namedtuple
Pair = namedtuple('Pair', ['y', 'x'])
dy = [-1,1,0,0]
dx = [0,0,-1,1]
NO_PAIR = Pair(-1,-1)
def OOB(y, x):
    return y<0 or y>=N or x<0 or x>=M

def findChanges(before: list[list], after: list[list]) -> Pair:

    for y in range(N):
        for x in range(M):
            if before[y][x] != after[y][x]:
                return Pair(y,x)

    return NO_PAIR



if __name__ == "__main__":
    N, M = map(int,input().split())
    before = [[] for _ in range(N)]

    for y in range(N):
        before[y] = list(map(int, input().split()))


    after = [[] for _ in range(N)]
    for y in range(N):
        after[y] = list(map(int,input().split()))


    target = findChanges(before, after)
    if target == NO_PAIR:
        print("YES")
        exit()



    q = deque([target])
    mask = after[target.y][target.x]
    original = before[target.y][target.x]
    before[target.y][target.x] = mask

    while q:
        cur = q.popleft()

        for d in range(4):
            ny = cur.y + dy[d]
            nx = cur.x + dx[d]

            if OOB(ny,nx):
                continue

            if before[ny][nx] == original:
                before[ny][nx] = mask
                q.append(Pair(ny,nx))

    print("YES" if findChanges(before, after) == NO_PAIR else "NO")
