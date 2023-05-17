import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	private static int v, vertexNum = 0, sccCnt = 0;
	private static int[] order;
	private static boolean[] finished;
	private static Stack<Integer> stack = new Stack<>();
	private static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
	private static ArrayList<ArrayList<Integer>> scc = new ArrayList<>();
	
	// DFS
	private static int findSCC(int node) {
		int minOrder, stackNum;
		order[node] = ++vertexNum;
		stack.push(node);
		minOrder = order[node];
		
		// 연결된 노드 확인
		for (int next : adj.get(node)) {
			if (order[next] == 0) {
				minOrder = Math.min(minOrder, findSCC(next));
			}
			else if (!finished[next]) {
				minOrder = Math.min(minOrder, order[next]);
			}
		}
		
		// 루트 노드일 경우 새로운 SCC 형성
		if (minOrder == order[node]) {
			scc.add(0, new ArrayList<>());
			do {
				stackNum = stack.pop();
				scc.get(0).add(stackNum);
				finished[stackNum] = true;
			} while (stackNum != node);
			Collections.sort(scc.get(0));
			sccCnt++;
		}
		return minOrder;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int e, i, a, b;
		
		// V, E 입력
		str = br.readLine();
		st = new StringTokenizer(str);
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		// 간선 긋기
		for (i = 0; i < v + 1; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < e; i++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			adj.get(a).add(b);
		}
		
		// SCC 탐색
		order = new int[v + 1];
		finished = new boolean[v + 1];
		for (i = 1; i <= v; i++) {
			if (!finished[i]) {
				findSCC(i);
			}
		}
		
		// 출력
		Collections.sort(scc, new Comparator<ArrayList<Integer>>() {
			@Override
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				return o1.get(0) - o2.get(0);
			}
		});
		sb.append(sccCnt);
		for (i = 0; i < sccCnt; i++) {
			sb.append("\n");
			for (int vertex : scc.get(i)) {
				sb.append(vertex).append(" ");
			}
			sb.append("-1");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}