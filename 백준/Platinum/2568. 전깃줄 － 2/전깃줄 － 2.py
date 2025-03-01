import bisect
from pprint import pprint
from copy import deepcopy
class Line:
    def __init__(self, id, a,b):
        self.id = id
        self.a = a
        self.b = b

    def __repr__(self):
        return f"{self.id}: [{self.a}, {self.b}]"

    def __lt__(self, o):
        return self.a < o.a

def lower_bound(arr, target):
    l = 0
    r = len(arr) - 1
    ret = 0
    while l<=r :
        mid = (l + r) // 2
        if target <= arr[mid].b:
            r = mid - 1
            ret = mid
        else:
            l = mid + 1

    return ret
if __name__ == '__main__':
    N = int(input())
    lines = [Line(-1,-1,-1)]
    for i in range(1,N+1):
        a,b = map(int,input().split())
        lines.append(Line(i, a,b))

    lines.sort()
    # print(lines)
    # path = [0 for _ in range(N + 1)]

    dp = [lines[1]]
    trace = [-1] * (N+1)
    trace[1] = 0
    # print(dp)
    # print('----')
    for i in range(2, N+1):
        if lines[i].b > dp[-1].b:
            dp.append(lines[i])
            trace[i] = len(dp) - 1
        else:
            idx = lower_bound(dp, lines[i].b)
            # print("idx: ", cur)

            dp[idx] = lines[i]
            trace[i] = idx
            # path.append(dp)


    print(N - len(dp))
    # print(trace)
    ans = []
    length = len(dp) - 1

    for i in range(N, 0, -1):
        if trace[i] == length:
            length -= 1
        else:
            ans.append(lines[i].a)
    ans.sort()
    print('\n'.join(map(str,ans)))