import java.util.ArrayList;

class Solution {
    private static final int FIN = 100;

    public int[] solution(int[] progresses, int[] speeds) {
        int i;
        int len;
        int cnt;
        int max;
        int time;
        int[] ans;
        ArrayList<Integer> list;

        list = new ArrayList<>();
        len = progresses.length;
        cnt = 1;
        max = (FIN - progresses[0] + speeds[0] - 1) / speeds[0];
        for (i = 1; i < len; i++) {
            time = (FIN - progresses[i] + speeds[i] - 1) / speeds[i];
            if (time > max) {
                list.add(cnt);
                cnt = 1;
                max = time;
            } else {
                cnt++;
            }
        }
        list.add(cnt);
        len = list.size();
        ans = new int[len];
        for (i = 0; i < len; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
