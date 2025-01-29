from collections import namedtuple

def find(x):
    if x == uf[x]:
        return x

    uf[x] = find(uf[x])
    return uf[x]

def union(x, y):
    x = find(x)
    y = find(y)

    if x == y:
        return

    uf[x] = y


if __name__ == '__main__':
    T = int(input())
    sb = []
    for tc in range(1,T+1):
        # print("------")
        # print("tc: ", tc)
        N = int(input())
        K = int(input())
        uf = [ i for i in range(N)]


        edges = []
        Edge = namedtuple('edge', ['a', 'b'])
        for _ in range(K):
            a,b = map(int, input().split())
            union(a,b)



        # print(uf)

        M = int(input())
        ans = []
        for _ in range(M):
            a, b = map(int,input().split())
            a = find(a)
            b = find(b)
            ans.append(1 if a == b else 0)


        # print()
        # sb.append(f"Scenario {tc}:\n"+'\n'.join(map(str,ans)))
        # print(f"Scenario {tc}:\n"+'\n'.join(map(str,ans)))
        ans = f"Scenario {tc}:\n"+('\n'.join(map(str,ans)) + '\n')
        sb.append(ans)
    print('\n'.join(sb))