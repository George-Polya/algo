import sys
import heapq
from collections import defaultdict

def main():
    n, p, k = map(int, input().split())

    adj = defaultdict(list)

    for _ in range(p):
        from_, to, cost = map(int, input().split())
        adj[from_].append((to, cost))
        adj[to].append((from_, cost))

    left, right = 0, 1_000_000
    ans = -1

    while left <= right:
        mid = (left + right) // 2
        if decide(mid, adj, n, k):
            ans = mid
            right = mid - 1
        else:
            left = mid + 1

    print(ans)

def decide(x, adj, n, k):
    INF = float('inf')
    dist = [INF] * (n + 1)

    pq = []
    heapq.heappush(pq, (0, 1)) # (cost, node)
    dist[1] = 0

    while pq:
        cur_cost, cur_idx = heapq.heappop(pq)

        if dist[cur_idx] < cur_cost:
            continue

        for nxt_idx, nxt_cost in adj[cur_idx]:
            temp = dist[cur_idx] + (1 if nxt_cost > x else 0)
            if dist[nxt_idx] > temp:
                dist[nxt_idx] = temp
                heapq.heappush(pq, (temp, nxt_idx))

    return dist[n] <= k

if __name__ == "__main__":
    main()