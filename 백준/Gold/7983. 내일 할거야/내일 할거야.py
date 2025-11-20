from dataclasses import dataclass
import sys

# sys.setrecursionlimit(100)
@dataclass
class Task:
    d: int
    t: int

    def __lt__(self, other):
        return self.t > other.t
if __name__ == "__main__":
    N = int(input())

    tasks = []

    for _ in range(N):
        d,t = map(int,input().split())
        tasks.append(Task(d, t))

    cur = float('inf')

    for info in sorted(tasks):
        end = min(cur, info.t)
        start = end - info.d
        cur = start

    print(cur)
