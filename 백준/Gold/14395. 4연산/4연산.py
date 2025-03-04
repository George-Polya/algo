from collections import deque

def OOB(x):
    return x<=0 or x > MAX_R

def getNxt(cur, d):
    if d == 0:
        return cur * cur, '*'
    elif d == 1:
        return cur + cur, '+'
    elif d == 2:
        return 0, '-'
    elif d == 3 and cur != 0:
        return 1, '/'

    return -1, None

if __name__ == '__main__':

    S, T = map(int ,input().split())

    MAX_R = 10**9
    dist = dict()

    dist[S] = ''
    q = deque([S])

    while q:
        cur = q.popleft()

        if T in dist:
            print( 0 if dist[T] == '' else dist[T] )
            exit()

        for DIR in range(4):
            nxt, op = getNxt(cur, DIR)
            # print(f"nxt: {nxt}, op: {op}")
            if OOB(nxt) or (nxt in dist):
                continue

            dist[nxt] = dist[cur] + op
            # print(f"dist[{nxt}]: {dist[nxt]}, op: {op}\n")
            q.append(nxt)

    print(-1)