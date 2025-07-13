from dataclasses import dataclass


@dataclass
class Pair:
    start: int
    end: int

    def __lt__(self, other):
        if self.start != other.start:
            return self.start < other.start

        return self.end < other.end

if __name__ == "__main__":
    N,M = map(int,input().split())
    pairs = []

    for _ in range(N):
        start, end = map(int,input().split())
        if start > end:
            pairs.append(Pair(end, start))

    if not pairs:
        print(M)
        exit()




    pairs.sort()


    start, end = pairs[0].start, pairs[0].end
    ans = 0
    for i in range(1, len(pairs)):
        nxtStart = pairs[i].start
        nxtEnd = pairs[i].end

        if nxtStart <= end:
            end = max(end, nxtEnd)
        else:
            ans += (end - start)
            start, end = nxtStart, nxtEnd


    ans += (end - start)




    print(ans * 2 + M)





