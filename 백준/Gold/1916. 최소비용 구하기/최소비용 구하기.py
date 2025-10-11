import sys
import heapq

# 빠른 입력을 위해 sys.stdin.readline 사용
input = sys.stdin.readline
# 무한대를 의미하는 값으로 시스템의 최대값을 사용
INF = sys.maxsize

def dijkstra(start_node):
    """다익스트라 알고리즘을 사용하여 start_node에서 다른 모든 노드까지의 최단 거리를 계산합니다."""
    
    # 우선순위 큐(최소 힙) 초기화. (거리, 노드) 형태로 저장
    pq = []
    heapq.heappush(pq, (0, start_node))
    
    # 시작 노드의 거리는 0으로 초기화
    dist[start_node] = 0
    
    while pq:
        # 가장 거리가 짧은 노드 정보를 가져옴
        current_dist, current_node = heapq.heappop(pq)
        
        # 이미 처리된 노드라면 (더 짧은 경로를 이미 발견했다면) 무시
        if dist[current_node] < current_dist:
            continue
            
        # 현재 노드와 연결된 다른 노드들을 확인
        for next_node, weight in adj[current_node]:
            new_dist = current_dist + weight
            
            # 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우
            if new_dist < dist[next_node]:
                # 거리 정보를 갱신하고 우선순위 큐에 추가
                dist[next_node] = new_dist
                heapq.heappush(pq, (new_dist, next_node))

# N: 도시의 개수 (노드), M: 버스의 개수 (간선)
n = int(input())
m = int(input())

# 인접 리스트 초기화 (1-based 인덱싱을 위해 n+1 크기)
adj = [[] for _ in range(n + 1)]
# 최단 거리 테이블 초기화
dist = [INF] * (n + 1)

# 버스(간선) 정보 입력받아 인접 리스트 구성
for _ in range(m):
    u, v, w = map(int, input().split())
    # u번 노드에서 v번 노드로 가는 비용이 w
    adj[u].append((v, w))

# 출발 도시와 도착 도시 입력
start, end = map(int, input().split())

# 다익스트라 알고리즘 수행
dijkstra(start)

# 도착 도시까지의 최소 비용 출력
print(dist[end])