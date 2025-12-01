class Solution {
    private static final int A = 'A';
    private static final int E = 'E';
    private static final int I = 'I';
    private static final int O = 'O';
    private static final int U = 'U';

    public int solution(String word) {
        int i;
        int len;
        int cnt;
        int add;
        int diff;
        
        len = word.length();
        cnt = len;
        add = 0;
        diff = 1;
        for (i = 4; i >= len; i--) {
            add += diff;
            diff *= 5;
        }
        for (; i >= 0; i--) {
            add += diff;
            cnt += add * switch (word.charAt(i)) {
                case A -> 0;
                case E -> 1;
                case I -> 2;
                case O -> 3;
                case U -> 4;
                default -> 5;
            };
            diff *= 5;
        }
        return cnt;
    }
}
