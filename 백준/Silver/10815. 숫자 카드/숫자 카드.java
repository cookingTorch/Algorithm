import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    private static final char[] TRUE = {'1', ' '};
    private static final char[] FALSE = {'0', ' '};

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        HashSet<String> set;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        set = new HashSet<>(n);
        st = new StringTokenizer(br.readLine(), " ", false);
        while (n-- > 0) {
            set.add(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine(), " ", false);
        while (m-- > 0) {
            if (set.contains(st.nextToken())) {
                sb.append(TRUE);
            } else {
                sb.append(FALSE);
            }
        }
        System.out.print(sb.toString());
    }
}
