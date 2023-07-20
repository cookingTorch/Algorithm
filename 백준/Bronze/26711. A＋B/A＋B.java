import java.io.*;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        BigInteger a, b;

        a = new BigInteger(br.readLine());
        b = new BigInteger(br.readLine());
        sb.append(a.add(b).toString());

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}