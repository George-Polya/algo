import math


class Pair:
    def __init__(self, idx, value):
        self.idx = idx
        self.value = value

    def __lt__(self, other):
        if self.value != other.value:
            return self.value < other.value
        return self.idx < other.idx

    def __repr__(self):
        return f"idx: {self.idx}, value: {self.value}"



class SegTree:
    def __init__(self, arr):
        n = len(arr) - 1
        h = int(math.ceil(math.log2(n)))
        size = 1 << (h+1)
        self.tree = [NO_PAIR] * size
        self.build(1,1, n)


    def build(self, cur, left, right):
        if left == right:
            # print(left)
            self.tree[cur] = Pair(left, arr[left])
            return

        mid = (left + right) // 2
        self.build(cur * 2 ,left, mid)
        self.build(cur * 2 + 1, mid + 1, right)

        leftValue = self.tree[cur*2]
        rightValue = self.tree[cur * 2 + 1]

        if leftValue.value< rightValue.value or (leftValue.value == rightValue.value and leftValue.idx < rightValue.idx):
            self.tree[cur] = leftValue
        else:
            self.tree[cur] = rightValue



    def update(self, cur, left, right, idx, value):
        if idx < left or right < idx:
            return

        if left == right:
            self.tree[cur] = Pair(idx, value)
            return

        mid = (left + right ) // 2
        self.update(cur * 2, left, mid, idx, value)
        self.update(cur * 2 + 1, mid + 1, right, idx, value)

        leftValue = self.tree[cur * 2]
        rightValue = self.tree[cur * 2 + 1]

        if leftValue.value < rightValue.value or (leftValue.value == rightValue.value and leftValue.idx < rightValue.idx):
            self.tree[cur] = leftValue
        else:
            self.tree[cur] = rightValue


    def query(self, cur, left, right, qLeft, qRight):
        if qRight < left or right < qLeft:
            return NO_PAIR

        if qLeft <= left and right <= qRight:
            return self.tree[cur]

        mid = (left + right )// 2
        leftValue = self.query(cur * 2, left, mid , qLeft, qRight)
        rightValue = self.query(cur * 2 + 1, mid + 1, right, qLeft, qRight)
        if leftValue.value < rightValue.value or (leftValue.value == rightValue.value and leftValue.idx < rightValue.idx):
            return leftValue
        else:
            return rightValue
if __name__=='__main__':
    N = int(input())
    arr = [0] + list(map(int,input().split()))
    NO_PAIR = Pair(N + 1, 10**9 + 1)
    segTree = SegTree(arr)

    M = int(input())
    ans = []
    for _ in range(M):

        cmd, i, j = map(int,input().split())
        if cmd == 1:
            segTree.update(1,1,N,i, j)

        elif cmd == 2:
            ans.append(segTree.query(1,1,N, i, j).idx)

    print('\n'.join(map(str, ans)))