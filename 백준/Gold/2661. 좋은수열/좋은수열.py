def possible(seq: str):
    length = len(seq)
    for sub in range(1, length//2 + 1):
        if seq[-sub:] == seq[-2*sub:-sub]:
            return False

    return True

def solve(seq: str):
    if len(seq) == N:
        print(seq)
        exit(0)

    for digit in ['1','2','3']:
        nxt = seq + digit

        if possible(nxt):
            solve(nxt)
if __name__ == "__main__":
    N = int(input())
    solve("")