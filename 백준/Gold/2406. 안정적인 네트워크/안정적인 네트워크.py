from dataclasses import dataclass

def printBoard(board):
    for y in range(1,N+1):
        for x in range(1, N+1):
            print(f"{board[y][x]:>5}", end='')
        print()

@dataclass
class Edge:
    u : int
    v : int
    cost: int

    def __lt__(self, other):
        return self.cost < other.cost

def find(x : int):
    if x == uf[x]:
        return x
    uf[x] = find(uf[x])
    return uf[x]

def union(x :int, y : int):
    x = find(x)
    y = find(y)

    if x == y:
        return False
    uf[y] = x
    return True



if __name__ == "__main__":
    N,M = map(int,input().split())

    adj = [[float('inf') for _ in range(N+1)] for _ in range(N+1)]
    uf = [i for i in range(N + 1)]

    for _ in range(M):
        a,b = map(int,input().split())
        union(a,b)


    for y in range(1,N+1):
        arr = list(map(int,input().split()))
        for x in range(1,N+1):
            adj[y][x] = min(adj[y][x], arr[x-1])

    # printBoard(adj)

    edges = []
    for y in range(2,N+1):
        for x in range(y+1, N+1):
            edges.append(Edge(y,x,adj[y][x]))


    edges.sort()
    cnt = 0
    cost = 0
    ans = []
    for edge in edges:
        if(union(edge.u, edge.v)):
            cost += edge.cost
            ans.append((edge.u, edge.v))
            cnt+=1

        if cnt == N - 1:
            break

    print(cost, len(ans))

    for u,v in ans:
        print(u, v)



