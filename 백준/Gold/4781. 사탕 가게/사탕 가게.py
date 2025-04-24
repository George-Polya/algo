import sys

# 빠른 입력을 위해 sys.stdin.readline 사용
input = sys.stdin.readline

results = [] # 각 테스트 케이스 결과를 저장할 리스트

while True:
    line = input().split()
    n = int(line[0])
    # 입력이 없거나 빈 줄이면 종료 (EOF 처리)
    if not line or n == 0:
        # m 값도 확인하여 0.00인지 보는 것이 더 안전할 수 있으나,
        # 문제 조건상 n=0이면 m=0.00이므로 n만 확인
        try:
            m_double = float(line[1])
            # 부동소수점 비교는 오차 감안 (epsilon 사용)
            # epsilon = 1e-9
            # if n == 0 and abs(m_double - 0.00) < epsilon:
            #     break
            # 문제 조건상 n=0이면 m=0.00이므로 아래 break로 충분
            if n == 0:
                 break
        except (IndexError, ValueError): # n만 입력되거나 잘못된 입력 처리
             break
        # 정상적인 종료 조건이 아니면 계속 진행 (혹은 에러 처리)
        # 여기서는 n=0이면 정상 종료로 간주

    m_double = float(line[1])

    # 1. 예산을 센트 단위의 정수로 변환
    m_cents = int(m_double * 100 + 0.5)

    # 2. DP 배열 선언 및 초기화
    # dp[j] = 예산 j 센트로 얻을 수 있는 최대 칼로리
    dp = [0] * (m_cents + 1) # 크기는 m_cents + 1 (0부터 m_cents까지)

    candies = [] # 사탕 정보를 저장할 리스트
    for _ in range(n):
        c_line = input().split()
        calories = int(c_line[0])
        price_double = float(c_line[1])
        # 가격을 센트 단위의 정수로 변환
        price_cents = int(price_double * 100 + 0.5)
        candies.append((calories, price_cents))

    # 3 & 4. DP 테이블 갱신 (무한 배낭 문제)
    for calories, price_cents in candies:
        # 가격이 0인 사탕은 예산 내에서 무한히 먹을 수 있으나,
        # 문제 제약상 가격은 0.01 이상일 것으로 예상됨.
        # 만약 가격이 0이고 칼로리가 양수면 답은 무한대가 될 수 있음.
        # 하지만 이 문제에서는 그런 경우는 없는 것으로 보임.
        if price_cents == 0 and calories > 0:
             # 특별 처리 필요 (문제 조건 확인 필요)
             # 여기서는 가격이 항상 0보다 크다고 가정
             continue

        # j는 현재 사탕의 가격(센트)부터 시작
        # price_cents가 0인 경우 range 시작점에 주의해야 하나, 위에서 처리함
        for j in range(price_cents, m_cents + 1):
            # 점화식 적용
            dp[j] = max(dp[j], dp[j - price_cents] + calories)

    # 5. 최종 결과 저장
    results.append(dp[m_cents])

# 결과 한 번에 출력
for result in results:
    print(result)