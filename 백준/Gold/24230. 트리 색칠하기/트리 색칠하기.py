from collections import namedtuple

if __name__ == "__main__":
    N = int(input())
    arr = [0] + list(map(int,input().split()))
    adj = [[] for _ in range(N+1)]

    for _ in range(N-1):
        a,b = map(int,input().split())
        adj[a].append(b)
        adj[b].append(a)

    visited = [False for _ in range(N+1)]
    State = namedtuple('State', ['node', 'color'])


    stk = [State(1,0)]
    visited[1] = True
    ans = 0
    while stk:
        cur = stk.pop()

        if arr[cur.node] != cur.color:
            ans+=1

        for nxt in adj[cur.node]:
            if visited[nxt]:
                continue
            visited[nxt] = True
            stk.append(State(nxt, arr[cur.node]))
    print(ans)