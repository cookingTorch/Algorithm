import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_POS = 1_000;

    public static void main(String[] args) throws IOException {
        int n;
        int i;
        int max;
        int pos;
        int end;
        int sum;
        int prev;
        int start;
        int height;
        int[] arr;
        BufferedReader br;
        StringTokenizer st;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[MAX_POS + 2];
        start = n;
        end = 0;
        max = 0;
        for (i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ", false);
            pos = Integer.parseInt(st.nextToken());
            height = Integer.parseInt(st.nextToken());
            if (height > max) {
                start = pos;
                end = pos;
                max = height;
            } else if (height == max) {
                start = Math.min(start, pos);
                end = Math.max(end, pos);
            }
            arr[pos] = height;
        }
        sum = 0;
        prev = 0;
        for (i = 1; i <= start; i++) {
            if (arr[i] > arr[prev]) {
                sum += arr[prev] * (i - prev);
                prev = i;
            }
        }
        sum += max * (end - start + 1);
        prev = MAX_POS + 1;
        for (i = MAX_POS; i >= end; i--) {
            if (arr[i] > arr[prev]) {
                sum += arr[prev] * (prev - i);
                prev = i;
            }
        }
        System.out.print(sum);
    }
}
