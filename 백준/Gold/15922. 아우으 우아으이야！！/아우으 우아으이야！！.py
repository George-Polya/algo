from collections import namedtuple

if __name__ == "__main__":
    N = int(input())
    Line = namedtuple('Line', ['start', 'end'])
    arr = []
    for _ in range(N):
        start, end = map(int, input().split())
        arr.append(Line(start, end))

    ans = 0
    curStart = arr[0].start
    curEnd = arr[0].end

    for i in range(1,N):
        if curEnd >= arr[i].start:
            curEnd = max(curEnd, arr[i].end)
        else:
            ans += curEnd - curStart
            curStart = arr[i].start
            curEnd = arr[i].end
        # print(f"curStart: {curStart}, curEnd: {curEnd}")
    ans += curEnd - curStart
    print(ans)