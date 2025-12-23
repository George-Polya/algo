from collections import namedtuple

if __name__ == "__main__":
    N = int(input())
    arr = [0]+ list(map(int,input().split()))
    Building = namedtuple('Building', ['idx', 'height'])

    stk = []
    INF = float('inf')
    cnt = [0 for _ in range(N + 1)]
    near = [[INF, 0] for _ in range(N+1)]

    for i in range(1, N+1):
        while stk and stk[-1].height <= arr[i]:
            stk.pop()

        cnt[i] += len(stk)

        if stk:
            top = stk[-1]

            gap = abs(top.idx - i)

            if gap < near[i][0]:
                near[i][0] = gap
                near[i][1] = top.idx
            elif gap == near[i][0] and top.idx < near[i][1]:
                near[i][1] = top.idx

        stk.append(Building(i, arr[i]))




    stk = []

    for i in range(N,0,-1):
        while stk and stk[-1].height <= arr[i]:
            stk.pop()

        cnt[i] += len(stk)
        if stk:
            top = stk[-1]

            gap = abs(top.idx - i)

            if gap < near[i][0]:
                near[i][0] = gap
                near[i][1] = top.idx
            elif gap == near[i][0] and top.idx < near[i][1]:
                near[i][1] = top.idx

        stk.append(Building(i, arr[i]))

    ans = []

    for i in range(1,N+1):
        if cnt[i] == 0:
            ans.append(f"{0}")
        else:
            ans.append(f"{cnt[i]} {near[i][1]}")

    print('\n'.join(ans))
