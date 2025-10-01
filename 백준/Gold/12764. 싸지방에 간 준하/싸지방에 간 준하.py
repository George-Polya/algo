from dataclasses import dataclass
from heapq import heappush, heappop
from typing import List

@dataclass
class Person:
    start: int
    end : int


    def __lt__(self, other):
        return self.start < other.start

@dataclass
class State:
    end : int
    pos: int

    def __lt__(self, other):
        return self.end < other.end

if __name__ == "__main__":
    N = int(input())
    people = []
    for _ in range(N):
        start, end = map(int,input().split())
        people.append(Person(start, end))

    people.sort()

    using_computers : List[State] = []
    available_computers : List[int] = [ ]
    positions = [0 for _ in range(N)]

    cnt = 0
    for p in people:
        while using_computers:
            if p.start >= using_computers[0].end:
                heappush(available_computers, heappop(using_computers).pos)
            else:
                break

        if not available_computers:
            heappush(using_computers, State(p.end, cnt))
            positions[cnt] += 1
            cnt+=1
        else:
            idx = heappop(available_computers)
            positions[idx] += 1
            heappush(using_computers, State(p.end, idx))



    print(cnt)
    print(' '.join(map(str, positions[:cnt])))



