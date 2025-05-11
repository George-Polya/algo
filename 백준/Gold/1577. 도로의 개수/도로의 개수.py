from collections import namedtuple
import sys
# sys.setrecursionlimit(10000)
Pair = namedtuple('Pair', ['x','y'])
dx = [0,1]
dy = [1,0]

def isUsed(cur, nxt):
    tmp = (cur.x, cur.y, nxt.x, nxt.y)

    return (cur.x, cur.y, nxt.x, nxt.y) in used

def OOB(nxt):
    return nxt.x > N or nxt.y > M

def solve(x, y):
    if x > N or y > M:
        return 0
    if x == N and y == M:
        return 1

    if (x,y) in dp:
        return dp[(x,y)]

    ret = 0
    right = frozenset({(x+1,y), (x,y)})
    if right not in used:
        ret += solve(x+1,y)


    up = frozenset({(x,y+1), (x,y) })
    if up not in used:
        ret += solve(x, y+1)

    dp[(x,y)] = ret
    return ret




if __name__ == "__main__":
    N,M = map(int,input().split())
    K = int(input())
    used = set()
    for _ in range(K):
        x1,y1,x2,y2 = map(int,input().split())
        used.add(frozenset({(x1,y1), (x2,y2)}))

    dp = {}
    print(solve(0,0))