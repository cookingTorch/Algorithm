import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static int s;
    private static int cnt;
    private static int[] arr;

    private static void dfs(int depth, int sum) {
        int i;

        if (depth == n) {
            if (sum == s) {
                cnt++;
            }
            return;
        }
        for (i = n; i > depth; i--) {
            dfs(i, sum + arr[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        int i;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        st = new StringTokenizer(br.readLine(), " ", false);
        for (i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        cnt = 0;
        dfs(-1, 0);
        if (s == 0) {
            cnt--;
        }
        System.out.print(cnt);
    }
}
