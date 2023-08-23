import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int m, i, j, x = 0;
        String order;
        HashSet<Integer> set = new HashSet<>();

        m = Integer.parseInt(br.readLine());
        for (i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            order = st.nextToken();
            if (!order.equals("all") && !order.equals("empty"))
                x = Integer.parseInt(st.nextToken());
            if (order.equals("add"))
                set.add(x);
            else if (order.equals("remove"))
                set.remove(x);
            else if (order.equals("check")) {
                if (set.contains(x))
                    sb.append(1);
                else
                    sb.append(0);
                sb.append("\n");
            }
            else if (order.equals("toggle")) {
                if (set.contains(x))
                    set.remove(x);
                else
                    set.add(x);
            }
            else if (order.equals("all")) {
                set.clear();
                for (j = 1; j <= 20; j++)
                    set.add(j);
            }
            else if (order.equals("empty"))
                set.clear();
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}