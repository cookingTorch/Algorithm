import java.io.*;

public class Main {
    private static long facto(int n) {
        if (n == 0)
            return 1;
        else return facto(n - 1) * n;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n;

        n = Integer.parseInt(br.readLine());
        sb.append(facto(n));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}