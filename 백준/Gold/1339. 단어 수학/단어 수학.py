from collections import defaultdict
from heapq import heappop, heappush

def addWeight(string):
    length = len(string)

    for i in range(length):
        ch = string[i]
        pos = length - i
        # print(10 ** pos)
        weights[ch] += 10 ** pos


weights = defaultdict(int)
class Pair:
    def __init__(self, ch, value):
        self.ch = ch
        self.value = value

    def __lt__(self, o):
        return self.value > o.value

    def __repr__(self):
        return f"ch: {self.ch}, value: {self.value}"

def convert(string):
    ret = 0
    for i in range(len(string)):
        ch = string[i]
        ret = ret * 10 + weights[ch]

    return ret

if __name__ == '__main__':
    N = int(input())
    arr = []
    for i in range(N):
        arr.append(input())
        addWeight(arr[i])

    pq = []
    for key, value in weights.items():

        heappush(pq, Pair(key,value))

    num = 9
    while pq:
        cur = heappop(pq)
        weights[cur.ch] = num
        num -= 1

    

    ans = 0
    for i in range(N):
        ans += convert(arr[i])

    print(ans)