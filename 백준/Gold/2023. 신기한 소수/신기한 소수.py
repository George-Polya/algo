def is_prime(num):
    if num <= 1 :
        return False

    i = 2
    while i * i <= num:
        if num % i == 0:
            return False
        i += 1
    return True

def solve(cur, cnt):
    if cnt == 0:
        if is_prime(cur):
            ans.append(cur)
        return

    for i in range(10):
        nxt = cur * 10 + i
        if is_prime(nxt):
            solve(nxt, cnt - 1)

if __name__ == '__main__':
    N = int(input())
    ans = []
    solve(0,N)
    print('\n'.join(map(str, ans)))