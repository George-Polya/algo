from collections import defaultdict
from pprint import pprint
def init(arr):
    length = len(arr) - 1

    ret = defaultdict(int)
    for y in range(1, length+1):
        sum = 0
        for x in range(length):
            nxt = length if (y+x) % length == 0 else (y+x) % length
            sum = sum + arr[nxt]
            ret[sum]+=1

    return ret

if __name__ == '__main__':
    target = int(input())
    N, M = map(int,input().split())
    A = [0]
    for i in range(N):
        A.append(int(input()))
    B = [0]
    for i in range(M):
        B.append(int(input()))

    mapA = init(A)
    mapB = init(B)
    # pprint(mapA)
    # print(mapB)


    maxA = max(mapA.keys())
    maxB = max(mapB.keys())
    mapA[maxA] = 1
    mapB[maxB] = 1
    ans = 0
    for key, value in mapA.items():

        if key < target and ((target - key) in mapB):
            # print(f"key: {key}, target - key : {target-key}")
            # ans += mapA[key] * mapB[target - key] if key != maxA and (target - key) != maxB else 1
            # print(f"mapA[{key}]: {mapA[key]}, mapB[{target-key}]: {mapB[target - key]}")

            ans += mapA[key] * mapB[target-key]



    if target in mapA:
        ans += mapA[target]

    if target in mapB:
        ans += mapB[target]


    # print(maxA, maxB)
    # if(target == maxA + maxB):
    #     ans += mapA[maxA] * mapB[maxB]

    print(ans)