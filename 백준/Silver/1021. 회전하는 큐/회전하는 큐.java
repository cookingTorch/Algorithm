import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int i;
        int num;
        int idx;
        int cnt;
        int moves;
        BufferedReader br;
        StringTokenizer st;
        ArrayDeque<Integer> dq;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dq = new ArrayDeque<>(n);
        for (i = 1; i <= n; i++) {
            dq.addLast(i);
        }
        cnt = 0;
        st = new StringTokenizer(br.readLine());
        while (m-- > 0) {
            idx = Integer.parseInt(st.nextToken());
            moves = 0;
            while ((num = dq.pollFirst()) != idx) {
                dq.addLast(num);
                moves++;
            }
            if (moves < n - moves) {
                cnt += moves;
            } else {
                cnt += n - moves;
            }
            n--;
        }
        System.out.print(cnt);
    }
}
