import math

if __name__ == '__main__':
    A,B = map(int,input().split())

    print('1' * math.gcd(A,B))