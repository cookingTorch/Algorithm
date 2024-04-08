import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, h, min, cnt, i;
		int[] arr;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		arr = new int[h];
		n >>= 1;
		arr[0] = n;
		for (i = 0; i < n; i++) {
			arr[Integer.parseInt(br.readLine())]--;
			arr[h - Integer.parseInt(br.readLine())]++;
		}
		min = arr[0];
		cnt = 1;
		for (i = 1; i < h; i++) {
			arr[i] += arr[i - 1];
			if (arr[i] == min) {
				cnt++;
			} else if (arr[i] < min) {
				min = arr[i];
				cnt = 1;
			}
		}
		
		sb.append(min).append(' ').append(cnt);
		System.out.print(sb);
	}
}
