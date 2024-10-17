import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private static final int QUERY0 = '0';
    private static final int QUERY1 = '1';
    private static final int QUERY2 = '2';
    private static final int REVERSE = -1;

    public static void main(String[] args) throws IOException {
        int n;
        int q;
        int k;
        int i;
        int lastSort;
        int[] queries;
        boolean forward;
        boolean[] exists;
        ArrayDeque<Integer> dq;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        lastSort = -1;
        queries = new int[q];
        for (i = 0; i < q; i++) {
            switch (br.read()) {
                case QUERY0:
                    br.read();
                    queries[i] = Integer.parseInt(br.readLine());
                    break;
                case QUERY1:
                    lastSort = i;
                    br.read();
                    break;
                case QUERY2:
                    queries[i] = REVERSE;
                    br.read();
                    break;
            }
        }
        exists = new boolean[n + 1];
        for (i = 0; i < lastSort; i++) {
            if (queries[i] > 0) {
                exists[queries[i]] = true;
            }
        }
        dq = new ArrayDeque<>(q);
        if (lastSort > 0) {
            for (i = 1; i <= n; i++) {
                if (exists[i]) {
                    dq.addLast(i);
                }
            }
        }
        forward = true;
        for (i = lastSort + 1; i < q; i++) {
            if (queries[i] == REVERSE) {
                forward ^= true;
            } else if (forward) {
                dq.addFirst(queries[i]);
            } else {
                dq.addLast(queries[i]);
            }
        }
        if (forward) {
            for (; --k > 0; dq.pollFirst());
            System.out.print(dq.peekFirst());
        } else {
            for (; --k > 0; dq.pollLast());
            System.out.print(dq.peekLast());
        }
    }
}
