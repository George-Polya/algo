from dataclasses import dataclass

@dataclass
class Pair:
    x : int
    y : int

    def __lt__(self, other):
        return self.x < other.x

def findMaxIdx(arr: list[Pair]):
    maxIdx = -1
    y = -1


    for i in range(N):
        if y < arr[i].y:
            y = arr[i].y
            maxIdx = i
    return maxIdx
if __name__ =="__main__":

    N = int(input())
    arr = []


    for i in range(N):
        x,y = map(int,input().split())
        arr.append(Pair(x,y))

    arr.sort()

    maxIdx = findMaxIdx(arr)

    ans = 0

    stk = []
    for i in range(maxIdx):
        cur = arr[i]

        if not stk:
            stk.append(cur)
        else:
            if stk[-1].y < cur.y:
                area = (cur.x - stk[-1].x) * stk[-1].y
                # print(f"area: {area}")
                ans += area
                stk.append(cur)
    cur = arr[maxIdx]
    if stk:
        area = (cur.x - stk[-1].x) * stk[-1].y
        ans += area

    stk = []


    for i in range(N-1, maxIdx , -1):
        cur = arr[i]

        if not stk:
            stk.append(cur)
        else:
            if stk[-1].y < cur.y:
                area = abs(cur.x - stk[-1].x) * stk[-1].y
                # print(f"area: {area}")
                ans += area
                stk.append(cur)
    cur = arr[maxIdx]
    if stk:
        area = abs(cur.x - stk[-1].x) * stk[-1].y
    # print(f"area: {area}")
        ans += area


    ans += arr[maxIdx].y
    print(ans)


