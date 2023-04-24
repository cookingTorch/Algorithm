import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[] minsu;
	static int[] parent;
	
	private static int root(int a) {
		if (parent[a] == a) {
			return a;
		}
		else {
			return root(parent[a]);
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int n, m, k, i, chulsu, left, right, mid, card;
		int[] idx;
		
		// n, m, k 입력
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		minsu = new int[m];
		parent = new int[n + 1];
		idx = new int[n + 1];
		
		// 민수 카드
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		for (i = 0; i < m; i++) {
			minsu[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(minsu);
		for (int p : minsu) {
			parent[p] = p;
		}
		for (i = 0; i < m; i++) {
			idx[minsu[i]] = i;
		}
		
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		right = -1;
		for (i = 0; i < k; i++) {
			
			// 철수 카드
			chulsu = Integer.parseInt(st.nextToken());
			
			// upperBound 이분 탐색
			left = 0;
			right = m;
			while (left < right) {
				mid = (left + right) >> 1;
				if (minsu[mid] > chulsu) {
					right = mid;
				}
				else {
					left = mid + 1;
				}
			}
			
			// 카드 내고 버리기
			card = root(minsu[right]);
			sb.append(card).append("\n");
			if (i < k - 1) {
				parent[card] = minsu[idx[card] + 1];
			}
			
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

}