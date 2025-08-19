import sys
import heapq

# 빠른 입력을 위해 sys.stdin.readline 사용
input = sys.stdin.readline

def main():
    try:
        n = int(input())
    except (ValueError, IndexError):
        # 입력이 없거나 정수가 아닌 경우 처리
        # 이 문제의 조건상 n >= 1 이므로 사실상 필요 없지만, 안정성을 위해 추가
        print(-1)
        return

    # 인접 리스트로 그래프 표현
    adj = [[] for _ in range(n + 1)]
    total_lan_length = 0

    for i in range(1, n + 1):
        line = input().strip()
        for j in range(1, n + 1):
            char = line[j - 1]
            cost = 0
            if 'a' <= char <= 'z':
                cost = ord(char) - ord('a') + 1
            elif 'A' <= char <= 'Z':
                cost = ord(char) - ord('A') + 27
            
            total_lan_length += cost
            
            # 비용이 0이 아니면 간선으로 추가 (무방향 그래프)
            if cost != 0 and i != j:
                adj[i].append((cost, j))
                adj[j].append((cost, i))

    # 프림 알고리즘으로 MST 비용 계산
    # 우선순위 큐(최소 힙) 사용
    pq = [(0, 1)]  # (비용, 노드 ID), 1번 노드에서 시작
    visited = [False] * (n + 1)
    mst_cost = 0
    connected_nodes = 0

    while pq:
        # 가장 비용이 적은 간선 pop
        cost, current_node = heapq.heappop(pq)

        # 이미 방문한 노드면 건너뜀
        if visited[current_node]:
            continue

        visited[current_node] = True
        mst_cost += cost
        connected_nodes += 1

        # 현재 노드와 연결된 다른 노드들을 큐에 추가
        for next_cost, next_node in adj[current_node]:
            if not visited[next_node]:
                heapq.heappush(pq, (next_cost, next_node))

    # 모든 노드가 연결되었는지 확인
    if connected_nodes == n:
        print(total_lan_length - mst_cost)
    else:
        print(-1)

if __name__ == "__main__":
    main()