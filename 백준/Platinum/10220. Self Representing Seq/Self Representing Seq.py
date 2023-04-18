def getSrs(i, n, a, cnt) :
    if i == n :
        flag = 1
        for j in range(n) :
            if a.count(j) != a[j] :
                flag = 0
                break
        if flag == 1 :
            cnt += 1
    else :
        if i == 0 :
            a = [0] * n
        for j in range(n) :
            a[i] = j
            cnt = getSrs(i + 1, n, a, cnt)
    return cnt

t = int(input())

for i in range(t) :
    n = int(input())
    if n < 7 :
        cnt = getSrs(0, n, [], 0)
        print(cnt)
    else :
        print(1)