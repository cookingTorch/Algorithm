class Solution {
    private static final int B = 6;
    private static final int E = 10;
    private static final int R = 10;
    private static final int IN = 13;
    private static final int SIZE = 10_000;

    private static final class Car {
        private static final int NIL = 0;
        private static final int MAX = timeToInt("23:59");

        int out;
        int sum;

        Car(int time, boolean in) {
            if (in) {
                sum = MAX - time;
            } else {
                out = time;
            }
        }

        void inOut(int time) {
            if (out == NIL) {
                out = time;
            } else {
                sum += out - time;
                out = NIL;
            }
        }
    }

    private static int timeToInt(String time) {
        return time.charAt(0) * 600 + time.charAt(1) * 60 + time.charAt(3) * 10 + time.charAt(4);
    }

    public int[] solution(int[] fees, String[] records) {
        int i;
        int j;
        int len;
        int num;
        int cnt;
        int time;
        int base;
        int unit;
        int baseFee;
        int unitFee;
        int[] ans;
        Car[] cars;

        cnt = 0;
        cars = new Car[SIZE];
        len = records.length;
        for (i = len - 1; i >= 0; i--) {
            time = timeToInt(records[i]);
            num = Integer.parseInt(records[i], B, E, R);
            if (cars[num] == null) {
                cars[num] = new Car(time, records[i].length() == IN);
                cnt++;
            } else {
                cars[num].inOut(time);
            }
        }
        base = fees[0];
        baseFee = fees[1];
        unit = fees[2];
        unitFee = fees[3];
        ans = new int[cnt];
        for (i = 0, j = 0; i < SIZE; i++) {
            if (cars[i] != null) {
                ans[j++] = baseFee + Math.max(0, (cars[i].sum - base + unit - 1)) / unit * unitFee;
            }
        }
        return ans;
    }
}




