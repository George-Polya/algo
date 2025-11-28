from collections import namedtuple,defaultdict



if __name__ == "__main__":
    D,P = map(int,input().split())
    Pair = namedtuple('Pair', ['length', 'capacity'])

    arr = []

    for _ in range(P):
        l, c = map(int,input().split())
        arr.append(Pair(l,c))

    dp = [0 for _ in range(D+1)]
    dp[0] = float('inf')

    for i in range(P):
        for len in range(D,0,-1):
            if len - arr[i].length >= 0 and dp[len-arr[i].length] != 0:
                dp[len] = max(dp[len], min(dp[len-arr[i].length], arr[i].capacity))


    print(dp[D])