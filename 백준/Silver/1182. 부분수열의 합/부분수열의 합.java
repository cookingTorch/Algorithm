import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static int s;
    private static int cnt;
    private static int[] arr;

    private static void dfs(int idx, int sum) {
        if (idx == n) {
            if (sum + arr[idx] == s) {
                cnt++;
            }
            return;
        }
        dfs(idx + 1, sum);
        if ((sum += arr[idx]) == s) {
            cnt++;
        }
        dfs(idx + 1, sum);
    }

    public static void main(String[] args) throws IOException {
        int i;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine(), " ", false);
        for (i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        cnt = 0;
        n--;
        dfs(0, 0);
        System.out.print(cnt);
    }
}
