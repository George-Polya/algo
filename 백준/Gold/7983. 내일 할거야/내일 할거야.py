from collections import namedtuple
from dataclasses import dataclass

@dataclass
class Pair:
    d: int
    t: int

    def __lt__(self, other):
        return self.t > other.t

    def __repr__(self):
        return f"d:{self.d} t:{self.t}"

if __name__ == "__main__":

    N = int(input())
    arr = []

    for _ in range(N):
        d,t = map(int,input().split())
        arr.append(Pair(d,t))

    arr.sort()
    # print(arr)

    cur = start = int(1e9) + 1

    for p in arr:
        cur = min(cur, p.t)
        if p.t > start:
            cur = min(cur, start)

        start = cur - p.d

    print(start)
