def solution(e, starts):
    spf = [0] * (e + 1)
    exp = [0] * (e + 1)
    cnt = [0] * (e + 1)
    cnt[1] = 1

    for i in range(2, e + 1):
        if spf[i] == 0:
            spf[i] = i
            exp[i] = 2
            cnt[i] = 2
            for j in range(i * 2, e + 1, i):
                if spf[j] == 0:
                    spf[j] = i
        else:
            prev = i // spf[i]
            if spf[prev] == spf[i]:
                exp[i] = exp[prev] + 1
                cnt[i] = cnt[prev] // exp[prev] * exp[i]
            else:
                exp[i] = 2
                cnt[i] = cnt[prev] * 2

    max_cnt = 0
    num = [0] * (e + 1)
    for i in range(e, 0, -1):
        if cnt[i] >= max_cnt:
            max_cnt = cnt[i]
            num[i] = i
        else:
            num[i] = num[i + 1]

    return [num[s] for s in starts]
