import bisect

class Student:
    def __init__(self, height, rank):
        self.height = height
        self.rank = rank

    def __lt__(self, o):
        return self.height > o.height

    def __repr__(self):
        return f"height: {self.height}, rank: {self.rank}"

def upper_bound(teams, target):
    l = 0
    r = len(teams) - 1
    ret = len(teams)

    while l<=r:
        mid = (l + r) // 2

        if target > teams[mid]:
            r = mid - 1
            ret = mid
        else:
            l = mid + 1

    return ret


if __name__== "__main__":
    N = int(input())
    students = []
    for _ in range(N):
        h,r = map(int,input().split())
        students.append(Student(h,r))

    students.sort()

    teams = []
    for student in students:
        idx = upper_bound(teams, student.rank)

        if idx == len(teams):
            teams.append(1)
        else:
            teams[idx] += 1
            i = idx
            while i > 0 and teams[i] > teams[i-1]:
                tmp = teams[i]
                teams[i] = teams[i-1]
                teams[i-1] = tmp
                i-=1

    print(len(teams))