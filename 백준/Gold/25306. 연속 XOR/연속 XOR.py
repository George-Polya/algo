

def solve(num: int):
    if num % 4 == 1:
        return 1
    elif num % 4 == 2:
        return num + 1
    elif num % 4 == 3:
        return 0

    return num

if __name__ == "__main__":
    A,B = map(int,input().split())
    print(solve(B) ^ solve(A-1))