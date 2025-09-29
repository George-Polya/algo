import sys

# 파이썬의 기본 재귀 깊이 제한은 1000 정도입니다.
# N이 최대 100,000이므로, 재귀 깊이 제한을 충분히 늘려주어야 런타임 에러(RecursionError)가 발생하지 않습니다.
sys.setrecursionlimit(110000)

# 빠른 입력을 위해 sys.stdin.readline을 사용합니다.
input = sys.stdin.readline

def dfs(current_node):
    global team_members_count
    
    # 1. 현재 노드를 방문 처리하고, 탐색이 끝났는지 여부를 False로 둡니다.
    visited[current_node] = True
    next_node = students[current_node]
    
    # 2. 다음 노드를 아직 방문하지 않았다면, 계속 탐색합니다.
    if not visited[next_node]:
        dfs(next_node)
    # 3. 다음 노드를 방문한 적이 있다면, 사이클인지 확인합니다.
    else:
        # 만약 다음 노드의 탐색이 아직 끝나지 않았다면, 사이클이 발생한 것입니다.
        if not finished[next_node]:
            # 사이클에 포함된 팀원의 수를 셉니다.
            # next_node부터 시작해서 다시 next_node로 돌아올 때까지 셉니다.
            i = next_node
            while True:
                team_members_count += 1
                i = students[i]
                if i == next_node:
                    break
    
    # 4. 현재 노드에 대한 모든 탐색이 끝났음을 표시합니다.
    finished[current_node] = True


# === 메인 로직 ===
T = int(input())  # 테스트 케이스의 수
for _ in range(T):
    N = int(input())  # 학생의 수
    
    # 학생들의 선택 정보를 입력받습니다.
    # 문제의 번호는 1부터 시작하므로, 인덱스를 맞추기 위해 리스트 맨 앞에 0을 추가합니다.
    students = [0] + list(map(int, input().split()))
    
    visited = [False] * (N + 1)   # 방문 여부 체크
    finished = [False] * (N + 1)  # 탐색 완료 여부 체크
    team_members_count = 0        # 팀에 속한 총 학생 수
    
    for i in range(1, N + 1):
        # 아직 방문하지 않은 학생에 대해서만 탐색을 시작합니다.
        if not visited[i]:
            dfs(i)
            
    # 전체 학생 수에서 팀에 속한 학생 수를 뺀 값을 출력합니다.
    print(N - team_members_count)