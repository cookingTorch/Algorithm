import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n, i;
        int[] x, arr;
        HashMap<Integer, Integer> rank = new HashMap<>();

        n = Integer.parseInt(br.readLine());
        x = new int[n];
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (i = 0; i < n; i++)
            x[i] = arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        i = 0;
        for (int v : arr) {
            if (!rank.containsKey(v)) {
                rank.put(v, i);
                i++;
            }
        }
        for (int v : x)
            sb.append(rank.get(v)).append(' ');

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}