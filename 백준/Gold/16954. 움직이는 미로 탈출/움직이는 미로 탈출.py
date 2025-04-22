import sys
from collections import deque

# --- 상수 정의 ---
BOARD_SIZE = 8
EMPTY = '.'
WALL = '#'
START_ROW = BOARD_SIZE - 1
START_COL = 0
GOAL_ROW = 0
GOAL_COL = BOARD_SIZE - 1

# 이동 방향 (8방향 + 제자리)
# 상, 하, 좌, 우, 제자리, 우상, 우하, 좌상, 좌하
DR = [-1, 1, 0, 0, 0, -1, 1, -1, 1]
DC = [0, 0, -1, 1, 0, 1, 1, -1, -1]
NUM_DIRECTIONS = len(DR)

class Position:
    """좌표를 나타내는 클래스 (데이터 클래스 역할)"""
    def __init__(self, row: int, col: int):
        self.row = row
        self.col = col

    def __eq__(self, other):
        if not isinstance(other, Position):
            return NotImplemented
        return self.row == other.row and self.col == other.col

    def __hash__(self):
        return hash((self.row, self.col))

    def __str__(self):
        return f"({self.row}, {self.col})"

class Board:
    """게임 보드 상태 및 조작을 관리하는 클래스"""
    def __init__(self, initial_state: list[list[str]]):
        self._validate_board(initial_state)
        self.grid = initial_state
        self.size = len(initial_state)

    def _validate_board(self, board_state: list[list[str]]):
        """보드 상태 유효성 검사"""
        if not board_state:
            raise ValueError("보드 상태가 비어있습니다.")
        rows = len(board_state)
        if rows != BOARD_SIZE:
             raise ValueError(f"보드 행의 크기가 {BOARD_SIZE}가 아닙니다.")
        for row in board_state:
            if len(row) != BOARD_SIZE:
                 raise ValueError(f"보드 열의 크기가 {BOARD_SIZE}가 아닙니다.")

    def get_cell(self, pos: Position) -> str:
        """주어진 위치의 셀 값을 반환"""
        if self.is_out_of_bounds(pos):
            # OOB 처리를 명확히 하거나 예외 발생 고려
            return WALL # OOB는 벽으로 간주하여 이동 불가 처리
        return self.grid[pos.row][pos.col]

    def is_out_of_bounds(self, pos: Position) -> bool:
        """좌표가 보드 범위를 벗어나는지 확인"""
        return not (0 <= pos.row < self.size and 0 <= pos.col < self.size)

    def shift_walls(self):
        """벽을 한 칸 아래로 이동시키는 메서드"""
        new_grid = [[EMPTY for _ in range(self.size)] for _ in range(self.size)]
        for r in range(self.size - 1): # 마지막 행 제외
            for c in range(self.size):
                if self.grid[r][c] == WALL:
                    new_grid[r + 1][c] = WALL # 아래 행으로 이동
        self.grid = new_grid

    def print_board(self):
         """디버깅용 보드 출력 메서드"""
         for row in self.grid:
             print("".join(row))
         print("-" * self.size)

class Pathfinder:
    """BFS를 사용하여 경로를 찾는 클래스"""
    def __init__(self, board: Board):
        self.board = board

    def can_reach_goal(self) -> bool:
        """BFS를 실행하여 목표 지점 도달 가능 여부 반환"""
        queue = deque([Position(START_ROW, START_COL)])

        time_step = 0
        while queue:
            # 각 시간 단계(BFS 레벨)마다 방문 기록 초기화
            # 벽이 움직이므로 같은 위치라도 다른 시간에 방문 가능
            visited_this_step = set()
            level_size = len(queue)

            # print(f"--- Time Step {time_step} ---")
            # self.board.print_board()
            # print(f"Queue size: {level_size}")
            # print(f"Queue: {[str(p) for p in queue]}")


            for _ in range(level_size):
                current_pos = queue.popleft()

                # 현재 위치가 벽으로 바뀌었는지 확인 (벽이 이동한 후)
                if self.board.get_cell(current_pos) == WALL:
                    continue

                # 목표 지점 도달 확인
                if current_pos.row == GOAL_ROW and current_pos.col == GOAL_COL:
                    return True

                # 9가지 이동 방향 탐색 (제자리 포함)
                for i in range(NUM_DIRECTIONS):
                    next_row = current_pos.row + DR[i]
                    next_col = current_pos.col + DC[i]
                    next_pos = Position(next_row, next_col)

                    # 유효성 검사: 범위 확인, 벽 확인, 현재 시간 단계 방문 여부 확인
                    if self.board.is_out_of_bounds(next_pos):
                        continue
                    if self.board.get_cell(next_pos) == WALL:
                        continue
                    if next_pos in visited_this_step:
                         continue

                    # 큐에 추가 및 현재 시간 단계 방문 처리
                    visited_this_step.add(next_pos)
                    queue.append(next_pos)

            # 현재 레벨의 모든 탐색이 끝나면 벽 이동
            self.board.shift_walls()
            time_step += 1


        # 큐가 비었지만 목표에 도달하지 못함
        return False

# --- 메인 실행 로직 ---
def main():
    """프로그램의 주 진입점"""
    try:
        # 표준 입력으로부터 보드 상태 읽기
        initial_board_state = [list(sys.stdin.readline().strip()) for _ in range(BOARD_SIZE)]

        # 객체 생성
        game_board = Board(initial_board_state)
        pathfinder = Pathfinder(game_board)

        # 경로 탐색 실행 및 결과 출력
        result = pathfinder.can_reach_goal()
        print(1 if result else 0)

    except ValueError as e:
        print(f"입력 오류: {e}", file=sys.stderr)
    except Exception as e:
        print(f"알 수 없는 오류 발생: {e}", file=sys.stderr)


if __name__ == "__main__":
    main()