import sys
from collections import deque

# 빠른 입력을 위해 sys.stdin.readline 사용
input = sys.stdin.readline

# N: 원판 개수, M: 원판의 숫자 개수, Q: 회전 횟수
n, m, q = map(int, input().split())

# 1-based 인덱싱을 위해 N+1, M+1 크기로 보드 초기화
board = [[0] * (m + 1)]
for _ in range(n):
    # 각 원판의 정보를 board에 추가
    board.append([0] + list(map(int, input().split())))

# 시뮬레이션 함수
def simulate(x, d, k):
    """원판 회전 및 숫자 제거/조정 작업을 수행합니다."""
    rotate(x, d, k)
    
    # 인접하면서 같은 숫자를 제거하고, 제거된 숫자가 있는지 여부를 반환
    was_removed = remove_adjacent()
    
    # 제거된 숫자가 없다면, 전체 평균에 따라 숫자를 조정
    if not was_removed:
        normalize()

def rotate(mod, d, k):
    """mod의 배수인 원판을 d 방향으로 k칸 회전합니다."""
    for i in range(1, n + 1):
        if i % mod == 0:
            # deque를 사용하여 효율적으로 회전
            # 0번 인덱스는 더미 값이므로 슬라이싱으로 실제 데이터만 사용
            numbers = deque(board[i][1:])
            
            if d == 0:  # 시계 방향
                numbers.rotate(k)
            else:  # 반시계 방향
                numbers.rotate(-k)
            
            # 회전된 결과를 다시 원래 board에 저장
            board[i] = [0] + list(numbers)

def remove_adjacent():
    """인접하면서 같은 값을 갖는 숫자를 찾아 제거합니다."""
    # 제거할 좌표를 중복 없이 저장하기 위해 set 사용
    to_remove = set()

    for r in range(1, n + 1):
        for c in range(1, m + 1):
            if board[r][c] == 0:
                continue
            
            # 1. 같은 원판 내에서 인접한 경우 (좌우)
            # 열 인덱스는 원형으로 연결됨 (c=m 다음은 c=1)
            next_c = c + 1 if c < m else 1
            if board[r][c] == board[r][next_c]:
                to_remove.add((r, c))
                to_remove.add((r, next_c))
            
            # 2. 다른 원판과 인접한 경우 (상하)
            if r < n and board[r][c] == board[r + 1][c]:
                to_remove.add((r, c))
                to_remove.add((r + 1, c))

    # 제거할 숫자가 없으면 False 반환
    if not to_remove:
        return False

    # 표시된 위치의 숫자를 0으로 변경
    for r, c in to_remove:
        board[r][c] = 0
        
    return True

def normalize():
    """원판에 남은 숫자들의 평균을 구해 값을 조정합니다."""
    total_sum = 0
    count = 0
    
    # 0이 아닌 숫자들의 합과 개수를 계산
    for r in range(1, n + 1):
        for c in range(1, m + 1):
            if board[r][c] != 0:
                total_sum += board[r][c]
                count += 1
    
    # 남은 숫자가 없으면 아무것도 하지 않음
    if count == 0:
        return

    # 평균 계산
    avg = total_sum / count
    
    # 평균을 기준으로 값을 조정
    for r in range(1, n + 1):
        for c in range(1, m + 1):
            if board[r][c] != 0:
                if board[r][c] > avg:
                    board[r][c] -= 1
                elif board[r][c] < avg:
                    board[r][c] += 1

# Q번의 회전을 수행
for _ in range(q):
    x, d, k = map(int, input().split())
    simulate(x, d, k)

# 최종적으로 원판에 남은 모든 수의 합을 계산
final_sum = 0
for r in range(1, n + 1):
    final_sum += sum(board[r])

print(final_sum)