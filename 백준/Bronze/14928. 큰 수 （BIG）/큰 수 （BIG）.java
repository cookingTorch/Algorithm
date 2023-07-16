import java.io.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n, i, len, ans;
        String str;

        str = br.readLine();
        ans = 0;
        len = str.length();
        for (i = 0; i < len; i++) {
            ans *= 10;
            ans += str.charAt(i) - '0';
            ans %= 20000303;
        }

        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}