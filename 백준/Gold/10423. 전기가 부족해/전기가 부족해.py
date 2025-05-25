class Edge:
    def __init__(self, a,b,cost):
        self.a = a
        self.b = b
        self.cost = cost

    def __lt__(self, other):
        return self.cost < other.cost

    def __repr__(self):
        return f"[ ({self.a},{self.b}) : {self.cost} ]"

def find(x):
    if x == uf[x]:
        return x

    uf[x] = find(uf[x])
    return uf[x]

if __name__ == "__main__":
    N,M,K = map(int,input().split())
    edges = []
    installed = set(map(int,input().split()))
    uf = [ i for i in range(N+1)]

    for _ in range(M):
        u,v,w = map(int,input().split())
        edges.append(Edge(u,v,w))

    edges.sort()

    ans = 0

    for edge in edges:
        # print("------")
        # print(edge)
        a = edge.a
        b = edge.b
        cost = edge.cost

        A = find(a)
        B = find(b)

        if A == B:
            continue

        if (A in installed) and (B in installed):
            continue

        if A not in installed:
            uf[A] = B
            ans += cost
        else:
            uf[B] = A
            ans += cost
    # print(uf)
    print(ans)