import sys

def solve():
    """
    Finds the minimum sum of friends for a group of three mutual friends.
    """
    # 0. Fast input setup
    input = sys.stdin.readline
    
    try:
        n, m = map(int, input().split())
    except ValueError:
        # Handles empty input lines that might occur
        return

    # 1. Prepare data structures
    # Adjacency matrix for quick O(1) friend lookup
    are_friends = [[False] * (n + 1) for _ in range(n + 1)]
    # Array to store the total friend count for each person
    friend_count = [0] * (n + 1)

    for _ in range(m):
        u, v = map(int, input().split())
        
        # Set bidirectional friendship
        are_friends[u][v] = True
        are_friends[v][u] = True
        
        # Increment friend count for both
        friend_count[u] += 1
        friend_count[v] += 1
        
    # Variable to store the minimum sum, initialized to a very large value
    min_sum = sys.maxsize

    # 2. Search and calculate
    # Iterate through all combinations of three people (A, B, C)
    for a in range(1, n + 1):
        for b in range(a + 1, n + 1):
            # Optimization: only proceed if A and B are friends
            if are_friends[a][b]:
                for c in range(b + 1, n + 1):
                    # Check if C is friends with both A and B to form a triangle
                    if are_friends[a][c] and are_friends[b][c]:
                        # A, B, and C are mutual friends.
                        # Calculate the sum of their friends *outside* this group.
                        # For each person, subtract the other 2 friends in the group.
                        current_sum = (friend_count[a] - 2) + (friend_count[b] - 2) + (friend_count[c] - 2)
                        
                        # Update the minimum sum if the current one is smaller
                        min_sum = min(min_sum, current_sum)

    # 3. Print the result
    # If min_sum was never updated, no group of three was found
    if min_sum == sys.maxsize:
        print(-1)
    else:
        print(min_sum)

solve()