import java.util.Arrays;

class Solution {
    private static final int END = 0;
    private static final int START = 1;
    private static final int DIFF = '0';

    private static final class Node implements Comparable<Node> {
        int time;
        int state;

        Node(String time, int state) {
            if (state == END) {
                this.time = timeToInt(time) + 10;
            } else {
                this.time = timeToInt(time);
            }
            this.state = state;
        }

        private static int timeToInt(String str) {
            return (str.charAt(0) - DIFF) * 600 + (str.charAt(1) - DIFF) * 60 + (str.charAt(3) - DIFF) * 10 + (str.charAt(4) - DIFF);
        }

        @Override
        public int compareTo(Node o) {
            if (time == o.time) {
                return state - o.state;
            }
            return time - o.time;
        }
    }

    public int solution(String[][] book_time) {
        int i;
        int len;
        int cur;
        int max;
        int size;
        Node[] arr;

        len = book_time.length;
        size = len << 1;
        arr = new Node[size];
        for (i = 0; i < len; i++) {
            arr[i] = new Node(book_time[i][0], START);
            arr[len + i] = new Node(book_time[i][1], END);
        }
        Arrays.sort(arr, 0, size);
        cur = 0;
        max = 0;
        for (i = 0; i < size; i++) {
            if (arr[i].state == START) {
                if (++cur > max) {
                    max = cur;
                }
            } else {
                cur--;
            }
        }
        return max;
    }
}
