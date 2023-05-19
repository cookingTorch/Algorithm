import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	private static int departure, arrival;
	private static int[] maxLength, waysLeft, waysLeftR;
	private static boolean[] flag;
	private static Queue<int[]> queue;
	private static ArrayList<ArrayList<Integer>> adj, adjR, distances, distancesR;
	private static Set<ArrayList<Integer>> everyPaths;
	
	// 임계 경로 길이 탐색
	private static void bfs(int from, int idx, int node) {
		
		// 경로 갱신
		if (node != departure) {
			waysLeft[node]--;
			if (maxLength[from] + distances.get(from).get(idx) > maxLength[node]) {
				maxLength[node] = maxLength[from] + distances.get(from).get(idx);
			}
		}
		
		// 연결 노드 큐에 삽입
		if (waysLeft[node] == 0) {
			int i;
			for (i = 0; i < adj.get(node).size(); i++) {
				queue.add(new int[] {node, i, adj.get(node).get(i)});
			}
		}
		
		// 다음 순서 탐색
		if ((node != arrival || waysLeft[node] != 0) && !queue.isEmpty()) {
			int[] queueArray = queue.poll();
			bfs(queueArray[0], queueArray[1], queueArray[2]);
		}
	}
	
	// 임계 경로 개수 탐색
	private static void bfsR(int node) {
		
		// 연결 노드 탐색
		if (node != departure) {
			int i, next;
			for (i = 0; i < adjR.get(node).size(); i++) {
				next = adjR.get(node).get(i);
				waysLeftR[next]--;
				if (flag[node] && maxLength[node] - distancesR.get(node).get(i) == maxLength[next]) {
					ArrayList<Integer> path = new ArrayList<>();
					path.add(node);
					path.add(next);
					everyPaths.add(path);
					flag[next] = true;
				}
				if (waysLeftR[next] == 0) {
					queue.add(new int[] {next});
				}
			}
		}
		
		// 다음 순서 탐색
		if ((node != departure || waysLeft[node] != 0) && !queue.isEmpty()) {
			int[] queueNum = queue.poll();
			bfsR(queueNum[0]);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int n, m, i, start, end, distance;
		
		// 노드, 간선 입력
		str = br.readLine();
		n = Integer.parseInt(str);
		str = br.readLine();
		m = Integer.parseInt(str);
		waysLeft = new int[n + 1];
		waysLeftR = new int[n + 1];
		adj = new ArrayList<>();
		adjR = new ArrayList<>();
		distances = new ArrayList<>();
		distancesR = new ArrayList<>();
		for (i = 0; i <= n; i++) {
			adj.add(new ArrayList<>());
			adjR.add(new ArrayList<>());
			distances.add(new ArrayList<>());
			distancesR.add(new ArrayList<>());
		}
		for (i = 0; i < m; i++) {
			str = br.readLine();
			st = new StringTokenizer(str);
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			distance = Integer.parseInt(st.nextToken());
			adj.get(start).add(end);
			adjR.get(end).add(start);
			distances.get(start).add(distance);
			distancesR.get(end).add(distance);
			waysLeft[end]++;
			waysLeftR[start]++;
		}
		
		// 출발, 도착 지점 입력
		str = br.readLine();
		st = new StringTokenizer(str);
		departure = Integer.parseInt(st.nextToken());
		arrival = Integer.parseInt(st.nextToken());
		
		// 초기화
		maxLength = new int[n + 1];
		flag = new boolean[n + 1];
		flag[arrival] = true;
		waysLeft[departure] = 0;
		queue = new LinkedList<>();
		everyPaths = new HashSet<>();
		
		// 임계 경로 탐색
		bfs(0, 0, departure);
		queue.clear();
		bfsR(arrival);
		
		// 출력
		sb.append(maxLength[arrival]).append("\n").append(everyPaths.size());
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

}