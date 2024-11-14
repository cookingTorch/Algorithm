import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int DIFF = 10_000_000;
    private static final char TRUE = '1';
    private static final char FALSE = '0';
    private static final char SPACE = ' ';

    public static void main(String[] args) throws IOException {
        int n;
        int m;
        boolean[] contains;
        StringBuilder sb;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        contains = new boolean[(DIFF << 1) + 1];
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ", false);
        while (n-- > 0) {
            contains[Integer.parseInt(st.nextToken()) + DIFF] = true;
        }
        m = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        st = new StringTokenizer(br.readLine(), " ", false);
        while (m-- > 0) {
            if (contains[Integer.parseInt(st.nextToken()) + DIFF]) {
                sb.append(TRUE).append(SPACE);
            } else {
                sb.append(FALSE).append(SPACE);
            }
        }
        System.out.print(sb.toString());
    }
}
