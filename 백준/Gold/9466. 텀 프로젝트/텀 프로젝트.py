import sys

# 파이썬의 기본 재귀 깊이 제한은 1000 정도입니다.
# N이 최대 100,000이므로, 재귀 깊이 제한을 충분히 늘려주어야 런타임 에러(RecursionError)가 발생하지 않습니다.
sys.setrecursionlimit(110000)

def dfs(cur):
    global cnt
    nxt = arr[cur]

    if not visited[nxt]:
        visited[nxt] = True
        dfs(nxt)
    else:
        if not finished[nxt]:
            idx = nxt
            while True:
                cnt += 1
                idx = arr[idx]
                if idx == nxt:
                    break
                    
    finished[cur] = True



cnt = 0
if __name__ == "__main__":
    T = int(input())
    ans = []
    for _ in range(T):
        # print('-' * 10)
        N = int(input())
        arr = [0] + list(map(int,input().split()))
        visited = [False for _ in range(N+1)]
        finished = [False for _ in range(N+1)]
        cnt = 0
        for i in range(1, N + 1):
            if not visited[i]:
                dfs(i)

        # print(visited)
        ans.append(str(N - cnt))

    print('\n'.join(ans))
