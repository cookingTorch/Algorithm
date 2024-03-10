heights = []
for i in range(9):
    heights.append(int(input()))
heights.sort()
diff = sum(heights) - 100
i = 0
j = 0
for i in range(8):
    for j in range(i + 1, 9):
        if heights[i] + heights[j] == diff:
            heights.pop(j)
            heights.pop(i)
            break
    if len(heights) == 7:
        break
for i in heights:
    print(i)