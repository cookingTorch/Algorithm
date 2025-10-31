import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

class Solution {
    private static final String DELIM = " :";

    private static final class Car implements Comparable<Car> {
        private static final int IN = 2;
        private static final int NIL = -1;
        private static final int MAX = timeToInt("23", "59");

        int in;
        int sum;
        int num;

        Car(String h, String m, int num) {
            in = timeToInt(h, m);
            this.num = num;
        }

        private static int timeToInt(String h, String m) {
            return Integer.parseInt(h) * 60 + Integer.parseInt(m);
        }

        private void in(String h, String m) {
            in = timeToInt(h, m);
        }

        private void out(String h, String m) {
            sum += timeToInt(h, m) - in;
            in = NIL;
        }

        void inOut(String h, String m, int io) {
            if (io == IN) {
                in(h, m);
            } else {
                out(h, m);
            }
        }

        int getSum() {
            if (in != NIL) {
                sum += MAX - in;
            }
            return sum;
        }

        @Override
        public int compareTo(Car o) {
            return num - o.num;
        }
    }
    public int[] solution(int[] fees, String[] records) {
        int i;
        int io;
        int len;
        int num;
        int[] ans;
        String h;
        String m;
        HashMap<Integer, Car> map;
        ArrayList<Car> cars;
        StringTokenizer st;

        map = new HashMap<>();
        len = records.length;
        for (i = 0; i < len; i++) {
            st = new StringTokenizer(records[i], DELIM, false);
            h = st.nextToken();
            m = st.nextToken();
            num = Integer.parseInt(st.nextToken());
            io = st.nextToken().length();
            if (map.containsKey(num)) {
                map.get(num).inOut(h, m, io);
            } else {
                map.put(num, new Car(h, m, num));
            }
        }
        cars = new ArrayList<>(map.values());
        Collections.sort(cars);
        len = cars.size();
        ans = new int[len];
        for (i = 0; i < len; i++) {
            ans[i] = fees[1] + (int) Math.ceil((double) Math.max(0, (cars.get(i).getSum() - fees[0])) / fees[2]) * fees[3];
        }
        return ans;
    }
}
