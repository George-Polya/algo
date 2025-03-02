import sys

input = sys.stdin.readline
MOD = 1000000007

def build(node, start, end):
    if start == end:
        tree[node] = arr[start]
        return
    mid = (start + end) // 2
    build(node * 2, start, mid)
    build(node * 2 + 1, mid + 1, end)
    tree[node] = (tree[node * 2] + tree[node * 2 + 1]) % MOD

def propagate(node, start, end):
    if lazy[node][0] == 1 and lazy[node][1] == 0:
        return
    tree[node] = (tree[node] * lazy[node][0] + lazy[node][1] * (end - start + 1)) % MOD
    if start != end:
        lazy[node * 2][0] = (lazy[node * 2][0] * lazy[node][0]) % MOD
        lazy[node * 2][1] = (lazy[node * 2][1] * lazy[node][0] + lazy[node][1]) % MOD
        lazy[node * 2 + 1][0] = (lazy[node * 2 + 1][0] * lazy[node][0]) % MOD
        lazy[node * 2 + 1][1] = (lazy[node * 2 + 1][1] * lazy[node][0] + lazy[node][1]) % MOD
    lazy[node][0] = 1
    lazy[node][1] = 0

def update_add(node, start, end, left, right, val):
    propagate(node, start, end)
    if right < start or end < left:
        return
    if left <= start and end <= right:
        lazy[node][1] = (lazy[node][1] + val) % MOD
        propagate(node, start, end)
        return
    mid = (start + end) // 2
    update_add(node * 2, start, mid, left, right, val)
    update_add(node * 2 + 1, mid + 1, end, left, right, val)
    tree[node] = (tree[node * 2] + tree[node * 2 + 1]) % MOD

def update_mul(node, start, end, left, right, val):
    propagate(node, start, end)
    if right < start or end < left:
        return
    if left <= start and end <= right:
        lazy[node][0] = (lazy[node][0] * val) % MOD
        lazy[node][1] = (lazy[node][1] * val) % MOD
        propagate(node, start, end)
        return
    mid = (start + end) // 2
    update_mul(node * 2, start, mid, left, right, val)
    update_mul(node * 2 + 1, mid + 1, end, left, right, val)
    tree[node] = (tree[node * 2] + tree[node * 2 + 1]) % MOD

def update_set(node, start, end, left, right, val):
    propagate(node, start, end)
    if right < start or end < left:
        return
    if left <= start and end <= right:
        lazy[node][0] = 0
        lazy[node][1] = val
        propagate(node, start, end)
        return
    mid = (start + end) // 2
    update_set(node * 2, start, mid, left, right, val)
    update_set(node * 2 + 1, mid + 1, end, left, right, val)
    tree[node] = (tree[node * 2] + tree[node * 2 + 1]) % MOD

def query(node, start, end, left, right):
    propagate(node, start, end)
    if right < start or end < left:
        return 0
    if left <= start and end <= right:
        return tree[node]
    mid = (start + end) // 2
    return (query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right)) % MOD

N = int(input())
arr = list(map(int, input().split()))
tree = [0] * (4 * N)
lazy = [[1, 0] for _ in range(4 * N)]
build(1, 0, N - 1)

M = int(input())
for _ in range(M):
    q = list(map(int, input().split()))
    if q[0] == 1:
        update_add(1, 0, N - 1, q[1] - 1, q[2] - 1, q[3])
    elif q[0] == 2:
        update_mul(1, 0, N - 1, q[1] - 1, q[2] - 1, q[3])
    elif q[0] == 3:
        update_set(1, 0, N - 1, q[1] - 1, q[2] - 1, q[3])
    else:
        print(query(1, 0, N - 1, q[1] - 1, q[2] - 1))