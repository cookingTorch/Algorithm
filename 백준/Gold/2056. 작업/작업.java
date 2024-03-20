import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n, num, weight, max, i, j;
        int[] distance;

        n = Integer.parseInt(br.readLine());
        distance = new int[n + 1];
        max = 0;
        for (i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            distance[i] = weight = Integer.parseInt(st.nextToken());
            num = Integer.parseInt(st.nextToken());
            for (j = 0; j < num; j++) {
                distance[i] = Math.max(distance[i], distance[Integer.parseInt(st.nextToken())] + weight);
            }
            if (max < distance[i]) {
                max = distance[i];
            }
        }
        System.out.print(max);
    }
}