from collections import deque, defaultdict

def find(x):
    if uf[x] == x:
        return x
    uf[x] = find(uf[x])
    return uf[x]

def union(x,y):
    x = find(x)
    y = find(y)
    if x == y:
        return
    uf[x] = y

if __name__ == "__main__":
    V, E = map(int,input().split())

    adj = [[] for _ in range(V+1)]
    uf = [i for i in range(V+1)]
    hm = defaultdict(int)
    idx = 1
    for _ in range(E):
        u,v = map(int,input().split())
        
        adj[u].append(v)
        adj[v].append(u)
        
        union(u,v)


    base = find(1)
    for i in range(2,V+1):
        if base != find(i):
            print("NO")
            exit()


    odd = 0
    for i in range(1,V+1):
        if len(adj[i]) % 2 == 1:
            odd += 1

    if odd == 0 or odd == 2:
        print("YES")
    else:
        print("NO")