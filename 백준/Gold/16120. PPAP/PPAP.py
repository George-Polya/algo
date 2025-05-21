def isPPAP(stk):
    size = len(stk)
    if size < 4:
        return False

    return stk[-1] == 'P' and stk[-2] == 'A' and stk[-3] == 'P' and stk[-4] =='P'

if __name__ == "__main__":
    line = input()
    N = len(line)

    stk = []
    for ch in line:
        stk.append(ch)
        if ch == 'P' and isPPAP(stk):
            for _ in range(4):
                stk.pop()
            stk.append('P')

    print("PPAP" if (len(stk) == 1 and stk[0] == 'P') else "NP")