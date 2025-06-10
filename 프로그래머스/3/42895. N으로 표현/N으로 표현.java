import java.util.ArrayList;
import java.util.HashSet;

class Solution {
    private static final int THR = 8;
    private static final int FAIL = -1;

    private int number;
    private HashSet<Integer> visited;
    private ArrayList<Integer> list;

    private boolean addNumber(int res) {
        if (!visited.contains(res)) {
            if (res == number) {
                return true;
            }
            list.add(res);
            visited.add(res);
        }
        return false;
    }

    public int solution(int N, int number) {
        int i;
        int j;
        int num;
        int thr;
        ArrayList<Integer>[] lists;

        this.number = number;
        lists = new ArrayList[THR + 1];
        visited = new HashSet<>();
        visited.add(0);
        num = 0;
        loop:
        for (i = 1; i <= THR; i++) {
            lists[i] = list = new ArrayList<>();
            if (addNumber(num = num * 10 + N)) {
                break loop;
            }
            thr = i >>> 1;
            for (j = 1; j <= thr; j++) {
                for (int num1 : lists[j]) {
                    for (int num2 : lists[i - j]) {
                        if (addNumber(num1 + num2)
                                || addNumber(Math.abs(num1 - num2))
                                || addNumber(num1 * num2)
                                || addNumber(num1 > num2 ? num1 / num2 : num2 / num1)) {
                            break loop;
                        }
                    }
                }
            }
        }
        return i > THR ? FAIL : i;
    }
}
