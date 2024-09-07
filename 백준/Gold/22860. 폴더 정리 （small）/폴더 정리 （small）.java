import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	private static final char FOLDER = '1';
	private static final char SPACE = ' ';
	private static final char LINE_BREAK = '\n';
	private static final String MAIN = "main";

	private static final class Edge {
		int to;
		Edge next;

		Edge(int to, Edge next) {
			this.to = to;
			this.next = next;
		}
	}

	private static int idx;
	private static int[] fileNum;
	private static Edge[] adj;
	private static HashSet<String>[] files;
	private static HashMap<String, Integer> map;

	private static void dfs(int parent, int curr) {
		Edge edge;

		for (edge = adj[curr]; edge != null; edge = edge.next) {
			dfs(curr, edge.to);
		}
		fileNum[parent] += fileNum[curr];
		for (String file : files[curr]) {
			files[parent].add(file);
		}
	}

	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int q;
		int i;
		int nm;
		int parent;
		int child;
		int root;
		char isFolder;
		Edge edge;
		String parentStr;
		String childStr;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		fileNum = new int[n + 2];
		files = new HashSet[n + 2];
		for (i = 1; i <= n + 1; i++) {
			files[i] = new HashSet<>();
		}
		adj = new Edge[n + 2];
		nm = n + m;
		idx = 1;
		map = new HashMap<>();
		for (i = 0; i < nm; i++) {
			st = new StringTokenizer(br.readLine());
			parentStr = st.nextToken();
			childStr = st.nextToken();
			isFolder = st.nextToken().charAt(0);
			if (!map.containsKey(parentStr)) {
				map.put(parentStr, idx++);
			}
			parent = map.get(parentStr);
			if (isFolder == FOLDER) {
				if (!map.containsKey(childStr)) {
					map.put(childStr, idx++);
				}
				child = map.get(childStr);
				adj[parent] = new Edge(child, adj[parent]);
			} else {
				fileNum[parent]++;
				files[parent].add(childStr);
			}
		}
		for (edge = adj[root = map.get(MAIN)]; edge != null; edge = edge.next) {
			dfs(root, edge.to);
		}
		q = Integer.parseInt(br.readLine());
		sb = new StringBuilder();
		while (q-- > 0) {
			st = new StringTokenizer(br.readLine(), "/");
			for (parentStr = st.nextToken(); st.hasMoreTokens(); parentStr = st.nextToken());
			parent = map.get(parentStr);
			sb.append(files[parent].size()).append(SPACE).append(fileNum[parent]).append(LINE_BREAK);
		}
		System.out.print(sb.toString());
	}
}
