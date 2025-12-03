
if __name__ == "__main__":
    line = input()
    target = input()
    stk = []
    for ch in line:
        stk.append(ch)

        # while stk[-len(target):] == target:
        #     stk = stk[:-len(target)]

        if len(stk) >= len(target) and stk[-1] == target[-1]:
            ss = ""
            for ch in target:
                ss += stk.pop()

            ss = ss[::-1]
            if target != ss:
                for c in ss:
                    stk.append(c)


    print(''.join(stk) if stk else "FRULA")