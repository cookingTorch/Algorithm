import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static int binarySearch(int[] arr, int target, int end) {
        int left = 0;
        int right = end;
        int mid;
        while (left < right) {
            mid = (left + right) >> 1;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine()), i, idx;
        int[] nums = new int[n];
        int[] temp = new int[n];
        int[] numIndex = new int[n];
        int[] ans = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int size = 0;
        for (i = 0; i < n; i++) {
            idx = binarySearch(temp, nums[i], size);
            temp[idx] = nums[i];
            numIndex[i] = idx;
            if (idx == size) {
                size++;
            }
        }

        int cnt = size - 1;
        for (i = n - 1; i >= 0; i--) {
            if (numIndex[i] == cnt) {
                ans[cnt--] = nums[i];
            }
            if (cnt < 0) {
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(size).append("\n");
        for (i = 0; i < size; i++) {
            sb.append(ans[i]).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}