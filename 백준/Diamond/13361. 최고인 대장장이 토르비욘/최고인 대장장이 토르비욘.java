import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	private static final String DELIM = " ";

	private static int[] parent;

	private static final int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

	private static final void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u != v) {
			parent[v] = u;
		}
	}

	private static final int lowerBound(int[] arr, int size, int target) {
		int left;
		int right;
		int mid;

		left = 0;
		right = size;
		while (left < right) {
			mid = (left + right) >> 1;
			if (arr[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}

	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int m;
		int u;
		int v;
		int root;
		int[] s;
		int[] t;
		int[] values;
		int[] compressed;
		int[] vertexCnt;
		int[] edgeCnt;
		int[] vertexMax;
		long total;
		long widthCost;
		long[] vertexSum;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		s = new int[n];
		t = new int[n];
		values = new int[n << 1];
		total = 0L;
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), DELIM, false);
			s[i] = Integer.parseInt(st.nextToken());
			t[i] = Integer.parseInt(st.nextToken());
			values[i << 1] = s[i];
			values[(i << 1) + 1] = t[i];
			total += s[i] + t[i];
		}
		Arrays.sort(values);
		compressed = new int[n << 1];
		m = 0;
		for (i = 0; i < (n << 1); i++) {
			if (i == 0 || values[i] != values[i - 1]) {
				compressed[m++] = values[i];
			}
		}
		parent = new int[m];
		for (i = 0; i < m; i++) {
			parent[i] = i;
		}
		for (i = 0; i < n; i++) {
			u = lowerBound(compressed, m, s[i]);
			v = lowerBound(compressed, m, t[i]);
			union(u, v);
		}
		vertexCnt = new int[m];
		edgeCnt = new int[m];
		vertexMax = new int[m];
		vertexSum = new long[m];
		for (i = 0; i < m; i++) {
			root = find(i);
			vertexCnt[root]++;
			vertexSum[root] += compressed[i];
			vertexMax[root] = Math.max(vertexMax[root], compressed[i]);
		}
		for (i = 0; i < n; i++) {
			u = lowerBound(compressed, m, s[i]);
			root = find(u);
			edgeCnt[root]++;
		}
		widthCost = 0L;
		for (i = 0; i < m; i++) {
			if (vertexCnt[i] == 0) {
				continue;
			}
			if (edgeCnt[i] == vertexCnt[i] - 1) {
				widthCost += vertexSum[i] - vertexMax[i];
			} else {
				widthCost += vertexSum[i];
			}
		}
		System.out.println(total - widthCost);
	}
}