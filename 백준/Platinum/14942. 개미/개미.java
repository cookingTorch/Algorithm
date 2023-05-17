import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	private static int[] energy;
	private static int[] parents;
	private static int[] distance;
	private static int[] ans;
	private static ArrayList<Integer> roomNum = new ArrayList<>();
	private static ArrayList<Integer> cumulativeDistance = new ArrayList<>();
	private static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
	private static ArrayList<ArrayList<Integer>> adjDistance = new ArrayList<>();

	// 트리 순회
	private static void tree(int node) {
		int i, next, depth, left, right, mid;
		
		// 방 찾기 이분 탐색
		depth = roomNum.size();
		roomNum.add(node);
		cumulativeDistance.add(cumulativeDistance.get(depth - 1) + distance[node]);
		left = 1;
		right = cumulativeDistance.size();
		while (left < right) {
			mid = (left + right) / 2;
			if (cumulativeDistance.get(depth) - cumulativeDistance.get(mid) > energy[node]) {
				left = mid + 1;
			}
			else {
				right = mid;
			}
		}
		ans[node] = roomNum.get(right);
		
		// 자식 노드 순회
		for (i = 0; i < adj.get(node).size(); i++) {
			next = adj.get(node).get(i);
			if (next != parents[node]) {
				parents[next] = node;
				distance[next] = adjDistance.get(node).get(i);
				tree(next);
			}
		}
		roomNum.remove(depth);
		cumulativeDistance.remove(depth);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int n, a, b, c, i;
		
		str = br.readLine();
		n = Integer.parseInt(str);
		
		// 에너지 입력
		energy = new int[n + 1];
		for (i = 1; i <= n; i++) {
			str = br.readLine();
			energy[i] = Integer.parseInt(str);
		}
		
		// 간선 입력
		for (i = 0; i < n + 1; i++) {
			adj.add(new ArrayList<>());
			adjDistance.add(new ArrayList<>());
		}
		for (i = 0; i < n - 1; i++) {
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			adj.get(a).add(b);
			adjDistance.get(a).add(c);
			adj.get(b).add(a);
			adjDistance.get(b).add(c);
		}
		
		// 트리
		parents = new int[n + 1];
		distance = new int[n + 1];
		ans = new int[n + 1];
		roomNum.add(0);
		cumulativeDistance.add(0);
		parents[1] = 1;
		tree(1);
		
		// 출력
		for (i = 1; i <= n; i++) {
			sb.append(ans[i]).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}