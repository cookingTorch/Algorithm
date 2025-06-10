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
        HashSet<Integer>[] sets;
        
        sets = new HashSet[THR + 1];
        visited = new HashSet<>();
        visited.add(0);
        num = 0;
        for (i = 1; i <= THR; i++) {
            sets[i] = new HashSet<>();
            num = num * 10 + N;
            sets[i].add(num);
            visited.add(num);
            thr = i >>> 1;
            for (j = 1; j <= thr; j++) {
                for (int num1 : sets[j]) {
                    for (int num2 : sets[i - j]) {
                        res = num1 + num2;
                        if (!visited.contains(res)) {
                            sets[i].add(res);
                            visited.add(res);
                        }
                        res = Math.abs(num1 - num2);
                        if (!visited.contains(res)) {
                            sets[i].add(res);
                            visited.add(res);
                        }
                        res = num1 * num2;
                        if (!visited.contains(res)) {
                            sets[i].add(res);
                            visited.add(res);
                        }
                        res = num1 > num2 ? num1 / num2 : num2 / num1;
                        if (!visited.contains(res)) {
                            sets[i].add(res);
                            visited.add(res);
                        }
                    }
                }
            }
            if (sets[i].contains(number)) {
                return i;
            }
        }
        return FAIL;
    }
}
