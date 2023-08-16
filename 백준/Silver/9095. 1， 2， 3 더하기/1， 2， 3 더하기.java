import java.io.*;

public class Main {
    private static int dp(int[] memo, int n) {
        if (memo[n] != 0)
            return memo[n];
        memo[n] = dp(memo, n - 3) + dp(memo, n - 2) + dp(memo, n - 1);
        return memo[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int testCase, t, n;
        int[] memo;

        testCase = Integer.parseInt(br.readLine());
        for (t = 0; t < testCase; t++) {
            n = Integer.parseInt(br.readLine());
            memo = new int[n + 1];
            memo[0] = 1;
            memo[1] = 1;
            if (n > 1)
                memo[2] = 2;
            sb.append(dp(memo, n)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}