import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        BigInteger a, b;

        st = new StringTokenizer(br.readLine());
        a = new BigInteger(st.nextToken());
        b = new BigInteger(st.nextToken());
        sb.append(a.add(b));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}