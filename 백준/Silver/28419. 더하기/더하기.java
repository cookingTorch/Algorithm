import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final char[] FAIL = {'-', '1'};
    private static final String DELIM = " ";

    public static void main(String[] args) throws IOException {
        int n;
        long num;
        boolean isThree;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        if (n == 3) {
            isThree = true;
        } else {
            isThree = false;
        }
        st = new StringTokenizer(br.readLine(), DELIM, false);
        if ((n & 1) == 0) {
            num = 0L;
        } else {
            num = -Integer.parseInt(st.nextToken());
        }
        n >>= 1;
        while (n-- > 0) {
            num += Integer.parseInt(st.nextToken()) - Integer.parseInt(st.nextToken());
        }
        if (num < 0) {
            if (isThree) {
                System.out.println(FAIL);
            } else {
                System.out.print(-num);
            }
        } else {
            System.out.print(num);
        }
    }
}
