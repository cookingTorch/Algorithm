import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	private static int[] roots;
	
	private static int find(int v) {
		if (roots[v] <= 0) {
			return v;
		}
		return roots[v] = find(roots[v]);
	}
	
	private static void union(int u, int v) {
		int ru, rv;
		
		if ((ru = find(u)) == (rv = find(v))) {
			return;
		}
		if (roots[ru] > roots[rv]) {
			roots[ru] = rv;
		} else {
			if (roots[ru] == roots[rv]) {
				roots[ru]--;
			}
			roots[rv] = ru;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n, m, size, first, cnt, i, j;
		ArrayList<Integer> party;
		ArrayList<ArrayList<Integer>> parties;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		roots = new int[n + 2];
		st = new StringTokenizer(br.readLine());
		size = Integer.parseInt(st.nextToken());
		for (i = 0; i < size; i++) {
			union(n + 1, Integer.parseInt(st.nextToken()));
		}
		parties = new ArrayList<>(m);
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			size = Integer.parseInt(st.nextToken());
			party = new ArrayList<>(size);
			for (j = 0; j < size; j++) {
				party.add(Integer.parseInt(st.nextToken()));
			}
			parties.add(party);
		}
		for (i = 0; i < m; i++) {
			if ((size = (party = parties.get(i)).size()) > 0) {
				first = party.get(0);
				for (j = 1; j < size; j++) {
					union(first, party.get(j));
				}
			}
		}
		cnt = 0;
		loop:
		for (i = 0; i < m; i++) {
			for (int person : parties.get(i)) {
				if (find(person) == find(n + 1)) {
					continue loop;
				}
			}
			cnt++;
		}
		System.out.print(cnt);
	}
}
