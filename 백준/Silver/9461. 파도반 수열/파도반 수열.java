import java.io.*;

public class Main {
    private static long dp(long[] memo, int n) {
        if (memo[n] == 0)
            memo[n] = dp(memo, n - 1) + dp(memo, n - 5);
        return memo[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int testCase, t, n;
        long[] memo;

        testCase = Integer.parseInt(br.readLine());
        for (t = 0; t < testCase; t++) {
            if (t > 0)
                sb.append("\n");
            n = Integer.parseInt(br.readLine());
            memo = new long[n + 1];
            memo[1] = 1;
            if (n > 1)
                memo[2] = 1;
            if (n > 2)
                memo[3] = 1;
            if (n > 3)
                memo[4] = 2;
            if (n > 4)
                memo[5] = 2;
            sb.append(dp(memo, n));
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}