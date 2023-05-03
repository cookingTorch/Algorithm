import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int[] parents, distance;
	
	// 루트 노드
	private static int root(int node) {
		if (parents[node] == node) {
			return node;
		}
		else {
			int ancestor;
			ancestor = root(parents[node]);
			distance[node] += distance[parents[node]];
			parents[node] = ancestor;
			return parents[node];
		}
	}
	
	// 트리 병합
	private static void union(int node1, int node2, int weightDifference) {
		if (root(node1) != root(node2)) {
			distance[parents[node2]] = distance[node1] + weightDifference - distance[node2];
			parents[parents[node2]] = parents[node1];
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int n, m, i, a, b, w;
		String punctuationMark;
		
		do {
			// 초기화
			str = br.readLine();
			st = new StringTokenizer(str, " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			if (n == 0 && m == 0) {
				break;
			}
			parents = new int[100001];
			for (i = 1; i <= n; i++) {
				parents[i] = i;
			}
			distance = new int[100001];
			
			// 쿼리 수행
			for (i = 0; i < m; i++) {
				str = br.readLine();
				st = new StringTokenizer(str, " ");
				punctuationMark = st.nextToken();
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				if (punctuationMark.charAt(0) == '!') {
					w = Integer.parseInt(st.nextToken());
					union(a, b, w);
				}
				else {
					if (root(a) == root(b)) {
						sb.append(distance[b] - distance[a]).append("\n");
					}
					else {
						sb.append("UNKNOWN").append("\n");
					}
				}
			}
		} while (n != 0 || m != 0);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

}