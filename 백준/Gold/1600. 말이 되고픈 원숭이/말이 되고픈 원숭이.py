import collections
import sys

# Set a higher recursion limit for potential deep recursion (though BFS is iterative)
# sys.setrecursionlimit(10**6) 

def solve():
    """
    Reads input, performs the 3D BFS, and prints the minimum number of moves.
    """
    try:
        # Read K
        # The input in Python is typically read from stdin.
        # k = int(sys.stdin.readline().strip())
        k = int(input())
    except EOFError:
        return
    except ValueError:
        return
    
    # Read W and H
    try:
        # w, h = map(int, sys.stdin.readline().split())
        w, h = map(int, input().split())
    except EOFError:
        # Handle case where only K is given
        return
    except ValueError:
        # Handle case where W and H line is empty or invalid
        return

    # Read the board. The Java code uses 1-based indexing for the board,
    # so we'll adjust the Python code to use a list of lists where
    # board[y][x] corresponds to the coordinate (y, x). 
    # We will pad the board with a dummy row and column to maintain 1-based indexing
    # for cleaner translation, though standard Python is 0-based.
    # The actual board will be from board[1][1] to board[h][w].

    board = [[0] * (w + 1)] # Dummy row 0
    
    for _ in range(h):
        try:
            # row_data = [0] + list(map(int, sys.stdin.readline().split()))
            row_data = [0] + list(map(int, input().split()))
        except EOFError:
            # Handle incomplete board input
            return
        except ValueError:
            # Handle invalid row data
            return

        board.append(row_data)

    # Initialize the 3D distance array 'dist'
    # dist[k_remaining][y][x] stores the minimum moves to reach (y, x) 
    # with 'k_remaining' knight moves left.
    # Dimensions: (k+1) x (h+1) x (w+1)
    # Initialize with -1 (unvisited)
    dist = [[[-1] * (w + 1) for _ in range(h + 1)] for _ in range(k + 1)]

    # Queue for BFS: stores tuples of (y, x, k_remaining)
    # y and x are 1-based indices.
    q = collections.deque([(1, 1, k)])
    dist[k][1][1] = 0

    # Knight moves (dy, dx)
    knight_moves = [
        (-2, -1), (-2, 1), (-1, -2), (-1, 2),
        (1, -2), (1, 2), (2, -1), (2, 1)
    ]

    # Monkey/King moves (4 directions: up, down, left, right)
    monkey_moves = [
        (-1, 0), (1, 0), (0, -1), (0, 1)
    ]
    
    # OOB (Out of Bounds) check
    def is_oob(y, x):
        return y <= 0 or y > h or x <= 0 or x > w

    while q:
        # cur_y, cur_x, cur_k_count correspond to Java's cur.y, cur.x, cur.count
        cur_y, cur_x, cur_k_count = q.popleft()

        # --- 1. Knight Moves (if k_remaining > 0) ---
        if cur_k_count > 0:
            next_k_count = cur_k_count - 1
            for dy, dx in knight_moves:
                ny, nx = cur_y + dy, cur_x + dx

                if is_oob(ny, nx) or board[ny][nx] == 1:
                    continue

                # Check if this state (next_k_count, ny, nx) has been visited
                if dist[next_k_count][ny][nx] == -1:
                    dist[next_k_count][ny][nx] = dist[cur_k_count][cur_y][cur_x] + 1
                    q.append((ny, nx, next_k_count))

        # --- 2. Monkey/King Moves (4 directions) ---
        next_k_count = cur_k_count # The number of knight moves remains the same
        for dy, dx in monkey_moves:
            ny, nx = cur_y + dy, cur_x + dx

            if is_oob(ny, nx) or board[ny][nx] == 1:
                continue

            # Check if this state (cur_k_count, ny, nx) has been visited
            if dist[next_k_count][ny][nx] == -1:
                dist[next_k_count][ny][nx] = dist[cur_k_count][cur_y][cur_x] + 1
                q.append((ny, nx, next_k_count))

    # --- Find the minimum answer ---
    # The target is (h, w). The answer is the minimum dist[i][h][w] for all i from 0 to k.
    ans = float('inf')
    found = False
    
    for i in range(k, -1, -1):
        if dist[i][h][w] != -1:
            ans = min(ans, dist[i][h][w])
            found = True
            
    # If the target was reachable, print the minimum moves. Otherwise, print -1.
    if found:
        print(ans)
    else:
        print(-1)

if __name__ == "__main__":
    solve()