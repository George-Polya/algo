from collections import deque, namedtuple

def getNxts(cur):
    ret = []
    a = cur.a
    b = cur.b
    c = cur.c

    if a > b:
        ret.append(State(a-b,b+b,c))
    if a < b:
        ret.append(State(a+a,b-a,c))

    if b > c:
        ret.append(State(a,b-c,c+c))
    if b < c:
        ret.append(State(a,b+b,c-b))

    if c > a:
        ret.append(State(a+a,b,c-a))
    if c < a:
        ret.append(State(a-c,b,c+c))

    return ret


def solve(a,b,c):
    state = State(a,b,c)
    q = deque([state])
    visited = set()
    visited.add(state)

    while q:
        cur = q.popleft()
        if(cur.a == cur.b == cur.c):
            return True

        for nxt in getNxts(cur):
            if nxt in visited:
                continue
            visited.add(nxt)
            q.append(nxt)
    return False

if __name__ == '__main__':
    A,B,C = map(int,input().split())
    State = namedtuple('State', ['a','b','c'])
    print(1 if solve(A,B,C) else 0)