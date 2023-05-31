import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	private static int rangeS = 0;
	private static int[] newIdx, rangeE, depth, tree;
	private static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
	
	// 새 index와 범위 부여
	private static void assignNewIdx(int node, int from) {
		int next, i;
		newIdx[node] = ++rangeS;
		rangeE[node] = rangeS;
		depth[node] = depth[from] + 1;
		for (i = 0; i < adj.get(node).size(); i++) {
			next = adj.get(node).get(i);
			if (next != from) {
				assignNewIdx(next, node);
				rangeE[node] = Math.max(rangeE[node], rangeE[next]);
			}
		}
	}
	
	// 트리 업데이트
	private static void update(int node, int start, int end, int idx) {
		tree[node]++;
		if (start < end) {
			int mid = (start + end) / 2;
			if (idx <= mid) {
				update(node * 2, start, mid, idx);
			}
			else {
				update(node * 2 + 1, mid + 1, end, idx);
			}
		}
	}
	
	// 구간 합
	private static long query(int node, int start, int end, int left, int right) {
		if (left <= start && end <= right) {
			return tree[node];
		}
		else if (end < left || right < start) {
			return 0;
		}
		else {
			int mid = (start + end) / 2;
			return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int n, c, q, i, city1, city2, queryNum, city;
		
		str = br.readLine();
		st = new StringTokenizer(str);
		n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		// 간선 입력
		for (i = 0; i <= n; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < n - 1; i++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			city1 = Integer.parseInt(st.nextToken());
			city2 = Integer.parseInt(st.nextToken());
			adj.get(city1).add(city2);
			adj.get(city2).add(city1);
		}
		
		// 세그먼트 트리로 변환
		newIdx = new int[n + 1];
		rangeE = new int[n + 1];
		depth = new int[n + 1];
		tree = new int[4 * n + 1];
		assignNewIdx(c, 0);
		
		// 쿼리 수행
		str = br.readLine();
		q = Integer.parseInt(str);
		for (i = 0; i < q; i++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			queryNum = Integer.parseInt(st.nextToken());
			city = Integer.parseInt(st.nextToken());
			
			// 1 번 쿼리
			if (queryNum == 1) {
				update(1, 1, n, newIdx[city]);
			}
			
			// 2 번 쿼리
			else {
				sb.append(depth[city] * query(1, 1, n, newIdx[city], rangeE[city])).append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}