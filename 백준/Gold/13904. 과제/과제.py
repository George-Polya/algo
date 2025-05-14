from pprint import pprint
from heapq import heappush, heappop


class Assignment:
    def __init__(self, deadline, weight):
        self.deadline = deadline
        self.weight = weight

    def __lt__(self, o):
        return self.weight > o.weight

    def __repr__(self):
        return f"deadline: {self.deadline}, weight: {self.weight}"

if __name__ == "__main__":
    N = int(input())
    assignments = [[] for _ in range(1001)]
    max_deadline = 0
    for _ in range(N):
        d, w = map(int,input().split())
        assignments[d].append(Assignment(d,w))
        max_deadline = max(max_deadline, d)

    ans = 0

    pq = []

    for cur in range(max_deadline, 0, -1):
        for task in assignments[cur]:
            heappush(pq, task)

        if pq:
            best = heappop(pq)
            ans += best.weight

    print(ans)