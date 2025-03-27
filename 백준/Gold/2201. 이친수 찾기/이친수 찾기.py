def main():
    import sys
    input = sys.stdin.readline

    # K: 찾고자 하는 이친수의 순서 (1 ≤ K ≤ 10^18)
    K = int(input().strip())
    
    # K의 최대값보다 커지는 피보나치 수열을 생성합니다.
    # 피보나치 수열: fib[0]=1, fib[1]=2, 그 이후 fib[i] = fib[i-1] + fib[i-2]
    MAX_K = 10**18
    fib = [1, 2]
    while fib[-1] <= MAX_K:
        fib.append(fib[-1] + fib[-2])
    
    n = len(fib)
    # out 배열: 각 자리(피보나치 항목을 사용할지 여부)를 저장
    out = [False] * n
    
    # 가장 큰 피보나치 수부터 K와 비교하면서
    # K가 해당 피보나치 수 이상이면 해당 자리를 1로 선택하고 K에서 빼줍니다.
    for i in range(n-1, -1, -1):
        if K >= fib[i]:
            K -= fib[i]
            out[i] = True

    # 결과를 출력 (앞부분의 불필요한 0은 건너뜁니다)
    result = []
    flag = False  # 처음 1이 나온 이후부터 출력하도록 하는 플래그
    for bit in reversed(out):
        if bit:
            flag = True
            result.append("1")
        else:
            if flag:
                result.append("0")
    # 결과가 없는 경우(실제로는 K>=1이므로 발생하지 않음) 대비
    if not result:
        result.append("0")
    
    sys.stdout.write("".join(result) + "\n")

if __name__ == '__main__':
    main()