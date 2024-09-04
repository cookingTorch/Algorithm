import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int INIT = 3;
    private static final int SIZE = 100;
    private static final int HALF = SIZE >> 1;
    private static final int[] EMPTY = new int[SIZE];
    private static final char[] FAIL = {'-', '1'};

    private static final class Count implements Comparable<Count> {
        int num;
        int cnt;

        Count(int num) {
            this.num = num;
            this.cnt = 0;
        }

        @Override
        public int compareTo(Count o) {
            if (this.cnt == o.cnt) {
                return this.num - o.num;
            } else {
                return this.cnt - o.cnt;
            }
        }
    }

    private static int[][] arr;
    private static boolean[] visited;
    private static Count[] counts;
    private static Count[] temp;

    private static int rowUpdate(int[] arr) {
        int i;
        int size;

        size = 0;
        for (int num : arr) {
            if (num != 0) {
                if (!visited[num]) {
                    visited[num] = true;
                    temp[size++] = counts[num];
                }
                counts[num].cnt++;
            }
        }
        Arrays.sort(temp, 0, size);
        size = Math.min(size, HALF);
        for (i = 0; i < size; i++) {
            arr[i << 1] = temp[i].num;
            arr[(i << 1) + 1] = temp[i].cnt;
            visited[temp[i].num] = false;
            temp[i].cnt = 0;
        }
        if ((i <<= 1) < SIZE) {
            System.arraycopy(EMPTY, 0, arr, i, SIZE - i);
        }
        return size << 1;
    }

    private static int colUpdate(int idx) {
        int i;
        int num;
        int size;

        size = 0;
        for (i = 0; i < SIZE; i++) {
            if ((num = arr[i][idx]) != 0) {
                if (!visited[num]) {
                    visited[num] = true;
                    temp[size++] = counts[num];
                }
                counts[num].cnt++;
            }
        }
        Arrays.sort(temp, 0, size);
        size = Math.min(size, HALF);
        for (i = 0; i < size; i++) {
            arr[i << 1][idx] = temp[i].num;
            arr[(i << 1) + 1][idx] = temp[i].cnt;
            visited[temp[i].num] = false;
            temp[i].cnt = 0;
        }
        for (i <<= 1; i < SIZE; i++) {
            arr[i][idx] = 0;
        }
        return size << 1;
    }

    public static void main(String[] args) throws IOException {
        int r;
        int c;
        int k;
        int i;
        int j;
        int time;
        int rowSize;
        int colSize;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());
        arr = new int[SIZE][SIZE];
        for (i = 0; i < INIT; i++) {
            st = new StringTokenizer(br.readLine());
            for (j = 0; j < INIT; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[SIZE + 1];
        counts = new Count[SIZE + 1];
        for (i = 1; i <= SIZE; i++) {
            counts[i] = new Count(i);
        }
        temp = new Count[SIZE];
        rowSize = INIT;
        colSize = INIT;
        for (time = 0; arr[r][c] != k && time < SIZE; time++) {
            if (colSize >= rowSize) {
                rowSize = 0;
                for (i = 0; i < colSize; i++) {
                    rowSize = Math.max(rowSize, rowUpdate(arr[i]));
                }
            } else {
                colSize = 0;
                for (i = 0; i < rowSize; i++) {
                    colSize = Math.max(colSize, colUpdate(i));
                }
            }
        }
        if (arr[r][c] == k) {
            System.out.print(time);
        } else {
            System.out.print(FAIL);
        }
    }
}
