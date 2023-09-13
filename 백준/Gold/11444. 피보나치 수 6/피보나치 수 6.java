import java.io.*;

public class Main {
    private static long[][] multi(long[][] a, long[][] b) {
        long[][] c = new long[2][2];

        c[0][0] = ((a[0][0] * b[0][0]) + (a[0][1] * b[1][0])) % 1000000007;
        c[0][1] = ((a[0][0] * b[0][1]) + (a[0][1] * b[1][1])) % 1000000007;
        c[1][0] = ((a[1][0] * b[0][0]) + (a[1][1] * b[1][0])) % 1000000007;
        c[1][1] = ((a[1][0] * b[0][1]) + (a[1][1] * b[1][1])) % 1000000007;
        return c;
    }

    private static long[][] pow(long[][] i, long n) {
        if (n == 0)
            return new long[2][2];
        if (n == 1)
            return i;
        if (n % 2 == 0) {
            long[][] sqrt = pow(i, n / 2);

            return multi(sqrt, sqrt);
        } else
            return multi(pow(i, n - 1), i);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        long[][] i = new long[][] {{1, 1}, {1, 0}};

        sb.append(pow(i, Long.parseLong(br.readLine()))[0][1]);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}