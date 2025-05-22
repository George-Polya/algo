from bisect import bisect_left, bisect_right

class Switch:
    def __init__(self, id, start, end):
        self.id = id
        self.start = start
        self.end = end

    def __lt__(self, o):
        return self.start < o.start

    def __repr__(self):
        return f"[switch: {self.id}, start: {self.start}, end: {self.end}]"


if __name__ == "__main__":
    N = int(input())
    switches = [0] + list(map(int,input().split()))
    lamps = [0] + list(map(int,input().split()))
    lamp2idx = {lamps[i] : i for i in range(N+1)}
    idx2switch = {i : switches[i] for i in range(N+1)}
    arr = [Switch(0, 0, N+1)]
    for i in range(1,N+1):
        switch = switches[i]
        # print(f"switch: {switch}, switch_idx:{i}, lamp_idx: {lamp2idx[switch]}")
        arr.append(Switch(switch, i, lamp2idx[switch]))

    arr.sort()

    trace = [-1 for _ in range(N + 1)]
    length = 0
    dp = []

    dp.append(arr[1].end)
    trace[1] = length
    length += 1


    for i in range(2,N+1):
        if arr[i].end > dp[-1]:
            dp.append(arr[i].end)
            trace[i] = length
            length += 1

        else:
            idx = bisect_left(dp, arr[i].end)
            dp[idx] = arr[i].end
            trace[i] = idx


    print(length)
    idx = length - 1
    ans = []
    for i in range(N,0,-1):
        if trace[i] == idx:
            ans.append(arr[i].id)
            idx -= 1
            if idx < 0:
                break
                
    if length > 0:
        print(' '.join(map(str, sorted(ans))))