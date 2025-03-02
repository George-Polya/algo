import sys

def init(node, start, end):
    """세그먼트 트리 초기화"""
    if start == end:
        tree[node] = arr[start]
        return tree[node]

    mid = (start + end) // 2
    tree[node] = min(init(node * 2, start, mid), init(node * 2 + 1, mid + 1, end))
    return tree[node]


def query(node, start, end, left, right):
    """최솟값 쿼리"""
    if left > end or right < start:
        return float('inf')

    if left <= start and end <= right:
        return tree[node]

    mid = (start + end) // 2
    return min(query(node * 2, start, mid, left, right),
               query(node * 2 + 1, mid + 1, end, left, right))


def update(node, start, end, index, diff):
    """값 업데이트"""
    if index < start or index > end:
        return

    if start == end:  # 리프 노드
        tree[node] = diff # diff가 아닌 업데이트된 값 자체를 넣어야함
        return

    mid = (start + end) // 2
    update(node * 2, start, mid, index, diff)
    update(node * 2 + 1, mid + 1, end, index, diff)
    tree[node] = min(tree[node * 2], tree[node * 2 + 1]) # 부모 노드 업데이트


if __name__ == "__main__":
    input = sys.stdin.readline
    N = int(input())
    arr = list(map(int, input().split()))

    # 트리 크기 계산 (4*N으로 해도 충분)
    h = 1
    while (1 << h) < N:
        h += 1
    tree_size = 1 << (h + 1)
    tree = [0] * tree_size

    init(1, 0, N - 1)  # 세그먼트 트리 초기화

    M = int(input())
    for _ in range(M):
        a, b, c = map(int, input().split())
        if a == 1:
            # b번째 수를 c로 바꾼다
            # arr[b - 1] = c  # 배열 업데이트는 필요 없음 (세그먼트 트리에서만)
            update(1, 0, N - 1, b - 1, c)
        else:
            # b번째 수부터 c번째 수까지의 최솟값 출력
            print(query(1, 0, N - 1, b - 1, c - 1))