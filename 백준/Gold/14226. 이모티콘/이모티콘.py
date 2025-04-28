from collections import deque, namedtuple

if __name__ == '__main__':
    S = int(input())
    Pair = namedtuple('Pair', ['first', 'second'])

    cur = Pair(1,0)
    visited = {cur: 0}
    q = deque([cur])

    while q:
        cur = q.popleft()

        if cur.first == S:
            print(visited[cur])
            break

        if cur.first != 0:
            # 3번 연산
            nxt = Pair(cur.first - 1, cur.second)
            if nxt not in visited:
                visited[nxt] = visited[cur] + 1
                q.append(nxt)

            # 1번 연산
            nxt = Pair(cur.first, cur.first)
            if nxt not in visited:
                visited[nxt] = visited[cur] + 1
                q.append(nxt)

        if cur.second != 0:
            nxt = Pair(cur.first + cur.second, cur.second)
            if nxt not in visited:
                visited[nxt] = visited[cur] + 1
                q.append(nxt)