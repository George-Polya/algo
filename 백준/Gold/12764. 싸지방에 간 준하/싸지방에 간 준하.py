import sys
from heapq import heappush, heappop
from dataclasses import dataclass
from collections import defaultdict
# sys.setrecursionlimit(1000)

@dataclass
class Data:
    end: int
    idx: int

    def __lt__(self, other):
        return self.end < other.end

@dataclass
class Person:
    start: int
    end : int

    def __lt__(self, other):
        return self.start < other.start

if __name__ == "__main__":
    N = int(input())
    arr = []

    for _ in range(N):
        start,end = map(int,input().split())
        arr.append(Person(start, end))

    arr.sort()

    idx = 1
    used = []
    availables = []
    ans = defaultdict(int)
    for cur in arr:
        while used and used[0].end <= cur.start:
            top = heappop(used)
            heappush(availables, top.idx)

        if availables:
            pos = heappop(availables)
            ans[pos] += 1
            heappush(used, Data(cur.end, pos))
        else:
            heappush(used, Data(cur.end, idx))
            ans[idx] += 1
            idx+=1

    sb = []
    print(len(ans))
    for key in sorted(ans.keys()):
        sb.append(ans[key])
    print(' '.join(map(str, sb)))