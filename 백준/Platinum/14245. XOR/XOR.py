import math


class SegTree:
    def __init__(self, n):
        h = int(math.ceil(math.log2(n)))
        size = 1 << (h + 1)
        self.tree = [0] * size
        self.lazy = [0] * size
        self.isLazy = [False] * size
        self.build(1, 0, n - 1)

    def build(self, cur, left, right):
        if left == right:
            self.tree[cur] = arr[left]
            return

        mid = (left + right) // 2
        self.build(cur * 2, left, mid)
        self.build(cur * 2 + 1, mid + 1, right)
        self.tree[cur] = self.tree[cur * 2] ^ self.tree[cur * 2 + 1]

    def update(self, cur, left, right, value, qLeft, qRight):
        self.propagate(cur, left, right)

        if qRight < left or right < qLeft:
            return

        if qLeft <= left and right <= qRight:
            self.isLazy[cur] = True
            self.lazy[cur] ^= value
            self.propagate(cur, left, right)
            return

        mid = (left + right) // 2
        self.update(cur * 2, left, mid ,value, qLeft, qRight)
        self.update(cur * 2 + 1, mid + 1,right, value, qLeft , qRight)

        self.tree[cur] = self.tree[cur * 2] ^ self.tree[cur * 2 + 1]

    def query(self, cur, left, right, qLeft, qRight):
        self.propagate(cur,left,right)

        if qRight < left or right < qLeft:
            return 0

        if qLeft <= left and right <= qRight:
            return self.tree[cur]

        mid = (left + right) // 2
        leftValue = self.query(cur * 2, left, mid ,qLeft, qRight)
        rightValue = self.query(cur *2 + 1, mid + 1,right , qLeft, qRight)

        return leftValue ^ rightValue

    def propagate(self,cur,left, right):
        if not self.isLazy[cur]:
            return

        if left != right:
            self.lazy[cur * 2] ^= self.lazy[cur]
            self.lazy[cur * 2 + 1] ^= self.lazy[cur]
            self.isLazy[cur * 2] = self.isLazy[cur * 2 + 1] = True

        len = right - left + 1

        if len % 2 != 0:
            self.tree[cur] ^= self.lazy[cur]

        self.isLazy[cur] = False
        self.lazy[cur] = 0



if __name__ == '__main__':
    N = int(input())
    arr = list(map(int,input().split()))

    seg = SegTree(N)

    M = int(input())
    ans = []
    for _ in range(M):
        line = list(map(int,input().split()))
        if line[0] == 1:
            qLeft,qRight,value = line[1:]
            seg.update(1, 0, N - 1, value, qLeft, qRight)
        else:
            qLeft = line[1]
            ans.append(seg.query(1, 0, N-1,qLeft,qLeft))

    print('\n'.join(map(str,ans)))