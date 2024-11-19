import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private static final int SIZE = 1_000_001;
    private static final char[] YES = {'Y', 'E', 'S'};
    private static final char[] NO = {'N', 'O'};

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int i;
        int num;
        int thr;
        int[] cnt;
        ArrayDeque<Integer> q;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        thr = (9 * (m + 1)) / 10;
        q = new ArrayDeque<>(m);
        cnt = new int[SIZE];
        st = new StringTokenizer(br.readLine(), " ", false);
        for (i = 1; i < m; i++) {
            num = Integer.parseInt(st.nextToken());
            if (++cnt[num] == thr) {
                System.out.print(YES);
                return;
            }
            q.addLast(num);
        }
        for (--i; i < n; i++) {
            num = Integer.parseInt(st.nextToken());
            if (++cnt[num] == thr) {
                System.out.print(YES);
                return;
            }
            q.addLast(num);
            cnt[q.pollFirst()]--;
        }
        System.out.print(NO);
    }
}
