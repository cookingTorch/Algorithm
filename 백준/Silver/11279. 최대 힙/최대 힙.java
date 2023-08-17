import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n, x, i;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        n = Integer.parseInt(br.readLine());
        for (i = 0; i < n; i++) {
            x = Integer.parseInt(br.readLine());
            if (x == 0) {
                if (pq.isEmpty())
                    sb.append("0\n");
                else
                    sb.append(pq.poll()).append("\n");
            } else {
               pq.add(x);
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}