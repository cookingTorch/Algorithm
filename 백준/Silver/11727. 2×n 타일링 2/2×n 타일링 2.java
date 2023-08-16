import java.io.*;

public class Main {
    private static int dp(int[] memo, int n) {
        if (memo[n] == 0)
            memo[n] = ((((dp(memo, n - 2) % 10007) * 2) % 10007) + (dp(memo, n - 1) % 10007)) % 10007;
        return memo[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n;
        int[] memo;

        n = Integer.parseInt(br.readLine());
        memo = new int[n + 1];
        memo[0] = 1;
        memo[1] = 1;
        sb.append(dp(memo, n) % 10007);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}