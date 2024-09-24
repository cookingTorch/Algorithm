import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int n;
        int s;
        int i;
        int pos;
        int bit;
        int sum;
        int cnt;
        int[] arr;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ", false);
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine(), " ", false);
        for (i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        cnt = 0;
        for (i = (1 << n) - 1; i != 0; i--) {
            sum = 0;
            for (bit = i; bit != 0; bit ^= 1 << pos) {
                pos = Integer.numberOfTrailingZeros(bit);
                sum += arr[pos];
            }
            if (sum == s) {
                cnt++;
            }
        }
        System.out.print(cnt);
    }
}
