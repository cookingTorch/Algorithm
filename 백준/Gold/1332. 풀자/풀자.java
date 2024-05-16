import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		int i;
		int n;
		int v;
		int num;
		int min;
		int max;
		int left;
		int right;
		int minIdx;
		int maxIdx;
		int[] arr;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		min = INF;
		max = -1;
		minIdx = 0;
		maxIdx = 0;
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (min > arr[i]) {
				min = arr[i];
				minIdx = i;
			}
			if (max < arr[i]) {
				max = arr[i];
				maxIdx = i;
			}
			if (max - min >= v) {
				break;
			}
		}
		if (i == n) {
			System.out.print(n);
			return;
		}
		if (minIdx < maxIdx) {
			if (((maxIdx - minIdx) & 1) == 1) {
				num = arr[maxIdx];
				for (i = maxIdx - 2; i >= 0; i -= 2) {
					if (num - arr[i] >= v) {
						minIdx = i;
						break;
					}
				}
			}
			left = minIdx;
			right = maxIdx;
		} else {
			if (((minIdx - maxIdx) & 1) == 1) {
				num = arr[minIdx];
				for (i = minIdx - 2; i >= 0; i -= 2) {
					if (arr[i] - num >= v) {
						maxIdx = i;
						break;
					}
				}
			}
			left = maxIdx;
			right = minIdx;
		}
		System.out.print(((left + 1 >> 1) + 1) + (right - left + 1 >> 1));
	}
}
