
if __name__ == "__main__":
    line = input()
    target = input()
    stk = []
    for ch in line:
        stk.append(ch)

        # while stk[-len(target):] == target:
        #     stk = stk[:-len(target)]

        if len(stk) >= len(target):
            isSame = True
            for i in range(len(target)-1,-1,-1):
                # if target[i] != stk[]
                if stk[len(stk) - len(target) + i] != target[i]:
                    isSame = False
                    break

            if isSame:
                for i in range(len(target)):
                    stk.pop()



    print(''.join(stk) if stk else "FRULA")