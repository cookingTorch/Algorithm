import java.util.*;

class Solution {
    private List<List<Integer[]>> timeTable(int k, int[][] reqs) {
        List<List<Integer[]>> result = new ArrayList<>();
        for (int idx = 0; idx <= k; idx++) {
            result.add(new ArrayList<>());
        }
        for (int[] req : reqs) {
            int start = req[0];
            int end = start + req[1];
            int type = req[2];
            result.get(type).add(new Integer[] { start, end });
        }
        return result;
    }
    private void calcTimeGap(int[] gapTable, List<Integer[]> timeTable) {
        for (int i = 0; i < gapTable.length; i++) {
            int maxPpl = i + 1;
            int count = 0;
            int gapTotal = 0;
            PriorityQueue<Integer> endQ = new PriorityQueue<>();
            for (Integer[] time : timeTable) {
                int nowStart = time[0];
                int nowEnd = time[1];
                while (!endQ.isEmpty() && nowStart >= endQ.peek()) {
                    endQ.poll();
                    count--;
                }
                if (endQ.isEmpty() || nowStart < endQ.peek()) {
                    count++;
                }
                if (count > maxPpl) {
                    int gap = endQ.poll() - nowStart;
                    nowEnd += gap;
                    gapTotal += gap;
                    count--;
                }
                endQ.add(nowEnd);
            }
            gapTable[i] = gapTotal;
        }
    }
    private int getMinTimeGap(int maxPpl, int[][] gapTable, int type) {
        int minTotal = Integer.MAX_VALUE;
        for (int i = 0; i <= maxPpl; i++) {
            int value = gapTable[type][i];
            if (type < gapTable.length - 1) {
                value += getMinTimeGap(maxPpl - i, gapTable, type + 1);
            }
            minTotal = Math.min(minTotal, value);
        }
        return minTotal;
    }

    public int solution(int k, int n, int[][] reqs) {
        int answer = 0;
        List<List<Integer[]>> timeTable = timeTable(k, reqs);
        int[][] gapTable = new int[k + 1][n - k + 1];
        for (int i = 1; i <= k; i++) {
            calcTimeGap(gapTable[i], timeTable.get(i));
        }
        answer = getMinTimeGap(n - k, gapTable, 1);
        return answer;
    }
}