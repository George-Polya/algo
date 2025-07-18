import sys

def solve():
    # 1. 입력 받기
    # N: 변수의 개수, M: 절의 개수
    try:
        # 백준 환경에서는 아래 코드가 더 빠르게 동작합니다.
        input = sys.stdin.readline
        N, M = map(int, input().split())
        clauses = [tuple(map(int, input().split())) for _ in range(M)]
    except (IOError, ValueError):
        # 로컬 테스트 환경을 위한 예외 처리
        # 예제 입력:
        # 2 4
        # 1 2
        # -1 2
        # 1 -2
        # -1 -2
        N, M = 2, 4
        clauses = [(1, 2), (-1, 2), (1, -2), (-1, -2)]
        # 이 예제의 정답은 0입니다.

    # 2. 모든 변수 조합 탐색 (완전 탐색)
    # 0부터 2^N - 1 까지의 숫자를 순회합니다.
    # 각 숫자의 이진수 표현이 변수들의 참/거짓 조합에 해당합니다.
    # 예: N=3, i=5 (이진수 101) -> x3=True, x2=False, x1=True
    for i in range(1 << N): # 1 << N 은 2^N 과 같습니다.
        
        # 현재 조합(i)이 모든 절을 만족하는지 확인하기 위한 플래그
        is_current_case_satisfiable = True
        
        # 3. 각 조합에 대해 모든 절(clause)을 검사
        for c in clauses:
            a, b = c[0], c[1]
            
            # 첫 번째 리터럴(a)의 참/거짓 값 계산
            var_a_idx = abs(a) - 1 # 변수 인덱스 (0부터 시작하도록 조정)
            # (i >> var_a_idx) & 1: i의 var_a_idx 번째 비트가 1인지 0인지 확인
            val_a = (i >> var_a_idx) & 1 
            if a < 0: # 변수가 음수이면 (NOT 연산)
                val_a = 1 - val_a # 값을 뒤집음 (0 -> 1, 1 -> 0)

            # 두 번째 리터럴(b)의 참/거짓 값 계산
            var_b_idx = abs(b) - 1
            val_b = (i >> var_b_idx) & 1
            if b < 0:
                val_b = 1 - val_b

            # 절(clause) (a OR b)의 결과 계산
            # val_a 와 val_b 둘 중 하나라도 참(1)이면 절은 참(1)이 됩니다.
            # 둘 다 거짓(0)일 때만 절이 거짓(0)이 됩니다.
            if (val_a | val_b) == 0: # |는 비트 OR 연산입니다.
                # 현재 조합(i)은 이 절을 만족시키지 못하므로 실패.
                is_current_case_satisfiable = False
                break # 더 이상 다른 절을 볼 필요 없이 다음 조합으로 넘어감

        # 4. 결과 확인
        # 만약 한 조합이 모든 절을 통과했다면 (플래그가 여전히 True라면)
        if is_current_case_satisfiable:
            # 만족하는 경우를 찾았으므로 1을 출력하고 즉시 종료
            print(1)
            return

    # 5. 모든 조합을 시도했지만 실패한 경우
    # for 루프가 중단 없이 모두 실행되었다면, 만족하는 경우가 없는 것.
    print(0)

solve()