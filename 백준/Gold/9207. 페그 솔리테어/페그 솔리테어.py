import sys

# 전역 변수 선언
board_global = []
dy_global = [-1, 0, 1, 0]  # 상, 우, 하, 좌 이동을 위한 y 변화량
dx_global = [0, 1, 0, -1]  # 상, 우, 하, 좌 이동을 위한 x 변화량
MAX_Y_CONST = 5  # 보드의 최대 세로 크기
MAX_X_CONST = 9  # 보드의 최대 가로 크기
min_pins_overall = 0  # 게임 종료 후 남은 최소 핀의 수
min_moves_overall = 0  # 최소 핀을 남기기 위해 필요한 이동 횟수

# 좌표가 보드 범위를 벗어나는지 확인하는 함수
def is_oob(r, c):
    return not (0 <= r < MAX_Y_CONST and 0 <= c < MAX_X_CONST)

# 깊이 우선 탐색(DFS) 함수
def solve_dfs(current_pins, moves_count):
    global min_pins_overall, min_moves_overall, board_global

    # 현재 상태의 핀 수와 이동 횟수를 기반으로 전역 최소값 업데이트
    # Java 코드의 로직:
    # 1. 현재 핀 수가 기존 최소 핀 수보다 적으면, 최소 핀 수와 이동 횟수 모두 업데이트
    # 2. 현재 핀 수가 기존 최소 핀 수와 같으면, 이동 횟수를 현재 이동 횟수로 업데이트 (덮어쓰기)
    if current_pins < min_pins_overall:
        min_pins_overall = current_pins
        min_moves_overall = moves_count
    elif current_pins == min_pins_overall:
        # Java 코드에서는 단순히 move = moveCnt로 덮어씁니다.
        # 이는 동일한 최소 핀 수에 도달하는 여러 경로 중 가장 마지막에 탐색된 경로의 이동 횟수를 저장합니다.
        # 만약 "최소 핀 수일 때 최소 이동 횟수"를 원한다면 min_moves_overall = min(min_moves_overall, moves_count)가 됩니다.
        # 여기서는 Java 코드의 동작을 그대로 따릅니다.
        min_moves_overall = moves_count

    # 보드의 모든 칸을 순회하며 움직일 수 있는 핀을 찾음
    for r_start in range(MAX_Y_CONST):  # 시작 핀의 y 좌표
        for c_start in range(MAX_X_CONST):  # 시작 핀의 x 좌표
            if board_global[r_start][c_start] == 'o':  # 현재 위치에 핀이 있다면
                # 4가지 방향으로 점프 시도
                for i in range(4):
                    # 뛰어넘을 핀의 위치
                    r_jumped = r_start + dy_global[i]
                    c_jumped = c_start + dx_global[i]

                    # 착지할 위치
                    r_land = r_jumped + dy_global[i]
                    c_land = c_jumped + dx_global[i]

                    # 점프가 유효한지 확인:
                    # 1. 뛰어넘을 핀이 보드 내에 있고 'o'인가?
                    # 2. 착지할 위치가 보드 내에 있고 '.'(빈칸)인가?
                    if not is_oob(r_jumped, c_jumped) and \
                       board_global[r_jumped][c_jumped] == 'o' and \
                       not is_oob(r_land, c_land) and \
                       board_global[r_land][c_land] == '.':
                        
                        # 점프 실행 (보드 상태 변경)
                        board_global[r_start][c_start] = '.'  # 시작 핀 제거
                        board_global[r_jumped][c_jumped] = '.'  # 뛰어넘은 핀 제거
                        board_global[r_land][c_land] = 'o'    # 착지 위치에 핀 놓기

                        # 재귀 호출: 핀 하나 줄고, 이동 횟수 하나 증가
                        solve_dfs(current_pins - 1, moves_count + 1)

                        # 백트래킹: 보드 상태 복원
                        board_global[r_start][c_start] = 'o'
                        board_global[r_jumped][c_jumped] = 'o'
                        board_global[r_land][c_land] = '.'
    
    # 더 이상 움직일 수 없는 상태이면 해당 DFS 경로는 종료됨.
    # 최소값 업데이트는 각 DFS 호출 시작 시점에 이루어짐.

def main():
    global board_global, min_pins_overall, min_moves_overall # 전역 변수 사용 명시
    
    input_func = sys.stdin.readline # 빠른 입력을 위해 sys.stdin.readline 사용

    T = int(input_func())  # 테스트 케이스 수 입력
    results = []  # 결과를 저장할 리스트

    for tc_num in range(T):
        board_global = []  # 각 테스트 케이스마다 보드 초기화
        initial_pin_count = 0
        
        # 보드 상태 입력 (Java 코드의 1-based 인덱싱을 Python의 0-based로 변환)
        for _ in range(MAX_Y_CONST):
            line = input_func().strip()
            row = []
            for char_idx in range(MAX_X_CONST):
                char = line[char_idx]
                row.append(char)
                if char == 'o':
                    initial_pin_count += 1
            board_global.append(row)
        
        # 현재 테스트 케이스의 전역 최소값 초기화
        min_pins_overall = initial_pin_count
        min_moves_overall = 0  # 초기 상태는 0번의 이동으로 간주

        # DFS 시작
        # 초기 상태 (initial_pin_count 개의 핀, 0번의 이동)도 solve_dfs 내부에서 평가됨
        solve_dfs(initial_pin_count, 0)
        
        # Java 코드에서 각 테스트 케이스 처리 후 `br.readLine()` 호출 부분이 있음.
        # 이는 테스트 케이스 사이에 빈 줄이나 다른 구분자가 있는 경우 이를 소비하기 위함일 수 있음.
        # Java 코드와 동일하게 동작하도록 모든 테스트 케이스 처리 후 한 줄을 읽음.
        # (마지막 테스트 케이스 후에는 불필요할 수 있으나, 원본 코드의 동작을 따름)
        input_func() # Java의 br.readLine()에 해당

        results.append(f"{min_pins_overall} {min_moves_overall}")

    # 모든 결과를 한 번에 출력
    sys.stdout.write("\n".join(results) + "\n")

if __name__ == "__main__":
    # Peg Solitaire와 같은 게임은 탐색 깊이가 깊어질 수 있으므로,
    # 필요에 따라 재귀 깊이 제한을 늘려야 할 수 있습니다.
    # sys.setrecursionlimit(10**6) # 예시: 재귀 깊이를 백만으로 설정
    main()