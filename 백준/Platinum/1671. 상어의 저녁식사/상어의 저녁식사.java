import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static class Shark {
		int idx;
		int size;
		int speed;
		int intelligence;
		
		Shark(int idx, int size, int speed, int intelligence) {
			this.idx = idx;
			this.size = size;
			this.speed = speed;
			this.intelligence = intelligence;
		}
		
		public boolean isBiggerThan(Shark o) {
			if (this != o && this.size >= o.size && this.speed >= o.speed && this.intelligence >= o.intelligence) {
				if (this.size == o.size && this.speed == o.speed && this.intelligence == o.intelligence && this.idx > o.idx) {
					return false;
				}
				return true;
			}
			return false;
		}
	}
	
	private static int n;
	private static int[] prey;
	private static boolean[] visited;
	private static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
	
	private static boolean eat(int predator) {
		int i, idx;
		
		for (i = 0; i < adj.get(predator).size(); i++) {
			idx = adj.get(predator).get(i);
			if (visited[idx] || (prey[idx] != -1 && (prey[idx] == predator - n || prey[idx] == predator + n))) {
				continue;
			}
			visited[idx] = true;
			if (prey[idx] == -1 || eat(prey[idx])) {
				prey[idx] = predator;
				return true;
			}
		}
		return false;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int i, j, size, speed, intelligence, eaten;
		ArrayList<Shark> sharks = new ArrayList<>();
		
		n = Integer.parseInt(br.readLine());
		for (i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			size = Integer.parseInt(st.nextToken());
			speed = Integer.parseInt(st.nextToken());
			intelligence = Integer.parseInt(st.nextToken());
			sharks.add(new Shark(i, size, speed, intelligence));
		}
		
		for (i = 0; i < 2 * n; i++) {
			adj.add(new ArrayList<>());
		}
		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				if (sharks.get(i).isBiggerThan(sharks.get(j))) {
					adj.get(i).add(j);
					adj.get(i + n).add(j);
				}
			}
		}
		
		prey = new int[n];
		Arrays.fill(prey, -1);
		eaten = 0;
		for (i = 0; i < 2 * n; i++) {
			visited = new boolean[n];
			if (eat(i)) {
				eaten++;
			}
		}
		
		sb.append(n - eaten);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}