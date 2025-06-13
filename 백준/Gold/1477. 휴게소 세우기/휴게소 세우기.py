import sys

# 입력을 빠르게 받기 위한 설정
input = sys.stdin.readline

def decide(target_dist, existing_stops, m, l):
    """
    휴게소 간 최대 거리를 'target_dist'로 만들 수 있는지 판별하는 함수.
    
    Args:
        target_dist (int): 만들고자 하는 휴게소 간 최대 거리 (이분 탐색의 'mid' 값)
        existing_stops (list): 기존 휴게소 위치 리스트
        m (int): 추가로 지을 수 있는 휴게소의 최대 개수
        l (int): 고속도로의 총 길이
        
    Returns:
        bool: 'target_dist'를 만족시키기 위해 필요한 휴게소 개수가 m개 이하면 True, 아니면 False
    """
    # 시작점(0)과 끝점(l)을 포함하여 모든 지점을 리스트로 만듦
    all_points = [0] + existing_stops + [l]
    
    needed_stops = 0 # 'target_dist'를 만족시키기 위해 필요한 휴게소 개수
    
    # 인접한 두 지점 사이의 거리를 확인
    for i in range(len(all_points) - 1):
        dist = all_points[i+1] - all_points[i]
        
        # 거리가 목표(target_dist)보다 길면, 그 사이에 휴게소를 추가해야 함
        if dist > target_dist:
            # 필요한 휴게소 개수 계산
            # 예: dist=11, target_dist=5 이면 (11-1)//5 = 2개의 휴게소가 필요 (5, 10 지점)
            # 예: dist=10, target_dist=5 이면 (10-1)//5 = 1개의 휴게소가 필요 (5 지점)
            needed_stops += (dist - 1) // target_dist
            
    # 필요한 휴게소 개수가 우리가 지을 수 있는 개수(m)보다 작거나 같으면 성공
    return needed_stops <= m

def solve():
    """메인 로직을 처리하는 함수"""
    try:
        # n, m, l 입력
        n, m, l = map(int, input().split())
        
        # 기존 휴게소 위치 입력
        if n > 0:
            arr = list(map(int, input().split()))
            arr.sort() # 위치를 기준으로 정렬
        else:
            arr = []

    except (IOError, ValueError):
        # 입력이 비어있거나 잘못된 경우 처리 (예: BOJ에서는 불필요)
        return

    # 이분 탐색을 위한 범위 설정
    # left: 가능한 최소 최대 거리 (1)
    # right: 가능한 최대 최대 거리 (l)
    left, right = 1, l
    answer = l # 정답을 저장할 변수, 최댓값으로 초기화

    # 이분 탐색 시작
    while left <= right:
        mid = (left + right) // 2 # 이번에 탐색할 '최대 거리' 후보
        
        if mid == 0: # 0으로 나누는 것을 방지
            left = mid + 1
            continue

        # 'mid'를 최대 거리로 만드는 것이 가능한지 확인
        if decide(mid, arr, m, l):
            # 가능하다면, 더 작은 값도 가능한지 탐색
            answer = mid
            right = mid - 1
        else:
            # 불가능하다면, 최대 거리를 더 늘려야 함
            left = mid + 1
            
    print(answer)

if __name__ == "__main__":
    solve()