import java.util.Arrays;

class Solution {
    private static final int D = 1 << 6;
    private static final int I = 1 << 3;
    private static final int CNT = 5;
    private static final int DIA = 'd';
    private static final int IRON = 'i';
    private static final int STONE = 's';

    private static final class Range implements Comparable<Range> {
        private static final int SI = 5;
        private static final int ID = 5;
        private static final int SD = 25;

        int w;
        int dCnt;
        int iCnt;
        int sCnt;

        Range(int dCnt, int iCnt, int sCnt) {
            this.dCnt = dCnt;
            this.iCnt = iCnt;
            this.sCnt = sCnt;
            w = dCnt * D | iCnt * I | sCnt;
        }

        @Override
        public int compareTo(Range o) {
            return o.w - w;
        }

        int dia() {
            return dCnt + iCnt + sCnt;
        }

        int iron() {
            return dCnt * ID + iCnt + sCnt;
        }

        int stone() {
            return dCnt * SD + iCnt * SI + sCnt;
        }
    }

    public int solution(int[] picks, String[] minerals) {
        int i;
        int j;
        int ch;
        int len;
        int end;
        int idx;
        int ans;
        int diff;
        int size;
        int pick;
        int dCnt;
        int iCnt;
        int sCnt;
        Range[] ranges;

        len = minerals.length;
        pick = picks[0] + picks[1] + picks[2];
        size = Math.min(pick, (len - 1) / CNT + 1);
        for (i = 2, diff = pick - size; i >= 0 && diff > 0; i--, diff = pick - size) {
            picks[i] = Math.max(0, picks[i] - diff);
            pick = picks[0] + picks[1] + picks[2];
        }
        ranges = new Range[size];
        for (i = 0; i < size; i++) {
            dCnt = 0;
            iCnt = 0;
            sCnt = 0;
            end = Math.min(len, (i + 1) * CNT);
            for (j = i * CNT; j < end; j++) {
                ch = minerals[j].charAt(0);
                if (ch == DIA) {
                    dCnt++;
                } else if (ch == IRON) {
                    iCnt++;
                } else if (ch == STONE) {
                    sCnt++;
                }
            }
            ranges[i] = new Range(dCnt, iCnt, sCnt);
        }
        Arrays.sort(ranges);
        idx = 0;
        ans = 0;
        pick = picks[0];
        for (i = 0; i < pick; i++) {
            ans += ranges[idx++].dia();
        }
        pick = picks[1];
        for (i = 0; i < pick; i++) {
            ans += ranges[idx++].iron();
        }
        pick = picks[2];
        for (i = 0; i < pick; i++) {
            ans += ranges[idx++].stone();
        }
        return ans;
    }
}
