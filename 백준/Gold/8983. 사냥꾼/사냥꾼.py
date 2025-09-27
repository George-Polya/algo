def binary_search(min_x, max_x, posts):
    l = 0
    r = len(posts) - 1

    while l <= r:
        mid = l + (r - l) // 2
        cur = posts[mid]

        if(cur >= min_x and cur <= max_x):
            return True
        elif cur < min_x :
            l = mid + 1
        else:
            r = mid - 1

    return False

if __name__ == "__main__":
    M,N,L = map(int,input().split())

    posts = list(map(int,input().split()))
    posts.sort()


    ans = 0
    for _ in range(N):
        a, b = map(int,input().split())

        if b > L :
            continue

        min_x = a - (L - b)
        max_x = a + (L - b)



        if binary_search(min_x, max_x, posts):
            ans += 1

    print(ans)
