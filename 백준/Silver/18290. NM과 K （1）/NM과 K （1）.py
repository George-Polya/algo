def solve():
    n, m, k = map(int, input().split())
    grid = [list(map(int, input().split())) for _ in range(n)]
    max_sum = -float('inf')
    visited = [[False for _ in range(m)] for _ in range(n)]

    def is_valid(r, c):
        return 0 <= r < n and 0 <= c < m

    def is_adjacent(r, c):
        for dr, dc in [(0, 1), (0, -1), (1, 0), (-1, 0)]:
            nr, nc = r + dr, c + dc
            if is_valid(nr, nc) and visited[nr][nc]:
                return True
        return False

    def find_max_sum(index, count, current_sum):
        nonlocal max_sum
        if count == k:
            max_sum = max(max_sum, current_sum)
            return

        if index >= n * m:
            return

        r = index // m
        c = index % m

        # Don't select the current cell
        find_max_sum(index + 1, count, current_sum)

        # Select the current cell if it's not adjacent to any selected cell
        if not visited[r][c] and not is_adjacent(r, c):
            visited[r][c] = True
            find_max_sum(index + 1, count + 1, current_sum + grid[r][c])
            visited[r][c] = False

    find_max_sum(0, 0, 0)
    print(max_sum)

if __name__ == "__main__":
    solve()