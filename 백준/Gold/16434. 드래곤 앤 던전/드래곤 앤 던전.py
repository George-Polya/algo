from collections import namedtuple

def decide(maxHP):
    curHP = maxHP
    power = P

    for t,a,h in states:
        if t == 1:
            atkCnt = h // power;
            if h % power != 0:
                atkCnt += 1

            defenseCnt = atkCnt - 1
            if curHP <= a * defenseCnt:
                return False
            else:
                curHP -= a * defenseCnt

        else:
            curHP = min(curHP + h, maxHP)
            power += a
    return True

if __name__ == "__main__":
    N, P = map(int,input().split())
    State = namedtuple('State', ['t','a','h'])
    states = []
    for _ in range(N):
        t,a,h = map(int,input().split())
        states.append(State(t,a,h))

    l = 1
    r = N * 1_000_000 * 1_000_000 + P
    ans = 0

    while l<=r :
        mid = (l+r) // 2

        if decide(mid):
            ans = mid
            r = mid - 1
        else:
            l = mid + 1

    print(ans)

