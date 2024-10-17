import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    private static final int QUERY0 = '0';
    private static final int QUERY1 = '1';
    private static final int QUERY2 = '2';
    private static final int NIL = -1;
    private static final int SORT = -2;
    private static final int REVERSE = -3;

    public static void main(String[] args) throws IOException {
        int n;
        int q;
        int k;
        int i;
        int lastSort;
        int[] queries;
        boolean forward;
        boolean[] exists;
        LinkedList<Integer> list;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        lastSort = NIL;
        queries = new int[q];
        for (i = 0; i < q; i++) {
            switch (br.read()) {
                case QUERY0:
                    br.read();
                    queries[i] = Integer.parseInt(br.readLine());
                    break;
                case QUERY1:
                    queries[i] = SORT;
                    lastSort = i;
                    br.read();
                    break;
                case QUERY2:
                    queries[i] = REVERSE;
                    br.read();
                    break;
            }
        }
        forward = true;
        exists = new boolean[n + 1];
        for (i = 0; i < lastSort; i++) {
            if (queries[i] == REVERSE) {
                forward ^= true;
            } else if (queries[i] != SORT) {
                exists[queries[i]] = true;
            }
        }
        list = new LinkedList<>();
        if (lastSort != NIL) {
            if (forward) {
                for (i = 1; i <= n; i++) {
                    if (exists[i]) {
                        list.addLast(i);
                    }
                }
            } else {
                for (i = n; i > 0; i--) {
                    if (exists[i]) {
                        list.addLast(i);
                    }
                }
            }
        }
        for (i = lastSort + 1; i < q; i++) {
            if (queries[i] == REVERSE) {
                forward ^= true;
            } else if (queries[i] != SORT) {
                if (forward) {
                    list.addFirst(queries[i]);
                } else {
                    list.addLast(queries[i]);
                }
            }
        }
        if (forward) {
            System.out.print(list.get(k - 1));
        } else {
            System.out.print(list.get(list.size() - k));
        }
    }
}
