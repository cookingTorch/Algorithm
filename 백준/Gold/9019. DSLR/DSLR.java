import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static class pair {
        String c;
        int n;

        pair(String c, int n) {
            this.c = c;
            this.n = n;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int t, testCase, a, b, i;
        boolean[] visited = new boolean[10000];
        Queue<pair> q = new LinkedList<>();
        pair node, next;

        testCase = Integer.parseInt(br.readLine());
        for (t = 0; t < testCase; t++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            for (i = 0; i < 10000; i++)
                visited[i] = false;
            visited[a] = true;
            q.add(new pair("", a));
            while (!q.isEmpty()) {
                node = q.poll();
                next = new pair(node.c + "D", (2 * node.n) % 10000);
                if (next.n == b) {
                    sb.append(next.c).append("\n");
                    break;
                }
                if (!visited[next.n]) {
                    visited[next.n] = true;
                    q.add(next);
                }
                next = new pair(node.c + "S", (node.n + 9999) % 10000);
                if (next.n == b) {
                    sb.append(next.c).append("\n");
                    break;
                }
                if (!visited[next.n]) {
                    visited[next.n] = true;
                    q.add(next);
                }
                next = new pair(node.c + "L", ((10 * node.n) + (node.n / 1000)) % 10000);
                if (next.n == b) {
                    sb.append(next.c).append("\n");
                    break;
                }
                if (!visited[next.n]) {
                    visited[next.n] = true;
                    q.add(next);
                }
                next = new pair(node.c + "R", ((node.n + ((node.n % 10) * 10000)) / 10) % 10000);
                if (next.n == b) {
                    sb.append(next.c).append("\n");
                    break;
                }
                if (!visited[next.n]) {
                    visited[next.n] = true;
                    q.add(next);
                }
            }
            q.clear();
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}