import sys

# 빠른 입력을 위해 sys.stdin.readline 사용
input = sys.stdin.readline

def solve():
    """하나의 테스트 케이스를 해결하는 함수"""
    n, m, w = map(int, input().split())
    
    edges = []
    # 도로 정보 입력 (양방향)
    for _ in range(m):
        s, e, t = map(int, input().split())
        edges.append((s, e, t))
        edges.append((e, s, t))
    
    # 웜홀 정보 입력 (단방향, 음수 가중치)
    for _ in range(w):
        s, e, t = map(int, input().split())
        edges.append((s, e, -t))

    # 벨만-포드 알고리즘
    # 거리 배열을 충분히 큰 값으로 초기화
    dist = [10**9] * (n + 1)
    dist[1] = 0  # 시작 노드 거리는 0
    
    # n번 반복
    for i in range(1, n + 1):
        # 모든 간선에 대해 완화(relaxation) 작업 수행
        for s, e, t in edges:
            if dist[e] > dist[s] + t:
                dist[e] = dist[s] + t
                # n번째 반복에서도 거리가 갱신되면 음수 사이클 존재
                if i == n:
                    print("YES")
                    return

    # n번 반복 후 음수 사이클이 발견되지 않음
    print("NO")


# 메인 실행 부분
def main():
    tc = int(input())
    for _ in range(tc):
        solve()

if __name__ == "__main__":
    main()