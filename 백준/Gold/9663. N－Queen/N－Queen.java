import java.io.*;

public class Main {
    private static int getCnt(int[] queens, int col, int n) {
        int cnt, i;

        if (col == n)
            return 1;
        cnt = 0;
        for (queens[col] = 0; queens[col] < n; queens[col]++) {
            for (i = 0; i < col; i++)
                if (queens[col] == queens[i] || Math.abs(queens[col] - queens[i]) == Math.abs(col - i))
                    break;
            if (i == col)
                cnt += getCnt(queens, col + 1, n);
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n;
        int[] queens;

        n = Integer.parseInt(br.readLine());
        queens = new int[n];
        sb.append(getCnt(queens, 0, n));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}