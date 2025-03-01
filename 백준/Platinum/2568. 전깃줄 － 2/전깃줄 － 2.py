import bisect

def solve():
    n = int(input())
    lines = []
    for _ in range(n):
        a, b = map(int, input().split())
        lines.append((a, b))

    lines.sort()  # A 전봇대 기준으로 정렬

    tails = []  # LIS의 끝 값들을 저장 (증가하는 순서)
    trace = [-1] * n  # 역추적을 위한 배열

    for i in range(n):
        a, b = lines[i]
        if not tails or b > tails[-1]:
            tails.append(b)
            trace[i] = len(tails) - 1  # LIS의 몇 번째 위치인지 기록
        else:
            idx = bisect.bisect_left(tails, b)  # b가 들어갈 위치 (lower bound)
            tails[idx] = b
            trace[i] = idx

    lis_length = len(tails)
    print(n - lis_length)  # 제거해야 할 전깃줄 개수

    removed_lines = []
    lis_index = lis_length - 1
    for i in range(n - 1, -1, -1):
        if trace[i] == lis_index:
            lis_index -= 1
        else:
            removed_lines.append(lines[i][0])

    removed_lines.sort()
    for a in removed_lines:
        print(a)

solve()