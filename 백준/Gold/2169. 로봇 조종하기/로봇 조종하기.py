n, m = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]

dp = [[0] * m for _ in range(n)]
dp[0][0] = board[0][0]

# 첫 번째 행 초기화
for j in range(1, m):
    dp[0][j] = dp[0][j - 1] + board[0][j]

# 나머지 행 처리
for i in range(1, n):
    left = [0] * m
    right = [0] * m

    # 왼쪽에서 오는 경우
    left[0] = dp[i - 1][0] + board[i][0]
    for j in range(1, m):
        left[j] = max(left[j - 1], dp[i - 1][j]) + board[i][j]

    # 오른쪽에서 오는 경우
    right[m - 1] = dp[i - 1][m - 1] + board[i][m - 1]
    for j in range(m - 2, -1, -1):
        right[j] = max(right[j + 1], dp[i - 1][j]) + board[i][j]

    # dp 테이블 갱신
    for j in range(m):
        dp[i][j] = max(left[j], right[j])

print(dp[n - 1][m - 1])