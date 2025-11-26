import sys
from collections import deque, defaultdict
import bisect

# 빠른 입출력을 위해 사용
input = sys.stdin.readline

def solve():
    # N: 홈의 개수, T: 목표 높이
    n, t = map(int, input().split())
    
    # y좌표를 키(key)로, x좌표들의 리스트를 값(value)으로 저장
    nodes = defaultdict(list)
    for _ in range(n):
        x, y = map(int, input().split())
        nodes[y].append(x)
    
    # 이분 탐색을 위해 각 y좌표의 x리스트를 오름차순 정렬
    for y in nodes:
        nodes[y].sort()
        
    # BFS 초기화
    # 큐에는 (x좌표, y좌표, 이동횟수) 저장
    q = deque([(0, 0, 0)])
    
    # 방문 체크를 위한 집합 (Set)
    # (x, y) 튜플을 저장하여 중복 방문 방지
    visited = set()
    visited.add((0, 0))
    
    while q:
        cx, cy, dist = q.popleft()
        
        # 목표 높이에 도달하면 결과 출력 후 종료
        if cy == t:
            print(dist)
            return

        # 현재 높이(cy) 기준 위아래 2칸 범위 탐색
        for ny in range(cy - 2, cy + 3):
            # 맵 범위를 벗어나거나, 해당 높이에 홈이 없으면 스킵
            if ny < 0 or ny > t:
                continue
            if not nodes[ny]:
                continue
            
            # 핵심: 이분 탐색으로 탐색 범위 좁히기
            # 해당 높이(ny)의 홈들 중, x좌표가 (cx - 2) 이상인 첫 번째 위치(인덱스)를 찾음
            start_idx = bisect.bisect_left(nodes[ny], cx - 2)
            
            # start_idx부터 오른쪽으로 확인
            for i in range(start_idx, len(nodes[ny])):
                nx = nodes[ny][i]
                
                # x좌표 차이가 2를 초과하면 더 이상 볼 필요 없음 (정렬되어 있으므로)
                if nx > cx + 2:
                    break
                
                # 방문하지 않은 홈이라면 큐에 추가
                if (nx, ny) not in visited:
                    visited.add((nx, ny))
                    q.append((nx, ny, dist + 1))
                    
    # 큐가 빌 때까지 목표에 도달하지 못하면 -1 출력
    print(-1)

if __name__ == "__main__":
    solve()