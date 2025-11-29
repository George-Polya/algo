from dataclasses import dataclass

@dataclass
class Edge:
    a:int
    b:int
    cost: int

    def __lt__(self, other):
        return self.cost > other.cost

    def __str__(self):
        return f"a: {self.a} b: {self.b} cost: {self.cost}"


def find(uf, x):
    if x == uf[x]:
        return x

    uf[x] = find(uf, uf[x])
    return uf[x]

def union(uf, a,b):
    A = find(uf,a)
    B = find(uf,b)

    if A == B:
        return False

    uf[A] = B
    return True

def MST(edges, reverse):
    # print("-" * 10)
    uf = [i for i in range(N + 1)]

    edges.sort(reverse=reverse)
    # print(edges)
    cnt = 0
    k = 0

    for edge in edges:
        if union(uf, edge.a, edge.b):
            # print(f"edge: {edge}")
            cnt += 1
            k += edge.cost
        if cnt == N:
            break

    return k * k

if __name__ == "__main__":
    N,M = map(int,input().split())
    edges = []

    for _ in range(M+1):
        a,b,c = map(int,input().split())
        edges.append(Edge(a,b, 1-c))

    # print(f"최악의 경우: {MST(edges, False)}")
    # print(f"최적의 경우: {MST(edges, True)}")

    print(abs(MST(edges, True) - MST(edges, False)))