from dataclasses import dataclass

@dataclass
class Event:
    start: int
    end: int



if __name__ == "__main__":
    N = int(input())
    list = []

    for _ in range(N):
        start, end = map(int,input().split())
        list.append(Event(start, end))


    events = []

    for e in list:
        events.append((e.start, 1))
        events.append((e.end, -1))

    events.sort()

    ans = 0
    cnt = 0

    for time, type in events:
        cnt += type
        if cnt > ans:
            ans = cnt

    print(ans)

