import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int find(int[] bucket, int n, int num) {
		int left, right, mid;
		
		left = 0;
		right = n;
		while (left < right) {
			mid = (left + right) / 2;
			if (bucket[mid] < num) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}
	
	private static int count(int[] bucket, int n, int k) {
		int left, right, mid;
		
		left = 0;
		right = n;
		while (left < right) {
			mid = (left + right) / 2;
			if (bucket[mid] <= k) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return n - right;
	}
	
	private static int sum(int[] arr, int[][] bucket, int left, int right, int k, int block, int n) {
		int val, i;
		
		val = 0;
		if (left / block == right / block) {
			for (i = left; i <= right; i++) {
				if (arr[i] > k) {
					val++;
				}
			}
			return val;
		}
		while (left % block != 0) {
			if (arr[left++] > k) {
				val++;
			}
		}
		while (right % block != block - 1) {
			if (arr[right--] > k) {
				val++;
			}
		}
		for (i = left / block; i <= right / block; i++) {
			val += count(bucket[i], block, k);
		}
		return val;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n, m, q, block, i, j, k, v, l, idx;
		int[] arr;
		int[][] bucket;
		
		n = Integer.parseInt(br.readLine());
		block = (int) Math.sqrt(n);
		arr = new int[n];
		bucket = new int[(n / block) + 1][block];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			bucket[i / block][i % block] = arr[i];
		}
		for (i = 0; i < bucket.length; i++) {
			Arrays.sort(bucket[i]);
		}
		m = Integer.parseInt(br.readLine());
		for (l = 0; l < m; l++) {
			st = new StringTokenizer(br.readLine());
			q = Integer.parseInt(st.nextToken());
			i = Integer.parseInt(st.nextToken()) - 1;
			if (q == 1) {
				v = Integer.parseInt(st.nextToken());
				idx = find(bucket[i / block], block, arr[i]);
				bucket[i / block][idx] = v;
				arr[i] = v;
				Arrays.sort(bucket[i / block]);
			} else {
				j = Integer.parseInt(st.nextToken()) - 1;
				k = Integer.parseInt(st.nextToken());
				sb.append(sum(arr, bucket, i, j, k, block, n)).append('\n');
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}