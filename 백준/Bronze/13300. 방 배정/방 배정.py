n, k = map(int, input().split())
room = []
for i in range(7):
    room.append([0, 0])
for i in range(n):
    s, y = map(int, input().split())
    room[y][s] += 1
num = 0
for i in range(1, 7):
    for j in range(2):
        if room[i][j] != 0:
            num += (room[i][j] - 1) // k + 1
print(num)