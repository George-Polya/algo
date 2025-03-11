from collections import deque, namedtuple

dy = [-1,1,0,0]
dx = [0,0,-1,1]

def printBoard(board):
    for y in range(N):
        for x in range(M):
            print(f"{board[y][x]:3s}", end='')
        print()

def OOB(y, x):
    return y<0 or y>=N or x<0 or x>=M

if __name__ == '__main__':
    N,M = map(int,input().split())
    board = [['.'] * M for _ in range(N)]
    cnt = 0
    q1 = deque()
    q2 = deque()
    Pair = namedtuple('Pair', ['y','x'])
    for y in range(N):
        line = input()
        for x in range(M):
            ch = line[x]
            if ch == 'o':
                if cnt == 0:
                    q1.append(Pair(y,x))
                else:
                    q2.append(Pair(y,x))
                cnt += 1
            else:
                board[y][x] = ch

    # printBoard(board)

    cnt = 0
    while q1:
        size = len(q1)
        # print(len(q1) == len(q2))
        # print("-----")
        # print("q1: ",q1)
        # print("q2: ",q2)
        for sz in range(size):

            cur1 = q1.popleft()
            cur2 = q2.popleft()

            for dir in range(4):
                ny1 = cur1.y + dy[dir]
                nx1 = cur1.x + dx[dir]
                ny2 = cur2.y + dy[dir]
                nx2 = cur2.x + dx[dir]


                if OOB(ny1, nx1) and not OOB(ny2, nx2):
                    cnt += 1
                    print(cnt if cnt <= 10 else -1)
                    exit()

                if not OOB(ny1, nx1) and OOB(ny2, nx2):
                    cnt += 1
                    print(cnt if cnt <= 10 else -1)
                    exit()

                if OOB(ny1, nx1) and OOB(ny2, nx2):
                    continue

                if board[ny1][nx1] != '#':
                    q1.append(Pair(ny1, nx1))
                else:
                    q1.append(cur1)


                if board[ny2][nx2] != '#':
                    q2.append(Pair(ny2, nx2))
                else:
                    q2.append(cur2)

        cnt += 1
        if cnt > 10:
            break
    print(-1)