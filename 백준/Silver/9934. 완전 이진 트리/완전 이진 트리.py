import sys

def build_tree(sub_inorder, depth):
    """
    중위 순회 결과를 받아 트리를 재구성하고, 각 노드를 레벨별로 저장합니다.
    sub_inorder: 현재 서브트리의 중위 순회 결과 리스트
    depth: 현재 트리의 깊이 (루트가 0)
    """
    # 재귀 탈출 조건: 더 이상 노드가 없으면 종료
    if not sub_inorder:
        return
    
    # 중위 순회에서 가운데 값이 현재 서브트리의 루트 노드입니다.
    mid_index = len(sub_inorder) // 2
    root = sub_inorder[mid_index]
    
    # 현재 깊이에 해당하는 리스트에 루트 노드를 추가합니다.
    tree_levels[depth].append(root)
    
    # 루트를 기준으로 왼쪽은 왼쪽 서브트리, 오른쪽은 오른쪽 서브트리가 됩니다.
    # 왼쪽 서브트리에 대해 재귀 호출
    build_tree(sub_inorder[:mid_index], depth + 1)
    # 오른쪽 서브트리에 대해 재귀 호출
    build_tree(sub_inorder[mid_index+1:], depth + 1)


# K: 트리의 높이
k = int(sys.stdin.readline())

# 중위 순회 결과 입력
inorder_list = list(map(int, sys.stdin.readline().split()))

# 결과를 레벨별로 저장할 2차원 리스트 초기화
tree_levels = [[] for _ in range(k)]

# 트리 재구성 시작
build_tree(inorder_list, 0)

# 각 레벨에 있는 노드들을 순서대로 출력
for level in tree_levels:
    # `*level`은 리스트의 모든 요소를 공백으로 구분하여 출력합니다.
    print(*level)