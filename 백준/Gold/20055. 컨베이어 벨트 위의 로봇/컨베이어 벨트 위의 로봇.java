import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int j;
		int k;
		int end;
		int len;
		int next;
		int start;
		int[] arr;
		boolean[] robot;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		len = 2 * n;
		arr = new int[len];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < len; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		start = 0;
		robot = new boolean[len];
		for (i = 0; k > 0; i++) {
			start = (start + len - 1) % len;
			end = (start + n - 1) % len;
			robot[end] = false;
			for (j = (end + len - 1) % len; j != start; j = (j + len - 1) % len) {
				next = (j + 1) % len;
				if (robot[j] && !robot[next] && arr[next] != 0) {
					robot[j] = false;
					robot[next] = true;
					if (--arr[next] == 0) {
						k--;
					}
				}
			}
			robot[end] = false;
			if (arr[start] != 0) {
				robot[start] = true;
				if (--arr[start] == 0) {
					k--;
				}
			}
		}
		System.out.print(i);
	}
}
