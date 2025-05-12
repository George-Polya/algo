import heapq
import sys

# 빠른 입력을 위한 설정
input = sys.stdin.readline
INF = float('inf') # 무한대 값 설정

def dijkstra(start, graph, v):
    """
    다익스트라 알고리즘을 수행하여 start 정점에서 다른 모든 정점까지의
    최단 거리를 계산하는 함수

    Args:
        start (int): 시작 정점 번호
        graph (list): 그래프의 인접 리스트 표현 (list of lists of tuples)
                      예: graph[u] = [(v1, w1), (v2, w2), ...]
        v (int): 총 정점의 개수

    Returns:
        list: 시작 정점으로부터 각 정점까지의 최단 거리 리스트
    """
    dist = [INF] * (v + 1) # 거리 배열 초기화 (1번부터 v번까지 사용)
    dist[start] = 0
    pq = [(0, start)] # 우선순위 큐 초기화 (비용, 정점 번호)

    while pq:
        current_cost, current_vertex = heapq.heappop(pq)

        # 이미 더 짧은 경로를 찾았다면 무시
        if current_cost > dist[current_vertex]:
            continue

        # 현재 정점과 연결된 다른 정점들을 확인
        if current_vertex < len(graph): # graph 범위 체크 추가
            for neighbor_vertex, weight in graph[current_vertex]:
                new_cost = dist[current_vertex] + weight
                # 새로운 경로가 기존 경로보다 짧다면 갱신하고 큐에 추가
                if new_cost < dist[neighbor_vertex]:
                    dist[neighbor_vertex] = new_cost
                    heapq.heappush(pq, (new_cost, neighbor_vertex))

    return dist

# 입력 받기
v, e, p = map(int, input().split()) # 정점 개수, 간선 개수, 건우 위치

# 그래프 초기화 (인접 리스트)
# 0번 인덱스는 사용하지 않으므로 v+1 크기로 생성
graph = [[] for _ in range(v + 1)]

# 간선 정보 입력 및 그래프 구성 (양방향)
for _ in range(e):
    u, v_node, w = map(int, input().split())
    # 양방향 그래프이므로 양쪽에 추가
    graph[u].append((v_node, w))
    graph[v_node].append((u, w))

# 1. 1번 정점에서 모든 정점까지의 최단 거리 계산
dist_from_start = dijkstra(1, graph, v)

# 2. P번 정점에서 모든 정점까지의 최단 거리 계산
dist_from_p = dijkstra(p, graph, v)

# 필요한 최단 거리 값 추출
shortest_path_total = dist_from_start[v]      # 1 -> V 최단 거리
shortest_path_via_p_part1 = dist_from_start[p] # 1 -> P 최단 거리
shortest_path_via_p_part2 = dist_from_p[v]     # P -> V 최단 거리

# 경로 존재 여부 확인 및 비교
# 만약 1->V, 1->P, P->V 중 하나라도 경로가 없다면 (INF), 건우를 거쳐가는 최단 경로는 불가능
# 파이썬에서는 INF 값과 비교하여 경로 존재 여부 확인 가능
if shortest_path_total == INF or shortest_path_via_p_part1 == INF or shortest_path_via_p_part2 == INF:
    # 1->V 경로 자체가 없거나, 1->P 또는 P->V 경로가 없으면 P를 거쳐가는 최단 경로는 불가능
    # 아래 비교에서 자동으로 걸러지지만, 명시적으로 처리할 수도 있음.
    # 여기서는 아래 비교 로직에 맡김.
    pass


# 3. 결과 비교 및 출력
# 1->V 최단 거리 == (1->P 최단 거리 + P->V 최단 거리) 인지 확인
# 경로가 없는 경우(INF) 덧셈 결과도 INF가 되므로, 아래 조건에서 자연스럽게 걸러짐
if shortest_path_total == shortest_path_via_p_part1 + shortest_path_via_p_part2:
    print("SAVE HIM")
else:
    print("GOOD BYE")