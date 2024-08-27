import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_N = 200_000;
    private static final String BORING = "boring\n";
    private static final String NON_BORING = "non-boring\n";

    private static int[] left;
    private static int[] right;
    private static HashMap<Integer, Integer> map;
    private static BufferedReader br;

    private static boolean dfs(int l, int r) {
        int i;
        int j;

        if (l >= r) {
            return true;
        }
        for (i = l, j = r; i <= j; i++, j--) {
            if (left[i] < l && right[i] > r) {
                return dfs(l, i - 1) && dfs(i + 1, r);
            }
            if (left[j] < l && right[j] > r) {
                return dfs(l, j - 1) && dfs(j + 1, r);
            }
        }
        return false;
    }

    private static boolean solution() throws IOException {
        int n;
        int i;
        int num;
        StringTokenizer st;

        map.clear();
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (i = 0; i < n; i++) {
            num = Integer.parseInt(st.nextToken());
            if (map.containsKey(num)) {
                left[i] = map.get(num);
                map.put(num, i);
            } else {
                left[i] = -1;
                map.put(num, i);
            }
        }
        for (i = 0; i < n; i++) {
            right[i] = n;
            if (left[i] != -1) {
                right[left[i]] = i;
            }
        }
        return dfs(0, n - 1);
    }

    public static void main(String[] args) throws IOException {
        int t;
        int i;
        StringBuilder sb;

        left = new int[MAX_N];
        right = new int[MAX_N];
        map = new HashMap<>();
        br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        while (t-- > 0) {
            sb.append(solution() ? NON_BORING : BORING);
        }
        System.out.print(sb);
    }
}
