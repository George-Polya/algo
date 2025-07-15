from pprint import pprint

def solve(cur: int):
    ret = 1

    for nxt in adj[cur]:
        if visited[nxt]:
            continue
        biggers[nxt] += 1
        visited[nxt] = True
        ret += solve(nxt)

    return ret

if __name__ == "__main__":
    N = int(input())
    M = int(input())
    biggers = [0] * (N+1)
    smallers = [0] * (N+1)

    adj = [[] for _ in range(N+1)]

    for _ in range(M):
        u, v = map(int,input().split())
        adj[u].append(v)


    for i in range(1,N+1):
        visited = [False] * (N+1)
        visited[i] = True
        smallers[i] = solve(i)


    ans = []
    for i in range(1,N+1):
        ans.append(N - (smallers[i] + biggers[i]))

    print('\n'.join(map(str, ans)))
