import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int n;
        int m;
        int i;
        BigInteger res;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        if (m > n >> 1) {
            m = n - m;
        }
        res = BigInteger.ONE;
        for (i = n - m + 1; i <= n; i++) {
            res = res.multiply(BigInteger.valueOf(i));
        }
        for (i = m; i > 1; i--) {
            res = res.divide(BigInteger.valueOf(i));
        }
        System.out.println(res.toString());
    }
}
