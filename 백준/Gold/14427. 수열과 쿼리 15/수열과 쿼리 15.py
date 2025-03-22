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
        return f"idx: {self.idx}, value: {self.value}"

if __name__=='__main__':
    N = int(input())

    pq = []
    arr = [0]
    for idx, value in enumerate(map(int,input().split())):
        arr.append(value)
        heappush(pq, Pair(idx+1, value))

    # print(arr)
    M = int(input())
    ans = []
    for _ in range(M):
        line = input().split()
        cmd = int(line[0])
        if cmd == 1:
            idx, value = map(int, line[1:])
            arr[idx] = value
            heappush(pq, Pair(idx, value))
        elif cmd == 2:
            while pq:
                cur = pq[0]
                if arr[cur.idx] != cur.value:
                    heappop(pq)
                else:
                    ans.append(str(cur.idx))
                    break

    print('\n'.join(ans))