import sys

INT_MIN = -1 # 매우 작은 값 (Java의 Integer.MIN_VALUE / 2와 유사)


def solve():
    # 입력 개수 T
    T = int(sys.stdin.readline().strip())

    results = []

    for tc in range(1, T + 1):
        # N, W1, W2 입력 받기
        N, W1, W2 = map(int, sys.stdin.readline().split())

        # 선물의 무게 입력 받기
        weights = [0] + list(map(int, sys.stdin.readline().split()))  # 1-based index 유지
        # 선물의 가치 입력 받기
        values = [0] + list(map(int, sys.stdin.readline().split()))  # 1-based index 유지

        # DP 테이블 초기화
        dp = [[INT_MIN] * (W2 + 1) for _ in range(W1 + 1)]
        dp[0][0] = 0  # 초기 상태

        # 각 선물을 고려하면서 DP 테이블 갱신
        for i in range(1, N + 1):
            wt, val = weights[i], values[i]
            for w1 in range(W1, -1, -1):
                for w2 in range(W2, -1, -1):
                    if w1 - wt >= 0 and dp[w1 - wt][w2] != INT_MIN:
                        dp[w1][w2] = max(dp[w1][w2], dp[w1 - wt][w2] + val)
                    if w2 - wt >= 0 and dp[w1][w2 - wt] != INT_MIN:
                        dp[w1][w2] = max(dp[w1][w2], dp[w1][w2 - wt] + val)

        # 최대 값 찾기
        max_value = dp[W1][W2]
        results.append(f"Problem {tc}: {max_value}")

    # 결과 출력
    sys.stdout.write("\n".join(results) + "\n")


if __name__ == "__main__":
    solve()