import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] arr;
	private static int[][] mergeSortTree;
	
	// merge
	private static int[] merge(int[] arr1, int[] arr2) {
		int i = 0, j = 0, idx = 0, length1 = arr1.length, length2 = arr2.length;
		int[] result = new int[length1 + length2];
		while (idx < length1 + length2) {
			if (j == length2 || (i < length1 && arr1[i] < arr2[j])) {
				result[idx++] = arr1[i++];
			}
			else {
				result[idx++] = arr2[j++];
			}
		}
		return result;
	}
	
	// upperBound 탐색
	private static int upperBound(int[] arr, int target) {
		int left = 0, right = arr.length, mid;
		while (left < right) {
			mid = (left + right) / 2;
			if (arr[mid] <= target) {
				left = mid + 1;
			}
			else {
				right = mid;
			}
		}
		return right;
	}
	
	// 트리 생성
	private static void buildTree(int node, int start, int end) {
		if (start < end) {
			int mid = (start + end) / 2;
			buildTree(node * 2, start, mid);
			buildTree(node * 2 + 1, mid + 1, end);
			mergeSortTree[node] = merge(mergeSortTree[node * 2], mergeSortTree[node * 2 + 1]);
		}
		else {
			mergeSortTree[node] = new int[] {arr[start]};
		}
	}
	
	// 쿼리
	private static int query(int node, int start, int end, int left, int right, int num) {
		if (left <= start && end <= right) {
			return mergeSortTree[node].length - upperBound(mergeSortTree[node], num);
		}
		else if (start > right || end < left) {
			return 0;
		}
		else {
			int mid = (start + end) / 2;
			return query(node * 2, start, mid, left, right, num) + query(node * 2 + 1, mid + 1, end, left, right, num);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int n, m, a, b, c, i, j, k, l, lastAns = 0;
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		// 수열 입력
		arr = new int[n + 1];
		str = br.readLine();
		st = new StringTokenizer(str);
		for (l = 1; l <= n; l++) {
			arr[l] = Integer.parseInt(st.nextToken());
		}
		
		// mergeSortTree 생성
		mergeSortTree = new int[4 * n + 1][];
		buildTree(1, 1, n);
		
		// 쿼리 수행
		str = br.readLine();
		m = Integer.parseInt(str);
		for (l = 0; l < m; l++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			i = a ^ lastAns;
			j = b ^ lastAns;
			k = c ^ lastAns;
			lastAns = query(1, 1, n, i, j, k);
			sb.append(lastAns).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}