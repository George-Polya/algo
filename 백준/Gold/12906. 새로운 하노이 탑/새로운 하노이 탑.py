from collections import deque,defaultdict

def allSame(line, target):

    for ch in line:
        if ch != target:
            return False
    return True


def check(state: str):
    state = state.split('/')


    if state[0] != '' and not allSame(state[0], 'A'):
        return False

    if state[1] != '' and not allSame(state[1], 'B'):
        return False

    if state[2] != '' and not allSame(state[2], 'C'):
        return False

    return True

def merge(A,B,C):
    return A+"/"+B+"/"+C

if __name__ == "__main__":

    towers = ["","",""]
    count_a, count_b, count_c = 0,0,0

    for idx in range(3):
        line = input().strip().split()
        if line[0] != '0':
            towers[idx] = line[1]
            count_a += line[1].count('A')
            count_b += line[1].count('B')
            count_c += line[1].count('C')

    initial_state = "/".join(towers)
    target_state = merge('A' * count_a, 'B'*count_b, 'C'*count_c)

    visited = defaultdict(int)
    visited[initial_state] = 0
    q = deque([initial_state])

    while q:
        cur = q.popleft()

        if cur == target_state:
            print(visited[cur])
            exit()

        current_towers = list(cur.split("/"))
        for src in range(3): #
            if current_towers[src] == "":
                continue

            for dst in range(3):
                if src == dst:
                    continue

                nxt_towers = list(current_towers)

                target = nxt_towers[src][-1]
                nxt_towers[src] = nxt_towers[src][:-1]
                nxt_towers[dst] += target

                nxt = "/".join(nxt_towers)

                if nxt not in visited:
                    visited[nxt] = visited[cur] + 1
                    q.append(nxt)


