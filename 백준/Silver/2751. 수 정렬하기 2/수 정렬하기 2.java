import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final char LINE_FEED = '\n';
    private static final int MAX = 1_000_000;
    private static final int SIZE = (MAX << 1) + 1;

    public static void main(String[] args) throws IOException {
        int n;
        int i;
        boolean[] visited;
        StringBuilder sb;
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visited = new boolean[SIZE];
        for (i = 0; i < n; i++) {
            visited[Integer.parseInt(br.readLine()) + MAX] = true;
        }
        sb = new StringBuilder();
        for (i = 0; i < SIZE; i++) {
            if (visited[i]) {
                sb.append(i - MAX).append(LINE_FEED);
            }
        }
        System.out.print(sb.toString());
    }
}
