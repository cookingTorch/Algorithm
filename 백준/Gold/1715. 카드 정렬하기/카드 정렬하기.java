import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        int n;
        int a;
        int b;
        int i;
        int idx;
        int[] arr;
        long cnt;
        ArrayDeque<Integer> q;
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1];
        for (i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        arr[n] = INF;
        Arrays.sort(arr, 0, n);
        q = new ArrayDeque<>();
        idx = 0;
        cnt = 0L;
        while (--n > 0) {
            if (q.isEmpty() || arr[idx] < q.peekFirst()) {
                a = arr[idx++];
            } else {
                a = q.pollFirst();
            }
            if (q.isEmpty() || arr[idx] < q.peekFirst()) {
                b = arr[idx++];
            } else {
                b = q.pollFirst();
            }
            cnt += a + b;
            q.addLast(a + b);
        }
        System.out.print(cnt);
    }
}
