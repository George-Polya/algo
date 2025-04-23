from collections import deque, namedtuple

def makeState(a,b,c):
    return str(a)+"#"+str(b)+"#"+str(c)

def getNxts(a,b,c):
    ret = []
    if a > b:
        ret.append(makeState(a-b,b+b,c))

    if a < b:
        ret.append(makeState(a + a, b - a, c))

    if b > c:
        ret.append(makeState(a, b - c, c + c))
    if b < c:
        ret.append(makeState(a, b + b, c - b))

    if c > a:
        ret.append(makeState(a + a, b, c - a))
    if c < a:
        ret.append(makeState(a - c, b, c + c))

    return ret

def solve(a,b,c):
    state = makeState(a,b,c)
    q = deque([state])
    visited = set()
    visited.add(state)

    while q:
        cur = q.popleft()
        a,b,c = map(int, cur.split("#"))
        if a == b == c:
            return True

        for nxt in getNxts(a,b,c):
            if nxt in visited:
                continue
            visited.add(nxt)
            q.append(nxt)
    return False

if __name__ == '__main__':
    A,B,C = map(int,input().split())
    State = namedtuple('State', ['a','b','c'])
    print(1 if solve(A,B,C) else 0)