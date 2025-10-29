class Solution {
    private static final int SIZE = 'Z' + 1;
    private static final int OFFSET = 'A';
    private static final int LENGTH = SIZE - OFFSET;

    public int solution(String skill, String[] skill_trees) {
        int i;
        int j;
        int ch;
        int len;
        int idx;
        int cur;
        int cnt;
        int size;
        int[] pat;
        boolean[] init;
        boolean[] check;
        String str;

        len = skill.length();
        init = new boolean[SIZE];
        check = new boolean[SIZE];
        pat = new int[len + 1];
        pat[0] = skill.charAt(0);
        for (i = 1; i < len; i++) {
            init[pat[i] = skill.charAt(i)] = true;
        }
        cnt = 0;
        size = skill_trees.length;
        for (i = 0; i < size; i++) {
            str = skill_trees[i];
            len = str.length();
            idx = 0;
            cur = pat[idx];
            System.arraycopy(init, OFFSET, check, OFFSET, LENGTH);
            for (j = 0; j < len; j++) {
                ch = str.charAt(j);
                if (check[ch]) {
                    break;
                }
                if (ch == cur) {
                    cur = pat[++idx];
                    check[cur] = false;
                }
            }
            if (j == len) {
                cnt++;
            }
        }
        return cnt;
    }
}
