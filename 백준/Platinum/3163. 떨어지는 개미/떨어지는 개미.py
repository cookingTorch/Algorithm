import math

t = int(input())

for i in range(0, t) :

    n, l, k = map(int,input().split())

    idTime = []
    timeLeft = []
    timeRight = []

    for j in range(0, n) :
        location, antId = map(int, input().split())
        idTime.append([antId])
        if antId < 0 :
            time = location
            timeLeft.append(time)
        else :
            time = l - location
            timeRight.append(time)

    cntLeft = len(timeLeft)
    
    for j in range(0, n) :
        if j < cntLeft :
            idTime[j].append(timeLeft[j])
        else :
            idTime[j].append(timeRight[j - cntLeft])

    idTime.sort(key=lambda x: (x[1], x[0]))

    print(idTime[k - 1][0])