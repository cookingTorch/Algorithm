import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX_SIZE = 1_000_000;
    private static final char LINE_FEED = '\n';
    private static final String DELIM = " ";

    private static int solution(int n, int[] arr, StringTokenizer st) {
        int a;
        int b;
        int c;
        int l;
        int r;
        int i;
        int sum;
        int diff;
        int size;

        sum = 0;
        size = 0;
        for (i = 0; i < n; i++) {
            sum += arr[size++] = Integer.parseInt(st.nextToken());
            while (size >= 3) {
                a = arr[size - 3];
                b = arr[size - 2];
                c = arr[size - 1];
                if (b >= Math.max(a, c)) {
                    arr[(size -= 2) - 1] = a + c - b;
                } else {
                    break;
                }
            }
        }
        diff = 0;
        l = 0;
        r = size - 1;
        while (l <= r) {
            if (arr[l] > arr[r]) {
                diff += arr[l++];
            } else {
                diff += arr[r--];
            }
            if (l > r) {
                break;
            }
            if (arr[l] > arr[r]) {
                diff -= arr[l++];
            } else {
                diff -= arr[r--];
            }
        }
        return sum + diff >>> 1;
    }

    public static void main(String[] args) throws IOException {
        int t;
        int[] arr;
        StringBuilder sb;
        BufferedReader br;

        arr = new int[MAX_SIZE];
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            sb.append(solution(Integer.parseInt(br.readLine()), arr, new StringTokenizer(br.readLine(), DELIM, false))).append(LINE_FEED);
        }
        System.out.print(sb.toString());
    }
}
