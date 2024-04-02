import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, left, right, sum, min, numL, numR, i;
		int[] arr;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		numL = 0;
		numR = 0;
		left = 0;
		right = n - 1;
		min = INF;
		while (left < right) {
			if (arr[left] >= 0) {
				if (Math.abs(arr[left] + arr[left + 1]) < min) {
					numL = arr[left];
					numR = arr[left + 1];
				}
				break;
			} else if (arr[right] <= 0) {
				if (Math.abs(arr[right - 1] + arr[right]) < min) {
					numL = arr[right - 1];
					numR = arr[right];
				}
				break;
			}
			sum = arr[left] + arr[right];
			if (Math.abs(sum) < min) {
				min = Math.abs(sum);
				numL = arr[left];
				numR = arr[right];
                if (min == 0) {
                    break;
                }
			}
			if (sum < 0) {
				left++;
			} else {
				right--;
			}
		}
		sb.append(numL).append(' ').append(numR);
		System.out.print(sb);
	}
}
