class Solution {
    private static final int ALPH = 26;
    private static final int DIFF = 'A';

    public int solution(String skill, String[] skill_trees) {
        int i;
        int j;
        int ch;
        int idx;
        int cur;
        int cnt;
        int len;
        int size;
        int skillLen;
        boolean[] arr;
        boolean[] init;
        String tree;

        init = new boolean[ALPH];
        arr = new boolean[ALPH];
        skillLen = skill.length();
        for (i = 1; i < skillLen; i++) {
            init[skill.charAt(i) - DIFF] = true;
        }
        cnt = 0;
        size = skill_trees.length;
        loop:
        for (i = 0; i < size; i++) {
            tree = skill_trees[i];
            idx = 0;
            cur = skill.charAt(idx) - DIFF;
            System.arraycopy(init, 0, arr, 0, ALPH);
            len = tree.length();
            for (j = 0; j < len; j++) {
                ch = tree.charAt(j) - DIFF;
                if (arr[ch]) {
                    continue loop;
                }
                if (ch == cur) {
                    if (++idx < skillLen) {
                        cur = skill.charAt(idx) - DIFF;
                        arr[cur] = false;
                    }
                }
            }
            if (j == len) {
                cnt++;
            }
        }
        return cnt;
    }
}
