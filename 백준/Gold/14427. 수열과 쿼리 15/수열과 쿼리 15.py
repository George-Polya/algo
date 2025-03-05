from heapq import heappush, heappop

class Pair:
    def __init__(self, idx, value):
        self.idx = idx
        self.value = value

    def __lt__(self, o):
        if self.value != o.value:
            return self.value < o.value
        return self.idx < o.idx

    def __repr__(self):
        return f"idx: {self.idx}, value:{self.value}"

if __name__ == '__main__':
    N = int(input())
    arr = [0] * (N+1)
    pq = []
    _list = list(map(int,input().split()))
    for i in range(1,N+1):
        arr[i] = _list[i-1]
        heappush(pq, Pair(i, arr[i]))

    M = int(input())
    ans = []
    for i in range(M):
        cmds = list(map(int,input().split()))

        if cmds[0] == 1:
            idx, value = cmds[1:]
            arr[idx] = value
            heappush(pq, Pair(idx, value))
        else:
            while pq:
                cur = heappop(pq)
                if arr[cur.idx] == cur.value:
                    ans.append(cur.idx)
                    heappush(pq, cur)
                    break

    print('\n'.join(map(str, ans)))