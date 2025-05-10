from collections import deque, namedtuple
import math

if __name__ == '__main__':
    N, T, G = map(int ,input().split())

    visited = [False] * 100000
    visited[N] = True
    Pair = namedtuple('Pair', ['value', 'cnt'])
    q = deque([Pair(N,0)])

    while q:
        cur : Pair = q.popleft()
        if cur.value == G:
            print(cur.cnt)
            exit(0)

        if cur.cnt == T:
            continue

        nxt = cur.value + 1
        if nxt <= 99999 and not visited[nxt]:
            visited[nxt] = True
            q.append(Pair(nxt, cur.cnt + 1))

        if 0 < cur.value * 2 <= 99999:
            nxt = cur.value * 2 - (10 ** int(math.log10(cur.value * 2)))
            if not visited[nxt]:
                visited[nxt] = True
                q.append(Pair(nxt, cur.cnt + 1))

    print("ANG")