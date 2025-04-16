from heapq import heappop, heappush, heapify

if __name__ == "__main__":
    T = int(input())
    ans = []
    MOD = 1000000007
    for _ in range(T):
        N = int(input())
        pq = list(map(int,input().split()))
        heapify(pq)
        total = 1

        if N == 1:
            total = 1
        else:
            while len(pq) > 1:
                first = heappop(pq)
                second = heappop(pq)
                nxt = first * second
                cost = nxt % MOD
                total = (total * nxt) % MOD
                heappush(pq, nxt)

        ans.append(total)

    print('\n'.join(map(str, ans)))