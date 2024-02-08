import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, i, j, num, prev, curr, next;
		int[] arr, left, right;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n + 1];
		left = new int[n + 1];
		right = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (i = 1; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			left[i + 1] = i;
			right[i] = i + 1;
		}
		arr[n] = Integer.parseInt(st.nextToken());
		right[n] = 1;
		left[1] = n;
		curr = 1;
		sb.append(curr).append(' ');
		prev = left[curr];
		next = right[curr];
		right[prev] = next;
		left[next] = prev;
		for (i = 0; i < n - 1; i++) {
			prev = left[curr];
			next = right[curr];
			num = arr[curr];
			if (num > 0) {
				for (j = 0; j < num; j++) {
					curr = right[curr];
				}
			} else {
				for (j = 0; j > num; j--) {
					curr = left[curr];
				}
			}
			sb.append(curr).append(' ');
			prev = left[curr];
			next = right[curr];
			right[prev] = next;
			left[next] = prev;
		}
		System.out.print(sb);
	}
}
