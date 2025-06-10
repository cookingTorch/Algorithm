import java.util.ArrayList;
import java.util.HashSet;

class Solution {
    private static final int THR = 8;
    private static final int FAIL = -1;

    public int solution(int N, int number) {
        int i;
        int j;
        int num;
        int thr;
        int res;
        HashSet<Integer> visited;
        ArrayList<Integer>[] lists;

        lists = new ArrayList[THR + 1];
        visited = new HashSet<>();
        visited.add(0);
        num = 0;
        loop:
        for (i = 1; i <= THR; i++) {
            lists[i] = new ArrayList<>();
            num = num * 10 + N;
            if (num == number) {
                break;
            }
            lists[i].add(num);
            visited.add(num);
            thr = i >>> 1;
            for (j = 1; j <= thr; j++) {
                for (int num1 : lists[j]) {
                    for (int num2 : lists[i - j]) {
                        res = num1 + num2;
                        if (!visited.contains(res)) {
                            if (res == number) {
                                break loop;
                            }
                            lists[i].add(res);
                            visited.add(res);
                        }
                        res = Math.abs(num1 - num2);
                        if (!visited.contains(res)) {
                            if (res == number) {
                                break loop;
                            }
                            lists[i].add(res);
                            visited.add(res);
                        }
                        res = num1 * num2;
                        if (!visited.contains(res)) {
                            if (res == number) {
                                break loop;
                            }
                            lists[i].add(res);
                            visited.add(res);
                        }
                        res = num1 > num2 ? num1 / num2 : num2 / num1;
                        if (!visited.contains(res)) {
                            if (res == number) {
                                break loop;
                            }
                            lists[i].add(res);
                            visited.add(res);
                        }
                    }
                }
            }
        }
        return i > THR ? FAIL : i;
    }
}
