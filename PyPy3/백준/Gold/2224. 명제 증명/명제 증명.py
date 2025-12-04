


def toInt(ch):
    idx =  ord(ch) - ord('A')
    if idx > 26:
        idx = idx - 32 + 26
    return idx

def toChar(num):
    if num >= 26:
        num = num - 26 + ord('a')
    else:
        num = num + ord('A')
    return chr(num)

def printBoard(board):
    n = len(board)
    m = len(board[0])
    for y in range(n):
        for x in range(m):
            if(board[y][x] == INF):
                print("%4s" % ('INF'), end='')
            else:
                print("%3d" % (board[y][x]), end='')
        print()

INF = int(1e10)
dist = [[INF if i != j else 0 for i in range(52)] for j in range(52)]
adj = [[ 0 for _ in range(52)] for _ in range(52)]

N = int(input())
for i in range(N):
    line = input().split()
    u = toInt(line[0])
    v = toInt(line[-1])
    # print(u,v)
    dist[u][v] = 1

for k in range(52):
    for a in range(52):
        for b in range(52):
            dist[a][b] = min(dist[a][b], dist[a][k] + dist[k][b])


result = []
for y in range(52):
    for x in range(52):
        if dist[y][x] != INF and y != x:
            result.append(toChar(y)+" => "+toChar(x))
print(len(result))
print('\n'.join(result))
