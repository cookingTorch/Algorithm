import java.util.Arrays;

class Solution {
    public int solution(int[][] targets) {
        int end;
        int cnt;

        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);
        cnt = 0;
        end = 0;
        for (int[] target : targets) {
            if (target[0] >= end) {
                cnt++;
                end = target[1];
            }
        }
        return cnt;
    }
}
