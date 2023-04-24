import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	static int[] matchA, matchB;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
	
	// 이분 매칭 매서드
	private static boolean bipartiteMatching(int a) {
		
		if (visited[a]) {
			return false;
		}
		visited[a] = true;
		for (int b : adj.get(a)) {
			if (matchB[b] == -1 || bipartiteMatching(matchB[b])) {
				matchA[a] = b;
				matchB[b] = a;
				return true;	
			}
		}
		return false;
		
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		String str;
		StringTokenizer st;
		
		int n, num, i, j, flag = 1, numAcnt = -1, numBcnt = -1, matchCnt;
		boolean isFirstOdd = true;
		int[] numA, numB;
		boolean[] isPrime = new boolean[2000];
		ArrayList<Integer> firstAdj = new ArrayList<>();
		ArrayList<Integer> ansArray = new ArrayList<>();
		
		str = br.readLine();
		n = Integer.parseInt(str);
		numA = new int[n / 2];
		numB = new int[n / 2];
		matchA = new int[n / 2 - 1];
		matchB = new int[n / 2];
		visited = new boolean[n / 2 - 1];
		
		// 홀짝 나누기
		str = br.readLine();
		st = new StringTokenizer(str, " ");
		for (i = 0; i < n; i++) {
			num = Integer.parseInt(st.nextToken());
			if (num % 2 == 1) {
				if (isFirstOdd) {
					numAcnt++;
					if (numAcnt >= n / 2) {
						flag--;
						break;
					}
					numA[numAcnt] = num;
				}
				else {
					numBcnt++;
					if (numBcnt >= n / 2) {
						flag--;
						break;
					}
					numB[numBcnt] = num;
				}
			}
			else {
				if (i == 0) {
					isFirstOdd = false;
				}
				if (isFirstOdd) {
					numBcnt++;
					if (numBcnt >= n / 2) {
						flag--;
						break;
					}
					numB[numBcnt] = num;
				}
				else {
					numAcnt++;
					if (numAcnt >= n / 2) {
						flag--;
						break;
					}
					numA[numAcnt] = num;
				}
			}
		}
		
		if (flag > 0) {
			
			// 소수 저장
			for (i = 2; i < 2000; i++) {
				isPrime[i] = true;
			}
			for (i = 2; i <= 44; i++) {
				if (isPrime[i]) {
					for (j = i * i; j < 2000; j += i) {
						isPrime[j] = false;
					}
				}
			}
			
			// 첫번째 원소 간선
			for (i = 0; i < n / 2; i++) {
				if (isPrime[numA[0] + numB[i]]) {
					firstAdj.add(i);
				}
			}
			
			for (int k : firstAdj) {
				
				Arrays.fill(matchA, -1);
				Arrays.fill(matchB, -1);
				
				// 간선 추가
				adj.clear();
				for (i = 0; i < n / 2 - 1; i++) {
					adj.add(new ArrayList<>());
				}
				for (i = 1; i < n / 2; i++) {
					for (j = 0; j < n / 2; j++) {
						if (j != k && isPrime[numA[i] + numB[j]]) {
							adj.get(i - 1).add(j);
						}
					}
				}
				
				// 이분 매칭
				matchCnt = 0;
				for (i = 0; i < n / 2 - 1; i++) {
					Arrays.fill(visited, false);
					if (bipartiteMatching(i)) {
						matchCnt++;
					}
				}
				if (matchCnt == n / 2 - 1) {
					ansArray.add(numB[k]);
				}
				
			}
			
			// 답이 존재하지 않음
			if (ansArray.size() == 0) {
				flag--;
			}
			
		}	
			
		// 출력
		if (flag > 0) {
			Collections.sort(ansArray);
			for (int ans : ansArray) {
				sb.append(ans).append(" ");
			}
		}
		else {
			sb.append(-1);
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

}